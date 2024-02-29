package mx.android.storichallenge.ui.singup

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.android.storichallenge.BuildConfig
import mx.android.storichallenge.core.ui.intentToAndClearStack
import mx.android.storichallenge.ui.home.ANY_PICTURE_IDENTIFICATION
import mx.android.storichallenge.ui.home.HomeActivity
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import java.io.File

@AndroidEntryPoint
class SingUpActivity : ComponentActivity() {

    private val singUpViewModel by viewModels<SingUpViewModel>()

    private var latestTmpUri: Uri? = null


    private val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            latestTmpUri?.let { uri ->
                Log.d("SingUpActivity", "Takye picture success ${uri.path}")
            }
        }
    }

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
            pictureIdentification = ANY_PICTURE_IDENTIFICATION,
            signInUiState = signInUiState,
            onProfileIdentification = { takeImage() },
            onSignUpButtonClick = { singUpViewModel.singUp(it) },
            onSingUpSuccess = { singUpViewModel.navigateToHome() },
            onBackPressedClick = { onBackPressedDispatcher.onBackPressed() }
        )
    }

    private fun takeImage() {
        getTmpFileUri().let { uri ->
            latestTmpUri = uri
            takeImageResult.launch(uri)
        }
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png", cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(applicationContext, "${BuildConfig.APPLICATION_ID}.provider", tmpFile)
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
