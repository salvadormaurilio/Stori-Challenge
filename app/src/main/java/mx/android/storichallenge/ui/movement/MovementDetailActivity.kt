package mx.android.storichallenge.ui.movement

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import mx.android.storichallenge.core.ui.intentTo
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@AndroidEntryPoint
class MovementDetailActivity : ComponentActivity() {

    private val movementDetailViewModel by viewModels<MovementDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        movementDetailViewModel.getMovementDetail(getMovementId())
    }

    private fun initContent() {
        setContent {
            StoriChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovementDetailScreen(movementDetailUi = givenMovementDetailUi())
                }
            }
        }
    }

    private fun getMovementId() = intent?.getStringExtra(MOVEMENT_ID).orEmpty()

    companion object {
        private const val MOVEMENT_ID = "movement_id"

        fun Context.intentToMovementDetailActivity(movementId: String) =
            intentTo<MovementDetailActivity>()
                .putExtra(MOVEMENT_ID, movementId)
    }
}
