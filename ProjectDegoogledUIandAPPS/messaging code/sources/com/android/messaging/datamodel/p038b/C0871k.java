package com.android.messaging.datamodel.p038b;

import android.accounts.Account;
import com.android.vcard.VCardConfig;
import com.android.vcard.VCardInterpreter;
import com.android.vcard.VCardProperty;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.k */
public class C0871k implements VCardInterpreter {
    private final Account mAccount;
    private C0869i mCurrentEntry;
    private final List mEntryHandlers;
    private final List mEntryStack;
    private final int mVCardType;

    public C0871k() {
        this(VCardConfig.VCARD_TYPE_V21_GENERIC, (Account) null);
    }

    /* renamed from: a */
    public void mo6146a(C0870j jVar) {
        this.mEntryHandlers.add(jVar);
    }

    public void clear() {
        this.mCurrentEntry = null;
        this.mEntryStack.clear();
    }

    public void onEntryEnded() {
        this.mCurrentEntry.consolidateFields();
        for (C0850M a : this.mEntryHandlers) {
            a.mo6118a(this.mCurrentEntry);
        }
        int size = this.mEntryStack.size();
        if (size > 1) {
            C0869i iVar = (C0869i) this.mEntryStack.get(size - 2);
            iVar.addChild(this.mCurrentEntry);
            this.mCurrentEntry = iVar;
        } else {
            this.mCurrentEntry = null;
        }
        this.mEntryStack.remove(size - 1);
    }

    public void onEntryStarted() {
        this.mCurrentEntry = new C0869i(this.mVCardType, this.mAccount);
        this.mEntryStack.add(this.mCurrentEntry);
    }

    public void onPropertyCreated(VCardProperty vCardProperty) {
        this.mCurrentEntry.addProperty(vCardProperty);
    }

    public void onVCardEnded() {
        for (C0850M m : this.mEntryHandlers) {
            if (m.this$0.f1104YC.size() > 0) {
                C0851N n = m.this$0;
                C0853P unused = n.f1105ZC = new C0853P(n.getKey(), m.this$0.f1104YC);
            }
            m.f1103XC.countDown();
        }
    }

    public void onVCardStarted() {
        for (C0850M onStart : this.mEntryHandlers) {
            onStart.onStart();
        }
    }

    public C0871k(int i, Account account) {
        this.mEntryStack = new ArrayList();
        this.mEntryHandlers = new ArrayList();
        this.mVCardType = i;
        this.mAccount = account;
    }
}
