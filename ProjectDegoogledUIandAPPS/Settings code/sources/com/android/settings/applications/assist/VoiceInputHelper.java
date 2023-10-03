package com.android.settings.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.service.voice.VoiceInteractionServiceInfo;
import java.util.ArrayList;
import java.util.List;

public final class VoiceInputHelper {
    final ArrayList<InteractionInfo> mAvailableInteractionInfos = new ArrayList<>();
    final List<ResolveInfo> mAvailableRecognition;
    final ArrayList<RecognizerInfo> mAvailableRecognizerInfos = new ArrayList<>();
    final List<ResolveInfo> mAvailableVoiceInteractions;
    final Context mContext;
    ComponentName mCurrentRecognizer;
    ComponentName mCurrentVoiceInteraction;

    public static class BaseInfo implements Comparable {
        public final CharSequence appLabel;
        public final ComponentName componentName;
        public final String key = this.componentName.flattenToShortString();
        public final CharSequence label;
        public final String labelStr;
        public final ServiceInfo service;
        public final ComponentName settings;

        public BaseInfo(PackageManager packageManager, ServiceInfo serviceInfo, String str) {
            this.service = serviceInfo;
            this.componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
            this.settings = str != null ? new ComponentName(serviceInfo.packageName, str) : null;
            this.label = serviceInfo.loadLabel(packageManager);
            this.labelStr = this.label.toString();
            this.appLabel = serviceInfo.applicationInfo.loadLabel(packageManager);
        }

        public int compareTo(Object obj) {
            return this.labelStr.compareTo(((BaseInfo) obj).labelStr);
        }
    }

    public static class InteractionInfo extends BaseInfo {
        public final VoiceInteractionServiceInfo serviceInfo;

        public InteractionInfo(PackageManager packageManager, VoiceInteractionServiceInfo voiceInteractionServiceInfo) {
            super(packageManager, voiceInteractionServiceInfo.getServiceInfo(), voiceInteractionServiceInfo.getSettingsActivity());
            this.serviceInfo = voiceInteractionServiceInfo;
        }
    }

    public static class RecognizerInfo extends BaseInfo {
        public RecognizerInfo(PackageManager packageManager, ServiceInfo serviceInfo, String str) {
            super(packageManager, serviceInfo, str);
        }
    }

    public VoiceInputHelper(Context context) {
        this.mContext = context;
        this.mAvailableVoiceInteractions = this.mContext.getPackageManager().queryIntentServices(new Intent("android.service.voice.VoiceInteractionService"), 128);
        this.mAvailableRecognition = this.mContext.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 128);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: android.content.res.XmlResourceParser} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: android.content.res.XmlResourceParser} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v23, resolved type: android.content.res.XmlResourceParser} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: android.content.res.XmlResourceParser} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x016a A[Catch:{ XmlPullParserException -> 0x0174, IOException -> 0x016b, NameNotFoundException -> 0x0162, all -> 0x015f, all -> 0x0193 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0173 A[Catch:{ XmlPullParserException -> 0x0174, IOException -> 0x016b, NameNotFoundException -> 0x0162, all -> 0x015f, all -> 0x0193 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x017d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x017d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildUi() {
        /*
            r13 = this;
            java.lang.String r0 = "error parsing recognition service meta-data"
            java.lang.String r1 = "VoiceInputHelper"
            android.content.Context r2 = r13.mContext
            android.content.ContentResolver r2 = r2.getContentResolver()
            java.lang.String r3 = "voice_interaction_service"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r3)
            r3 = 0
            if (r2 == 0) goto L_0x0020
            boolean r4 = r2.isEmpty()
            if (r4 != 0) goto L_0x0020
            android.content.ComponentName r2 = android.content.ComponentName.unflattenFromString(r2)
            r13.mCurrentVoiceInteraction = r2
            goto L_0x0022
        L_0x0020:
            r13.mCurrentVoiceInteraction = r3
        L_0x0022:
            android.util.ArraySet r2 = new android.util.ArraySet
            r2.<init>()
            java.util.List<android.content.pm.ResolveInfo> r4 = r13.mAvailableVoiceInteractions
            int r4 = r4.size()
            r5 = 0
            r6 = r5
        L_0x002f:
            if (r6 >= r4) goto L_0x00a2
            java.util.List<android.content.pm.ResolveInfo> r7 = r13.mAvailableVoiceInteractions
            java.lang.Object r7 = r7.get(r6)
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7
            android.service.voice.VoiceInteractionServiceInfo r8 = new android.service.voice.VoiceInteractionServiceInfo
            android.content.Context r9 = r13.mContext
            android.content.pm.PackageManager r9 = r9.getPackageManager()
            android.content.pm.ServiceInfo r10 = r7.serviceInfo
            r8.<init>(r9, r10)
            java.lang.String r9 = r8.getParseError()
            if (r9 == 0) goto L_0x007f
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Error in VoiceInteractionService "
            r9.append(r10)
            android.content.pm.ServiceInfo r10 = r7.serviceInfo
            java.lang.String r10 = r10.packageName
            r9.append(r10)
            java.lang.String r10 = "/"
            r9.append(r10)
            android.content.pm.ServiceInfo r7 = r7.serviceInfo
            java.lang.String r7 = r7.name
            r9.append(r7)
            java.lang.String r7 = ": "
            r9.append(r7)
            java.lang.String r7 = r8.getParseError()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            java.lang.String r8 = "VoiceInteractionService"
            android.util.Log.w(r8, r7)
            goto L_0x009f
        L_0x007f:
            java.util.ArrayList<com.android.settings.applications.assist.VoiceInputHelper$InteractionInfo> r9 = r13.mAvailableInteractionInfos
            com.android.settings.applications.assist.VoiceInputHelper$InteractionInfo r10 = new com.android.settings.applications.assist.VoiceInputHelper$InteractionInfo
            android.content.Context r11 = r13.mContext
            android.content.pm.PackageManager r11 = r11.getPackageManager()
            r10.<init>(r11, r8)
            r9.add(r10)
            android.content.ComponentName r9 = new android.content.ComponentName
            android.content.pm.ServiceInfo r7 = r7.serviceInfo
            java.lang.String r7 = r7.packageName
            java.lang.String r8 = r8.getRecognitionService()
            r9.<init>(r7, r8)
            r2.add(r9)
        L_0x009f:
            int r6 = r6 + 1
            goto L_0x002f
        L_0x00a2:
            java.util.ArrayList<com.android.settings.applications.assist.VoiceInputHelper$InteractionInfo> r4 = r13.mAvailableInteractionInfos
            java.util.Collections.sort(r4)
            android.content.Context r4 = r13.mContext
            android.content.ContentResolver r4 = r4.getContentResolver()
            java.lang.String r6 = "voice_recognition_service"
            java.lang.String r4 = android.provider.Settings.Secure.getString(r4, r6)
            if (r4 == 0) goto L_0x00c2
            boolean r6 = r4.isEmpty()
            if (r6 != 0) goto L_0x00c2
            android.content.ComponentName r4 = android.content.ComponentName.unflattenFromString(r4)
            r13.mCurrentRecognizer = r4
            goto L_0x00c4
        L_0x00c2:
            r13.mCurrentRecognizer = r3
        L_0x00c4:
            java.util.List<android.content.pm.ResolveInfo> r4 = r13.mAvailableRecognition
            int r4 = r4.size()
            r6 = r5
        L_0x00cb:
            if (r6 >= r4) goto L_0x019a
            java.util.List<android.content.pm.ResolveInfo> r7 = r13.mAvailableRecognition
            java.lang.Object r7 = r7.get(r6)
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7
            android.content.ComponentName r8 = new android.content.ComponentName
            android.content.pm.ServiceInfo r9 = r7.serviceInfo
            java.lang.String r9 = r9.packageName
            android.content.pm.ServiceInfo r10 = r7.serviceInfo
            java.lang.String r10 = r10.name
            r8.<init>(r9, r10)
            r2.contains(r8)
            android.content.pm.ServiceInfo r8 = r7.serviceInfo
            android.content.Context r9 = r13.mContext     // Catch:{ XmlPullParserException -> 0x0174, IOException -> 0x016b, NameNotFoundException -> 0x0162, all -> 0x015f }
            android.content.pm.PackageManager r9 = r9.getPackageManager()     // Catch:{ XmlPullParserException -> 0x0174, IOException -> 0x016b, NameNotFoundException -> 0x0162, all -> 0x015f }
            java.lang.String r10 = "android.speech"
            android.content.res.XmlResourceParser r9 = r8.loadXmlMetaData(r9, r10)     // Catch:{ XmlPullParserException -> 0x0174, IOException -> 0x016b, NameNotFoundException -> 0x0162, all -> 0x015f }
            if (r9 == 0) goto L_0x0146
            android.content.Context r10 = r13.mContext     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            android.content.pm.PackageManager r10 = r10.getPackageManager()     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            android.content.pm.ApplicationInfo r8 = r8.applicationInfo     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            android.content.res.Resources r8 = r10.getResourcesForApplication(r8)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            android.util.AttributeSet r10 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
        L_0x0105:
            int r11 = r9.next()     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            r12 = 1
            if (r11 == r12) goto L_0x0110
            r12 = 2
            if (r11 == r12) goto L_0x0110
            goto L_0x0105
        L_0x0110:
            java.lang.String r11 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.String r12 = "recognition-service"
            boolean r11 = r12.equals(r11)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            if (r11 == 0) goto L_0x0135
            int[] r11 = com.android.internal.R.styleable.RecognitionService     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            android.content.res.TypedArray r8 = r8.obtainAttributes(r10, r11)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.String r10 = r8.getString(r5)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            r8.recycle()     // Catch:{ XmlPullParserException -> 0x0133, IOException -> 0x0131, NameNotFoundException -> 0x012f }
            if (r9 == 0) goto L_0x017d
        L_0x012b:
            r9.close()
            goto L_0x017d
        L_0x012f:
            r8 = move-exception
            goto L_0x0165
        L_0x0131:
            r8 = move-exception
            goto L_0x016e
        L_0x0133:
            r8 = move-exception
            goto L_0x0177
        L_0x0135:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.String r10 = "Meta-data does not start with recognition-service tag"
            r8.<init>(r10)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            throw r8     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
        L_0x013d:
            r8 = move-exception
            r10 = r3
            goto L_0x0165
        L_0x0140:
            r8 = move-exception
            r10 = r3
            goto L_0x016e
        L_0x0143:
            r8 = move-exception
            r10 = r3
            goto L_0x0177
        L_0x0146:
            org.xmlpull.v1.XmlPullParserException r10 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            r11.<init>()     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.String r12 = "No android.speech meta-data for "
            r11.append(r12)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.String r8 = r8.packageName     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            r11.append(r8)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            java.lang.String r8 = r11.toString()     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            r10.<init>(r8)     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
            throw r10     // Catch:{ XmlPullParserException -> 0x0143, IOException -> 0x0140, NameNotFoundException -> 0x013d }
        L_0x015f:
            r13 = move-exception
            r9 = r3
            goto L_0x0194
        L_0x0162:
            r8 = move-exception
            r9 = r3
            r10 = r9
        L_0x0165:
            android.util.Log.e(r1, r0, r8)     // Catch:{ all -> 0x0193 }
            if (r9 == 0) goto L_0x017d
            goto L_0x012b
        L_0x016b:
            r8 = move-exception
            r9 = r3
            r10 = r9
        L_0x016e:
            android.util.Log.e(r1, r0, r8)     // Catch:{ all -> 0x0193 }
            if (r9 == 0) goto L_0x017d
            goto L_0x012b
        L_0x0174:
            r8 = move-exception
            r9 = r3
            r10 = r9
        L_0x0177:
            android.util.Log.e(r1, r0, r8)     // Catch:{ all -> 0x0193 }
            if (r9 == 0) goto L_0x017d
            goto L_0x012b
        L_0x017d:
            java.util.ArrayList<com.android.settings.applications.assist.VoiceInputHelper$RecognizerInfo> r8 = r13.mAvailableRecognizerInfos
            com.android.settings.applications.assist.VoiceInputHelper$RecognizerInfo r9 = new com.android.settings.applications.assist.VoiceInputHelper$RecognizerInfo
            android.content.Context r11 = r13.mContext
            android.content.pm.PackageManager r11 = r11.getPackageManager()
            android.content.pm.ServiceInfo r7 = r7.serviceInfo
            r9.<init>(r11, r7, r10)
            r8.add(r9)
            int r6 = r6 + 1
            goto L_0x00cb
        L_0x0193:
            r13 = move-exception
        L_0x0194:
            if (r9 == 0) goto L_0x0199
            r9.close()
        L_0x0199:
            throw r13
        L_0x019a:
            java.util.ArrayList<com.android.settings.applications.assist.VoiceInputHelper$RecognizerInfo> r13 = r13.mAvailableRecognizerInfos
            java.util.Collections.sort(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.assist.VoiceInputHelper.buildUi():void");
    }
}
