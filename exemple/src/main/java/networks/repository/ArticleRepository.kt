package networks.repository

import android.util.Log
import modeles.Article
import networks.ArticleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ArticleRepository() {
    private val service: ArticleService

    init {


        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org/v2/")
        }
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        service = retrofit.create(ArticleService::class.java)
    }

    fun list(): List<Article> {
        Log.d("Repo", " == getList")
        val response = service.list().execute()
        Log.d("Repo", " == has list ${response.code()} == ${response.body()}")
        val result = response.body()?.articles ?: emptyList()
        Log.d("Repo", " == has list $result")
        return result
    }

}
