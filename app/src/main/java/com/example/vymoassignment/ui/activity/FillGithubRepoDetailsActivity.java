package com.example.vymoassignment.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.vymoassignment.R;
import com.example.vymoassignment.databinding.ActivityFillGithubRepoDetailsBinding;
import com.example.vymoassignment.util.AppConstants;
import com.example.vymoassignment.viewmodel.FillGithubRepoDetailsActivityViewModel;
import com.example.vymoassignment.viewmodel.FillGithubRepoDetailsActivityViewModel.ViewState;

public class FillGithubRepoDetailsActivity extends AppCompatActivity {

    private FillGithubRepoDetailsActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFillGithubRepoDetailsBinding activityBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_fill_github_repo_details);
        activityViewModel = ViewModelProviders.of(this)
                .get(FillGithubRepoDetailsActivityViewModel.class);
        activityBinding.setActivityViewModel(activityViewModel);
        activityBinding.setSubmitClickRunnable(this::onSubmitClick);
    }

    private void onSubmitClick() {
        ViewState viewState = activityViewModel.getViewState();
        if (viewState instanceof ViewState.Success) {
            Intent homeActivityIntent = new Intent(this, HomeActivity.class);
            homeActivityIntent.putExtra(AppConstants.IntentKey.GITHUB_REPO_DETAILS,
                    activityViewModel.getGithubRepoDetails());
            startActivity(homeActivityIntent);
        } else if (viewState instanceof ViewState.Error) {
            Toast.makeText(this,
                    ((ViewState.Error) viewState).getErrorMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}