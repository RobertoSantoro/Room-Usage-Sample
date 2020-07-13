package com.studio.mattiaferigutti.roomsample

import androidx.room.*

@Dao
interface ElementDao {

    @Insert
    fun insert(element: Element)

    @Update
    fun update(element: Element)

    @Delete
    fun delete(element: Element)

    @Query("SELECT * from table_elements WHERE elementId = :key")
    fun get(key: Long) : Element?

    @Query("SELECT * from table_elements")
    fun getAllUsers(): List<Element?>?

}