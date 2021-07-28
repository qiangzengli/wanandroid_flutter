package zeng.qiang.wanandroid.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import zeng.qiang.wanandroid.R

sealed class Screen(val route:String,@StringRes val titleId:Int,val icon:ImageVector){
    object Article:Screen("article", R.string.article, Icons.Filled.Article)
    object Wechat:Screen("wechat",R.string.wechat,Icons.Filled.AccessTimeFilled)
    object Profile:Screen("profile",R.string.profile,Icons.Filled.Person)
}
