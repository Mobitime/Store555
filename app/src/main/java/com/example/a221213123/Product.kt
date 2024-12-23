package com.example.a221213123

import android.net.Uri

data class Product (
    val name: String,
    val price: String,
    val description: String,
    val imageUri: Uri?
)