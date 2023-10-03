package com.android.messaging.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1038Da;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.C1369oa;
import com.android.messaging.p041ui.C1371pa;
import com.android.messaging.p041ui.C1376qa;
import java.util.List;

/* renamed from: com.android.messaging.util.ya */
public class C1486ya {
    public static final Interpolator EASE_OUT_INTERPOLATOR = new C1483x(0.0f, 0.0f, 0.2f, 1.0f);

    /* renamed from: YK */
    public static final int f2354YK = getApplicationContext().getResources().getInteger(R.integer.mediapicker_transition_duration);

    /* renamed from: ZK */
    public static final int f2355ZK = getApplicationContext().getResources().getInteger(R.integer.compose_transition_duration);

    /* renamed from: _K */
    public static final int f2356_K = getApplicationContext().getResources().getInteger(R.integer.reveal_view_animation_duration);

    /* renamed from: aL */
    public static final Interpolator f2357aL = new C1483x(0.4f, 0.0f, 0.2f, 1.0f);

    /* renamed from: bL */
    public static final Interpolator f2358bL = new C1483x(0.4f, 0.0f, 0.8f, 0.5f);

    static {
        getApplicationContext().getResources().getInteger(R.integer.asyncimage_transition_duration);
    }

    /* renamed from: B */
    public static Activity m3844B(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return m3844B(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /* renamed from: C */
    public static RemoteViews m3845C(Context context) {
        return new RemoteViews(context.getPackageName(), R.layout.widget_missing_permission);
    }

    /* renamed from: Ma */
    public static void m3846Ma(String str) {
        Toast makeText = Toast.makeText(getApplicationContext(), str, 1);
        makeText.setGravity(81, 0, 0);
        makeText.show();
    }

    /* renamed from: Oa */
    public static void m3847Oa(int i) {
        Toast makeText = Toast.makeText(getApplicationContext(), getApplicationContext().getString(i), 1);
        makeText.setGravity(1, 0, 0);
        makeText.show();
    }

    /* renamed from: Pa */
    public static void m3848Pa(int i) {
        m3846Ma(getApplicationContext().getString(i));
    }

    /* renamed from: a */
    public static void m3853a(Context context, View view, String str, Runnable runnable, int i, List list) {
        C1369oa h;
        C1424b.m3594t(context);
        if (i == 0) {
            h = C1369oa.m3484h(runnable);
        } else if (i != 1) {
            h = null;
        } else {
            h = C1369oa.m3483g(runnable);
        }
        m3852a(context, view, str, h, list, (C1376qa) null);
    }

    /* renamed from: d */
    public static boolean m3856d(Activity activity) {
        if (C1464na.m3752Sj()) {
            return false;
        }
        C1040Ea.get().mo6989z(activity);
        activity.finish();
        return true;
    }

    /* renamed from: g */
    public static boolean m3857g() {
        C1474sa saVar = C1474sa.getDefault();
        return saVar.isSmsCapable() && saVar.mo8206ek() && saVar.mo8228kk();
    }

    private static Context getApplicationContext() {
        return C0967f.get().getApplicationContext();
    }

    public static int getPaddingStart(View view) {
        return C1464na.m3755Vj() ? view.getPaddingStart() : view.getPaddingLeft();
    }

    /* renamed from: h */
    public static Rect m3858h(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], view.getMeasuredWidth() + iArr[0], view.getMeasuredHeight() + iArr[1]);
    }

    /* renamed from: ok */
    public static boolean m3859ok() {
        return C0967f.get().getApplicationContext().getResources().getConfiguration().orientation == 2;
    }

    /* renamed from: pk */
    public static boolean m3860pk() {
        if (!C1464na.m3756Wj() || C0967f.get().getApplicationContext().getResources().getConfiguration().getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }

    public static void showToast(int i, int i2) {
        Toast makeText = Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getQuantityString(i, i2), 1);
        makeText.setGravity(1, 0, 0);
        makeText.show();
    }

    /* renamed from: a */
    public static void m3851a(Context context, View view, String str) {
        C1424b.m3594t(context);
        C1424b.m3592ia(!TextUtils.isEmpty(str));
        C1371pa g = C1038Da.get().mo6947g(view);
        g.setText(str);
        g.show();
    }

    /* renamed from: a */
    public static void m3852a(Context context, View view, String str, C1369oa oaVar, List list, C1376qa qaVar) {
        C1424b.m3594t(context);
        C1424b.m3592ia(!TextUtils.isEmpty(str));
        C1424b.m3594t(oaVar);
        C1371pa g = C1038Da.get().mo6947g(view);
        g.setText(str);
        g.mo7987a(oaVar);
        g.mo7989l(list);
        g.mo7988a(qaVar);
        g.show();
    }

    /* renamed from: a */
    public static void m3855a(View view, Runnable runnable) {
        view.addOnLayoutChangeListener(new C1482wa(runnable, view));
    }

    /* renamed from: a */
    public static CharSequence m3849a(String str, TextPaint textPaint, int i, String str2, String str3) {
        CharSequence commaEllipsize = TextUtils.commaEllipsize(str, textPaint, (float) i, str2, str3);
        return TextUtils.isEmpty(commaEllipsize) ? str : commaEllipsize;
    }

    /* renamed from: a */
    public static void m3854a(View view, int i, Runnable runnable) {
        if (view.getVisibility() != i) {
            float f = i == 0 ? 0.0f : 1.0f;
            float f2 = i == 0 ? 1.0f : 0.0f;
            ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration((long) f2356_K);
            scaleAnimation.setInterpolator(f2357aL);
            scaleAnimation.setAnimationListener(new C1484xa(runnable));
            view.clearAnimation();
            view.startAnimation(scaleAnimation);
            view.setVisibility(i);
        } else if (runnable != null) {
            C1480va.getMainThreadHandler().post(runnable);
        }
    }

    /* renamed from: a */
    public static void m3850a(Activity activity, int i) {
        activity.getWindow().setStatusBarColor(i);
    }
}
