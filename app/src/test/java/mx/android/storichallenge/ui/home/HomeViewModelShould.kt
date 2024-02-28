package mx.android.storichallenge.ui.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.TestDispatcherRule
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.domain.GetUserDataUseCase
import mx.android.storichallenge.domain.model.UserData
import mx.android.storichallenge.fakedata.ANY_MOVEMENT_ID
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

    private val getUserDataUseCase = mock<GetUserDataUseCase>()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(getUserDataUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `get UserDataUi from userDataUiState when getUserData is called and getUserDataUseCase is success`() = runTest {
        val userData = givenUserData()
        val userDataUi = givenUserDataUi()
        val resultUserData = Result.success(userData)

        whenever(getUserDataUseCase.getUserData()).thenReturn(flowOf(resultUserData))

        homeViewModel.getUserData()

        val result = homeViewModel.userDataUiState.firstOrNull()

        verify(getUserDataUseCase).getUserData()
        assertThatIsInstanceOf<UserDataUiState.Success>(result)
        assertThatEquals((result as UserDataUiState.Success).userData, userDataUi)
    }

    @Test
    fun `Get GetUserDataException from userDataUiState when getUserData is called and getUserDataUseCase is failure`() = runTest {
        val resultGetUserDataException: Result<UserData> = Result.failure(UserException.GetUserDataException())

        whenever(getUserDataUseCase.getUserData()).thenReturn(flowOf(resultGetUserDataException))

        homeViewModel.getUserData()

        val result = homeViewModel.userDataUiState.firstOrNull()

        verify(getUserDataUseCase).getUserData()
        assertThatIsInstanceOf<UserException.GetUserDataException>((result as UserDataUiState.Error).error)
    }

    @Test
    fun `navigate to movementDetail when navigateToMovementDetail is called`() = runTest {
        var result: String? = null

        homeViewModel.viewModelScope.launch {
            result = homeViewModel.navigateToMovementDetail.firstOrNull()
        }

        homeViewModel.navigateToMovementDetail(ANY_MOVEMENT_ID)

        assertThatEquals(result, ANY_MOVEMENT_ID)
    }
}