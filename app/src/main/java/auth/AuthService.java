package auth;

import com.google.firebase.auth.FirebaseUser;

public interface AuthService {
    void login(String email, String password, AuthCallback callback);
    void signUpWithEmail(String email, String password, AuthCallback callback);
    void signInWithGoogle(String idToken, AuthCallback callback);
    FirebaseUser getCurrentUser();
}
