package p000;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.google.android.apps.photosgo.R;

/* renamed from: edw */
/* compiled from: PG */
public abstract class edw implements eea {
    /* renamed from: b */
    public final boolean mo4684b() {
        return true;
    }

    /* renamed from: c */
    public abstract String mo4729c();

    /* renamed from: d */
    public abstract String mo4730d();

    /* renamed from: e */
    public abstract String mo4731e();

    /* renamed from: f */
    public abstract int mo4732f();

    /* renamed from: g */
    public abstract long mo4733g();

    /* renamed from: h */
    public abstract boolean mo4734h();

    /* renamed from: i */
    public abstract boolean mo4735i();

    /* renamed from: k */
    public abstract edv mo4736k();

    /* renamed from: l */
    public final ContentValues mo4737l() {
        ContentValues contentValues = new ContentValues(4);
        contentValues.put("activity_class_name", mo4729c());
        contentValues.put("package_name", mo4730d());
        contentValues.put("a", mo4731e());
        contentValues.put("b", Integer.valueOf(mo4732f()));
        contentValues.put("c", Long.valueOf(mo4733g()));
        contentValues.put("d", Integer.valueOf(mo4734h() ? 1 : 0));
        contentValues.put("e", Integer.valueOf(mo4735i() ? 1 : 0));
        return contentValues;
    }

    /* renamed from: a */
    public final Drawable mo4680a(Context context) {
        try {
            return context.getPackageManager().getApplicationIcon(mo4730d());
        } catch (PackageManager.NameNotFoundException e) {
            return context.getDrawable(R.drawable.quantum_gm_ic_error_vd_theme_24);
        }
    }

    /* renamed from: a */
    public final String mo4682a() {
        return mo4731e();
    }

    /* renamed from: j */
    public static edv m7289j() {
        edv edv = new edv((byte[]) null);
        edv.mo4722a(1);
        return edv;
    }

    /* renamed from: a */
    public final ieh mo4681a(een een) {
        ieh a = een.mo4750a(mo4729c(), mo4730d());
        cwn.m5509a(a, "Sharing: Failed to write new share count to database", new Object[0]);
        return a;
    }

    /* renamed from: a */
    public final boolean mo4683a(Context context, Intent intent) {
        intent.setClassName(mo4730d(), mo4729c());
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            cwn.m5515b((Throwable) e, "Could not start share activity for intent %s", intent);
            return false;
        }
    }
}
