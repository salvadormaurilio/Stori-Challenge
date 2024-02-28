package mx.android.storichallenge.domain

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.UserRepository
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.domain.model.MovementDetail
import mx.android.storichallenge.fakedata.ANY_MOVEMENT_ID
import mx.android.storichallenge.fakedata.givenMovementDetail
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetMovementDetailCaseShould {

    private val userRepository = mock<UserRepository>()

    private lateinit var getMovementDetailUseCase: GetMovementDetailUseCase

    @Before
    fun setup() {
        getMovementDetailUseCase = GetMovementDetailUseCase(userRepository)
    }

    @Test
    fun `Return MovementDetail when getMovementDetail is called and getMovementDetail repository are success`() = runTest {
        val movementDetail = givenMovementDetail()
        val resultMovementDetail = Result.success(movementDetail)

        whenever(userRepository.getMovementDetail(ANY_MOVEMENT_ID)).thenReturn(flowOf(resultMovementDetail))

        val result = getMovementDetailUseCase.getMovementDetail(ANY_MOVEMENT_ID).lastOrNull()

        verify(userRepository).getMovementDetail(ANY_MOVEMENT_ID)
        assertThatEquals(result?.getOrNull(), movementDetail)
    }

    @Test
    fun `Get GetUserDataException when getMovementDetail is called and getMovementDetail in repository is failure`() = runTest {
        val resultGetUserDataException: Result<MovementDetail> = Result.failure(UserException.GetUserDataException())

        whenever(userRepository.getMovementDetail(ANY_MOVEMENT_ID)).thenReturn(flowOf(resultGetUserDataException))

        val result = getMovementDetailUseCase.getMovementDetail(ANY_MOVEMENT_ID).lastOrNull()

        verify(userRepository).getMovementDetail(ANY_MOVEMENT_ID)
        assertThatIsInstanceOf<UserException.GetUserDataException>(result?.exceptionOrNull())
    }
}