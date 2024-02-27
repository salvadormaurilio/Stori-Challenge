package mx.android.storichallenge.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.data.datasource.model.UserDataResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                               private val firebaseFirestore: FirebaseFirestore) {

    fun storeUserData(userDataMap: Map<String, String>): Flow<Result<String>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
                .document(userId)
                .set(userDataMap)
                .addOnSuccessListener {
                    trySend(Result.success(userId))
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    trySend(Result.failure(UserException.StoreUserDataException()))
                }
    }

    fun getUserData(): Flow<Result<UserDataResponse?>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
                .document(userId)
                .get()
                .addOnSuccessListener {
                    trySend(if (it.exists()) Result.success(it.toObject<UserDataResponse>()) else Result.failure(UserException.GetUserDataException()))
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    trySend(Result.failure(UserException.GetUserDataException()))
                }
    }

    companion object {

        private const val USERS_COLLECTION = "users"
        private const val MOVEMENTS_COLLECTION = "movements"

    }
}