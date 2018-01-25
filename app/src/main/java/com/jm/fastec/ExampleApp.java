package com.jm.fastec;

import android.app.Application;

import com.jm.core.app.Latte;
import com.jm.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost("http://wap.faxingw.cn/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();

    }
}
