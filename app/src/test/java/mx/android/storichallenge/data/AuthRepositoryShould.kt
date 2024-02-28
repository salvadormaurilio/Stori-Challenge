package mx.android.storichallenge.data

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.AuthException
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.data.datasource.remote.AuthRemoteDataSource
import mx.android.storichallenge.data.datasource.remote.UserRemoteDataSource
import mx.android.storichallenge.fakedata.ANY_PASSWORD
import mx.android.storichallenge.fakedata.ANY_USER_EMAIL
import mx.android.storichallenge.fakedata.ANY_USER_ID
import mx.android.storichallenge.fakedata.givenUserDataMap
import mx.android.storichallenge.fakedata.givenUserDataSubmit
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryShould {

    private val authRemoteDataSource = mock<AuthRemoteDataSource>()
    private val userRemoteDataSource = mock<UserRemoteDataSource>()

    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        authRepository = AuthRepository(authRemoteDataSource, userRemoteDataSource)
    }

    @Test
    fun `Return UserId when signUp is called and signUp and storeUserData in datasource are success`() = runTest {
        val userDataSubmit = givenUserDataSubmit()
        val userDataMap = givenUserDataMap()
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))
        whenever(userRemoteDataSource.storeUserData(userDataMap)).thenReturn(flowOf(resultUserId))

        val result = authRepository.signUp(userDataSubmit).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource).storeUserData(userDataMap)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignUpException when signUp is called and signUp in datasource is failure`() = runTest {
        val userDataSubmit = givenUserDataSubmit()
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignUpException())

        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignUpException))

        val result = authRepository.signUp(userDataSubmit).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource, never()).storeUserData(any())
        assertThatIsInstanceOf<AuthException.SignUpException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get StoreUserDataException when signUp is called and storeUserData in datasource is failure`() = runTest {
        val userDataSubmit = givenUserDataSubmit()
        val userDataMap = givenUserDataMap()
        val resultUserId = Result.success(ANY_USER_ID)
        val resultStoreUserDataException: Result<String> = Result.failure(UserException.StoreUserDataException())

        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))
        whenever(userRemoteDataSource.storeUserData(userDataMap)).thenReturn(flowOf(resultStoreUserDataException))

        val result = authRepository.signUp(userDataSubmit).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource).storeUserData(userDataMap)
        assertThatIsInstanceOf<UserException.StoreUserDataException>(result?.exceptionOrNull())
    }

    @Test
    fun `Return UserId when signIn is called and signIn in datasource is success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRemoteDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn in datasource is failure`() = runTest {
        val resultSignInException: Result<String> = Result.failure(AuthException.SignInException())

        whenever(authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRemoteDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}