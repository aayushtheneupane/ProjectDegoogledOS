package p010me.leolin.shortcutbadger.impl;

import java.util.Collections;
import java.util.List;
import p010me.leolin.shortcutbadger.Badger;

/* renamed from: me.leolin.shortcutbadger.impl.OPPOHomeBader */
public class OPPOHomeBader implements Badger {
    private static int ROMVERSION = -1;

    private boolean checkObjExists(Object obj) {
        return obj == null || obj.toString().equals("") || obj.toString().trim().equals("null");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r3.getSuperclass() != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return getMethod(r3.getSuperclass(), r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        return r3.getMethod(r4, r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.reflect.Method getMethod(java.lang.Class r3, java.lang.String r4, java.lang.Class[] r5) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x0028
            boolean r1 = r2.checkObjExists(r4)
            if (r1 == 0) goto L_0x000a
            goto L_0x0028
        L_0x000a:
            r3.getMethods()     // Catch:{ Exception -> 0x0015 }
            r3.getDeclaredMethods()     // Catch:{ Exception -> 0x0015 }
            java.lang.reflect.Method r2 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x0015 }
            return r2
        L_0x0015:
            java.lang.reflect.Method r2 = r3.getMethod(r4, r5)     // Catch:{ Exception -> 0x001a }
            return r2
        L_0x001a:
            java.lang.Class r1 = r3.getSuperclass()
            if (r1 == 0) goto L_0x0028
            java.lang.Class r3 = r3.getSuperclass()
            java.lang.reflect.Method r0 = r2.getMethod(r3, r4, r5)
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p010me.leolin.shortcutbadger.impl.OPPOHomeBader.getMethod(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c A[SYNTHETIC, Splitter:B:13:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043 A[SYNTHETIC, Splitter:B:21:0x0043] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getSystemProperty(java.lang.String r4) {
        /*
            r3 = this;
            r3 = 0
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            r1.<init>()     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.lang.String r2 = "getprop "
            r1.append(r2)     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            r1.append(r4)     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.lang.String r4 = r1.toString()     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.lang.Process r4 = r0.exec(r4)     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            r4 = 1024(0x400, float:1.435E-42)
            r0.<init>(r1, r4)     // Catch:{ IOException -> 0x0040, all -> 0x0039 }
            java.lang.String r4 = r0.readLine()     // Catch:{ IOException -> 0x0041, all -> 0x0035 }
            r0.close()     // Catch:{ IOException -> 0x0041, all -> 0x0035 }
            r0.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r4
        L_0x0035:
            r3 = move-exception
            r4 = r3
            r3 = r0
            goto L_0x003a
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            if (r3 == 0) goto L_0x003f
            r3.close()     // Catch:{ IOException -> 0x003f }
        L_0x003f:
            throw r4
        L_0x0040:
            r0 = r3
        L_0x0041:
            if (r0 == 0) goto L_0x0046
            r0.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p010me.leolin.shortcutbadger.impl.OPPOHomeBader.getSystemProperty(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063  */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBadge(android.content.Context r5, android.content.ComponentName r6, int r7) throws p010me.leolin.shortcutbadger.ShortcutBadgeException {
        /*
            r4 = this;
            if (r7 != 0) goto L_0x0003
            r7 = -1
        L_0x0003:
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.oppo.unsettledevent"
            r0.<init>(r1)
            java.lang.String r6 = r6.getPackageName()
            java.lang.String r1 = "pakeageName"
            r0.putExtra(r1, r6)
            java.lang.String r6 = "number"
            r0.putExtra(r6, r7)
            java.lang.String r6 = "upgradeNumber"
            r0.putExtra(r6, r7)
            boolean r6 = android.support.p002v7.appcompat.R$style.canResolveBroadcast(r5, r0)
            if (r6 == 0) goto L_0x0028
            r5.sendBroadcast(r0)
            goto L_0x00bf
        L_0x0028:
            int r6 = ROMVERSION
            r1 = 0
            if (r6 < 0) goto L_0x002e
            goto L_0x008b
        L_0x002e:
            java.lang.String r6 = "com.color.os.ColorBuild"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException -> 0x0035 }
            goto L_0x0036
        L_0x0035:
            r6 = r1
        L_0x0036:
            java.lang.String r2 = "getColorOSVERSION"
            if (r6 == 0) goto L_0x0058
            boolean r3 = r4.checkObjExists(r2)     // Catch:{ Exception -> 0x0060 }
            if (r3 != 0) goto L_0x0058
            java.lang.reflect.Method r6 = r4.getMethod(r6, r2, r1)     // Catch:{ Exception -> 0x0060 }
            if (r6 == 0) goto L_0x0058
            r2 = 1
            r6.setAccessible(r2)     // Catch:{ Exception -> 0x0060 }
            java.lang.Object r6 = r6.invoke(r1, r1)     // Catch:{ IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004f }
            goto L_0x0059
        L_0x004f:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ Exception -> 0x0060 }
            goto L_0x0058
        L_0x0054:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ Exception -> 0x0060 }
        L_0x0058:
            r6 = r1
        L_0x0059:
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x0060 }
            int r6 = r6.intValue()     // Catch:{ Exception -> 0x0060 }
            goto L_0x0061
        L_0x0060:
            r6 = 0
        L_0x0061:
            if (r6 != 0) goto L_0x0087
            java.lang.String r2 = "ro.build.version.opporom"
            java.lang.String r4 = r4.getSystemProperty(r2)     // Catch:{ Exception -> 0x0087 }
            java.lang.String r2 = "V1.4"
            boolean r2 = r4.startsWith(r2)     // Catch:{ Exception -> 0x0087 }
            if (r2 == 0) goto L_0x0073
            r6 = 3
            goto L_0x008b
        L_0x0073:
            java.lang.String r2 = "V2.0"
            boolean r2 = r4.startsWith(r2)     // Catch:{ Exception -> 0x0087 }
            if (r2 == 0) goto L_0x007d
            r6 = 4
            goto L_0x008b
        L_0x007d:
            java.lang.String r2 = "V2.1"
            boolean r4 = r4.startsWith(r2)     // Catch:{ Exception -> 0x0087 }
            if (r4 == 0) goto L_0x0087
            r6 = 5
            goto L_0x008b
        L_0x0087:
            ROMVERSION = r6
            int r6 = ROMVERSION
        L_0x008b:
            r4 = 6
            if (r6 != r4) goto L_0x00bf
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00a8 }
            r4.<init>()     // Catch:{ all -> 0x00a8 }
            java.lang.String r6 = "app_badge_count"
            r4.putInt(r6, r7)     // Catch:{ all -> 0x00a8 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ all -> 0x00a8 }
            java.lang.String r6 = "content://com.android.badge/badge"
            android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch:{ all -> 0x00a8 }
            java.lang.String r7 = "setAppBadgeCount"
            r5.call(r6, r7, r1, r4)     // Catch:{ all -> 0x00a8 }
            goto L_0x00bf
        L_0x00a8:
            me.leolin.shortcutbadger.ShortcutBadgeException r4 = new me.leolin.shortcutbadger.ShortcutBadgeException
            java.lang.String r5 = "unable to resolve intent: "
            java.lang.StringBuilder r5 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r5)
            java.lang.String r6 = r0.toString()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x00bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p010me.leolin.shortcutbadger.impl.OPPOHomeBader.executeBadge(android.content.Context, android.content.ComponentName, int):void");
    }

    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.oppo.launcher");
    }
}
