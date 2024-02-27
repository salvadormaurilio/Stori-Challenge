package mx.android.storichallenge.domain.model

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

private const val ID = "id"
private const val NAME = "name"
private const val DATE = "date"
private const val AMOUNT = "amount"
