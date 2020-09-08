package com.example.rossen.breakingbad.model

import com.google.gson.annotations.SerializedName

class MovieCharacter (
    @SerializedName("char_id") val charId: String,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String
)