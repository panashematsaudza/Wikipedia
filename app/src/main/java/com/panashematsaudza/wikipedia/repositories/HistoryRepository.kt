package com.panashematsaudza.wikipedia.repositories

import android.provider.SyncStateContract.Helpers.insert
import com.google.gson.Gson
import com.panashematsaudza.wikipedia.models.WikiPage
import com.panashematsaudza.wikipedia.models.WikiThumbnail
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser

class HistoryRepository(val databasehelper:ArticleDatabaseOpenHelper) {
    private val TABLE_NAME  : String = "History"

    fun addFavourite(page: WikiPage){

        databasehelper.use {
            insert(TABLE_NAME ,
                "id" to page.pageId,
                "title"  to  page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))
        }
    }

    fun removePageById(pageId : Int){
        databasehelper.use {
            delete(TABLE_NAME ,"id ={pageId}","pageId" to pageId)
        }
    }



    fun getAllHistory():ArrayList<WikiPage>{

        var pages = ArrayList<WikiPage>()
        val articleRowarser = rowParser{id:Int ,title:String ,url:String ,thumbnailJson:String ->
            val  page = WikiPage()
            page.title =title
            page.fullurl = url
            page.pageId = id
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)
            pages.add(page)
        }
        return pages
    }
}