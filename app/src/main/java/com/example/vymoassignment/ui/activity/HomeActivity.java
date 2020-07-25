package com.example.vymoassignment.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vymoassignment.R;
import com.example.vymoassignment.adapter.ViewPagerAdapter;
import com.example.vymoassignment.databinding.ActivityHomeBinding;
import com.example.vymoassignment.ui.fragment.IssueListFragment;

public class HomeActivity extends AppCompatActivity {

    private static final String ISSUE_TYPE_1 = "Open";
    private static final String ISSUE_TYPE_2 = "Closed";

    private ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activityHomeBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_home);
        setupViewPager();
    }

    private void setupViewPager() {
        activityHomeBinding.tabLayout.setupWithViewPager(activityHomeBinding.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(IssueListFragment.newInstance(ISSUE_TYPE_1), ISSUE_TYPE_1);
        viewPagerAdapter.addFragment(IssueListFragment.newInstance(ISSUE_TYPE_2), ISSUE_TYPE_2);
        activityHomeBinding.viewpager.setAdapter(viewPagerAdapter);
    }
}