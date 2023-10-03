package com.android.settingslib.drawer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TileUtils {
    static final String SETTING_PKG = "com.android.settings";

    public static List<DashboardCategory> getCategories(Context context, Map<Pair<String, String>, Tile> map) {
        System.currentTimeMillis();
        boolean z = false;
        if (Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0) {
            z = true;
        }
        ArrayList arrayList = new ArrayList();
        for (UserHandle next : ((UserManager) context.getSystemService("user")).getUserProfiles()) {
            if (next.getIdentifier() == ActivityManager.getCurrentUser()) {
                Context context2 = context;
                UserHandle userHandle = next;
                Map<Pair<String, String>, Tile> map2 = map;
                ArrayList arrayList2 = arrayList;
                getTilesForAction(context2, userHandle, "com.android.settings.action.SETTINGS", map2, (String) null, arrayList2, true);
                getTilesForAction(context2, userHandle, "com.android.settings.OPERATOR_APPLICATION_SETTING", map2, "com.android.settings.category.wireless", arrayList2, false);
                getTilesForAction(context2, userHandle, "com.android.settings.MANUFACTURER_APPLICATION_SETTING", map2, "com.android.settings.category.device", arrayList2, false);
            }
            if (z) {
                Context context3 = context;
                UserHandle userHandle2 = next;
                Map<Pair<String, String>, Tile> map3 = map;
                ArrayList arrayList3 = arrayList;
                getTilesForAction(context3, userHandle2, "com.android.settings.action.EXTRA_SETTINGS", map3, (String) null, arrayList3, false);
                getTilesForAction(context3, userHandle2, "com.android.settings.action.IA_SETTINGS", map3, (String) null, arrayList3, false);
            }
        }
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Tile tile = (Tile) it.next();
            String category = tile.getCategory();
            DashboardCategory dashboardCategory = (DashboardCategory) hashMap.get(category);
            if (dashboardCategory == null) {
                dashboardCategory = new DashboardCategory(category);
                hashMap.put(category, dashboardCategory);
            }
            dashboardCategory.addTile(tile);
        }
        ArrayList arrayList4 = new ArrayList(hashMap.values());
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            ((DashboardCategory) it2.next()).sortTiles();
        }
        return arrayList4;
    }

    static void getTilesForAction(Context context, UserHandle userHandle, String str, Map<Pair<String, String>, Tile> map, String str2, List<Tile> list, boolean z) {
        Intent intent = new Intent(str);
        if (z) {
            intent.setPackage(SETTING_PKG);
        }
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivitiesAsUser(intent, 128, userHandle.getIdentifier())) {
            if (resolveInfo.system) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                Bundle bundle = activityInfo.metaData;
                String str3 = "com.android.settings.category";
                if ((bundle == null || !bundle.containsKey(str3)) && str2 == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Found ");
                    sb.append(resolveInfo.activityInfo.name);
                    sb.append(" for intent ");
                    sb.append(intent);
                    sb.append(" missing metadata ");
                    if (bundle == null) {
                        str3 = "";
                    }
                    sb.append(str3);
                    Log.w("TileUtils", sb.toString());
                } else {
                    String string = bundle.getString(str3);
                    Pair pair = new Pair(activityInfo.packageName, activityInfo.name);
                    Tile tile = map.get(pair);
                    if (tile == null) {
                        tile = new Tile(activityInfo, string);
                        map.put(pair, tile);
                    } else {
                        tile.setMetaData(bundle);
                    }
                    if (!tile.userHandle.contains(userHandle)) {
                        tile.userHandle.add(userHandle);
                    }
                    if (!list.contains(tile)) {
                        list.add(tile);
                    }
                }
            }
        }
    }

    public static Pair<String, Integer> getIconFromUri(Context context, String str, String str2, Map<String, IContentProvider> map) {
        Bundle bundleFromUri = getBundleFromUri(context, str2, map);
        if (bundleFromUri == null) {
            return null;
        }
        String string = bundleFromUri.getString("com.android.settings.icon_package");
        if (TextUtils.isEmpty(string) || bundleFromUri.getInt("com.android.settings.icon", 0) == 0) {
            return null;
        }
        if (string.equals(str) || string.equals(context.getPackageName())) {
            return Pair.create(string, Integer.valueOf(bundleFromUri.getInt("com.android.settings.icon", 0)));
        }
        return null;
    }

    public static String getTextFromUri(Context context, String str, Map<String, IContentProvider> map, String str2) {
        Bundle bundleFromUri = getBundleFromUri(context, str, map);
        if (bundleFromUri != null) {
            return bundleFromUri.getString(str2);
        }
        return null;
    }

    private static Bundle getBundleFromUri(Context context, String str, Map<String, IContentProvider> map) {
        IContentProvider providerFromUri;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        String methodFromUri = getMethodFromUri(parse);
        if (TextUtils.isEmpty(methodFromUri) || (providerFromUri = getProviderFromUri(context, parse, map)) == null) {
            return null;
        }
        try {
            return providerFromUri.call(context.getPackageName(), parse.getAuthority(), methodFromUri, str, (Bundle) null);
        } catch (RemoteException unused) {
            return null;
        }
    }

    private static IContentProvider getProviderFromUri(Context context, Uri uri, Map<String, IContentProvider> map) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            return null;
        }
        if (!map.containsKey(authority)) {
            map.put(authority, context.getContentResolver().acquireUnstableProvider(uri));
        }
        return map.get(authority);
    }

    static String getMethodFromUri(Uri uri) {
        List<String> pathSegments;
        if (uri == null || (pathSegments = uri.getPathSegments()) == null || pathSegments.isEmpty()) {
            return null;
        }
        return pathSegments.get(0);
    }
}
