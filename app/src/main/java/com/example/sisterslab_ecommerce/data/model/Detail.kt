// Ürün detaylarına erişim için kullanılan veri modeli
package com.example.sisterslab_ecommerce.data.model

data class Detail(
    val message: String,  // Yanıt mesajı
    val product: ProductBagResponse,  // Ürün detayları
    val status: Int  // Yanıt durumu
)