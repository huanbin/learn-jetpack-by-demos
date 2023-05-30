package com.example.jetpack_demos.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream


object UserSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User(0, "")

    override suspend fun readFrom(input: InputStream): User {
        return User.parseFrom(input)
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }
}