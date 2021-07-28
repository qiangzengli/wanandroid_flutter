package zeng.qiang.wanandroid.entity


data class BaseEntity<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String

)