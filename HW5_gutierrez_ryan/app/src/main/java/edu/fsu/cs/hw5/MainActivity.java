package edu.fsu.cs.hw5;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements
        MainFragment.OnFragmentInteractionListener, LoginFragment.OnLoginFragmentInteractionListener,
        RegisterFragment.OnRegisterFragmentInteractionListener,
        ViewEmployeeFragment.OnUserFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onMain();
    }

    public void onMain() {
        MainFragment fragment = new MainFragment();
        String tag = MainFragment.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();
    }

    @Override
    public void onLoggedIn(Uri uri) {
        // TODO: User logged in
    }

    @Override
    public void onSubmit(ContentValues values) {
        // TODO: User registered
    }

    @Override
    public void onStartLogin() {
        LoginFragment fragment = new LoginFragment();
        String tag = LoginFragment.class.getCanonicalName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStartRegister() {
        RegisterFragment fragment = new RegisterFragment();
        String tag = RegisterFragment.class.getCanonicalName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, fragment, tag)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onLoggedOut() {
        // TODO: User logged out
    }

    @Override
    public void onUserDeleted(int res) {
        // TODO: User account deleted
    }
}
