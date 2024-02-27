package mx.android.storichallenge.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import mx.android.storichallenge.data.datasource.remote.AuthRemoteDataSource
import mx.android.storichallenge.data.datasource.remote.UserRemoteDataSource
import mx.android.storichallenge.domain.model.UserDataSubmit
import mx.android.storichallenge.domain.model.toUserDataMap
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepository @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource,
                                         private val userRemoteDataSource: UserRemoteDataSource) {

    fun signIn(email: String, password: String): Flow<Result<String>> = authRemoteDataSource.signIn(email, password)

    fun signUp(userDataSubmit: UserDataSubmit): Flow<Result<String>> =
            authRemoteDataSource.signUp(userDataSubmit.email, userDataSubmit.password)
                    .flatMapConcat { if (it.isSuccess) userRemoteDataSource.storeUserData(userDataSubmit.toUserDataMap()) else flowOf(it) }
}
