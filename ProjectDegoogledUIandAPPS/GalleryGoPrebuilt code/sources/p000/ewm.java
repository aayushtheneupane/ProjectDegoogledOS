package p000;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import org.json.JSONException;

/* renamed from: ewm */
/* compiled from: PG */
public final class ewm extends epv implements ewc {

    /* renamed from: r */
    private final boolean f9134r;

    /* renamed from: s */
    private final epk f9135s;

    /* renamed from: t */
    private final Bundle f9136t;

    /* renamed from: u */
    private final Integer f9137u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ewm(Context context, Looper looper, epk epk, ekt ekt, eku eku) {
        super(context, looper, 44, epk, ekt, eku);
        epk epk2 = epk;
        ewd ewd = epk2.f8781f;
        Integer num = epk2.f8782g;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable) null);
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        if (ewd != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", (String) null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", (String) null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", (String) null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        this.f9134r = true;
        this.f9135s = epk2;
        this.f9136t = bundle;
        this.f9137u = epk2.f8782g;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo4883a() {
        return "com.google.android.gms.signin.service.START";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final String mo4884b() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* renamed from: c */
    public final int mo4885c() {
        return 12451000;
    }

    /* renamed from: g */
    public final boolean mo5120g() {
        return this.f9134r;
    }

    /* renamed from: m */
    public final void mo5333m() {
        try {
            ((ewj) mo5126p()).mo5340a(this.f9137u.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    /* renamed from: n */
    public final void mo5334n() {
        mo5112a((epc) new epe(this));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ IInterface mo4882a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (!(queryLocalInterface instanceof ewj)) {
            return new ewi(iBinder);
        }
        return (ewj) queryLocalInterface;
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public final Bundle mo5125o() {
        if (!this.f8749c.getPackageName().equals(this.f9135s.f8779d)) {
            this.f9136t.putString("com.google.android.gms.signin.internal.realClientPackageName", this.f9135s.f8779d);
        }
        return this.f9136t;
    }

    /* renamed from: a */
    public final void mo5331a(eqg eqg, boolean z) {
        try {
            ((ewj) mo5126p()).mo5341a(eqg, this.f9137u.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    /* renamed from: a */
    public final void mo5332a(ewh ewh) {
        abj.m86a((Object) ewh, (Object) "Expecting a valid ISignInCallbacks");
        try {
            Account account = new Account("<<default account>>", "com.google");
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(account.name)) {
                eiu a = eiu.m7613a(this.f8749c);
                String a2 = a.mo4861a("defaultGoogleSignInAccount");
                if (!TextUtils.isEmpty(a2)) {
                    StringBuilder sb = new StringBuilder(String.valueOf(a2).length() + 20);
                    sb.append("googleSignInAccount");
                    sb.append(":");
                    sb.append(a2);
                    String a3 = a.mo4861a(sb.toString());
                    if (a3 != null) {
                        try {
                            googleSignInAccount = GoogleSignInAccount.m4938a(a3);
                        } catch (JSONException e) {
                        }
                    }
                }
            }
            ((ewj) mo5126p()).mo5342a(new ewn(new eqq(account, this.f9137u.intValue(), googleSignInAccount)), ewh);
        } catch (RemoteException e2) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                ewh.mo5020a(new ewp());
            } catch (RemoteException e3) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e2);
            }
        }
    }
}
