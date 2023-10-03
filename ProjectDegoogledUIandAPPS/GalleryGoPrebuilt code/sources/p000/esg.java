package p000;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.data.BitmapTeleporter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/* renamed from: esg */
/* compiled from: PG */
public class esg {

    /* renamed from: a */
    public Bitmap f8908a;

    /* renamed from: b */
    public String f8909b;

    /* renamed from: c */
    public final Bundle f8910c;

    /* renamed from: d */
    public String f8911d;

    /* renamed from: e */
    public String f8912e;

    /* renamed from: f */
    public final List f8913f;

    /* renamed from: g */
    public boolean f8914g;

    /* renamed from: h */
    public eso f8915h;

    /* renamed from: i */
    public boolean f8916i;

    /* renamed from: j */
    public long f8917j;

    /* renamed from: k */
    public ApplicationErrorReport f8918k;

    /* renamed from: l */
    public esv f8919l;

    /* renamed from: m */
    private BitmapTeleporter f8920m;

    /* renamed from: n */
    private esm f8921n;

    /* renamed from: o */
    private final String f8922o;

    /* renamed from: p */
    private final boolean f8923p;

    @Deprecated
    public esg() {
        this.f8910c = new Bundle();
        this.f8913f = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        long abs = Math.abs(new SecureRandom().nextLong());
        StringBuilder sb = new StringBuilder(41);
        sb.append(currentTimeMillis);
        sb.append("-");
        sb.append(abs);
        this.f8922o = sb.toString();
        this.f8923p = false;
        this.f8917j = 0;
    }

    public esg(esi esi) {
        this.f8908a = esi.f8936m;
        this.f8920m = esi.f8929f;
        this.f8909b = esi.f8924a;
        this.f8911d = esi.f8926c;
        this.f8910c = esi.f8925b;
        this.f8912e = esi.f8928e;
        this.f8913f = esi.f8931h;
        this.f8914g = esi.f8932i;
        this.f8915h = esi.f8933j;
        this.f8921n = esi.f8934k;
        this.f8916i = esi.f8935l;
        this.f8919l = esi.f8940q;
        this.f8922o = esi.f8937n;
        this.f8923p = esi.f8938o;
        this.f8917j = esi.f8939p;
        this.f8918k = esi.f8927d;
    }

    /* renamed from: a */
    public esi mo5198a() {
        esi esi = new esi(new ApplicationErrorReport(), (byte[]) null);
        esi.f8936m = this.f8908a;
        esi.f8929f = this.f8920m;
        esi.f8924a = this.f8909b;
        esi.f8926c = this.f8911d;
        esi.f8925b = this.f8910c;
        esi.f8928e = this.f8912e;
        esi.f8931h = this.f8913f;
        esi.f8932i = this.f8914g;
        esi.f8933j = this.f8915h;
        esi.f8934k = this.f8921n;
        esi.f8935l = this.f8916i;
        esi.f8940q = this.f8919l;
        esi.f8937n = this.f8922o;
        esi.f8938o = this.f8923p;
        esi.f8939p = this.f8917j;
        return esi;
    }
}
