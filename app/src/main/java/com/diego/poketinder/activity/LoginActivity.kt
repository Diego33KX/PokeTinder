package com.diego.poketinder.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.diego.poketinder.R
import com.diego.poketinder.data.User
import com.diego.poketinder.databinding.ActivityLoginBinding
import com.diego.poketinder.util.SharedPreferenceUtil

class LoginActivity:BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceUtil= SharedPreferenceUtil().also { SharedPreferenceUtil
            it.setSharedPreference(this)
        }
    }
    fun AlertDialog(mensaje:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta!")
        builder.setMessage(mensaje)
            .setPositiveButton("Volver",
                DialogInterface.OnClickListener{dialog, id -> })
        builder.show()
    }
    fun startLogin(view: View){
        //Validate input
        if(binding.edtEmail.text.isEmpty() && binding.edtPassword.text.isEmpty()){
            //Se agrega el alert dialog
            AlertDialog("Debe completar los campos solicitados para iniciar")
        }else if(binding.edtEmail.text.isEmpty()){
            AlertDialog("Debe ingresar un correo")
        }else if(binding.edtPassword.text.isEmpty()){
            AlertDialog("Ingrese una contraseña")
        }else{
            val user: User? =sharedPreferenceUtil.getUser()

            if(user?.email.equals(binding.edtEmail.text.toString()) && user?.password.equals(binding.edtPassword.text.toString())){
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("user",user)
                startActivity(intent)
            }else{
                //Toast.makeText(this,"Error de usuario",Toast.LENGTH_SHORT).show()
                AlertDialog("Error de usuario")
            }
        }
    }
    fun registrarUsuario(view: View){
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}


