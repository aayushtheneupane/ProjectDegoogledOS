package com.android.incallui.answer.impl.classifier;

class Point {
    public long timeOffsetNano;

    /* renamed from: x */
    public float f40x;

    /* renamed from: y */
    public float f41y;

    public Point(float f, float f2) {
        this.f40x = f;
        this.f41y = f2;
        this.timeOffsetNano = 0;
    }

    public float dist(Point point) {
        return (float) Math.hypot((double) (point.f40x - this.f40x), (double) (point.f41y - this.f41y));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        if (this.f40x == point.f40x && this.f41y == point.f41y) {
            return true;
        }
        return false;
    }

    public float getAngle(Point point, Point point2) {
        float dist = dist(point);
        float dist2 = dist(point2);
        if (dist == 0.0f || dist2 == 0.0f) {
            return 0.0f;
        }
        float f = point.f40x;
        float f2 = this.f40x;
        float f3 = point2.f41y;
        float f4 = this.f41y;
        float f5 = f3 - f4;
        float f6 = point.f41y;
        float f7 = point2.f40x;
        float f8 = f7 - f2;
        float acos = (float) Math.acos((double) Math.min(1.0f, Math.max(-1.0f, ((((f3 - f4) * (f6 - f4)) + ((f7 - f2) * (f - f2))) / dist) / dist2)));
        return ((double) ((f5 * (f - f2)) - (f8 * (f6 - f4)))) < 0.0d ? 6.2831855f - acos : acos;
    }

    public int hashCode() {
        float f = this.f40x;
        int i = 0;
        int floatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.f41y;
        if (f2 != 0.0f) {
            i = Float.floatToIntBits(f2);
        }
        return floatToIntBits + i;
    }

    public Point(float f, float f2, long j) {
        this.f40x = f;
        this.f41y = f2;
        this.timeOffsetNano = j;
    }
}
