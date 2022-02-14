package com.example.model

import com.google.gson.annotations.SerializedName

class DataModelDto(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)