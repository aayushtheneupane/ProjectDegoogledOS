package com.android.p032ex.chips;

import android.net.Uri;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;

/* renamed from: com.android.ex.chips.ra */
public class C0699ra {

    /* renamed from: Av */
    private final int f813Av;

    /* renamed from: Bv */
    private final String f814Bv;

    /* renamed from: Cv */
    private final long f815Cv;

    /* renamed from: Dv */
    private final Uri f816Dv;

    /* renamed from: Ev */
    private final boolean f817Ev = true;

    /* renamed from: Fv */
    private boolean f818Fv;

    /* renamed from: Gv */
    private byte[] f819Gv = null;

    /* renamed from: Hv */
    private int f820Hv;

    /* renamed from: Iv */
    private String f821Iv;

    /* renamed from: Jv */
    private final String f822Jv;

    /* renamed from: Rj */
    private final long f823Rj;
    private final int mEntryType;
    private final String[] mPermissions;

    /* renamed from: ql */
    private final Long f824ql;

    /* renamed from: xv */
    private boolean f825xv;

    /* renamed from: yv */
    private final String f826yv;

    /* renamed from: zv */
    private final String f827zv;

    protected C0699ra(int i, String str, String str2, int i2, String str3, long j, Long l, long j2, Uri uri, boolean z, boolean z2, String str4, String[] strArr) {
        this.mEntryType = i;
        this.f825xv = z;
        this.f826yv = str;
        this.f827zv = str2;
        this.f813Av = i2;
        this.f814Bv = str3;
        this.f823Rj = j;
        this.f824ql = l;
        this.f815Cv = j2;
        this.f816Dv = uri;
        this.f818Fv = z2;
        this.f822Jv = str4;
        this.f820Hv = 0;
        this.f821Iv = null;
        this.mPermissions = strArr;
    }

    /* renamed from: a */
    public static C0699ra m1081a(String str, String str2, boolean z) {
        return new C0699ra(0, str, str2, -1, (String) null, -2, (Long) null, -2, (Uri) null, true, z, (String) null, (String[]) null);
    }

    /* renamed from: b */
    public static C0699ra m1083b(String str, boolean z) {
        Rfc822Token[] rfc822TokenArr = Rfc822Tokenizer.tokenize(str);
        String address = rfc822TokenArr.length > 0 ? rfc822TokenArr[0].getAddress() : str;
        return new C0699ra(0, address, address, -1, (String) null, -1, (Long) null, -1, (Uri) null, true, z, (String) null, (String[]) null);
    }

    /* renamed from: b */
    public static boolean m1084b(long j) {
        return j == -1 || j == -2;
    }

    /* renamed from: c */
    private static String m1085c(int i, String str, String str2) {
        return i > 20 ? str : str2;
    }

    /* renamed from: Ad */
    public boolean mo5647Ad() {
        return this.f817Ev;
    }

    /* renamed from: F */
    public void mo5648F(boolean z) {
    }

    /* renamed from: R */
    public Long mo5649R() {
        return this.f824ql;
    }

    /* renamed from: ga */
    public long mo5651ga() {
        return this.f815Cv;
    }

    public long getContactId() {
        return this.f823Rj;
    }

    public String getDestination() {
        return this.f827zv;
    }

    public String getDisplayName() {
        return this.f826yv;
    }

    public int getEntryType() {
        return this.mEntryType;
    }

    public boolean isSelectable() {
        int i = this.mEntryType;
        return i == 0 || i == 1;
    }

    public boolean isValid() {
        return this.f818Fv;
    }

    /* renamed from: j */
    public synchronized void mo5658j(byte[] bArr) {
        this.f819Gv = bArr;
    }

    /* renamed from: m */
    public String mo5659m() {
        return this.f822Jv;
    }

    /* renamed from: td */
    public String mo5660td() {
        return this.f814Bv;
    }

    public String toString() {
        return this.f826yv + " <" + this.f827zv + ">, isValid=" + this.f818Fv;
    }

    /* renamed from: ud */
    public int mo5662ud() {
        return this.f813Av;
    }

    /* renamed from: vd */
    public int mo5663vd() {
        return this.f820Hv;
    }

    /* renamed from: wd */
    public String mo5664wd() {
        return this.f821Iv;
    }

    /* renamed from: xd */
    public synchronized byte[] mo5665xd() {
        return this.f819Gv;
    }

    /* renamed from: yd */
    public Uri mo5666yd() {
        return this.f816Dv;
    }

    /* renamed from: zd */
    public boolean mo5667zd() {
        return this.f825xv;
    }

    /* renamed from: a */
    public static C0699ra m1079a(String str, int i, String str2, int i2, String str3, long j, Long l, long j2, Uri uri, boolean z, String str4) {
        String str5 = str2;
        return new C0699ra(0, m1085c(i, str, str5), str5, i2, str3, j, l, j2, uri, true, z, str4, (String[]) null);
    }

    /* renamed from: a */
    public static C0699ra m1080a(String str, int i, String str2, int i2, String str3, long j, Long l, long j2, String str4, boolean z, String str5) {
        return new C0699ra(0, m1085c(i, str, str2), str2, i2, str3, j, l, j2, str4 != null ? Uri.parse(str4) : null, false, z, str5, (String[]) null);
    }

    /* renamed from: b */
    public static C0699ra m1082b(String str, int i, String str2, int i2, String str3, long j, Long l, long j2, String str4, boolean z, String str5) {
        return new C0699ra(0, m1085c(i, str, str2), str2, i2, str3, j, l, j2, str4 != null ? Uri.parse(str4) : null, true, z, str5, (String[]) null);
    }

    /* renamed from: a */
    public boolean mo5650a(C0699ra raVar) {
        return raVar != null && this.f823Rj == raVar.f823Rj;
    }
}
