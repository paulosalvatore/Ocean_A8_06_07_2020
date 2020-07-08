package com.oceanbrasil.ocean_a8_06_07_2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        /*
        RecyclerView:
        - LayoutManager: Configuração visual da lista
        - Adapter: Define como exibir os dados de uma lista
        */

        // Vertical simplificado
//        rvLinguagemProgramacao.layoutManager =
//            LinearLayoutManager(
//                this
//            )

        // Vertical
//        rvLinguagemProgramacao.layoutManager =
//            LinearLayoutManager(
//                this,
//                LinearLayoutManager.VERTICAL,
//                false
//            )

        // Horizontal
//        rvLinguagemProgramacao.layoutManager =
//            LinearLayoutManager(
//                this,
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )

        // Staggered na Vertical
        rvLinguagemProgramacao.layoutManager =
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )

        val kotlin = LinguagemProgramacao(
            "Kotlin",
            2011,
            "Kotlin é uma Linguagem de programação multiplataforma, orientada a objetos e funcional, concisa e estaticamente tipada (variáveis com tipos específicos), desenvolvida pela JetBrains em 2016,[4] que compila para a Máquina virtual Java e que também pode ser traduzida para a linguagem JavaScript e compilada para código nativo (via LLVM).[5] [6] Foi anunciada em 2017 pela Google como a linguagem oficial do sistema Android."
        )

        val java = LinguagemProgramacao(
            "Java",
            1995,
            "Java é uma linguagem de programação orientada a objetos desenvolvida na década de 90 por uma equipe de programadores chefiada por James Gosling, na empresa Sun Microsystems."
        )

        val teste = LinguagemProgramacao(
            "Teste",
            2020,
            "Descrição"
        )

        val items = listOf(kotlin, teste, java, kotlin, java, kotlin, java, kotlin, java)

        rvLinguagemProgramacao.adapter = LinguagemProgramacaoAdapter(items)
    }
}