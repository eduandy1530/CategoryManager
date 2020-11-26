package com.eica.categorymanager.Parsers

import com.eica.categorymanager.model.Category
import org.jetbrains.anko.db.RowParser

class CategoryParser: RowParser<Category> {
    override fun parseRow(columns: Array<Any?>): Category {
        return Category(columns[0]?.toString()?.toInt() ?: 0, columns[1]?.toString()!!)
    }
}