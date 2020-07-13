package com.studio.mattiaferigutti.roomsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_elements")
data class Element (

    @PrimaryKey(autoGenerate = true)
    var elementId: Long = 0L,

    @ColumnInfo(name = "element_name")
    var elementName: String = "Nothing"
)