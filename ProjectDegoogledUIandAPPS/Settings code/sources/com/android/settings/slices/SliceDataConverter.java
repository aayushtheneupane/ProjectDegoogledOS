package com.android.settings.slices;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.provider.SearchIndexableResource;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import com.android.settings.accessibility.AccessibilitySettings;
import com.android.settings.accessibility.AccessibilitySlicePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.DatabaseIndexingUtils;
import com.android.settings.search.Indexable;
import com.android.settings.slices.SliceData;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class SliceDataConverter {
    private Context mContext;
    private final MetricsFeatureProvider mMetricsFeatureProvider;

    public SliceDataConverter(Context context) {
        this.mContext = context;
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public List<SliceData> getSliceData() {
        ArrayList arrayList = new ArrayList();
        for (Class next : FeatureFactory.getFactory(this.mContext).getSearchFeatureProvider().getSearchIndexableResources().getProviderValues()) {
            String name = next.getName();
            Indexable.SearchIndexProvider searchIndexProvider = DatabaseIndexingUtils.getSearchIndexProvider(next);
            if (searchIndexProvider == null) {
                Log.e("SliceDataConverter", name + " dose not implement Search Index Provider");
            } else {
                arrayList.addAll(getSliceDataFromProvider(searchIndexProvider, name));
            }
        }
        arrayList.addAll(getAccessibilitySliceData());
        return arrayList;
    }

    private List<SliceData> getSliceDataFromProvider(Indexable.SearchIndexProvider searchIndexProvider, String str) {
        ArrayList arrayList = new ArrayList();
        List<SearchIndexableResource> xmlResourcesToIndex = searchIndexProvider.getXmlResourcesToIndex(this.mContext, true);
        if (xmlResourcesToIndex == null) {
            return arrayList;
        }
        for (SearchIndexableResource searchIndexableResource : xmlResourcesToIndex) {
            int i = searchIndexableResource.xmlResId;
            if (i == 0) {
                Log.e("SliceDataConverter", str + " provides invalid XML (0) in search provider.");
            } else {
                arrayList.addAll(getSliceDataFromXML(i, str));
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0121, code lost:
        if (0 == 0) goto L_0x014d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0152  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.android.settings.slices.SliceData> getSliceDataFromXML(int r14, java.lang.String r15) {
        /*
            r13 = this;
            java.lang.String r0 = "SliceDataConverter"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = ""
            r3 = 0
            android.content.Context r4 = r13.mContext     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.content.res.XmlResourceParser r3 = r4.getXml(r14)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
        L_0x0014:
            int r4 = r3.next()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r5 = 1
            if (r4 == r5) goto L_0x001f
            r5 = 2
            if (r4 == r5) goto L_0x001f
            goto L_0x0014
        L_0x001f:
            java.lang.String r4 = r3.getName()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r5 = "PreferenceScreen"
            boolean r5 = r5.equals(r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x00c4
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r3)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.content.Context r5 = r13.mContext     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r4 = com.android.settings.core.PreferenceXmlParserUtils.getDataTitle(r5, r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.content.Context r5 = r13.mContext     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r6 = 2302(0x8fe, float:3.226E-42)
            java.util.List r14 = com.android.settings.core.PreferenceXmlParserUtils.extractMetadata(r5, r14, r6)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.util.Iterator r14 = r14.iterator()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
        L_0x0041:
            boolean r5 = r14.hasNext()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x00c1
            java.lang.Object r5 = r14.next()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.os.Bundle r5 = (android.os.Bundle) r5     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r6 = "controller"
            java.lang.String r2 = r5.getString(r6)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            boolean r6 = android.text.TextUtils.isEmpty(r2)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            if (r6 == 0) goto L_0x005a
            goto L_0x0041
        L_0x005a:
            java.lang.String r6 = "key"
            java.lang.String r6 = r5.getString(r6)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r7 = "title"
            java.lang.String r7 = r5.getString(r7)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r8 = "summary"
            java.lang.String r8 = r5.getString(r8)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r9 = "icon"
            int r9 = r5.getInt(r9)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.content.Context r10 = r13.mContext     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            int r10 = com.android.settings.slices.SliceBuilderUtils.getSliceType(r10, r2, r6)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r11 = "platform_slice"
            boolean r11 = r5.getBoolean(r11)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r12 = "unavailable_slice_subtitle"
            java.lang.String r5 = r5.getString(r12)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            com.android.settings.slices.SliceData$Builder r12 = new com.android.settings.slices.SliceData$Builder     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.<init>()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setKey(r6)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setTitle(r7)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setSummary(r8)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setIcon(r9)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setScreenTitle(r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setPreferenceControllerClassName(r2)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setFragmentName(r15)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setSliceType(r10)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setPlatformDefined(r11)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r12.setUnavailableSliceSubtitle(r5)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            com.android.settings.slices.SliceData r5 = r12.build()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            android.content.Context r6 = r13.mContext     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            com.android.settings.core.BasePreferenceController r6 = com.android.settings.slices.SliceBuilderUtils.getPreferenceController(r6, r5)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            boolean r7 = r6.isSliceable()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            if (r7 == 0) goto L_0x0041
            boolean r6 = r6.isAvailable()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            if (r6 == 0) goto L_0x0041
            r1.add(r5)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            goto L_0x0041
        L_0x00c1:
            if (r3 == 0) goto L_0x014d
            goto L_0x0123
        L_0x00c4:
            java.lang.RuntimeException r14 = new java.lang.RuntimeException     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r5.<init>()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r6 = "XML document must start with <PreferenceScreen> tag; found"
            r5.append(r6)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r5.append(r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r4 = " at "
            r5.append(r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r4 = r3.getPositionDescription()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r5.append(r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            java.lang.String r4 = r5.toString()     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            r14.<init>(r4)     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
            throw r14     // Catch:{ InvalidSliceDataException -> 0x0127, NotFoundException | IOException | XmlPullParserException -> 0x0110, Exception -> 0x00e9 }
        L_0x00e7:
            r13 = move-exception
            goto L_0x0150
        L_0x00e9:
            r14 = move-exception
            java.lang.String r4 = "Get slice data from XML failed "
            android.util.Log.w(r0, r4, r14)     // Catch:{ all -> 0x00e7 }
            com.android.settingslib.core.instrumentation.MetricsFeatureProvider r5 = r13.mMetricsFeatureProvider     // Catch:{ all -> 0x00e7 }
            r6 = 0
            r7 = 1727(0x6bf, float:2.42E-42)
            r8 = 0
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e7 }
            r13.<init>()     // Catch:{ all -> 0x00e7 }
            r13.append(r15)     // Catch:{ all -> 0x00e7 }
            java.lang.String r14 = "_"
            r13.append(r14)     // Catch:{ all -> 0x00e7 }
            r13.append(r2)     // Catch:{ all -> 0x00e7 }
            java.lang.String r9 = r13.toString()     // Catch:{ all -> 0x00e7 }
            r10 = 1
            r5.action(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00e7 }
            if (r3 == 0) goto L_0x014d
            goto L_0x0123
        L_0x0110:
            r14 = move-exception
            java.lang.String r2 = "Error parsing PreferenceScreen: "
            android.util.Log.w(r0, r2, r14)     // Catch:{ all -> 0x00e7 }
            com.android.settingslib.core.instrumentation.MetricsFeatureProvider r4 = r13.mMetricsFeatureProvider     // Catch:{ all -> 0x00e7 }
            r5 = 0
            r6 = 1726(0x6be, float:2.419E-42)
            r7 = 0
            r9 = 1
            r8 = r15
            r4.action(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00e7 }
            if (r3 == 0) goto L_0x014d
        L_0x0123:
            r3.close()
            goto L_0x014d
        L_0x0127:
            r14 = move-exception
            r6 = r2
            r8 = r3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x014e }
            r2.<init>()     // Catch:{ all -> 0x014e }
            java.lang.String r3 = "Invalid data when building SliceData for "
            r2.append(r3)     // Catch:{ all -> 0x014e }
            r2.append(r15)     // Catch:{ all -> 0x014e }
            java.lang.String r15 = r2.toString()     // Catch:{ all -> 0x014e }
            android.util.Log.w(r0, r15, r14)     // Catch:{ all -> 0x014e }
            com.android.settingslib.core.instrumentation.MetricsFeatureProvider r2 = r13.mMetricsFeatureProvider     // Catch:{ all -> 0x014e }
            r3 = 0
            r4 = 1725(0x6bd, float:2.417E-42)
            r5 = 0
            r7 = 1
            r2.action(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x014e }
            if (r8 == 0) goto L_0x014d
            r8.close()
        L_0x014d:
            return r1
        L_0x014e:
            r13 = move-exception
            r3 = r8
        L_0x0150:
            if (r3 == 0) goto L_0x0155
            r3.close()
        L_0x0155:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.slices.SliceDataConverter.getSliceDataFromXML(int, java.lang.String):java.util.List");
    }

    private List<SliceData> getAccessibilitySliceData() {
        ArrayList arrayList = new ArrayList();
        String name = AccessibilitySlicePreferenceController.class.getName();
        String name2 = AccessibilitySettings.class.getName();
        CharSequence text = this.mContext.getText(C1715R.string.accessibility_settings);
        SliceData.Builder builder = new SliceData.Builder();
        builder.setFragmentName(name2);
        builder.setScreenTitle(text);
        builder.setPreferenceControllerClassName(name);
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, this.mContext.getResources().getStringArray(C1715R.array.config_settings_slices_accessibility_components));
        List<AccessibilityServiceInfo> accessibilityServiceInfoList = getAccessibilityServiceInfoList();
        PackageManager packageManager = this.mContext.getPackageManager();
        for (AccessibilityServiceInfo resolveInfo : accessibilityServiceInfoList) {
            ResolveInfo resolveInfo2 = resolveInfo.getResolveInfo();
            ServiceInfo serviceInfo = resolveInfo2.serviceInfo;
            String flattenToString = new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString();
            if (hashSet.contains(flattenToString)) {
                String charSequence = resolveInfo2.loadLabel(packageManager).toString();
                int iconResource = resolveInfo2.getIconResource();
                if (iconResource == 0) {
                    iconResource = C1715R.C1717drawable.ic_accessibility_generic;
                }
                builder.setKey(flattenToString);
                builder.setTitle(charSequence);
                builder.setIcon(iconResource);
                builder.setSliceType(1);
                try {
                    arrayList.add(builder.build());
                } catch (SliceData.InvalidSliceDataException e) {
                    Log.w("SliceDataConverter", "Invalid data when building a11y SliceData for " + flattenToString, e);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<AccessibilityServiceInfo> getAccessibilityServiceInfoList() {
        return AccessibilityManager.getInstance(this.mContext).getInstalledAccessibilityServiceList();
    }
}
