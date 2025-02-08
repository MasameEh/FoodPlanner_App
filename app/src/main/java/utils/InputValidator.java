package utils;

import android.util.Patterns;
import android.widget.EditText;

public class InputValidator {
    public static boolean validateSignupInputs(EditText usernameEt, EditText emailEt, EditText passwordEt, EditText confirmPassEt) {
        boolean isValid = true;

        String username = usernameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String confirmPass = confirmPassEt.getText().toString().trim();

        if (username.isEmpty()) {
            usernameEt.setError("Username is required");
            isValid = false;
        }

        if (email.isEmpty()) {
            emailEt.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Invalid email format");
            isValid = false;
        }

        if (password.isEmpty()) {
            passwordEt.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            passwordEt.setError("Password must be at least 6 characters");
            isValid = false;
        }

        if (confirmPass.isEmpty()) {
            confirmPassEt.setError("Confirm password is required");
            isValid = false;
        } else if (!confirmPass.equals(password)) {
            confirmPassEt.setError("Passwords do not match");
            isValid = false;
        }

        return isValid; // Return true if all fields are valid
    }
}
