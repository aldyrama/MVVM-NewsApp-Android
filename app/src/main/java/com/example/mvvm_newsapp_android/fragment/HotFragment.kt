package com.example.mvvm_newsapp_android.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.activity.PopularActivity
import kotlinx.android.synthetic.main.fragment_hot.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HotFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_arrow_popular.setOnClickListener {
            startActivity(Intent(activity, PopularActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HotFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}