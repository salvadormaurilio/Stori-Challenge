package mx.android.storichallenge.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.android.storichallenge.core.ui.intentTo
import mx.android.storichallenge.ui.movement.MovementDetailActivity

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            collectMovementDetailAction()
        }
    }

    private suspend fun collectMovementDetailAction() {
        homeViewModel.navigateToMovementDetail
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .collect { openSignUpActivity() }
    }

    private fun openSignUpActivity() {
        startActivity(intentTo<MovementDetailActivity>())
    }
}
