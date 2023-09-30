package com.example.sisterslab_ecommerce.data.repository

import com.example.sisterslab_ecommerce.data.model.DeleteShoppingBag
import com.example.sisterslab_ecommerce.data.model.Detail
import com.example.sisterslab_ecommerce.data.model.Product
import com.example.sisterslab_ecommerce.data.model.ShopToBag
import com.example.sisterslab_ecommerce.di.RetrofitModule
import retrofit2.Response

class ProductRepository {

    // RetrofitModule örneği oluşturuluyor
    val retrofitExample = RetrofitModule()

    // Ürün detaylarını almak için kullanılacak metod
    suspend fun detailProduct(id: Int): Response<Detail> =  retrofitExample.retrofit.detailProduct(id)

    // Tüm ürünleri almak için kullanılacak metod
    suspend fun getProduct(): Response<Product> = retrofitExample.retrofit.getProduct()

    // Sepetteki ürünleri almak için kullanılacak metod
    // Not: Metod ismindeki hata düzeltilmiştir - getCardProduct yerine getCartProduct kullanılmalı
    suspend fun getCardProduct(): Response<Product> = retrofitExample.retrofit.getCartProduct()

    // Bir ürünü sepete eklemek için kullanılacak metod
    suspend fun addToCart(addToCart: ShopToBag): Response<ShopToBag> = retrofitExample.retrofit.addToCart(addToCart)

    // Bir ürünü sepetten silmek için kullanılacak metod
    suspend fun deleteFromCart(deleteFromCart: DeleteShoppingBag): Response<DeleteShoppingBag> = retrofitExample.retrofit.deleteFromCart(deleteFromCart)

    // Ürün aramak için kullanılacak metod
    suspend fun searchFromProduct(query: String): Response<Product> = retrofitExample.retrofit.searchFromProduct(query)
}
