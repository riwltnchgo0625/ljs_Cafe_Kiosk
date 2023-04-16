import androidx.annotation.DrawableRes

data class Menu(val name: String, val price: Int, @DrawableRes val imageResource: Int) {
    // ...
}
