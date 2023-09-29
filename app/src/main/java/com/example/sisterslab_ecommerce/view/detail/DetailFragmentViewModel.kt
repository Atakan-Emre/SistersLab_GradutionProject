package com.example.sisterslab_ecommerce.view.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisterslab_ecommerce.model.ProductBagResponse
import com.example.sisterslab_ecommerce.model.ShopToBag
import com.example.sisterslab_ecommerce.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// DetailFragmentViewModel, DetailFragment için ViewModel'dir.
// Bu ViewModel, ürün detaylarını almak ve sepete ürün eklemek için metodlar içerir.
class DetailFragmentViewModel : ViewModel() {

    // Coroutine'ler için arka plan işi
    private var job: Job? = null

    // Ürün detayları için LiveData. ProductBagResponse? null olmasına izin verir.
    val detailLiveData = MutableLiveData<ProductBagResponse?>()

    // Ağ işlemleri için ProductRepository örneği.
    private val productRepository = ProductRepository()  // Bu private olmalıdır çünkü ViewModel dışında erişilebilir olmamalıdır.

    // Sepete ekleme işlemi için LiveData. ShopToBag? null olmasına izin verir.
    var addCartLiveData = MutableLiveData<ShopToBag?>()

    // detailProducts metodunu verilen ürün id'si için ürün detaylarını alır.
    fun detailProducts(id: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = productRepository.detailProduct(id)  // Ürün detaylarını alıyor
            if (result.isSuccessful) {
                result.body()?.let { products ->
                    Log.d("Ürünler : ", products.toString())  // Ürünleri logluyor
                    detailLiveData.postValue(products.product)  // Ürünlerle LiveData'yı güncelliyor
                }
            } else {
                detailLiveData.postValue(null)  // İstek başarılı değilse LiveData'yı null ile güncelliyor
            }
        }
    }

    // addToCart metodu bir ürünü sepete ekler.
    fun addToCart(addToCart: ShopToBag) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = productRepository.addToCart(addToCart)  // Ürünü sepete ekliyor
            if (result.isSuccessful) {
                result.body()?.let {
                    addCartLiveData.postValue(result.body())  // Sepete ekleme yanıtı ile LiveData'yı güncelliyor
                }
            } else {
                addCartLiveData.postValue(null)  // İstek başarılı değilse LiveData'yı null ile güncelliyor
            }
        }
    }

    // onCleared'i override ederek, ViewModel temizlendiğinde işi iptal ediyoruz
    override fun onCleared() {
        super.onCleared()
        job?.cancel()  // Bellek sızıntılarını önlemek için işi iptal ediyor
    }
}
