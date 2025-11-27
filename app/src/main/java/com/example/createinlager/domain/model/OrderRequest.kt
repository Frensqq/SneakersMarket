package com.example.createinlager.domain.model

data class OrderRequest(
    val idusers: String,
    val id_snakers_title: String,
    val email: String,
    val phone: String,
    val address: String,
    val card: String,
    val cost: String,
    val costOrder: String
)
