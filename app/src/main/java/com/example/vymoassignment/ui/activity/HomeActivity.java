package com.example.vymoassignment.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vymoassignment.R;
import com.example.vymoassignment.adapter.ViewPagerAdapter;
import com.example.vymoassignment.databinding.ActivityHomeBinding;
import com.example.vymoassignment.enums.GithubIssueType;
import com.example.vymoassignment.model.GithubRepoDetails;
import com.example.vymoassignment.ui.fragment.IssueListFragment;
import com.example.vymoassignment.util.AppConstants;

public class HomeActivity extends AppCompatActivity {

    private static final String ISSUE_TYPE_1 = "open";
    private static final String ISSUE_TYPE_2 = "closed";
    private ActivityHomeBinding activityHomeBinding;
    private GithubRepoDetails githubRepoDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        githubRepoDetails = getIntent().getParcelableExtra(AppConstants.IntentKey.GITHUB_REPO_DETAILS);
        activityHomeBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_home);
        setupViewPager();
    }

    private void setupViewPager() {
        activityHomeBinding.tabLayout.setupWithViewPager(activityHomeBinding.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(IssueListFragment.newInstance(githubRepoDetails,
                GithubIssueType.OPEN), GithubIssueType.OPEN.getTypeString());
        viewPagerAdapter.addFragment(IssueListFragment.newInstance(githubRepoDetails,
                GithubIssueType.CLOSED), ISSUE_TYPE_2);
        activityHomeBinding.viewpager.setAdapter(viewPagerAdapter);
    }
}