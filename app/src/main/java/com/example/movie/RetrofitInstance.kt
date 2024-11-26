package com.example.movie

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitInstance {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NWM0ZmYyODcxZTkzOTMzNzEyOGM4YzNlYjZiNWQ0ZiIsIm5iZiI6MTczMjAwMTQ5OS42MTI1MTAyLCJzdWIiOiI2NzNjM2Q4ZTI0ODViODViM2NhOGNjOWIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.qpd-T9An6RbYZfmwfJD30FGG-YtdjOjot-PYRfrNCQY"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $BEARER_TOKEN")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieTvService: MovieTvService by lazy {
        retrofit.create(MovieTvService::class.java)
    }
}
