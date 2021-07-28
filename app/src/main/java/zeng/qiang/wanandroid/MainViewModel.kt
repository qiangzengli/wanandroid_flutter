package zeng.qiang.wanandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zeng.qiang.wanandroid.entity.Article
import zeng.qiang.wanandroid.entity.Banner
import zeng.qiang.wanandroid.net.Net

class MainViewModel(
    var bannerLiveData: MutableLiveData<List<Banner>> = MutableLiveData(),
    var articleLiveData:MutableLiveData<List<Article>> = MutableLiveData()
    ) : ViewModel() {
    fun banner() {
        viewModelScope.launch {
            try {
                bannerLiveData.value = Net.api.banner().data
            } catch (e: Exception) {

            }
        }
    }
    fun articleList(page:Int=0) {
        viewModelScope.launch {
            try {
                articleLiveData.value = Net.api.articleList(page = page).data.datas
            } catch (e: Exception) {

            }
        }
    }
}