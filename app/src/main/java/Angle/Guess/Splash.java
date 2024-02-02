package Angle.Guess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import Angle.Guess.Repository.Animations;
import Angle.Guess.Repository.IntConnection;
import Angle.Guess.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private Animations TheAnimation;
    private IntConnection GMInternet;
    private String FSStatus, URLStatus, url;
    boolean HadInternetConnection, NoInternetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TheAnimation = new Animations(this);

        GMInternet = new IntConnection(this);
        HadInternetConnection = GMInternet.getInternetConnection();
        LoadApp();
    }

    private void LoadApp() {
        if (HadInternetConnection) {
            LoadF();
        } else {
            NoInternetConnection = GMInternet.getNoInternetConnection();
        }
    }

    private void LoadF() {
        binding.SplashLogo.setVisibility(View.VISIBLE);
        TheAnimation.getScaleAnimation(binding.SplashLogo);
        Handler delayHandler = new Handler();
        delayHandler.postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 700);
    }

    private void CustomDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Application")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            CustomDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    } // Exit Functions
}



