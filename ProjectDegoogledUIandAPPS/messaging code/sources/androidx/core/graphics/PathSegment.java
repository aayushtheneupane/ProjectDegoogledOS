package androidx.core.graphics;

import android.graphics.PointF;
import androidx.core.util.Preconditions;
import p026b.p027a.p030b.p031a.C0632a;

public final class PathSegment {
    private final PointF mEnd;
    private final float mEndFraction;
    private final PointF mStart;
    private final float mStartFraction;

    public PathSegment(PointF pointF, float f, PointF pointF2, float f2) {
        Preconditions.checkNotNull(pointF, "start == null");
        this.mStart = pointF;
        this.mStartFraction = f;
        Preconditions.checkNotNull(pointF2, "end == null");
        this.mEnd = pointF2;
        this.mEndFraction = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        if (Float.compare(this.mStartFraction, pathSegment.mStartFraction) != 0 || Float.compare(this.mEndFraction, pathSegment.mEndFraction) != 0 || !this.mStart.equals(pathSegment.mStart) || !this.mEnd.equals(pathSegment.mEnd)) {
            return false;
        }
        return true;
    }

    public PointF getEnd() {
        return this.mEnd;
    }

    public float getEndFraction() {
        return this.mEndFraction;
    }

    public PointF getStart() {
        return this.mStart;
    }

    public float getStartFraction() {
        return this.mStartFraction;
    }

    public int hashCode() {
        int hashCode = this.mStart.hashCode() * 31;
        float f = this.mStartFraction;
        int i = 0;
        int hashCode2 = (this.mEnd.hashCode() + ((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31)) * 31;
        float f2 = this.mEndFraction;
        if (f2 != 0.0f) {
            i = Float.floatToIntBits(f2);
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("PathSegment{start=");
        Pa.append(this.mStart);
        Pa.append(", startFraction=");
        Pa.append(this.mStartFraction);
        Pa.append(", end=");
        Pa.append(this.mEnd);
        Pa.append(", endFraction=");
        Pa.append(this.mEndFraction);
        Pa.append('}');
        return Pa.toString();
    }
}
