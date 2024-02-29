package mx.android.storichallenge.ui.movement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.core.ui.empty
import mx.android.storichallenge.ui.composable.LabelMovement
import mx.android.storichallenge.ui.theme.BlueGrey800
import mx.android.storichallenge.ui.theme.Space12
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.Space80
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementDetailScreen(modifier: Modifier = Modifier, movementDetailUi: MovementDetailUi) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            title = {
                Text(
                    text = stringResource(id = R.string.movement_detail),
                    color = White800
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = String.empty()
                    )
                }
            }
        )
    }) {
        MovementDetailContent(
            modifier = modifier.padding(paddingValues = it),
            movementDetailUi = movementDetailUi
        )
    }
}

@Composable
fun MovementDetailContent(modifier: Modifier = Modifier, movementDetailUi: MovementDetailUi) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = Space32,
                bottom = Space32,
                start = Space24,
                end = Space24
            )
    ) {
        Icon(
            modifier = Modifier
                .size(size = Space80)
                .clip(shape = RoundedCornerShape(Space80))
                .align(alignment = Alignment.CenterHorizontally)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true)
                ) {},
            imageVector = Icons.Filled.MonetizationOn,
            tint = BlueGrey800,
            contentDescription = String.empty()
        )
        Spacer(modifier = Modifier.height(Space16))
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = movementDetailUi.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Space12))
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = movementDetailUi.amount,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(Space12))
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = movementDetailUi.date,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(Space32))
        LabelMovement(
            title = R.string.card,
            text = movementDetailUi.card
        )
        LabelMovement(
            title = R.string.category,
            text = movementDetailUi.category
        )
        LabelMovement(
            title = R.string.reference,
            text = movementDetailUi.reference
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovementDetailScreenPreview() {
    StoriChallengeTheme {
        MovementDetailScreen(movementDetailUi = givenMovementDetailUi())
    }
}
