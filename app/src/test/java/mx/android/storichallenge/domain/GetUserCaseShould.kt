package mx.android.storichallenge.domain

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.UserRepository
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.domain.model.UserData
import mx.android.storichallenge.fakedata.givenUserData
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetUserCaseShould {

    private val userRepository = mock<UserRepository>()

    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setup() {
        getUserUseCase = GetUserUseCase(userRepository)
    }

    @Test
    fun `Return UserData when getUserData is called and getUserData repository are success`() = runTest {
        val userData = givenUserData()
        val resultUserData = Result.success(userData)

        whenever(userRepository.getUserData()).thenReturn(flowOf(resultUserData))

        val result = getUserUseCase.getUserData().lastOrNull()

        verify(userRepository).getUserData()
        assertThatEquals(result?.getOrNull(), userData)
    }

    @Test
    fun `Get GetUserDataException when getUserData is called and getUserData in repository is failure`() = runTest {
        val resultGetUserDataException: Result<UserData> = Result.failure(UserException.GetUserDataException())

        whenever(userRepository.getUserData()).thenReturn(flowOf(resultGetUserDataException))

        val result = getUserUseCase.getUserData().lastOrNull()

        verify(userRepository).getUserData()
        assertThatIsInstanceOf<UserException.GetUserDataException>(result?.exceptionOrNull())
    }
}