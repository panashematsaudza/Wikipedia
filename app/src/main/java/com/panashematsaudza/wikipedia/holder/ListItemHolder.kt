package com.panashematsaudza.wikipedia.holder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.panashematsaudza.wikipedia.R
import com.panashematsaudza.wikipedia.activities.ArticleDetailActivity
import com.panashematsaudza.wikipedia.models.WikiPage
import com.squareup.picasso.Picasso

class ListItemHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {

    private  val articleImageView :ImageView =itemView.findViewById<ImageView>(R.id.result_icon)
    private  val titleTextView: TextView = itemView.findViewById<TextView>(R.id.result_title)
    private  var currentPage:WikiPage? =null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)

        }
    }

    fun updateWithPage(page: WikiPage){
        if (page.thumbnail != null)
            Picasso.get().load(page.thumbnail!!.source)
                .into(articleImageView)
        titleTextView.text = page.title

        currentPage = page
    }


}