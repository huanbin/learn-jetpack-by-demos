package com.example.jetpack_demos.fragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//@Serializable
@Parcelize
data class MyImgItem(val id:String, val path:String) : Parcelable
