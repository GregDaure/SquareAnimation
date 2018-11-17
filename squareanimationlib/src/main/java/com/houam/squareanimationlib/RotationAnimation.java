package com.houam.squareanimationlib;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class RotationAnimation extends LinearAnimation {

    @Override
    protected void createAnimatorSets(View[] views) {
        super.createAnimatorSets(views);
        for (int i = 0; i < views.length; i++){
            createAnimationRotation(views[i],getSets().get(i));
        }
    }

    private void createAnimationRotation(View v, AnimatorSet as){
        ObjectAnimator animatorR = ObjectAnimator.ofFloat(v, "rotation", 0,  getRotation());
        as.play(animatorR);
    }

    protected int getRotation(){
        return 360;
    }


}
