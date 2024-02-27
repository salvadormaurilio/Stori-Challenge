package mx.android.storichallenge.data

import kotlinx.coroutines.flow.map
import mx.android.storichallenge.data.datasource.remote.UserRemoteDataSource
import mx.android.storichallenge.domain.toResultUserResponseData
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    fun getUserData() = userRemoteDataSource.getUserData().map { it.toResultUserResponseData()}
}