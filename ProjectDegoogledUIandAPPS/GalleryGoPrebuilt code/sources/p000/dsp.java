package p000;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.apps.photosgo.oneup.secure.unlock.UnlockActivity;

/* renamed from: dsp */
/* compiled from: PG */
public final class dsp {

    /* renamed from: a */
    public final BroadcastReceiver f7286a = new dsn(this);

    /* renamed from: b */
    public final BroadcastReceiver f7287b = new dso(this);

    /* renamed from: c */
    public final Activity f7288c;

    /* renamed from: d */
    public final dsm f7289d;

    /* renamed from: e */
    private boolean f7290e;

    public dsp(Activity activity, dsm dsm) {
        this.f7288c = activity;
        this.f7289d = dsm;
    }

    /* renamed from: a */
    public static Intent m6593a(Context context, Intent intent) {
        Intent intent2 = new Intent(context, UnlockActivity.class);
        intent2.putExtra("target_intent", intent);
        return intent2;
    }

    /* renamed from: a */
    public final void mo4390a() {
        if (!this.f7290e) {
            this.f7290e = true;
            Intent intent = this.f7289d.f7283a;
            if (intent != null) {
                this.f7288c.startActivityForResult((Intent) ife.m12898e((Object) intent), 0);
                this.f7289d.mo4387a((Intent) null);
            }
            this.f7288c.finish();
        }
    }
}
