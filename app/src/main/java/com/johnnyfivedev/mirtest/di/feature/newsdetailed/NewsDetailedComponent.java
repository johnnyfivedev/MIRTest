package com.johnnyfivedev.mirtest.di.feature.newsdetailed;

import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.ui.fragment.NewsDetailedFragment;
import com.johnnyfivedev.mirtest.ui.fragment.NewsFragment;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = {NewsDetailedModule.class})
public interface NewsDetailedComponent {

    void inject(NewsDetailedFragment fragment);
}