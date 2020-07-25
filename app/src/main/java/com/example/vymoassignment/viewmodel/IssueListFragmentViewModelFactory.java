package com.example.vymoassignment.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class IssueListFragmentViewModelFactory implements ViewModelProvider.Factory {

    private Context applicationContext;

    public IssueListFragmentViewModelFactory(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(IssueListFragmentViewModel.class)) {
            return (T) new IssueListFragmentViewModel(applicationContext);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
