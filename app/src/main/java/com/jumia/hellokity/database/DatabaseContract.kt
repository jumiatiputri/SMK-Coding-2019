package com.jumia.hellokity.database

data class DatabaseContract(
    val id:Long?=null,
    val title:String?=null,
    val posterPath:String?=null,
    val rating:Double?=null,
    val description:String?=null
)