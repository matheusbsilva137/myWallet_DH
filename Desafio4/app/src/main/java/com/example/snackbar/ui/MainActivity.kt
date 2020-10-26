package com.example.snackbar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.snackbar.R
import com.example.snackbar.`interface`.ContractMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), ContractMainActivity {

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
        replace(R.id.fl_fragment, frag_home, "home") //substitui o fragment do layout pelo fragment instanciado
            addToBackStack(null)
            commit()
        }

        btn_home.setOnClickListener {
            destacarBotao(btn_home)
            tirarDestaqueBotao(btn_entradas)
            tirarDestaqueBotao(btn_gastos)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_home, "home") //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }

        btn_entradas.setOnClickListener {
            destacarBotao(btn_entradas)
            tirarDestaqueBotao(btn_home)
            tirarDestaqueBotao(btn_gastos)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_entradas, "entradas") //substitui o fragment do layout pelo fragment instanciado
                addToBackStack(null)
                commit()
            }
        }

        btn_gastos.setOnClickListener {
            destacarBotao(btn_gastos)
            tirarDestaqueBotao(btn_entradas)
            tirarDestaqueBotao(btn_home)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, frag_gastos, "gastos") //substitui o fragment do layout pelo fragment instanciado
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

    override fun onBackPressed() {
        var fragAtual = this.supportFragmentManager.findFragmentByTag("home")
        if (fragAtual != null && fragAtual.isVisible()) tirarDestaqueBotao(btn_home)
        else{
            fragAtual = this.supportFragmentManager.findFragmentByTag("entradas")
            if (fragAtual != null && fragAtual.isVisible()) tirarDestaqueBotao(btn_entradas)
            else tirarDestaqueBotao(btn_gastos)
        }

        super.onBackPressed()

        var novoFrag = this.supportFragmentManager.findFragmentByTag("home")
        if (novoFrag != null && novoFrag.isVisible()) destacarBotao(btn_home)
        else{
            novoFrag = this.supportFragmentManager.findFragmentByTag("entradas")
            if (novoFrag != null && novoFrag.isVisible()) destacarBotao(btn_entradas)
            else{
                novoFrag = this.supportFragmentManager.findFragmentByTag("gastos")
                if (novoFrag != null && novoFrag.isVisible()) destacarBotao(btn_gastos)
                else finish()
            }
        }
    }

    override fun callFragDetailGastos() {
        val fragDetailGastos = DetailGastosFragment.newInstance("TEXTO")

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_details, fragDetailGastos)
            addToBackStack(null)
            commit()
        }
    }

}