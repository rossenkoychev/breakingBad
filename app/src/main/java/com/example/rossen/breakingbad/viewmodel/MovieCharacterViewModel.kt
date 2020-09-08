package com.example.rossen.breakingbad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rossen.breakingbad.dataprovider.MovieCharacterDataProvider
import com.example.rossen.breakingbad.model.MovieCharacterDataState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.sellmair.disposer.disposeBy
import io.reactivex.schedulers.Schedulers
import io.sellmair.disposer.Disposer

class MovieCharacterViewModel : ViewModel() {

    //these filters are applied during fetching the data
    val nameFilter: String = "*"
    val seasonFilter: String = "*"

    private val internalMovieCharacterDataState = MutableLiveData<MovieCharacterDataState>()
    val reposState: LiveData<MovieCharacterDataState> = internalMovieCharacterDataState

    private val disposer: Disposer = Disposer.create()

    //should be singleton and injected
    private val dataProvider: MovieCharacterDataProvider = MovieCharacterDataProvider()

    init {
        internalMovieCharacterDataState.value = MovieCharacterDataState.Loading
        fetchData()
    }

    private fun fetchData() {

        dataProvider.getMovieCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
          //  .filter  for nameFilter
        //    .filter for season
            .subscribeBy(
                onNext = { movieCharacters ->
                    internalMovieCharacterDataState.value =
                        MovieCharacterDataState.MovieCharacters(movieCharacters)
                }
                ,
                onError = { error ->
                    internalMovieCharacterDataState.value =
                        MovieCharacterDataState.DataError(error.message)

                }
            ).disposeBy(disposer)
    }

    override fun onCleared() {
        disposer.dispose()
        super.onCleared()
    }


    companion object{
        val noSeasonFilterList = listOf(1,2,3,4,5,6,7,8,9)
    }
}