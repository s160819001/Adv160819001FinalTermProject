package dk.ubaya.adv160819001finaltermproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Book::class,Thesis::class, User::class), version = 1)
abstract class LibraryDatabase: RoomDatabase() {
    abstract fun bookDao():BookDao
    abstract fun thesisDao():ThesisDao
    abstract fun userDao():UserDao

    companion object{
        @Volatile private var instance:LibraryDatabase ?=null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LibraryDatabase::class.java,
            "librarydb"
        ).build()

        operator fun invoke(context: Context)=instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }
    }
}