package com.android.messaging.p041ui.p042a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.android.messaging.R;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1486ya;

@TargetApi(18)
/* renamed from: com.android.messaging.ui.a.i */
class C1082i {

    /* renamed from: KG */
    private final View f1712KG;
    /* access modifiers changed from: private */

    /* renamed from: LG */
    public final Bitmap f1713LG;
    /* access modifiers changed from: private */

    /* renamed from: al */
    public final View f1714al;
    private final ViewGroup mContainer;
    private final int mDuration;

    public C1082i(View view, ViewGroup viewGroup, boolean z, int i) {
        this.f1714al = view;
        this.mContainer = viewGroup;
        this.mDuration = i;
        if (z) {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Drawable background = view.getBackground();
            C1416U.m3566a(view, (Drawable) null);
            view.draw(new Canvas(createBitmap));
            C1416U.m3566a(view, background);
            this.f1713LG = createBitmap;
            this.f1712KG = new View(view.getContext());
            return;
        }
        this.f1712KG = null;
        this.f1713LG = null;
    }

    public void startAnimation() {
        float f;
        Context context = this.f1714al.getContext();
        Resources resources = context.getResources();
        View decorView = ((Activity) context).getWindow().getDecorView();
        ViewOverlay overlay = decorView.getOverlay();
        if (overlay instanceof ViewGroupOverlay) {
            ViewGroupOverlay viewGroupOverlay = (ViewGroupOverlay) overlay;
            FrameLayout frameLayout = new FrameLayout(context);
            Drawable background = this.f1714al.getBackground();
            Rect h = C1486ya.m3858h(this.mContainer);
            Rect h2 = C1486ya.m3858h(decorView);
            h.offset(-h2.left, -h2.top);
            frameLayout.setLeft(h.left);
            frameLayout.setTop(h.top);
            frameLayout.setBottom(h.bottom);
            frameLayout.setRight(h.right);
            frameLayout.setBackgroundColor(resources.getColor(R.color.open_conversation_animation_background_shadow));
            if (!(background instanceof ColorDrawable)) {
                this.f1714al.setBackground((Drawable) null);
            }
            viewGroupOverlay.add(frameLayout);
            View view = new View(context);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.explode_animation_highlight_elevation);
            Rect h3 = C1486ya.m3858h(this.f1714al);
            h3.offset((-h.left) - h2.left, (-h.top) - h2.top);
            int height = h3.height() / 2;
            int i = h3.top;
            int height2 = h.height() - h3.bottom;
            if (height == 0) {
                f = 1.0f;
            } else {
                float f2 = (float) height;
                f = (((float) Math.max(i, height2)) + f2) / f2;
            }
            frameLayout.addView(view);
            view.setLeft(h3.left);
            view.setTop(h3.top);
            view.setBottom(h3.bottom);
            view.setRight(h3.right);
            view.setBackgroundColor(resources.getColor(R.color.conversation_background));
            float f3 = (float) dimensionPixelSize;
            ViewCompat.setElevation(view, f3);
            View view2 = this.f1712KG;
            if (view2 != null) {
                frameLayout.addView(view2);
                this.f1712KG.setLeft(h3.left);
                this.f1712KG.setTop(h3.top);
                this.f1712KG.setBottom(h3.bottom);
                this.f1712KG.setRight(h3.right);
                this.f1712KG.setBackground(new BitmapDrawable(resources, this.f1713LG));
                View view3 = this.f1712KG;
                int i2 = Build.VERSION.SDK_INT;
                view3.setElevation(f3);
            }
            view.animate().scaleY(f).setDuration((long) this.mDuration).setInterpolator(C1486ya.f2358bL).withEndAction(new C1081h(this, viewGroupOverlay, frameLayout, background));
        }
    }
}
