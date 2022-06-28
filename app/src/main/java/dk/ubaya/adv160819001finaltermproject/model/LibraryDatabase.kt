package dk.ubaya.adv160819001finaltermproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dk.ubaya.adv160819001finaltermproject.util.MIGRATION_1_2

@Database(entities = arrayOf(Book::class,Thesis::class, User::class, UserLogin::class), version = 2)
abstract class LibraryDatabase: RoomDatabase() {
    abstract fun bookDao():BookDao
    abstract fun thesisDao():ThesisDao
    abstract fun userDao():UserDao
    abstract fun userLogDao():UserLogDao

    companion object{
        @Volatile private var instance:LibraryDatabase ?=null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LibraryDatabase::class.java,
            "librarydb"
        )   .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context)=instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }
    }
}