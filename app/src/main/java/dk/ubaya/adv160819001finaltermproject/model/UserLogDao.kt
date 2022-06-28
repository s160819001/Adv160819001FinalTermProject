package dk.ubaya.adv160819001finaltermproject.model

import androidx.room.*

@Dao
interface UserLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLog(vararg userLogin: UserLogin)

    @Query("SELECT * FROM user_log_table ORDER BY id DESC LIMIT 1")
    suspend fun selectUserLog():UserLogin

    @Query("UPDATE user_log_table SET email=:email WHERE id=:id")
    suspend fun updateUser(email: String,id:Int)

    @Delete
    suspend fun deleteUser(user: User)
}