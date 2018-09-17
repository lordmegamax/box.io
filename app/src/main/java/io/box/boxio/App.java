package io.box.boxio;

import android.app.Application;
import io.box.boxio.di.AppComponent;
import io.box.boxio.di.DaggerAppComponent;
import io.box.boxio.di.DataModule;
import io.box.boxio.di.ValidatorModule;

public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                                         .dataModule(new DataModule())
                                         .validatorModule(new ValidatorModule())
                                         .build();
    }
}
