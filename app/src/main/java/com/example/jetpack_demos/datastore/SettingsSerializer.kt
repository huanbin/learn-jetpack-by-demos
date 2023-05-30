package com.example.jetpack_demos.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.jetpack_demos.MySettings
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<MySettings> {
    override val defaultValue: MySettings = MySettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): MySettings {
        try {
            return MySettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: MySettings,
        output: OutputStream
    ) = t.writeTo(output)
}

