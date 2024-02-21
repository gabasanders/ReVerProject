package com.example.reverproject.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LocalCard::class], version = 1)
//Abstract class defines (abstract) functions of a class without implementing them
//CardDatabase
abstract class CardDatabase : RoomDatabase() {

    //The abstract function CardDao() will return a CardDao
    abstract fun cardDao() : CardDao

    companion object{
        //Singleton
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase {
            //if the INSTANCE is not null, then return it,
            //if it is, then create the databse
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java, "cards-database"
                ).build()
                INSTANCE = instance
                instance
                }
            }
        }
    }

