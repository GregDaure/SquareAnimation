package com.houam.squareanimationlib;

public class BounceAnimation extends LinearAnimation {
    private boolean big = true;

    @Override
    public void repeat() {
        big = !big;
        createAnimatorSets(getViews());
        setAnimatorsDelay();
        super.repeat();
    }

    @Override
    public float getSpacement() {
        return big ? super.getSpacement() : super.getSpacement()/2;
    }
}
