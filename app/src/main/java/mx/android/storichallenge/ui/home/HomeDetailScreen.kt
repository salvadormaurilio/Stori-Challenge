package mx.android.storichallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.core.ui.empty
import mx.android.storichallenge.ui.composable.LabelMovement
import mx.android.storichallenge.ui.theme.BlueGrey500
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space2
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@Composable
fun HomeScreen(modifier: Modifier = Modifier, userDataUi: UserDataUi) {
    Scaffold(topBar = {
        HomeTopAppBar(
            firstName = userDataUi.firstName,
            lastName = userDataUi.lastName,
            email = userDataUi.email
        )
    }) {
        HomeContent(
            modifier = modifier.padding(paddingValues = it),
            userDataUi = userDataUi
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar(firstName: String, lastName: String, email: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Column {
                Text(
                    text = stringResource(id = R.string.user_name, firstName, lastName),
                    color = White800,
                    style = MaterialTheme.typography.titleMedium

                )
                Spacer(modifier = Modifier.height(Space2))
                Text(
                    text = stringResource(id = R.string.user_email, email),
                    color = White800,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = BlueGrey500,
                    contentDescription = String.empty()
                )
            }
        }
    )
}

@Composable
fun HomeContent(modifier: Modifier = Modifier, userDataUi: UserDataUi) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = Space24,
                bottom = Space24
            )
    ) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.movements),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(Space16))
        }
        items(
            items = userDataUi.movements,
            key = { it.id }
        ) {
            LabelMovement(movementUi = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    StoriChallengeTheme {
        HomeScreen(userDataUi = givenUserDataUi())
    }
}
