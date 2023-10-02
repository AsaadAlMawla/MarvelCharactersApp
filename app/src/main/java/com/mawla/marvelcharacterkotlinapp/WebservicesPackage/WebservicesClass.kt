package com.mawla.marvelcharacterkotlinapp.WebservicesPackage

import android.util.Log
import com.mawla.marvelcharacterkotlinapp.AppUtils.Constants
import com.mawla.marvelcharacterkotlinapp.Dtos.*
import com.mawla.marvelcharacterkotlinapp.MarvelCharacterDetailsFragment
import com.mawla.marvelcharacterkotlinapp.MarvelCharactersListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebservicesClass() {

    private var apiInterface: MarvelAPIInterface? = null

    fun initWebservicesClass(){
        apiInterface = MarvelAPIClient.getClient()?.create(MarvelAPIInterface::class.java)
    }

    fun callGetCharactersList(marvelCharactersListFragment: MarvelCharactersListFragment?) {
        val call: Call<MarvelCharacterDataWrapper?>? = apiInterface?.getCharactersList(Constants.tsLong, Constants.PUBLIC_KEY, Constants.hashedKey)
        call?.enqueue(object : Callback<MarvelCharacterDataWrapper?> {
            override fun onResponse(call: Call<MarvelCharacterDataWrapper?>, response: Response<MarvelCharacterDataWrapper?>) {
                val marvelCharacterDataWrapper: MarvelCharacterDataWrapper? = response.body()
                marvelCharactersListFragment?.onGetCharactersListSucceed(marvelCharacterDataWrapper)
            }

            override fun onFailure(call: Call<MarvelCharacterDataWrapper?>, t: Throwable) {
                marvelCharactersListFragment?.onGetCharactersListFailed()
                call.cancel()
            }
        })
    }

    fun callCharacterDetailsService(marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment, characterId: Int?) {
        val call: Call<MarvelCharacterDataWrapper?>? = apiInterface!!.getCharacterDetails(characterId, Constants.tsLong, Constants.PUBLIC_KEY, Constants.hashedKey)
        call?.enqueue(object : Callback<MarvelCharacterDataWrapper?> {
            override fun onResponse(
                call: Call<MarvelCharacterDataWrapper?>,
                response: Response<MarvelCharacterDataWrapper?>
            ) {
                val marvelCharacterDataWrapper: MarvelCharacterDataWrapper? = response.body()
                marvelCharacterDetailsFragment.onGetCharacterDataSucceed(marvelCharacterDataWrapper)
            }

            override fun onFailure(call: Call<MarvelCharacterDataWrapper?>, t: Throwable) {
                marvelCharacterDetailsFragment.onGetCharacterDataFailed()
                call.cancel()
            }
        })
    }

    fun callCharacterComicsService(marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment, characterId: Int?) {
        val call: Call<MarvelComicDataWrapper?>? = apiInterface?.getCharacterComics(characterId, Constants.tsLong, Constants.PUBLIC_KEY, Constants.hashedKey)
        call?.enqueue(object : Callback<MarvelComicDataWrapper?> {
            override fun onResponse(call: Call<MarvelComicDataWrapper?>, response: Response<MarvelComicDataWrapper?>) {
                val marvelComicDataWrapper: MarvelComicDataWrapper? = response.body()
                marvelCharacterDetailsFragment.onGetCharacterComicsSucceed(marvelComicDataWrapper)
            }

            override fun onFailure(call: Call<MarvelComicDataWrapper?>, t: Throwable) {
                marvelCharacterDetailsFragment.onGetCharacterComicsFailed()
                call.cancel()
            }
        })
    }

    fun callCharacterEventsService(marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment, characterId: Int?) {
        val call: Call<MarvelEventDataWrapper?>? = apiInterface?.getCharacterEvents(characterId, Constants.tsLong, Constants.PUBLIC_KEY, Constants.hashedKey)
        call?.enqueue(object : Callback<MarvelEventDataWrapper?> {
            override fun onResponse(call: Call<MarvelEventDataWrapper?>, response: Response<MarvelEventDataWrapper?>) {
                val marvelEventDataWrapper: MarvelEventDataWrapper? = response.body()
                marvelCharacterDetailsFragment.onGetCharacterEventsSucceed(marvelEventDataWrapper)
            }

            override fun onFailure(call: Call<MarvelEventDataWrapper?>, t: Throwable) {
                marvelCharacterDetailsFragment.onGetCharacterEventsFailed()
                call.cancel()
            }
        })
    }

    fun callCharacterStoriesService(marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment, characterId: Int?) {
        val call: Call<MarvelStoryDataWrapper?>? = apiInterface?.getCharacterStories(characterId, Constants.tsLong, Constants.PUBLIC_KEY, Constants.hashedKey)
        call?.enqueue(object : Callback<MarvelStoryDataWrapper?> {
            override fun onResponse(call: Call<MarvelStoryDataWrapper?>, response: Response<MarvelStoryDataWrapper?>) {
                val marvelStoryDataWrapper: MarvelStoryDataWrapper? = response.body()
                marvelCharacterDetailsFragment.onGetCharacterStoriesSucceed(marvelStoryDataWrapper)
            }

            override fun onFailure(call: Call<MarvelStoryDataWrapper?>, t: Throwable) {
                marvelCharacterDetailsFragment.onGetCharacterStoriesFailed()
                call.cancel()
            }
        })
    }

    fun callCharacterSeriesService(marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment, characterId: Int?) {
        val call: Call<MarvelSeriesDataWrapper?>? = apiInterface?.getCharacterSeries(characterId, Constants.tsLong, Constants.PUBLIC_KEY, Constants.hashedKey)
        call?.enqueue(object : Callback<MarvelSeriesDataWrapper?> {
            override fun onResponse(call: Call<MarvelSeriesDataWrapper?>, response: Response<MarvelSeriesDataWrapper?>) {
                val marvelSeriesDataWrapper: MarvelSeriesDataWrapper? = response.body()
                marvelCharacterDetailsFragment.onGetCharacterSeriesSucceed(marvelSeriesDataWrapper)
            }

            override fun onFailure(call: Call<MarvelSeriesDataWrapper?>, t: Throwable) {
                marvelCharacterDetailsFragment.onGetCharacterSeriesFailed()
                call.cancel()
            }
        })
    }

}