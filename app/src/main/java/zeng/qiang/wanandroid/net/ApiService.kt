package zeng.qiang.wanandroid.net

import retrofit2.http.GET
import retrofit2.http.Path
import zeng.qiang.wanandroid.entity.*

interface ApiService {
    @GET("https://www.wanandroid.com/banner/json")
    suspend fun banner(): BaseEntity<List<Banner>>

    @GET("https://www.wanandroid.com/article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int): BaseEntity<BasePagesEntity<List<Article>>>

}