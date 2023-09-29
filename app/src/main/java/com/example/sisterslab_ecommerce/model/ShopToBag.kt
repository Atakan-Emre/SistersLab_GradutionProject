// Kullanıcının alışveriş çantasına ürün eklemesini temsil eden veri modeli
package com.example.sisterslab_ecommerce.model

data class ShopToBag (
    val userId: String,  // Kullanıcı ID'si
    val productId: Int  // Ürün ID'si
)