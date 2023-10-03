package com.android.voicemail.impl.mail.store.imap;

import java.util.ArrayList;
import java.util.Iterator;

public class ImapList extends ImapElement {
    public static final ImapList EMPTY = new ImapList() {
        /* access modifiers changed from: package-private */
        public void add(ImapElement imapElement) {
            throw new RuntimeException();
        }

        public void destroy() {
        }
    };
    private ArrayList<ImapElement> list = new ArrayList<>();

    /* access modifiers changed from: package-private */
    public void add(ImapElement imapElement) {
        if (imapElement != null) {
            this.list.add(imapElement);
            return;
        }
        throw new RuntimeException("Can't add null");
    }

    public void destroy() {
        ArrayList<ImapElement> arrayList = this.list;
        if (arrayList != null) {
            Iterator<ImapElement> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().destroy();
            }
            this.list = null;
        }
        super.destroy();
    }

    public final ImapElement getElementOrNone(int i) {
        return i >= this.list.size() ? ImapElement.NONE : this.list.get(i);
    }

    /* access modifiers changed from: package-private */
    public final ImapElement getKeyedElementOrNull(String str, boolean z) {
        for (int i = 1; i < size(); i += 2) {
            if (mo9248is(i - 1, str, z)) {
                return this.list.get(i);
            }
        }
        return null;
    }

    public final ImapList getKeyedListOrEmpty(String str) {
        ImapElement keyedElementOrNull = getKeyedElementOrNull(str, false);
        return keyedElementOrNull != null ? (ImapList) keyedElementOrNull : EMPTY;
    }

    public final ImapString getKeyedStringOrEmpty(String str) {
        ImapElement keyedElementOrNull = getKeyedElementOrNull(str, false);
        return keyedElementOrNull != null ? (ImapString) keyedElementOrNull : ImapString.EMPTY;
    }

    public final ImapList getListOrEmpty(int i) {
        ImapElement elementOrNone = getElementOrNone(i);
        return elementOrNone.isList() ? (ImapList) elementOrNone : EMPTY;
    }

    public final ImapString getStringOrEmpty(int i) {
        ImapElement elementOrNone = getElementOrNone(i);
        return elementOrNone.isString() ? (ImapString) elementOrNone : ImapString.EMPTY;
    }

    /* renamed from: is */
    public final boolean mo9247is(int i, String str) {
        return mo9248is(i, str, false);
    }

    public final boolean isList() {
        return true;
    }

    public final boolean isString() {
        return false;
    }

    public final int size() {
        return this.list.size();
    }

    public String toString() {
        return this.list.toString();
    }

    /* renamed from: is */
    public final boolean mo9248is(int i, String str, boolean z) {
        if (!z) {
            return getStringOrEmpty(i).mo9269is(str);
        }
        return getStringOrEmpty(i).startsWith(str);
    }

    public final ImapString getKeyedStringOrEmpty(String str, boolean z) {
        ImapElement keyedElementOrNull = getKeyedElementOrNull(str, z);
        return keyedElementOrNull != null ? (ImapString) keyedElementOrNull : ImapString.EMPTY;
    }
}
