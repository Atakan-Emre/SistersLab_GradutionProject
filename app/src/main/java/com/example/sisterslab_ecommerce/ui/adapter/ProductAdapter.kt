package com.example.sisterslab_ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sisterslab_ecommerce.R
import com.example.sisterslab_ecommerce.data.model.ProductResponse
import com.example.sisterslab_ecommerce.databinding.ProductCardBinding
import com.example.sisterslab_ecommerce.util.network.downloadFromUrl

class ProductAdapter(
    private var productList: ArrayList<ProductResponse>,  // productList'ı var olarak değiştirildi ve tipi ArrayList olarak güncellendi
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.RowHolder>() {

    // ViewHolder sınıfı, her bir ürün kartını temsil eder
    inner class RowHolder(private val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Ürün verilerini görünüme bağla
        fun bind(product: ProductResponse) {
            binding.apply {
                productTextview.text = product.title  // Ürün başlığını ayarla
                // Ürün fiyatını ayarla
                priceTextview.text = String.format(
                    itemView.context.resources.getString(R.string.price),
                    product.price.toString()
                )
                productImagesview.downloadFromUrl(product.imageOne)  // Ürün resmini indir ve ayarla
                productImagesview.contentDescription = product.title  // Erişilebilirlik için içerik açıklaması ayarla
                root.setOnClickListener {
                    onItemClickListener.invoke(product.id)  // Ürün kartına tıklandığında dinleyiciyi bilgilendir
                }
            }
        }
    }



    // Yeni bir ViewHolder oluştur ve görünüm bağlamayı (view binding) ayarla
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = ProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    // Toplam ürün sayısını al
    override fun getItemCount(): Int {
        return productList.size
    }

    // Ürün verilerini görünüme bağla
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    // Ürün listesini güncelle ve adapterı bilgilendir
    fun updateList(newList: List<ProductResponse>) {
        val diffUtil = DiffUtil.calculateDiff(ProductAdapterDiffCallback(productList, newList))  // Fark hesapla
        productList.clear()  // Listeyi temizle
        productList.addAll(newList)  // Yeni ürünlerle listeyi doldur
        diffUtil.dispatchUpdatesTo(this)  // Adapter'a verilerin güncellendiğini bildir
    }
}

class ProductAdapterDiffCallback(
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
