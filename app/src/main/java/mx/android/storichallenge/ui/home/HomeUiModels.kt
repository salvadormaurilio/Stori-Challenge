package mx.android.storichallenge.ui.home

import mx.android.storichallenge.domain.model.Movement
import mx.android.storichallenge.domain.model.UserData

data class UserDataUi(
    val firstName: String,
    val lastName: String,
    val email: String,
    var pictureIdentification: String,
    val movements: List<MovementUi>
)

data class MovementUi(
    val id: String,
    val name: String,
    val date: String,
    val amount: String
)

fun UserData.toUserDataUi() = UserDataUi(
    firstName = firstName,
    lastName = lastName,
    email = email,
    pictureIdentification = pictureIdentification,
    movements = movements.toMovementUiList()
)

private fun List<Movement>.toMovementUiList() = map { it.toMovementUi() }

private fun Movement.toMovementUi() = MovementUi(
    id = id,
    name = name,
    date = date,
    amount = amount
)
