package mx.android.storichallenge.ui.movement

const val ANY_MOVEMENT_NAME = "Uber Eats"
const val ANY_MOVEMENT_DATE = "14 feb 2024, 15:20"
const val ANY_MOVEMENT_AMOUNT_1 = "$120.00"

const val ANY_MOVEMENT_CARD = "**** 0490"
const val ANY_MOVEMENT_REFERENCE = "2131231231232131"
const val ANY_MOVEMENT_CATEGORY = "Comida"

fun givenMovementDetailUi() = MovementDetailUi(
    name = ANY_MOVEMENT_NAME,
    date = ANY_MOVEMENT_DATE,
    amount = ANY_MOVEMENT_AMOUNT_1,
    card = ANY_MOVEMENT_CARD,
    reference = ANY_MOVEMENT_REFERENCE,
    category = ANY_MOVEMENT_CATEGORY
)
