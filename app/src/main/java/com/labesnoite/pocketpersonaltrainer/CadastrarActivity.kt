package com.labesnoite.pocketpersonaltrainer

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.Profile
import com.labesnoite.pocketpersonaltrainer.entidade.Usuario
import kotlinx.android.synthetic.main.activity_cadastrar.*
import java.util.*


class CadastrarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        if (intent?.extras != null) {
            val profile = intent.extras.get("PerfilFb") as Profile
            fotoPerfil.setImageURI(profile.getProfilePictureUri(fotoPerfil.width, fotoPerfil.height))
            edtNome.text.insert(0, profile.name)
        }

        btnCadastrar.setOnClickListener {
            if (salvarDados()) {
                AlertDialog.Builder(this@CadastrarActivity)
                        .setMessage("Cadastrado feito com sucesso!")
                        .setPositiveButton(android.R.string.ok, null)
                        .show()
                val intent = Intent(this, MenuPrincipalActivity::class.java)
                intent.putExtra("Usuario", user)
                startActivity(intent)
            }
        }

        //fabtnTirarFoto.setOnClickListener {
            //val intent = Intent(this, CameraActivity::class.java)
            //startActivity(intent)

        //}
    }

    private lateinit var user: Usuario
    private fun salvarDados(): Boolean {
        var isSaved = false
        if (edtNome.text.isBlank() || edtSenhaC.text.isBlank() ||
                edtEmailCadastro.text.isBlank() || edtPeso.text.isBlank() ||
                edtAltura.text.isBlank() || txtNomeAcademia.text.isBlank()) {
            AlertDialog.Builder(this@CadastrarActivity)
                    .setMessage("Erro! Há campos em branco!")
                    .setNegativeButton(android.R.string.ok, null)
                    .show()
            edtNome.setFocusable(true)
        } else {
            validarSenha()
            user = Usuario(edtNome.text.toString(), edtEmailCadastro.text.toString(), edtSenhaC.text.toString(), edtPeso.text.toString().toDouble(),
                    edtAltura.text.toString().toDouble(), edtTelefone.text.toString(), 0, 50.00, Date())

            //codigo pra consumir a API SPRINGBOOT e salvar no Banco de dados
            //usar Retrofit2

            isSaved = true
        }
        return isSaved
    }

    private fun validarSenha(): Boolean {
        var isValidSenha = false
        txtView_validarPwd.text = ""
        while (edtConfSenha.equals(edtSenhaC.text)) {
            txtView_validarPwd.setText(R.string.lbl_passwdNotValid)
            if (edtConfSenha.equals(edtSenhaC.text)) {
                txtView_validarPwd.setText(R.string.lbl_passwdNotValid)
                isValidSenha = true
                break
            }
            isValidSenha = false
        }
        return isValidSenha
    }

    /*fun setPic(mCurrentPhotoPath :String) {
       // Get the dimensions of the View
       val targetW: Int = fotoPerfil.width
       val targetH: Int = fotoPerfil.height

       val bmOptions = BitmapFactory.Options().apply {
           // Get the dimensions of the bitmap
           inJustDecodeBounds = true
           BitmapFactory.decodeFile(mCurrentPhotoPath, this)
           val photoW: Int = outWidth
           val photoH: Int = outHeight

           // Determine how much to scale down the image
           val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

           // Decode the image file into a Bitmap sized to fill the View
           inJustDecodeBounds = false
           inSampleSize = scaleFactor
           inPurgeable = true
       }
       BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)?.also { bitmap ->
           fotoPerfil.setImageBitmap(bitmap)
       }
   }*/
}

