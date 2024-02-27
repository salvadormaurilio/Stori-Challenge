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

fun Map<String, String>.toMovement() = Movement(
    id = this[ID].orEmpty(),
    name = this[NAME].orEmpty(),
    date = this[DATE].orEmpty(),
    amount = this[AMOUNT].orEmpty()
)

fun Result<MovementDetailResponse?>.toMovementDetail() = map { it.toMovementDetail() }

fun MovementDetailResponse?.toMovementDetail() = MovementDetail(
    name = this?.name.orEmpty(),
    date = this?.date.orEmpty(),
    amount = this?.amount.orEmpty(),
    card = this?.card.orEmpty(),
    reference = this?.reference.orEmpty(),
    category = this?.category.orEmpty()
)

private const val ID = "id"
private const val NAME = "name"
private const val DATE = "date"
private const val AMOUNT = "amount"
