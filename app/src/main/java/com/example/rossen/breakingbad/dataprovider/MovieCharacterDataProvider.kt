package com.example.rossen.breakingbad.dataprovider

import com.example.rossen.breakingbad.model.MovieCharacter
import com.example.rossen.breakingbad.web.ReposClient
import io.reactivex.Observable

/**
 * This class handles fetching the correct data.
 * Current implementation queries the server only.
 */
//TODO add to Dagger as injectable
class MovieCharacterDataProvider {

    //should be singleton and injected
    private val reposClient: ReposClient = ReposClient()

    /**
     * This function gets all the data from the backend,
     * no filters are applied here since this is data layer.
     */
    fun getMovieCharacters(): Observable<List<MovieCharacter>> {
        return reposClient.queryCharacters()

    }

}