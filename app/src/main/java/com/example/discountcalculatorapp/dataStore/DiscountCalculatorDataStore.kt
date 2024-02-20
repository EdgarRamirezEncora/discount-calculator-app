package com.example.discountcalculatorapp.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscountCalculatorDataStore(private val context: Context) {

    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("DiscountCalculator")
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    val getDarkMode: Flow<Boolean> = context.datastore.data
        .map {
            it[DARK_MODE]?: false
        }

    suspend fun saveDarkMode(darkModeStatus: Boolean) {
        context.datastore.edit {
            it[DARK_MODE] = darkModeStatus
        }
    }
}