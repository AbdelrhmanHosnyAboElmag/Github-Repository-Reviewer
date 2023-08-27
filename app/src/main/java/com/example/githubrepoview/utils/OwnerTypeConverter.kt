package com.example.githubrepoview.utils

import androidx.room.TypeConverter
import com.example.githubrepoview.data.local.entity.OwnerDatabase
import com.google.gson.Gson

class OwnerTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromOwner(owner: OwnerDatabase?): String? {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun toOwner(ownerJson: String?): OwnerDatabase? {
        return gson.fromJson(ownerJson, OwnerDatabase::class.java)
    }
}