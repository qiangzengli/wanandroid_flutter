package zeng.qiang.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import zeng.qiang.wanandroid.entity.Article
import zeng.qiang.wanandroid.pages.*
import zeng.qiang.wanandroid.screen.Screen
import zeng.qiang.wanandroid.ui.theme.WanandroidTheme

class MainActivity : ComponentActivity() {
    val items = listOf(Screen.Article, Screen.Wechat, Screen.Profile)

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanandroidTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        BottomNavigation {
                            items.forEach { screen ->

                                BottomNavigationItem(
                                    selected = currentDestination
                                        ?.hierarchy
                                        ?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            screen.icon,
                                            contentDescription = stringResource(id = screen.titleId)
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = stringResource(id = screen.titleId)
                                        )
                                    }
                                )
                            }
                        }

                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Article.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Article.route) { ArticlePage(navController) }
                        composable(Screen.Wechat.route) { WechatPage(navController) }
                        composable(Screen.Profile.route) { ProfilePage(navController) }

                    }


                }

            }
        }
    }
}

//@Composable
//fun BannerItem(item: Banner) {
//    Column {
//        Text(text = item.title)
//        Image()
//    }
//
//}

