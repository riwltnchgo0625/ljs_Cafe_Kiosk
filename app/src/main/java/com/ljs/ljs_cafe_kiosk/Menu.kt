import androidx.annotation.DrawableRes

data class Menu(
    //메뉴 이름, 가격, 이미지
    var name: String,
    var price: Int,
    @DrawableRes
    val imageResource: Int
) {

}
