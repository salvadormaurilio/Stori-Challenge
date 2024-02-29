package mx.android.storichallenge.ui.singup

import android.net.Uri
import android.os.Bundle
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
import mx.android.storichallenge.ui.home.HomeActivity
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import java.io.File

@AndroidEntryPoint
class SingUpActivity : ComponentActivity() {

    private val singUpViewModel by viewModels<SingUpViewModel>()

    private var latestTmpUri: Uri? = null

    private val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            latestTmpUri?.run { singUpViewModel.loadPictureIdentification(this.toString()) }
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
        val pictureIdentificationUiState by singUpViewModel.pictureIdentificationUiState.collectAsState()
        SigUpScreen(
            pictureIdentification = pictureIdentificationUiState,
            signInUiState = signInUiState,
            onProfileIdentification = { takeImage() },
            onSignUpButtonClick = { singUpViewModel.singUp(it) },
            onSingUpSuccess = { singUpViewModel.navigateToHome() },
            onBackPressedClick = { onBackPressedDispatcher.onBackPressed() }
        )
    }

    private fun takeImage() {
        latestTmpUri = getTmpFileUri()
        takeImageResult.launch(latestTmpUri)

    }

    private fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile(DEFAULT_IMAGE_PREFIX, DEFAULT_IMAGE_SUFFIX, cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return FileProvider.getUriForFile(applicationContext, DEFAULT_IMAGE_AUTHORITY, tmpFile)
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

    companion object{
        private const val DEFAULT_IMAGE_PREFIX = "image_picture_identification"
        private const val DEFAULT_IMAGE_SUFFIX = ".png"
        private const val DEFAULT_IMAGE_AUTHORITY = "${BuildConfig.APPLICATION_ID}.provider"
    }
}
