package com.example.githubrepoview.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase

@Dao
interface RepositoriesDao {
    @Query("select * from repositoriesdatabase")
    fun getRepositories(): MutableList<RepositoriesDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg product: RepositoriesDatabase)
}