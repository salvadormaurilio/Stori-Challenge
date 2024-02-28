package mx.android.storichallenge.data

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.data.datasource.model.MovementDetailResponse
import mx.android.storichallenge.data.datasource.model.UserDataResponse
import mx.android.storichallenge.data.datasource.remote.UserRemoteDataSource
import mx.android.storichallenge.fakedata.ANY_MOVEMENT_ID
import mx.android.storichallenge.fakedata.givenMovementDetail
import mx.android.storichallenge.fakedata.givenMovementDetailResponse
import mx.android.storichallenge.fakedata.givenUserData
import mx.android.storichallenge.fakedata.givenUserDataResponse
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserRepositoryShould {

    private val userRemoteDataSource = mock<UserRemoteDataSource>()

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        userRepository = UserRepository(userRemoteDataSource)
    }

    @Test
    fun `Return UserData when getUserData is called and getUserData in datasource is success`() = runTest {
        val userDataResponse = givenUserDataResponse()
        val userData = givenUserData()
        val resultUserDataResponse = Result.success(userDataResponse)

        whenever(userRemoteDataSource.getUserData()).thenReturn(flowOf(resultUserDataResponse))

        val result = userRepository.getUserData().lastOrNull()

        verify(userRemoteDataSource).getUserData()
        assertThatEquals(result?.getOrNull(), userData)
    }

    @Test
    fun `Get GetUserDataException when signUp is called and signUp in datasource is failure`() = runTest {
        val resultSGetUserDataException: Result<UserDataResponse> = Result.failure(UserException.GetUserDataException())

        whenever(userRemoteDataSource.getUserData()).thenReturn(flowOf(resultSGetUserDataException))

        val result = userRepository.getUserData().lastOrNull()

        verify(userRemoteDataSource).getUserData()
        assertThatIsInstanceOf<UserException.GetUserDataException>(result?.exceptionOrNull())
    }

    @Test
    fun `Return MovementDetail when getMovementDetail is called and getMovementDetail in datasource is success`() = runTest {
        val movementDetailResponse = givenMovementDetailResponse()
        val movementDetail = givenMovementDetail()
        val resultMovementDetailResponse = Result.success(movementDetailResponse)

        whenever(userRemoteDataSource.getMovementDetail(ANY_MOVEMENT_ID)).thenReturn(flowOf(resultMovementDetailResponse))

        val result = userRepository.getMovementDetail(ANY_MOVEMENT_ID).lastOrNull()

        verify(userRemoteDataSource).getMovementDetail(ANY_MOVEMENT_ID)
        assertThatEquals(result?.getOrNull(), movementDetail)
    }

    @Test
    fun `Get GetUserDataException when getMovementDetail is called and getMovementDetail in datasource is failure`() = runTest {
        val resultGetUserDataException: Result<MovementDetailResponse> = Result.failure(UserException.GetUserDataException())

        whenever(userRemoteDataSource.getMovementDetail(ANY_MOVEMENT_ID)).thenReturn(flowOf(resultGetUserDataException))

        val result = userRepository.getMovementDetail(ANY_MOVEMENT_ID).lastOrNull()

        verify(userRemoteDataSource).getMovementDetail(ANY_MOVEMENT_ID)
        assertThatIsInstanceOf<UserException.GetUserDataException>(result?.exceptionOrNull())
    }
}