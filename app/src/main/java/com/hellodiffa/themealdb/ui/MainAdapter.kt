package com.hellodiffa.themealdb.ui

import androidx.core.view.ViewCompat
import com.hellodiffa.themealdb.R
import com.hellodiffa.themealdb.base.BaseAdapter
import com.hellodiffa.themealdb.base.BaseViewHolder
import com.hellodiffa.themealdb.databinding.ItemMealBinding
import com.hellodiffa.themealdb.model.CategoriesItem
import com.hellodiffa.themealdb.ui.MainAdapter.Holder

class MainAdapter : BaseAdapter<CategoriesItem, Holder, ItemMealBinding>() {

    override fun getLayoutResourceId(): Int = R.layout.item_meal

    override fun instantiateViewHolder(binding: ItemMealBinding): Holder =
        Holder(mViewDataBinding)

    inner class Holder(private val binding: ItemMealBinding) :
        BaseViewHolder<CategoriesItem>(binding) {

        override fun onBind(item: CategoriesItem) {
            ViewCompat.setTransitionName(binding.itemContainer, item.strCategory)
            binding.meal = item
            binding.executePendingBindings()
        }

    }

}