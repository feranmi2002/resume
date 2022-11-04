package com.faithdeveloper.resume.models

data class Child(
    var type: String,
    var data: MutableList<Any>
){
    constructor():this("", mutableListOf())
}
