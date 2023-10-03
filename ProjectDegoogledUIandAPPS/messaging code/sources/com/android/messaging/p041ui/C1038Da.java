package com.android.messaging.p041ui;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.Da */
public class C1038Da {
    private static C1038Da sInstance;
    /* access modifiers changed from: private */

    /* renamed from: AG */
    public final Handler f1639AG = new Handler();
    /* access modifiers changed from: private */

    /* renamed from: BG */
    public C1380sa f1640BG;
    /* access modifiers changed from: private */

    /* renamed from: CG */
    public C1380sa f1641CG;
    /* access modifiers changed from: private */

    /* renamed from: DG */
    public boolean f1642DG;
    /* access modifiers changed from: private */
    public final Runnable mDismissRunnable = new C1386va(this);
    /* access modifiers changed from: private */
    public PopupWindow mPopupWindow;

    /* renamed from: xG */
    private final View.OnTouchListener f1643xG = new C1388wa(this);

    /* renamed from: yG */
    private final C1378ra f1644yG = new C1390xa(this);
    /* access modifiers changed from: private */

    /* renamed from: yn */
    public final View.OnAttachStateChangeListener f1645yn = new C1392ya(this);

    /* renamed from: zG */
    private final int f1646zG = C0967f.get().getApplicationContext().getResources().getInteger(R.integer.snackbar_translation_duration_ms);

    private C1038Da() {
    }

    /* renamed from: F */
    private WindowManager m2545F(Context context) {
        return (WindowManager) context.getSystemService("window");
    }

    /* renamed from: f */
    static /* synthetic */ void m2557f(C1038Da da) {
        da.f1640BG.getRootView().setOnTouchListener(da.f1643xG);
        da.f1640BG.mo8002cj().setOnTouchListener(da.f1643xG);
    }

    public static C1038Da get() {
        if (sInstance == null) {
            synchronized (C1038Da.class) {
                if (sInstance == null) {
                    sInstance = new C1038Da();
                }
            }
        }
        return sInstance;
    }

    public void dismiss() {
        ViewPropertyAnimator c;
        this.f1639AG.removeCallbacks(this.mDismissRunnable);
        C1380sa saVar = this.f1640BG;
        if (saVar != null && !this.f1642DG) {
            C1430e.m3620d("MessagingApp", "Dismissing snack bar.");
            this.f1642DG = true;
            saVar.setEnabled(false);
            View rootView = saVar.getRootView();
            m2546a(saVar.mo8002cj().animate()).translationY((float) saVar.getRootView().getHeight()).withEndAction(new C1036Ca(this, rootView, saVar));
            for (C1384ua uaVar : saVar.mo7998_i()) {
                if (!(uaVar == null || (c = ((C1382ta) uaVar).mo8008c(saVar)) == null)) {
                    m2546a(c);
                }
            }
        }
    }

    /* renamed from: e */
    public void mo6946e(C1380sa saVar) {
        ViewPropertyAnimator d;
        int i;
        C1424b.m3594t(saVar);
        if (this.f1640BG != null) {
            C1430e.m3620d("MessagingApp", "Showing snack bar, but currentSnackBar was not null.");
            this.f1641CG = saVar;
            dismiss();
            return;
        }
        this.f1640BG = saVar;
        saVar.mo7999a(this.f1644yG);
        this.f1639AG.removeCallbacks(this.mDismissRunnable);
        this.f1639AG.postDelayed(this.mDismissRunnable, (long) saVar.getDuration());
        saVar.setEnabled(false);
        View rootView = saVar.getRootView();
        if (Log.isLoggable("MessagingApp", 3)) {
            C1430e.m3620d("MessagingApp", "Showing snack bar: " + saVar);
        }
        View rootView2 = saVar.getRootView();
        Point point = new Point();
        m2545F(saVar.getContext()).getDefaultDisplay().getSize(point);
        rootView2.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(point.x, 1073741824), 0, -1), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(point.y, 1073741824), 0, -2));
        this.mPopupWindow = new PopupWindow(saVar.getContext());
        this.mPopupWindow.setWidth(-1);
        this.mPopupWindow.setHeight(-2);
        this.mPopupWindow.setBackgroundDrawable((Drawable) null);
        this.mPopupWindow.setContentView(rootView);
        C1376qa bj = saVar.mo8001bj();
        if (bj == null) {
            PopupWindow popupWindow = this.mPopupWindow;
            View aj = saVar.mo8000aj();
            WindowManager F = m2545F(saVar.getContext());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (C1464na.m3758Yj()) {
                F.getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                F.getDefaultDisplay().getMetrics(displayMetrics);
            }
            int i2 = displayMetrics.heightPixels;
            if (C1464na.m3758Yj()) {
                Rect rect = new Rect();
                saVar.mo8000aj().getRootView().getWindowVisibleDisplayFrame(rect);
                i = i2 - rect.bottom;
            } else {
                i = 0;
            }
            popupWindow.showAtLocation(aj, 8388691, 0, i);
        } else {
            View anchorView = bj.getAnchorView();
            C1394za zaVar = new C1394za(this, anchorView, saVar);
            anchorView.getViewTreeObserver().addOnGlobalLayoutListener(zaVar);
            this.mPopupWindow.setOnDismissListener(new C1032Aa(this, anchorView, zaVar));
            this.mPopupWindow.showAsDropDown(anchorView, 0, m2556f(saVar));
        }
        saVar.mo8000aj().addOnAttachStateChangeListener(this.f1645yn);
        saVar.mo8002cj().setTranslationY((float) saVar.getRootView().getMeasuredHeight());
        m2546a(saVar.mo8002cj().animate()).translationX(0.0f).translationY(0.0f).withEndAction(new C1034Ba(this, saVar));
        for (C1384ua uaVar : saVar.mo7998_i()) {
            if (!(uaVar == null || (d = ((C1382ta) uaVar).mo8009d(saVar)) == null)) {
                m2546a(d);
            }
        }
    }

    /* renamed from: g */
    public C1371pa mo6947g(View view) {
        return new C1371pa(this, view);
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public int m2556f(C1380sa saVar) {
        C1376qa bj = saVar.mo8001bj();
        C1424b.m3594t(bj);
        View anchorView = bj.getAnchorView();
        if (bj.mo7994Zi()) {
            return (-saVar.getRootView().getMeasuredHeight()) - anchorView.getHeight();
        }
        return 0;
    }

    /* renamed from: a */
    private ViewPropertyAnimator m2546a(ViewPropertyAnimator viewPropertyAnimator) {
        return viewPropertyAnimator.setInterpolator(C1486ya.f2357aL).setDuration((long) this.f1646zG);
    }
}
