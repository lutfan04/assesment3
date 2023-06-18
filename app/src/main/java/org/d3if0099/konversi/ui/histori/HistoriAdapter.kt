package org.d3if0099.konversi.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0099.konversi.R
import org.d3if0099.konversi.databinding.ItemHistoriBinding
import org.d3if0099.konversi.db.KonversiEntity
import org.d3if0099.konversi.model.hitungKonversi
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<KonversiEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<KonversiEntity>() {
                override fun areItemsTheSame(
                    oldData: KonversiEntity, newData: KonversiEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: KonversiEntity, newData: KonversiEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemHistoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: KonversiEntity) = with(binding) {
            val hasilKonversi = item.hitungKonversi()
            kategoriTextView.text = hasilKonversi.total.toString().substring(0, 1)
            val colorRes = when (hasilKonversi.total) {
                else -> R.color.hasil
            }
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            konversiTextView.text = root.context.getString(
                R.string.hasil_x,
                hasilKonversi.total.toString()
            )
        }
    }
}