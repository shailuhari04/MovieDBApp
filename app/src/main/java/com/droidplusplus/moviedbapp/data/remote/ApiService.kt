package com.droidplusplus.moviedbapp.data.remote

import android.content.Context
import com.droidplusplus.moviedbapp.BuildConfig
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.model.response.MovieCreditResponse
import com.droidplusplus.moviedbapp.data.model.response.MovieListResponse
import com.droidplusplus.moviedbapp.data.model.response.MovieReviewResponse
import com.droidplusplus.moviedbapp.data.model.response.SimilarMovieListResponse
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovieAsync(@QueryMap hashMap: HashMap<String, String> = HashMap()): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieAsync(@Path("movie_id") movieId: String): Movie

    @GET("3/movie/{movie_id}/reviews")
    suspend fun getMovieReviewsAsync(@Path("movie_id") movieId: String): MovieReviewResponse

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieCreditsAsync(@Path("movie_id") movieId: String): MovieCreditResponse

    @GET("3/movie/{movie_id}/similar")
    suspend fun getMovieSimilarAsync(
        @Path("movie_id") movieId: String,
        @QueryMap hashMap: HashMap<String, String> = HashMap()
    ): SimilarMovieListResponse
}

object ApiParams {
    const val PAGE = "page"
}

object APIClient {
    const val TIMEOUT = 30

    fun createOkHttpCache(context: Context): Cache =
        Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

    fun createLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    fun createHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
                .method(request.method, request.body)
                .build()
            chain.proceed(newRequest)
        }

    fun createOkHttpClient(
        logging: Interceptor,
        header: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(header)
            .addInterceptor(logging)
            .build()

    fun createAppRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun createApiService(retrofit: Retrofit, mockApi: MockApi, mock: Mock): ApiService =
        if (mock.isMock) mockApi
        else retrofit.create(ApiService::class.java)
}

class Mock(val isMock: Boolean)