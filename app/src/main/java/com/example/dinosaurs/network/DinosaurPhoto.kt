package com.example.dinosaurs.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DinosaurPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
