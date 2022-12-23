

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class AraclarAdapter(private val mContext:Context, private var kisilerListe:List<Araclar>) : RecyclerView.Adapter<AraclarAdapter.CardTasarimTutucu>() {
    private lateinit var builder: androidx.appcompat.app.AlertDialog.Builder

    inner class CardTasarimTutucu(tasarim:View) : RecyclerView.ViewHolder(tasarim){
        var textViewKisiBilgi:TextView
        var MoreButton:ImageView
        init {
            textViewKisiBilgi = tasarim.findViewById(R.id.AracBilgi)
            MoreButton = tasarim.findViewById(R.id.morebutton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarimi,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return kisilerListe.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val arac = kisilerListe.get(position)
        holder.textViewKisiBilgi.text = "${arac.arac_adi}  ${arac.model_tipi} ${arac.yakit_tipi}"
        holder.MoreButton.setOnClickListener{
            val popup = PopupMenu(mContext,holder.MoreButton)
            popup.menuInflater.inflate(R.menu.popup_menu,popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_detay->{
                        builder = AlertDialog.Builder(mContext)
                        builder.setTitle("Araç Detay")
                            .setMessage("Araç Adı  : ${arac.arac_adi} \n" +
                                        "Araç Modeli : ${arac.model_tipi} \n" +
                                        "Yakıt Tipi  : ${arac.yakit_tipi} \n" +
                                        "Araç Kimliği : ${arac.arac_ozel_id}")
                            .setNegativeButton("Kapat") { dialogInterface, it ->
                                dialogInterface.cancel()
                            }.show()
                        true
                    }
                    .R.id.action_sil->{
                        true
                    }
                    else-> false
                }
            }
        }
    }
}
