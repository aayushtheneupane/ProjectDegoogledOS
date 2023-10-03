package com.android.settingslib.dream;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DreamBackend {
    private static DreamBackend sInstance;
    private final DreamInfoComparator mComparator = new DreamInfoComparator(getDefaultDream());
    private final Context mContext;
    private final IDreamManager mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.getService("dreams"));
    private final boolean mDreamsActivatedOnDockByDefault = this.mContext.getResources().getBoolean(17891431);
    private final boolean mDreamsActivatedOnSleepByDefault = this.mContext.getResources().getBoolean(17891432);
    private final boolean mDreamsEnabledByDefault = this.mContext.getResources().getBoolean(17891433);

    public static class DreamInfo {
        public CharSequence caption;
        public ComponentName componentName;
        public Drawable icon;
        public boolean isActive;
        public ComponentName settingsComponentName;

        public String toString() {
            StringBuilder sb = new StringBuilder(DreamInfo.class.getSimpleName());
            sb.append('[');
            sb.append(this.caption);
            if (this.isActive) {
                sb.append(",active");
            }
            sb.append(',');
            sb.append(this.componentName);
            if (this.settingsComponentName != null) {
                sb.append("settings=");
                sb.append(this.settingsComponentName);
            }
            sb.append(']');
            return sb.toString();
        }
    }

    public static DreamBackend getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DreamBackend(context);
        }
        return sInstance;
    }

    public DreamBackend(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public List<DreamInfo> getDreamInfos() {
        ComponentName activeDream = getActiveDream();
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent("android.service.dreams.DreamService"), 128);
        ArrayList arrayList = new ArrayList(queryIntentServices.size());
        for (ResolveInfo next : queryIntentServices) {
            if (next.serviceInfo != null) {
                DreamInfo dreamInfo = new DreamInfo();
                dreamInfo.caption = next.loadLabel(packageManager);
                dreamInfo.icon = next.loadIcon(packageManager);
                dreamInfo.componentName = getDreamComponentName(next);
                dreamInfo.isActive = dreamInfo.componentName.equals(activeDream);
                dreamInfo.settingsComponentName = getSettingsComponentName(packageManager, next);
                arrayList.add(dreamInfo);
            }
        }
        Collections.sort(arrayList, this.mComparator);
        return arrayList;
    }

    public ComponentName getDefaultDream() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager == null) {
            return null;
        }
        try {
            return iDreamManager.getDefaultDreamComponent();
        } catch (RemoteException e) {
            Log.w("DreamBackend", "Failed to get default dream", e);
            return null;
        }
    }

    public CharSequence getActiveDreamName() {
        ComponentName activeDream = getActiveDream();
        if (activeDream != null) {
            PackageManager packageManager = this.mContext.getPackageManager();
            try {
                ServiceInfo serviceInfo = packageManager.getServiceInfo(activeDream, 0);
                if (serviceInfo != null) {
                    return serviceInfo.loadLabel(packageManager);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    public int getWhenToDreamSetting() {
        if (!isEnabled()) {
            return 3;
        }
        if (isActivatedOnDock() && isActivatedOnSleep()) {
            return 2;
        }
        if (isActivatedOnDock()) {
            return 1;
        }
        if (isActivatedOnSleep()) {
            return 0;
        }
        return 3;
    }

    public void setWhenToDream(int i) {
        setEnabled(i != 3);
        if (i == 0) {
            setActivatedOnDock(false);
            setActivatedOnSleep(true);
        } else if (i == 1) {
            setActivatedOnDock(true);
            setActivatedOnSleep(false);
        } else if (i == 2) {
            setActivatedOnDock(true);
            setActivatedOnSleep(true);
        }
    }

    public boolean isEnabled() {
        return getBoolean("screensaver_enabled", this.mDreamsEnabledByDefault);
    }

    public void setEnabled(boolean z) {
        new Object[1][0] = Boolean.valueOf(z);
        setBoolean("screensaver_enabled", z);
    }

    public boolean isActivatedOnDock() {
        return getBoolean("screensaver_activate_on_dock", this.mDreamsActivatedOnDockByDefault);
    }

    public void setActivatedOnDock(boolean z) {
        new Object[1][0] = Boolean.valueOf(z);
        setBoolean("screensaver_activate_on_dock", z);
    }

    public boolean isActivatedOnSleep() {
        return getBoolean("screensaver_activate_on_sleep", this.mDreamsActivatedOnSleepByDefault);
    }

    public void setActivatedOnSleep(boolean z) {
        new Object[1][0] = Boolean.valueOf(z);
        setBoolean("screensaver_activate_on_sleep", z);
    }

    private boolean getBoolean(String str, boolean z) {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), str, z ? 1 : 0) == 1;
    }

    private void setBoolean(String str, boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), str, z ? 1 : 0);
    }

    public void setActiveDream(ComponentName componentName) {
        new Object[1][0] = componentName;
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager != null) {
            try {
                ComponentName[] componentNameArr = {componentName};
                if (componentName == null) {
                    componentNameArr = null;
                }
                iDreamManager.setDreamComponents(componentNameArr);
            } catch (RemoteException e) {
                Log.w("DreamBackend", "Failed to set active dream to " + componentName, e);
            }
        }
    }

    public ComponentName getActiveDream() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager == null) {
            return null;
        }
        try {
            ComponentName[] dreamComponents = iDreamManager.getDreamComponents();
            if (dreamComponents == null || dreamComponents.length <= 0) {
                return null;
            }
            return dreamComponents[0];
        } catch (RemoteException e) {
            Log.w("DreamBackend", "Failed to get active dream", e);
            return null;
        }
    }

    public void launchSettings(Context context, DreamInfo dreamInfo) {
        new Object[1][0] = dreamInfo;
        if (dreamInfo != null && dreamInfo.settingsComponentName != null) {
            context.startActivity(new Intent().setComponent(dreamInfo.settingsComponentName));
        }
    }

    public void startDreaming() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager != null) {
            try {
                iDreamManager.dream();
            } catch (RemoteException e) {
                Log.w("DreamBackend", "Failed to dream", e);
            }
        }
    }

    private static ComponentName getDreamComponentName(ResolveInfo resolveInfo) {
        ServiceInfo serviceInfo;
        if (resolveInfo == null || (serviceInfo = resolveInfo.serviceInfo) == null) {
            return null;
        }
        return new ComponentName(serviceInfo.packageName, resolveInfo.serviceInfo.name);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0069, code lost:
        r6 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x001b, B:30:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0069 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:10:0x001b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0099 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.ComponentName getSettingsComponentName(android.content.pm.PackageManager r6, android.content.pm.ResolveInfo r7) {
        /*
            java.lang.String r0 = "DreamBackend"
            r1 = 0
            if (r7 == 0) goto L_0x00c2
            android.content.pm.ServiceInfo r2 = r7.serviceInfo
            if (r2 == 0) goto L_0x00c2
            android.os.Bundle r2 = r2.metaData
            if (r2 != 0) goto L_0x000f
            goto L_0x00c2
        L_0x000f:
            android.content.pm.ServiceInfo r2 = r7.serviceInfo     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x0076, all -> 0x006e }
            java.lang.String r3 = "android.service.dream"
            android.content.res.XmlResourceParser r2 = r2.loadXmlMetaData(r6, r3)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x0076, all -> 0x006e }
            if (r2 != 0) goto L_0x0024
            java.lang.String r6 = "No android.service.dream meta-data"
            android.util.Log.w(r0, r6)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            if (r2 == 0) goto L_0x0023
            r2.close()
        L_0x0023:
            return r1
        L_0x0024:
            android.content.pm.ServiceInfo r3 = r7.serviceInfo     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            android.content.res.Resources r6 = r6.getResourcesForApplication(r3)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r2)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
        L_0x0030:
            int r4 = r2.next()     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            r5 = 1
            if (r4 == r5) goto L_0x003b
            r5 = 2
            if (r4 == r5) goto L_0x003b
            goto L_0x0030
        L_0x003b:
            java.lang.String r4 = r2.getName()     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            java.lang.String r5 = "dream"
            boolean r4 = r5.equals(r4)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            if (r4 != 0) goto L_0x0052
            java.lang.String r6 = "Meta-data does not start with dream tag"
            android.util.Log.w(r0, r6)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            if (r2 == 0) goto L_0x0051
            r2.close()
        L_0x0051:
            return r1
        L_0x0052:
            int[] r4 = com.android.internal.R.styleable.Dream     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            android.content.res.TypedArray r6 = r6.obtainAttributes(r3, r4)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            r3 = 0
            java.lang.String r3 = r6.getString(r3)     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x006b, all -> 0x0069 }
            r6.recycle()     // Catch:{ NameNotFoundException | IOException | XmlPullParserException -> 0x0067, all -> 0x0069 }
            if (r2 == 0) goto L_0x0065
            r2.close()
        L_0x0065:
            r6 = r1
            goto L_0x007e
        L_0x0067:
            r6 = move-exception
            goto L_0x0079
        L_0x0069:
            r6 = move-exception
            goto L_0x0070
        L_0x006b:
            r6 = move-exception
            r3 = r1
            goto L_0x0079
        L_0x006e:
            r6 = move-exception
            r2 = r1
        L_0x0070:
            if (r2 == 0) goto L_0x0075
            r2.close()
        L_0x0075:
            throw r6
        L_0x0076:
            r6 = move-exception
            r2 = r1
            r3 = r2
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.close()
        L_0x007e:
            if (r6 == 0) goto L_0x0099
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Error parsing : "
            r2.append(r3)
            android.content.pm.ServiceInfo r7 = r7.serviceInfo
            java.lang.String r7 = r7.packageName
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            android.util.Log.w(r0, r7, r6)
            return r1
        L_0x0099:
            if (r3 == 0) goto L_0x00bb
            r6 = 47
            int r6 = r3.indexOf(r6)
            if (r6 >= 0) goto L_0x00bb
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            android.content.pm.ServiceInfo r7 = r7.serviceInfo
            java.lang.String r7 = r7.packageName
            r6.append(r7)
            java.lang.String r7 = "/"
            r6.append(r7)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
        L_0x00bb:
            if (r3 != 0) goto L_0x00be
            goto L_0x00c2
        L_0x00be:
            android.content.ComponentName r1 = android.content.ComponentName.unflattenFromString(r3)
        L_0x00c2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.dream.DreamBackend.getSettingsComponentName(android.content.pm.PackageManager, android.content.pm.ResolveInfo):android.content.ComponentName");
    }

    private static class DreamInfoComparator implements Comparator<DreamInfo> {
        private final ComponentName mDefaultDream;

        public DreamInfoComparator(ComponentName componentName) {
            this.mDefaultDream = componentName;
        }

        public int compare(DreamInfo dreamInfo, DreamInfo dreamInfo2) {
            return sortKey(dreamInfo).compareTo(sortKey(dreamInfo2));
        }

        private String sortKey(DreamInfo dreamInfo) {
            StringBuilder sb = new StringBuilder();
            sb.append(dreamInfo.componentName.equals(this.mDefaultDream) ? '0' : '1');
            sb.append(dreamInfo.caption);
            return sb.toString();
        }
    }
}
