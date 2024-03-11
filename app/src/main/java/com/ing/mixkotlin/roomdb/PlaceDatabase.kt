package com.ing.mixkotlin.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ing.mixkotlin.modal.Place

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}