package p000;

import android.os.Build;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.apps.photosgo.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* renamed from: gdo */
/* compiled from: PG */
public final class gdo extends C0418pg {

    /* renamed from: a */
    public boolean f11030a;

    /* renamed from: b */
    public boolean f11031b;

    /* renamed from: c */
    public boolean f11032c;

    /* renamed from: d */
    private BottomSheetBehavior f11033d;

    /* renamed from: e */
    private FrameLayout f11034e;

    /* renamed from: f */
    private final gdh f11035f;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gdo(android.content.Context r5) {
        /*
            r4 = this;
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.res.Resources$Theme r1 = r5.getTheme()
            r2 = 2130968669(0x7f04005d, float:1.7545998E38)
            r3 = 1
            boolean r1 = r1.resolveAttribute(r2, r0, r3)
            if (r1 == 0) goto L_0x0016
            int r0 = r0.resourceId
            goto L_0x001a
        L_0x0016:
            r0 = 2131952166(0x7f130226, float:1.9540767E38)
        L_0x001a:
            r4.<init>(r5, r0)
            r4.f11030a = r3
            r4.f11031b = r3
            gdh r5 = new gdh
            r5.<init>(r4)
            r4.f11035f = r5
            r4.mo9617a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gdo.<init>(android.content.Context):void");
    }

    public final void cancel() {
        mo6471b();
        super.cancel();
    }

    /* renamed from: c */
    private final void m10130c() {
        if (this.f11034e == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, (ViewGroup) null);
            this.f11034e = frameLayout;
            ViewGroup.LayoutParams layoutParams = ((FrameLayout) frameLayout.findViewById(R.id.design_bottom_sheet)).getLayoutParams();
            if (layoutParams instanceof abm) {
                abj abj = ((abm) layoutParams).f80a;
                if (abj instanceof BottomSheetBehavior) {
                    BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) abj;
                    this.f11033d = bottomSheetBehavior;
                    gdh gdh = this.f11035f;
                    if (!bottomSheetBehavior.f5180o.contains(gdh)) {
                        bottomSheetBehavior.f5180o.add(gdh);
                    }
                    this.f11033d.mo3615c(this.f11030a);
                    return;
                }
                throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
            }
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
    }

    /* renamed from: b */
    public final BottomSheetBehavior mo6471b() {
        if (this.f11033d == null) {
            m10130c();
        }
        return this.f11033d;
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            int i = Build.VERSION.SDK_INT;
            window.clearFlags(67108864);
            window.addFlags(RecyclerView.UNDEFINED_DURATION);
            window.setLayout(-1, -1);
        }
    }

    /* access modifiers changed from: protected */
    public final void onStart() {
        super.onStart();
        BottomSheetBehavior bottomSheetBehavior = this.f11033d;
        if (bottomSheetBehavior != null && bottomSheetBehavior.f5175j == 5) {
            bottomSheetBehavior.mo3614c(4);
        }
    }

    public final void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.f11030a != z) {
            this.f11030a = z;
            BottomSheetBehavior bottomSheetBehavior = this.f11033d;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.mo3615c(z);
            }
        }
    }

    public final void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.f11030a) {
            this.f11030a = true;
        }
        this.f11031b = z;
        this.f11032c = true;
    }

    public final void setContentView(int i) {
        super.setContentView(m10129a(i, (View) null, (ViewGroup.LayoutParams) null));
    }

    public final void setContentView(View view) {
        super.setContentView(m10129a(0, view, (ViewGroup.LayoutParams) null));
    }

    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(m10129a(0, view, layoutParams));
    }

    /* renamed from: a */
    private final View m10129a(int i, View view, ViewGroup.LayoutParams layoutParams) {
        m10130c();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.f11034e.findViewById(R.id.coordinator);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, coordinatorLayout, false);
        }
        FrameLayout frameLayout = (FrameLayout) this.f11034e.findViewById(R.id.design_bottom_sheet);
        if (layoutParams == null) {
            frameLayout.addView(view);
        } else {
            frameLayout.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new gdl(this));
        C0340mj.m14698a((View) frameLayout, (C0315ll) new gdm(this));
        frameLayout.setOnTouchListener(new gdn());
        return this.f11034e;
    }
}
