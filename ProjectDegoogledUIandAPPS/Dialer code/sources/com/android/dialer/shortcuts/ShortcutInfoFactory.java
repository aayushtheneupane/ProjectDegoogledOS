package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.provider.ContactsContract;
import com.android.dialer.common.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@TargetApi(25)
final class ShortcutInfoFactory {
    private final Context context;
    private final IconFactory iconFactory;

    ShortcutInfoFactory(Context context2, IconFactory iconFactory2) {
        this.context = context2;
        this.iconFactory = iconFactory2;
    }

    /* access modifiers changed from: package-private */
    public List<ShortcutInfo> buildShortcutInfos(Map<String, DialerShortcut> map) {
        Assert.isWorkerThread();
        ArrayList arrayList = new ArrayList(map.size());
        for (DialerShortcut next : map.values()) {
            Intent intent = new Intent();
            intent.setClassName(this.context, "com.android.dialer.shortcuts.CallContactActivity");
            intent.setData(ContactsContract.Contacts.getLookupUri(next.getContactId(), next.getLookupKey()));
            intent.setAction("com.android.dialer.shortcuts.CALL_CONTACT");
            intent.putExtra("contactId", next.getContactId());
            ShortcutInfo.Builder icon = new ShortcutInfo.Builder(this.context, next.getLookupKey()).setIntent(intent).setShortLabel(next.getDisplayName()).setLongLabel(next.getDisplayName()).setIcon(this.iconFactory.create(next));
            if (next.getRank() != -1) {
                icon.setRank(next.getRank());
            }
            arrayList.add(icon.build());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ShortcutInfo withUpdatedIcon(ShortcutInfo shortcutInfo) {
        Assert.isWorkerThread();
        return new ShortcutInfo.Builder(this.context, shortcutInfo.getId()).setIntent(shortcutInfo.getIntent()).setShortLabel(shortcutInfo.getShortLabel()).setLongLabel(shortcutInfo.getLongLabel()).setRank(shortcutInfo.getRank()).setIcon(this.iconFactory.create(shortcutInfo)).build();
    }
}
