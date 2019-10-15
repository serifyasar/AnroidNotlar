package com.serifyasar.kuran_ikelimeler.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serifyasar.kuran_ikelimeler.Fragment.KelimeFragment
import com.serifyasar.kuran_ikelimeler.Fragment.KuranFragment
import com.serifyasar.kuran_ikelimeler.Fragment.ProfileFragment
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_sure.*

class SureActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sure)
        main_toolbar.title="Kelime Çalışması"
        val fragment = KelimeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
            .commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);





    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_kelime -> {
                val fragment = KelimeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                main_toolbar.title="Kelime Çalışması"
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_kuran -> {
                val fragment = KuranFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                main_toolbar.title="Ayet Çalışması"
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profil -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                main_toolbar.title="Profil"
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }


    override fun onResume() {
        super.onResume()
        main_toolbar.title="Kelime Çalışması"
        val fragment = KelimeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }


}
