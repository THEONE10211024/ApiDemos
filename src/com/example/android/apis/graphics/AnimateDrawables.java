/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.graphics;

import com.example.android.apis.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimateDrawables extends GraphicsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private AnimateDrawable mDrawable;
        //流程：1）先预先设定一种轨迹动画，然后让动画start，此时动画没有被付给任何的view，所以看不到运动效果。
        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            Drawable dr = context.getResources().getDrawable(R.drawable.beach);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            
            Animation an = new TranslateAnimation(0, 100, 0, 200);
            an.setDuration(2000);
            an.setRepeatCount(-1);//INFINITE
            an.initialize(10, 10, 10, 10);

            mDrawable = new AnimateDrawable(dr, an);
            an.startNow();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
//流程：2）调用AnimateDrawable的draw方法，它会去读取当前时刻的动画状态，获得动画的矩阵与当前Canvas的矩阵进行contac变化，此时再
            //绘制Bitmap的时候就会有位移。然后不断读取当前动画状态，不断进行矩阵变换，不断重绘。动画就动起来了
            mDrawable.draw(canvas);
            invalidate();
        }
    }
}

