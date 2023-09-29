package com.example.sisterslab_ecommerce.view.shop

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.example.sisterslab_ecommerce.R
import com.example.sisterslab_ecommerce.adapter.ShopProductAdapter
import com.example.sisterslab_ecommerce.base.viewBinding
import com.example.sisterslab_ecommerce.model.DeleteShoppingBag
import com.example.sisterslab_ecommerce.databinding.FragmentShopBinding

class ShopFragment : Fragment(R.layout.fragment_shop) {

    // Binding ve ViewModel nesnelerini tembel yükleme ile başlat
    private val binding by viewBinding(FragmentShopBinding::bind)
    private val viewModel: ShopFragmentViewModel by lazy { ViewModelProvider(this).get(ShopFragmentViewModel::class.java) }
    private lateinit var adapter: ShopProductAdapter  // Adapter nesnesini başlat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView için LinearLayoutManager'ı ayarla
        binding.bagProductRv.layoutManager = LinearLayoutManager(requireContext())

        // Adapter nesnesini başlat ve RecyclerView'a ata
        adapter = ShopProductAdapter(::deleteFromCart)
        binding.bagProductRv.adapter = adapter

        // ViewModel'den alışveriş sepetindeki ürünleri al
        viewModel.cartProducts()

        // LiveData nesnelerini gözlemle
        observe()
    }

    // LiveData gözlemleme fonksiyonu
    private fun observe() {
        // Sepetteki ürünlerin listesini gözlemle
        viewModel.cartLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.updateList(it)  // Eğer liste boş değilse, adapter'ı güncelle
            } else {
                Snackbar.make(requireView(), "Liste boş", Snackbar.LENGTH_LONG).show()  // Eğer liste boş ise, Snackbar göster
            }
        }
        // Silinen ürünleri gözlemle
        viewModel.deleteLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("ShopFragment", "Ürün Silindi!")  // Eğer ürün başarıyla silinirse, loga yaz
            } else {
                Log.e("ShopFragment", "Ürün silinemedi.")  // Eğer ürün silinemezse, hata logunu yaz
            }
        }
    }

    // Sepetten ürün silme fonksiyonu
    fun deleteFromCart(id: Int) {
        // Silme işlemi için bir AlertDialog göster
        AlertDialog.Builder(requireContext())
            .setTitle("Ürün Sil")
            .setMessage("Ürünü silmek istiyor musunuz?")
            .setPositiveButton("Evet") { dialog, _ ->
                viewModel.deleteFromCart(DeleteShoppingBag(id))  // Evet'e tıklandığında ürünü sil
                dialog.dismiss()  // Dialog'u kapat
            }
            .setNegativeButton("Hayır") { dialog, _ ->
                dialog.dismiss()  // Hayır'a tıklandığında dialog'u kapat
            }
            .show()
    }
}
