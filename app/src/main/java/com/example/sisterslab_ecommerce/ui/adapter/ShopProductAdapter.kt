package com.example.sisterslab_ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sisterslab_ecommerce.R
import com.example.sisterslab_ecommerce.data.model.ProductResponse
import com.example.sisterslab_ecommerce.databinding.BagCardBinding
import com.example.sisterslab_ecommerce.util.network.downloadFromUrl

// Sepetteki Ürünlerin listelendiği Adapter sınıfı
class ShopProductAdapter(
    private val onItemClickListener: (Int) -> Unit  // Ürün silme butonu tıklanınca tetiklenen dinleyici
) : RecyclerView.Adapter<ShopProductAdapter.RowHolder>() {

    // Ürünleri tutmak için liste
    private val shoppingBagProductList = ArrayList<ProductResponse>()

    // ViewHolder sınıfı, her bir ürün kartını temsil eder
    inner class RowHolder(private val binding: BagCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Ürün verilerini görünüme bağla
        fun bind(shoppingBagProduct: ProductResponse) {
            binding.apply {
                bagProductNameTextview.text = shoppingBagProduct.title  // Ürün başlığını ayarla
                bagProductPriceTextview.text = String.format(
                    itemView.context.resources.getString(R.string.price),shoppingBagProduct.price.toString()) // Ürün fiyatını ayarla
                bagProductImagesview.downloadFromUrl(shoppingBagProduct.imageOne)  // Ürün resmini indir ve ayarla
                deleteIv.setOnClickListener {
                    onItemClickListener.invoke(shoppingBagProduct.id)  // Silme butonu tıklandığında dinleyiciyi bilgilendir
                }
            }
        }
    }

    // Toplam ürün sayısını al
    override fun getItemCount(): Int {
        return shoppingBagProductList.size
    }

    // Ürün verilerini görünüme bağla
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val cartProduct = shoppingBagProductList[position]
        holder.bind(cartProduct)
    }

    // Yeni bir ViewHolder oluştur ve görünüm bağlamayı (view binding) ayarla
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = BagCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    // Ürün listesini güncelle ve adapterı bilgilendir
    fun updateList(updateList: List<ProductResponse>) {
        val diffUtil = DiffUtil.calculateDiff(ProductDiffCallback(shoppingBagProductList, updateList))
        shoppingBagProductList.clear()  // Listeyi temizle
        shoppingBagProductList.addAll(updateList)  // Yeni ürünlerle listeyi doldur
        diffUtil.dispatchUpdatesTo(this)  // Adapter'a verilerin güncellendiğini bildir
    }
}

// DiffUtil için Callback sınıfı
class ProductDiffCallback(
    private val oldList: List<ProductResponse>,
    private val newList: List<ProductResponse>
) : DiffUtil.Callback() {

    // Eski ve yeni liste öğelerini karşılaştırır ve ID'lerine göre aynı olup olmadığını kontrol eder
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    // Eski listenin boyutunu döndür
    override fun getOldListSize(): Int = oldList.size

    // Yeni listenin boyutunu döndür
    override fun getNewListSize(): Int = newList.size

    // Eski ve yeni liste öğelerini karşılaştırır ve içeriklerinin aynı olup olmadığını kontrol eder
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
