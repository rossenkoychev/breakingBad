package com.example.rossen.breakingbad.web


import com.example.rossen.breakingbad.model.MovieCharacter
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class is used to build and parametrise retrofit
 */
class ReposClient {
    private val baseUrl = "https://breakingbadapi.com"
    private val breakingBadService: BreakingBadService

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        breakingBadService = retrofit.create(BreakingBadService::class.java)
    }

    fun queryCharacters(): Observable<List<MovieCharacter>> {
        return breakingBadService.queryCharacters()
    }


}