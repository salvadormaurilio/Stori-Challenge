package mx.android.storichallenge.domain

import mx.android.storichallenge.data.UserRepository
import javax.inject.Inject

class GetMovementUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getUserData(movementId: String) = userRepository.getMovementDetail(movementId)
}
