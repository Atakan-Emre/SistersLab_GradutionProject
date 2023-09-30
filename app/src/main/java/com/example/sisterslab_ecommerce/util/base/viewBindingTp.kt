// Bu paket temel uygulama işlevselliği sağlamak amacıyla oluşturulmuştur.
package com.example.sisterslab_ecommerce.util.base

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Bir AppCompatActivity için bir ViewBinding örneği oluşturur.
 *
 * @param T ViewBinding tipi.
 * @param factory ViewBinding örneğini oluşturmak için kullanılacak fabrika fonksiyonu.
 * @return Oluşturulan ViewBinding örneğine tembel bir delegeden döner.
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline factory: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    // LayoutInflater'i fabrika fonksiyonuna geçirerek ViewBinding örneğini oluşturur.
    factory(layoutInflater)
}

/**
 * Bir Fragment için bir ViewBinding örneği oluşturur.
 *
 * @param T ViewBinding tipi.
 * @param factory ViewBinding örneğini oluşturmak için kullanılacak fabrika fonksiyonu.
 * @return Oluşturulan ViewBinding örneğine bir ReadOnlyProperty delegesi döner.
 */
fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

        // Bağlama örneğini tutar
        private var binding: T? = null

        /**
         * Bağlama örneğini sağlar, null ise oluşturur ve fragment'ın görünüm yaşam döngüsünü gözlemler.
         *
         * @param thisRef Fragment referansı.
         * @param property KProperty referansı.
         * @return ViewBinding örneği.
         * @throws IllegalStateException Eğer fragment görünümü null ise bir istisna fırlatır.
         */
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            return binding ?: run {
                val view = thisRef.view ?: throw IllegalStateException("Fragment view is null")
                factory(view).also {
                    if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                        // Görünüm yaşam döngüsü en azından INITIALIZED durumunda ise, yaşam döngüsü gözlemcisini ekler ve bağlamayı ayarlar
                        viewLifecycleOwner.lifecycle.addObserver(this)
                        binding = it
                    }
                }
            }
        }

        /**
         * Görünüm yok edildiğinde bağlama örneğini temizler.
         *
         * @param owner LifecycleOwner referansı.
         */
        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }
    }
