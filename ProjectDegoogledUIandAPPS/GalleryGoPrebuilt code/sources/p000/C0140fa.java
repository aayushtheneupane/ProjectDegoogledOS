package p000;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

/* renamed from: fa */
/* compiled from: PG */
public class C0140fa extends C0147fh implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* renamed from: Z */
    private Handler f9228Z;

    /* renamed from: a */
    public final DialogInterface.OnDismissListener f9229a = new C0138ez(this);

    /* renamed from: aa */
    private final Runnable f9230aa = new C0136ex(this);

    /* renamed from: ab */
    private final DialogInterface.OnCancelListener f9231ab = new C0137ey(this);

    /* renamed from: ac */
    private int f9232ac = 0;

    /* renamed from: ad */
    private int f9233ad = 0;

    /* renamed from: ae */
    private int f9234ae = -1;

    /* renamed from: af */
    private boolean f9235af;

    /* renamed from: ag */
    private boolean f9236ag;

    /* renamed from: ah */
    private boolean f9237ah;

    /* renamed from: b */
    public boolean f9238b = true;

    /* renamed from: c */
    public boolean f9239c = true;

    /* renamed from: d */
    public Dialog f9240d;

    public void onCancel(DialogInterface dialogInterface) {
    }

    /* renamed from: a */
    public final void mo5428a(boolean z, boolean z2) {
        if (!this.f9236ag) {
            this.f9236ag = true;
            this.f9237ah = false;
            Dialog dialog = this.f9240d;
            if (dialog != null) {
                dialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
                this.f9240d.dismiss();
                if (!z2) {
                    if (Looper.myLooper() == this.f9228Z.getLooper()) {
                        onDismiss(this.f9240d);
                    } else {
                        this.f9228Z.post(this.f9230aa);
                    }
                }
            }
            this.f9235af = true;
            if (this.f9234ae >= 0) {
                C0171gd q = mo5658q();
                int i = this.f9234ae;
                if (i >= 0) {
                    q.mo6425a((C0168gb) new C0169gc(q, i), false);
                    this.f9234ae = -1;
                    return;
                }
                throw new IllegalArgumentException("Bad id: " + i);
            }
            C0182gn a = mo5658q().mo6419a();
            a.mo5243a((C0147fh) this);
            if (z) {
                a.mo5252c();
            } else {
                a.mo5251b();
            }
        }
    }

    /* renamed from: d */
    public void mo2667d(Bundle bundle) {
        Bundle bundle2;
        super.mo2667d(bundle);
        if (this.f9239c) {
            View view = this.f9573L;
            if (view != null) {
                if (view.getParent() == null) {
                    this.f9240d.setContentView(view);
                } else {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
            }
            C0149fj m = mo5653m();
            if (m != null) {
                this.f9240d.setOwnerActivity(m);
            }
            this.f9240d.setCancelable(this.f9238b);
            this.f9240d.setOnCancelListener(this.f9231ab);
            this.f9240d.setOnDismissListener(this.f9229a);
            if (bundle != null && (bundle2 = bundle.getBundle("android:savedDialogState")) != null) {
                this.f9240d.onRestoreInstanceState(bundle2);
            }
        }
    }

    /* renamed from: a */
    public void mo1832a(Context context) {
        super.mo1832a(context);
        if (!this.f9237ah) {
            this.f9236ag = false;
        }
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        this.f9228Z = new Handler();
        this.f9239c = this.f9563B == 0;
        if (bundle != null) {
            this.f9232ac = bundle.getInt("android:style", 0);
            this.f9233ad = bundle.getInt("android:theme", 0);
            this.f9238b = bundle.getBoolean("android:cancelable", true);
            this.f9239c = bundle.getBoolean("android:showsDialog", this.f9239c);
            this.f9234ae = bundle.getInt("android:backStackId", -1);
        }
    }

    /* renamed from: c */
    public Dialog mo193c(Bundle bundle) {
        return new Dialog(mo5652l(), this.f9233ad);
    }

    /* renamed from: f */
    public void mo212f() {
        super.mo212f();
        Dialog dialog = this.f9240d;
        if (dialog != null) {
            this.f9235af = true;
            dialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
            this.f9240d.dismiss();
            if (!this.f9236ag) {
                onDismiss(this.f9240d);
            }
            this.f9240d = null;
        }
    }

    /* renamed from: c */
    public void mo1834c() {
        super.mo1834c();
        if (!this.f9237ah && !this.f9236ag) {
            this.f9236ag = true;
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.f9235af) {
            mo5428a(true, true);
        }
    }

    /* renamed from: b */
    public LayoutInflater mo2633b(Bundle bundle) {
        if (!this.f9239c) {
            return mo5627H();
        }
        Dialog c = mo193c(bundle);
        this.f9240d = c;
        if (c == null) {
            return (LayoutInflater) this.f9605x.f10593c.getSystemService("layout_inflater");
        }
        int i = this.f9232ac;
        if (!(i == 1 || i == 2)) {
            if (i == 3) {
                c.getWindow().addFlags(24);
            }
            return (LayoutInflater) this.f9240d.getContext().getSystemService("layout_inflater");
        }
        c.requestWindowFeature(1);
        return (LayoutInflater) this.f9240d.getContext().getSystemService("layout_inflater");
    }

    /* renamed from: e */
    public void mo168e(Bundle bundle) {
        Bundle onSaveInstanceState;
        Dialog dialog = this.f9240d;
        if (!(dialog == null || (onSaveInstanceState = dialog.onSaveInstanceState()) == null)) {
            bundle.putBundle("android:savedDialogState", onSaveInstanceState);
        }
        int i = this.f9232ac;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.f9233ad;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        if (!this.f9238b) {
            bundle.putBoolean("android:cancelable", false);
        }
        if (!this.f9239c) {
            bundle.putBoolean("android:showsDialog", false);
        }
        int i3 = this.f9234ae;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    /* renamed from: d */
    public void mo210d() {
        super.mo210d();
        Dialog dialog = this.f9240d;
        if (dialog != null) {
            this.f9235af = false;
            dialog.show();
        }
    }

    /* renamed from: e */
    public void mo211e() {
        super.mo211e();
        Dialog dialog = this.f9240d;
        if (dialog != null) {
            dialog.hide();
        }
    }

    /* renamed from: a */
    public final void mo5427a(C0171gd gdVar, String str) {
        this.f9236ag = false;
        this.f9237ah = true;
        C0182gn a = gdVar.mo6419a();
        a.mo6851a((C0147fh) this, str);
        a.mo5251b();
    }

    /* renamed from: b */
    public final void mo5429b(C0171gd gdVar, String str) {
        this.f9236ag = false;
        this.f9237ah = true;
        C0182gn a = gdVar.mo6419a();
        a.mo6851a((C0147fh) this, str);
        a.mo5244a();
    }
}
