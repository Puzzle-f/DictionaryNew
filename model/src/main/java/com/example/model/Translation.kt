package com.example.model

import com.google.gson.annotations.SerializedName

class TranslationDto(@field:SerializedName("text") val translation: String?)

data class Translation(val translation: String = "")