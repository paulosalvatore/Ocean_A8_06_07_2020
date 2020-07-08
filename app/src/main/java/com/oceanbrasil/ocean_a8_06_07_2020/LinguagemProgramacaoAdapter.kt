package com.oceanbrasil.ocean_a8_06_07_2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.linguagem_programacao_item.view.*

data class LinguagemProgramacao(
    val titulo: String,
    val ano: Int,
    val descricao: String
)

class LinguagemProgramacaoAdapter(val items: List<LinguagemProgramacao>) :
    RecyclerView.Adapter<LinguagemProgramacaoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: LinguagemProgramacao) = with(itemView) {
            tvTitulo.text = item.titulo
            tvAno.text = item.ano.toString()
            tvDescricao.text = item.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.linguagem_programacao_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }
}
