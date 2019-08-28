package com.panashematsaudza.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.panashematsaudza.wikipedia.R
import com.panashematsaudza.wikipedia.holder.CardHolder
import com.panashematsaudza.wikipedia.holder.ListItemHolder
import com.panashematsaudza.wikipedia.models.WikiPage


class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>() {

    val currentResults :ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {

        val listItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item,parent ,false)

        return  ListItemHolder(listItem)
    }

    override fun getItemCount(): Int {

        return  currentResults.size

    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val page = currentResults[position]
        holder.updateWithPage(page)

    }


}