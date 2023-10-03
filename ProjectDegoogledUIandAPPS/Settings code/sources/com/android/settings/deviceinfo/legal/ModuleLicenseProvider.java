package com.android.settings.deviceinfo.legal;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import java.io.File;
import java.util.List;

public class ModuleLicenseProvider extends ContentProvider {
    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException();
    }

    public String getType(Uri uri) {
        checkUri(getContext(), uri);
        return "text/html";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0074, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007d, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.ParcelFileDescriptor openFile(android.net.Uri r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "ModuleLicenseProvider"
            android.content.Context r6 = r6.getContext()
            checkUri(r6, r7)
            java.lang.String r1 = "r"
            boolean r8 = r1.equals(r8)
            java.lang.String r1 = "Read is the only supported mode"
            androidx.core.util.Preconditions.checkArgument(r8, r1)
            java.util.List r7 = r7.getPathSegments()     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            r8 = 0
            java.lang.Object r7 = r7.get(r8)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            java.io.File r1 = getCachedHtmlFile(r6, r7)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            boolean r2 = isCachedHtmlFileOutdated(r6, r7)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            if (r2 == 0) goto L_0x007e
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            android.content.pm.PackageManager r3 = r6.getPackageManager()     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            android.content.res.AssetManager r3 = getPackageAssetManager(r3, r7)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            java.lang.String r4 = "NOTICE.html.gz"
            java.io.InputStream r3 = r3.open(r4)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            r2.<init>(r3)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            java.io.File r3 = getCachedFileDirectory(r6, r7)     // Catch:{ all -> 0x0072 }
            boolean r4 = r3.exists()     // Catch:{ all -> 0x0072 }
            if (r4 != 0) goto L_0x0049
            r3.mkdir()     // Catch:{ all -> 0x0072 }
        L_0x0049:
            java.nio.file.Path r3 = r1.toPath()     // Catch:{ all -> 0x0072 }
            r4 = 1
            java.nio.file.CopyOption[] r4 = new java.nio.file.CopyOption[r4]     // Catch:{ all -> 0x0072 }
            java.nio.file.StandardCopyOption r5 = java.nio.file.StandardCopyOption.REPLACE_EXISTING     // Catch:{ all -> 0x0072 }
            r4[r8] = r5     // Catch:{ all -> 0x0072 }
            java.nio.file.Files.copy(r2, r3, r4)     // Catch:{ all -> 0x0072 }
            r2.close()     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            android.content.SharedPreferences r8 = getPrefs(r6)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            android.content.SharedPreferences$Editor r8 = r8.edit()     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            android.content.pm.PackageInfo r6 = getPackageInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            long r2 = r6.getLongVersionCode()     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            android.content.SharedPreferences$Editor r6 = r8.putLong(r7, r2)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            r6.commit()     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            goto L_0x007e
        L_0x0072:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r7 = move-exception
            r2.close()     // Catch:{ all -> 0x0079 }
            goto L_0x007d
        L_0x0079:
            r8 = move-exception
            r6.addSuppressed(r8)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
        L_0x007d:
            throw r7     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
        L_0x007e:
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            android.os.ParcelFileDescriptor r6 = android.os.ParcelFileDescriptor.open(r1, r6)     // Catch:{ NameNotFoundException -> 0x008c, IOException -> 0x0085 }
            return r6
        L_0x0085:
            r6 = move-exception
            java.lang.String r7 = "Could not open file descriptor"
            android.util.Log.e(r0, r7, r6)
            goto L_0x0092
        L_0x008c:
            r6 = move-exception
            java.lang.String r7 = "checkUri should have already caught this error"
            android.util.Log.wtf(r0, r7, r6)
        L_0x0092:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.deviceinfo.legal.ModuleLicenseProvider.openFile(android.net.Uri, java.lang.String):android.os.ParcelFileDescriptor");
    }

    static boolean isCachedHtmlFileOutdated(Context context, String str) throws PackageManager.NameNotFoundException {
        SharedPreferences prefs = getPrefs(context);
        File cachedHtmlFile = getCachedHtmlFile(context, str);
        return !prefs.contains(str) || prefs.getLong(str, 0) != getPackageInfo(context, str).getLongVersionCode() || !cachedHtmlFile.exists() || cachedHtmlFile.length() == 0;
    }

    static AssetManager getPackageAssetManager(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        return packageManager.getResourcesForApplication(packageManager.getPackageInfo(str, 1073741824).applicationInfo).getAssets();
    }

    static Uri getUriForPackage(String str) {
        return new Uri.Builder().scheme("content").authority("com.android.settings.module_licenses").appendPath(str).appendPath("NOTICE.html").build();
    }

    private static void checkUri(Context context, Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (!"content".equals(uri.getScheme()) || !"com.android.settings.module_licenses".equals(uri.getAuthority()) || pathSegments == null || pathSegments.size() != 2 || !"NOTICE.html".equals(pathSegments.get(1))) {
            throw new IllegalArgumentException(uri + "is not a valid URI");
        }
        try {
            context.getPackageManager().getModuleInfo(pathSegments.get(0), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(uri + "is not a valid URI", e);
        }
    }

    private static File getCachedFileDirectory(Context context, String str) {
        return new File(context.getCacheDir(), str);
    }

    private static File getCachedHtmlFile(Context context, String str) {
        return new File(context.getCacheDir() + "/" + str, "NOTICE.html");
    }

    private static PackageInfo getPackageInfo(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 1073741824);
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences("ModuleLicenseProvider", 0);
    }
}
