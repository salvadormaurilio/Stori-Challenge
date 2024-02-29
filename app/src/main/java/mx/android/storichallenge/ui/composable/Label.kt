package mx.android.storichallenge.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.ui.movement.ANY_MOVEMENT_CATEGORY
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space2
import mx.android.storichallenge.ui.theme.Space8
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@Composable
fun LabelMovement(modifier: Modifier = Modifier, @StringRes title: Int, text: String) {
    Spacer(modifier = modifier.height(Space8))
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.labelMedium

    )
    Spacer(modifier = Modifier.height(Space2))
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(Space8))
    Divider()
}

@Preview(showBackground = true)
@Composable
fun LabelMovementPreview() {
    StoriChallengeTheme {
        Column(modifier = Modifier.padding(all = Space16)) {
            LabelMovement(title = R.string.category, text = ANY_MOVEMENT_CATEGORY)
        }
    }
}
