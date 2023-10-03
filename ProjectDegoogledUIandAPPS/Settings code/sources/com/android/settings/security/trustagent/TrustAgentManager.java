package com.android.settings.security.trustagent;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.util.ArrayList;
import java.util.List;

public class TrustAgentManager {
    static final String PERMISSION_PROVIDE_AGENT = "android.permission.PROVIDE_TRUST_AGENT";
    private static final Intent TRUST_AGENT_INTENT = new Intent("android.service.trust.TrustAgentService");

    public static class TrustAgentComponentInfo {
        public RestrictedLockUtils.EnforcedAdmin admin = null;
        public ComponentName componentName;
        public String summary;
        public String title;
    }

    public boolean shouldProvideTrust(ResolveInfo resolveInfo, PackageManager packageManager) {
        String str = resolveInfo.serviceInfo.packageName;
        if (packageManager.checkPermission(PERMISSION_PROVIDE_AGENT, str) == 0) {
            return true;
        }
        Log.w("TrustAgentManager", "Skipping agent because package " + str + " does not have permission " + PERMISSION_PROVIDE_AGENT + ".");
        return false;
    }

    public CharSequence getActiveTrustAgentLabel(Context context, LockPatternUtils lockPatternUtils) {
        List<TrustAgentComponentInfo> activeTrustAgents = getActiveTrustAgents(context, lockPatternUtils);
        if (activeTrustAgents.isEmpty()) {
            return null;
        }
        return activeTrustAgents.get(0).title;
    }

    public List<TrustAgentComponentInfo> getActiveTrustAgents(Context context, LockPatternUtils lockPatternUtils) {
        int myUserId = UserHandle.myUserId();
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(TRUST_AGENT_INTENT, 128);
        List enabledTrustAgents = lockPatternUtils.getEnabledTrustAgents(myUserId);
        RestrictedLockUtils.EnforcedAdmin checkIfKeyguardFeaturesDisabled = RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(context, 16, myUserId);
        if (enabledTrustAgents != null && !enabledTrustAgents.isEmpty()) {
            for (ResolveInfo next : queryIntentServices) {
                if (next.serviceInfo != null && shouldProvideTrust(next, packageManager)) {
                    TrustAgentComponentInfo settingsComponent = getSettingsComponent(packageManager, next);
                    if (settingsComponent.componentName != null && enabledTrustAgents.contains(getComponentName(next)) && !TextUtils.isEmpty(settingsComponent.title)) {
                        if (checkIfKeyguardFeaturesDisabled != null && devicePolicyManager.getTrustAgentConfiguration((ComponentName) null, getComponentName(next)) == null) {
                            settingsComponent.admin = checkIfKeyguardFeaturesDisabled;
                        }
                        arrayList.add(settingsComponent);
                    }
                }
            }
        }
        return arrayList;
    }

    public ComponentName getComponentName(ResolveInfo resolveInfo) {
        ServiceInfo serviceInfo;
        if (resolveInfo == null || (serviceInfo = resolveInfo.serviceInfo) == null) {
            return null;
        }
        return new ComponentName(serviceInfo.packageName, resolveInfo.serviceInfo.name);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0078, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007a, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007c, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007e, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x008d, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0094, code lost:
        if (r2 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0096, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x009d, code lost:
        if (r2 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00a3, code lost:
        if (r2 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:11:0x0020, B:30:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0020] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.settings.security.trustagent.TrustAgentManager.TrustAgentComponentInfo getSettingsComponent(android.content.pm.PackageManager r9, android.content.pm.ResolveInfo r10) {
        /*
            r8 = this;
            java.lang.String r8 = "TrustAgentManager"
            r0 = 0
            if (r10 == 0) goto L_0x00ed
            android.content.pm.ServiceInfo r1 = r10.serviceInfo
            if (r1 == 0) goto L_0x00ed
            android.os.Bundle r1 = r1.metaData
            if (r1 != 0) goto L_0x000f
            goto L_0x00ed
        L_0x000f:
            com.android.settings.security.trustagent.TrustAgentManager$TrustAgentComponentInfo r1 = new com.android.settings.security.trustagent.TrustAgentManager$TrustAgentComponentInfo
            r1.<init>()
            android.content.pm.ServiceInfo r2 = r10.serviceInfo     // Catch:{ NameNotFoundException -> 0x00a0, IOException -> 0x009a, XmlPullParserException -> 0x0091, all -> 0x0089 }
            java.lang.String r3 = "android.service.trust.trustagent"
            android.content.res.XmlResourceParser r2 = r2.loadXmlMetaData(r9, r3)     // Catch:{ NameNotFoundException -> 0x00a0, IOException -> 0x009a, XmlPullParserException -> 0x0091, all -> 0x0089 }
            if (r2 != 0) goto L_0x0029
            java.lang.String r9 = "Can't find android.service.trust.trustagent meta-data"
            android.util.Slog.w(r8, r9)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            if (r2 == 0) goto L_0x0028
            r2.close()
        L_0x0028:
            return r0
        L_0x0029:
            android.content.pm.ServiceInfo r3 = r10.serviceInfo     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            android.content.res.Resources r9 = r9.getResourcesForApplication(r3)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r2)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
        L_0x0035:
            int r4 = r2.next()     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            r5 = 2
            r6 = 1
            if (r4 == r6) goto L_0x0040
            if (r4 == r5) goto L_0x0040
            goto L_0x0035
        L_0x0040:
            java.lang.String r4 = r2.getName()     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            java.lang.String r7 = "trust-agent"
            boolean r4 = r7.equals(r4)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            if (r4 != 0) goto L_0x0057
            java.lang.String r9 = "Meta-data does not start with trust-agent tag"
            android.util.Slog.w(r8, r9)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            if (r2 == 0) goto L_0x0056
            r2.close()
        L_0x0056:
            return r0
        L_0x0057:
            int[] r4 = com.android.internal.R.styleable.TrustAgent     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            android.content.res.TypedArray r9 = r9.obtainAttributes(r3, r4)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            java.lang.String r3 = r9.getString(r6)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            r1.summary = r3     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            r3 = 0
            java.lang.String r3 = r9.getString(r3)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            r1.title = r3     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            java.lang.String r3 = r9.getString(r5)     // Catch:{ NameNotFoundException -> 0x0086, IOException -> 0x0083, XmlPullParserException -> 0x0080, all -> 0x007e }
            r9.recycle()     // Catch:{ NameNotFoundException -> 0x007c, IOException -> 0x007a, XmlPullParserException -> 0x0078, all -> 0x007e }
            if (r2 == 0) goto L_0x0076
            r2.close()
        L_0x0076:
            r9 = r0
            goto L_0x00a6
        L_0x0078:
            r9 = move-exception
            goto L_0x0094
        L_0x007a:
            r9 = move-exception
            goto L_0x009d
        L_0x007c:
            r9 = move-exception
            goto L_0x00a3
        L_0x007e:
            r8 = move-exception
            goto L_0x008b
        L_0x0080:
            r9 = move-exception
            r3 = r0
            goto L_0x0094
        L_0x0083:
            r9 = move-exception
            r3 = r0
            goto L_0x009d
        L_0x0086:
            r9 = move-exception
            r3 = r0
            goto L_0x00a3
        L_0x0089:
            r8 = move-exception
            r2 = r0
        L_0x008b:
            if (r2 == 0) goto L_0x0090
            r2.close()
        L_0x0090:
            throw r8
        L_0x0091:
            r9 = move-exception
            r2 = r0
            r3 = r2
        L_0x0094:
            if (r2 == 0) goto L_0x00a6
        L_0x0096:
            r2.close()
            goto L_0x00a6
        L_0x009a:
            r9 = move-exception
            r2 = r0
            r3 = r2
        L_0x009d:
            if (r2 == 0) goto L_0x00a6
            goto L_0x0096
        L_0x00a0:
            r9 = move-exception
            r2 = r0
            r3 = r2
        L_0x00a3:
            if (r2 == 0) goto L_0x00a6
            goto L_0x0096
        L_0x00a6:
            if (r9 == 0) goto L_0x00c1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Error parsing : "
            r1.append(r2)
            android.content.pm.ServiceInfo r10 = r10.serviceInfo
            java.lang.String r10 = r10.packageName
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            android.util.Slog.w(r8, r10, r9)
            return r0
        L_0x00c1:
            if (r3 == 0) goto L_0x00e3
            r8 = 47
            int r8 = r3.indexOf(r8)
            if (r8 >= 0) goto L_0x00e3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            android.content.pm.ServiceInfo r9 = r10.serviceInfo
            java.lang.String r9 = r9.packageName
            r8.append(r9)
            java.lang.String r9 = "/"
            r8.append(r9)
            r8.append(r3)
            java.lang.String r3 = r8.toString()
        L_0x00e3:
            if (r3 != 0) goto L_0x00e6
            goto L_0x00ea
        L_0x00e6:
            android.content.ComponentName r0 = android.content.ComponentName.unflattenFromString(r3)
        L_0x00ea:
            r1.componentName = r0
            return r1
        L_0x00ed:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.security.trustagent.TrustAgentManager.getSettingsComponent(android.content.pm.PackageManager, android.content.pm.ResolveInfo):com.android.settings.security.trustagent.TrustAgentManager$TrustAgentComponentInfo");
    }
}
