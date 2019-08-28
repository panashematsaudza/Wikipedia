package com.panashematsaudza.wikipedia.managers

import com.panashematsaudza.wikipedia.models.WikiPage
import com.panashematsaudza.wikipedia.models.WikiResult
import com.panashematsaudza.wikipedia.providers.ArticleDataProvider
import com.panashematsaudza.wikipedia.repositories.FavouritesRepository
import com.panashematsaudza.wikipedia.repositories.HistoryRepository

class WikiManager(
    private val provider: ArticleDataProvider,
    private val favouritesRepository: FavouritesRepository,
    private val historyRepository: HistoryRepository
) {
    private  var favouritesCache :ArrayList<WikiPage>? = null
    private  var historyCache :ArrayList<WikiPage> ? = null

    fun search(term:String ,skip:Int,take:Int ,handler:(result:WikiResult) ->Unit?){
        return provider.search(term , skip ,take,handler)

    }

    fun getRandom(take: Int,handler: (result: WikiResult) -> Unit?){
        return provider.getRandom(take,handler)
    }

    fun getHistory():ArrayList<WikiPage>?{
        if (historyCache == null)
            historyCache=historyRepository.getAllHistory()
        return historyCache
    }


    fun getFavourites():ArrayList<WikiPage>?{
        if (favouritesCache == null)
            favouritesCache = favouritesRepository.getAllFavourites()
        return favouritesCache
    }

    fun addFavourite(page: WikiPage){
        favouritesCache?.add(page)
        favouritesRepository.addFavourite(page)

    }

    fun removefavourite(pageId:Int){
        favouritesRepository.removeFaouriteById(pageId)
        favouritesCache = favouritesCache!!.filter {
            it.pageId != pageId
        } as ArrayList<WikiPage>
    }

    fun getIsFavourite(pageId: Int) :Boolean{
        return  favouritesRepository.isArticleFavourite(pageId)
    }


    fun addHistory(page:WikiPage){
        historyCache?.add(page)
        historyRepository.addFavourite(page)
    }


    fun clearHistory(){
        historyCache?.clear()
        val allHistory = historyRepository.getAllHistory()
        allHistory?.forEach {
            historyRepository.removePageById(it.pageId!!)
        }
    }

}