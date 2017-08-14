package com.gotech.tv.launcher.anim;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

public class AnimationMagnify {
    // private static final String TAG = "AnimationMagnify";
    private static AnimationSet mAnimationSet;

    public static void Magnify(View view, float fl) {
        AnimationSet animationSet = new AnimationSet(true);
        if (mAnimationSet != null && mAnimationSet != animationSet) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 1f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, // 使用动画播放图片
                    Animation.RELATIVE_TO_PARENT, 0.5f);
            scaleAnimation.setDuration(50);
            mAnimationSet.addAnimation(scaleAnimation);
            mAnimationSet.setFillAfter(false); // 让其保持动画结束时的状态。
            view.startAnimation(mAnimationSet);
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, fl, 1, fl, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(50);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        AccelerateInterpolator localAccelerateInterpolator = new AccelerateInterpolator();
        animationSet.setInterpolator(localAccelerateInterpolator);
        view.startAnimation(animationSet);
        mAnimationSet = animationSet;
    }

    public static void backView(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
        scaleAnimation.setDuration(50);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
        mAnimationSet = animationSet;
    }

}
