package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.content.pm.ShortcutInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;

@AutoValue
@TargetApi(25)
abstract class DialerShortcut {

    static abstract class Builder {
        Builder() {
        }

        /* access modifiers changed from: package-private */
        public abstract DialerShortcut build();

        /* access modifiers changed from: package-private */
        public abstract Builder setContactId(long j);

        /* access modifiers changed from: package-private */
        public abstract Builder setDisplayName(String str);

        /* access modifiers changed from: package-private */
        public abstract Builder setLookupKey(String str);

        /* access modifiers changed from: package-private */
        public abstract Builder setRank(int i);
    }

    DialerShortcut() {
    }

    static Uri getLookupUriFromShortcutInfo(ShortcutInfo shortcutInfo) {
        long longExtra = shortcutInfo.getIntent().getLongExtra("contactId", -1);
        if (longExtra != -1) {
            return ContactsContract.Contacts.getLookupUri(longExtra, shortcutInfo.getId());
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("No contact ID found for shortcut: ");
        outline13.append(shortcutInfo.getId());
        throw new IllegalStateException(outline13.toString());
    }

    /* access modifiers changed from: package-private */
    public abstract long getContactId();

    /* access modifiers changed from: package-private */
    public abstract String getDisplayName();

    /* access modifiers changed from: package-private */
    public abstract String getLookupKey();

    /* access modifiers changed from: package-private */
    public abstract int getRank();

    /* access modifiers changed from: package-private */
    public boolean needsUpdate(ShortcutInfo shortcutInfo) {
        if ((getRank() == -1 || shortcutInfo.getRank() == getRank()) && shortcutInfo.getShortLabel().equals(getDisplayName()) && shortcutInfo.getLongLabel().equals(getDisplayName())) {
            return false;
        }
        return true;
    }
}
