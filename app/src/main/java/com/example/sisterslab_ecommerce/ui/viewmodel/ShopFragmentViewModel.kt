// ShopFragmentViewModel.kt
package com.example.sisterslab_ecommerce.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisterslab_ecommerce.data.model.DeleteShoppingBag
import com.example.sisterslab_ecommerce.data.model.ProductResponse
import com.example.sisterslab_ecommerce.data.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// ShopFragmentViewModel sınıfı, ViewModel sınıfından türetilmiştir.
class ShopFragmentViewModel : ViewModel() {

    private var job: Job? = null  // İşlem için bir Coroutine Job nesnesi tanımlanır.

    // Sepetteki ürünler ve silme işlemi için LiveData nesneleri tanımlanır.
    val cartLiveData = MutableLiveData<List<ProductResponse>?>()
    val deleteLiveData = MutableLiveData<DeleteShoppingBag?>()

    // ProductRepository nesnesi örneği oluşturulur.
    private val productRepository = ProductRepository()

    // Sepetteki ürünleri almak için bir metod tanımlanır.
    fun cartProducts() {
        job = CoroutineScope(Dispatchers.IO).launch {  // IO Dispatcher üzerinde yeni bir Coroutine başlatılır.
            val result = productRepository.getCardProduct()  // Repository'den sepet ürünlerini al.
            if (result.isSuccessful) {  // Eğer işlem başarılı ise
                result.body()?.let { cartProductList ->  // Sonuçtan dönen cevabı al
                    cartLiveData.postValue(cartProductList.products)  // LiveData'yi güncelle.
                }
            } else {  // Eğer işlem başarısız ise
                cartLiveData.postValue(null)  // LiveData'yi null olarak güncelle.
            }
        }
    }

    // Sepetten bir ürün silmek için bir metod tanımlanır.
    fun deleteFromCart(deleteFromCart: DeleteShoppingBag) {
        job = CoroutineScope(Dispatchers.IO).launch {  // IO Dispatcher üzerinde yeni bir Coroutine başlatılır.
            val result = productRepository.deleteFromCart(deleteFromCart)  // Repository'den sepetten ürün sil.
            if (result.isSuccessful) {  // Eğer işlem başarılı ise
                result.body()?.let { cartProductList ->
                    cartProducts()  // Sepet ürünlerini tekrar al.
                    deleteLiveData.postValue(result.body())  // LiveData'yi güncelle.
                }
            } else {  // Eğer işlem başarısız ise
                deleteLiveData.postValue(null)  // LiveData'yi null olarak güncelle.
            }
        }
    }
}
