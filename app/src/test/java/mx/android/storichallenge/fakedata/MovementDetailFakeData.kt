package mx.android.storichallenge.fakedata

import mx.android.storichallenge.data.datasource.model.MovementDetailResponse
import mx.android.storichallenge.domain.model.MOVEMENT_AMOUNT
import mx.android.storichallenge.domain.model.MOVEMENT_DATE
import mx.android.storichallenge.domain.model.MOVEMENT_ID
import mx.android.storichallenge.domain.model.MOVEMENT_NAME
import mx.android.storichallenge.domain.model.Movement
import mx.android.storichallenge.domain.model.MovementDetail

const val ANY_MOVEMENT_ID = "MaKNASzR0ySOFA99PS4IFASW5rpX2"

const val ANY_MOVEMENT_ID_1 = "MaKNASzR0ySOFA99PS4IFASW5rpX2"
const val ANY_MOVEMENT_NAME_1 = "Uber Eats"
const val ANY_MOVEMENT_DATE_1 = "14 feb 2024, 15:20"
const val ANY_MOVEMENT_AMOUNT_1 = "$120.00"

const val ANY_MOVEMENT_ID_2 = "MaKNASzR0ySOFA99PS4IFASW5rpX3"
const val ANY_MOVEMENT_NAME_2 = "Amazon Mx"
const val ANY_MOVEMENT_DATE_2 = "15 feb 2024, 16:25"
const val ANY_MOVEMENT_AMOUNT_2 = "$560.00"

const val ANY_MOVEMENT_CARD = "**** 0490"
const val ANY_MOVEMENT_REFERENCE = "2131231231232131"
const val ANY_MOVEMENT_CATEGORY = "Comida"

fun givenMovementsResponseArray() = arrayListOf(givenMovementMap1(), givenMovementMap2())

private fun givenMovementMap1() = mapOf(
    MOVEMENT_ID to ANY_MOVEMENT_ID_1,
    MOVEMENT_NAME to ANY_MOVEMENT_NAME_1,
    MOVEMENT_DATE to ANY_MOVEMENT_DATE_1,
    MOVEMENT_AMOUNT to ANY_MOVEMENT_AMOUNT_1
)

private fun givenMovementMap2() = mapOf(
    MOVEMENT_ID to ANY_MOVEMENT_ID_2,
    MOVEMENT_NAME to ANY_MOVEMENT_NAME_2,
    MOVEMENT_DATE to ANY_MOVEMENT_DATE_2,
    MOVEMENT_AMOUNT to ANY_MOVEMENT_AMOUNT_2
)

fun givenMovementList() = listOf(givenMovementDetail1(), givenMovementDetail2())

private fun givenMovementDetail1() = Movement(
    id = ANY_MOVEMENT_ID_1,
    name = ANY_MOVEMENT_NAME_1,
    date = ANY_MOVEMENT_DATE_1,
    amount = ANY_MOVEMENT_AMOUNT_1
)

private fun givenMovementDetail2() = Movement(
    id = ANY_MOVEMENT_ID_2,
    name = ANY_MOVEMENT_NAME_2,
    date = ANY_MOVEMENT_DATE_2,
    amount = ANY_MOVEMENT_AMOUNT_2
)

fun givenMovementDetailResponse() = MovementDetailResponse(
    name = ANY_MOVEMENT_NAME_1,
    date = ANY_MOVEMENT_DATE_1,
    amount = ANY_MOVEMENT_AMOUNT_1,
    card = ANY_MOVEMENT_CARD,
    reference = ANY_MOVEMENT_REFERENCE,
    category = ANY_MOVEMENT_CATEGORY
)

fun givenMovementDetail() = MovementDetail(
    name = ANY_MOVEMENT_NAME_1,
    date = ANY_MOVEMENT_DATE_1,
    amount = ANY_MOVEMENT_AMOUNT_1,
    card = ANY_MOVEMENT_CARD,
    reference = ANY_MOVEMENT_REFERENCE,
    category = ANY_MOVEMENT_CATEGORY
)

