package com.example.mealsearchpractice.di

import android.content.Context
import android.os.Build
import com.example.mealsearchpractice.common.Constants.HEADER_CACHE_CONTROL
import com.example.mealsearchpractice.common.Constants.HEADER_PRAGMA
import com.example.mealsearchpractice.data.remote.MealSearchAPI
import com.example.mealsearchpractice.utils.NetworkUtils
import com.facebook.stetho.okhttp3.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.internal.userAgent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val URL = "https://www.themealdb.com/"

    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context
    ): OkHttpClient {

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(provideOfflineCacheInterceptor(context))
//            .addInterceptor(AuthorizationInterceptor())
            .addInterceptor(UserAgentInterceptor("MealSearchPractice/" + BuildConfig.VERSION_NAME + " (" + BuildConfig.APPLICATION_ID + "; build:" + BuildConfig.VERSION_CODE + "; android " + Build.VERSION.RELEASE + ") " + userAgent))
            .addNetworkInterceptor(provideCacheInterceptor(context))
            .cache(provideCache(context))

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor();
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
            httpClient.addNetworkInterceptor(StethoInterceptor())
        }

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024) // 10 MB
        } catch (e: Exception) {
            Timber.e("Cache: Could not create Cache!")
        }
        return cache
    }

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.isInternetAvailable(context)) {
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl: CacheControl = if (NetworkUtils.isInternetAvailable(context)) {
                CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()
            } else {
                CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build()
            }
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    class UserAgentInterceptor(private val userAgent: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder: Request.Builder = chain.request().newBuilder()

            builder.addHeader("User-Agent", userAgent)

            return chain.proceed(builder.build());
        }
    }

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi =
        Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(
            moshi
        )

    @Singleton
    @Provides
    fun provideMealSearchAPI(retrofit: Retrofit): MealSearchAPI {
        return retrofit.create(MealSearchAPI::class.java)
    }

    @Provides
    @Singleton fun provideRetrofitClient(
        okHttp: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(URL)
        .client(okHttp)
        .build()

}