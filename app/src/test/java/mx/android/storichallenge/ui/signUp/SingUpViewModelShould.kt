package mx.android.storichallenge.ui.signUp

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.TestDispatcherRule
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.AuthException
import mx.android.storichallenge.domain.SignUpUseCase
import mx.android.storichallenge.fakedata.ANY_USER_ID
import mx.android.storichallenge.fakedata.givenUserDataSubmit
import mx.android.storichallenge.fakedata.givenUserDataSubmitUi
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidEmail
import mx.android.storichallenge.ui.exception.AuthExceptionHandler
import mx.android.storichallenge.ui.exception.AuthUiException
import mx.android.storichallenge.ui.singup.SignUpUiState
import mx.android.storichallenge.ui.singup.SingUpViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingUpViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val authExceptionHandler = AuthExceptionHandler()
    private val signUpUseCase = mock<SignUpUseCase>()

    private lateinit var singUpViewModel: SingUpViewModel

    @Before
    fun setup() {
        singUpViewModel = SingUpViewModel(
            authExceptionHandler,
            signUpUseCase,
            testDispatcherRule.coroutinesDispatchers
        )
    }

    @Test
    fun `Get EmailException from signUpUiState when singUp is called and email is invalid`() = runTest {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidEmail()

        singUpViewModel.singUp(userDataSubmitUi)

        val result = singUpViewModel.signUpUiState.firstOrNull()

        verify(signUpUseCase, never()).signUp(any())
        assertThatIsInstanceOf<SignUpUiState.Error>(result)
        assertThatIsInstanceOf<AuthUiException.EmailException>((result as SignUpUiState.Error).error)
    }

    @Test
    fun `Success from signUpUiState when singUp is called and signUpUseCase is success`() = runTest {
        val userDataSubmitUi = givenUserDataSubmitUi()
        val userDataSubmit = givenUserDataSubmit()
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(signUpUseCase.signUp(userDataSubmit)).thenReturn(flowOf(resultUserId))

        singUpViewModel.singUp(userDataSubmitUi)

        val result = singUpViewModel.signUpUiState.firstOrNull()

        verify(signUpUseCase).signUp(userDataSubmit)
        assertThatIsInstanceOf<SignUpUiState.Success>(result)
    }

    @Test
    fun `get SignUpException from signUpUiState when singUp is called and signUpUseCase is failure`() = runTest {
        val userDataSubmitUi = givenUserDataSubmitUi()
        val userDataSubmit = givenUserDataSubmit()
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignUpException())

        whenever(signUpUseCase.signUp(userDataSubmit)).thenReturn(flowOf(resultSignUpException))

        singUpViewModel.singUp(userDataSubmitUi)

        val result = singUpViewModel.signUpUiState.firstOrNull()

        verify(signUpUseCase).signUp(userDataSubmit)
        assertThatIsInstanceOf<SignUpUiState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignUpException>((result as SignUpUiState.Error).error)
    }
}