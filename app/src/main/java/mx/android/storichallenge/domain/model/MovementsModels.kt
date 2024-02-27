package mx.android.storichallenge.domain.model

data class Movement(
    private val id: String,
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