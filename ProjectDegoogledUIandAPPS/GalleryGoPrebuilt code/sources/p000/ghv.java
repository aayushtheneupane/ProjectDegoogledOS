package p000;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.BaseTransientBottomBar$Behavior;

/* renamed from: ghv */
/* compiled from: PG */
final class ghv implements Handler.Callback {
    public final boolean handleMessage(Message message) {
        int i = message.what;
        int i2 = 0;
        if (i == 0) {
            gik gik = (gik) message.obj;
            gik.f11418e.f11407b = new gib(gik);
            if (gik.f11418e.getParent() == null) {
                ViewGroup.LayoutParams layoutParams = gik.f11418e.getLayoutParams();
                if (layoutParams instanceof abm) {
                    abm abm = (abm) layoutParams;
                    BaseTransientBottomBar$Behavior baseTransientBottomBar$Behavior = new BaseTransientBottomBar$Behavior();
                    baseTransientBottomBar$Behavior.f5226g.f11404a = gik.f11427n;
                    baseTransientBottomBar$Behavior.f5149b = new gid(gik);
                    abm.mo103a((abj) baseTransientBottomBar$Behavior);
                    if (gik.f11421h == null) {
                        abm.f86g = 80;
                    }
                }
                View view = gik.f11421h;
                if (view != null) {
                    int[] iArr = new int[2];
                    view.getLocationOnScreen(iArr);
                    int i3 = iArr[1];
                    int[] iArr2 = new int[2];
                    gik.f11416c.getLocationOnScreen(iArr2);
                    i2 = (iArr2[1] + gik.f11416c.getHeight()) - i3;
                }
                gik.f11426m = i2;
                gik.mo6712a();
                gik.f11418e.setVisibility(4);
                gik.f11416c.addView(gik.f11418e);
            }
            if (!C0340mj.m14732w(gik.f11418e)) {
                gik.f11418e.f11406a = new gic(gik);
            } else {
                gik.mo6716d();
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            gik gik2 = (gik) message.obj;
            int i4 = message.arg1;
            if (!gik2.mo6719g() || gik2.f11418e.getVisibility() != 0) {
                gik2.mo6720h();
            } else if (gik2.f11418e.f11408c != 1) {
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setIntValues(new int[]{0, gik2.mo6717e()});
                valueAnimator.setInterpolator(gci.f10937b);
                valueAnimator.setDuration(250);
                valueAnimator.addListener(new ght(gik2));
                valueAnimator.addUpdateListener(new ghu(gik2));
                valueAnimator.start();
            } else {
                ValueAnimator a = gik2.mo6711a(1.0f, 0.0f);
                a.setDuration(75);
                a.addListener(new gho(gik2));
                a.start();
            }
            return true;
        }
    }
}
