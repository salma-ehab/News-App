package com.example.newsappinkotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappinkotlin.model.NewsModel


@Dao
interface NewsDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insertNews(news:NewsModel)

    @Query("SELECT * FROM news_table")
    fun getAllSavedNews():List<NewsModel>
}

