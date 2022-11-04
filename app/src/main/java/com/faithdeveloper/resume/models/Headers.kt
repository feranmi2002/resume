package com.faithdeveloper.resume.models

import com.faithdeveloper.resume.Utils
import com.faithdeveloper.resume.Utils.PARENT

data class Headers(
    val title:String,
    var type:Int = Utils.PARENT,
    var child: Child,
    var isExpanded:Boolean = false

){
    constructor():this("", PARENT, Child(), false)
}
