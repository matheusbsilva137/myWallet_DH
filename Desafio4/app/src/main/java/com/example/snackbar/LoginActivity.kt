package com.example.snackbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.snackbar.domain.Usuario
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login_body.*
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnImg_Google.setOnClickListener {
            Toast.makeText(this, "Google+", Toast.LENGTH_SHORT).show()
        }

        btnImg_Face.setOnClickListener {
            Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show()
        }

        btnImg_Insta.setOnClickListener {
            Toast.makeText(this, "Instagram", Toast.LENGTH_SHORT).show()
        }

        btnLogin.setOnClickListener {
            if (edPassword.text != null && edPassword.text.toString() != "123"){
                Snackbar.make(act_login, "Senha incorreta", Snackbar.LENGTH_LONG)
                    .setAction("Ok", View.OnClickListener {}).show()
            }else{
                var intent = Intent(this, MainActivity::class.java)

                intent.putExtra("user", Usuario(0,
                edUserName.text.toString(),
                edPassword.text.toString()).username)
                startActivity(intent)

                Toast.makeText(this, "Usuário '${edUserName.text.toString()}' realizou Login", Toast.LENGTH_SHORT).show()
            }
        }

        txv_cadastro.setOnClickListener {
            var intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("username", edUserName.text.toString())
            intent.putExtra("password", edPassword.text.toString())

            startActivity(intent)

            Toast.makeText(this, "Cadastro de Usuário", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {

        super.onStop()
    }
}