package p010me.leolin.shortcutbadger.impl;

import android.content.pm.ResolveInfo;
import java.util.Arrays;
import java.util.List;
import p010me.leolin.shortcutbadger.Badger;

/* renamed from: me.leolin.shortcutbadger.impl.XiaomiHomeBadger */
public class XiaomiHomeBadger implements Badger {
    private ResolveInfo resolveInfo;

    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r3.set(r2, java.lang.Integer.valueOf(r9));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBadge(android.content.Context r7, android.content.ComponentName r8, int r9) throws p010me.leolin.shortcutbadger.ShortcutBadgeException {
        /*
            r6 = this;
            java.lang.String r0 = ""
            r1 = 1
            java.lang.String r2 = "android.app.MiuiNotification"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r2 = r2.newInstance()     // Catch:{ Exception -> 0x0032 }
            java.lang.Class r3 = r2.getClass()     // Catch:{ Exception -> 0x0032 }
            java.lang.String r4 = "messageCount"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ Exception -> 0x0032 }
            r3.setAccessible(r1)     // Catch:{ Exception -> 0x0032 }
            if (r9 != 0) goto L_0x001e
            r4 = r0
            goto L_0x0022
        L_0x001e:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x002a }
        L_0x0022:
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x002a }
            r3.set(r2, r4)     // Catch:{ Exception -> 0x002a }
            goto L_0x0074
        L_0x002a:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0032 }
            r3.set(r2, r4)     // Catch:{ Exception -> 0x0032 }
            goto L_0x0074
        L_0x0032:
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "android.intent.action.APPLICATION_MESSAGE_UPDATE"
            r2.<init>(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r8.getPackageName()
            r3.append(r4)
            java.lang.String r4 = "/"
            r3.append(r4)
            java.lang.String r8 = r8.getClassName()
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            java.lang.String r3 = "android.intent.extra.update_application_component_name"
            r2.putExtra(r3, r8)
            if (r9 != 0) goto L_0x005e
            r8 = r0
            goto L_0x0062
        L_0x005e:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)
        L_0x0062:
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r3 = "android.intent.extra.update_application_message_text"
            r2.putExtra(r3, r8)
            boolean r8 = android.support.p002v7.appcompat.R$style.canResolveBroadcast(r7, r2)
            if (r8 == 0) goto L_0x0074
            r7.sendBroadcast(r2)
        L_0x0074:
            java.lang.String r8 = android.os.Build.MANUFACTURER
            java.lang.String r2 = "Xiaomi"
            boolean r8 = r8.equalsIgnoreCase(r2)
            if (r8 == 0) goto L_0x00f8
            android.content.pm.ResolveInfo r8 = r6.resolveInfo
            if (r8 != 0) goto L_0x009a
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r2 = "android.intent.action.MAIN"
            r8.<init>(r2)
            java.lang.String r2 = "android.intent.category.HOME"
            r8.addCategory(r2)
            android.content.pm.PackageManager r2 = r7.getPackageManager()
            r3 = 65536(0x10000, float:9.18355E-41)
            android.content.pm.ResolveInfo r8 = r2.resolveActivity(r8, r3)
            r6.resolveInfo = r8
        L_0x009a:
            android.content.pm.ResolveInfo r8 = r6.resolveInfo
            if (r8 == 0) goto L_0x00f8
            java.lang.String r8 = "notification"
            java.lang.Object r8 = r7.getSystemService(r8)
            android.app.NotificationManager r8 = (android.app.NotificationManager) r8
            android.app.Notification$Builder r2 = new android.app.Notification$Builder
            r2.<init>(r7)
            android.app.Notification$Builder r7 = r2.setContentTitle(r0)
            android.app.Notification$Builder r7 = r7.setContentText(r0)
            android.content.pm.ResolveInfo r6 = r6.resolveInfo
            int r6 = r6.getIconResource()
            android.app.Notification$Builder r6 = r7.setSmallIcon(r6)
            android.app.Notification r6 = r6.build()
            java.lang.Class r7 = r6.getClass()     // Catch:{ Exception -> 0x00ef }
            java.lang.String r0 = "extraNotification"
            java.lang.reflect.Field r7 = r7.getDeclaredField(r0)     // Catch:{ Exception -> 0x00ef }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ Exception -> 0x00ef }
            java.lang.Class r0 = r7.getClass()     // Catch:{ Exception -> 0x00ef }
            java.lang.String r2 = "setMessageCount"
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x00ef }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x00ef }
            r5 = 0
            r3[r5] = r4     // Catch:{ Exception -> 0x00ef }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r2, r3)     // Catch:{ Exception -> 0x00ef }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x00ef }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x00ef }
            r1[r5] = r9     // Catch:{ Exception -> 0x00ef }
            r0.invoke(r7, r1)     // Catch:{ Exception -> 0x00ef }
            r8.notify(r5, r6)     // Catch:{ Exception -> 0x00ef }
            goto L_0x00f8
        L_0x00ef:
            r6 = move-exception
            me.leolin.shortcutbadger.ShortcutBadgeException r7 = new me.leolin.shortcutbadger.ShortcutBadgeException
            java.lang.String r8 = "not able to set badge"
            r7.<init>(r8, r6)
            throw r7
        L_0x00f8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p010me.leolin.shortcutbadger.impl.XiaomiHomeBadger.executeBadge(android.content.Context, android.content.ComponentName, int):void");
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2", "com.i.miui.launcher"});
    }
}
