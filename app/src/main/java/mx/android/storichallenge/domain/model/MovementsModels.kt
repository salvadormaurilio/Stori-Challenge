package mx.android.storichallenge.domain.model

import mx.android.storichallenge.data.datasource.model.MovementDetailResponse

data class Movement(
    val id: String,
    val name: String,
    val date: String,
    val amount: String
)

data class MovementDetail(
    val name: String,
    val date: String,
    val amount: String,
    val card: String,
    val reference: String,
    val category: String
)

fun ArrayList<Map<String, String>>?.toMovementList() = this?.map { it.toMovement() }.orEmpty()

private fun Map<String, String>.toMovement() = Movement(
    id = this[MOVEMENT_ID].orEmpty(),
    name = this[MOVEMENT_NAME].orEmpty(),
    date = this[MOVEMENT_DATE].orEmpty(),
    amount = this[MOVEMENT_AMOUNT].orEmpty()
)

fun Result<MovementDetailResponse?>.toMovementDetail() = map { it.toMovementDetail() }

private fun MovementDetailResponse?.toMovementDetail() = MovementDetail(
    name = this?.name.orEmpty(),
    date = this?.date.orEmpty(),
    amount = this?.amount.orEmpty(),
    card = this?.card.orEmpty(),
    reference = this?.reference.orEmpty(),
    category = this?.category.orEmpty()
)

const val MOVEMENT_ID = "id"
const val MOVEMENT_NAME = "name"
const val MOVEMENT_DATE = "date"
const val MOVEMENT_AMOUNT = "amount"
