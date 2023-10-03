package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.database.Cursor;
import android.support.p000v4.content.ContextCompat;
import android.util.ArrayMap;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.shortcuts.AutoValue_DialerShortcut;
import java.util.ArrayList;

@TargetApi(25)
final class PinnedShortcuts {
    private static final String[] PROJECTION = {"_id", "display_name", "contact_last_updated_timestamp"};
    private final Context context;
    private final ShortcutInfoFactory shortcutInfoFactory;

    PinnedShortcuts(Context context2) {
        this.context = context2;
        this.shortcutInfoFactory = new ShortcutInfoFactory(context2, new IconFactory(context2));
    }

    public void refresh() {
        Assert.isWorkerThread();
        LogUtil.enterBlock("PinnedShortcuts.refresh");
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.READ_CONTACTS") != 0) {
            LogUtil.m9i("PinnedShortcuts.refresh", "no contact permissions", new Object[0]);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayMap arrayMap = new ArrayMap();
        for (ShortcutInfo next : ((ShortcutManager) this.context.getSystemService(ShortcutManager.class)).getPinnedShortcuts()) {
            if (!next.isDeclaredInManifest() && !next.isDynamic()) {
                String action = next.getIntent() != null ? next.getIntent().getAction() : null;
                if (action != null && action.equals("com.android.dialer.shortcuts.CALL_CONTACT")) {
                    String id = next.getId();
                    Cursor query = this.context.getContentResolver().query(DialerShortcut.getLookupUriFromShortcutInfo(next), PROJECTION, (String) null, (String[]) null, (String) null);
                    if (query != null) {
                        try {
                            if (query.moveToNext()) {
                                AutoValue_DialerShortcut.Builder builder = new AutoValue_DialerShortcut.Builder();
                                builder.setRank(-1);
                                builder.setContactId(query.getLong(query.getColumnIndexOrThrow("_id")));
                                builder.setLookupKey(id);
                                builder.setDisplayName(query.getString(query.getColumnIndexOrThrow("display_name")));
                                DialerShortcut build = builder.build();
                                if (build.needsUpdate(next)) {
                                    LogUtil.m9i("PinnedShortcuts.refresh", "contact updated", new Object[0]);
                                    arrayMap.put(next.getId(), build);
                                }
                                query.close();
                            }
                        } catch (Throwable th) {
                            if (query != null) {
                                try {
                                    query.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    }
                    LogUtil.m9i("PinnedShortcuts.refresh", "contact disabled", new Object[0]);
                    arrayList.add(next.getId());
                    if (query == null) {
                    }
                    query.close();
                }
            }
        }
        ShortcutManager shortcutManager = (ShortcutManager) this.context.getSystemService(ShortcutManager.class);
        String string = this.context.getResources().getString(R.string.dialer_shortcut_disabled_message);
        if (!arrayList.isEmpty()) {
            shortcutManager.disableShortcuts(arrayList, string);
        }
        if (!arrayMap.isEmpty() && !shortcutManager.updateShortcuts(this.shortcutInfoFactory.buildShortcutInfos(arrayMap))) {
            LogUtil.m9i("PinnedShortcuts.applyDelta", "shortcutManager rate limited.", new Object[0]);
        }
    }
}
