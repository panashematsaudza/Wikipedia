package com.panashematsaudza.wikipedia.fragments


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.panashematsaudza.wikipedia.R
import com.panashematsaudza.wikipedia.WikiApplication
import com.panashematsaudza.wikipedia.activities.SearchActivity
import com.panashematsaudza.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.panashematsaudza.wikipedia.managers.WikiManager
import com.panashematsaudza.wikipedia.models.WikiResult
import com.panashematsaudza.wikipedia.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.fragment_explore.*
import java.lang.Exception


class ExploreFragment : Fragment() {
 private var wikiManager :WikiManager? = null
    var searchCardView : CardView? = null
    var exploreRecycler : RecyclerView? = null
    var refresher : SwipeRefreshLayout? = null
    var adapter :ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          val view = inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById(R.id.refresher)
        searchCardView!!.setOnClickListener{

            val searchIntent = Intent (context ,SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {

            getRandomArticle()
        }
        getRandomArticle()
        return  view
    }

private fun getRandomArticle(){
    refresher?.isRefreshing = true

    try {

        wikiManager?.getRandom(15) { wikiResult ->
            adapter.currentResults.clear()
            adapter.currentResults.addAll(wikiResult.query!!.pages)
            activity?.runOnUiThread {
                adapter.notifyDataSetChanged()
                refresher?.isRefreshing =false
            }

        }
    }catch (ex:Exception){

        val builder = AlertDialog.Builder(activity)
        builder.setMessage(ex.message).setTitle("eish !!!")
        val dialog = builder.create()
        dialog.show()
    }
}
}
