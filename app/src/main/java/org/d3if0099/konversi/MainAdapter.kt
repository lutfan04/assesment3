package org.d3if0099.konversi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0099.konversi.databinding.ItemKonversiBinding
import org.d3if0099.konversi.model.Konversi
import org.d3if0099.konversi.network.KonversiApi


class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<Konversi>()
    fun updateData(newData: List<Konversi>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemKonversiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(konversi: Konversi) = with(binding) {
            namaTextView.text = konversi.nama
            penemuTextView.text = konversi.penemu
            Glide.with(imageView.context)
                .load(KonversiApi.getKonversiUrl(konversi.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)


            root.setOnClickListener {
                val message = root.context.getString(R.string.message, konversi.nama)
                Toast.makeText(root.context, konversi.nama, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemKonversiBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}