import androidx.annotation.DrawableRes

data class Menu(
    //메뉴 이름, 가격, 이미지
    var ljs_name: String,
    var ljs_price: Int,
    @DrawableRes
    val ljs_imageResource: Int
) {

}
