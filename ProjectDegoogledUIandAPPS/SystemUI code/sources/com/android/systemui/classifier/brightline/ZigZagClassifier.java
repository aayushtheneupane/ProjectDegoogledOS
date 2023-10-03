package com.android.systemui.classifier.brightline;

import android.graphics.Point;
import android.provider.DeviceConfig;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ZigZagClassifier extends FalsingClassifier {
    private final float mMaxXPrimaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_x_primary_deviance", 0.05f);
    private final float mMaxXSecondaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_x_secondary_deviance", 0.6f);
    private final float mMaxYPrimaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_y_primary_deviance", 0.1f);
    private final float mMaxYSecondaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_y_secondary_deviance", 0.3f);

    ZigZagClassifier(FalsingDataProvider falsingDataProvider) {
        super(falsingDataProvider);
    }

    /* access modifiers changed from: package-private */
    public boolean isFalseTouch() {
        List<Point> list;
        float f;
        float f2;
        float f3;
        if (getRecentMotionEvents().size() < 3) {
            return false;
        }
        if (isHorizontal()) {
            list = rotateHorizontal();
        } else {
            list = rotateVertical();
        }
        float abs = (float) Math.abs(list.get(0).x - list.get(list.size() - 1).x);
        float abs2 = (float) Math.abs(list.get(0).y - list.get(list.size() - 1).y);
        "Actual: (" + abs + "," + abs2 + ")";
        float f4 = 0.0f;
        boolean z = true;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (Point next : list) {
            if (z) {
                f6 = (float) next.x;
                f7 = (float) next.y;
                z = false;
            } else {
                f4 += Math.abs(((float) next.x) - f6);
                f5 += Math.abs(((float) next.y) - f7);
                f6 = (float) next.x;
                f7 = (float) next.y;
                "(x, y, runningAbsDx, runningAbsDy) - (" + f6 + ", " + f7 + ", " + f4 + ", " + f5 + ")";
            }
        }
        float f8 = f4 - abs;
        float f9 = f5 - abs2;
        float xdpi = abs / getXdpi();
        float ydpi = abs2 / getYdpi();
        float sqrt = (float) Math.sqrt((double) ((xdpi * xdpi) + (ydpi * ydpi)));
        if (abs > abs2) {
            f3 = this.mMaxXPrimaryDeviance * sqrt * getXdpi();
            f2 = this.mMaxYSecondaryDeviance * sqrt;
            f = getYdpi();
        } else {
            f3 = this.mMaxXSecondaryDeviance * sqrt * getXdpi();
            f2 = this.mMaxYPrimaryDeviance * sqrt;
            f = getYdpi();
        }
        float f10 = f2 * f;
        "Straightness Deviance: (" + f8 + "," + f9 + ") vs (" + f3 + "," + f10 + ")";
        if (f8 > f3 || f9 > f10) {
            return true;
        }
        return false;
    }

    private float getAtan2LastPoint() {
        MotionEvent firstMotionEvent = getFirstMotionEvent();
        MotionEvent lastMotionEvent = getLastMotionEvent();
        float x = firstMotionEvent.getX();
        return (float) Math.atan2((double) (lastMotionEvent.getY() - firstMotionEvent.getY()), (double) (lastMotionEvent.getX() - x));
    }

    private List<Point> rotateVertical() {
        double atan2LastPoint = 1.5707963267948966d - ((double) getAtan2LastPoint());
        "Rotating to vertical by: " + atan2LastPoint;
        return rotateMotionEvents(getRecentMotionEvents(), -atan2LastPoint);
    }

    private List<Point> rotateHorizontal() {
        double atan2LastPoint = (double) getAtan2LastPoint();
        "Rotating to horizontal by: " + atan2LastPoint;
        return rotateMotionEvents(getRecentMotionEvents(), atan2LastPoint);
    }

    private List<Point> rotateMotionEvents(List<MotionEvent> list, double d) {
        List<MotionEvent> list2 = list;
        ArrayList arrayList = new ArrayList();
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        MotionEvent motionEvent = list2.get(0);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (Iterator<MotionEvent> it = list.iterator(); it.hasNext(); it = it) {
            MotionEvent next = it.next();
            double x2 = (double) (next.getX() - x);
            double y2 = (double) (next.getY() - y);
            arrayList.add(new Point((int) ((cos * x2) + (sin * y2) + ((double) x)), (int) (((-sin) * x2) + (y2 * cos) + ((double) y))));
            motionEvent = motionEvent;
        }
        MotionEvent motionEvent2 = motionEvent;
        MotionEvent motionEvent3 = list2.get(list.size() - 1);
        Point point = (Point) arrayList.get(0);
        Point point2 = (Point) arrayList.get(arrayList.size() - 1);
        "Before: (" + motionEvent2.getX() + "," + motionEvent2.getY() + "), (" + motionEvent3.getX() + "," + motionEvent3.getY() + ")";
        "After: (" + point.x + "," + point.y + "), (" + point2.x + "," + point2.y + ")";
        return arrayList;
    }
}
