package uk.co.sullenart.kallingkard.heroku

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HerokuReverser @Inject constructor() {
    interface HerokuService {
        @GET("reverse")
        fun reverse(@Query("input") input: String): Single<String>
    }

    private val herokuService = Retrofit.Builder()
            .baseUrl("https://heroku-callingcard.herokuapp.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(HerokuService::class.java)

    fun reverse(input: String) =
            herokuService.reverse(input)
}