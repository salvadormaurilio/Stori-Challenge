package mx.android.storichallenge.data.datasource.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.collectAndCancel
import mx.android.storichallenge.fakedata.ANY_MOVEMENT_ID
import mx.android.storichallenge.fakedata.ANY_USER_ID
import mx.android.storichallenge.fakedata.givenUserDataMap
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserRemoteDataShould {

    private val firebaseAuth = mock<FirebaseAuth>()
    private val firebaseUser = mock<FirebaseUser>()
    private val firebaseStorage = mock<FirebaseStorage>()
    private val firebaseFirestore = mock<FirebaseFirestore>()
    private val collectionReference = mock<CollectionReference>()
    private val documentReference = mock<DocumentReference>()
    private val documentSnapshotTask = mock<Task<DocumentSnapshot>>()
    private val voidTask = mock<Task<Void>>()

    private lateinit var authRemoteDataSource: UserRemoteDataSource

    @Before
    fun setup() {
        authRemoteDataSource = UserRemoteDataSource(firebaseAuth, firebaseStorage, firebaseFirestore)
    }

    @Test
    fun `Call store user data collection when storeUserData is called`() = runTest {
        val userDataMap = givenUserDataMap()

        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)
        whenever(firebaseUser.uid).thenReturn(ANY_USER_ID)

        whenever(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_USER_ID)).thenReturn(documentReference)
        whenever(documentReference.set(userDataMap)).thenReturn(voidTask)
        whenever(voidTask.addOnSuccessListener(any())).thenReturn(voidTask)

        authRemoteDataSource.storeUserData(userDataMap).collectAndCancel()

        verify(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION).document(ANY_USER_ID)).set(userDataMap)
    }

    @Test
    fun `Call get user data collection when getUserData is called`() = runTest {
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)
        whenever(firebaseUser.uid).thenReturn(ANY_USER_ID)

        whenever(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_USER_ID)).thenReturn(documentReference)
        whenever(documentReference.get()).thenReturn(documentSnapshotTask)
        whenever(documentSnapshotTask.addOnSuccessListener(any())).thenReturn(documentSnapshotTask)

        authRemoteDataSource.getUserData().collectAndCancel()

        verify(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION).document(ANY_USER_ID)).get()
    }

    @Test
    fun `Call get movement detail data collection when getMovementDetail is called`() = runTest {
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)
        whenever(firebaseUser.uid).thenReturn(ANY_USER_ID)

        whenever(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_USER_ID)).thenReturn(documentReference)
        whenever(documentReference.collection(UserRemoteDataSource.MOVEMENTS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_MOVEMENT_ID)).thenReturn(documentReference)
        whenever(documentReference.get()).thenReturn(documentSnapshotTask)
        whenever(documentSnapshotTask.addOnSuccessListener(any())).thenReturn(documentSnapshotTask)

        authRemoteDataSource.getMovementDetail(ANY_MOVEMENT_ID).collectAndCancel()

        verify(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION).document(ANY_USER_ID)).get()
    }
}
