package com.houam.squareanimationlib;

import android.view.View;

public class SemiLinearAnimation extends LinearAnimation {

    @Override
    protected void createAnimatorSets(View[] views) {
        super.createAnimatorSets(views);
    }

    @Override
    public float getSpacement() {
        return super.getSpacement()/2;
    }
}
