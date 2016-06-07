/*
 * Copyright (C) 2007 The Android Open Source Project
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

import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;
//以数组形式给定坐标，绘制点和直线
public class DrawPoints extends GraphicsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private Paint   mPaint = new Paint();
        private float[] mPts;

        private static final float SIZE = 300;
        private static final int SEGS = 32;
        private static final int X = 0;
        private static final int Y = 1;

        private void buildPoints() {
            final int ptCount = (SEGS + 1) * 2;//ptCount == 66;
            mPts = new float[ptCount * 2];//mPt size是132

            float value = 0;
            final float delta = SIZE / SEGS;//9.375 偏移量，即每次移动多远
            //这个for循环其实就是设定蓝色点的横纵坐标
            for (int i = 0; i <= SEGS; i++) {
                mPts[i*4 + X] = SIZE - value;
                mPts[i*4 + Y] = 0;
                mPts[i*4 + X + 2] = 0;
                mPts[i*4 + Y + 2] = value;
                value += delta;
            }
        }

        public SampleView(Context context) {
            super(context);

            buildPoints();
        }

        @Override protected void onDraw(Canvas canvas) {
            Paint paint = mPaint;

            canvas.translate(10, 10);

            canvas.drawColor(Color.WHITE);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            canvas.drawLines(mPts, paint);//画直线mPts形如[x0,y0,x1,y1,x2,y2,x3,y3....]分别在(x0,y0)和(x1,y1)、(x2,y3)和(x3,y3)之间画线

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(3);
            canvas.drawPoints(mPts, paint);//画点mPts形如[x0,y0,x1,y1,x2,y2,x3,y3....]分别绘制(x0,y0)、(x1,y1)、(x2,y3)、(x3,y3)几个点
        }
    }
}

