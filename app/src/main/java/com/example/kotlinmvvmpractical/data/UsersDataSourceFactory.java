package com.example.kotlinmvvmpractical.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class UsersDataSourceFactory extends DataSource.Factory<Long,UsersItem> {

    UsersDataSource usersDataSource;
    MutableLiveData<UsersDataSource> mutableLiveData;

    public UsersDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        usersDataSource = new UsersDataSource();
        mutableLiveData.postValue(usersDataSource);
        return usersDataSource;
    }

    public MutableLiveData<UsersDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
