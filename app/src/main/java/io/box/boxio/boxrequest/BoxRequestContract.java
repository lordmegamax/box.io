package io.box.boxio.boxrequest;

import io.box.boxio.BasePresenter;
import io.box.boxio.BaseView;
import io.box.boxio.data.Box;

import java.util.List;

public interface BoxRequestContract {
    interface View extends BaseView<Presenter> {
        void showLoading(boolean isLoading);

        void showAvailableBoxes(List<Box> boxes);

        void showErrorMessage();

        void showColors(Box box);

        void notifyInvalidUsername();

        void notifyInvalidEmail();

        void notifyInvalidSelectedColor();

        void showCongratsDialog();
    }

    interface Presenter extends BasePresenter {
        void onBoxSelected(Box box);

        void onColorSelected(Box.Color color);

        void onBoxRequested(String username, String email, boolean printName);
    }
}
