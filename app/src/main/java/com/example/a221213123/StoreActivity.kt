package com.example.a221213123

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StoreActivity : AppCompatActivity() {

    private lateinit var productName: EditText
    private lateinit var productPrice: EditText
    private lateinit var productDescription: EditText
    private lateinit var productListView: ListView
    private lateinit var addProductButton: Button
    private lateinit var selectImageButton: Button

    private var selectedImageUri: Uri? = null
    private val productList = mutableListOf<Product>()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        productName = findViewById(R.id.productName)
        productPrice = findViewById(R.id.productPrice)
        productDescription = findViewById(R.id.productDescription)
        productListView = findViewById(R.id.productList)
        addProductButton = findViewById(R.id.addProductButton)
        selectImageButton = findViewById(R.id.selectImageButton)

        productAdapter = ProductAdapter(this, productList)
        productListView.adapter = productAdapter

        selectImageButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        addProductButton.setOnClickListener {
            val name = productName.text.toString()
            val price = productPrice.text.toString()
            val description = productDescription.text.toString()

            if (name.isEmpty() || price.isEmpty() ||description.isEmpty() || selectedImageUri == null) {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
        } else {

            val product = Product(name, price, description, selectedImageUri)


            productList.add(product)


            productAdapter.notifyDataSetChanged()


            productName.text.clear()
            productPrice.text.clear()
            productDescription.text.clear()


            selectedImageUri = null
            Toast.makeText(this, "Товар добавлен", Toast.LENGTH_SHORT).show()
        }
        }


        productListView.setOnItemClickListener { _, _, position, _ ->
            val selectedProduct = productList[position]


            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("productName", selectedProduct.name)
                putExtra("productPrice", selectedProduct.price)
                putExtra("productDescription", selectedProduct.description)
                putExtra("productImageUri", selectedProduct.imageUri?.toString())
            }
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            selectedImageUri = data?.data
            Toast.makeText(this, "Изображение выбрано", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}