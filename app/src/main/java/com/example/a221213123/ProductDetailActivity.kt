package com.example.a221213123

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productDescriptionTextView: TextView
    private lateinit var productImageView: ImageView
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        productNameTextView = findViewById(R.id.productNameDetail)
        productPriceTextView = findViewById(R.id.productPriceDetail)
        productDescriptionTextView = findViewById(R.id.productDescriptionDetail)
        productImageView = findViewById(R.id.productImageDetail)
        exitButton = findViewById(R.id.exitProductDetailButton)

        val productName = intent.getStringExtra("productName")
        val productPrice = intent.getStringExtra("productPrice")
        val productDescription = intent.getStringExtra("productDescription")
        val productImageUriString = intent.getStringExtra("productImageUri")


        productNameTextView.text = productName
        productPriceTextView.text = "$productPrice руб."
        productDescriptionTextView.text = productDescription

        if (!productImageUriString.isNullOrEmpty()) {
            val productImageUri = Uri.parse(productImageUriString)
            productImageView.setImageURI(productImageUri)
        }


        exitButton.setOnClickListener {
            finish()
        }
    }
}