package com.faithdeveloper.resume.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.faithdeveloper.resume.models.Headers

open class BaseViewHolder(binding: View):RecyclerView.ViewHolder(binding) {
    val context: Context = itemView.context
    open fun bind(item: Headers) {

    }
}