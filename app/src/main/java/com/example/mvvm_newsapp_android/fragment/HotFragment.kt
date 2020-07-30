package com.example.mvvm_newsapp_android.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.denzcoskun.imageslider.models.SlideModel
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.activity.PopularActivity
import com.example.mvvm_newsapp_android.adapter.HotNewsAdapter
import com.example.mvvm_newsapp_android.base.BaseView
import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.example.mvvm_newsapp_android.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.component_list_news_one.*
import kotlinx.android.synthetic.main.fragment_hot.*

class HotFragment : Fragment(), BaseView {

    var isRefresh = false
    val imgSlider = ArrayList<SlideModel>()
    private var list = ArrayList<ArticlesModel>()
    private val adapter by lazy { HotNewsAdapter() }
    private val viewModel by lazy { ViewModelProviders.of(this).get(NewsViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
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
        swipe_refresh.setOnRefreshListener {
            refresh()
        }

        img_arrow_popular.setOnClickListener {
            startActivity(Intent(activity, PopularActivity::class.java))
        }
    }

    private fun initViewModel(){
        recyclerView_news_one.adapter = adapter
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
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun fetchData(){
        list.clear();
        viewModel.getTopHeadlines("id")
    }

    override fun refresh() {
        isRefresh = true
        list.clear();
        viewModel.onRefreshTopHeadlines("id")
    }

    override fun reload() {
    }

    override fun onFailure(t: Throwable) {
        news_progress_load.visibility = View.GONE
        swipe_refresh.isRefreshing = false
        Toast.makeText(activity, "Failure $t", Toast.LENGTH_SHORT).show()
    }

    override fun onLoading() {
        if (!isRefresh) news_progress_load.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        news_progress_load.visibility = View.GONE
        swipe_refresh.isRefreshing = false
        if (list.size > 0) {
            imgSlider.clear()
            for (listImg in list) {
                imgSlider.add(SlideModel(listImg.urlToImage.toString()))
            }
        }
        if (imgSlider.size > 0){
            slider.setImageList(imageList = imgSlider)
        }
    }
}