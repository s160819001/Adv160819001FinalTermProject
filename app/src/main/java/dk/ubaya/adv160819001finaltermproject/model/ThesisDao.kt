package dk.ubaya.adv160819001finaltermproject.model

import androidx.room.*

@Dao
interface ThesisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllThesis(vararg thesis: Thesis)

    @Query("SELECT * FROM thesis_table")
    suspend fun selectAllThesis():List<Thesis>

    @Query("SELECT * FROM thesis_table WHERE id=:id")
    suspend fun selectThesis(id:String):Thesis

    @Delete
    suspend fun deleteThesis(thesis: Thesis)
}