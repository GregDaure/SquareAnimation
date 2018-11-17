package com.houam.squareanimation;

import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.houam.squareanimationlib.AnimationType;
import com.houam.squareanimationlib.SquareLoading;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SquareLoading loading = findViewById(R.id.loading);
        final Spinner animationTypeSpinner = findViewById(R.id.spinnerAnimationType);
        final Spinner interpolatorSpinner = findViewById(R.id.spinnerInterpolator);



        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.stop();
            }
        });
        int colors[] = {0xFF8C57CA,0xFF89CCBA,0xFFFFCF35,0xFF4E4E4C};
        loading.setColors(colors);
        loading.setCustomAnimation(new MyCustomAnimation());

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String animationType = animationTypeSpinner.getSelectedItem().toString();
                String interpolator = "android.view.animation."+interpolatorSpinner.getSelectedItem().toString()+"Interpolator";
                loading.setType(AnimationType.valueOf(animationType));
                loading.setType(AnimationType.CUSTOM);
                try {
                    loading.setInterpolator((TimeInterpolator) Class.forName(interpolator).newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                loading.start();
            }
        });
    }
}
