package com.eica.categorymanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eica.categorymanager.model.Product
import com.google.gson.Gson
import org.jetbrains.anko.layoutInflater

class ProductsAdapter(val context: Fragment, val list: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class  ViewHolder(item:View):RecyclerView.ViewHolder(item){
        val txtNombre = item.findViewById<TextView>(R.id.txtNombre)
        val txtPrecio = item.findViewById<TextView>(R.id.txtPrecio)
        val txtCategory = item.findViewById<TextView>(R.id.txtCategoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.products_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]

        holder.txtNombre.text = product.name
        holder.txtPrecio.text = product.price.toString()
        holder.txtCategory.text = product.category?.name
        holder.itemView.setOnClickListener {
            var bundle = bundleOf("producto" to Gson().toJson(product))
            context.findNavController().navigate(R.id.action_productListFragment_to_addProductFragment,bundle )
        }
    }


}