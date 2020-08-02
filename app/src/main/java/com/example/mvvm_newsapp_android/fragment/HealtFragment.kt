package com.example.mvvm_newsapp_android.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.adapter.NewsAdapter
import com.example.mvvm_newsapp_android.base.BaseView
import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.example.mvvm_newsapp_android.viewmodel.NewsCategoryViewModel
import kotlinx.android.synthetic.main.component_list_news.*
import kotlinx.android.synthetic.main.component_progress_bar.*

class HealtFragment : Fragment(), BaseView {

    var isRefresh = false
    private var list = ArrayList<ArticlesModel>()
    private val adapter by lazy { NewsAdapter() }
    private val viewModel by lazy { ViewModelProviders.of(this).get(NewsCategoryViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_healt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    companion object {
        fun newInstance(): HotFragment {
            return HotFragment()
        }
    }

    private fun init(){
        initViewModel()
        fetchData()
//        swipe_refresh_entertainment.setOnRefreshListener {
//            refresh()
//        }
    }

    private fun initViewModel(){
        recyclerView_news.adapter = adapter
        viewModel.resource.observe(viewLifecycleOwner, Observer{
            when(it?.status) {
                Resource.LOADING -> onLoading()
                Resource.SUCCESS -> onSuccess()
                Resource.ERROR -> onFailure(it.error)
            }
        })

//        lifecycle.addObserver(viewModel)

        viewModel.dataList.observe(viewLifecycleOwner, Observer{
            list = it
            adapter.setCategoryList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun fetchData(){
        list.clear();
        viewModel.getCategoryTopHeadlines("id", pageSize = 20, category = "health")
    }

    override fun refresh() {
        isRefresh = true
        list.clear();
        viewModel.onRefreshCategoryTopHeadlines("id", category = "health")
    }

    override fun reload() {
    }

    override fun onFailure(t: Throwable) {
        news_progress_load.visibility = View.GONE
//        swipe_refresh_entertainment.isRefreshing = false
        Toast.makeText(activity, "Failure $t", Toast.LENGTH_SHORT).show()
    }

    override fun onLoading() {
        if (!isRefresh) news_progress_load.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        news_progress_load.visibility = View.GONE
//        swipe_refresh_entertainment.isRefreshing = false
    }
}