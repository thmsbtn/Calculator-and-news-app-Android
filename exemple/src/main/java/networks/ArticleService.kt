package networks

import modeles.Article
import modeles.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {
    @GET("everything?q=bitcoin&apiKey=02c97b760bec417786d2aeee35e04d9a")
    fun list(): Call<ArticleResponse>
}
