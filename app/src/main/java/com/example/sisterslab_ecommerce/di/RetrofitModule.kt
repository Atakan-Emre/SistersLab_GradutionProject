// Bu paket Dependency Injection (DI) için gerekli konfigürasyonları ve modülleri içerir.
package com.example.sisterslab_ecommerce.di

import com.example.sisterslab_ecommerce.services.ProductApi
import com.example.sisterslab_ecommerce.util.constants.Constants.BASE_URL
import com.example.sisterslab_ecommerce.util.constants.Constants.STORE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitModule {


    /**
     * HTTP isteklerini yönlendirmek ve düzenlemek için bir Interceptor oluşturur.
     *
     * @return Oluşturulan Interceptor nesnesi.
     */
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .addHeader("Content-Type", "application/json")  // JSON içerik tipi başlığını ekler
                .addHeader("store", STORE)  // Mağaza başlığını ekler
                .build()  // Yeni bir HTTP isteği oluşturur

            it.proceed(request)  // Düzenlenmiş isteği gerçekleştirir
        }
    }

    /**
     * OkHttpClient örneği oluşturur.
     *
     * @param interceptor HTTP isteklerini ve yanıtlarını yönlendirmek için kullanılan Interceptor.
     * @return Oluşturulan OkHttpClient nesnesi.
     */
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)  // Interceptor'u ekler
            .connectTimeout(30, TimeUnit.SECONDS)  // Bağlantı zaman aşımı süresini ayarlar
            .readTimeout(30, TimeUnit.SECONDS)  // Okuma zaman aşımı süresini ayarlar
            .writeTimeout(50, TimeUnit.SECONDS)  // Yazma zaman aşımı süresini ayarlar

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))  // Kullanılacak protokolleri belirtir
            .build()  // OkHttpClient örneğini oluşturur ve döndürür
    }

    // Retrofit örneğini oluşturur ve ProductApi servisini yapılandırır
    val retrofit: ProductApi = Retrofit.Builder()
        .baseUrl(BASE_URL)  // Temel URL'yi ayarlar
        .addConverterFactory(GsonConverterFactory.create())  // GSON dönüştürücü fabrikasını ekler
        .client(getOkHttpClient(getInterceptor()))  // OkHttpClient örneğini ayarlar
        .build()  // Retrofit örneğini oluşturur
        .create(ProductApi::class.java)  // ProductApi servisini oluşturur ve döndürür
}
