package mx.android.storichallenge.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.ui.home.MovementUi
import mx.android.storichallenge.ui.home.givenMovementUi1
import mx.android.storichallenge.ui.movement.ANY_MOVEMENT_CATEGORY
import mx.android.storichallenge.ui.theme.Space12
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space2
import mx.android.storichallenge.ui.theme.Space8
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@Composable
fun LabelMovement(modifier: Modifier = Modifier, movementUi: MovementUi) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = {}
            )
            .padding(start = Space16, end = Space16)
    ) {
        Spacer(modifier = modifier.height(Space12))
        Row {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = movementUi.name,
                    style = MaterialTheme.typography.labelLarge

                )
                Spacer(modifier = Modifier.height(Space2))
                Text(
                    text = movementUi.date,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                text = movementUi.amount,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(Space12))
        Divider()
    }
}

@Composable
fun LabelMovementDetail(modifier: Modifier = Modifier, @StringRes title: Int, text: String) {
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
        Box(modifier = Modifier.padding(top = Space16, bottom = Space16)) {
            LabelMovement(movementUi = givenMovementUi1())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabelMovementDetailPreview() {
    StoriChallengeTheme {
        Column(modifier = Modifier.padding(all = Space16)) {
            LabelMovementDetail(
                title = R.string.category,
                text = ANY_MOVEMENT_CATEGORY
            )
        }
    }
}
