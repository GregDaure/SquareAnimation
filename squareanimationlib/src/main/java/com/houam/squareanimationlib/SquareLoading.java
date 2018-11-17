package com.houam.squareanimationlib;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class SquareLoading extends RelativeLayout {

    //AnimationType to be played
    private AnimationType type = AnimationType.LINEAR;
    private View[] views;

    private Animation animation;
    private Animation custom;

    public SquareLoading(Context context) {
        super(context);
        init();
    }

    public SquareLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View squareLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.squareloading,this, true);
        View[] views = {squareLoadingView.findViewById(R.id.square1),
                        squareLoadingView.findViewById(R.id.square2),
                        squareLoadingView.findViewById(R.id.square3),
                        squareLoadingView.findViewById(R.id.square4)};
        this.views = views;
        this.animation = new LinearAnimation();
        this.animation.setViews(views);

    }

    public void stop(){
        animation.stopAtEnd();
    }

    private void initAnimationWithAttributes(Animation oldOne, Animation newOne){
        long duration = oldOne.getDuration();
        long delay = oldOne.getDelay();
        long startDelay = oldOne.getStartDelay();
        int[] colors = oldOne.getColors();
        TimeInterpolator interpolator = oldOne.getInterpolator();

        newOne.setViews(views);
        newOne.setDuration(duration);
        newOne.setDelay(delay);
        newOne.setStartDelay(startDelay);
        newOne.setColors(colors);
        newOne.setInterpolator(interpolator);

        animation = newOne;
    }

    public void start(){
        if (animation.isStarted()){ /**An animation is already running. Abort*/
            return;
        }

        switch (type){
            case LINEAR:{
                 initAnimationWithAttributes(animation, new LinearAnimation());
                break;
            }
            case SEMI_LINEAR:{
                initAnimationWithAttributes(animation, new SemiLinearAnimation());
                break;
            }
            case ROTATION:{
                initAnimationWithAttributes(animation, new RotationAnimation());
                break;
            }
            case SEMI_ROTATION:{
                initAnimationWithAttributes(animation, new SemiRotationAnimation());
                break;
            }
            case BOUNCE:{
                initAnimationWithAttributes(animation, new BounceAnimation());
                break;
            }
            case TRANSLATION:{
                initAnimationWithAttributes(animation, new TranslationAnimation());
                break;
            }
            case CUSTOM:{
                initAnimationWithAttributes(animation, getCustomAnimation());
                break;
            }
        }
        animation.start();
    }

    public void setType(AnimationType type){
        if (type == AnimationType.CUSTOM && custom == null){
            throw new IllegalArgumentException("You must call setCustomAnimation() method before");
        }
        this.type = type;
    }

    public void setInterpolator(TimeInterpolator interpolator){
        animation.setInterpolator(interpolator);
    }

    public void setDuration(long duration){
        animation.setDuration(duration);
    }

    public void setDelay(long delay){
        animation.setDelay(delay);
    }

    public void setStartDelay(long startDelay){
        animation.setStartDelay(startDelay);
    }

    public void setSpacement(float spacement){
        animation.setSpacement(spacement);
    }

    public void setColors(int[] colors){
        animation.setColors(colors);
    }

    public Animation getCustomAnimation() {
        return custom;
    }

    public void setCustomAnimation(CustomAnimation custom) {
        this.custom = custom;
    }
}
