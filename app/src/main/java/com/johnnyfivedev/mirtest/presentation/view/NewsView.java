package com.johnnyfivedev.mirtest.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface NewsView extends MvpView {
    void showNews(List<Object> news);
}
