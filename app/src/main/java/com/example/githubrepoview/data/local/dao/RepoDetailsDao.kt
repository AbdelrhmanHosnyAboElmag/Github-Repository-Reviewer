package com.example.githubrepoview.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase

@Dao
interface RepoDetailsDao {
    @Query("SELECT * FROM RepoDetailsDatabase WHERE nodeId = :nodeId")
    fun searchRepositoryByNodeId(nodeId: String): RepoDetailsDatabase


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repoDetails: RepoDetailsDatabase)
}