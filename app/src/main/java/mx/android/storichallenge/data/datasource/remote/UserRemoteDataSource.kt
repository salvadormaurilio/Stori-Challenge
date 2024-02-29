package mx.android.storichallenge.data.datasource.remote

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.data.datasource.model.MovementDetailResponse
import mx.android.storichallenge.data.datasource.model.UserDataResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseFirestore: FirebaseFirestore
) {

    fun storagePictureIdentification(pictureIdentification: String): Flow<Result<String>> = callbackFlow {
        val uri = Uri.parse(pictureIdentification)
        val mountainImagesRef = firebaseStorage.reference.child("$PATH_IMAGE/${uri.lastPathSegment}")
        mountainImagesRef.putFile(uri)
            .addOnSuccessListener {
                mountainImagesRef.downloadUrl.addOnSuccessListener {
                    trySend(Result.success(it.toString()))
                }.addOnFailureListener {
                    trySend(Result.failure(UserException.StorePictureIdentificationException()))
                }
            }.addOnFailureListener {
                it.printStackTrace()
                trySend(Result.failure(UserException.StorePictureIdentificationException()))
            }
        awaitClose { close() }
    }

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
        awaitClose { close() }
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
        awaitClose { close() }
    }

    fun getMovementDetail(movementId: String): Flow<Result<MovementDetailResponse?>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(MOVEMENTS_COLLECTION)
            .document(movementId)
            .get()
            .addOnSuccessListener {
                trySend(if (it.exists()) Result.success(it.toObject<MovementDetailResponse>()) else Result.failure(UserException.GetUserDataException()))
            }
            .addOnFailureListener {
                it.printStackTrace()
                trySend(Result.failure(UserException.GetUserDataException()))
            }
        awaitClose { close() }
    }

    companion object {
        const val USERS_COLLECTION = "users"
        const val MOVEMENTS_COLLECTION = "movements"
        const val PATH_IMAGE = "images"
    }
}
