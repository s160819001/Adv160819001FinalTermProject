package dk.ubaya.adv160819001finaltermproject.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(vararg user: User)

    @Query("SELECT * FROM user_table")
    suspend fun selectAllUser():List<User>

    @Query("SELECT * FROM user_table WHERE email=:email")
    suspend fun selectUser(email:String):User

    @Query("UPDATE user_table SET name=:name, password=:password WHERE email=:email")
    suspend fun updateUser(name:String,password:String,email:String)

    @Delete
    suspend fun deleteUser(user: User)
}