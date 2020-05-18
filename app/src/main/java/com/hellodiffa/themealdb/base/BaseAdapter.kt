package com.hellodiffa.themealdb.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, VH : BaseViewHolder<D>, B : ViewDataBinding> :
    RecyclerView.Adapter<VH>() {

    var dataSource: List<D> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    protected lateinit var mViewDataBinding: B

    override fun getItemCount(): Int = dataSource.size

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        mViewDataBinding = DataBindingUtil.inflate<B>(inflater, getLayoutResourceId(), parent, false)
        return instantiateViewHolder(mViewDataBinding)
    }

    abstract fun instantiateViewHolder(binding: B): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    protected fun getItem(position: Int) = dataSource[position]
}