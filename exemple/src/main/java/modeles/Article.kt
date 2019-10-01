package modeles

import android.media.Image

data class Article(val title: String, val description: String, var src: Int )

data class ArticleResponse(val status: String, val totalResults: Int, val articles: List<Article>)


