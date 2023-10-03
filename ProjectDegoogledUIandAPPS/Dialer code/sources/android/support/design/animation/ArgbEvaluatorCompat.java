package android.support.design.animation;

import android.animation.TypeEvaluator;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class ArgbEvaluatorCompat implements TypeEvaluator<Integer> {
    private static final ArgbEvaluatorCompat instance = new ArgbEvaluatorCompat();

    public static ArgbEvaluatorCompat getInstance() {
        return instance;
    }

    public Object evaluate(float f, Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        float f2 = ((float) ((intValue >> 24) & 255)) / 255.0f;
        int intValue2 = ((Integer) obj2).intValue();
        float pow = (float) Math.pow((double) (((float) ((intValue >> 16) & 255)) / 255.0f), 2.2d);
        float pow2 = (float) Math.pow((double) (((float) ((intValue >> 8) & 255)) / 255.0f), 2.2d);
        float pow3 = (float) Math.pow((double) (((float) (intValue & 255)) / 255.0f), 2.2d);
        float pow4 = (float) Math.pow((double) (((float) ((intValue2 >> 16) & 255)) / 255.0f), 2.2d);
        float outline0 = GeneratedOutlineSupport.outline0(((float) ((intValue2 >> 24) & 255)) / 255.0f, f2, f, f2);
        float outline02 = GeneratedOutlineSupport.outline0(pow4, pow, f, pow);
        float outline03 = GeneratedOutlineSupport.outline0((float) Math.pow((double) (((float) ((intValue2 >> 8) & 255)) / 255.0f), 2.2d), pow2, f, pow2);
        float outline04 = GeneratedOutlineSupport.outline0((float) Math.pow((double) (((float) (intValue2 & 255)) / 255.0f), 2.2d), pow3, f, pow3);
        int round = Math.round(((float) Math.pow((double) outline02, 0.45454545454545453d)) * 255.0f) << 16;
        return Integer.valueOf(Math.round(((float) Math.pow((double) outline04, 0.45454545454545453d)) * 255.0f) | round | (Math.round(outline0 * 255.0f) << 24) | (Math.round(((float) Math.pow((double) outline03, 0.45454545454545453d)) * 255.0f) << 8));
    }
}
