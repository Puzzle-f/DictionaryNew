package com.example.model

import com.google.gson.annotations.SerializedName

class MeaningsDto(
    @field:SerializedName("translation") val translation: TranslationDto?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)

data class Meanings(
    val translation: Translation = Translation(),
    val imageUrl: String = ""
)