package zeng.qiang.wanandroid.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.launch
import zeng.qiang.wanandroid.R
import zeng.qiang.wanandroid.entity.Article
import zeng.qiang.wanandroid.entity.Banner
import zeng.qiang.wanandroid.net.Net

class ArticleViewModel(
    var bannerLiveData: MutableLiveData<List<Banner>> = MutableLiveData(),
    var articleLiveData: MutableLiveData<List<Article>> = MutableLiveData()
) : ViewModel() {
    fun banner() {
        viewModelScope.launch {
            try {
                bannerLiveData.value = Net.api.banner().data
            } catch (e: Exception) {

            }
        }
    }

    fun articleList(page: Int = 0) {
        viewModelScope.launch {
            try {
                articleLiveData.value = Net.api.articleList(page = page).data.datas
            } catch (e: Exception) {

            }
        }
    }
}

@Composable
fun ArticlePage(navController: NavController, viewModel: ArticleViewModel = viewModel()) {
    Surface(color = MaterialTheme.colors.background) {
        var isRefreshing: Boolean
        viewModel.articleList(page = 1)
        viewModel.articleLiveData.observeAsState().value?.let {
            isRefreshing = false
            Box(modifier = Modifier.fillMaxSize()) {
                SwipeRefresh(
                    state = SwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = {
                        isRefreshing = true
                        viewModel.articleList(page = 0)
                    }) {
                    LazyColumn {
//                                stickyHeader {
//                                    LazyRow {
//                                        items(
//                                            it.size,
//                                            itemContent = { index: Int -> BannerItem(it[index]) })
//                                    }
//                                }

                        items(count = it.size) { index: Int ->
                            ArticleItem(
                                item = it[index],
                                navController
                            )
                        }
                    }
                }

            }
        }


    }
}

@Composable
fun ArticleItem(item: Article, navController: NavController) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate()
                }
                .padding(
                    horizontal = 10.dp,
                    vertical = 5.dp,
                ),
            elevation = 5.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 10.dp,
                    )
            ) {
                Row() {
                    Column {
                        Text(
                            text = item.title, style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        val author = if (item.author.isNullOrEmpty()) "未知" else item.author
                        Text(
                            text = author, style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )

//                        Image(item.envelopePic)

                    }

                }

            }
        }
    }


}

@Composable
fun Image(url: String) {
    val painter = rememberCoilPainter(url)

    Box {
        androidx.compose.foundation.Image(
            painter = painter,
            contentDescription = stringResource(R.string.image_content_desc),
        )

        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                // Display a circular progress indicator whilst loading
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is ImageLoadState.Error -> {
                // If you wish to display some content if the request fails
            }
        }
    }
}