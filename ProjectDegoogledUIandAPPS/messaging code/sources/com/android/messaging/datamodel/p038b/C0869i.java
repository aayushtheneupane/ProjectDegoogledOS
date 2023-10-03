package com.android.messaging.datamodel.p038b;

import android.accounts.Account;
import com.android.vcard.VCardEntry;
import com.android.vcard.VCardProperty;
import java.util.Map;
import p000a.p005b.C0015b;

/* renamed from: com.android.messaging.datamodel.b.i */
public class C0869i extends VCardEntry {

    /* renamed from: tM */
    private final Map f1118tM = new C0015b();

    public C0869i(int i, Account account) {
        super(i, account);
    }

    public void addProperty(VCardProperty vCardProperty) {
        super.addProperty(vCardProperty);
        this.f1118tM.put(vCardProperty.getName(), vCardProperty);
    }
}
