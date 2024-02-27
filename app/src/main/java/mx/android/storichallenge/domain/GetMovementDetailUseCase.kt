package mx.android.storichallenge.domain

import mx.android.storichallenge.data.UserRepository
import javax.inject.Inject

class GetMovementDetailUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getMovementDetail(movementId: String) = userRepository.getMovementDetail(movementId)
}
