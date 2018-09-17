package io.box.boxio.boxrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.box.boxio.App;
import io.box.boxio.R;
import io.box.boxio.data.BoxesDataSource;
import io.box.boxio.utils.FieldsValidator;

import javax.inject.Inject;

public class BoxRequestActivity extends AppCompatActivity {

    @Inject
    BoxesDataSource repository;

    @Inject
    FieldsValidator fieldsValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_request);

        App.getAppComponent().injectBoxRequestActivity(this);

        BoxRequestFragment boxRequestFragment = (BoxRequestFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_box_request_content);

        if (boxRequestFragment == null) {
            boxRequestFragment = BoxRequestFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_box_request_content, boxRequestFragment)
                    .commit();
        }

        final BoxRequestPresenter presenter = new BoxRequestPresenter(
                boxRequestFragment, repository, fieldsValidator);

        // TODO: 16-Sep-18 load previous state

    }
}
