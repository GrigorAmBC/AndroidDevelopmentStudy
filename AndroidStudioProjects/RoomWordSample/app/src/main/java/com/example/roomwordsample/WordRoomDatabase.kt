package com.example.roomwordsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase: RoomDatabase(){

    abstract fun  wordDao(): WordDao

    companion object{
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context,
                        scope: CoroutineScope): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "Word_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.wordDao())
                }

            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()

            var word = Word("Hello!")
            wordDao.insert(word)
            word = Word("Book")
            wordDao.insert(word)
            word = Word("Journal")
            wordDao.insert(word)
            word = Word("Influence")
            wordDao.insert(word)
            word = Word("Leadership")
            wordDao.insert(word)
            word = Word("Self-dev")
            wordDao.insert(word)
        }
    }
}
