package com.example.rossen.breakingbad.web

import com.example.rossen.breakingbad.model.MovieCharacter
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Interface for all web calls
 */
interface BreakingBadService {

    @GET("/api/characters")
    fun queryCharacters(): Observable<List<MovieCharacter>>

}
