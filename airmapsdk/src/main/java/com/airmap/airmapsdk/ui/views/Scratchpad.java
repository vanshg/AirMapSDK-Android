package com.airmap.airmapsdk.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vansh Gandhi on 10/30/16.
 * Copyright © 2016 AirMap, Inc. All rights reserved.
 *
 * This view is used to draw the dotted lines when adjusting points
 */

public class Scratchpad extends View {

    private Canvas canvas;
    private Paint paint;
    private Path path;
    private List<Path> disconnectedPaths;

    public Scratchpad(Context context) {
        super(context);
    }

    public Scratchpad(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Scratchpad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(7);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{25, 30}, 0));
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas = new Canvas();
        path = new Path();
        disconnectedPaths = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        for (Path path : disconnectedPaths) {
            canvas.drawPath(path, paint);
        }
    }

    public void dragTo(PointF start, PointF middle, PointF end) {
        reset();
        path.moveTo(start.x, start.y);
        path.lineTo(middle.x, middle.y);
        path.lineTo(end.x, end.y);
        canvas.drawPath(path, paint);
        invalidate();
    }

    public void dragTo(PointF start, PointF end) {
        reset();
        path.moveTo(start.x, start.y);
        path.lineTo(end.x, end.y);
        canvas.drawPath(path, paint);
        invalidate();
    }

    public void drawShape(List<PointF> shape) {
        reset();
        if (shape.isEmpty()) return;
        path.moveTo(shape.get(0).x, shape.get(0).y);
        for (PointF point : shape) {
            path.lineTo(point.x, point.y);
        }
        canvas.drawPath(path, paint);
        invalidate();
    }

    public void drawShapeDisconnected(List<PointF> shape) {
        if (shape.isEmpty()) return;
        Path path = new Path();
        path.moveTo(shape.get(0).x, shape.get(0).y);
        for (PointF point : shape) {
            path.lineTo(point.x, point.y);
        }
        canvas.drawPath(path, paint);
        disconnectedPaths.add(path);
        invalidate();
    }

    public void reset() {
        path.reset();
        for (Path path : disconnectedPaths) {
            path.reset();
        }
        disconnectedPaths.clear();
    }
}
