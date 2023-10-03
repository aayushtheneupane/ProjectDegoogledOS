package com.android.vcard;

import com.android.vcard.VCardEntry;

/* renamed from: com.android.vcard.e */
class C1498e implements VCardEntry.EntryElementIterator {

    /* renamed from: rM */
    private boolean f2375rM = true;

    /* synthetic */ C1498e(VCardEntry vCardEntry, C1496c cVar) {
    }

    public boolean getResult() {
        return this.f2375rM;
    }

    public boolean onElement(VCardEntry.EntryElement entryElement) {
        if (entryElement.isEmpty()) {
            return true;
        }
        this.f2375rM = false;
        return false;
    }

    public void onElementGroupEnded() {
    }

    public void onElementGroupStarted(VCardEntry.EntryLabel entryLabel) {
    }

    public void onIterationEnded() {
    }

    public void onIterationStarted() {
    }
}
