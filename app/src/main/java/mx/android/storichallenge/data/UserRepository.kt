package mx.android.storichallenge.data

import kotlinx.coroutines.flow.map
import mx.android.storichallenge.data.datasource.remote.UserRemoteDataSource
import mx.android.storichallenge.domain.model.toMovementDetail
import mx.android.storichallenge.domain.model.toResultUserData
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    fun getUserData() = userRemoteDataSource.getUserData().map { it.toResultUserData() }

    fun getMovementDetail(movementId: String) = userRemoteDataSource.getMovementDetail(movementId).map { it.toMovementDetail() }
}