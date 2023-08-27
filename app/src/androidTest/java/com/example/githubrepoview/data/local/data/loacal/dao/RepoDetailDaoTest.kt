package com.example.githubrepoview.data.local.data.loacal.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.githubrepoview.data.local.dao.RepoDetailsDao
import com.example.githubrepoview.data.local.database.DatabaseRepositories
import com.example.githubrepoview.data.local.entity.OwnerDatabase
import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDetailDaoTest {
    private lateinit var database: DatabaseRepositories
    private lateinit var dao: RepoDetailsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseRepositories::class.java
        ).allowMainThreadQueries().build()
        dao = database.RepoDetailDao
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertRepostoryDetailItem() = runTest {
        val repoDetailItem = RepoDetailsDatabase(
            id = 1,
            owner = OwnerDatabase(),
            description = "test description",
            userName = "test namr",
            nodeId = "1323",
            starCount = 10,
            forkCount = 3
        )
        dao.insert(repoDetailItem)
        Truth.assertThat(dao.searchRepositoryByNodeId("1323")).isEqualTo(repoDetailItem)
    }

}