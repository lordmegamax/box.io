package io.box.boxio.di;

import dagger.Module;
import dagger.Provides;
import io.box.boxio.utils.FieldsValidator;

import javax.inject.Singleton;

@Module
public class ValidatorModule {

    @Provides
    @Singleton
    FieldsValidator provideFieldsValidator() {
        return new FieldsValidator();
    }
}
