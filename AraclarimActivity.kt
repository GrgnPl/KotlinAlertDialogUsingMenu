package com.kacyakiyor.aracimkacyakti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.izleyerekkazan.izlekazanprojesi.API.ApiUtils
import com.kacyakiyor.aracimkacyakti.API.Araclar
import com.kacyakiyor.aracimkacyakti.API.AraclarCevap
import com.kacyakiyor.aracimkacyakti.Adapter.AraclarAdapter
import com.kacyakiyor.aracimkacyakti.databinding.ActivityAracEkleBinding
import com.kacyakiyor.aracimkacyakti.databinding.ActivityAraclarimBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AraclarimActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAraclarimBinding
    private lateinit var derslerListe:ArrayList<Araclar>
    private lateinit var adapter: AraclarAdapter
    val kdi = ApiUtils.getKisilerDaoInterface()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityAraclarimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rv.layoutManager = LinearLayoutManager(this)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val sp = this.getSharedPreferences("degerler", MODE_PRIVATE)
        val id = sp.getString("id","idyok")
        Log.e("id", id.toString())
        val drawarLayout = binding.drawarLayout
        val imgMenu = binding.imgMenu
        val navView = findViewById<NavigationView>(R.id.navDawar)
        navView.itemIconTintList = null
        imgMenu.setOnClickListener {
            drawarLayout.openDrawer(GravityCompat.START)
        }
        navView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.getItemId()) {
                    R.id.MenuActivity -> {
                        startActivity(Intent(applicationContext, MenuActivity::class.java))
                    }
                    R.id.AracEkleActivity->{
                        startActivity(Intent(applicationContext,AracEkleActivity::class.java))
                    }
                    R.id.HesabimActivity->{
                        startActivity(Intent(applicationContext,HesabimActivity::class.java))
                    }
                    R.id.ContactActivity->{
                        startActivity(Intent(applicationContext,ContactActivity::class.java))
                    }
                    R.id.MainActivity->{
                        val sp = getSharedPreferences("checkbox", MODE_PRIVATE)
                        val editor = sp.edit()
                        editor.putString("remember", "false")
                        editor.apply()
                        Toast.makeText(applicationContext, "Başarıyla Çıkış Yapıldı", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext,MainActivity::class.java))

                    }
                    R.id.FisEkleActivity->{
                        startActivity(Intent(applicationContext,FisEkleActivity::class.java))
                    }
                    R.id.FislerimActivity->{
                        startActivity(Intent(applicationContext,FislerimActivity::class.java))
                    }
                }
                return true
            }
        })
        kdi.araclarim(id.toString()).enqueue(object :Callback<AraclarCevap>{
            override fun onResponse(call: Call<AraclarCevap>, response: Response<AraclarCevap>) {
                val arac_liste = response.body()?.dersler
                adapter = AraclarAdapter(this@AraclarimActivity, arac_liste!!)
                binding.rv.adapter = adapter
            }

            override fun onFailure(call: Call<AraclarCevap>, t: Throwable) {

            }
        })
    }
}