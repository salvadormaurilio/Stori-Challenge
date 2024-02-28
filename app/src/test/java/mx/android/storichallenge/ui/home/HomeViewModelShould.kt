package mx.android.storichallenge.ui.home

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.TestDispatcherRule
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.domain.GetUserUseCase
import mx.android.storichallenge.domain.model.UserData
import mx.android.storichallenge.fakedata.givenUserData
import mx.android.storichallenge.fakedata.givenUserDataUi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getUserUseCase = mock<GetUserUseCase>()

    private lateinit var singInViewModel: HomeViewModel

    @Before
    fun setup() {
        singInViewModel = HomeViewModel(getUserUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `get UserData from userDataUiState when getUserData is called and getUserUseCase is success`() = runTest {
        val userData = givenUserData()
        val userDataUi = givenUserDataUi()
        val resultUserData = Result.success(userData)

        whenever(getUserUseCase.getUserData()).thenReturn(flowOf(resultUserData))

        singInViewModel.getUserData()

        val result = singInViewModel.userDataUiState.firstOrNull()

        verify(getUserUseCase).getUserData()
        assertThatIsInstanceOf<UserDataUiState.Success>(result)
        assertThatEquals((result as UserDataUiState.Success).userData, userDataUi)
    }

    @Test
    fun `Get GetUserDataException from userDataUiState when getUserData is called and getUserUseCase is failure`() = runTest {
        val resultGetUserDataException: Result<UserData> = Result.failure(UserException.GetUserDataException())

        whenever(getUserUseCase.getUserData()).thenReturn(flowOf(resultGetUserDataException))

        singInViewModel.getUserData()

        val result = singInViewModel.userDataUiState.firstOrNull()

        verify(getUserUseCase).getUserData()
        assertThatIsInstanceOf<UserException.GetUserDataException>((result as UserDataUiState.Error).error)
    }
}