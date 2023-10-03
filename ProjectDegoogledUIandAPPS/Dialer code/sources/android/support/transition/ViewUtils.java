package android.support.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class ViewUtils {
    private static final ViewUtilsBase IMPL = new ViewUtilsApi22();
    static final Property<View, Float> TRANSITION_ALPHA = new Property<View, Float>(Float.class, "translationAlpha") {
        public Object get(Object obj) {
            return Float.valueOf(ViewUtils.getTransitionAlpha((View) obj));
        }

        public void set(Object obj, Object obj2) {
            ViewUtils.setTransitionAlpha((View) obj, ((Float) obj2).floatValue());
        }
    };
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    static {
        int i = Build.VERSION.SDK_INT;
        new Property<View, Rect>(Rect.class, "clipBounds") {
            public Object get(Object obj) {
                return ViewCompat.getClipBounds((View) obj);
            }

            public void set(Object obj, Object obj2) {
                ViewCompat.setClipBounds((View) obj, (Rect) obj2);
            }
        };
    }

    static void clearNonTransitionAlpha(View view) {
        IMPL.clearNonTransitionAlpha(view);
    }

    static ViewOverlayImpl getOverlay(View view) {
        int i = Build.VERSION.SDK_INT;
        return new ViewOverlayApi18(view);
    }

    static float getTransitionAlpha(View view) {
        return IMPL.getTransitionAlpha(view);
    }

    static WindowIdImpl getWindowId(View view) {
        int i = Build.VERSION.SDK_INT;
        return new WindowIdApi18(view);
    }

    static void saveNonTransitionAlpha(View view) {
        IMPL.saveNonTransitionAlpha(view);
    }

    static void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        IMPL.setLeftTopRightBottom(view, i, i2, i3, i4);
    }

    static void setTransitionAlpha(View view, float f) {
        IMPL.setTransitionAlpha(view, f);
    }

    static void setTransitionVisibility(View view, int i) {
        if (!sViewFlagsFieldFetched) {
            try {
                sViewFlagsField = View.class.getDeclaredField("mViewFlags");
                sViewFlagsField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtils", "fetchViewFlagsField: ");
            }
            sViewFlagsFieldFetched = true;
        }
        Field field = sViewFlagsField;
        if (field != null) {
            try {
                sViewFlagsField.setInt(view, i | (field.getInt(view) & -13));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    static void transformMatrixToGlobal(View view, Matrix matrix) {
        IMPL.transformMatrixToGlobal(view, matrix);
    }

    static void transformMatrixToLocal(View view, Matrix matrix) {
        IMPL.transformMatrixToLocal(view, matrix);
    }
}
