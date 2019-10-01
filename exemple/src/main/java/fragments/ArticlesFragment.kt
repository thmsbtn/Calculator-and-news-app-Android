package fragments

import adapters.ArticleAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exemple.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modeles.Article
import networks.repository.ArticleRepository


class ArticlesFragment : Fragment() {
    private val repository = ArticleRepository()

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecyclerView(view)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlobalScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list()
            bindData(result)
        }
    }

    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            val adapterRecycler = ArticleAdapter(result)
            recyclerView.adapter = adapterRecycler
        }
    }


    private fun bindRecyclerView(root: View) {
        val planetes = resources.getStringArray(R.array.planetes)
        val spinner: Spinner = root.findViewById(R.id.spinner)
        val adapter =
            ArrayAdapter(root.context, android.R.layout.simple_spinner_item, planetes)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    root.context,
                    "Vous n'avez rien selectionné",
                    Toast.LENGTH_LONG
                )
                    .show()
            }

            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    root.context,
                    "Vous avez selectionné ${planetes[position]}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(root.context)

    }


}

