package mx.android.storichallenge.domain

import mx.android.storichallenge.data.AuthRepository
import mx.android.storichallenge.domain.model.UserDataSubmit
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun signUp(userDataSubmit: UserDataSubmit) = authRepository.signUp(userDataSubmit)
}
