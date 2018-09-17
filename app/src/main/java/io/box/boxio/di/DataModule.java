package io.box.boxio.di;

import dagger.Module;
import dagger.Provides;
import io.box.boxio.data.BoxesDataSource;
import io.box.boxio.data.MockedBoxesDataSource;
import io.box.boxio.data.Repository;

import javax.inject.Singleton;

@Module
public class DataModule {

    @Provides
    @Singleton
    Repository provideRepository(BoxesDataSource dataSource) {
        return new Repository(dataSource);
    }

    @Provides
    @Singleton
    BoxesDataSource provideDataSource() {
        return MockedBoxesDataSource.getInstance();
    }
}
