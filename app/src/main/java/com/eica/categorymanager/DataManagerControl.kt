package com.eica.categorymanager

import android.content.Context
import com.eica.categorymanager.Parsers.CategoryParser
import com.eica.categorymanager.Parsers.ProductParser
import com.eica.categorymanager.model.Category
import com.eica.categorymanager.model.Product
import org.jetbrains.anko.db.*


class DataManagerControl(val context:Context) {
    companion object{
        val PRODUCTO_TBL = "Products"
        val CATEGORY_TBL = "Category"
    }
    fun addProduct(product: Product){
       context.database.use {
           this.insert(PRODUCTO_TBL,
                   "name" to product.name,
                            "price" to product.price,
                            "categoryId" to product.categoryId

           )
       }
    }
    fun editProduct(product: Product){
        context.database.use {
            this.update(PRODUCTO_TBL, "name" to product.name,
                    "price" to product.price,
                    "categoryId" to product.categoryId).whereSimple("id  = ?", product.id.toString())
                    .exec()
        }
    }

    fun getAllProducts():List<Product>{
        return  context.database.use {
           select("$PRODUCTO_TBL p inner join $CATEGORY_TBL c on c.id = p.categoryId","p.id,p.name,p.price,p.categoryId,c.name" ).distinct().parseList(ProductParser())
        }

    }
    fun getProduct(id:Int):Product{
        return context.database.use { select(PRODUCTO_TBL).whereSimple("id = ?", id.toString()).exec { parseSingle(ProductParser()) } }
    }
    fun deleteProduct(id:Int){
       context.database.use {
           val numRowsDeleted = delete(PRODUCTO_TBL, "id = {productID}", "productID" to id)
       }
    }

    fun addCategory(category: Category){
        context.database.use {
            this.insert(CATEGORY_TBL,
                    "name" to category.name
            )
        }
    }
    fun editCategory(category: Category){
        context.database.use {
            this.update(CATEGORY_TBL, "name" to category.name).whereSimple("id  = ?", category.id.toString())
                    .exec()
        }
    }
    fun getAllCategory():List<Category>{
        return context.database.use {
            select(CATEGORY_TBL).exec { parseList(CategoryParser()) }
        }
    }
    fun getCategory(id:Int):Category{
        return context.database.use { select(CATEGORY_TBL).whereSimple("id = ?", id.toString()).exec { parseSingle(CategoryParser()) } }
    }
    fun deleteCategory(id:Int){
        context.database.use {
            delete(CATEGORY_TBL, "id = {categoryID}", "categoryID" to id)
        }
    }

}