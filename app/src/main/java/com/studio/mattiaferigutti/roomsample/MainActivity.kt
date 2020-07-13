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
    private var list =  mutableListOf<Element?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uiScope.launch {
            val data = getAllData()?.toMutableList()!!
            for (previous in data)
                list.add(previous)
            adapter.notifyDataSetChanged()
        }

        adapter = Adapter(list)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        database = ElementDatabase.getInstance(this)

        addButton.setOnClickListener {
            val text = editTextTextPersonName.text.toString()
            val element = Element(elementName = text)
            uiScope.launch {
                insertValue(element)
            }
            adapter.addInList(element)
        }
    }

    private suspend fun insertValue(element: Element) {
        withContext(Dispatchers.IO) {
            database.elementDatabaseDao.insert(element)
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