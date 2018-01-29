package com.jm.fastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jm.core.app.Latte;
import com.jm.core.net.interceptors.DebugInterceptor;
import com.jm.ec.database.DatabaseManager;
import com.jm.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost("http://192.168.0.81/")
                .withLoaderDelay(1000)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);

        initStetho();

    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
