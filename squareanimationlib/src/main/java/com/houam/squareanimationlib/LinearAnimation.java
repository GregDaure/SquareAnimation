package com.houam.squareanimationlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class LinearAnimation extends Animation {

    @Override
    protected void createAnimatorSets(View[] views){
        getSets().clear();
        for(View v : views){
            getSets().add(createAnimationLinear(v));
        }
    }

    private AnimatorSet createAnimationLinear(View v){
        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(v, "translationX", -getSpacement());
        animatorX1.setRepeatMode(ValueAnimator.REVERSE);
        animatorX1.setRepeatCount(1);

        ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(v, "translationX", getSpacement());
        animatorX2.setRepeatMode(ValueAnimator.REVERSE);
        animatorX2.setRepeatCount(1);

        ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(v, "translationY", -getSpacement());
        animatorY1.setRepeatMode(ValueAnimator.REVERSE);
        animatorY1.setRepeatCount(1);

        ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(v, "translationY", getSpacement());
        animatorY2.setRepeatMode(ValueAnimator.REVERSE);
        animatorY2.setRepeatCount(1);

        final AnimatorSet set = new AnimatorSet();
        if (v == getViews()[0]){
            set.playTogether(animatorX1,animatorY1);
        }

        if (v == getViews()[1]){
            set.playTogether(animatorX2,animatorY1);
        }

        if (v == getViews()[2]){
            set.playTogether(animatorX1,animatorY2);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    repeat();
                }
            });
        }

        if (v == getViews()[3]){
            set.playTogether(animatorX2,animatorY2);
        }

        return set;
    }}
