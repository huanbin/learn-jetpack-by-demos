package com.example.jetpack_demos.hilt

//@Module
//class NetworkModule {

// @Provides tell Dagger how to create instances of the type that this function
// returns (i.e. LoginRetrofitService).
// Function parameters are the dependencies of this type.
//    @Singleton
//    @Provides
//    fun provideLoginRetrofitService(): LoginRetrofitService {
//        // Whenever Dagger needs to provide an instance of type LoginRetrofitService,
//        // this code (the one inside the @Provides method) is run.
//        return Retrofit.Builder()
//            .baseUrl("https://example.com")
//            .build()
//            .create(LoginService::class.java)
//    }
//}