package com.example.githubrepoview.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubrepoview.data.local.dao.RepoDetailsDao
import com.example.githubrepoview.data.local.dao.RepositoriesDao
import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.utils.OwnerTypeConverter

@Database(entities =[RepositoriesDatabase::class, RepoDetailsDatabase::class], version = 1)
@TypeConverters(OwnerTypeConverter::class)
abstract class DatabaseRepositories : RoomDatabase() {
    abstract val RepositoriesDao: RepositoriesDao
    abstract val RepoDetailDao: RepoDetailsDao
}
