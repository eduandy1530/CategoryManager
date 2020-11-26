package com.eica.categorymanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.eica.categorymanager.model.Category
import com.eica.categorymanager.model.Product
import org.jetbrains.anko.db.*

class DataManager private constructor(ctx: Context): ManagedSQLiteOpenHelper(ctx, "categorymanagerdb", null, 3) {
    init {
        instance = this
    }
    companion object {
        private var instance: DataManager? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: DataManager(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("Products",
        true,
                "id" to INTEGER  + PRIMARY_KEY + AUTOINCREMENT ,
        "name" to TEXT,
                    "price" to INTEGER,
        "categoryId" to INTEGER)

        db?.createTable("Category",
        true,
        "id" to INTEGER + PRIMARY_KEY  + AUTOINCREMENT ,
        "name" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable("Products")
        db?.dropTable("Category")
    }
}

// Access property for Context
val Context.database: DataManager
    get() = DataManager.getInstance(this)