package com.example.sisterslab_ecommerce.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

// Bu fonksiyon, bir ImageView'ın içeriğini belirli bir URL'den indirir ve bu ImageView'da gösterir.
fun ImageView.downloadFromUrl(url: String) {
    Picasso.get()  // Picasso kütüphanesinin bir örneğini alır.
        .load(url)  // Belirtilen URL'den içerik yükler.
        .into(this)  // Yüklenen içeriği bu ImageView'ın içine yerleştirir.
}
