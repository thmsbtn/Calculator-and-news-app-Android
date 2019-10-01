package adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exemple.R
import modeles.Article


class ArticleAdapter(private val dataset: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val txtDesc = root.findViewById<TextView>(R.id.article_description)
            val imgView = root.findViewById<ImageView>(R.id.imageView)
            txtTitle.text = item .title
            txtDesc.text = item.description
            if(item.src == 0){
                imgView.setImageResource(R.drawable.ic_launcher_background)
            }else {
                imgView.setImageLevel(item.src)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView= LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}
