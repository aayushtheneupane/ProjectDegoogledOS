package com.android.incallui.answer.impl.classifier;

import java.util.ArrayList;

class Stroke {
    private final float dpi;
    private long endTimeNano;
    private float length;
    private ArrayList<Point> points = new ArrayList<>();
    private long startTimeNano;

    public Stroke(long j, float f) {
        this.dpi = f;
        this.endTimeNano = j;
        this.startTimeNano = j;
    }

    public void addPoint(float f, float f2, long j) {
        this.endTimeNano = j;
        float f3 = this.dpi;
        Point point = new Point(f / f3, f2 / f3, j - this.startTimeNano);
        if (!this.points.isEmpty()) {
            float f4 = this.length;
            ArrayList<Point> arrayList = this.points;
            this.length = f4 + arrayList.get(arrayList.size() - 1).dist(point);
        }
        this.points.add(point);
    }

    public int getCount() {
        return this.points.size();
    }

    public float getDurationSeconds() {
        return ((float) (this.endTimeNano - this.startTimeNano)) / 1.0E9f;
    }

    public float getEndPointLength() {
        ArrayList<Point> arrayList = this.points;
        return this.points.get(0).dist(arrayList.get(arrayList.size() - 1));
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public float getTotalLength() {
        return this.length;
    }
}
