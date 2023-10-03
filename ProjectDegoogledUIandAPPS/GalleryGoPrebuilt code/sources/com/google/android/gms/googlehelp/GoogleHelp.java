package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.util.List;

/* compiled from: PG */
public class GoogleHelp extends eqv implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new eta();

    /* renamed from: A */
    private int f5072A;

    /* renamed from: B */
    private PendingIntent f5073B;

    /* renamed from: C */
    private boolean f5074C;

    /* renamed from: D */
    private int f5075D;

    /* renamed from: E */
    private String f5076E;

    /* renamed from: F */
    private boolean f5077F;

    /* renamed from: G */
    private String f5078G;

    /* renamed from: a */
    public Account f5079a;

    /* renamed from: b */
    public Uri f5080b;

    /* renamed from: c */
    public List f5081c;

    /* renamed from: d */
    public ErrorReport f5082d;

    /* renamed from: e */
    public TogglingData f5083e;

    /* renamed from: f */
    public int f5084f;

    /* renamed from: g */
    public boolean f5085g;

    /* renamed from: h */
    public esv f5086h;

    /* renamed from: i */
    private final int f5087i;

    /* renamed from: j */
    private String f5088j;

    /* renamed from: k */
    private Bundle f5089k;

    /* renamed from: l */
    private String f5090l;

    /* renamed from: m */
    private String f5091m;

    /* renamed from: n */
    private Bitmap f5092n;

    /* renamed from: o */
    private boolean f5093o;

    /* renamed from: p */
    private boolean f5094p;

    /* renamed from: q */
    private List f5095q;
    @Deprecated

    /* renamed from: r */
    private Bundle f5096r;
    @Deprecated

    /* renamed from: s */
    private Bitmap f5097s;
    @Deprecated

    /* renamed from: t */
    private byte[] f5098t;
    @Deprecated

    /* renamed from: u */
    private int f5099u;
    @Deprecated

    /* renamed from: v */
    private int f5100v;

    /* renamed from: w */
    private String f5101w;

    /* renamed from: x */
    private eso f5102x;

    /* renamed from: y */
    private List f5103y;

    /* renamed from: z */
    private boolean f5104z;

    @Deprecated
    private GoogleHelp(int i, String str, Account account, Bundle bundle, String str2, String str3, Bitmap bitmap, boolean z, boolean z2, List list, Bundle bundle2, Bitmap bitmap2, byte[] bArr, int i2, int i3, String str4, Uri uri, List list2, int i4, eso eso, List list3, boolean z3, ErrorReport errorReport, TogglingData togglingData, int i5, PendingIntent pendingIntent, int i6, boolean z4, boolean z5, int i7, String str5, boolean z6) {
        eso eso2;
        int i8 = i;
        ErrorReport errorReport2 = errorReport;
        this.f5082d = new ErrorReport();
        if (!TextUtils.isEmpty(str)) {
            this.f5087i = i8;
            this.f5084f = i6;
            this.f5074C = z4;
            this.f5085g = z5;
            this.f5075D = i7;
            this.f5076E = str5;
            this.f5088j = str;
            this.f5079a = account;
            this.f5089k = bundle;
            this.f5090l = str2;
            this.f5091m = str3;
            this.f5092n = bitmap;
            this.f5093o = z;
            this.f5094p = z2;
            this.f5077F = z6;
            this.f5095q = list;
            this.f5073B = pendingIntent;
            this.f5096r = bundle2;
            this.f5097s = bitmap2;
            this.f5098t = bArr;
            this.f5099u = i2;
            this.f5100v = i3;
            this.f5101w = str4;
            this.f5080b = uri;
            this.f5081c = list2;
            if (i8 < 4) {
                eso eso3 = new eso();
                eso3.f8948a = i4;
                this.f5102x = eso3;
            } else {
                if (eso == null) {
                    eso2 = new eso();
                } else {
                    eso2 = eso;
                }
                this.f5102x = eso2;
            }
            this.f5103y = list3;
            this.f5104z = z3;
            this.f5082d = errorReport2;
            if (errorReport2 != null) {
                errorReport2.f5037a = "GoogleHelp";
            }
            this.f5083e = togglingData;
            this.f5072A = i5;
            return;
        }
        throw new IllegalStateException("Help requires a non-empty appContext");
    }

    public GoogleHelp(int i, String str, Account account, Bundle bundle, String str2, String str3, Bitmap bitmap, boolean z, boolean z2, List list, Bundle bundle2, Bitmap bitmap2, byte[] bArr, int i2, int i3, String str4, Uri uri, List list2, int i4, eso eso, List list3, boolean z3, ErrorReport errorReport, TogglingData togglingData, int i5, PendingIntent pendingIntent, int i6, boolean z4, boolean z5, int i7, String str5, boolean z6, String str6) {
        this(i, str, account, bundle, str2, str3, bitmap, z, z2, list, bundle2, bitmap2, bArr, i2, i3, str4, uri, list2, i4, eso, list3, z3, errorReport, togglingData, i5, pendingIntent, i6, z4, z5, i7, str5, z6);
        this.f5078G = str6;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleHelp(java.lang.String r34) {
        /*
            r33 = this;
            r0 = r33
            r2 = r34
            java.util.ArrayList r1 = new java.util.ArrayList
            r10 = r1
            r1.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r18 = r1
            r1.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r21 = r1
            r1.<init>()
            com.google.android.gms.feedback.ErrorReport r1 = new com.google.android.gms.feedback.ErrorReport
            r23 = r1
            r1.<init>()
            r1 = 14
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 1
            r9 = 1
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r19 = 3
            r20 = 0
            r22 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = -1
            r28 = 0
            r29 = 0
            r30 = 200(0xc8, float:2.8E-43)
            r31 = 0
            r32 = 0
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.googlehelp.GoogleHelp.<init>(java.lang.String):void");
    }

    /* renamed from: a */
    public static Bitmap m4965a(Activity activity) {
        try {
            View rootView = activity.getWindow().getDecorView().getRootView();
            Bitmap createBitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.RGB_565);
            rootView.draw(new Canvas(createBitmap));
            return createBitmap;
        } catch (Error | Exception e) {
            Log.w("gH_GoogleHelp", "Get screenshot failed!", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f5087i);
        abj.m98a(parcel, 2, this.f5088j);
        abj.m97a(parcel, 3, (Parcelable) this.f5079a, i);
        abj.m95a(parcel, 4, this.f5089k);
        abj.m100a(parcel, 5, this.f5093o);
        abj.m100a(parcel, 6, this.f5094p);
        abj.m99a(parcel, 7, this.f5095q);
        abj.m95a(parcel, 10, this.f5096r);
        abj.m97a(parcel, 11, (Parcelable) this.f5097s, i);
        abj.m98a(parcel, 14, this.f5101w);
        abj.m97a(parcel, 15, (Parcelable) this.f5080b, i);
        abj.m115b(parcel, 16, this.f5081c);
        abj.m114b(parcel, 17, 0);
        abj.m115b(parcel, 18, this.f5103y);
        abj.m101a(parcel, 19, this.f5098t);
        abj.m114b(parcel, 20, this.f5099u);
        abj.m114b(parcel, 21, this.f5100v);
        abj.m100a(parcel, 22, this.f5104z);
        abj.m97a(parcel, 23, (Parcelable) this.f5082d, i);
        abj.m97a(parcel, 25, (Parcelable) this.f5102x, i);
        abj.m98a(parcel, 28, this.f5090l);
        abj.m97a(parcel, 31, (Parcelable) this.f5083e, i);
        abj.m114b(parcel, 32, this.f5072A);
        abj.m97a(parcel, 33, (Parcelable) this.f5073B, i);
        abj.m98a(parcel, 34, this.f5091m);
        abj.m97a(parcel, 35, (Parcelable) this.f5092n, i);
        abj.m114b(parcel, 36, this.f5084f);
        abj.m100a(parcel, 37, this.f5074C);
        abj.m100a(parcel, 38, this.f5085g);
        abj.m114b(parcel, 39, this.f5075D);
        abj.m98a(parcel, 40, this.f5076E);
        abj.m100a(parcel, 41, this.f5077F);
        abj.m98a(parcel, 42, this.f5078G);
        abj.m92a(parcel, a);
    }
}
