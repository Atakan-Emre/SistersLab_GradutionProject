package com.example.sisterslab_ecommerce.util.constants

// Constants adında bir Singleton object tanımlanıyor.
// Bu object içinde uygulama genelinde kullanılabilecek sabit değerler bulunuyor.
object Constants {
    // API istekleri için temel URL adresi.
    // Bu değer, Retrofit gibi bir HTTP istemcisi tarafından kullanılabilir.
    const val BASE_URL = "https://api.canerture.com/ecommerce/"

    // Mağaza ismi sabiti.
    // Bu değer, uygulama içinde farklı yerlerde kullanılabilir, örneğin istatistiklerde veya analizlerde.
    const val STORE = "canerture"
}
