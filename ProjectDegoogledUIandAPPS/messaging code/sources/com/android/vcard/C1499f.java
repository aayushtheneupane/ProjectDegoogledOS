package com.android.vcard;

import com.android.vcard.VCardEntry;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.vcard.f */
class C1499f implements VCardEntry.EntryElementIterator {
    private StringBuilder mBuilder;

    /* renamed from: sM */
    private boolean f2376sM;
    final /* synthetic */ VCardEntry this$0;

    /* synthetic */ C1499f(VCardEntry vCardEntry, C1496c cVar) {
        this.this$0 = vCardEntry;
    }

    public boolean onElement(VCardEntry.EntryElement entryElement) {
        if (!this.f2376sM) {
            this.mBuilder.append(", ");
            this.f2376sM = false;
        }
        StringBuilder sb = this.mBuilder;
        sb.append("[");
        sb.append(entryElement.toString());
        sb.append("]");
        return true;
    }

    public void onElementGroupEnded() {
        this.mBuilder.append("\n");
    }

    public void onElementGroupStarted(VCardEntry.EntryLabel entryLabel) {
        StringBuilder sb = this.mBuilder;
        sb.append(entryLabel.toString() + ": ");
        this.f2376sM = true;
    }

    public void onIterationEnded() {
        this.mBuilder.append("]]\n");
    }

    public void onIterationStarted() {
        this.mBuilder = new StringBuilder();
        StringBuilder sb = this.mBuilder;
        StringBuilder Pa = C0632a.m1011Pa("[[hash: ");
        Pa.append(this.this$0.hashCode());
        Pa.append("\n");
        sb.append(Pa.toString());
    }

    public String toString() {
        return this.mBuilder.toString();
    }
}
