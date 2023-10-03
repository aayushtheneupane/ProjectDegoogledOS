package androidx.appcompat.mms;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p026b.p027a.p030b.p031a.C0632a;

public class MmsHttpClient {
    private static final String ACCEPT_LANG_FOR_US_LOCALE = "en-US";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String HEADER_VALUE_ACCEPT = "*/*, application/vnd.wap.mms-message, application/vnd.wap.sic";
    private static final String HEADER_VALUE_CONTENT_TYPE_WITHOUT_CHARSET = "application/vnd.wap.mms-message";
    private static final String HEADER_VALUE_CONTENT_TYPE_WITH_CHARSET = "application/vnd.wap.mms-message; charset=utf-8";
    private static final String MACRO_LINE1 = "LINE1";
    private static final String MACRO_LINE1NOCOUNTRYCODE = "LINE1NOCOUNTRYCODE";
    private static final String MACRO_NAI = "NAI";
    private static final Pattern MACRO_P = Pattern.compile("##(\\S+)##");
    static final String METHOD_GET = "GET";
    static final String METHOD_POST = "POST";
    private static final String NAI_PROPERTY = "persist.radio.cdma.nai";
    private final Context mContext;
    private final TelephonyManager mTelephonyManager;

    MmsHttpClient(Context context) {
        this.mContext = context;
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    private void addExtraHeaders(HttpURLConnection httpURLConnection, Bundle bundle) {
        String string = bundle.getString(CarrierConfigValuesLoader.CONFIG_HTTP_PARAMS);
        if (!TextUtils.isEmpty(string)) {
            for (String split : string.split("\\|")) {
                String[] split2 = split.split(":", 2);
                if (split2.length == 2) {
                    String trim = split2[0].trim();
                    String resolveMacro = resolveMacro(split2[1].trim(), bundle);
                    if (!TextUtils.isEmpty(trim) && !TextUtils.isEmpty(resolveMacro)) {
                        httpURLConnection.setRequestProperty(trim, resolveMacro);
                    }
                }
            }
        }
    }

    private static void addLocaleToHttpAcceptLanguage(StringBuilder sb, Locale locale) {
        String convertObsoleteLanguageCodeToNew = convertObsoleteLanguageCodeToNew(locale.getLanguage());
        if (convertObsoleteLanguageCodeToNew != null) {
            sb.append(convertObsoleteLanguageCodeToNew);
            String country = locale.getCountry();
            if (country != null) {
                sb.append("-");
                sb.append(country);
            }
        }
    }

    private static void checkMethod(String str) {
        if (!METHOD_GET.equals(str) && !METHOD_POST.equals(str)) {
            throw new MmsHttpException(0, C0632a.m1025k("Invalid method ", str));
        }
    }

    private static String convertObsoleteLanguageCodeToNew(String str) {
        if (str == null) {
            return null;
        }
        if ("iw".equals(str)) {
            return "he";
        }
        if ("in".equals(str)) {
            return "id";
        }
        return "ji".equals(str) ? "yi" : str;
    }

    public static String getCurrentAcceptLanguage(Locale locale) {
        StringBuilder sb = new StringBuilder();
        addLocaleToHttpAcceptLanguage(sb, locale);
        if (!Locale.US.equals(locale)) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(ACCEPT_LANG_FOR_US_LOCALE);
        }
        return sb.toString();
    }

    private String getEncodedNai(String str) {
        byte[] bArr;
        int i = Build.VERSION.SDK_INT;
        String naiBySystemApi = getNaiBySystemApi(getSlotId(Utils.getEffectiveSubscriptionId(-1)));
        if (TextUtils.isEmpty(naiBySystemApi)) {
            return null;
        }
        Log.i("MmsLib", "NAI is not empty");
        if (!TextUtils.isEmpty(str)) {
            naiBySystemApi = C0632a.m1025k(naiBySystemApi, str);
        }
        try {
            bArr = Base64.encode(naiBySystemApi.getBytes("UTF-8"), 2);
        } catch (UnsupportedEncodingException unused) {
            bArr = Base64.encode(naiBySystemApi.getBytes(), 2);
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused2) {
            return new String(bArr);
        }
    }

    private String getHttpParamMacro(String str, Bundle bundle) {
        if (MACRO_LINE1.equals(str)) {
            return getSelfNumber();
        }
        if (MACRO_LINE1NOCOUNTRYCODE.equals(str)) {
            return PhoneNumberHelper.getNumberNoCountryCode(getSelfNumber(), getSimOrLocaleCountry());
        }
        if (MACRO_NAI.equals(str)) {
            return getEncodedNai(bundle.getString(CarrierConfigValuesLoader.CONFIG_NAI_SUFFIX, CarrierConfigValuesLoader.CONFIG_NAI_SUFFIX_DEFAULT));
        }
        return null;
    }

    private String getNaiBySystemApi(int i) {
        try {
            Method method = this.mTelephonyManager.getClass().getMethod("getNai", new Class[]{Integer.TYPE});
            if (method == null) {
                return null;
            }
            return (String) method.invoke(this.mTelephonyManager, new Object[]{Integer.valueOf(i)});
        } catch (Exception e) {
            Log.w("MmsLib", "TelephonyManager.getNai failed " + e);
            return null;
        }
    }

    private static String getNaiBySystemProperty() {
        Method method;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            if (!(cls == null || (method = cls.getMethod("get", new Class[]{String.class})) == null)) {
                return (String) method.invoke((Object) null, new Object[]{NAI_PROPERTY});
            }
        } catch (Exception e) {
            Log.w("MmsLib", "SystemProperties.get failed " + e);
        }
        return null;
    }

    private String getSelfNumber() {
        int i = Build.VERSION.SDK_INT;
        SubscriptionInfo activeSubscriptionInfo = SubscriptionManager.from(this.mContext).getActiveSubscriptionInfo(SmsManager.getDefaultSmsSubscriptionId());
        if (activeSubscriptionInfo != null) {
            return activeSubscriptionInfo.getNumber();
        }
        return null;
    }

    private String getSimOrLocaleCountry() {
        int i = Build.VERSION.SDK_INT;
        SubscriptionInfo activeSubscriptionInfo = SubscriptionManager.from(this.mContext).getActiveSubscriptionInfo(SmsManager.getDefaultSmsSubscriptionId());
        String countryIso = activeSubscriptionInfo != null ? activeSubscriptionInfo.getCountryIso() : null;
        if (!TextUtils.isEmpty(countryIso)) {
            return countryIso.toUpperCase();
        }
        return Locale.getDefault().getCountry();
    }

    private static int getSlotId(int i) {
        try {
            Method method = SubscriptionManager.class.getMethod("getSlotIndex", new Class[]{Integer.TYPE});
            if (method == null) {
                return -1;
            }
            return ((Integer) method.invoke((Object) null, new Object[]{Integer.valueOf(i)})).intValue();
        } catch (Exception e) {
            Log.w("MmsLib", "SubscriptionManager.getSlotIndex failed " + e);
            return -1;
        }
    }

    private static void logHttpHeaders(Map map) {
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                List<String> list = (List) entry.getValue();
                if (list != null) {
                    for (String append : list) {
                        sb.append(str);
                        sb.append('=');
                        sb.append(append);
                        sb.append(10);
                    }
                }
            }
            StringBuilder Pa = C0632a.m1011Pa("HTTP: headers\n");
            Pa.append(sb.toString());
            Log.v("MmsLib", Pa.toString());
        }
    }

    private String resolveMacro(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = MACRO_P.matcher(str);
        int i = 0;
        StringBuilder sb = null;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            int start = matcher.start();
            if (start > i) {
                sb.append(str.substring(i, start));
            }
            String httpParamMacro = getHttpParamMacro(matcher.group(1), bundle);
            if (httpParamMacro != null) {
                sb.append(httpParamMacro);
            }
            i = matcher.end();
        }
        if (sb != null && i < str.length()) {
            sb.append(str.substring(i));
        }
        return sb == null ? str : sb.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r9v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r9v8 */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01d6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01d7, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01d9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01da, code lost:
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01e6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01e7, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0219, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x021a, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x024c, code lost:
        r0 = th;
        r9 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x024d, code lost:
        if (r9 != 0) goto L_0x024f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x024f, code lost:
        r9.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0252, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01d6 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01d9 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:9:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x024f  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] execute(java.lang.String r17, byte[] r18, java.lang.String r19, boolean r20, java.lang.String r21, int r22, android.os.Bundle r23, java.lang.String r24, java.lang.String r25) {
        /*
            r16 = this;
            r0 = r18
            r1 = r19
            r2 = r21
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            java.lang.String r7 = "GET"
            java.lang.String r8 = "POST"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "HTTP: "
            r9.append(r10)
            r9.append(r1)
            java.lang.String r11 = " "
            r9.append(r11)
            java.lang.String r12 = androidx.appcompat.mms.Utils.redactUrlForNonVerbose(r17)
            r9.append(r12)
            if (r20 == 0) goto L_0x0047
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = ", proxy="
            r12.append(r13)
            r12.append(r2)
            java.lang.String r13 = ":"
            r12.append(r13)
            r12.append(r3)
            java.lang.String r12 = r12.toString()
            goto L_0x0049
        L_0x0047:
            java.lang.String r12 = ""
        L_0x0049:
            r9.append(r12)
            java.lang.String r12 = ", PDU size="
            r9.append(r12)
            r12 = 0
            if (r0 == 0) goto L_0x0056
            int r13 = r0.length
            goto L_0x0057
        L_0x0056:
            r13 = r12
        L_0x0057:
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            java.lang.String r13 = "MmsLib"
            android.util.Log.d(r13, r9)
            checkMethod(r19)
            java.net.Proxy r14 = java.net.Proxy.NO_PROXY     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
            if (r20 == 0) goto L_0x0076
            java.net.Proxy r14 = new java.net.Proxy     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
            java.net.Proxy$Type r15 = java.net.Proxy.Type.HTTP     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
            java.net.InetSocketAddress r9 = new java.net.InetSocketAddress     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
            r9.<init>(r2, r3)     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
            r14.<init>(r15, r9)     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
        L_0x0076:
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0219, ProtocolException -> 0x01e6, IOException -> 0x01d9, all -> 0x01d6 }
            r3 = r17
            r2.<init>(r3)     // Catch:{ MalformedURLException -> 0x01d4, ProtocolException -> 0x01d2, IOException -> 0x01d9, all -> 0x01d6 }
            java.net.URLConnection r2 = r2.openConnection(r14)     // Catch:{ MalformedURLException -> 0x01d4, ProtocolException -> 0x01d2, IOException -> 0x01d9, all -> 0x01d6 }
            r9 = r2
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ MalformedURLException -> 0x01d4, ProtocolException -> 0x01d2, IOException -> 0x01d9, all -> 0x01d6 }
            r2 = 1
            r9.setDoInput(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r14 = "httpSocketTimeout"
            r15 = 60000(0xea60, float:8.4078E-41)
            int r14 = r4.getInt(r14, r15)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r9.setConnectTimeout(r14)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r14 = "Accept"
            java.lang.String r15 = "*/*, application/vnd.wap.mms-message, application/vnd.wap.sic"
            r9.setRequestProperty(r14, r15)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r14 = "Accept-Language"
            java.util.Locale r15 = java.util.Locale.getDefault()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r15 = getCurrentAcceptLanguage(r15)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r9.setRequestProperty(r14, r15)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r14.<init>()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r15 = "HTTP: User-Agent="
            r14.append(r15)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r14.append(r5)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r14 = r14.toString()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            android.util.Log.i(r13, r14)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r14 = "User-Agent"
            r9.setRequestProperty(r14, r5)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r5 = "uaProfTagName"
            java.lang.String r14 = "x-wap-profile"
            java.lang.String r5 = r4.getString(r5, r14)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r6 == 0) goto L_0x00e2
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r14.<init>()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r15 = "HTTP: UaProfUrl="
            r14.append(r15)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r14.append(r6)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r14 = r14.toString()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            android.util.Log.i(r13, r14)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r9.setRequestProperty(r5, r6)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x00e2:
            r5 = r16
            r5.addExtraHeaders(r9, r4)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            boolean r5 = r8.equals(r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r6 = 2
            if (r5 == 0) goto L_0x013f
            if (r0 == 0) goto L_0x0132
            int r1 = r0.length     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r1 < r2) goto L_0x0132
            r9.setDoOutput(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r9.setRequestMethod(r8)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r1 = "supportHttpCharsetHeader"
            boolean r1 = r4.getBoolean(r1, r12)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r2 = "Content-Type"
            if (r1 == 0) goto L_0x0109
            java.lang.String r1 = "application/vnd.wap.mms-message; charset=utf-8"
            r9.setRequestProperty(r2, r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            goto L_0x010e
        L_0x0109:
            java.lang.String r1 = "application/vnd.wap.mms-message"
            r9.setRequestProperty(r2, r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x010e:
            boolean r1 = android.util.Log.isLoggable(r13, r6)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r1 == 0) goto L_0x011b
            java.util.Map r1 = r9.getRequestProperties()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            logHttpHeaders(r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x011b:
            int r1 = r0.length     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r9.setFixedLengthStreamingMode(r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.io.OutputStream r2 = r9.getOutputStream()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r1.<init>(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r1.write(r0)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r1.flush()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r1.close()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            goto L_0x0155
        L_0x0132:
            java.lang.String r0 = "HTTP: empty pdu"
            android.util.Log.e(r13, r0)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            androidx.appcompat.mms.MmsHttpException r0 = new androidx.appcompat.mms.MmsHttpException     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r1 = "Sending empty PDU"
            r0.<init>((int) r12, (java.lang.String) r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            throw r0     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x013f:
            boolean r0 = r7.equals(r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r0 == 0) goto L_0x0155
            boolean r0 = android.util.Log.isLoggable(r13, r6)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r0 == 0) goto L_0x0152
            java.util.Map r0 = r9.getRequestProperties()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            logHttpHeaders(r0)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x0152:
            r9.setRequestMethod(r7)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x0155:
            int r0 = r9.getResponseCode()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r1 = r9.getResponseMessage()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2.<init>()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2.append(r10)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2.append(r0)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2.append(r11)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2.append(r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r2 = r2.toString()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            android.util.Log.d(r13, r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            boolean r2 = android.util.Log.isLoggable(r13, r6)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r2 == 0) goto L_0x0182
            java.util.Map r2 = r9.getHeaderFields()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            logHttpHeaders(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x0182:
            int r2 = r0 / 100
            if (r2 != r6) goto L_0x01c6
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.io.InputStream r1 = r9.getInputStream()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r0.<init>(r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r1.<init>()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r2]     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x0198:
            int r4 = r0.read(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r4 <= 0) goto L_0x01a2
            r1.write(r2, r12, r4)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            goto L_0x0198
        L_0x01a2:
            r0.close()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            byte[] r0 = r1.toByteArray()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r1.<init>()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r2 = "HTTP: response size="
            r1.append(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            if (r0 == 0) goto L_0x01b7
            int r2 = r0.length     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            goto L_0x01b8
        L_0x01b7:
            r2 = r12
        L_0x01b8:
            r1.append(r2)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            java.lang.String r1 = r1.toString()     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            android.util.Log.d(r13, r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r9.disconnect()
            return r0
        L_0x01c6:
            androidx.appcompat.mms.MmsHttpException r2 = new androidx.appcompat.mms.MmsHttpException     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            r2.<init>((int) r0, (java.lang.String) r1)     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
            throw r2     // Catch:{ MalformedURLException -> 0x01d0, ProtocolException -> 0x01ce, IOException -> 0x01cc }
        L_0x01cc:
            r0 = move-exception
            goto L_0x01db
        L_0x01ce:
            r0 = move-exception
            goto L_0x01ea
        L_0x01d0:
            r0 = move-exception
            goto L_0x021d
        L_0x01d2:
            r0 = move-exception
            goto L_0x01e9
        L_0x01d4:
            r0 = move-exception
            goto L_0x021c
        L_0x01d6:
            r0 = move-exception
            r9 = 0
            goto L_0x024d
        L_0x01d9:
            r0 = move-exception
            r9 = 0
        L_0x01db:
            java.lang.String r1 = "HTTP: IO failure"
            android.util.Log.e(r13, r1, r0)     // Catch:{ all -> 0x024c }
            androidx.appcompat.mms.MmsHttpException r1 = new androidx.appcompat.mms.MmsHttpException     // Catch:{ all -> 0x024c }
            r1.<init>((int) r12, (java.lang.Throwable) r0)     // Catch:{ all -> 0x024c }
            throw r1     // Catch:{ all -> 0x024c }
        L_0x01e6:
            r0 = move-exception
            r3 = r17
        L_0x01e9:
            r9 = 0
        L_0x01ea:
            java.lang.String r1 = androidx.appcompat.mms.Utils.redactUrlForNonVerbose(r17)     // Catch:{ all -> 0x024c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x024c }
            r2.<init>()     // Catch:{ all -> 0x024c }
            java.lang.String r3 = "HTTP: invalid URL protocol "
            r2.append(r3)     // Catch:{ all -> 0x024c }
            r2.append(r1)     // Catch:{ all -> 0x024c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x024c }
            android.util.Log.e(r13, r2, r0)     // Catch:{ all -> 0x024c }
            androidx.appcompat.mms.MmsHttpException r2 = new androidx.appcompat.mms.MmsHttpException     // Catch:{ all -> 0x024c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x024c }
            r3.<init>()     // Catch:{ all -> 0x024c }
            java.lang.String r4 = "Invalid URL protocol "
            r3.append(r4)     // Catch:{ all -> 0x024c }
            r3.append(r1)     // Catch:{ all -> 0x024c }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x024c }
            r2.<init>(r12, r1, r0)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x0219:
            r0 = move-exception
            r3 = r17
        L_0x021c:
            r9 = 0
        L_0x021d:
            java.lang.String r1 = androidx.appcompat.mms.Utils.redactUrlForNonVerbose(r17)     // Catch:{ all -> 0x024c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x024c }
            r2.<init>()     // Catch:{ all -> 0x024c }
            java.lang.String r3 = "HTTP: invalid URL "
            r2.append(r3)     // Catch:{ all -> 0x024c }
            r2.append(r1)     // Catch:{ all -> 0x024c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x024c }
            android.util.Log.e(r13, r2, r0)     // Catch:{ all -> 0x024c }
            androidx.appcompat.mms.MmsHttpException r2 = new androidx.appcompat.mms.MmsHttpException     // Catch:{ all -> 0x024c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x024c }
            r3.<init>()     // Catch:{ all -> 0x024c }
            java.lang.String r4 = "Invalid URL "
            r3.append(r4)     // Catch:{ all -> 0x024c }
            r3.append(r1)     // Catch:{ all -> 0x024c }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x024c }
            r2.<init>(r12, r1, r0)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x024c:
            r0 = move-exception
        L_0x024d:
            if (r9 == 0) goto L_0x0252
            r9.disconnect()
        L_0x0252:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.MmsHttpClient.execute(java.lang.String, byte[], java.lang.String, boolean, java.lang.String, int, android.os.Bundle, java.lang.String, java.lang.String):byte[]");
    }
}
