package Angle.Guess.Repository;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

public class Animations {

    public Animations(Context context) {
    }
    public void getAlphaAnimation(final View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.start();
    }
    public void getFadingAnimation(final View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.start();
    }
    public void getArrowAnimation(final View view) {

        ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, "translationX", 0f, 25f, 0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(ObjectAnimator.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateAnimation);
        animatorSet.start();
    }
    public void TextBlink(final View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); // Set the duration of each half (blink up and blink down)
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);
    }
    public void getLeftAnimation(final View view) {
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels; // Get the width of the screen
        view.setTranslationX(-screenWidth); // Move the view outside the left side of the screen
        ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -screenWidth, 0f);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0.1f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateAnimation, alphaAnimation);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
    public void getRightAnimation(final View view) {
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels; // Get the width of the screen
        view.setTranslationX(screenWidth); // Move the view outside the left side of the screen
        ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, screenWidth, 0f);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0.1f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateAnimation, alphaAnimation);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
    public void getTopAnimation(final View view) {
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels; // Get the width of the screen
        view.setTranslationY(-screenHeight); // Move the view outside the left side of the screen
        ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -screenHeight, 0f);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0.1f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateAnimation, alphaAnimation);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
    public void getRotatingAnimation(final View view) {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(view, "rotation", -1.1f, 1.1f, -1.1f);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.start();
    }
    public void get360RotatingAnimation(final View view) {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(view, "rotation", -360f, 0f, 360f);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.start();
    }
    public void getInOutAnimation(final View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f, 1f);
        scaleX.setDuration(2000);
        scaleY.setDuration(2000);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleY.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleX.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY.setRepeatCount(ObjectAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }
    public void getFullScaleAnimation(final View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);
        alphaAnimation.setDuration(1000);
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(view, "rotation", -360f, 0f, 360f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 5f);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, alphaAnimation, rotateAnimation);
        animatorSet.start();
    }
    public void getScaleAnimation(final View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f);
        scaleX.setDuration(700);
        scaleY.setDuration(700);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }

}
