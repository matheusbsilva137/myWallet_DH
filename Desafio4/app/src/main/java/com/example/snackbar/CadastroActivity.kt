package com.example.snackbar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.snackbar.domain.Usuario
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.cadastro_body.*
import kotlinx.android.synthetic.main.toolbar.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        my_toolbar.title = "CADASTRAR"
        my_toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_backbt)

        setSupportActionBar(my_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        edUserName_cad.setText(intent.extras?.get("username").toString(), TextView.BufferType.EDITABLE)
        edPassword_cad.setText(intent.extras?.get("password").toString(), TextView.BufferType.EDITABLE)

        var pass = edPassword_cad.text
        var username = edUserName_cad.text
        var email = edEmail_cad.text
        var passRepet = edPasswordRepeat_cad.text

        btn_cadastrar.setOnClickListener {
            if(username == null || pass == null || email == null || passRepet == null){
                Toast.makeText(this, "Digite novamente as informações!", Toast.LENGTH_SHORT).show()
            }else if (pass.isEmpty() || username.isEmpty() || email.isEmpty() || passRepet.isEmpty()){
                Snackbar.make(act_cadastro, "Complete os campos destacados", Snackbar.LENGTH_LONG)
                    .setAction("Ok", View.OnClickListener {}).show()

                if (pass.isEmpty()) edPassword_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorRed)
                else edPassword_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorAccent)

                if (username.isEmpty()) edUserName_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorRed)
                else edUserName_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorAccent)

                if (passRepet.isEmpty()) edPasswordRepeat_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorRed)
                else edPasswordRepeat_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorAccent)

                if (email.isEmpty()) edEmail_cad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorRed)
                else edEmail_cad.backgroundTintList  = ContextCompat.getColorStateList(this, R.color.colorAccent)
            } else if (edPassword_cad.text.toString() != edPasswordRepeat_cad.text.toString()) {
                edPasswordRepeat_cad.text?.clear()
                Snackbar.make(act_cadastro, "Repita a senha corretamente", Snackbar.LENGTH_LONG)
                    .setAction("Ok", View.OnClickListener {}).show()
            }else{
                Toast.makeText(this, "Usuário '${edUserName_cad.text.toString()}' realizou Login", Toast.LENGTH_SHORT).show()

                var intent = Intent(this, MainActivity::class.java)

                intent.putExtra("user", Usuario(0,
                    edUserName_cad.text.toString(),
                    edPassword_cad.text.toString()).username)
                startActivity(intent)
            }
        }

        my_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}