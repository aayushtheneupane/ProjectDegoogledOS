package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.concurrent.Executor;

/* renamed from: gsk */
/* compiled from: PG */
public final class gsk implements Parcelable {
    public static final Parcelable.Creator CREATOR = new gsi();

    /* renamed from: f */
    private static final hvy f11961f = hvy.m12261a("com/google/apps/tiktok/concurrent/futuresmixin/ParcelableFuture");

    /* renamed from: a */
    public final int f11962a;

    /* renamed from: b */
    public boolean f11963b = false;

    /* renamed from: c */
    public Object f11964c;

    /* renamed from: d */
    public Object f11965d;

    /* renamed from: e */
    public Throwable f11966e;

    /* renamed from: g */
    private gsj f11967g;

    public final int describeContents() {
        return 0;
    }

    public gsk(int i, Object obj, ieh ieh) {
        this.f11962a = i;
        this.f11964c = obj;
        ife.m12841a(ieh, hmq.m11746a((idw) new gsh(this)), (Executor) idh.f13918a);
    }

    public /* synthetic */ gsk(Parcel parcel) {
        boolean z = false;
        ClassLoader classLoader = getClass().getClassLoader();
        this.f11962a = parcel.readInt();
        try {
            this.f11964c = parcel.readValue(classLoader);
            z = parcel.readInt() == 1 ? true : z;
            this.f11963b = z;
            if (z) {
                this.f11965d = parcel.readValue(classLoader);
                this.f11966e = (Throwable) parcel.readValue(classLoader);
            } else {
                this.f11963b = true;
                this.f11966e = new gsl("ParcelableFuture was Parceled by a lifecycle change before it completed.");
                ((hvv) ((hvv) ((hvv) f11961f.mo8180b()).mo8202a(this.f11966e)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/ParcelableFuture", "<init>", 78, "ParcelableFuture.java")).mo8203a("Result was lost due to parceling.");
            }
        } catch (RuntimeException e) {
            ((hvv) ((hvv) ((hvv) f11961f.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/ParcelableFuture", "<init>", 81, "ParcelableFuture.java")).mo8204a("Failed to unparcel value for %d.", this.f11962a);
            this.f11963b = true;
            this.f11966e = e;
        }
        mo6997a();
    }

    /* renamed from: a */
    public final void mo6997a() {
        gsj gsj = this.f11967g;
        if (gsj != null) {
            Throwable th = this.f11966e;
            if (th == null) {
                gsg gsg = (gsg) gsj;
                gsg.f11956a.execute(hmq.m11748a((Runnable) new gse(gsg, this, this.f11965d)));
                return;
            }
            gsg gsg2 = (gsg) gsj;
            gsg2.f11956a.execute(hmq.m11748a((Runnable) new gsf(gsg2, this, th)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6998a(gsj gsj) {
        this.f11967g = gsj;
        if (gsj != null && this.f11963b) {
            mo6997a();
        }
    }

    public final String toString() {
        String str;
        String str2;
        Object obj = this.f11964c;
        String str3 = "";
        if (obj != null) {
            String name = obj.getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 7);
            sb.append("input=");
            sb.append(name);
            sb.append(";");
            str = sb.toString();
        } else {
            str = str3;
        }
        Object obj2 = this.f11965d;
        if (obj2 == null) {
            str2 = str3;
        } else {
            String name2 = obj2.getClass().getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(name2).length() + 8);
            sb2.append("result=");
            sb2.append(name2);
            sb2.append(";");
            str2 = sb2.toString();
        }
        Throwable th = this.f11966e;
        if (th != null) {
            String name3 = th.getClass().getName();
            StringBuilder sb3 = new StringBuilder(String.valueOf(name3).length() + 7);
            sb3.append("error=");
            sb3.append(name3);
            sb3.append(";");
            str3 = sb3.toString();
        }
        int length = String.valueOf(str).length();
        StringBuilder sb4 = new StringBuilder(length + 18 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb4.append("ParcelableFuture(");
        sb4.append(str);
        sb4.append(str2);
        sb4.append(str3);
        sb4.append(")");
        return sb4.toString();
    }

    /* renamed from: a */
    private static void m10722a(Object obj, Parcel parcel) {
        int dataPosition = parcel.dataPosition();
        try {
            parcel.writeValue(obj);
        } catch (RuntimeException e) {
            parcel.setDataPosition(dataPosition);
            ((hvv) ((hvv) ((hvv) f11961f.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/ParcelableFuture", "tryToWriteValue", 186, "ParcelableFuture.java")).mo8203a("Result lost due to non-parcelable type.");
            String valueOf = String.valueOf(obj.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 50);
            sb.append("Type not supported by Parcel and will be dropped: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f11962a);
        try {
            m10722a(this.f11964c, parcel);
        } catch (RuntimeException e) {
            parcel.writeValue((Object) null);
            this.f11963b = true;
            this.f11966e = new IllegalArgumentException("FuturesMixin input isn't Parcelable.", e);
            this.f11965d = null;
        }
        parcel.writeInt(this.f11963b ? 1 : 0);
        if (this.f11963b) {
            try {
                m10722a(this.f11965d, parcel);
            } catch (RuntimeException e2) {
                parcel.writeValue((Object) null);
                this.f11966e = new IllegalArgumentException("FuturesMixin result isn't Parcelable.", e2);
            }
            try {
                m10722a(this.f11966e, parcel);
            } catch (RuntimeException e3) {
                String valueOf = String.valueOf(this.f11966e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 38);
                sb.append("FuturesMixin result isn't Parcelable: ");
                sb.append(valueOf);
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(sb.toString(), e3);
                this.f11966e = illegalArgumentException;
                m10722a(illegalArgumentException, parcel);
            }
        }
    }
}
