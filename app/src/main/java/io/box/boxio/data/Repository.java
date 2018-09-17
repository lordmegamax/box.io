package io.box.boxio.data;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import java.util.List;

public class Repository implements BoxesDataSource {
    private final BoxesDataSource dataSource;

    public Repository(BoxesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Flowable<List<Box>> availableBoxes() {
        return dataSource.availableBoxes();
    }

    @Override
    public Completable save(BoxRequest boxRequest) {
        return dataSource.save(boxRequest);
    }
}
