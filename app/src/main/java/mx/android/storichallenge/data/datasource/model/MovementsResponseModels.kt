package mx.android.storichallenge.data.datasource.model

data class MovementDetailResponse(
    val name: String,
    val date: String,
    val amount: String,
    val card: String,
    val reference: String,
    val category: String
)