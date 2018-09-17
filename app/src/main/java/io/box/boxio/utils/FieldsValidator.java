package io.box.boxio.utils;

import android.util.Patterns;
import io.box.boxio.data.Box;

public class FieldsValidator {

    public boolean isUsernameValid(final String username) {
        if (username == null) return false;

        if (username.isEmpty()) return false;

        return true;
    }

    public boolean isEmailValid(final String email) {
        if (email == null) return false;

        if (email.isEmpty()) return false;

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isSelectedColorValid(final Box box, final Box.Color color) {
        if (box == null || color == null) return false;

        return box.getAvailableColors().contains(color);
    }
}
