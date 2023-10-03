package com.android.vcard;

import android.accounts.Account;
import java.util.ArrayList;
import java.util.List;

public class VCardEntryConstructor implements VCardInterpreter {
    private static String LOG_TAG = "vCard";
    private final Account mAccount;
    private VCardEntry mCurrentEntry;
    private final List mEntryHandlers;
    private final List mEntryStack;
    private final int mVCardType;

    public VCardEntryConstructor() {
        this(VCardConfig.VCARD_TYPE_V21_GENERIC, (Account) null, (String) null);
    }

    public void addEntryHandler(VCardEntryHandler vCardEntryHandler) {
        this.mEntryHandlers.add(vCardEntryHandler);
    }

    public void clear() {
        this.mCurrentEntry = null;
        this.mEntryStack.clear();
    }

    public void onEntryEnded() {
        this.mCurrentEntry.consolidateFields();
        for (VCardEntryHandler onEntryCreated : this.mEntryHandlers) {
            onEntryCreated.onEntryCreated(this.mCurrentEntry);
        }
        int size = this.mEntryStack.size();
        if (size > 1) {
            VCardEntry vCardEntry = (VCardEntry) this.mEntryStack.get(size - 2);
            vCardEntry.addChild(this.mCurrentEntry);
            this.mCurrentEntry = vCardEntry;
        } else {
            this.mCurrentEntry = null;
        }
        this.mEntryStack.remove(size - 1);
    }

    public void onEntryStarted() {
        this.mCurrentEntry = new VCardEntry(this.mVCardType, this.mAccount);
        this.mEntryStack.add(this.mCurrentEntry);
    }

    public void onPropertyCreated(VCardProperty vCardProperty) {
        this.mCurrentEntry.addProperty(vCardProperty);
    }

    public void onVCardEnded() {
        for (VCardEntryHandler onEnd : this.mEntryHandlers) {
            onEnd.onEnd();
        }
    }

    public void onVCardStarted() {
        for (VCardEntryHandler onStart : this.mEntryHandlers) {
            onStart.onStart();
        }
    }

    public VCardEntryConstructor(int i) {
        this(i, (Account) null, (String) null);
    }

    public VCardEntryConstructor(int i, Account account) {
        this(i, account, (String) null);
    }

    @Deprecated
    public VCardEntryConstructor(int i, Account account, String str) {
        this.mEntryStack = new ArrayList();
        this.mEntryHandlers = new ArrayList();
        this.mVCardType = i;
        this.mAccount = account;
    }
}
