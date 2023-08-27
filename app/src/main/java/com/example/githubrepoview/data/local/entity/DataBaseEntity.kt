package com.example.githubrepoview.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoriesDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val owner: OwnerDatabase,
    val description: String,
    val userName: String,
    val nodeId:String
)

@Entity
data class RepoDetailsDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val owner: OwnerDatabase = OwnerDatabase(),
    val description: String="",
    val userName: String="",
    val starCount:Int=0,
    val forkCount:Int=0,
    val nodeId:String=""
)

data class OwnerDatabase(
    val id: Long = 0,
    val ownerName:String="",
    val avater: String="",
)