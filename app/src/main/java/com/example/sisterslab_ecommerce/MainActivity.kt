package com.example.sisterslab_ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.sisterslab_ecommerce.base.viewBinding
import com.example.sisterslab_ecommerce.databinding.ActivityMainBinding

// MainActivity sınıfı, AppCompatActivity'den türetilir.
class MainActivity : AppCompatActivity() {

    // ActivityMainBinding sınıfından bir binding nesnesi oluşturulur.
    // Bu nesne, XML layout ile Kotlin kodu arasında bir bağ oluşturur.
    private val binding by viewBinding(ActivityMainBinding::inflate)

    // Aktivitenin oluşturulduğunda çağrılan metod.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ana iş parçacığı 3 saniye (3000 milisaniye) boyunca uyutulur.
        // Bu basit bir splash ekranı gösterme yöntemidir, ancak pratikte genellikle önerilmez.
        Thread.sleep(3000)

        // Splash ekranını yükler.
        installSplashScreen()

        // Aktivitenin içeriğini ayarlar.
        setContentView(binding.root)

        // NavHostFragment'ı alır, bu fragment navigasyon grafiği ve navigasyon mantığını içerir.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Alt gezinme çubuğunu NavHostFragment ile ayarlar,
        // böylece alt gezinme çubuğu navigasyon kontrollerini yönetebilir.
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navHostFragment.navController
        )
    }
}
