package com.houam.squareanimationlib;

import android.animation.AnimatorSet;
import android.view.View;

public abstract class CustomAnimation extends Animation {

    @Override
    protected void createAnimatorSets(View[] views) {
        getSets().clear();
        for(View v : views){
            getSets().add(createAnimation(v));
        }
    }

    public abstract AnimatorSet createAnimation(View v);
}
