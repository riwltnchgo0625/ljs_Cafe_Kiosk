package com.ljs.ljs_cafe_kiosk

data class OrderHistoryItem
    (
    val menuName: String,
    var menuPrice: Int,
    var quantity: Int = 1,
    var totalPrice: Int = menuPrice
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderHistoryItem

        if (menuName != other.menuName) return false

        return true
    }

    override fun hashCode(): Int {
        return menuName.hashCode()
    }
}