package mx.android.storichallenge.ui.singup

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
import mx.android.storichallenge.core.ui.intentToAndClearStack
import mx.android.storichallenge.ui.home.HomeActivity
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@AndroidEntryPoint
class SingUpActivity : ComponentActivity() {

    private val singUpViewModel by viewModels<SingUpViewModel>()

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
        val signInUiState by singUpViewModel.signUpUiState.collectAsState()

        SigUpScreen(
            signInUiState = signInUiState,
            onSignUpButtonClick = { singUpViewModel.singUp(it) },
            onSingUpSuccess = { singUpViewModel.navigateToHome() },
            onBackPressedClick = { onBackPressedDispatcher.onBackPressed() }
        )
    }

    private fun collectSingUpAction() {
        lifecycleScope.launch {
            singUpViewModel.navigateToHome
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { openHomeActivity() }
        }
    }

    private fun openHomeActivity() {
        startActivity(intentToAndClearStack<HomeActivity>())
    }
}
