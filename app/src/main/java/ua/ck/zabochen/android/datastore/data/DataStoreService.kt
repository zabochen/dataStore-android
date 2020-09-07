package ua.ck.zabochen.android.datastore.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreService(context: Context) {

    companion object {
        val BOOLEAN_FIELD_KEY = preferencesKey<Boolean>("boolean_field_key")
        val INT_FIELD_KEY = preferencesKey<Int>("int_field_key")
        val FLOAT_FIELD_KEY = preferencesKey<Float>("float_field_key")
        val STRING_FIELD_KEY = preferencesKey<String>("string_field_key")
    }

    private val dataStoreInstance: DataStore<Preferences> =
        context.createDataStore(name = "appDataStore.ds")

    val stringFieldFlow: Flow<String> = dataStoreInstance.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[STRING_FIELD_KEY] ?: "stringFieldFlow is Empty"
        }

    suspend fun updateStringField(stringValue: String) {
        dataStoreInstance.edit { mutablePreferences ->
            mutablePreferences[STRING_FIELD_KEY] = stringValue
        }
    }
}