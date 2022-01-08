package com.example.hostapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hostapp.mainMenu.MainActivity;
import com.example.hostapp.R;
import com.example.hostapp.utils.UiUtils;
import com.example.hostapp.serverapi.DemoServerApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.lifecycle.ViewModelProvider;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private LoginViewModel loginViewModel;

    private TextInputLayout loginLayout;
    private TextInputEditText loginEditText;
    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //private AppCompatTextView signUpButton;
        MaterialButton signInButton = findViewById(R.id.signInButton);

        loginLayout = findViewById(R.id.loginLayout);
        loginEditText = findViewById(R.id.loginEditText);

        passwordLayout = findViewById(R.id.passwordLayout);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        signInButton.setOnClickListener(this);

        loginViewModel.getIsLoginError().observe(this, isError -> {
            if (isError)
                loginLayout.setError(getString(R.string.enter_login));
            else {
                loginLayout.setError(null);
                loginLayout.setErrorEnabled(false);
            }
        });

        loginViewModel.getIsPasswordError().observe(this, isError -> {
            if (isError)
                passwordLayout.setError(getString(R.string.enter_password));
            else {
                passwordLayout.setError(null);
                passwordLayout.setErrorEnabled(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signInButton) {
            SignIn();
        }
    }

    private void SignIn() {
        String login = "";
        String password = "";

        if (loginEditText.getText() != null)
            login = loginEditText.getText().toString();
        if (passwordEditText.getText() != null)
            password = passwordEditText.getText().toString();

        loginViewModel.setIsLoginError(login.isEmpty());
        loginViewModel.setIsPasswordError(password.isEmpty());

        if (!login.isEmpty() && !password.isEmpty()) {
            boolean isSignedIn = DemoServerApi.checkSignIn(login, password);
            if (!isSignedIn) {
                UiUtils.ShowSimpleDialog(getString(R.string.error), getString(R.string.wrong_sign_in_credentials), this);
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
