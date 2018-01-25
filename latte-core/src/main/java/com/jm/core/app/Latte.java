package com.jm.core.app;

import android.content.Context;
import android.os.Handler;


/**
 * 统一配置工具类
 * <p>
 * Created by ltjs1024 on 2018/01/25
 */
public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHander() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
