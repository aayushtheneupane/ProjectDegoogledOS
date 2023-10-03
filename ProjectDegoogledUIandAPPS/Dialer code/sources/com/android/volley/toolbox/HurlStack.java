package com.android.volley.toolbox;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.Request;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class HurlStack extends BaseHttpStack {
    private final SSLSocketFactory mSslSocketFactory = null;

    static class UrlConnectionInputStream extends FilterInputStream {
        private final HttpURLConnection mConnection;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        UrlConnectionInputStream(java.net.HttpURLConnection r2) {
            /*
                r1 = this;
                java.io.InputStream r0 = r2.getInputStream()     // Catch:{ IOException -> 0x0005 }
                goto L_0x0009
            L_0x0005:
                java.io.InputStream r0 = r2.getErrorStream()
            L_0x0009:
                r1.<init>(r0)
                r1.mConnection = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.HurlStack.UrlConnectionInputStream.<init>(java.net.HttpURLConnection):void");
        }

        public void close() throws IOException {
            super.close();
            this.mConnection.disconnect();
        }
    }

    private static void addBody(HttpURLConnection httpURLConnection, Request<?> request, byte[] bArr) throws IOException {
        httpURLConnection.setDoOutput(true);
        if (!httpURLConnection.getRequestProperties().containsKey("Content-Type")) {
            httpURLConnection.setRequestProperty("Content-Type", request.getBodyContentType());
        }
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }

    static List<Header> convertHeaders(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            if (next.getKey() != null) {
                for (String header : (List) next.getValue()) {
                    arrayList.add(new Header((String) next.getKey(), header));
                }
            }
        }
        return arrayList;
    }

    private HttpURLConnection openConnection(URL url, Request<?> request) throws IOException {
        SSLSocketFactory sSLSocketFactory;
        HttpURLConnection createConnection = createConnection(url);
        int timeoutMs = request.getTimeoutMs();
        createConnection.setConnectTimeout(timeoutMs);
        createConnection.setReadTimeout(timeoutMs);
        createConnection.setUseCaches(false);
        createConnection.setDoInput(true);
        if ("https".equals(url.getProtocol()) && (sSLSocketFactory = this.mSslSocketFactory) != null) {
            ((HttpsURLConnection) createConnection).setSSLSocketFactory(sSLSocketFactory);
        }
        return createConnection;
    }

    static void setConnectionParametersForRequest(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setRequestMethod("POST");
                    addBody(httpURLConnection, request, postBody);
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                httpURLConnection.setRequestMethod("POST");
                byte[] body = request.getBody();
                if (body != null) {
                    addBody(httpURLConnection, request, body);
                    return;
                }
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                byte[] body2 = request.getBody();
                if (body2 != null) {
                    addBody(httpURLConnection, request, body2);
                    return;
                }
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                byte[] body3 = request.getBody();
                if (body3 != null) {
                    addBody(httpURLConnection, request, body3);
                    return;
                }
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.toolbox.HttpResponse executeRequest(com.android.volley.Request<?> r6, java.util.Map<java.lang.String, java.lang.String> r7) throws java.io.IOException, com.android.volley.AuthFailureError {
        /*
            r5 = this;
            java.lang.String r0 = r6.getUrl()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.putAll(r7)
            java.util.Map r7 = java.util.Collections.emptyMap()
            r1.putAll(r7)
            java.net.URL r7 = new java.net.URL
            r7.<init>(r0)
            java.net.HttpURLConnection r5 = r5.openConnection(r7, r6)
            r7 = 1
            r0 = 0
            java.util.Set r2 = r1.keySet()     // Catch:{ all -> 0x0094 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0094 }
        L_0x0026:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0094 }
            if (r3 == 0) goto L_0x003c
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0094 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0094 }
            java.lang.Object r4 = r1.get(r3)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0094 }
            r5.setRequestProperty(r3, r4)     // Catch:{ all -> 0x0094 }
            goto L_0x0026
        L_0x003c:
            setConnectionParametersForRequest(r5, r6)     // Catch:{ all -> 0x0094 }
            int r1 = r5.getResponseCode()     // Catch:{ all -> 0x0094 }
            r2 = -1
            if (r1 == r2) goto L_0x008c
            int r6 = r6.getMethod()     // Catch:{ all -> 0x0094 }
            r2 = 4
            if (r6 == r2) goto L_0x005f
            r6 = 100
            if (r6 > r1) goto L_0x0055
            r6 = 200(0xc8, float:2.8E-43)
            if (r1 < r6) goto L_0x005f
        L_0x0055:
            r6 = 204(0xcc, float:2.86E-43)
            if (r1 == r6) goto L_0x005f
            r6 = 304(0x130, float:4.26E-43)
            if (r1 == r6) goto L_0x005f
            r6 = r7
            goto L_0x0060
        L_0x005f:
            r6 = r0
        L_0x0060:
            if (r6 != 0) goto L_0x0073
            com.android.volley.toolbox.HttpResponse r6 = new com.android.volley.toolbox.HttpResponse     // Catch:{ all -> 0x0094 }
            java.util.Map r7 = r5.getHeaderFields()     // Catch:{ all -> 0x0094 }
            java.util.List r7 = convertHeaders(r7)     // Catch:{ all -> 0x0094 }
            r6.<init>(r1, r7)     // Catch:{ all -> 0x0094 }
            r5.disconnect()
            return r6
        L_0x0073:
            com.android.volley.toolbox.HttpResponse r6 = new com.android.volley.toolbox.HttpResponse     // Catch:{ all -> 0x008a }
            java.util.Map r0 = r5.getHeaderFields()     // Catch:{ all -> 0x008a }
            java.util.List r0 = convertHeaders(r0)     // Catch:{ all -> 0x008a }
            int r2 = r5.getContentLength()     // Catch:{ all -> 0x008a }
            com.android.volley.toolbox.HurlStack$UrlConnectionInputStream r3 = new com.android.volley.toolbox.HurlStack$UrlConnectionInputStream     // Catch:{ all -> 0x008a }
            r3.<init>(r5)     // Catch:{ all -> 0x008a }
            r6.<init>(r1, r0, r2, r3)     // Catch:{ all -> 0x008a }
            return r6
        L_0x008a:
            r6 = move-exception
            goto L_0x0096
        L_0x008c:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x0094 }
            java.lang.String r7 = "Could not retrieve response code from HttpUrlConnection."
            r6.<init>(r7)     // Catch:{ all -> 0x0094 }
            throw r6     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r6 = move-exception
            r7 = r0
        L_0x0096:
            if (r7 != 0) goto L_0x009b
            r5.disconnect()
        L_0x009b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.HurlStack.executeRequest(com.android.volley.Request, java.util.Map):com.android.volley.toolbox.HttpResponse");
    }
}
