package com.ljs.ljs_cafe_kiosk

data class OrderHistoryItem
    (
    val ljs_menuName: String,
    var ljs_menuPrice: Int,
    var ljs_quantity: Int = 1,
    var ljs_totalPrice: Int = ljs_menuPrice
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderHistoryItem

        if (ljs_menuName != other.ljs_menuName) return false
        return true
    }

    override fun hashCode(): Int {
        return ljs_menuName.hashCode()
    }
}