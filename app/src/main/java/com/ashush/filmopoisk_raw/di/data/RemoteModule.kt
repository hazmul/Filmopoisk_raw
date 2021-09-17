package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.ashush.filmopoisk_raw.data.config.DataConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Модуль зависимостей связанный с объектами работающими с сетью ([Retrofit] и [OkHttpClient])
 */

@Module
class RemoteModule {

    /**
     * Предоставление сформированного экземпляра [Retrofit] с на основе заданного [OkHttpClient]
     */

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DataConfig.baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * Предоставление сформированного экземпляра [OkHttpClient]
     */

    @Singleton
    @Provides
    fun provideOkHttpClient(applicationContext: Context): OkHttpClient {

        val cacheSize = (10 * 1024 * 1024 * 10).toLong() // 100 MB
        val cache = Cache(applicationContext.cacheDir, cacheSize)

        /**
         * Проверка допуспности интернета
         */

        fun isInternetAvailable(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }
            return result
        }

        /**
         * Перехватичк запросов для отладки и их отображении в консоли
         */

        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

        /**
         * Оффлайн перехватичк запросов для реализаци кэшировании
         */
        val offlineInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            if (!isInternetAvailable(applicationContext)) {
                val maxStale = 60 * 60 * 24 * 14 // Offline cache available for 14 days
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }

        /**
         * Онлайн перехватичк запросов для реализаци кэшировании
         */
        val onlineInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(cache)
            .build()
    }
}