package com.android.dialer.util;

import android.graphics.Point;

public class TouchPointManager {
    private static TouchPointManager instance = new TouchPointManager();
    private Point point = new Point();

    private TouchPointManager() {
    }

    public static TouchPointManager getInstance() {
        return instance;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(int i, int i2) {
        this.point.set(i, i2);
    }
}
