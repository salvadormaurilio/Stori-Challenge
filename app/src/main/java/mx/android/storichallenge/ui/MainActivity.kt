package mx.android.storichallenge.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import mx.android.storichallenge.data.datasource.model.UserDataResponse
import mx.android.storichallenge.domain.model.toUserData
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoriChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }

        val firebaseFirestore: FirebaseFirestore = Firebase.firestore

        firebaseFirestore.collection("users")
            .document("MaKNkzR0ySOFAd9PS4IFxnW5rpX2")
            .get()
            .addOnSuccessListener {
                val data = it.toObject<UserDataResponse>()
                val userData = data.toUserData()
                Log.d("my data", data.toString())
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StoriChallengeTheme {
        Greeting("Android")
    }
}