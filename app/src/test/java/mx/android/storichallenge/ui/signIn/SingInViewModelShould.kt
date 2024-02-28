package mx.android.storichallenge.ui.signIn

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.TestDispatcherRule
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.AuthException
import mx.android.storichallenge.domain.SignInUseCase
import mx.android.storichallenge.fakedata.ANY_INVALID_USER_EMAIL
import mx.android.storichallenge.fakedata.ANY_PASSWORD
import mx.android.storichallenge.fakedata.ANY_USER_EMAIL
import mx.android.storichallenge.fakedata.ANY_USER_ID
import mx.android.storichallenge.ui.exception.AuthExceptionHandler
import mx.android.storichallenge.ui.exception.AuthUiException
import mx.android.storichallenge.ui.singin.SignInUiState
import mx.android.storichallenge.ui.singin.SingInViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingInViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val authExceptionHandler = AuthExceptionHandler()
    private val signInUseCase = mock<SignInUseCase>()

    private lateinit var singInViewModel: SingInViewModel

    @Before
    fun setup() {
        singInViewModel = SingInViewModel(
            authExceptionHandler,
            signInUseCase,
            testDispatcherRule.coroutinesDispatchers
        )
    }

    @Test
    fun `get EmailException from signInUiState when signIn is called and email is invalid`() = runTest {
        singInViewModel.signIn(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiState.firstOrNull()

        verify(signInUseCase, never()).signIn(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiState.Error>(result)
        assertThatIsInstanceOf<AuthUiException.EmailException>((result as SignInUiState.Error).error)
    }

    @Test
    fun `Success from signInUiState when signIn is called and signInUseCase is success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))

        singInViewModel.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiState.firstOrNull()

        verify(signInUseCase).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiState.Success>(result)
    }

    @Test
    fun `get SignInException from signInUiState when signIn is called and signInUseCase is failure`() = runTest {
        val resultSignInException: Result<String> = Result.failure(AuthException.SignInException())

        whenever(signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        singInViewModel.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiState.firstOrNull()

        verify(signInUseCase).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignInException>((result as SignInUiState.Error).error)
    }
}