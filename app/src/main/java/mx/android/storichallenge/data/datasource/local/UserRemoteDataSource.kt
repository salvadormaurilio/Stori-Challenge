package mx.android.storichallenge.data.datasource.local

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import mx.android.storichallenge.data.datasource.exception.AuthException
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                               private val firebaseFirestore: FirebaseFirestore) {

    fun storeUserData(userDataMap: Map<String, String>): Flow<Result<String>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(STORI_COLLECTION)
                .document(userId)
                .set(userDataMap)
                .addOnSuccessListener {
                    trySend(Result.success(userId))
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    trySend(Result.failure(AuthException.SignInException()))
                }
    }

    suspend fun getUserData(): Flow<Result<Map<String, Any>>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(STORI_COLLECTION)
                .document(userId)
                .get()
                .addOnSuccessListener {
                    trySend(Result.success(it.data.orEmpty()))
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    trySend(Result.failure(AuthException.SignInException()))
                }
    }

    companion object {

        private const val STORI_COLLECTION = "stori"

    }
}