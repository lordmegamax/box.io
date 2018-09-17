package io.box.boxio.boxrequest;

import android.support.annotation.NonNull;
import android.util.Log;
import io.box.boxio.data.Box;
import io.box.boxio.data.BoxRequest;
import io.box.boxio.data.BoxesDataSource;
import io.box.boxio.data.CustomizedBox;
import io.box.boxio.data.User;
import io.box.boxio.utils.FieldsValidator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;

import java.util.List;

public class BoxRequestPresenter implements BoxRequestContract.Presenter {

    private static final String TAG = "BoxRequestPresenter";

    private final BoxRequestContract.View boxRequestView;
    private final BoxesDataSource repository;
    private final FieldsValidator validator;

    private final CompositeDisposable compositeDisposable;

    private Box selectedBox;
    private Box.Color selectedBoxColor;

    public BoxRequestPresenter(@NonNull BoxRequestContract.View boxRequestView,
                               @NonNull BoxesDataSource repository,
                               @NonNull FieldsValidator validator) {
        this.boxRequestView = boxRequestView;
        this.boxRequestView.setPresenter(this);
        this.repository = repository;
        this.validator = validator;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onBoxRequested(final String username, final String email, final boolean printName) {
        final CustomizedBox customizedBox = new CustomizedBox(selectedBox, selectedBoxColor);
        final BoxRequest boxRequest = new BoxRequest(new User(username, email), customizedBox, printName);

        if (!validate(boxRequest)) {
            return;
        }

        compositeDisposable.add(
                repository.save(boxRequest)
                          .subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(new Action() {
                              @Override
                              public void run() throws Exception {
                                  boxRequestView.showCongratsDialog();
                              }
                          }, new Consumer<Throwable>() {
                              @Override
                              public void accept(Throwable throwable) throws Exception {
                                  boxRequestView.showErrorMessage();
                              }
                          }));
    }

    private boolean validate(final BoxRequest boxRequest) {
        final User user = boxRequest.getUser();

        final String username = user.getUsername();
        if (!validator.isUsernameValid(username)) {
            boxRequestView.notifyInvalidUsername();
            return false;
        }

        final String email = user.getEmail();
        if (!validator.isEmailValid(email)) {
            boxRequestView.notifyInvalidEmail();
            return false;
        }

        final CustomizedBox box = boxRequest.getBox();
        final Box.Color selectedColor = box.getSelectedColor();
        if (!validator.isSelectedColorValid(box, selectedColor)) {
            boxRequestView.notifyInvalidSelectedColor();
            return false;
        }

        return true;
    }

    @Override
    public void onBoxSelected(Box box) {
        selectedBox = box;
        boxRequestView.showColors(box);
    }

    @Override
    public void onColorSelected(Box.Color color) {
        selectedBoxColor = color;
    }

    @Override
    public void subscribe() {
        compositeDisposable.add(
                repository.availableBoxes()
                          .doOnSubscribe(new Consumer<Subscription>() {
                              @Override
                              public void accept(Subscription subscription) throws Exception {
                                  boxRequestView.showLoading(true);
                              }
                          })
                          .subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(new Consumer<List<Box>>() {
                              @Override
                              public void accept(List<Box> boxes) throws Exception {
                                  boxRequestView.showAvailableBoxes(boxes);
                                  boxRequestView.showLoading(false);
                              }
                          }, new Consumer<Throwable>() {
                              @Override
                              public void accept(Throwable t) throws Exception {
                                  boxRequestView.showErrorMessage();
                                  boxRequestView.showLoading(false);
                              }
                          }));
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
