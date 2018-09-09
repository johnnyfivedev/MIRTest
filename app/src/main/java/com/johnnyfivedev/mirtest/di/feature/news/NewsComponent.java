package com.johnnyfivedev.mirtest.di.feature.news;

import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.ui.fragment.NewsFragment;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = {NewsModule.class})
public interface NewsComponent {

    void inject(NewsFragment fragment);
}