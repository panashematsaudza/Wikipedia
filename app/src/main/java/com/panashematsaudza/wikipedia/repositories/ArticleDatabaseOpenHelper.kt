package com.panashematsaudza.wikipedia.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import java.awt.font.TextAttribute

class ArticleDatabaseOpenHelper(context:Context) : ManagedSQLiteOpenHelper(context ,"ArticlesDatabase.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //define tables in this database
        db?.createTable("Favourites" ,true ,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT ,
            "thumnailJson" to TEXT)

        db?.createTable("History",true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT ,
            "url" to TEXT,
            "thumbnailJSON" to TEXT)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
      //use to upgrade the schema of the table if needed

    }


}