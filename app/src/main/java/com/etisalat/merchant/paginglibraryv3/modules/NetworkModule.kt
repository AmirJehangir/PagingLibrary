package com.etisalat.merchant.paginglibraryv3.modules

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.etisalat.merchant.paginglibraryv3.BuildConfig
import com.etisalat.merchant.paginglibraryv3.R
import com.etisalat.merchant.paginglibraryv3.data.source.SharedPrefDataSource
import com.etisalat.merchant.paginglibraryv3.data.source.remote.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
/**
 * Created by Amir.jehangir on 26,September,2021
 */
val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get()) }

    single { createOkHttpClient(get(), get()) }

}

fun createOkHttpClient(context: Context, sharedPref: SharedPrefDataSource): OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BODY
        } else
            HttpLoggingInterceptor.Level.NONE

//    val cf = CertificateFactory.getInstance("X.509")
//    var caInput: InputStream? = null
//    caInput = BufferedInputStream(context.resources.openRawResource(R.raw.uat_mart_app))
//    val ca: X509Certificate = caInput.use {
//        cf.generateCertificate(it) as X509Certificate
//    }
//    val keyStoreType = KeyStore.getDefaultType()
//    val keyStore = KeyStore.getInstance(keyStoreType).apply {
//        load(null, null)
//        setCertificateEntry("ca", ca)
//    }
//    val tmf = getTrustManager(keyStore)
//    val sslContext: SSLContext = SSLContext.getInstance("TLS").apply {
//        init(null, tmf.trustManagers, null)
//    }

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .cache(null)
       // .sslSocketFactory(sslContext.socketFactory, tmf.trustManagers[0] as X509TrustManager)
        .hostnameVerifier(HostnameVerifier { hostname, session -> true })
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()



            if (BuildConfig.FLAVOR.equals("amirWiremock", true) || BuildConfig.FLAVOR.equals(
                    "awaisWiremock", true)) {
                requestBuilder.header("Content-Type", "application/json; charset=utf-8")
            }
            requestBuilder.header("X-app-build-version", "${BuildConfig.VERSION_CODE}")
            requestBuilder.header("X-app-version", BuildConfig.VERSION_NAME)
            requestBuilder.header("X-os-version", "${Build.VERSION.SDK_INT}")
            requestBuilder.header(
                "X-device-id",
                Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)
            )
            requestBuilder.header("X-device-model", Build.MANUFACTURER + " " + Build.MODEL)
            requestBuilder.header("X-app-language", sharedPref.currLang)
            requestBuilder.header("X-request-os", "Android")
            requestBuilder.header("Service-requester", "mobile-app")


            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiInterface {
    return retrofit.create(ApiInterface::class.java)
}

fun getTrustManager(keyStore: KeyStore): TrustManagerFactory {
    val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
    val tmf: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm).apply {
        init(keyStore)
    }
    return tmf
}