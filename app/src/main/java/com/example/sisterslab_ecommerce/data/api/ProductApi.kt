package com.example.sisterslab_ecommerce.data.api

import com.example.sisterslab_ecommerce.data.model.ShopToBag
import com.example.sisterslab_ecommerce.data.model.DeleteShoppingBag
import com.example.sisterslab_ecommerce.data.model.Detail
import com.example.sisterslab_ecommerce.data.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// Bu interface, retrofit kütüphanesi aracılığıyla backend servisleri ile iletişim kurmayı sağlar.
interface ProductApi{

    // Bu metod, backend servisten tüm ürünleri alır.
    @GET("get_products.php")
    suspend fun getProduct(): Response<Product>

    // Bu metod, belirli bir ürünün detaylarını alır, 'id' parametresini URL'ye ekleyerek.
    @GET("get_product_detail.php")
    suspend fun detailProduct(
        @Query("id") id: Int
    ): Response<Detail>

    // Bu metod, belirli bir kullanıcının sepetindeki ürünleri alır. Kullanıcı ID'si URL'de sabit olarak verilmiştir.
    @GET("get_cart_products.php?userId=b3sa6dj721312ssadas21d")
    suspend fun getCartProduct(): Response<Product>

    // Bu metod, bir ürünü sepete ekler, 'addToCart' parametresini HTTP body'si olarak göndererek.
    @POST("add_to_cart.php")
    suspend fun addToCart(
        @Body addToCart: ShopToBag
    ): Response<ShopToBag>

    // Bu metod, bir ürünü sepetten siler, 'deleteFromCart' parametresini HTTP body'si olarak göndererek.
    @POST("delete_from_cart.php")
    suspend fun deleteFromCart(
        @Body deleteFromCart: DeleteShoppingBag
    ): Response<DeleteShoppingBag>

    // Bu metod, belirli bir sorgu ile ürün araması yapar, 'query' parametresini URL'ye ekleyerek.
    @GET("search_product.php")
    suspend fun searchFromProduct(
        @Query("query") query: String
    ): Response<Product>
}
