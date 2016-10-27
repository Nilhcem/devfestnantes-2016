package com.nilhcem.devfestnantes.core.dagger.module;

import android.app.Application;

import com.nilhcem.devfestnantes.DevFestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private final DevFestApp app;

    public AppModule(DevFestApp app) {
        this.app = app;
    }

    @Provides @Singleton Application provideApplication() {
        return app;
    }
}
