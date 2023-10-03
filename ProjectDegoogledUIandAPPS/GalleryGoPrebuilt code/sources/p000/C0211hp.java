package p000;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

/* renamed from: hp */
/* compiled from: PG */
public final class C0211hp {

    /* renamed from: a */
    public final Context f13191a;

    /* renamed from: b */
    public final ArrayList f13192b = new ArrayList();

    /* renamed from: c */
    public final ArrayList f13193c = new ArrayList();

    /* renamed from: d */
    public CharSequence f13194d;

    /* renamed from: e */
    public CharSequence f13195e;

    /* renamed from: f */
    public PendingIntent f13196f;

    /* renamed from: g */
    public int f13197g;

    /* renamed from: h */
    public C0212hq f13198h;

    /* renamed from: i */
    public boolean f13199i = false;

    /* renamed from: j */
    public Bundle f13200j;

    /* renamed from: k */
    public String f13201k;

    /* renamed from: l */
    public final Notification f13202l;
    @Deprecated

    /* renamed from: m */
    public final ArrayList f13203m;

    @Deprecated
    public C0211hp(Context context) {
        Notification notification = new Notification();
        this.f13202l = notification;
        this.f13191a = context;
        this.f13201k = null;
        notification.when = System.currentTimeMillis();
        this.f13202l.audioStreamType = -1;
        this.f13197g = 0;
        this.f13203m = new ArrayList();
    }

    /* renamed from: a */
    public final Bundle mo7637a() {
        if (this.f13200j == null) {
            this.f13200j = new Bundle();
        }
        return this.f13200j;
    }

    /* renamed from: a */
    public static CharSequence m11861a(CharSequence charSequence) {
        if (charSequence != null) {
            return charSequence.length() > 5120 ? charSequence.subSequence(0, 5120) : charSequence;
        }
        return null;
    }

    /* renamed from: a */
    public final void mo7638a(int i) {
        this.f13202l.icon = i;
    }

    /* renamed from: a */
    public final void mo7639a(C0212hq hqVar) {
        if (this.f13198h != hqVar) {
            this.f13198h = hqVar;
            if (hqVar != null && hqVar.f13243a != this) {
                hqVar.f13243a = this;
                C0211hp hpVar = hqVar.f13243a;
                if (hpVar != null) {
                    hpVar.mo7639a(hqVar);
                }
            }
        }
    }
}
