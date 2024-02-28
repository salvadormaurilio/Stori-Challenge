package mx.android.storichallenge.domain

import mx.android.storichallenge.data.UserRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getUserData() = userRepository.getUserData()
}
