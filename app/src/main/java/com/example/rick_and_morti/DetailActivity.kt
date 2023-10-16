package com.example.rick_and_morti

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rick_and_morti.databinding.ActivityDetailBinding
import com.example.rick_and_morti.models.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        //  setContentView(R.layout.activity_detail)


        val view = binding.root

        setContentView(view)


        var authJson: String = intent.getStringExtra("character").toString()
        val gson = Gson()
        var character = gson.fromJson(authJson, Results::class.java)

//        textViewReceiptId.setText(auth.receiptId)
//        textViewRRN.setText(auth.rrn)
//        textViewStatusCode.setText(auth.statusCode)
//        textViewStatusDescription.setText(auth.statusDescription)

        binding.txtId.text = "ID: "+ character.id.toString()
        binding.txtName.text = "Nombre: "+character.name
        binding.txtStatus.text = "Estatus: "+character.status
        binding.txtSpecies.text = "Especie: "+character.species
        binding.txtType.text = "Tipo: "+character.type
        binding.txtGender.text = "Genero: "+character.gender

        Picasso.with(this).load(character.image).into(binding.imageView)
        val actionbar = supportActionBar
        actionbar!!.title = character.name
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }


}