package com.example.sisterslab_ecommerce.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisterslab_ecommerce.data.model.Product
import com.example.sisterslab_ecommerce.data.model.ProductResponse  // Import edilen ProductResponse
import com.example.sisterslab_ecommerce.data.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {

    private var job: Job? = null  // Arka plan işlemi için Job referansı

    val productLiveData = MutableLiveData<List<ProductResponse>?>()  // Ürün verileri için LiveData
    val searchLiveData = MutableLiveData<List<ProductResponse>?>()  // Arama sonuçları için LiveData
    val productRepository = ProductRepository()  // Ürün Repository

    init {
        getProducts()  // ViewModel başladığında ürünleri al
    }

    // Ürünleri almak için fonksiyon
    fun getProducts() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = productRepository.getProduct()  // Repository'den ürünleri al
            if (result.isSuccessful) {
                (result.body() as? Product)?.let { productResponse ->  // Tip kontrolü ve dönüşüm
                    println("Update $productResponse")
                    productLiveData.postValue(productResponse.products)  // LiveData'yı güncelle
                }
            }
        }
    }

    // Belirli bir sorgu ile ürün arama fonksiyonu
    fun searchFromProduct(query: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = productRepository.searchFromProduct(query)  // Repository'den arama yap
            if (result.isSuccessful) {
                (result.body() as? Product)?.let { productResponse ->  // Tip kontrolü ve dönüşüm
                    Log.d("Aranan Ürün:", productResponse.toString())
                    searchLiveData.postValue(productResponse.products)  // Arama sonuçları LiveData'yı güncelle
                }
            } else {
                searchLiveData.postValue(null)  // Başarısız ise LiveData'yı null ile güncelle
            }
        }
    }
}
