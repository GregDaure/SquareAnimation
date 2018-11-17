package com.houam.squareanimationlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.RelativeLayout;

public class TranslationAnimation extends Animation {

    private int value;

    @Override
    protected void createAnimatorSets(View[] views) {
        getSets().clear();
        for(View v : views){
            getSets().add(createTranslationAnimation(v));
        }
    }

    private AnimatorSet createTranslationAnimation(View v){
        if (v == getViews()[0]){
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) v.getLayoutParams();
            value = v.getWidth() + lp.rightMargin;
        }

        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(v, "translationX", value);
        ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(v, "translationX", -value);
        ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(v, "translationY", -value);
        ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(v, "translationY", value);

        ObjectAnimator animatorDefaultX = ObjectAnimator.ofFloat(v, "translationX", 0);
        ObjectAnimator animatorDefaultY = ObjectAnimator.ofFloat(v, "translationY", 0);

        final AnimatorSet set = new AnimatorSet();

        if (v == getViews()[0]){
            set.playSequentially(animatorX1, animatorY2, animatorDefaultX, animatorDefaultY);
        }

        if (v == getViews()[1]){
            set.playSequentially(animatorY2, animatorX2, animatorDefaultY, animatorDefaultX);
        }

        if (v == getViews()[2]){
            set.playSequentially(animatorY1, animatorX1, animatorDefaultY, animatorDefaultX);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    repeat();
                }
            });
        }

        if (v == getViews()[3]){
            set.playSequentially(animatorX2, animatorY1, animatorDefaultX, animatorDefaultY);

        }

        return set;
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
