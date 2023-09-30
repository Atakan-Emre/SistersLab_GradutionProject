// Tek bir ürünün detaylarını içeren veri modeli
package com.example.sisterslab_ecommerce.data.model

data class ProductBagResponse (
    val description: String,  // Ürün açıklaması
    val id: Int,  // Ürün ID'si
    val imageOne: String,  // Ürünün birinci resmi
    val price: Double,  // Ürün fiyatı
    val title: String  // Ürün başlığı
)