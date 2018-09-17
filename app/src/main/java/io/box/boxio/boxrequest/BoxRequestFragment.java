package io.box.boxio.boxrequest;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import io.box.boxio.R;
import io.box.boxio.data.Box;

import java.util.List;

public class BoxRequestFragment extends Fragment implements BoxRequestContract.View {

    View contentView;
    ProgressBar progressBar;
    EditText usernameEditText;
    EditText emailEditText;

    Spinner boxSizesSpinner;
    Spinner boxColorsSpinner;
    AppCompatCheckBox printUsernameCheckBox;
    Button requestButton;

    private BoxSizesAdapter boxSizesAdapter;
    private BoxColorsAdapter boxColorsAdapter;

    private BoxRequestContract.Presenter presenter;

    public BoxRequestFragment() {
        // Required empty public constructor
    }

    public static BoxRequestFragment newInstance() {
        return new BoxRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_box_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        initRequestButton();
        initBoxSizes();
        initBoxColors();

        presenter.subscribe();
    }

    private void initRequestButton() {
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                boolean printName = printUsernameCheckBox.isChecked();
                presenter.onBoxRequested(username, email, printName);
            }
        });
    }

    private void initViews(@NonNull View view) {
        // FIXME: 16-Sep-18 replace findViewById() calls with ButterKnife

        progressBar = view.findViewById(R.id.fr_box_request_progress_bar);
        contentView = view.findViewById(R.id.fr_box_request_content);

        usernameEditText = view.findViewById(R.id.fr_box_request_username);
        emailEditText = view.findViewById(R.id.fr_box_request_email);
        printUsernameCheckBox = view.findViewById(R.id.fr_box_request_print_username);

        boxSizesSpinner = view.findViewById(R.id.fr_box_request_box_size);
        boxColorsSpinner = view.findViewById(R.id.fr_box_request_colors);

        requestButton = view.findViewById(R.id.fr_box_request_request);
    }

    private void initBoxSizes() {
        boxSizesAdapter = new BoxSizesAdapter(getActivity());
        boxSizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boxSizesSpinner.setAdapter(boxSizesAdapter);
        boxSizesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final Box box = boxSizesAdapter.getItem(i);
                presenter.onBoxSelected(box);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                /*NOP*/
            }
        });
    }

    private void initBoxColors() {
        boxColorsAdapter = new BoxColorsAdapter(getActivity());
        boxColorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boxColorsSpinner.setAdapter(boxColorsAdapter);
        boxColorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final Box.Color color = boxColorsAdapter.getItem(i);
                presenter.onColorSelected(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                /*NOP*/
            }
        });
    }

    @Override
    public void showLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        contentView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAvailableBoxes(final List<Box> boxes) {
        boxSizesAdapter.setBoxes(boxes);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(contentView, R.string.error_something_bad, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showColors(Box box) {
        boxColorsAdapter.setBox(box);
    }

    @Override
    public void notifyInvalidUsername() {
        clearErrorState();
        usernameEditText.setError(getString(R.string.error_name));
        usernameEditText.requestFocus();
    }

    @Override
    public void notifyInvalidEmail() {
        clearErrorState();
        emailEditText.setError(getString(R.string.error_email));
        emailEditText.requestFocus();
    }

    @Override
    public void notifyInvalidSelectedColor() {
        Toast.makeText(getActivity(), getString(R.string.error_color), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCongratsDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.message_box_added)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*NOP*/
                    }
                })
                .setNegativeButton(R.string.clear, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), getString(R.string.message_not_implemented), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    @Override
    public void setPresenter(BoxRequestContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void clearErrorState() {
        usernameEditText.setError(null);
        emailEditText.setError(null);
    }
}
