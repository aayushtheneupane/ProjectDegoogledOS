package androidx.appcompat.mms;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.appcompat.mms.ApnSettingsLoader;
import androidx.appcompat.mms.ApnsXmlParser;
import com.android.messaging.R;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

class DefaultApnSettingsLoader implements ApnSettingsLoader {
    private static final String APN_APN = "apn";
    private static final String APN_MCC = "mcc";
    private static final String APN_MMSC = "mmsc";
    private static final String APN_MMSPORT = "mmsport";
    private static final String APN_MMSPROXY = "mmsproxy";
    private static final String APN_MNC = "mnc";
    private static final String[] APN_PROJECTION = {APN_TYPE, APN_MMSC, APN_MMSPROXY, APN_MMSPORT};
    private static final String APN_TYPE = "type";
    public static final String APN_TYPE_ALL = "*";
    public static final String APN_TYPE_MMS = "mms";
    private static final int COLUMN_MMSC = 1;
    private static final int COLUMN_MMSPORT = 3;
    private static final int COLUMN_MMSPROXY = 2;
    private static final int COLUMN_TYPE = 0;
    private final SparseArray mApnsCache = new SparseArray();
    private final Context mContext;

    class BaseApn implements ApnSettingsLoader.Apn {
        private final String mMmsProxy;
        private final int mMmsProxyPort;
        private final String mMmsc;

        public BaseApn(String str, String str2, int i) {
            this.mMmsc = str;
            this.mMmsProxy = str2;
            this.mMmsProxyPort = i;
        }

        public static BaseApn from(String str, String str2, String str3, String str4) {
            if (!DefaultApnSettingsLoader.isValidApnType(DefaultApnSettingsLoader.trimWithNullCheck(str), DefaultApnSettingsLoader.APN_TYPE_MMS)) {
                return null;
            }
            String access$000 = DefaultApnSettingsLoader.trimWithNullCheck(str2);
            if (TextUtils.isEmpty(access$000)) {
                return null;
            }
            String access$100 = DefaultApnSettingsLoader.trimV4AddrZeros(access$000);
            try {
                new URI(access$100);
                String access$0002 = DefaultApnSettingsLoader.trimWithNullCheck(str3);
                int i = 80;
                if (!TextUtils.isEmpty(access$0002)) {
                    access$0002 = DefaultApnSettingsLoader.trimV4AddrZeros(access$0002);
                    String access$0003 = DefaultApnSettingsLoader.trimWithNullCheck(str4);
                    if (access$0003 != null) {
                        try {
                            i = Integer.parseInt(access$0003);
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
                return new BaseApn(access$100, access$0002, i);
            } catch (URISyntaxException unused2) {
                return null;
            }
        }

        public boolean equals(BaseApn baseApn) {
            return TextUtils.equals(this.mMmsc, baseApn.getMmsc()) && TextUtils.equals(this.mMmsProxy, baseApn.getMmsProxy()) && this.mMmsProxyPort == baseApn.getMmsProxyPort();
        }

        public String getMmsProxy() {
            return this.mMmsProxy;
        }

        public int getMmsProxyPort() {
            return this.mMmsProxyPort;
        }

        public String getMmsc() {
            return this.mMmsc;
        }

        public void setSuccess() {
        }
    }

    class MemoryApn implements ApnSettingsLoader.Apn {
        private final List mApns;
        private final BaseApn mBase;

        public MemoryApn(List list, BaseApn baseApn) {
            this.mApns = list;
            this.mBase = baseApn;
        }

        public static MemoryApn from(List list, String str, String str2, String str3, String str4) {
            BaseApn from;
            if (list == null || (from = BaseApn.from(str, str2, str3, str4)) == null) {
                return null;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ApnSettingsLoader.Apn apn = (ApnSettingsLoader.Apn) it.next();
                if ((apn instanceof MemoryApn) && ((MemoryApn) apn).equals(from)) {
                    return null;
                }
            }
            return new MemoryApn(list, from);
        }

        public boolean equals(BaseApn baseApn) {
            if (baseApn == null) {
                return false;
            }
            return this.mBase.equals(baseApn);
        }

        public String getMmsProxy() {
            return this.mBase.getMmsProxy();
        }

        public int getMmsProxyPort() {
            return this.mBase.getMmsProxyPort();
        }

        public String getMmsc() {
            return this.mBase.getMmsc();
        }

        public void setSuccess() {
            boolean z;
            synchronized (this.mApns) {
                z = false;
                if (this.mApns.get(0) != this) {
                    this.mApns.remove(this);
                    this.mApns.add(0, this);
                    z = true;
                }
            }
            if (z) {
                StringBuilder Pa = C0632a.m1011Pa("Set APN [MMSC=");
                Pa.append(getMmsc());
                Pa.append(", PROXY=");
                Pa.append(getMmsProxy());
                Pa.append(", PORT=");
                Pa.append(getMmsProxyPort());
                Pa.append("] to be first");
                Log.d("MmsLib", Pa.toString());
            }
        }
    }

    DefaultApnSettingsLoader(Context context) {
        this.mContext = context;
    }

    public static boolean isValidApnType(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        for (String str3 : str.split(",")) {
            if (str3.equals(str2) || str3.equals(APN_TYPE_ALL)) {
                return true;
            }
        }
        return false;
    }

    private void loadFromResources(int i, final String str, final List list) {
        Log.i("MmsLib", "Loading APNs from resources, apnName=" + str);
        final int[] mccMnc = Utils.getMccMnc(this.mContext, i);
        if (mccMnc[0] == 0 && mccMnc[0] == 0) {
            Log.w("MmsLib", "Can not get valid mcc/mnc from system");
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = this.mContext.getResources().getXml(R.xml.apns);
            new ApnsXmlParser(xmlResourceParser, new ApnsXmlParser.ApnProcessor() {
                public void process(ContentValues contentValues) {
                    MemoryApn from;
                    String access$000 = DefaultApnSettingsLoader.trimWithNullCheck(contentValues.getAsString(DefaultApnSettingsLoader.APN_MCC));
                    String access$0002 = DefaultApnSettingsLoader.trimWithNullCheck(contentValues.getAsString(DefaultApnSettingsLoader.APN_MNC));
                    String access$0003 = DefaultApnSettingsLoader.trimWithNullCheck(contentValues.getAsString(DefaultApnSettingsLoader.APN_APN));
                    try {
                        if (mccMnc[0] != Integer.parseInt(access$000) || mccMnc[1] != Integer.parseInt(access$0002)) {
                            return;
                        }
                        if ((TextUtils.isEmpty(str) || str.equalsIgnoreCase(access$0003)) && (from = MemoryApn.from(list, contentValues.getAsString(DefaultApnSettingsLoader.APN_TYPE), contentValues.getAsString(DefaultApnSettingsLoader.APN_MMSC), contentValues.getAsString(DefaultApnSettingsLoader.APN_MMSPROXY), contentValues.getAsString(DefaultApnSettingsLoader.APN_MMSPORT))) != null) {
                            list.add(from);
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }).parse();
            if (xmlResourceParser == null) {
                return;
            }
        } catch (Resources.NotFoundException e) {
            Log.w("MmsLib", "Can not get apns.xml " + e);
            if (xmlResourceParser == null) {
                return;
            }
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
        xmlResourceParser.close();
    }

    private void loadFromSystem(int i, String str, List list) {
        Uri uri;
        BaseApn from;
        int i2 = Build.VERSION.SDK_INT;
        if (i != -1) {
            Uri uri2 = Telephony.Carriers.CONTENT_URI;
            uri = Uri.withAppendedPath(uri2, "/subId/" + i);
        } else {
            uri = Telephony.Carriers.CONTENT_URI;
        }
        try {
            Cursor querySystem = querySystem(uri, true, str);
            if (querySystem == null) {
                querySystem = querySystem(uri, false, str);
                if (querySystem == null) {
                    querySystem = querySystem(uri, true, (String) null);
                    if (querySystem == null) {
                        querySystem = querySystem(uri, false, (String) null);
                    }
                }
            }
            if (querySystem != null) {
                try {
                    if (querySystem.moveToFirst() && (from = BaseApn.from(querySystem.getString(0), querySystem.getString(1), querySystem.getString(2), querySystem.getString(3))) != null) {
                        list.add(from);
                    }
                } finally {
                    querySystem.close();
                }
            }
        } catch (SecurityException unused) {
        }
    }

    private void loadLocked(int i, String str, List list) {
        loadFromSystem(i, str, list);
        if (list.size() <= 0) {
            loadFromResources(i, str, list);
            if (list.size() <= 0) {
                loadFromResources(i, (String) null, list);
            }
        }
    }

    private Cursor querySystem(Uri uri, boolean z, String str) {
        String[] strArr;
        Log.i("MmsLib", "Loading APNs from system, checkCurrent=" + z + " apnName=" + str);
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("current");
            sb.append(" IS NOT NULL");
        }
        String trimWithNullCheck = trimWithNullCheck(str);
        if (!TextUtils.isEmpty(trimWithNullCheck)) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append(APN_APN);
            sb.append("=?");
            strArr = new String[]{trimWithNullCheck};
        } else {
            strArr = null;
        }
        try {
            Cursor query = this.mContext.getContentResolver().query(uri, APN_PROJECTION, sb.toString(), strArr, (String) null);
            if (query != null) {
                if (query.getCount() >= 1) {
                    return query;
                }
            }
            if (query != null) {
                query.close();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Query ");
            sb2.append(uri);
            sb2.append(" with apn ");
            sb2.append(trimWithNullCheck);
            sb2.append(" and ");
            sb2.append(z ? "checking CURRENT" : "not checking CURRENT");
            sb2.append(" returned empty");
            Log.w("MmsLib", sb2.toString());
            return null;
        } catch (SQLiteException e) {
            Log.w("MmsLib", "APN table query exception: " + e);
            return null;
        } catch (SecurityException e2) {
            Log.w("MmsLib", "Platform restricts APN table access: " + e2);
            throw e2;
        }
    }

    /* access modifiers changed from: private */
    public static String trimV4AddrZeros(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("\\.");
        if (split.length != 4) {
            return str;
        }
        StringBuilder sb = new StringBuilder(16);
        int i = 0;
        while (i < 4) {
            try {
                if (split[i].length() > 3) {
                    return str;
                }
                sb.append(Integer.parseInt(split[i]));
                if (i < 3) {
                    sb.append('.');
                }
                i++;
            } catch (NumberFormatException unused) {
                return str;
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static String trimWithNullCheck(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public List get(String str) {
        List list;
        boolean z;
        int effectiveSubscriptionId = Utils.getEffectiveSubscriptionId(-1);
        synchronized (this) {
            list = (List) this.mApnsCache.get(effectiveSubscriptionId);
            if (list == null) {
                list = new ArrayList();
                this.mApnsCache.put(effectiveSubscriptionId, list);
                loadLocked(effectiveSubscriptionId, str, list);
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            StringBuilder Pa = C0632a.m1011Pa("Loaded ");
            Pa.append(list.size());
            Pa.append(" APNs");
            Log.i("MmsLib", Pa.toString());
        }
        return list;
    }
}
