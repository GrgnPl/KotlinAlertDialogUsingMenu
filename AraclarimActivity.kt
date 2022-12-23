

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
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
