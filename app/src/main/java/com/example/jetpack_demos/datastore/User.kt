package com.example.jetpack_demos.datastore

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class User(val id: Int, val name: String, val isVip: Boolean = false) {
    companion object {
        fun parseFrom(input: InputStream): User {
            return Json.decodeFromStream(input)
        }
    }

    fun writeTo(output: OutputStream) {
        Json.encodeToStream(this, output)
    }
}