package io.box.boxio.data;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import java.util.List;

public interface BoxesDataSource {
    Flowable<List<Box>> availableBoxes();

    Completable save(BoxRequest boxRequest);
}
