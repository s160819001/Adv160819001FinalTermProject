package dk.ubaya.adv160819001finaltermproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey var isbn:String,
    var title:String,
    var author:String,
    var publisher:String,
    var year:String,
    var synopsis:String,
    var location:String?="Online",
    var image:String?="https://freesvg.org/img/matt-icons_file-x-unknown.png"
)

@Entity(tableName = "thesis_table")
data class Thesis(
    @PrimaryKey var id:String,
    var title:String,
    var author: String,
    var year: String
)

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey var email:String,
    var name:String,
    var password:String
)