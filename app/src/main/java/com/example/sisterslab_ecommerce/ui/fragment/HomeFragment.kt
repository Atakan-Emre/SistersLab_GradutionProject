package com.example.sisterslab_ecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.example.sisterslab_ecommerce.R
import com.example.sisterslab_ecommerce.ui.adapter.ProductAdapter
import com.example.sisterslab_ecommerce.util.base.viewBinding
import com.example.sisterslab_ecommerce.databinding.FragmentHomeBinding
import com.example.sisterslab_ecommerce.ui.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    // View Binding ile bağlantı oluşturuluyor.
    private val binding by viewBinding(FragmentHomeBinding::bind)
    // Adapter başlangıçta null, lazy initialization ile ViewModel oluşturuluyor.
    private var adapter: ProductAdapter? = null
    private val viewModel: HomeFragmentViewModel by lazy { HomeFragmentViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel'dan ürünleri alma işlemi başlatılıyor.
        viewModel.getProducts()
        // Arama işlevselliği için metot çağrılıyor.
        search()
        // LiveData nesneleri üzerinde gözlemleme işlemi başlatılıyor.
        observe()
    }

    private fun observe() {
        viewModel.productLiveData.observe(viewLifecycleOwner) {
            it?.let {
                // List it nesnesini ArrayList'e dönüştürüyor ve adapter ile bağlıyor.
                adapter = ProductAdapter(ArrayList(it), ::homeToDetail)
                binding.productRv.adapter = adapter
                adapter?.notifyDataSetChanged()
            } ?: run {
                // it null ise Snackbar ile kullanıcıya bilgi veriliyor.
                Snackbar.make(requireView(), "liste boş", Snackbar.LENGTH_LONG).show()
            }
        }
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            it?.let {
                // Arama sonuçları için yeni bir adapter oluşturuluyor ve RecyclerView'e bağlanıyor.
                adapter = ProductAdapter(ArrayList(it), ::homeToDetail)
                binding.productRv.adapter = adapter
                adapter?.notifyDataSetChanged()
            } ?: run {
                // it null ise Snackbar ile kullanıcıya bilgi veriliyor.
                Snackbar.make(requireView(), "liste boş", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    // Bu metot ile HomeFragment'tan DetailFragment'a yönlendirme işlemi gerçekleştiriliyor.
    private fun homeToDetail(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    // Arama işlevselliği bu metot ile sağlanıyor.
    fun search() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Arama sorgusunu ViewModel'a iletiyor ve arama işlemini başlatıyor.
                viewModel.searchFromProduct(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Arama metni değiştikçe arama işlemini tekrar başlatıyor.
                viewModel.searchFromProduct(newText)
                return true
            }
        })
    }
}
