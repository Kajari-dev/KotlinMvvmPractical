package com.example.kotlinmvvmpractical.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.kotlinmvvmpractical.data.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainViewModel(application: Application) : AndroidViewModel(application) {
    var photoDataSourceFactory: UsersDataSourceFactory = UsersDataSourceFactory()
    var dataSourceMutableLiveData: MutableLiveData<UsersDataSource>
    var executor: Executor
    var pagedListLiveData: LiveData<PagedList<UsersItem>>

    init {
        dataSourceMutableLiveData = photoDataSourceFactory.mutableLiveData
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(4)
            .build()
        executor = Executors.newFixedThreadPool(5)
        pagedListLiveData =
            LivePagedListBuilder<Long, UsersItem>(photoDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build()
    }
}