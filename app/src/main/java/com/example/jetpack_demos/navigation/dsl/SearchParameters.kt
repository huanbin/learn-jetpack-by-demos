package com.example.jetpack_demos.navigation.dsl

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class SearchParameters(
    val searchQuery: String,
    val filters: List<String>
) : Parcelable


val SearchParametersType = object : NavType<SearchParameters>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: SearchParameters) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): SearchParameters {
        return bundle.getParcelable<SearchParameters>(key) as SearchParameters
    }

    override fun parseValue(value: String): SearchParameters {
        return Json.decodeFromString(value)
    }

    // Only required when using Navigation 2.4.0-alpha07 and lower
    override val name = "SearchParameters"
}