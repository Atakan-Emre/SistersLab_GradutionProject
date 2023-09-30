// Parcelable arayüzünü uygulayarak Android'de kolayca iletilen ürün veri modeli
package com.example.sisterslab_ecommerce.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    val description: String,  // Ürün açıklaması
    val id: Int,  // Ürün ID'si
    val price: Double,  // Normal fiyat
    val imageOne: String,  // Ürünün birinci resmi
    val salePrice: Double,  // Satış fiyatı
    val title: String  // Ürün başlığı
): Parcelable