package com.android.messaging.datamodel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.android.messaging.datamodel.action.C0814d;
import com.android.messaging.datamodel.action.C0815e;
import com.android.messaging.datamodel.action.FixupMessageStatusOnStartupAction;
import com.android.messaging.datamodel.action.ProcessPendingMessagesAction;
import com.android.messaging.datamodel.action.SyncMessagesAction;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.datamodel.data.C0890B;
import com.android.messaging.datamodel.data.C0891C;
import com.android.messaging.datamodel.data.C0892D;
import com.android.messaging.datamodel.data.C0896H;
import com.android.messaging.datamodel.data.C0902N;
import com.android.messaging.datamodel.data.C0905Q;
import com.android.messaging.datamodel.data.C0906S;
import com.android.messaging.datamodel.data.C0907T;
import com.android.messaging.datamodel.data.C0912Y;
import com.android.messaging.datamodel.data.C0914a;
import com.android.messaging.datamodel.data.C0915aa;
import com.android.messaging.datamodel.data.C0916b;
import com.android.messaging.datamodel.data.C0918c;
import com.android.messaging.datamodel.data.C0920d;
import com.android.messaging.datamodel.data.C0921da;
import com.android.messaging.datamodel.data.C0922e;
import com.android.messaging.datamodel.data.C0924g;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.datamodel.data.C0932o;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1467p;
import com.android.messaging.util.C1468pa;
import com.android.messaging.util.C1474sa;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.android.messaging.datamodel.k */
public class C0950k extends C0947h {

    /* renamed from: Ix */
    private static C1467p f1353Ix;
    /* access modifiers changed from: private */

    /* renamed from: Jx */
    public static final ConcurrentHashMap f1354Jx = new ConcurrentHashMap();

    /* renamed from: Fx */
    private final C0814d f1355Fx = new C0814d();

    /* renamed from: Gx */
    private final C0815e f1356Gx = new C0815e();

    /* renamed from: Hx */
    private final C0779U f1357Hx;
    /* access modifiers changed from: private */
    public final Context mContext;

    /* renamed from: zb */
    private final C0951l f1358zb;

    public C0950k(Context context) {
        this.mContext = context;
        this.f1358zb = C0951l.getInstance(context);
        this.f1357Hx = new C0779U();
        if (C1464na.m3759Zj()) {
            m2128_n();
        } else {
            f1353Ix = new C1467p(context);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: _n */
    public void m2128_n() {
        C1474sa.m3794a(new C0949j(this));
    }

    /* renamed from: ia */
    public static C1467p m2132ia(int i) {
        if (C1464na.m3759Zj()) {
            return (C1467p) f1354Jx.get(Integer.valueOf(i));
        }
        return f1353Ix;
    }

    /* renamed from: K */
    public C0889A mo6587K(String str) {
        return new C0889A(str);
    }

    /* renamed from: Qd */
    public void mo6591Qd() {
        this.f1357Hx.mo5919j(this.mContext);
        ParticipantRefresh.m1287oe();
    }

    /* renamed from: Yd */
    public C0918c mo6592Yd() {
        return new C0918c();
    }

    /* renamed from: Zd */
    public C0890B mo6593Zd() {
        return new C0890B();
    }

    /* renamed from: _d */
    public C0814d mo6594_d() {
        return this.f1355Fx;
    }

    /* renamed from: ae */
    public C0815e mo6604ae() {
        return this.f1356Gx;
    }

    /* renamed from: be */
    public C0779U mo6606be() {
        return this.f1357Hx;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo6607c(SQLiteDatabase sQLiteDatabase) {
        C1430e.m3630w("MessagingApp", "Rebuilt databases: reseting related state");
        C1451h Hd = C1451h.m3724Hd();
        Hd.putLong("last_full_sync_time_millis", -1);
        Hd.putLong("last_sync_time_millis", -1);
    }

    /* renamed from: ce */
    public void mo6608ce() {
        FixupMessageStatusOnStartupAction.m1368Ge();
        ProcessPendingMessagesAction.m1417Ie();
        SyncMessagesAction.m1448Ke();
        if (C1464na.m3759Zj()) {
            ((C1468pa) C1474sa.getDefault().mo8231mk()).mo8204a(new C0948i(this));
        }
    }

    public C0955p getDatabase() {
        C1424b.m3584Gj();
        return this.f1358zb.getDatabase();
    }

    /* renamed from: h */
    public C0896H mo6610h(Context context) {
        return new C0896H(context);
    }

    /* renamed from: i */
    public C0907T mo6611i(Context context) {
        return new C0907T(context);
    }

    /* renamed from: a */
    public C0933p mo6603a(Context context, C0932o oVar, boolean z) {
        return new C0933p(context, oVar, z);
    }

    /* renamed from: b */
    public C0902N mo6605b(ParticipantData participantData) {
        return new C0902N(participantData);
    }

    /* renamed from: a */
    public C0931n mo6602a(Context context, C0924g gVar, String str) {
        return new C0931n(context, gVar, str);
    }

    /* renamed from: a */
    public C0922e mo6601a(Context context, C0920d dVar) {
        return new C0922e(context, dVar);
    }

    /* renamed from: a */
    public C0916b mo6598a(Context context, C0914a aVar) {
        return new C0916b(context, aVar);
    }

    /* renamed from: a */
    public C0892D mo6595a(C0891C c) {
        return new C0892D(c);
    }

    /* renamed from: a */
    public C0906S mo6596a(String str, Context context, C0905Q q) {
        return new C0906S(str, context, q);
    }

    /* renamed from: a */
    public C0921da mo6600a(Context context, MessagePartData messagePartData) {
        C0921da daVar = new C0921da(context, messagePartData.getContentUri());
        C1424b.m3592ia(messagePartData.mo6314hh());
        return daVar;
    }

    /* renamed from: a */
    public C0921da mo6599a(Context context, Uri uri) {
        return new C0921da(context, uri);
    }

    /* renamed from: a */
    public C0915aa mo6597a(Context context, C0912Y y) {
        return new C0915aa(context, y);
    }
}
