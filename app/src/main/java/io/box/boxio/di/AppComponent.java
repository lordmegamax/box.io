package io.box.boxio.di;

import dagger.Component;
import io.box.boxio.boxrequest.BoxRequestActivity;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DataModule.class, ValidatorModule.class})
public interface AppComponent {
    void injectBoxRequestActivity(BoxRequestActivity activity);
}
