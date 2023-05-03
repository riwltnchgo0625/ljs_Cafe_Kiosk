import androidx.annotation.DrawableRes

data class Menu(
    //메뉴 이름, 가격, 이미지
    val name: String, val price: Int,
    @DrawableRes val imageResource: Int
) {
    // ...
}
