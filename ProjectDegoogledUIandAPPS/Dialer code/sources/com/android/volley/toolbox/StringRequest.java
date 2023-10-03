package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class StringRequest extends Request<String> {
    private Response.Listener<String> mListener;
    private final Object mLock = new Object();

    public StringRequest(int i, String str, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
    }

    public void cancel() {
        super.cancel();
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        String str;
        Cache.Entry entry;
        long j;
        long j2;
        boolean z;
        long j3;
        long j4;
        NetworkResponse networkResponse2 = networkResponse;
        try {
            str = new String(networkResponse2.data, HttpHeaderParser.parseCharset(networkResponse2.headers));
        } catch (UnsupportedEncodingException unused) {
            str = new String(networkResponse2.data);
        }
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse2.headers;
        String str2 = map.get("Date");
        long parseDateAsEpoch = str2 != null ? HttpHeaderParser.parseDateAsEpoch(str2) : 0;
        String str3 = map.get("Cache-Control");
        int i = 0;
        if (str3 != null) {
            String[] split = str3.split(",", 0);
            z = false;
            j2 = 0;
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    entry = null;
                    break;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused2) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    z = true;
                }
                i++;
            }
            i = 1;
        } else {
            z = false;
            j2 = 0;
            j = 0;
        }
        String str4 = map.get("Expires");
        long parseDateAsEpoch2 = str4 != null ? HttpHeaderParser.parseDateAsEpoch(str4) : 0;
        String str5 = map.get("Last-Modified");
        long parseDateAsEpoch3 = str5 != null ? HttpHeaderParser.parseDateAsEpoch(str5) : 0;
        String str6 = map.get("ETag");
        if (i != 0) {
            j4 = currentTimeMillis + (j2 * 1000);
            if (!z) {
                j3 = (j * 1000) + j4;
                Cache.Entry entry2 = new Cache.Entry();
                entry2.data = networkResponse2.data;
                entry2.etag = str6;
                entry2.softTtl = j4;
                entry2.ttl = j3;
                entry2.serverDate = parseDateAsEpoch;
                entry2.lastModified = parseDateAsEpoch3;
                entry2.responseHeaders = map;
                entry2.allResponseHeaders = networkResponse2.allHeaders;
                entry = entry2;
                return Response.success(str, entry);
            }
        } else {
            j4 = (parseDateAsEpoch <= 0 || parseDateAsEpoch2 < parseDateAsEpoch) ? 0 : currentTimeMillis + (parseDateAsEpoch2 - parseDateAsEpoch);
        }
        j3 = j4;
        Cache.Entry entry22 = new Cache.Entry();
        entry22.data = networkResponse2.data;
        entry22.etag = str6;
        entry22.softTtl = j4;
        entry22.ttl = j3;
        entry22.serverDate = parseDateAsEpoch;
        entry22.lastModified = parseDateAsEpoch3;
        entry22.responseHeaders = map;
        entry22.allResponseHeaders = networkResponse2.allHeaders;
        entry = entry22;
        return Response.success(str, entry);
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(String str) {
        Response.Listener<String> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            ((RequestFuture) listener).onResponse(str);
        }
    }
}
