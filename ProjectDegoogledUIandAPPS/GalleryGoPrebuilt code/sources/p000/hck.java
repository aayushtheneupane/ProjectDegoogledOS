package p000;

import android.app.Activity;
import java.util.Collections;
import java.util.Set;

/* renamed from: hck */
/* compiled from: PG */
public final class hck extends ftl {
    private hck(Activity activity) {
        super(activity);
    }

    /* renamed from: a */
    public static hck m11206a(Activity activity) {
        return new hck(activity);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo6178a() {
        ife.m12879b(this.f10570a.getApplication() instanceof hbs, "TikTok activity, %s, cannot be attached to a non-TikTok application, %s.", this.f10570a.getClass().getSimpleName(), this.f10570a.getApplication().getClass().getSimpleName());
        return super.mo6178a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final Object mo6179b(gkn gkn) {
        Set unmodifiableSet;
        synchronized (this.f10571b) {
            unmodifiableSet = Collections.unmodifiableSet(this.f10572c.keySet());
        }
        boolean z = false;
        if (unmodifiableSet.isEmpty() || (unmodifiableSet.size() == 1 && unmodifiableSet.contains(gkn))) {
            z = true;
        }
        ife.m12879b(z, "There is already an account id in use! TikTok does not support multiple accounts yet.\n\tCurrent AccountId: %s\n\tNew AccountId: %s", unmodifiableSet, gkn);
        return super.mo6179b(gkn);
    }

    /* renamed from: a */
    public final Object mo2452a(gkn gkn) {
        Object a;
        String str;
        synchronized (this.f10571b) {
            gkn gkn2 = (gkn) ((hcj) mo2453b()).mo2368n().mo7648c();
            if (gkn != null) {
                boolean equals = gkn.equals(gkn2);
                if (gkn2 != null) {
                    str = "";
                } else {
                    str = "\nDid you forget to add one of the account modules:\n\t\"//java/com/google/apps/tiktok/account:module\",\n\t\"//java/com/google/apps/tiktok/account/testing:module\",";
                }
                ife.m12851a(equals, "The given account id does not match the propagated account id.\n\tPropagated AccountId: %s\n\tGiven AccountId: %s%s", (Object) gkn2, (Object) gkn, (Object) str);
            } else {
                gkn = gkn2;
            }
            a = super.mo2452a(gkn);
        }
        return a;
    }
}
