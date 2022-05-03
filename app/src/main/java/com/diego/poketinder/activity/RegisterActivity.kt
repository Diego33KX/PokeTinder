package com.diego.poketinder.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.diego.poketinder.R
import com.diego.poketinder.data.User
import com.diego.poketinder.databinding.ActivityRegisterBinding
import com.diego.poketinder.util.SharedPreferenceUtil

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(this)
        }
    }
    //SE IMPLEMENTA UNA FUNCION QUE MOSTRARÁ UN ALERTDIALOG DEPENDIENDO DE LA VALIDACIÓN
    fun AlertDialog(mensaje:String){
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Alerta!")
        builder.setMessage(mensaje)
            .setPositiveButton("Volver",
                DialogInterface.OnClickListener{ dialog, id -> })
        builder.show()
    }

    fun registerUser(view: View){
        //SE REALIZAN LAS VALIDACIONES
        if(binding.edtEmail.text.isBlank() && binding.edtUserName.text.isBlank() && binding.edtPassword.text.isBlank() &&
            binding.edtPassword2.text.isBlank()) {
            AlertDialog("Necesita completar todos los campos solicitados para registrarse")
        }else if(binding.edtUserName.text.isBlank()){
            AlertDialog("Debe completar el campo Username")
        }else if(binding.edtEmail.text.isBlank()){
            AlertDialog("Debe introducir su correo")
        }else if(binding.edtPassword.text.isBlank()){
            AlertDialog("Introduzca una constraseña")
        }else if (binding.edtPassword2.text.isBlank()){
            AlertDialog("Vuelva a introducir su contraseña para validar")
        }else{
            //TANTO EL CAMPO DONDE SE COLOCAR LA CONTRASEÑA Y DONDE SE VUELVE A INGRESAR PARA CONFIRMAR, DEBEN SER LOS MISMOS
            if(binding.edtPassword.text.toString().equals(binding.edtPassword2.text.toString())){
                val user = User(
                    "1",
                    binding.edtUserName.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString())
                sharedPreferenceUtil.saveFacebookUser(user)
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }else{
                AlertDialog("Las contraseñas no coinciden")
            }
        }

    }
    fun loginUser(view: View){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }
}