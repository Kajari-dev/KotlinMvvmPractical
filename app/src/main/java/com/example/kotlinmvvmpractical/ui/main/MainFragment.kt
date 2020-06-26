package com.example.kotlinmvvmpractical.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvvmpractical.R
import com.example.kotlinmvvmpractical.data.Data
import com.example.kotlinmvvmpractical.data.UserResponse
import com.example.kotlinmvvmpractical.data.UsersItem
import com.example.kotlinmvvmpractical.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rvMain.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        viewModel.pagedListLiveData.observe(viewLifecycleOwner,
            Observer<PagedList<UsersItem>> { usersResponse ->
                val usersAdapter = activity?.let { UserAdapter(it) }
                usersAdapter?.submitList(usersResponse)
                rvMain.adapter = usersAdapter
            });
    }

    }

