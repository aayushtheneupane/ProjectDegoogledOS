package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.support.p000v4.content.ContextCompat;
import android.util.ArrayMap;
import com.android.contacts.common.list.ContactEntry;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.shortcuts.AutoValue_DialerShortcut;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@TargetApi(25)
final class DynamicShortcuts {
    private final Context context;
    private final ShortcutInfoFactory shortcutInfoFactory;

    DynamicShortcuts(Context context2, IconFactory iconFactory) {
        this.context = context2;
        this.shortcutInfoFactory = new ShortcutInfoFactory(context2, iconFactory);
    }

    private static ShortcutManager getShortcutManager(Context context2) {
        return (ShortcutManager) context2.getSystemService("shortcut");
    }

    public void refresh(List<ContactEntry> list) {
        boolean z;
        Assert.isWorkerThread();
        LogUtil.enterBlock("DynamicShortcuts.refresh");
        ShortcutManager shortcutManager = getShortcutManager(this.context);
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.READ_CONTACTS") != 0) {
            LogUtil.m9i("DynamicShortcuts.refresh", "no contact permissions", new Object[0]);
            shortcutManager.removeAllDynamicShortcuts();
            return;
        }
        int min = Math.min(3, shortcutManager.getMaxShortcutCountPerActivity() - shortcutManager.getManifestShortcuts().size());
        ArrayMap arrayMap = new ArrayMap(min);
        int i = 0;
        for (ContactEntry next : list) {
            if (arrayMap.size() >= min) {
                break;
            }
            AutoValue_DialerShortcut.Builder builder = new AutoValue_DialerShortcut.Builder();
            builder.setRank(-1);
            builder.setContactId(next.f5id);
            builder.setLookupKey(next.lookupKey);
            builder.setDisplayName(next.getPreferredDisplayName(this.context));
            int i2 = i + 1;
            builder.setRank(i);
            DialerShortcut build = builder.build();
            arrayMap.put(build.getLookupKey(), build);
            i = i2;
        }
        ArrayList<ShortcutInfo> arrayList = new ArrayList<>(shortcutManager.getDynamicShortcuts());
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayList arrayList2 = new ArrayList();
        ArrayMap arrayMap3 = new ArrayMap();
        if (arrayList.isEmpty()) {
            arrayMap3.putAll(arrayMap);
        } else {
            for (ShortcutInfo shortcutInfo : arrayList) {
                DialerShortcut dialerShortcut = (DialerShortcut) arrayMap.get(shortcutInfo.getId());
                if (dialerShortcut == null) {
                    LogUtil.m9i("DynamicShortcuts.computeDelta", "contact removed", new Object[0]);
                    arrayList2.add(shortcutInfo.getId());
                } else if (dialerShortcut.needsUpdate(shortcutInfo)) {
                    LogUtil.m9i("DynamicShortcuts.computeDelta", "contact updated", new Object[0]);
                    arrayMap2.put(shortcutInfo.getId(), dialerShortcut);
                }
            }
            for (Map.Entry entry : arrayMap.entrySet()) {
                String str = (String) entry.getKey();
                DialerShortcut dialerShortcut2 = (DialerShortcut) entry.getValue();
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((ShortcutInfo) it.next()).getId().equals(str)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    LogUtil.m9i("DynamicShortcuts.computeDelta", "contact added", new Object[0]);
                    arrayMap3.put(str, dialerShortcut2);
                }
            }
        }
        ShortcutManager shortcutManager2 = getShortcutManager(this.context);
        if (!arrayList2.isEmpty()) {
            shortcutManager2.removeDynamicShortcuts(arrayList2);
        }
        if (!arrayMap2.isEmpty()) {
            shortcutManager2.updateShortcuts(this.shortcutInfoFactory.buildShortcutInfos(arrayMap2));
        }
        if (!arrayMap3.isEmpty()) {
            shortcutManager2.addDynamicShortcuts(this.shortcutInfoFactory.buildShortcutInfos(arrayMap3));
        }
    }

    /* access modifiers changed from: package-private */
    public void updateIcons() {
        Assert.isWorkerThread();
        LogUtil.enterBlock("DynamicShortcuts.updateIcons");
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.READ_CONTACTS") != 0) {
            LogUtil.m9i("DynamicShortcuts.updateIcons", "no contact permissions", new Object[0]);
            return;
        }
        ShortcutManager shortcutManager = getShortcutManager(this.context);
        int maxShortcutCountPerActivity = shortcutManager.getMaxShortcutCountPerActivity() - shortcutManager.getManifestShortcuts().size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (ShortcutInfo withUpdatedIcon : shortcutManager.getDynamicShortcuts()) {
            arrayList.add(this.shortcutInfoFactory.withUpdatedIcon(withUpdatedIcon));
            i++;
            if (i >= maxShortcutCountPerActivity) {
                break;
            }
        }
        LogUtil.m9i("DynamicShortcuts.updateIcons", "updating %d shortcut icons", Integer.valueOf(arrayList.size()));
        shortcutManager.setDynamicShortcuts(arrayList);
    }
}
