package p000;

import android.content.res.TypedArray;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.apps.photosgo.R;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* renamed from: gin */
/* compiled from: PG */
public final class gin extends gik {

    /* renamed from: p */
    private static final int[] f11432p = {R.attr.snackbarButtonStyle, R.attr.snackbarTextViewStyle};

    /* renamed from: o */
    private final AccessibilityManager f11433o;

    private gin(ViewGroup viewGroup, View view, gil gil) {
        super(viewGroup, view, gil);
        this.f11433o = (AccessibilityManager) viewGroup.getContext().getSystemService("accessibility");
    }

    /* renamed from: b */
    public final int mo6714b() {
        int i = this.f11420g;
        return Build.VERSION.SDK_INT >= 29 ? this.f11433o.getRecommendedTimeoutMillis(i, 3) : i;
    }

    /* renamed from: a */
    public static gin m10373a(View view, int i, int i2) {
        return m10374a(view, view.getResources().getText(i), i2);
    }

    /* renamed from: a */
    public static gin m10374a(View view, CharSequence charSequence, int i) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2 = null;
        while (true) {
            if (view instanceof CoordinatorLayout) {
                viewGroup = (ViewGroup) view;
                break;
            }
            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    viewGroup = (ViewGroup) view;
                    break;
                }
                viewGroup2 = (ViewGroup) view;
            }
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    view = (View) parent;
                    continue;
                } else {
                    view = null;
                    continue;
                }
            }
            if (view == null) {
                viewGroup = viewGroup2;
                break;
            }
        }
        if (viewGroup != null) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes(f11432p);
            int resourceId = obtainStyledAttributes.getResourceId(0, -1);
            int resourceId2 = obtainStyledAttributes.getResourceId(1, -1);
            obtainStyledAttributes.recycle();
            int i2 = R.layout.design_layout_snackbar_include;
            if (!(resourceId == -1 || resourceId2 == -1)) {
                i2 = R.layout.mtrl_layout_snackbar_include;
            }
            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) from.inflate(i2, viewGroup, false);
            gin gin = new gin(viewGroup, snackbarContentLayout, snackbarContentLayout);
            ((SnackbarContentLayout) gin.f11418e.getChildAt(0)).f5227a.setText(charSequence);
            gin.f11420g = i;
            return gin;
        }
        throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
    }
}
