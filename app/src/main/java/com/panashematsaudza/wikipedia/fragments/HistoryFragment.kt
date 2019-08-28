package com.panashematsaudza.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.panashematsaudza.wikipedia.R
import com.panashematsaudza.wikipedia.WikiApplication
import com.panashematsaudza.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.panashematsaudza.wikipedia.adapters.ArticleListItemRecyclerAdapter
import com.panashematsaudza.wikipedia.managers.WikiManager
import com.panashematsaudza.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class HistoryFragment : Fragment() {
    private var wikiManager : WikiManager? = null
    var historyRecycler : RecyclerView ? = null
    private val adapter = ArticleListItemRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById<RecyclerView>(R.id.history_article_recycler)

        historyRecycler!!.layoutManager = LinearLayoutManager(context)

        historyRecycler!!.adapter = adapter




        return  view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onResume() {
        super.onResume()
        doAsync {
            val historyArticles  = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(historyArticles as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.history_menu,menu)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_clear_history){

            activity?.alert("Are you sure you want to clear your history","Confim" ){
                    yesButton {
                        adapter.currentResults.clear()
                        doAsync {

                            wikiManager?.clearHistory()
                        }
                        activity?.runOnUiThread { adapter.notifyDataSetChanged() }
                    }
                noButton {


                }
            }?.show()
        }
        return  true
    }
}
