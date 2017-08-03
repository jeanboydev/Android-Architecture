package com.jeanboy.app.architecture.di.component;


import com.jeanboy.app.architecture.di.modules.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jeanboy on 2017/6/20.
 */
@Singleton
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {

//    WeatherInfoGetTask weatherInfoGetTask();

//    void inject(WeatherPresenter presenter);
//
//    void inject(CityPresenter presenter);
//
//    void inject(CityEditPresenter presenter);
//
//    void inject(AlertPresenter presenter);
}
