package mx.android.storichallenge.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import mx.android.storichallenge.data.datasource.exception.AuthException
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    fun signIn(email: String, password: String): Flow<Result<String>> = callbackFlow {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(Result.success(it.user?.uid.orEmpty()))
            }
            .addOnFailureListener {
                it.printStackTrace()
                trySend(Result.failure(AuthException.SignInException()))
            }
        awaitClose { close() }
    }

    fun signUp(email: String, password: String): Flow<Result<String>> = callbackFlow {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(Result.success(it.user?.uid.orEmpty()))
            }
            .addOnFailureListener {
                it.printStackTrace()
                trySend(Result.failure(AuthException.SignUpException()))
            }
        awaitClose { close() }
    }
}
