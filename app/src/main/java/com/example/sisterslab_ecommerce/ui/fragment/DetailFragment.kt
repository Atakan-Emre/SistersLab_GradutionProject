package com.example.sisterslab_ecommerce.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.example.sisterslab_ecommerce.R
import com.example.sisterslab_ecommerce.util.base.viewBinding
import com.example.sisterslab_ecommerce.data.model.ShopToBag
import com.example.sisterslab_ecommerce.databinding.FragmentDetailBinding
import com.example.sisterslab_ecommerce.util.network.downloadFromUrl
import com.example.sisterslab_ecommerce.ui.viewmodel.DetailFragmentViewModel

// DetailFragment, kullanıcıya bir ürünün detaylarını gösterir.
class DetailFragment : Fragment(R.layout.fragment_detail) {

    // Binding ve ViewModel lazy initialization ile tanımlanır.
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailFragmentViewModel by lazy { DetailFragmentViewModel() }

    // NavArgs ile geçirilen argümanlar alınır.
    private val args: DetailFragmentArgs by navArgs()

    // Fragment görünürlük kazandığında çağrılır.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Argümanlardan ürün id'sini alır.
        val id = args.id

        Log.d("id", id.toString())  // Debug log ile id'yi logcat'e yazdırır.
        viewModel.detailProducts(id)  // ViewModel üzerinden detaylı ürün bilgisini alır.
        observe()  // LiveData observasyonunu başlatır.

        // Alışveriş butonuna tıklanınca sepete ekleme işlemi yapılır.
        binding.shopNowButton.setOnClickListener {
            viewModel.addToCart(ShopToBag("b3sa6dj721312ssadas21d" ,id))
            cartObserve()  // Sepete ekleme işleminin sonucunu gözlemleyen observasyonu başlatır.
        }

        // Toolbar'ın geri okuna tıklanınca bir önceki fragment'a geri dönülür.
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    // LiveData observasyon fonksiyonu, detaylı ürün bilgisinin güncellenmesini gözlemler.
    private fun observe() {
        viewModel.detailLiveData.observe(viewLifecycleOwner) {
            if(it != null){
                binding.productDetailTextview.text = it.title
                binding.descriptionTextview.text = it.description
                binding.PriceTextview.text = getString(R.string.price, it.price.toString())
                binding.productDetailImagesview.downloadFromUrl(it.imageOne)
            }else{
                Snackbar.make(requireView(), "Güncelleme Yapılıyor", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    // Sepete ekleme işleminin sonucunu gözlemleyen fonksiyon.
    private fun cartObserve() {
        viewModel.addCartLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                Snackbar.make(requireView(), "Ürün Eklendi", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()  // Ürün eklendikten sonra bir önceki fragment'a geri döner.
            } else {
                Snackbar.make(requireView(), "Ürün Eklenemedi", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
