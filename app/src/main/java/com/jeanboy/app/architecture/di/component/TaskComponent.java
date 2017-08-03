package com.jeanboy.app.architecture.di.component;


import com.jeanboy.app.architecture.di.modules.TaskModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jeanboy on 2017/6/20.
 */
@Singleton
@Component(modules = {TaskModule.class})
public interface TaskComponent {

//    AlertGetRemoteTask alertGetRemoteTask();
}
