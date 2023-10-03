package p000;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: nn */
/* compiled from: PG */
final class C0371nn extends C0358na {

    /* renamed from: b */
    private final /* synthetic */ C0372no f15294b;

    public C0371nn(C0372no noVar) {
        this.f15294b = noVar;
    }

    /* renamed from: a */
    public final C0354mx mo9453a(int i) {
        return C0354mx.m14778a(AccessibilityNodeInfo.obtain(this.f15294b.mo9463b(i).f15257a));
    }

    /* renamed from: b */
    public final C0354mx mo9455b(int i) {
        int i2 = i == 2 ? this.f15294b.f15298d : this.f15294b.f15299e;
        if (i2 == Integer.MIN_VALUE) {
            return null;
        }
        return mo9453a(i2);
    }

    /* renamed from: a */
    public final boolean mo9454a(int i, int i2, Bundle bundle) {
        int i3;
        int i4;
        C0372no noVar = this.f15294b;
        if (i == -1) {
            return C0340mj.m14703a(noVar.f15297c, i2, bundle);
        }
        if (i2 != 1) {
            if (i2 == 2) {
                return noVar.mo9466d(i);
            }
            if (i2 != 64) {
                if (i2 != 128) {
                    return noVar.mo2873a();
                }
                return noVar.mo9465c(i);
            } else if (noVar.f15296b.isEnabled() && noVar.f15296b.isTouchExplorationEnabled() && (i4 = noVar.f15298d) != i) {
                if (i4 != Integer.MIN_VALUE) {
                    noVar.mo9465c(i4);
                }
                noVar.f15298d = i;
                noVar.f15297c.invalidate();
                noVar.mo9464b(i, 32768);
                return true;
            }
        } else if ((noVar.f15297c.isFocused() || noVar.f15297c.requestFocus()) && (i3 = noVar.f15299e) != i) {
            if (i3 != Integer.MIN_VALUE) {
                noVar.mo9466d(i3);
            }
            if (i != Integer.MIN_VALUE) {
                noVar.f15299e = i;
                noVar.mo9464b(i, 8);
                return true;
            }
        }
        return false;
    }
}
