package mx.android.storichallenge.ui.home

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
import mx.android.storichallenge.ui.movement.MovementDetailActivity.Companion.intentToMovementDetailActivity
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        lifecycleScope.launch {
            collectMovementDetailAction()
        }
    }

    private fun initContent() {
        setContent {
            StoriChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(userDataUi = givenUserDataUi(),
                        onMovementClick = { homeViewModel.navigateToMovementDetail(it) })
                }
            }
        }
    }

    private suspend fun collectMovementDetailAction() {
        homeViewModel.navigateToMovementDetail
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .collect { openMovementDetailActivity(it) }
    }

    private fun openMovementDetailActivity(movementId: String) {
        startActivity(intentToMovementDetailActivity(movementId))
    }
}
