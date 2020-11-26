package com.eica.categorymanager

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eica.categorymanager.model.Category
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class CategoryAdapter(val activity:Activity, val context:Context,val list:List<Category>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    val database = DataManagerControl(context)

    class ViewHolder(val itemview: View):RecyclerView.ViewHolder(itemview){
        val txtCategoryName = itemview.findViewById<TextView>(R.id.txtCategoria)
        val btnBorrar = itemview.findViewById<Button>(R.id.btnBorrar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.categories_layout, parent, false)
        return CategoryAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = list[position]

        holder.txtCategoryName.text = category.name
        holder.btnBorrar.setOnClickListener {
            context.alert("Estas seguro que quieres borrar esta categoria") { yesButton {
                database.deleteCategory(category.id!!)
                activity.onBackPressed()
            }
            noButton {  }}.show()
        }
    }
}