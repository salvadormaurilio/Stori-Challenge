package mx.android.storichallenge.ui.home

private const val ANY_FIRST_NAME = "Salvador"
private const val ANY_LAST_NAME = "Maurilio"
const val ANY_USER_EMAIL = "salvador@google.mx"

const val ANY_PICTURE_IDENTIFICATION = "https://previews.123rf.com/images/alexutemov/alexutemov1605/alexutemov160500480/56958565-id-identidad-corporativa-medicina-identificador-identificaci%C3%B3n-del-m%C3%A9dico-y-la-tarjeta-de.jpg"

const val ANY_MOVEMENT_ID_1 = "MaKNASzR0ySOFA99PS4IFASW5rpX2"
const val ANY_MOVEMENT_NAME_1 = "Uber Eats"
const val ANY_MOVEMENT_DATE_1 = "14 feb 2024, 15:20"
const val ANY_MOVEMENT_AMOUNT_1 = "$120.00"

const val ANY_MOVEMENT_ID_2 = "MaKNASzR0ySOFA99PS4IFASW5rpX3"
const val ANY_MOVEMENT_NAME_2 = "Amazon Mx"
const val ANY_MOVEMENT_DATE_2 = "15 feb 2024, 16:25"
const val ANY_MOVEMENT_AMOUNT_2 = "$560.00"

fun givenUserDataUi() = UserDataUi(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
    movements = givenMovementUiList()
)

fun givenMovementUiList() = listOf(givenMovementUi1(), givenMovementUi2())

fun givenMovementUi1() = MovementUi(
    id = ANY_MOVEMENT_ID_1,
    name = ANY_MOVEMENT_NAME_1,
    date = ANY_MOVEMENT_DATE_1,
    amount = ANY_MOVEMENT_AMOUNT_1
)

fun givenMovementUi2() = MovementUi(
    id = ANY_MOVEMENT_ID_2,
    name = ANY_MOVEMENT_NAME_2,
    date = ANY_MOVEMENT_DATE_2,
    amount = ANY_MOVEMENT_AMOUNT_2
)
