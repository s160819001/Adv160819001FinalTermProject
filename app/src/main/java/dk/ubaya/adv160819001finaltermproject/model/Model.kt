package dk.ubaya.adv160819001finaltermproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey val isbn:String,
    val title:String?,
    val author:String?,
    val publisher:String?,
    val year:String?,
    val synopsis:String?,
    val location:String?="Online",
    val image:String?="https://freesvg.org/img/matt-icons_file-x-unknown.png"
)

@Entity(tableName = "thesis_table")
data class Thesis(
    @PrimaryKey val id:String,
    val title:String?,
    val author: String?,
    val year: String?
)

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val email:String,
    val name:String,
    val password:String
)