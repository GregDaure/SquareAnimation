package com.houam.squareanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.houam.squareanimationlib.CustomAnimation;

public class MyCustomAnimation extends CustomAnimation {


    @Override
    public AnimatorSet createAnimation(View v) {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator animator = null;

        if (v == getViews()[0]) { //First square
            animator = ObjectAnimator.ofFloat(v, "translationX", -100);
        }

        if (v == getViews()[1]) { //Second square
            animator = ObjectAnimator.ofFloat(v, "translationX", 100);
        }

        if (v == getViews()[2]) { //Fourth square
            animator = ObjectAnimator.ofFloat(v, "translationY", 100);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    repeat();
                }
            });
        }

        if (v == getViews()[3]) { //Third square
            animator = ObjectAnimator.ofFloat(v, "translationY", 100);
        }

        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        set.play(animator);

        return set;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public long getStartDelay() {
        return 0;
    }
}
