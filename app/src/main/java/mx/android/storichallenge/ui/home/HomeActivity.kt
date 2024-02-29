package mx.android.storichallenge.ui.home

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
import mx.android.storichallenge.ui.movement.MovementDetailActivity.Companion.intentToMovementDetailActivity
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        collectMovementDetailAction()
        homeViewModel.getUserData()

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
        val userDataUiState by homeViewModel.userDataUiState.collectAsState()
        userDataUiState?.run {
            HomeScreen(
                userDataUiState = this,
                onMovementClick = { homeViewModel.navigateToMovementDetail(it) })
        }
    }

    private fun collectMovementDetailAction() {
        lifecycleScope.launch {
            homeViewModel.navigateToMovementDetail
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { openMovementDetailActivity(it) }
        }
    }

    private fun openMovementDetailActivity(movementId: String) {
        startActivity(intentToMovementDetailActivity(movementId))
    }
}
