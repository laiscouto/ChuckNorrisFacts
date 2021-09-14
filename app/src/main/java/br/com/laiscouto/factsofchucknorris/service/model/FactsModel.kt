package br.com.laiscouto.factsofchucknorris.service.model

import com.google.gson.annotations.SerializedName

data class FactsModel(
    val result: List<ResultOfFacts>
)

data class ResultOfFacts (
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("value")
    val value:String,
    @SerializedName("url")
    val url : String
    )
