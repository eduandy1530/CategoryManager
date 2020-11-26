package com.eica.categorymanager.Parsers

import com.eica.categorymanager.model.Category
import com.eica.categorymanager.model.Product
import org.jetbrains.anko.db.RowParser

class ProductParser : RowParser<Product> {
    override fun parseRow(columns: Array<Any?>): Product {
        return Product(
                columns[0]?.toString()?.toInt() ?: 0,
                columns[1] as String,
                columns[2]?.toString()?.toInt() ?: 0,
                columns[3]?.toString()?.toInt() ?: 0,
                Category(name = columns[4].toString())
        )
    }
}