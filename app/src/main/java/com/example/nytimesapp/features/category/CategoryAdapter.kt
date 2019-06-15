package com.example.nytimesapp.features.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesapp.R
import com.example.nytimesapp.model.CategoryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_category.view.*

class CategoryAdapter(private val categoryList : List<CategoryModel>, private val listener : Listener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    interface Listener {
        fun onCategoryClick(categoryModel : CategoryModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(categoryList[position], listener, position)
    }

    override fun getItemCount(): Int = categoryList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(categoryModel: CategoryModel, listener: Listener, position: Int) {

            itemView.setOnClickListener{ listener.onCategoryClick(categoryModel) }
            itemView.category_textview.text = categoryModel.category
            Picasso.get().load(categoryModel.image).into(itemView.category_image)
        }
    }
}