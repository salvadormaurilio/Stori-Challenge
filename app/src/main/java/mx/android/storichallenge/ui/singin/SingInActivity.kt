package mx.android.storichallenge.ui.singin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.android.storichallenge.core.ui.intentTo
import mx.android.storichallenge.ui.singup.SingUpActivity
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@AndroidEntryPoint
class SingInActivity : ComponentActivity() {

    private val singInViewModel by viewModels<SingInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        lifecycleScope.launch {
            collectSingUpAction()
        }
    }

    private fun initContent() {
        setContent {
            StoriChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SigInScreen(
                        onSignInButtonClick = { email, password -> singInViewModel.signIn(email, password) },
                        onSignUpButtonClick = { singInViewModel.navigateToSingUp() }
                    )
                }
            }
        }
    }

    private suspend fun collectSingUpAction() {
        singInViewModel.navigateToSingUp
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .collect { openSignUpActivity() }
    }

    private fun openSignUpActivity() {
        startActivity(intentTo<SingUpActivity>())
    }
}


