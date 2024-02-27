package mx.android.storichallenge.ui.movement

import mx.android.storichallenge.domain.model.MovementDetail

data class MovementDetailUi(
    val name: String,
    val date: String,
    val amount: String,
    val card: String,
    val reference: String,
    val category: String
)

fun MovementDetail.toUserDataUi() = MovementDetailUi(
    name = name,
    date = date,
    amount = amount,
    card = card,
    reference = reference,
    category = category
)
