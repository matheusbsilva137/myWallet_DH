package com.example.snackbar

import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent().extras
        setContentView(R.layout.activity_main)

        my_toolbar.title = "MyWallet"
        my_toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_backbt)

        setSupportActionBar(my_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        destacarBotao(btn_home)

        val frag_home = FragmentHome.newInstance()
        val frag_gastos = FragmentGastos.newInstance()
        val frag_entradas = FragmentEntradas.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_fragment, frag_home) //substitui o fragment do layout pelo fragment instanciado
            addToBackStack(null)
            commit()
        }

        btn_home.setOnClickListener {
            destacarBotao(btn_home)
            tirarDestaqueBotao(btn_entradas)
            tirarDestaqueBotao(btn_gastos)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_home) //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }

        btn_entradas.setOnClickListener {
            destacarBotao(btn_entradas)
            tirarDestaqueBotao(btn_home)
            tirarDestaqueBotao(btn_gastos)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_entradas) //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }

        btn_gastos.setOnClickListener {
            destacarBotao(btn_gastos)
            tirarDestaqueBotao(btn_entradas)
            tirarDestaqueBotao(btn_home)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_gastos) //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }

        my_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    fun tirarDestaqueBotao(b: Button){
        b.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorWhite)
        b.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
    }

    fun destacarBotao(b: Button){
        b.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorWine)
        b.setTextColor(ContextCompat.getColor(this, R.color.colorWine))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

}