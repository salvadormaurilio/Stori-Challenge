package mx.android.storichallenge.ui.exception

import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.fakedata.ANY_INVALID_PASSWORD
import mx.android.storichallenge.fakedata.ANY_INVALID_USER_EMAIL
import mx.android.storichallenge.fakedata.ANY_PASSWORD
import mx.android.storichallenge.fakedata.ANY_USER_EMAIL
import mx.android.storichallenge.fakedata.givenUserDataSubmitUi
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithDifferentPasswords
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidConfirmPassword
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidEmail
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidFirstName
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidLastName
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidPassword
import mx.android.storichallenge.fakedata.givenUserDataSubmitUiWithInvalidPictureIdentification
import org.junit.Before
import org.junit.Test

class AuthExceptionHandlerShould {

    private lateinit var authExceptionHandler: AuthExceptionHandler

    @Before
    fun setup() {
        authExceptionHandler = AuthExceptionHandler()
    }

    @Test
    fun `return NoValidationNeededException exception when areInvalidSingUpCredentials is called and userDataSubmitUi are valid`() {
        val userDataSubmitUi = givenUserDataSubmitUi()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.second, AuthUiException.NoValidationNeededException)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return PictureIdentificationException when areInvalidSingUpCredentials is called and userDataSubmitUi has picture Identification invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidPictureIdentification()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.PictureIdentificationException)
    }

    @Test
    fun `return FirstNameException when areInvalidSingUpCredentials is called and userDataSubmitUi has first name invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidFirstName()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.FirstNameException)
    }

    @Test
    fun `return LastNameException when areInvalidSingUpCredentials is called and userDataSubmitUi has last name invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidLastName()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.LastNameException)
    }

    @Test
    fun `return EmailException when areInvalidSingUpCredentials is called and userDataSubmitUi has email invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidEmail()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.EmailException)
    }

    @Test
    fun `return PasswordException when areInvalidSingUpCredentials is called and userDataSubmitUi has password invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidPassword()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.PasswordException)
    }

    @Test
    fun `return ConfirmPasswordException when areInvalidSingUpCredentials is called and userDataSubmitUi has confirm password invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithInvalidConfirmPassword()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.ConfirmPasswordException)
    }

    @Test
    fun `return DifferentPasswordException when areInvalidSingUpCredentials is called and userDataSubmitUi has different passwords invalid`() {
        val userDataSubmitUi = givenUserDataSubmitUiWithDifferentPasswords()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.DifferentPasswordException)
    }

    @Test
    fun `return NoValidationNeededException when areInvalidSingInCredentials is called and email and password are valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.NoValidationNeededException)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return EmailException when areInvalidSingInCredentials is called and email is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.EmailException)
        assertThatEquals(result.first, true)
    }

    @Test
    fun `return PasswordException when areInvalidSingInCredentials is called and password is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_INVALID_PASSWORD)

        assertThatEquals(result.second, AuthUiException.PasswordException)
        assertThatEquals(result.first, true)
    }
}
