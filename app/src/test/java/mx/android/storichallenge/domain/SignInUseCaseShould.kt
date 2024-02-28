package mx.android.storichallenge.domain

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.AuthRepository
import mx.android.storichallenge.data.datasource.exception.AuthException
import mx.android.storichallenge.fakedata.ANY_PASSWORD
import mx.android.storichallenge.fakedata.ANY_USER_EMAIL
import mx.android.storichallenge.fakedata.ANY_USER_ID
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SignInUseCaseShould {

    private val authRepository = mock<AuthRepository>()

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setup() {
        signInUseCase = SignInUseCase(authRepository)
    }

    @Test
    fun `Return UserId when signIn is called and signIn repository are success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn in repository is failure`() = runTest {
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignInException())

        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignUpException))

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}