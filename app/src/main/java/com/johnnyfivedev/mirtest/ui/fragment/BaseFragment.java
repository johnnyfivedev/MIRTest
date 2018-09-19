package com.johnnyfivedev.mirtest.ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.johnnyfivedev.mirtest.BackButtonListener;
import com.johnnyfivedev.mirtest.R;


public abstract class BaseFragment extends MvpBaseFragment {

    public void setupToolbar(
            View inflatedFragment,
            Integer titleResId,
            Integer homeIconId,
            boolean backButtonEnabled,
            BackButtonListener backButtonListener) {
        Toolbar toolbar = inflatedFragment.findViewById(R.id.toolbar);
        if (toolbar != null) {
            final AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity != null) {
                activity.setSupportActionBar(toolbar);
                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                if (titleResId != null) {
                    toolbar.setTitle(titleResId);
                }
                if (homeIconId != null) {
                    toolbar.setNavigationIcon(homeIconId);
                } else if (backButtonEnabled) {
                    activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                toolbar.setNavigationOnClickListener(v -> backButtonListener.onBackPressed());
            }
        }
    }
}
