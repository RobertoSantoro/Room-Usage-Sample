package com.studio.mattiaferigutti.roomsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var myJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + myJob)
    private lateinit var database: ElementDatabase
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<String>("Marco", "Arianna", "Gino")

        adapter = Adapter(list)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        database = ElementDatabase.getInstance(this)

        uiScope.launch {
            getAllData()
        }

        addButton.setOnClickListener {
            val text = editTextTextPersonName.text.toString()
            uiScope.launch {
                insertValue(Element(elementName = text))
            }
        }
    }

    private suspend fun insertValue(element: Element) {
        withContext(Dispatchers.IO) {
            database.elementDatabaseDao.insert(element)
            adapter.addInList(element.elementName)
        }
    }

    private suspend fun getAllData() : List<Element?>? {
        return withContext(Dispatchers.IO) {
            database.elementDatabaseDao.getAllUsers()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myJob.cancel()
    }
}