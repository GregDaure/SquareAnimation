package com.houam.squareanimationlib;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

public abstract class Animation {

    private float spacement;
    private long duration;
    private long delay;
    private long startDelay;
    private TimeInterpolator interpolator;
    private View[] views;
    private List<AnimatorSet> sets;
    private int[] colors;

    private boolean stopped; // false
    private boolean started; // false

    public Animation(){
        this.sets = new ArrayList<>();

        /**
         * Default values
         * {@link spacement}
         * {@link duration}
         * {@link delay}
         * {@link startDelay}
         * {@link interpolator}
         */
        setSpacement(50.0F);
        setStartDelay(200);
        setDelay(150);
        setDuration(600);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void setSpacement(float spacement){
        this.spacement = spacement;
    }

    public float getSpacement(){
        return this.spacement;
    }

    public View[] getViews(){
        return this.views;
    }

    public void setDuration(long duration){
        this.duration = duration;
    }

    public long getDuration(){
        return this.duration;
    }

    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(@NonNull TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    protected abstract void createAnimatorSets(View[] views);

    public void repeat(){
        if (isStopped()){
            stopped = false;
            started = false;
            return;
        }
        Log.d("SquareLoading", "Animation repeated!");
        runAnimator();
    }

    public void start(){
        started = true;
        createAnimatorSets(getViews());
        setAnimatorAttributes();
        runAnimator();
        Log.d("SquareLoading", "Animation started!");
    }

    public void stopAtEnd(){
        Log.d("SquareLoading", "Animation stopped!");
        stopped = true;
    }

    private void runAnimator(){
        for (AnimatorSet as : getSets()){
            as.start();
        }
    }

    private void setAnimatorAttributes(){
        for (AnimatorSet as : getSets()){
            as.setInterpolator(getInterpolator());
            as.setDuration(getDuration());
        }
        setAnimatorsDelay();
    }

    protected void setAnimatorsDelay(){
        long startDelay = getStartDelay() <= 0 ? 1 : getStartDelay(); //Start delay cannot be nul or negative
        getSets().get(0).setStartDelay(startDelay);
        getSets().get(1).setStartDelay(getDelay()+startDelay);
        getSets().get(2).setStartDelay(getDelay()*3+startDelay);
        getSets().get(3).setStartDelay(getDelay()*2+startDelay);
    }

    public List<AnimatorSet> getSets() {
        return sets;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        if (colors == null){
            return;
        }
        if (colors.length != 4){
            throw new IllegalArgumentException("Required array size : 4 ; Found array size : " + colors.length);
        }
        this.colors = colors;
        for (int i = 0; i < colors.length; i++){
            getViews()[i].setBackgroundColor(colors[i]);
        }
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isStopped() {
        return stopped;
    }

    public long getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(long startDelay) {
        this.startDelay = startDelay;
    }

    public void setViews(View[] views) {
        this.views = views;
    }
}
