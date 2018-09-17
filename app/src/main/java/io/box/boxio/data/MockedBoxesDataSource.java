package io.box.boxio.data;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MockedBoxesDataSource implements BoxesDataSource {

    private static MockedBoxesDataSource instance;

    private MockedBoxesDataSource() {
    }

    public static BoxesDataSource getInstance() {
        if (instance == null) {
            instance = new MockedBoxesDataSource();
        }
        return instance;
    }


    @Override
    public Flowable<List<Box>> availableBoxes() {
        return Flowable.fromCallable(new Callable<List<Box>>() {
            @Override
            public List<Box> call() throws Exception {
                final List<Box> boxes = new ArrayList<>();
                final List<Box.Color> smallBoxColors = Arrays.asList(
                        Box.Color.RED, Box.Color.BLUE, Box.Color.YELLOW);
                boxes.add(new Box(1, new Box.PredefinedSize(15, 15, 15), Box.Color.BLUE, smallBoxColors));

                final List<Box.Color> mediumBoxColors = Arrays.asList(
                        Box.Color.RED, Box.Color.YELLOW, Box.Color.PURPLE, Box.Color.GREEN);
                boxes.add(new Box(2, new Box.PredefinedSize(100, 25, 25), Box.Color.GREEN, mediumBoxColors));

                final List<Box.Color> largeBoxColors = Arrays.asList(
                        Box.Color.GREEN, Box.Color.ORANGE, Box.Color.BLUE);
                boxes.add(new Box(3, new Box.PredefinedSize(75, 75, 75), Box.Color.ORANGE, largeBoxColors));

                return boxes;
            }
        }).delay(2, TimeUnit.SECONDS);
    }

    @Override
    public Completable save(BoxRequest boxRequest) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                final boolean happyServer = new Random().nextBoolean();
                if (happyServer) {
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("Server is not happy for this time"));
                }
            }
        }).delay(2, TimeUnit.SECONDS);
    }
}
