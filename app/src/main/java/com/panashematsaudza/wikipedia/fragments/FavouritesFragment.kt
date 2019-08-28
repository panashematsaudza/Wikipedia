package com.panashematsaudza.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.panashematsaudza.wikipedia.R
import com.panashematsaudza.wikipedia.WikiApplication
import com.panashematsaudza.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.panashematsaudza.wikipedia.adapters.ArticleListItemRecyclerAdapter
import com.panashematsaudza.wikipedia.managers.WikiManager
import com.panashematsaudza.wikipedia.models.WikiPage
import org.jetbrains.anko.doAsync


class FavouritesFragment : Fragment() {

    private var wikiManager : WikiManager? = null
    var favouritesRecycler : RecyclerView? = null
    private val adapter = ArticleListItemRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        favouritesRecycler = view.findViewById<RecyclerView>(R.id.favourites_article_recycler)

        favouritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favouritesRecycler!!.adapter = adapter


        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onResume() {
        super.onResume()
        doAsync {
            val favoriteArticles  = wikiManager!!.getFavourites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favoriteArticles as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }
}
