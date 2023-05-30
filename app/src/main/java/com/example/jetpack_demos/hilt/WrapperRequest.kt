package com.example.jetpack_demos.hilt

import com.bumptech.glide.RequestBuilder

class WrapperRequest private constructor(
    var url: String,
    var method: String,
    var data: Map<String, String>
) {
    private constructor() : this("", "", emptyMap())

    class RequestBuilder private constructor(
        var url: String,
        var method: String,
        var data: Map<String, String>
    ) {
        constructor() : this("", "", emptyMap())

        fun url(url: String): RequestBuilder {
            this.url = url
            return this
        }

        fun method(method: String): RequestBuilder {
            this.method= method
            return this
        }

        fun data(data: Map<String, String>): RequestBuilder {
            this.data = data
            return this
        }

        fun build(): WrapperRequest {
            return WrapperRequest(url, method, data)
        }
    }

}