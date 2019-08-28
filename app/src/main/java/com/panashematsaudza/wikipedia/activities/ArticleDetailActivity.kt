package com.panashematsaudza.wikipedia.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.panashematsaudza.wikipedia.R

import com.panashematsaudza.wikipedia.managers.WikiManager
import com.panashematsaudza.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast
import java.lang.Exception

class ArticleDetailActivity : AppCompatActivity() {
    private  var wikiManager :WikiManager ? = null

private  var  currentPage :WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val wikiPageJson = intent.getStringExtra("page")

        currentPage = Gson().fromJson<WikiPage>(wikiPageJson,WikiPage::class.java)

        supportActionBar?.title = currentPage?.title

        article_detail_webview?.webViewClient = object :WebViewClient(){

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return true
            }
        }
        this.article_detail_webview.loadUrl(currentPage!!.fullurl)
        wikiManager?.addHistory(currentPage!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.action_favourite) {
        }
        try {
            if (wikiManager!!.getIsFavourite(currentPage!!.pageId!!)) {
                wikiManager!!.removefavourite(currentPage!!.pageId!!)
                toast("Article removed from favourites")
            } else {

                wikiManager!!.addFavourite(currentPage!!)
                toast("Articles added to favourites")
            }
        } catch (ex: Exception) {
            toast("unable to update this article")
        }

        return true

    }
}