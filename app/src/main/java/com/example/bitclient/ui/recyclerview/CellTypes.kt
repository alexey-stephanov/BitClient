package com.example.bitclient.ui.recyclerview

class CellTypes<T>(vararg types: Cell<T>) {

    private val cellTypes: ArrayList<Cell<T>> = ArrayList()

    init {
        types.forEach { cellTypes.add(it) }
    }

    fun of(item: T?): Cell<T> {
        for (cellType in cellTypes) {
            if (cellType.belongsTo(item)) return cellType
        }
        throw NoSuchElementException()
    }

    fun of(viewType: Int): Cell<T> {
        for (cellType in cellTypes) {
            if (cellType.type() == viewType) return cellType
        }
        throw NoSuchElementException()
    }

}