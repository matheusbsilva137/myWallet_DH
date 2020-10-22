package com.example.snackbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent().extras
        setContentView(R.layout.activity_main)

        val frag_home = FragmentHome.newInstance()
        val frag_gastos = FragmentGastos.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_fragment, frag_home) //substitui o fragment do layout pelo fragment instanciado
            addToBackStack(null)
            commit()
        }

        btn_home.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_home) //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }

        btn_gastos.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_gastos) //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }
    }

}