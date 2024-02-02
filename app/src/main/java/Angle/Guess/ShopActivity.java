package Angle.Guess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import Angle.Guess.Repository.Animations;
import Angle.Guess.databinding.ActivityShopBinding;

public class ShopActivity extends AppCompatActivity {
    private ActivityShopBinding binding;
    private static final String SELECTED_SKIN_KEY = "selectedSkin";
    private SharedPreferences sharedPreferences;

    public static final int SKIN_DEFAULT = R.drawable.pencil;
    public static final int SKIN_SKIN1 = R.drawable.pen;
    public static final int SKIN_SKIN2 = R.drawable.marker;
    public static final int SKIN_SKIN3 = R.drawable.knife;
    public static final int SKIN_SKIN4 = R.drawable.bow;
    public static final int SKIN_SKIN5 = R.drawable.screw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animations theAnimation = new Animations(this);
        theAnimation.getRotatingAnimation(binding.NavBar);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        LoadApp();
    }

    private void LoadApp() {
        FontStyle();
        binding.skinDefault.setEnabled(false);
        binding.skin1.setEnabled(true);
        binding.skin2.setEnabled(true);
        binding.skin3.setEnabled(true);
        binding.skin4.setEnabled(true);
        binding.skin5.setEnabled(true);
        restoreSelectedSkin();
        BtnListener();
    }

    private void restoreSelectedSkin() {
        int selectedSkinResource = sharedPreferences.getInt(SELECTED_SKIN_KEY, SKIN_DEFAULT);
        if (selectedSkinResource == SKIN_DEFAULT) {
            updateButtonState(binding.skinDefault, SKIN_DEFAULT);
        } else if (selectedSkinResource == SKIN_SKIN1) {
            updateButtonState(binding.skin1, SKIN_SKIN1);
        } else if (selectedSkinResource == SKIN_SKIN2) {
            updateButtonState(binding.skin2, SKIN_SKIN2);
        } else if (selectedSkinResource == SKIN_SKIN3) {
            updateButtonState(binding.skin3, SKIN_SKIN3);
        } else if (selectedSkinResource == SKIN_SKIN4) {
            updateButtonState(binding.skin4, SKIN_SKIN4);
        } else if (selectedSkinResource == SKIN_SKIN5) {
            updateButtonState(binding.skin5, SKIN_SKIN5);
        }
    }

    private void BtnListener() {
        binding.skinDefault.setOnClickListener(v -> updateButtonState(binding.skinDefault, SKIN_DEFAULT));
        binding.skin1.setOnClickListener(v -> updateButtonState(binding.skin1, SKIN_SKIN1));
        binding.skin2.setOnClickListener(v -> updateButtonState(binding.skin2, SKIN_SKIN2));
        binding.skin3.setOnClickListener(v -> updateButtonState(binding.skin3, SKIN_SKIN3));
        binding.skin4.setOnClickListener(v -> updateButtonState(binding.skin4, SKIN_SKIN4));
        binding.skin5.setOnClickListener(v -> updateButtonState(binding.skin5, SKIN_SKIN5));
    }


    @SuppressLint("SetTextI18n")
    private void updateButtonState(Button button, int selectedSkinResource) {
        button.setEnabled(false);
        button.setText("Equipped");
        button.setTextColor(Color.WHITE);
        button.setBackgroundResource(R.drawable.btn_status);
        Drawable backgroundDrawable = button.getBackground();
        if (backgroundDrawable != null) {
            backgroundDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        }

        Button otherButton1, otherButton2, otherButton3, otherButton4, otherButton5;
        if (button == binding.skinDefault) {
            otherButton1 = binding.skin1;
            otherButton2 = binding.skin2;
            otherButton3 = binding.skin3;
            otherButton4 = binding.skin4;
            otherButton5 = binding.skin5;
        } else if (button == binding.skin1) {
            otherButton1 = binding.skinDefault;
            otherButton2 = binding.skin2;
            otherButton3 = binding.skin3;
            otherButton4 = binding.skin4;
            otherButton5 = binding.skin5;
        } else if (button == binding.skin2) {
            otherButton1 = binding.skinDefault;
            otherButton2 = binding.skin1;
            otherButton3 = binding.skin3;
            otherButton4 = binding.skin4;
            otherButton5 = binding.skin5;
        } else if (button == binding.skin3) {
            otherButton1 = binding.skinDefault;
            otherButton2 = binding.skin1;
            otherButton3 = binding.skin2;
            otherButton4 = binding.skin4;
            otherButton5 = binding.skin5;
        } else if (button == binding.skin4) {
            otherButton1 = binding.skinDefault;
            otherButton2 = binding.skin1;
            otherButton3 = binding.skin2;
            otherButton4 = binding.skin3;
            otherButton5 = binding.skin5;
        } else if (button == binding.skin5) {
            otherButton1 = binding.skinDefault;
            otherButton2 = binding.skin1;
            otherButton3 = binding.skin2;
            otherButton4 = binding.skin3;
            otherButton5 = binding.skin4;
        } else {
            otherButton1 = binding.skin1;
            otherButton2 = binding.skin2;
            otherButton3 = binding.skin3;
            otherButton4 = binding.skin4;
            otherButton5 = binding.skin5;
        }

        otherButton1.setEnabled(true);
        otherButton1.setText("Equip");
        otherButton1.setTextColor(Color.BLACK);
        otherButton1.setBackgroundResource(R.drawable.btn_status);
        Drawable otherBackgroundDrawable1 = otherButton1.getBackground();
        if (otherBackgroundDrawable1 != null) {
            otherBackgroundDrawable1.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        otherButton2.setEnabled(true);
        otherButton2.setText("Equip");
        otherButton2.setTextColor(Color.BLACK);
        otherButton2.setBackgroundResource(R.drawable.btn_status);
        Drawable otherBackgroundDrawable2 = otherButton2.getBackground();
        if (otherBackgroundDrawable2 != null) {
            otherBackgroundDrawable2.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        otherButton3.setEnabled(true);
        otherButton3.setText("Equip");
        otherButton3.setTextColor(Color.BLACK);
        otherButton3.setBackgroundResource(R.drawable.btn_status);
        Drawable otherBackgroundDrawable3 = otherButton3.getBackground();
        if (otherBackgroundDrawable3 != null) {
            otherBackgroundDrawable3.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        otherButton4.setEnabled(true);
        otherButton4.setText("Equip");
        otherButton4.setTextColor(Color.BLACK);
        otherButton4.setBackgroundResource(R.drawable.btn_status);
        Drawable otherBackgroundDrawable4 = otherButton4.getBackground();
        if (otherBackgroundDrawable4 != null) {
            otherBackgroundDrawable4.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        otherButton5.setEnabled(true);
        otherButton5.setText("Equip");
        otherButton5.setTextColor(Color.BLACK);
        otherButton5.setBackgroundResource(R.drawable.btn_status);
        Drawable otherBackgroundDrawable5 = otherButton5.getBackground();
        if (otherBackgroundDrawable5 != null) {
            otherBackgroundDrawable5.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        saveSelectedSkin(selectedSkinResource);
        onSkinSelected(selectedSkinResource);
    }


    private void saveSelectedSkin(int selectedSkinResource) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECTED_SKIN_KEY, selectedSkinResource);
        editor.apply();

        // Also save in the GameActivity preferences
        SharedPreferences gameActivityPreferences = getSharedPreferences("GamePreferences", MODE_PRIVATE);
        SharedPreferences.Editor gameActivityEditor = gameActivityPreferences.edit();
        gameActivityEditor.putInt(SELECTED_SKIN_KEY, selectedSkinResource);
        gameActivityEditor.apply();
    }

    @SuppressLint("SetTextI18n")
    private void onSkinSelected(int selectedSkinResource) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedSkinResource", selectedSkinResource);
        setResult(RESULT_OK, resultIntent);
    }
    private void FontStyle() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font.ttf");
        binding.skinDefault.setTypeface(customFont);
        binding.skinDefaultText.setTypeface(customFont);
        binding.skin1.setTypeface(customFont);
        binding.skin1Text.setTypeface(customFont);
        binding.skin2.setTypeface(customFont);
        binding.skin2Text.setTypeface(customFont);
        binding.skin3.setTypeface(customFont);
        binding.skin3Text.setTypeface(customFont);
        binding.skin4.setTypeface(customFont);
        binding.skin4Text.setTypeface(customFont);
        binding.skin5.setTypeface(customFont);
        binding.skin5Text.setTypeface(customFont);
    }
}
