package Angle.Guess;

import static Angle.Guess.ShopActivity.SKIN_DEFAULT;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.DecelerateInterpolator;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import Angle.Guess.Repository.Animations;
import Angle.Guess.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding Binding;
    private float currentRotation = 0f;
    private boolean isSpinInProgress = false;
    private int currentScore = 0;
    private int bestScore = 0;
    private SharedPreferences sharedPreferences;
    private static final String BEST_SCORE_KEY = "bestScore";
    private static final String SELECTED_SKIN_KEY = "selectedSkin";
    Handler HandlerDelay = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        sharedPreferences = getPreferences(MODE_PRIVATE);
        bestScore = sharedPreferences.getInt(BEST_SCORE_KEY, 0);

        Animations theAnimation = new Animations(this);
        theAnimation.getRotatingAnimation(Binding.NavBar);

        restoreSelectedSkin();
        LoadGame();
    }
    private void LoadGame() {
        Binding.editTextGuess.setFilters(new InputFilter[]{new NumericInputFilter(), new InputFilter.LengthFilter(3)});
        FontStyle();
        UpdateCurrent();
        UpdateBest();
        Listeners();
        ObjectRotation();
    }

    private static class NumericInputFilter implements InputFilter {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!Character.isDigit(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }
    }

    private void ObjectRotation() {
        if (!isSpinInProgress) {
            Random random = new Random();
            int spinTime = 500;

            float spinAngle = random.nextFloat() * 360;

            float newRotation = currentRotation + spinAngle;

            // Normalize the rotation angle to be within the range of 0 to 360 degrees
            newRotation = (newRotation + 360) % 360;
            // Round the angle to the nearest multiple of 5
            newRotation = Math.round(newRotation / 5) * 5;

            ObjectAnimator rotation = ObjectAnimator.ofFloat(Binding.ImgPencil, "rotation", currentRotation, newRotation);
            rotation.setDuration(spinTime);
            rotation.setInterpolator(new DecelerateInterpolator());

            rotation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {
                    isSpinInProgress = true;
                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {
                    isSpinInProgress = false;

                    // Get the current rotation angle
                    float currentAngle = Binding.ImgPencil.getRotation();

                    // Normalize the current angle to be within the range of 0 to 360 degrees
                    currentRotation = (currentAngle + 360) % 360;

                    // Round the angle to the nearest whole number
                    int roundedAngle = Math.round(currentRotation);

                    // Log the rounded rotation angle
                    Log.d("AngleLog", "Current Rotation Angle: " + roundedAngle);
                }

                @Override
                public void onAnimationCancel(@NonNull Animator animation) {
                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {
                }
            });

            rotation.start();
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void submitAnswer() {
        int additionalScore;
        String guessText = Binding.editTextGuess.getText().toString().trim();


        if (!TextUtils.isEmpty(guessText)) {
            float userGuess = Float.parseFloat(guessText);
            float angleDifference = Math.abs(currentRotation % 360 - userGuess);

            Binding.CorrectAnswer.setText("Angle:" + (int) (currentRotation % 360));

            if (angleDifference <= 5) {
                if (angleDifference <= 0.5) {
                    currentScore += 2;
                    additionalScore = 2;
                } else {
                    currentScore += 1;
                    additionalScore = 1;
                }

                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    UpdateBest();
                }
                UpdateCurrent();

                Binding.DisplayGetScore.setText("+" + additionalScore + " Score");

                String ColorGreen = "#4CAF50";
                int ColorGreen2 = Color.parseColor(ColorGreen);
                Binding.AnswerStatus.setTextColor(ColorGreen2);
                Binding.AnswerStatus.setText("Correct Guess");

                Binding.ImageSubmit.setImageResource(R.drawable.start2);
                Binding.ImageSubmit.setEnabled(false);
                Binding.editTextGuess.setEnabled(false);

                HandlerDelay.postDelayed(() -> {
                    Binding.AnswerStatus.setText("Guess the Angle");
                    Binding.AnswerStatus.setTextColor(Color.WHITE);
                    Binding.CorrectAnswer.setText("of the Object.");
                    Binding.DisplayGetScore.setText("(Tip of the Object)");
                    Binding.editTextGuess.setText("");

                    Binding.ImageSubmit.setImageResource(R.drawable.start);
                    Binding.ImageSubmit.setEnabled(true);
                    Binding.editTextGuess.setEnabled(true);
                    ObjectRotation();
                }, 2000);
            } else {
                Binding.ImageSubmit.setImageResource(R.drawable.start2);
                Binding.ImageSubmit.setEnabled(false);
                Binding.editTextGuess.setEnabled(false);

                String ColorRed = "#F44336";
                int ColorRed2 = Color.parseColor(ColorRed);
                Binding.AnswerStatus.setText("Game Over");
                Binding.AnswerStatus.setTextColor(ColorRed2);
            }
        } else {
            String ColorBlue = "#B5DCFB";
            int ColorBlue2 = Color.parseColor(ColorBlue);
            Binding.AnswerStatus.setText("Empty Entry");
            Binding.CorrectAnswer.setText("");
            Binding.AnswerStatus.setTextColor(ColorBlue2);

            Binding.ImageSubmit.setImageResource(R.drawable.start2);
            Binding.ImageSubmit.setEnabled(false);
            Binding.editTextGuess.setEnabled(false);

            HandlerDelay.postDelayed(() -> {
                Binding.AnswerStatus.setText("Guess the Angle");
                Binding.AnswerStatus.setTextColor(Color.WHITE);
                Binding.CorrectAnswer.setText("of the Object.");

                Binding.ImageSubmit.setImageResource(R.drawable.start);
                Binding.ImageSubmit.setEnabled(true);
                Binding.editTextGuess.setEnabled(true);
            }, 2000);
        }
    }

    @SuppressLint("SetTextI18n")
    private void UpdateCurrent() {
        Binding.CurrentScore.setText("Score: " + currentScore);
    }

    @SuppressLint("SetTextI18n")
    private void UpdateBest() {
        Binding.BestScore.setText("Best: " + bestScore);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(BEST_SCORE_KEY, bestScore);
        editor.apply();
    }

    private void Listeners() {
        Binding.ImageSubmit.setOnClickListener(v -> submitAnswer());
        Binding.ImageRestart.setOnClickListener(v -> RestartDialog());
        Binding.ImageShop.setOnClickListener(v -> openShop());
    }
    private final ActivityResultLauncher<Intent> shopLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        int selectedSkinResource = data.getIntExtra("selectedSkinResource", 0);
                        updateImgPencil(selectedSkinResource);
                    }
                }
            });
    private void updateImgPencil(int selectedSkinResource) {
        Binding.ImgPencil.setImageResource(selectedSkinResource);
    }

    private void openShop() {
        Intent intent = new Intent(this, ShopActivity.class);
        shopLauncher.launch(intent);
    }

    private void FontStyle() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font.ttf");
        Binding.AnswerStatus.setTypeface(customFont);
        Binding.CorrectAnswer.setTypeface(customFont);
        Binding.editTextGuess.setTypeface(customFont);
        Binding.DisplayGetScore.setTypeface(customFont);
        Binding.CurrentScore.setTypeface(customFont);
        Binding.BestScore.setTypeface(customFont);
    }
    private void restoreSelectedSkin() {
        SharedPreferences gameActivityPreferences = getSharedPreferences("GamePreferences", MODE_PRIVATE);
        int selectedSkinResource = gameActivityPreferences.getInt(SELECTED_SKIN_KEY, SKIN_DEFAULT);

        // You can then use this selectedSkinResource to update the ImgPencil in GameActivity
        updateImgPencil(selectedSkinResource);
    }

    @SuppressLint("SetTextI18n")
    private void RestartGame() {
        Binding.ImageSubmit.setImageResource(R.drawable.start);
        Binding.ImageSubmit.setEnabled(true);
        Binding.editTextGuess.setEnabled(true);
        currentScore = 0;
        Binding.AnswerStatus.setText("Guess the Angle");
        Binding.AnswerStatus.setTextColor(Color.WHITE);
        Binding.CorrectAnswer.setText("of the Object.");
        Binding.editTextGuess.setText("");
        UpdateCurrent();
        UpdateBest();
        ObjectRotation();
    }

    private void RestartDialog() {
        new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
                .setTitle("Restart")
                .setMessage("Are you sure you want to restart?")
                .setPositiveButton("Yes", (dialog, which) -> RestartGame())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
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