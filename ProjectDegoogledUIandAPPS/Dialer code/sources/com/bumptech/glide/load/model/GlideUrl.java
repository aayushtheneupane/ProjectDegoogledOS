package com.bumptech.glide.load.model;

import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

public class GlideUrl implements Key {
    private volatile byte[] cacheKeyBytes;
    private int hashCode;
    private final Headers headers;
    private String safeStringUrl;
    private URL safeUrl;
    private final String stringUrl;
    private final URL url;

    public GlideUrl(URL url2) {
        Headers headers2 = Headers.DEFAULT;
        R$style.checkNotNull(url2, "Argument must not be null");
        this.url = url2;
        this.stringUrl = null;
        R$style.checkNotNull(headers2, "Argument must not be null");
        this.headers = headers2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GlideUrl)) {
            return false;
        }
        GlideUrl glideUrl = (GlideUrl) obj;
        if (!getCacheKey().equals(glideUrl.getCacheKey()) || !this.headers.equals(glideUrl.headers)) {
            return false;
        }
        return true;
    }

    public String getCacheKey() {
        String str = this.stringUrl;
        if (str != null) {
            return str;
        }
        URL url2 = this.url;
        R$style.checkNotNull(url2, "Argument must not be null");
        return url2.toString();
    }

    public Map<String, String> getHeaders() {
        return this.headers.getHeaders();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = getCacheKey().hashCode();
            this.hashCode = this.headers.hashCode() + (this.hashCode * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        return getCacheKey();
    }

    public URL toURL() throws MalformedURLException {
        if (this.safeUrl == null) {
            if (TextUtils.isEmpty(this.safeStringUrl)) {
                String str = this.stringUrl;
                if (TextUtils.isEmpty(str)) {
                    URL url2 = this.url;
                    R$style.checkNotNull(url2, "Argument must not be null");
                    str = url2.toString();
                }
                this.safeStringUrl = Uri.encode(str, "@#&=*+-_.,:!?()/~'%;$");
            }
            this.safeUrl = new URL(this.safeStringUrl);
        }
        return this.safeUrl;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        if (this.cacheKeyBytes == null) {
            this.cacheKeyBytes = getCacheKey().getBytes(Key.CHARSET);
        }
        messageDigest.update(this.cacheKeyBytes);
    }

    public GlideUrl(String str) {
        Headers headers2 = Headers.DEFAULT;
        this.url = null;
        R$style.checkNotEmpty(str);
        this.stringUrl = str;
        R$style.checkNotNull(headers2, "Argument must not be null");
        this.headers = headers2;
    }
}
