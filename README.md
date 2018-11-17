
# SquareAnimation
<a target="_blank" href="https://developer.android.com/reference/android/os/Build.VERSION_CODES.html#ICE_CREAM_SANDWICH"><img src="https://img.shields.io/badge/API-15%2B-blue.svg?style=flat" alt="API" /></a> [![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/greg6614/SquareAnimation/issues) [![Release](https://jitpack.io/v/greg6614/SquareAnimation.svg)](https://jitpack.io/#greg6614/SquareAnimation)

A simple android library to create nice SquareAnimation.
Built with [`ObjectAnimator`](https://developer.android.com/reference/android/animation/ObjectAnimator) introduced in API 11.


## Screenshots

I will post more screenshots of the demo app as soon as possible.

![](https://media.giphy.com/media/Xtg8K1d5bdnXsuRj2g/giphy.gif) ![](https://media.giphy.com/media/7YDsqjFPAvcQeiRwPJ/giphy.gif)
![](https://media.giphy.com/media/9Vi7EFVOQNtOd9HUHW/giphy.gif) ![](https://media.giphy.com/media/yx5D6W4BtzrwJqG86c/giphy.gif)
![](https://media.giphy.com/media/fituCrArGTqcdic0OV/giphy.gif)

## Usage

Add the `SquareLoading` to your layout XML:

```xml
<?xml version="1.0" encoding="utf-8"?>  
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:tools="http://schemas.android.com/tools"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  tools:context=".MainActivity">  
  
	<com.houam.squareanimationlib.SquareLoading  
	android:id="@+id/loading"  
	android:layout_width="wrap_content"  
	android:layout_height="wrap_content"  
	android:layout_centerInParent="true"/>  
  
 ...
</RelativeLayout>
```
There's not yet customs attributes for customize the `SquareLoading` component. It will be added in a future release.

However you can customize it via your code directly:

```java
SquareLoading loading = findViewById(R.id.loading);  
  
loading.setSpacement(60);  
loading.setDuration(1000);  
loading.setDelay(200);  
loading.setInterpolator(new AccelerateDecelerateInterpolator());  
loading.setType(AnimationType.LINEAR);
```

| Method name        |Parameters        |Default value| Description                                                                                    |
|:------------------:|:----------------:|:-------------:|------------------------------------------------------------------------------------------------|
| `setSpacement()`   |`float`           |`50.0F`|Translation distance. e.g. `setSpacement(60)` will result in a translation animation of 60 on axis X and Y of each squares |
|`setDuration()`     | `long`           |`600`| Duration of the animation of each squares. It means that a complete animation from sqaures 1 to 4 is tihs value multiplied by 4.
|`setDelay()`        |`long`            |`150`|Delay in millis between each square animation. e.g. `setDelay(150)` tells `square2` to start 150ms after `square1` etc...
|`setInterpolator()` |`TimeInterpolator`|`AccelerateDecelerateInterpolator`|Defines the time interpolator to be used. See below [Interpolator list](#interpolator).
|`setType()`         |`AnimationType`   |`AnimationType.LINEAR` |The animation type to be used. See below [AnimationType](#animationtype).
|`setColors()`	     |`int[]`           |`{0xFF1565c0,0xFF1976d2,0xFF1e88e5,0xFF2196f3}`|The colors of each squares.    

For further doumentation about how to use the library, check the [demo](https://github.com/greg6614/SquareAnimation/tree/master/app) app included in this project. 

## <a href="interpolator"></a>Interpolator

The default <a href="https://developer.android.com/reference/android/animation/TimeInterpolator">`TimeInterpolator`</a> is <a href="https://developer.android.com/reference/android/view/animation/AccelerateDecelerateInterpolator">`AccelerateDecelerateInterpolator`</a>. You can change this behavior by using one of the interpolator below:

 - <a href="https://developer.android.com/reference/android/view/animation/AccelerateDecelerateInterpolator">AccelerateDecelerateInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/AccelerateInterpolator">AccelerateInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/AnticipateInterpolator">AnticipateInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/AnticipateOvershootInterpolator">AnticipateOvershootInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/BounceInterpolator">BounceInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/DecelerateInterpolator">DecelerateInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/LinearInterpolator">LinearInterpolator</a>
 - <a href="https://developer.android.com/reference/android/view/animation/OvershootInterpolator">OvershootInterpolator</a>
 
 You can also create your own by implementing the `Interpolator` interface and by overriding needed methods.

## <a href="animationtype"></a>AnimationType

The library provide an enum called `AnimationType` to choose wich type of animation will be played. Here's the list of availables `AnimationType`:

- `AnimationType.LINEAR` - The default one. Simple diagonal translation based on `getSpacement()` value.
- `AnimationType.SEMI_LINEAR` - Same as `AnimationType.LINEAR`but based on `getSpacement()/2` value. 
- `AnimationType.ROTATION` - Simple diagonal translation with 360 degrees rotation.
- `AnimationType.SEMI_ROTATION` - Same as `AnimationType.ROTATION` but with 180 degrees rotation.
- `AnimationType.BOUNCE` - Combined `AnimationType.LINEAR` and `AnimationType.SEMI_LINEAR`.

## Download

Add the jitpack.io repository in your root build.gradle:
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency in your app module build.gradle:

```groovy
dependencies {
	        implementation 'com.github.greg6614:SquareAnimation:1.1.2'
	}
```

