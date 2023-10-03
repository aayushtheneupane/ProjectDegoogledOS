package androidx.leanback.widget;

import java.util.ArrayList;
import java.util.List;

public abstract class ParallaxEffect {
    final List<Object> mMarkerValues = new ArrayList(2);
    final List<ParallaxTarget> mTargets = new ArrayList(4);
    final List<Float> mTotalWeights = new ArrayList(2);
    final List<Float> mWeights = new ArrayList(2);

    /* access modifiers changed from: package-private */
    public abstract Number calculateDirectValue(Parallax parallax);

    /* access modifiers changed from: package-private */
    public abstract float calculateFraction(Parallax parallax);

    ParallaxEffect() {
    }

    public final void performMapping(Parallax parallax) {
        if (this.mMarkerValues.size() >= 2) {
            parallax.verifyFloatProperties();
            Number number = null;
            float f = 0.0f;
            boolean z = false;
            for (int i = 0; i < this.mTargets.size(); i++) {
                ParallaxTarget parallaxTarget = this.mTargets.get(i);
                if (parallaxTarget.isDirectMapping()) {
                    if (number == null) {
                        number = calculateDirectValue(parallax);
                    }
                    parallaxTarget.directUpdate(number);
                } else {
                    if (!z) {
                        f = calculateFraction(parallax);
                        z = true;
                    }
                    parallaxTarget.update(f);
                }
            }
        }
    }
}
