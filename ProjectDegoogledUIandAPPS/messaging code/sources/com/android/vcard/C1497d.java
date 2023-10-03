package com.android.vcard;

import com.android.vcard.VCardEntry;
import java.util.List;

/* renamed from: com.android.vcard.d */
class C1497d implements VCardEntry.EntryElementIterator {
    private final List mOperationList;

    /* renamed from: qM */
    private final int f2374qM;

    public C1497d(VCardEntry vCardEntry, List list, int i) {
        this.mOperationList = list;
        this.f2374qM = i;
    }

    public boolean onElement(VCardEntry.EntryElement entryElement) {
        if (entryElement.isEmpty()) {
            return true;
        }
        entryElement.constructInsertOperation(this.mOperationList, this.f2374qM);
        return true;
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
