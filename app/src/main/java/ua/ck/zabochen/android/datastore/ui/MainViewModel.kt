package ua.ck.zabochen.android.datastore.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.ck.zabochen.android.datastore.data.DataStoreService

class MainViewModel(context: Context) : ViewModel() {

    private val dataStoreService: DataStoreService = DataStoreService(context)

    val stringFieldFlow: LiveData<String> = dataStoreService.stringFieldFlow.asLiveData()

    fun saveText(text: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            dataStoreService.updateStringField(text)
        }
    }
}