package ua.ck.zabochen.android.datastore.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import ua.ck.zabochen.android.datastore.R

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observeDataStore()
        initUi()
        initActions()
    }

    private fun initViewModel() {
        val mainViewModelFactory = MainViewModelFactory(applicationContext)
        this.mainViewModel = ViewModelProvider(this, mainViewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun observeDataStore() {
        mainViewModel.stringFieldFlow.observe(this) {
            Log.i("MainActivity", "initDataStore: $it")
        }
    }

    private fun initUi() {
        setContentView(R.layout.activity_main)
    }

    private fun initActions() {
        btnSave.setOnClickListener {
            mainViewModel.saveText(etData.text.toString())
        }
    }
}