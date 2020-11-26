package com.eica.categorymanager.model

class Category (val id: Int? = null, var name: String) {
    override fun toString(): String {
        return this.name
    }
}