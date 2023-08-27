package com.example.githubrepoview.data.local.data.loacal.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.githubrepoview.data.local.dao.RepositoriesDao
import com.example.githubrepoview.data.local.database.DatabaseRepositories
import com.example.githubrepoview.data.local.entity.OwnerDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
@SmallTest
class RepositoriesDaoTest {

    private lateinit var database: DatabaseRepositories
    private lateinit var dao: RepositoriesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseRepositories::class.java
        ).allowMainThreadQueries().build()
        dao = database.RepositoriesDao
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertRepostoryItem() = runTest {
        val repoItem = RepositoriesDatabase(
            id = 1,
            owner = OwnerDatabase(),
            description = "test description",
            userName = "test namr",
            nodeId = "1323"
        )
        dao.insertAll(repoItem)
        assertThat(dao.getRepositories()).contains(repoItem)
    }

}