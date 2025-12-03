package com.example.drinkmenu

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private val orangeJuice ="Orange Juice"
    private val mangoJuice ="Mango Juice"
    private val appleJuice ="Apple Juice"
    private val kiwiJuice ="Kiwi Juice"

    val juicePrice = mapOf(
        orangeJuice to 20,
        mangoJuice to 30,
        kiwiJuice to 45,
        appleJuice to 25
    )

    lateinit var dropDownMenu :AutoCompleteTextView
    lateinit var price :TextView
    lateinit var orderBtn :Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        menuAdapter()
        dropDownMenu.setOnItemClickListener { parent, view, position, id ->
            selectedItem()
        }
        orderSubmitted()
    }
    private fun initializeViews(){
        dropDownMenu = findViewById(R.id.menuComplete)
        price = findViewById(R.id.priceTV)
        orderBtn = findViewById(R.id.submittedBtn)
    }
    private fun menuAdapter(){
        val listOfJuice = listOf(orangeJuice,mangoJuice,appleJuice,kiwiJuice)
        val menuAdapter =ArrayAdapter(this,R.layout.drop_down_menu,listOfJuice)
        dropDownMenu.setAdapter(menuAdapter)
    }
    private fun selectedItem(){
        val selectedDrink = dropDownMenu.text.toString()
        val selectedPriceDrink = juicePrice[selectedDrink]
        price.setText(selectedPriceDrink.toString())
    }
    private fun orderSubmitted(){
        orderBtn.setOnClickListener {
            val orderIntent = Intent(Intent.ACTION_SENDTO)
            orderIntent.data= Uri.parse("mailto: noranmoahmed90@gmail.com?subject=Drink Menu&body=I want to order ${dropDownMenu.text.toString()}" +
                    "!")
            orderIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("nm272319@gmail.com"))
            try {
                startActivity(orderIntent)
            }catch (e:Exception){
                Toast.makeText(this, "No email app found", Toast.LENGTH_LONG).show()
            }
        }
    }
}