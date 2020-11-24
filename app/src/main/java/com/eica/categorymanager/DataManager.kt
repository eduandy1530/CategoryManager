package com.eica.categorymanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.eica.categorymanager.model.Category
import com.eica.categorymanager.model.Product

class DataManager(var context: Context) {
    private val db: SQLiteDatabase = context.openOrCreateDatabase("ProductManager", Context.MODE_PRIVATE, null);
    init {
        val categoryCreateQuery = "CREATE TABLE IF NOT EXISTS `Category` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT NOT NULL)"
        val productCategoryQuery = "CREATE TABLE IF NOT EXISTS `Product` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT NOT NULL, `price` INTEGER NOT NULL, `categoryId` INTEGER NOT NULL)"
        db.execSQL(categoryCreateQuery)
        db.execSQL(productCategoryQuery)
    }

    fun add(category: Category) {
        val query = "INSERT INTO Category (name) VALUES ('${category.name}')"
        db.execSQL(query)
    }

    fun add(product: Product) {
        val query = "INSERT INTO Product (name, price, categoryId) VALUES ('${product.name}', '${product.price}', '${product.category.id}')"
        db.execSQL(query)
    }

    fun allCategories() : List<Category> {
        val categories = mutableListOf<Category>()
        val cursor = db.rawQuery("SELECT * FROM Category", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))

                categories.add(Category(id, name))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categories
    }
}