package Angle.Guess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import Angle.Guess.Repository.Animations;
import Angle.Guess.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding Binding;
    private String textToType1, textToType2;
    private int charIndex1 = 0;
    private int charIndex2 = 0;
    private Animations TheAnimation;
    Handler HandlerDelay = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        TheAnimation = new Animations(this);

        LoadApp();
    }
    @SuppressLint("SetTextI18n")
    private void LoadApp() {
        FontStyle();
        TheAnimation.get360RotatingAnimation(Binding.LoadingImg);
        TheAnimation.getRotatingAnimation(Binding.NavBar);

        ProgressBar loadingBar = Binding.ProgressBar;
        TextView progressText = Binding.ProgressText;
        loadingBar.setProgress(0);
        progressText.setText("Loading... 0%");


        HandlerDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                int randomIncrement = new Random().nextInt(11) + 10;
                int progress = loadingBar.getProgress() + randomIncrement;
                progress = Math.min(progress, 100);
                loadingBar.setProgress(progress);

                if (progress < 50) {
                    progressText.setText("Loading... " + progress + "%");
                    HandlerDelay.postDelayed(this, 500);
                } else if (progress < 100) {
                    progressText.setText("Preparing Assets... " + progress + "%");
                    HandlerDelay.postDelayed(this, 500);
                } else {
                    progressText.setText("Application Start... " + progress + "%");
                    HandlerDelay.postDelayed(() -> {
                        Binding.LoadingImg.setVisibility(View.GONE);
                        Binding.ProgressBar.setVisibility(View.GONE);
                        Binding.ProgressText.setVisibility(View.GONE);
                        TypeCredits1();
                    }, 1000);
                }
            }
        }, 500);
    }
    private void TypeCredits1() {
        textToType1 = getString(R.string.Credits1);
        Binding.Text1.setVisibility(View.VISIBLE);

        HandlerDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (charIndex1 <= textToType1.length()) {
                    String displayedText = textToType1.substring(0, charIndex1);
                    Binding.Text1.setText(displayedText);
                    charIndex1++;
                    HandlerDelay.postDelayed(this, 100);
                } else {
                    HandlerDelay.postDelayed(() -> TheAnimation.getFadingAnimation(Binding.Text1), 500);
                    HandlerDelay.postDelayed(() -> {
                        Binding.Text1.setVisibility(View.GONE);
                        Binding.Pic1.setVisibility(View.VISIBLE);
                        TheAnimation.getFullScaleAnimation(Binding.Pic1);
                        TypeCredits2();
                    }, 1000);
                }
            }
        }, 500);
    }
    private void TypeCredits2() {
        textToType2 = getString(R.string.Credits2);
        Binding.Text2.setVisibility(View.VISIBLE);

        HandlerDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (charIndex2 <= textToType2.length()) {
                    String displayedText = textToType2.substring(0, charIndex2);
                    Binding.Text2.setText(displayedText);
                    charIndex2++;
                    HandlerDelay.postDelayed(this, 100);
                } else {
                    HandlerDelay.postDelayed(() -> TheAnimation.getFadingAnimation(Binding.Text2),1000);

                    HandlerDelay.postDelayed(() -> {
                        Binding.Text2.setVisibility(View.GONE);
                        Binding.Pic1.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent);
                        finish();
                    },1500);
                }
            }
        }, 1000);
    }

    private void FontStyle() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font.ttf");
        Binding.ProgressText.setTypeface(customFont);
        Binding.Text1.setTypeface(customFont);
        Binding.Text2.setTypeface(customFont);
        Binding.AppVersion.setTypeface(customFont);
    }
    private void CustomDialog() {
        new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
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