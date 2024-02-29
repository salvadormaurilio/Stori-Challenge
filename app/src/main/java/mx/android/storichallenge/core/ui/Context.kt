package mx.android.storichallenge.core.ui

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity

inline fun <reified T : ComponentActivity> Context.intentTo() = Intent(this, T::class.java)
