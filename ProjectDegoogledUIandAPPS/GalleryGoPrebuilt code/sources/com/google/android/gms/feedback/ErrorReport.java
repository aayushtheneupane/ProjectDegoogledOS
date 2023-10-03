package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.io.File;
import java.util.List;

/* compiled from: PG */
public class ErrorReport extends eqv implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new erv();

    /* renamed from: A */
    private int f5011A;

    /* renamed from: B */
    private String f5012B;

    /* renamed from: C */
    private String f5013C;

    /* renamed from: D */
    private String f5014D;

    /* renamed from: E */
    private Bundle f5015E;

    /* renamed from: F */
    private boolean f5016F;

    /* renamed from: G */
    private int f5017G;

    /* renamed from: H */
    private int f5018H;

    /* renamed from: I */
    private boolean f5019I;

    /* renamed from: J */
    private String f5020J;

    /* renamed from: K */
    private String f5021K;

    /* renamed from: L */
    private int f5022L;

    /* renamed from: M */
    private String f5023M;

    /* renamed from: N */
    private String f5024N;

    /* renamed from: O */
    private String f5025O;

    /* renamed from: P */
    private String f5026P;

    /* renamed from: Q */
    private String f5027Q;
    @Deprecated

    /* renamed from: R */
    private String f5028R;

    /* renamed from: S */
    private String f5029S;

    /* renamed from: T */
    private BitmapTeleporter f5030T;

    /* renamed from: U */
    private String f5031U;

    /* renamed from: V */
    private esk[] f5032V;

    /* renamed from: W */
    private String[] f5033W;

    /* renamed from: X */
    private boolean f5034X;

    /* renamed from: Y */
    private eso f5035Y;

    /* renamed from: Z */
    private esm f5036Z;

    /* renamed from: a */
    public String f5037a;
    @Deprecated

    /* renamed from: aa */
    private String f5038aa;

    /* renamed from: ab */
    private boolean f5039ab;

    /* renamed from: ac */
    private Bundle f5040ac;

    /* renamed from: ad */
    private List f5041ad;

    /* renamed from: ae */
    private boolean f5042ae;

    /* renamed from: af */
    private Bitmap f5043af;

    /* renamed from: ag */
    private String f5044ag;

    /* renamed from: ah */
    private List f5045ah;

    /* renamed from: ai */
    private int f5046ai;

    /* renamed from: b */
    private ApplicationErrorReport f5047b;

    /* renamed from: c */
    private String f5048c;

    /* renamed from: d */
    private int f5049d;

    /* renamed from: e */
    private String f5050e;

    /* renamed from: f */
    private String f5051f;

    /* renamed from: g */
    private String f5052g;

    /* renamed from: h */
    private String f5053h;

    /* renamed from: i */
    private String f5054i;

    /* renamed from: j */
    private String f5055j;

    /* renamed from: k */
    private String f5056k;

    /* renamed from: l */
    private int f5057l;

    /* renamed from: m */
    private String f5058m;

    /* renamed from: n */
    private String f5059n;

    /* renamed from: o */
    private String f5060o;

    /* renamed from: p */
    private String f5061p;

    /* renamed from: q */
    private String f5062q;

    /* renamed from: r */
    private String[] f5063r;

    /* renamed from: s */
    private String[] f5064s;

    /* renamed from: t */
    private String[] f5065t;

    /* renamed from: u */
    private String f5066u;

    /* renamed from: v */
    private String f5067v;

    /* renamed from: w */
    private byte[] f5068w;

    /* renamed from: x */
    private int f5069x;

    /* renamed from: y */
    private int f5070y;

    /* renamed from: z */
    private int f5071z;

    public ErrorReport() {
        this.f5047b = new ApplicationErrorReport();
    }

    public ErrorReport(ApplicationErrorReport applicationErrorReport, String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, String str11, String str12, String str13, String[] strArr, String[] strArr2, String[] strArr3, String str14, String str15, byte[] bArr, int i3, int i4, int i5, int i6, String str16, String str17, String str18, Bundle bundle, boolean z, int i7, int i8, boolean z2, String str19, String str20, int i9, String str21, String str22, String str23, String str24, String str25, String str26, String str27, BitmapTeleporter bitmapTeleporter, String str28, esk[] eskArr, String[] strArr4, boolean z3, String str29, eso eso, esm esm, String str30, boolean z4, Bundle bundle2, List list, boolean z5, Bitmap bitmap, String str31, List list2, int i10) {
        new ApplicationErrorReport();
        this.f5047b = applicationErrorReport;
        this.f5048c = str;
        this.f5049d = i;
        this.f5050e = str2;
        this.f5051f = str3;
        this.f5052g = str4;
        this.f5053h = str5;
        this.f5054i = str6;
        this.f5055j = str7;
        this.f5056k = str8;
        this.f5057l = i2;
        this.f5058m = str9;
        this.f5059n = str10;
        this.f5060o = str11;
        this.f5061p = str12;
        this.f5062q = str13;
        this.f5063r = strArr;
        this.f5064s = strArr2;
        this.f5065t = strArr3;
        this.f5066u = str14;
        this.f5067v = str15;
        this.f5068w = bArr;
        this.f5069x = i3;
        this.f5070y = i4;
        this.f5071z = i5;
        this.f5011A = i6;
        this.f5012B = str16;
        this.f5013C = str17;
        this.f5014D = str18;
        this.f5015E = bundle;
        this.f5016F = z;
        this.f5017G = i7;
        this.f5018H = i8;
        this.f5019I = z2;
        this.f5020J = str19;
        this.f5021K = str20;
        this.f5022L = i9;
        this.f5023M = str21;
        this.f5024N = str22;
        this.f5025O = str23;
        this.f5026P = str24;
        this.f5027Q = str25;
        this.f5028R = str26;
        this.f5029S = str27;
        this.f5030T = bitmapTeleporter;
        this.f5031U = str28;
        this.f5032V = eskArr;
        this.f5033W = strArr4;
        this.f5034X = z3;
        this.f5037a = str29;
        this.f5035Y = eso;
        this.f5036Z = esm;
        this.f5038aa = str30;
        this.f5039ab = z4;
        this.f5040ac = bundle2;
        this.f5041ad = list;
        this.f5042ae = z5;
        this.f5043af = bitmap;
        this.f5044ag = str31;
        this.f5045ah = list2;
        this.f5046ai = i10;
    }

    public ErrorReport(esi esi, File file) {
        ApplicationErrorReport.CrashInfo crashInfo;
        this.f5047b = new ApplicationErrorReport();
        if (esi != null) {
            Bundle bundle = esi.f8925b;
            if (bundle != null && bundle.size() > 0) {
                this.f5015E = esi.f8925b;
            }
            if (!TextUtils.isEmpty(esi.f8924a)) {
                this.f5013C = esi.f8924a;
            }
            if (!TextUtils.isEmpty(esi.f8926c)) {
                this.f5048c = esi.f8926c;
            }
            ApplicationErrorReport applicationErrorReport = esi.f8927d;
            if (applicationErrorReport != null) {
                crashInfo = applicationErrorReport.crashInfo;
            } else {
                crashInfo = null;
            }
            if (crashInfo != null) {
                this.f5024N = crashInfo.throwMethodName;
                this.f5022L = crashInfo.throwLineNumber;
                this.f5023M = crashInfo.throwClassName;
                this.f5025O = crashInfo.stackTrace;
                this.f5020J = crashInfo.exceptionClassName;
                this.f5026P = crashInfo.exceptionMessage;
                this.f5021K = crashInfo.throwFileName;
            }
            eso eso = esi.f8933j;
            if (eso != null) {
                this.f5035Y = eso;
            }
            if (!TextUtils.isEmpty(esi.f8928e)) {
                this.f5027Q = esi.f8928e;
            }
            if (!TextUtils.isEmpty(esi.f8930g)) {
                this.f5047b.packageName = esi.f8930g;
            }
            if (!TextUtils.isEmpty(esi.f8937n)) {
                this.f5044ag = esi.f8937n;
            }
            Bitmap bitmap = esi.f8936m;
            if (bitmap != null) {
                this.f5043af = bitmap;
            }
            if (file != null) {
                BitmapTeleporter bitmapTeleporter = esi.f8929f;
                if (bitmapTeleporter != null) {
                    this.f5030T = bitmapTeleporter;
                    bitmapTeleporter.f4998a = file;
                }
                List<esk> list = esi.f8931h;
                if (list != null && !list.isEmpty()) {
                    for (esk esk : list) {
                        esk.f8941a = file;
                    }
                    this.f5032V = (esk[]) list.toArray(new esk[esi.f8931h.size()]);
                }
            }
            esm esm = esi.f8934k;
            if (esm != null) {
                this.f5036Z = esm;
            }
            this.f5034X = esi.f8932i;
            this.f5042ae = esi.f8935l;
            this.f5016F = esi.f8938o;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m97a(parcel, 2, (Parcelable) this.f5047b, i);
        abj.m98a(parcel, 3, this.f5048c);
        abj.m114b(parcel, 4, this.f5049d);
        abj.m98a(parcel, 5, this.f5050e);
        abj.m98a(parcel, 6, this.f5051f);
        abj.m98a(parcel, 7, this.f5052g);
        abj.m98a(parcel, 8, this.f5053h);
        abj.m98a(parcel, 9, this.f5054i);
        abj.m98a(parcel, 10, this.f5055j);
        abj.m98a(parcel, 11, this.f5056k);
        abj.m114b(parcel, 12, this.f5057l);
        abj.m98a(parcel, 13, this.f5058m);
        abj.m98a(parcel, 14, this.f5059n);
        abj.m98a(parcel, 15, this.f5060o);
        abj.m98a(parcel, 16, this.f5061p);
        abj.m98a(parcel, 17, this.f5062q);
        abj.m104a(parcel, 18, this.f5063r);
        abj.m104a(parcel, 19, this.f5064s);
        abj.m104a(parcel, 20, this.f5065t);
        abj.m98a(parcel, 21, this.f5066u);
        abj.m98a(parcel, 22, this.f5067v);
        abj.m101a(parcel, 23, this.f5068w);
        abj.m114b(parcel, 24, this.f5069x);
        abj.m114b(parcel, 25, this.f5070y);
        abj.m114b(parcel, 26, this.f5071z);
        abj.m114b(parcel, 27, this.f5011A);
        abj.m98a(parcel, 28, this.f5012B);
        abj.m98a(parcel, 29, this.f5013C);
        abj.m98a(parcel, 30, this.f5014D);
        abj.m95a(parcel, 31, this.f5015E);
        abj.m100a(parcel, 32, this.f5016F);
        abj.m114b(parcel, 33, this.f5017G);
        abj.m114b(parcel, 34, this.f5018H);
        abj.m100a(parcel, 35, this.f5019I);
        abj.m98a(parcel, 36, this.f5020J);
        abj.m98a(parcel, 37, this.f5021K);
        abj.m114b(parcel, 38, this.f5022L);
        abj.m98a(parcel, 39, this.f5023M);
        abj.m98a(parcel, 40, this.f5024N);
        abj.m98a(parcel, 41, this.f5025O);
        abj.m98a(parcel, 42, this.f5026P);
        abj.m98a(parcel, 43, this.f5027Q);
        abj.m98a(parcel, 44, this.f5028R);
        abj.m98a(parcel, 45, this.f5029S);
        abj.m97a(parcel, 46, (Parcelable) this.f5030T, i);
        abj.m98a(parcel, 47, this.f5031U);
        abj.m103a(parcel, 48, (Parcelable[]) this.f5032V, i);
        abj.m104a(parcel, 49, this.f5033W);
        abj.m100a(parcel, 50, this.f5034X);
        abj.m98a(parcel, 51, this.f5037a);
        abj.m97a(parcel, 52, (Parcelable) this.f5035Y, i);
        abj.m97a(parcel, 53, (Parcelable) this.f5036Z, i);
        abj.m98a(parcel, 54, this.f5038aa);
        abj.m100a(parcel, 55, this.f5039ab);
        abj.m95a(parcel, 56, this.f5040ac);
        abj.m115b(parcel, 57, this.f5041ad);
        abj.m100a(parcel, 58, this.f5042ae);
        abj.m97a(parcel, 59, (Parcelable) this.f5043af, i);
        abj.m98a(parcel, 60, this.f5044ag);
        abj.m99a(parcel, 61, this.f5045ah);
        abj.m114b(parcel, 62, this.f5046ai);
        abj.m92a(parcel, a);
    }
}
