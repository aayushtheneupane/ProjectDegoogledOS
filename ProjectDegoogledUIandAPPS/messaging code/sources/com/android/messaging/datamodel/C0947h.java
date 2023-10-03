package com.android.messaging.datamodel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.action.C0814d;
import com.android.messaging.datamodel.action.C0815e;
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

/* renamed from: com.android.messaging.datamodel.h */
public abstract class C0947h {

    /* renamed from: Dx */
    private String f1351Dx;

    /* renamed from: Ex */
    private boolean f1352Ex;

    public static C0947h get() {
        return C0967f.get().getDataModel();
    }

    /* renamed from: I */
    public void mo6586I(boolean z) {
        this.f1352Ex = z;
    }

    /* renamed from: K */
    public abstract C0889A mo6587K(String str);

    /* renamed from: L */
    public boolean mo6588L(String str) {
        return !TextUtils.isEmpty(this.f1351Dx) && TextUtils.equals(this.f1351Dx, str);
    }

    /* renamed from: M */
    public boolean mo6589M(String str) {
        return this.f1352Ex || mo6588L(str);
    }

    /* renamed from: N */
    public void mo6590N(String str) {
        this.f1351Dx = str;
    }

    /* renamed from: Qd */
    public abstract void mo6591Qd();

    /* renamed from: Yd */
    public abstract C0918c mo6592Yd();

    /* renamed from: Zd */
    public abstract C0890B mo6593Zd();

    /* renamed from: _d */
    public abstract C0814d mo6594_d();

    /* renamed from: a */
    public abstract C0892D mo6595a(C0891C c);

    /* renamed from: a */
    public abstract C0906S mo6596a(String str, Context context, C0905Q q);

    /* renamed from: a */
    public abstract C0915aa mo6597a(Context context, C0912Y y);

    /* renamed from: a */
    public abstract C0916b mo6598a(Context context, C0914a aVar);

    /* renamed from: a */
    public abstract C0921da mo6599a(Context context, Uri uri);

    /* renamed from: a */
    public abstract C0921da mo6600a(Context context, MessagePartData messagePartData);

    /* renamed from: a */
    public abstract C0922e mo6601a(Context context, C0920d dVar);

    /* renamed from: a */
    public abstract C0931n mo6602a(Context context, C0924g gVar, String str);

    /* renamed from: a */
    public abstract C0933p mo6603a(Context context, C0932o oVar, boolean z);

    /* renamed from: ae */
    public abstract C0815e mo6604ae();

    /* renamed from: b */
    public abstract C0902N mo6605b(ParticipantData participantData);

    /* renamed from: be */
    public abstract C0779U mo6606be();

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract void mo6607c(SQLiteDatabase sQLiteDatabase);

    /* renamed from: ce */
    public abstract void mo6608ce();

    public abstract C0955p getDatabase();

    /* renamed from: h */
    public abstract C0896H mo6610h(Context context);

    /* renamed from: i */
    public abstract C0907T mo6611i(Context context);
}
