package mx.android.storichallenge.ui.singin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        collectSingUpAction()
    }

    private fun initContent() {
        setContent {
            StoriChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InitContentWithUiState()
                }
            }
        }
    }

    @Composable
    private fun InitContentWithUiState() {
        val signInUiState by singInViewModel.signInUiState.collectAsState()

        SigInScreen(
            signInUiState = signInUiState,
            onSignInButtonClick = { email, password -> singInViewModel.signIn(email, password) },
            onSingInSuccess = {  },
            onSignUpButtonClick = { singInViewModel.navigateToSingUp() }
        )

    }

    private fun collectSingUpAction() {
        lifecycleScope.launch {
            singInViewModel.navigateToSingUp
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { openSignUpActivity() }
        }
    }

    private fun openSignUpActivity() {
        startActivity(intentTo<SingUpActivity>())
    }
}


