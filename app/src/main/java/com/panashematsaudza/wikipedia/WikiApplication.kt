package com.panashematsaudza.wikipedia

import android.app.Application
import com.panashematsaudza.wikipedia.managers.WikiManager
import com.panashematsaudza.wikipedia.providers.ArticleDataProvider
import com.panashematsaudza.wikipedia.repositories.ArticleDatabaseOpenHelper
import com.panashematsaudza.wikipedia.repositories.FavouritesRepository
import com.panashematsaudza.wikipedia.repositories.HistoryRepository

class WikiApplication :Application(){

   private var dbHelper:ArticleDatabaseOpenHelper? = null
   private var favouritesRepository:FavouritesRepository? = null
   private var historyRepository:HistoryRepository?  = null

   private var wikiProvider  :ArticleDataProvider? = null

    var wikiManager : WikiManager? = null
    private  set

    override fun onCreate() {
        super.onCreate()
        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favouritesRepository = FavouritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!,favouritesRepository!!,historyRepository!!)
    }
}