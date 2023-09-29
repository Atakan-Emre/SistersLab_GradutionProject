// Tüm ürünleri listelemek için kullanılan veri modeli
package com.example.sisterslab_ecommerce.model

data class Product(
    val message: String,  // Yanıt mesajı
    val products: List<ProductResponse>,  // Ürün listesi
    val status: Int  // Yanıt durumu
)