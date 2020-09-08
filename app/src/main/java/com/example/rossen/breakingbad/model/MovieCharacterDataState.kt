package com.example.rossen.breakingbad.model

/**
 * Class representing the state of movie characters' data.
 * They can be in any of the following three states:
 * state Loading - when the app is just started;
 * state DataError is when we were not able to fetch the data
 * state Repositories is used for when we already have all the repositories in memory
 */
sealed class MovieCharacterDataState {
    object Loading : MovieCharacterDataState()
    data class DataError(val message: String?) : MovieCharacterDataState()
    data class MovieCharacters(val movieCharacters: List<MovieCharacter>) : MovieCharacterDataState()
}