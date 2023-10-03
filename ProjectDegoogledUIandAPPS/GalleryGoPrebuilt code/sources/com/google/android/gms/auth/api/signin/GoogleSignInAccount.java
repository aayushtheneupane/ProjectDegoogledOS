package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PG */
public class GoogleSignInAccount extends eqv implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new eit();

    /* renamed from: a */
    private final int f4956a;

    /* renamed from: b */
    private String f4957b;

    /* renamed from: c */
    private String f4958c;

    /* renamed from: d */
    private String f4959d;

    /* renamed from: e */
    private String f4960e;

    /* renamed from: f */
    private Uri f4961f;

    /* renamed from: g */
    private String f4962g;

    /* renamed from: h */
    private long f4963h;

    /* renamed from: i */
    private String f4964i;

    /* renamed from: j */
    private List f4965j;

    /* renamed from: k */
    private String f4966k;

    /* renamed from: l */
    private String f4967l;

    /* renamed from: m */
    private Set f4968m = new HashSet();

    public GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.f4956a = i;
        this.f4957b = str;
        this.f4958c = str2;
        this.f4959d = str3;
        this.f4960e = str4;
        this.f4961f = uri;
        this.f4962g = str5;
        this.f4963h = j;
        this.f4964i = str6;
        this.f4965j = list;
        this.f4966k = str7;
        this.f4967l = str8;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GoogleSignInAccount) {
            GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
            return googleSignInAccount.f4964i.equals(this.f4964i) && googleSignInAccount.m4939a().equals(m4939a());
        }
    }

    /* renamed from: a */
    public static GoogleSignInAccount m4938a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl", (String) null);
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        GoogleSignInAccount googleSignInAccount = r3;
        GoogleSignInAccount googleSignInAccount2 = new GoogleSignInAccount(3, jSONObject.optString("id"), jSONObject.optString("tokenId", (String) null), jSONObject.optString("email", (String) null), jSONObject.optString("displayName", (String) null), parse, (String) null, Long.valueOf(parseLong).longValue(), abj.m88a(jSONObject.getString("obfuscatedIdentifier")), new ArrayList((Collection) abj.m85a((Object) hashSet)), jSONObject.optString("givenName", (String) null), jSONObject.optString("familyName", (String) null));
        GoogleSignInAccount googleSignInAccount3 = googleSignInAccount;
        googleSignInAccount3.f4962g = jSONObject.optString("serverAuthCode", (String) null);
        return googleSignInAccount3;
    }

    /* renamed from: a */
    private final Set m4939a() {
        HashSet hashSet = new HashSet(this.f4965j);
        hashSet.addAll(this.f4968m);
        return hashSet;
    }

    public final int hashCode() {
        return ((this.f4964i.hashCode() + 527) * 31) + m4939a().hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f4956a);
        abj.m98a(parcel, 2, this.f4957b);
        abj.m98a(parcel, 3, this.f4958c);
        abj.m98a(parcel, 4, this.f4959d);
        abj.m98a(parcel, 5, this.f4960e);
        abj.m97a(parcel, 6, (Parcelable) this.f4961f, i);
        abj.m98a(parcel, 7, this.f4962g);
        abj.m94a(parcel, 8, this.f4963h);
        abj.m98a(parcel, 9, this.f4964i);
        abj.m115b(parcel, 10, this.f4965j);
        abj.m98a(parcel, 11, this.f4966k);
        abj.m98a(parcel, 12, this.f4967l);
        abj.m92a(parcel, a);
    }
}
