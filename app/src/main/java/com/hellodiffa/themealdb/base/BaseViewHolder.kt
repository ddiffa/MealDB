package com.hellodiffa.themealdb.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in D>(private val B: ViewDataBinding) : RecyclerView.ViewHolder(B.root) {

    abstract fun onBind(item: D) : ViewDataBinding
}