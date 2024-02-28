package mx.android.storichallenge.domain

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.AuthRepository
import mx.android.storichallenge.data.datasource.exception.AuthException
import mx.android.storichallenge.fakedata.ANY_USER_ID
import mx.android.storichallenge.fakedata.givenUserDataSubmit
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SignUpUseCaseShould {

    private val authRepository = mock<AuthRepository>()

    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase(authRepository)
    }

    @Test
    fun `Return UserId when signUp is called and signUp repository are success`() = runTest {
        val userDataSubmit = givenUserDataSubmit()
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(authRepository.signUp(userDataSubmit)).thenReturn(flowOf(resultUserId))

        val result = signUpUseCase.signUp(userDataSubmit).lastOrNull()

        verify(authRepository).signUp(userDataSubmit)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignUpException when signUp is called and signUp in repository is failure`() = runTest {
        val userDataSubmit = givenUserDataSubmit()
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignUpException())

        whenever(authRepository.signUp(userDataSubmit)).thenReturn(flowOf(resultSignUpException))

        val result = signUpUseCase.signUp(userDataSubmit).lastOrNull()

        verify(authRepository).signUp(userDataSubmit)
        assertThatIsInstanceOf<AuthException.SignUpException>(result?.exceptionOrNull())
    }
}