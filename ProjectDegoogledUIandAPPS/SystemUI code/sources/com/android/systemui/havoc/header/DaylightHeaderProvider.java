package com.android.systemui.havoc.header;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.havoc.header.StatusBarHeaderMachine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DaylightHeaderProvider implements StatusBarHeaderMachine.IStatusBarHeaderProvider {
    private PendingIntent mAlarmIntent;
    private int mAlarmIntervalMinutes = 0;
    private Context mContext;
    private int mHeaderIndex;
    private String mHeaderName;
    private List<DaylightHeaderInfo> mHeadersList;
    private boolean mLinearMode;
    private String mPackageName;
    private boolean mRandomMode;
    private Resources mRes;
    private String mSettingHeaderPackage;

    public String getName() {
        return "daylight";
    }

    private class DaylightHeaderInfo {
        public int mDay;
        public int mHour;
        public String mImage;
        public int mMonth;
        public int mType;

        private DaylightHeaderInfo() {
            this.mType = 0;
            this.mHour = -1;
            this.mDay = -1;
            this.mMonth = -1;
        }
    }

    public DaylightHeaderProvider(Context context) {
        this.mContext = context;
    }

    public void settingsChanged(Uri uri) {
        String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "status_bar_daylight_header_pack", -2);
        boolean z = true;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_custom_header", 0, -2) != 1) {
            z = false;
        }
        if (z) {
            stopAlarm();
            if (stringForUser == null) {
                loadDefaultHeaderPackage();
            } else {
                String str = this.mSettingHeaderPackage;
                if (str == null || !stringForUser.equals(str)) {
                    this.mSettingHeaderPackage = stringForUser;
                    loadCustomHeaderPackage();
                }
            }
            startAlarm();
        }
    }

    public void enableProvider() {
        settingsChanged((Uri) null);
    }

    public void disableProvider() {
        stopAlarm();
    }

    private void stopAlarm() {
        if (this.mAlarmIntent != null) {
            ((AlarmManager) this.mContext.getSystemService("alarm")).cancel(this.mAlarmIntent);
        }
        this.mAlarmIntent = null;
    }

    private void startAlarm() {
        Calendar instance = Calendar.getInstance();
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        PendingIntent pendingIntent = this.mAlarmIntent;
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
        this.mAlarmIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.systemui.havoc.header.STATUS_BAR_HEADER_UPDATE"), 268435456);
        if (this.mAlarmIntervalMinutes == 0) {
            instance.add(11, 1);
            instance.set(12, 0);
        }
        long timeInMillis = instance.getTimeInMillis();
        int i = this.mAlarmIntervalMinutes;
        alarmManager.setInexactRepeating(1, timeInMillis, i == 0 ? 3600000 : (long) (i * 60 * 1000), this.mAlarmIntent);
    }

    private void loadCustomHeaderPackage() {
        if (this.mSettingHeaderPackage.indexOf("/") != -1) {
            String[] split = this.mSettingHeaderPackage.split("/");
            this.mPackageName = split[0];
            this.mHeaderName = split[1];
        } else {
            this.mPackageName = this.mSettingHeaderPackage;
            this.mHeaderName = null;
        }
        try {
            this.mRes = this.mContext.getPackageManager().getResourcesForApplication(this.mPackageName);
            loadHeaders();
        } catch (Exception e) {
            Log.e("DaylightHeaderProvider", "Failed to load icon pack " + this.mHeaderName, e);
            this.mRes = null;
        }
        if (this.mRes == null) {
            Log.w("DaylightHeaderProvider", "Header pack loading failed - loading default");
            loadDefaultHeaderPackage();
        }
    }

    private void loadDefaultHeaderPackage() {
        this.mPackageName = "com.android.systemui";
        this.mHeaderName = null;
        this.mSettingHeaderPackage = this.mPackageName;
        try {
            this.mRes = this.mContext.getPackageManager().getResourcesForApplication(this.mPackageName);
            loadHeaders();
        } catch (Exception unused) {
            this.mRes = null;
        }
        if (this.mRes == null) {
            Log.w("DaylightHeaderProvider", "No default package found");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072 A[SYNTHETIC, Splitter:B:22:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadHeaders() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.mHeadersList = r0
            r0 = 0
            java.lang.String r1 = r4.mHeaderName     // Catch:{ all -> 0x0065 }
            if (r1 != 0) goto L_0x0019
            android.content.res.Resources r1 = r4.mRes     // Catch:{ all -> 0x0065 }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ all -> 0x0065 }
            java.lang.String r2 = "daylight_header.xml"
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ all -> 0x0065 }
            goto L_0x0044
        L_0x0019:
            java.lang.String r1 = r4.mHeaderName     // Catch:{ all -> 0x0065 }
            java.lang.String r2 = "."
            int r1 = r1.lastIndexOf(r2)     // Catch:{ all -> 0x0065 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r2.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = r4.mHeaderName     // Catch:{ all -> 0x0065 }
            int r1 = r1 + 1
            java.lang.String r1 = r3.substring(r1)     // Catch:{ all -> 0x0065 }
            r2.append(r1)     // Catch:{ all -> 0x0065 }
            java.lang.String r1 = ".xml"
            r2.append(r1)     // Catch:{ all -> 0x0065 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0065 }
            android.content.res.Resources r2 = r4.mRes     // Catch:{ all -> 0x0065 }
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch:{ all -> 0x0065 }
            java.io.InputStream r1 = r2.open(r1)     // Catch:{ all -> 0x0065 }
        L_0x0044:
            org.xmlpull.v1.XmlPullParserFactory r2 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ all -> 0x0063 }
            org.xmlpull.v1.XmlPullParser r0 = r2.newPullParser()     // Catch:{ all -> 0x0063 }
            java.lang.String r2 = "UTF-8"
            r0.setInput(r1, r2)     // Catch:{ all -> 0x0063 }
            r4.loadResourcesFromXmlParser(r0)     // Catch:{ all -> 0x0063 }
            boolean r4 = r0 instanceof android.content.res.XmlResourceParser
            if (r4 == 0) goto L_0x005d
            android.content.res.XmlResourceParser r0 = (android.content.res.XmlResourceParser) r0
            r0.close()
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            return
        L_0x0063:
            r4 = move-exception
            goto L_0x0067
        L_0x0065:
            r4 = move-exception
            r1 = r0
        L_0x0067:
            boolean r2 = r0 instanceof android.content.res.XmlResourceParser
            if (r2 == 0) goto L_0x0070
            android.content.res.XmlResourceParser r0 = (android.content.res.XmlResourceParser) r0
            r0.close()
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ IOException -> 0x0075 }
        L_0x0075:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.havoc.header.DaylightHeaderProvider.loadHeaders():void");
    }

    private void loadResourcesFromXmlParser(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        this.mRandomMode = false;
        this.mLinearMode = false;
        this.mAlarmIntervalMinutes = 0;
        do {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name.equalsIgnoreCase("day_header")) {
                    if (!this.mRandomMode) {
                        DaylightHeaderInfo daylightHeaderInfo = new DaylightHeaderInfo();
                        daylightHeaderInfo.mHour = -1;
                        daylightHeaderInfo.mType = 0;
                        String attributeValue = xmlPullParser.getAttributeValue((String) null, "day");
                        if (attributeValue != null) {
                            daylightHeaderInfo.mDay = Integer.valueOf(attributeValue).intValue();
                        }
                        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "month");
                        if (attributeValue2 != null) {
                            daylightHeaderInfo.mMonth = Integer.valueOf(attributeValue2).intValue();
                        }
                        String attributeValue3 = xmlPullParser.getAttributeValue((String) null, "hour");
                        if (attributeValue3 != null) {
                            daylightHeaderInfo.mHour = Integer.valueOf(attributeValue3).intValue();
                        }
                        String attributeValue4 = xmlPullParser.getAttributeValue((String) null, "image");
                        if (attributeValue4 != null) {
                            daylightHeaderInfo.mImage = attributeValue4;
                        }
                        if (!(daylightHeaderInfo.mImage == null || daylightHeaderInfo.mDay == -1 || daylightHeaderInfo.mMonth == -1)) {
                            this.mHeadersList.add(daylightHeaderInfo);
                        }
                    }
                } else if (name.equalsIgnoreCase("hour_header")) {
                    if (!this.mRandomMode) {
                        DaylightHeaderInfo daylightHeaderInfo2 = new DaylightHeaderInfo();
                        daylightHeaderInfo2.mType = 1;
                        String attributeValue5 = xmlPullParser.getAttributeValue((String) null, "hour");
                        if (attributeValue5 != null) {
                            daylightHeaderInfo2.mHour = Integer.valueOf(attributeValue5).intValue();
                        }
                        String attributeValue6 = xmlPullParser.getAttributeValue((String) null, "image");
                        if (attributeValue6 != null) {
                            daylightHeaderInfo2.mImage = attributeValue6;
                        }
                        if (!(daylightHeaderInfo2.mImage == null || daylightHeaderInfo2.mHour == -1)) {
                            this.mHeadersList.add(daylightHeaderInfo2);
                        }
                    }
                } else if (name.equalsIgnoreCase("random_header") || name.equalsIgnoreCase("list_header")) {
                    this.mRandomMode = name.equalsIgnoreCase("random_header");
                    this.mLinearMode = name.equalsIgnoreCase("list_header");
                    DaylightHeaderInfo daylightHeaderInfo3 = new DaylightHeaderInfo();
                    daylightHeaderInfo3.mType = 2;
                    String attributeValue7 = xmlPullParser.getAttributeValue((String) null, "image");
                    if (attributeValue7 != null) {
                        daylightHeaderInfo3.mImage = attributeValue7;
                    }
                    if (daylightHeaderInfo3.mImage != null) {
                        this.mHeadersList.add(daylightHeaderInfo3);
                    }
                } else if (name.equalsIgnoreCase("change_interval")) {
                    this.mAlarmIntervalMinutes = Integer.valueOf(xmlPullParser.getAttributeValue((String) null, "minutes")).intValue();
                }
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        if (this.mRandomMode) {
            Collections.shuffle(this.mHeadersList);
        }
        if (!this.mLinearMode && !this.mRandomMode) {
            this.mAlarmIntervalMinutes = 0;
        }
    }

    private DaylightHeaderInfo getLastHourHeader(List<DaylightHeaderInfo> list) {
        DaylightHeaderInfo daylightHeaderInfo = null;
        if (!(list == null || list.size() == 0)) {
            int i = -1;
            for (DaylightHeaderInfo next : list) {
                int i2 = next.mHour;
                if (i2 != -1 && (daylightHeaderInfo == null || i2 > i)) {
                    daylightHeaderInfo = next;
                    i = i2;
                }
            }
        }
        return daylightHeaderInfo;
    }

    private DaylightHeaderInfo getFirstHourHeader(List<DaylightHeaderInfo> list) {
        DaylightHeaderInfo daylightHeaderInfo = null;
        if (!(list == null || list.size() == 0)) {
            int i = -1;
            for (DaylightHeaderInfo next : list) {
                int i2 = next.mHour;
                if (i2 != -1 && (daylightHeaderInfo == null || i2 < i)) {
                    daylightHeaderInfo = next;
                    i = i2;
                }
            }
        }
        return daylightHeaderInfo;
    }

    public Drawable getCurrent(Calendar calendar) {
        List<DaylightHeaderInfo> list;
        if (!Utils.isPackageInstalled(this.mContext, this.mPackageName)) {
            Log.w("DaylightHeaderProvider", "Header pack no longer available - loading default " + this.mPackageName);
            loadDefaultHeaderPackage();
        }
        if (!(this.mRes == null || (list = this.mHeadersList) == null || list.size() == 0)) {
            try {
                if (!this.mRandomMode) {
                    if (!this.mLinearMode) {
                        List<DaylightHeaderInfo> todayHeaders = getTodayHeaders(calendar);
                        if (todayHeaders.size() != 0) {
                            DaylightHeaderInfo firstHourHeader = getFirstHourHeader(todayHeaders);
                            DaylightHeaderInfo lastHourHeader = getLastHourHeader(todayHeaders);
                            if (firstHourHeader != null) {
                                if (lastHourHeader != null) {
                                    return this.mRes.getDrawable(this.mRes.getIdentifier(getMatchingHeader(calendar, todayHeaders).mImage, "drawable", this.mPackageName), (Resources.Theme) null);
                                }
                            }
                            return this.mRes.getDrawable(this.mRes.getIdentifier(todayHeaders.get(0).mImage, "drawable", this.mPackageName), (Resources.Theme) null);
                        }
                        List<DaylightHeaderInfo> hourHeaders = getHourHeaders();
                        if (hourHeaders.size() != 0) {
                            return this.mRes.getDrawable(this.mRes.getIdentifier(getMatchingHeader(calendar, hourHeaders).mImage, "drawable", this.mPackageName), (Resources.Theme) null);
                        }
                    }
                }
                DaylightHeaderInfo daylightHeaderInfo = this.mHeadersList.get(this.mHeaderIndex);
                this.mHeaderIndex++;
                if (this.mHeaderIndex == this.mHeadersList.size()) {
                    if (this.mRandomMode) {
                        Collections.shuffle(this.mHeadersList);
                    }
                    this.mHeaderIndex = 0;
                }
                return this.mRes.getDrawable(this.mRes.getIdentifier(daylightHeaderInfo.mImage, "drawable", this.mPackageName), (Resources.Theme) null);
            } catch (Resources.NotFoundException unused) {
                Log.w("DaylightHeaderProvider", "No drawable found for " + calendar + " in " + this.mPackageName);
            }
        }
        return null;
    }

    private boolean isItToday(Calendar calendar, DaylightHeaderInfo daylightHeaderInfo) {
        if (calendar.get(2) + 1 == daylightHeaderInfo.mMonth && calendar.get(5) == daylightHeaderInfo.mDay) {
            return true;
        }
        return false;
    }

    private List<DaylightHeaderInfo> getTodayHeaders(Calendar calendar) {
        ArrayList arrayList = new ArrayList();
        for (DaylightHeaderInfo next : this.mHeadersList) {
            if (next.mType == 0 && isItToday(calendar, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private List<DaylightHeaderInfo> getHourHeaders() {
        ArrayList arrayList = new ArrayList();
        for (DaylightHeaderInfo next : this.mHeadersList) {
            if (next.mType == 1) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private DaylightHeaderInfo getMatchingHeader(Calendar calendar, List<DaylightHeaderInfo> list) {
        DaylightHeaderInfo firstHourHeader = getFirstHourHeader(list);
        DaylightHeaderInfo lastHourHeader = getLastHourHeader(list);
        Iterator<DaylightHeaderInfo> it = list.iterator();
        DaylightHeaderInfo daylightHeaderInfo = firstHourHeader;
        while (it.hasNext()) {
            DaylightHeaderInfo next = it.next();
            if (next.mHour > calendar.get(11)) {
                return next == firstHourHeader ? lastHourHeader : daylightHeaderInfo;
            }
            daylightHeaderInfo = next;
        }
        return lastHourHeader;
    }
}
