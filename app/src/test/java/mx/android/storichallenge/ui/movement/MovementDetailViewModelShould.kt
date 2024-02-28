package mx.android.storichallenge.ui.movement

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.storichallenge.core.TestDispatcherRule
import mx.android.storichallenge.core.assertThatEquals
import mx.android.storichallenge.core.assertThatIsInstanceOf
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.domain.GetMovementDetailUseCase
import mx.android.storichallenge.domain.model.MovementDetail
import mx.android.storichallenge.fakedata.ANY_MOVEMENT_ID
import mx.android.storichallenge.fakedata.givenMovementDetail
import mx.android.storichallenge.fakedata.givenMovementDetailUi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovementDetailViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getMovementDetailUseCase = mock<GetMovementDetailUseCase>()

    private lateinit var movementDetailViewModel: MovementDetailViewModel

    @Before
    fun setup() {
        movementDetailViewModel = MovementDetailViewModel(getMovementDetailUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `get MovementDetailUi from movementDetailUiState when getMovementDetail is called and getMovementDetail is success`() = runTest {
        val movementDetail = givenMovementDetail()
        val movementDetailUi = givenMovementDetailUi()
        val resultMovementDetail = Result.success(movementDetail)

        whenever(getMovementDetailUseCase.getMovementDetail(ANY_MOVEMENT_ID)).thenReturn(flowOf(resultMovementDetail))

        movementDetailViewModel.getMovementDetail(ANY_MOVEMENT_ID)

        val result = movementDetailViewModel.movementDetailUiState.firstOrNull()

        verify(getMovementDetailUseCase).getMovementDetail(ANY_MOVEMENT_ID)
        assertThatIsInstanceOf<MovementDetailUiState.Success>(result)
        assertThatEquals((result as MovementDetailUiState.Success).movementDetailUi, movementDetailUi)
    }

    @Test
    fun `Get GetUserDataException from MovementDetailUiState when getMovementDetail is called and getMovementDetail is failure`() = runTest {
        val resultGetUserDataException: Result<MovementDetail> = Result.failure(UserException.GetUserDataException())

        whenever(getMovementDetailUseCase.getMovementDetail(ANY_MOVEMENT_ID)).thenReturn(flowOf(resultGetUserDataException))

        movementDetailViewModel.getMovementDetail(ANY_MOVEMENT_ID)

        val result = movementDetailViewModel.movementDetailUiState.firstOrNull()

        verify(getMovementDetailUseCase).getMovementDetail(ANY_MOVEMENT_ID)
        assertThatIsInstanceOf<UserException.GetUserDataException>((result as MovementDetailUiState.Error).error)
    }
}