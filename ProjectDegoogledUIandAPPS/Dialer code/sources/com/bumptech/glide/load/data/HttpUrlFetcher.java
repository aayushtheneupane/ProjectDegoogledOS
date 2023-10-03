package com.bumptech.glide.load.data;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class HttpUrlFetcher implements DataFetcher<InputStream> {
    static final HttpUrlConnectionFactory DEFAULT_CONNECTION_FACTORY = new DefaultHttpUrlConnectionFactory();
    private final HttpUrlConnectionFactory connectionFactory;
    private final GlideUrl glideUrl;
    private volatile boolean isCancelled;
    private InputStream stream;
    private final int timeout;
    private HttpURLConnection urlConnection;

    private static class DefaultHttpUrlConnectionFactory implements HttpUrlConnectionFactory {
        DefaultHttpUrlConnectionFactory() {
        }

        public HttpURLConnection build(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

    interface HttpUrlConnectionFactory {
    }

    public HttpUrlFetcher(GlideUrl glideUrl2, int i) {
        HttpUrlConnectionFactory httpUrlConnectionFactory = DEFAULT_CONNECTION_FACTORY;
        this.glideUrl = glideUrl2;
        this.timeout = i;
        this.connectionFactory = httpUrlConnectionFactory;
    }

    private InputStream loadDataWithRedirects(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        if (i < 5) {
            if (url2 != null) {
                try {
                    if (url.toURI().equals(url2.toURI())) {
                        throw new HttpException("In re-direct loop");
                    }
                } catch (URISyntaxException unused) {
                }
            }
            this.urlConnection = ((DefaultHttpUrlConnectionFactory) this.connectionFactory).build(url);
            for (Map.Entry next : map.entrySet()) {
                this.urlConnection.addRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            this.urlConnection.setConnectTimeout(this.timeout);
            this.urlConnection.setReadTimeout(this.timeout);
            boolean z = false;
            this.urlConnection.setUseCaches(false);
            this.urlConnection.setDoInput(true);
            this.urlConnection.setInstanceFollowRedirects(false);
            this.urlConnection.connect();
            this.stream = this.urlConnection.getInputStream();
            if (this.isCancelled) {
                return null;
            }
            int responseCode = this.urlConnection.getResponseCode();
            int i2 = responseCode / 100;
            if (i2 == 2) {
                HttpURLConnection httpURLConnection = this.urlConnection;
                if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
                    this.stream = ContentLengthInputStream.obtain(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength());
                } else {
                    if (Log.isLoggable("HttpUrlFetcher", 3)) {
                        String valueOf = String.valueOf(httpURLConnection.getContentEncoding());
                        Log.d("HttpUrlFetcher", valueOf.length() != 0 ? "Got non empty content encoding: ".concat(valueOf) : new String("Got non empty content encoding: "));
                    }
                    this.stream = httpURLConnection.getInputStream();
                }
                return this.stream;
            }
            if (i2 == 3) {
                z = true;
            }
            if (z) {
                String headerField = this.urlConnection.getHeaderField("Location");
                if (!TextUtils.isEmpty(headerField)) {
                    URL url3 = new URL(url, headerField);
                    cleanup();
                    return loadDataWithRedirects(url3, i + 1, url, map);
                }
                throw new HttpException("Received empty or null redirect url");
            } else if (responseCode == -1) {
                StringBuilder sb = new StringBuilder(49);
                sb.append("Http request failed with status code: ");
                sb.append(responseCode);
                throw new HttpException(sb.toString(), responseCode);
            } else {
                throw new HttpException(this.urlConnection.getResponseMessage(), responseCode);
            }
        } else {
            throw new HttpException("Too many (> 5) redirects!");
        }
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public void cleanup() {
        InputStream inputStream = this.stream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.urlConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.urlConnection = null;
    }

    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }

    public void loadData(Priority priority, DataFetcher.DataCallback<? super InputStream> dataCallback) {
        StringBuilder sb;
        double d;
        long logTime = LogTime.getLogTime();
        try {
            dataCallback.onDataReady(loadDataWithRedirects(this.glideUrl.toURL(), 0, (URL) null, this.glideUrl.getHeaders()));
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                d = LogTime.getElapsedMillis(logTime);
                sb = new StringBuilder(59);
                sb.append("Finished http url fetcher fetch in ");
                sb.append(d);
                Log.v("HttpUrlFetcher", sb.toString());
            }
        } catch (IOException e) {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                Log.d("HttpUrlFetcher", "Failed to load data for url", e);
            }
            dataCallback.onLoadFailed(e);
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                d = LogTime.getElapsedMillis(logTime);
                sb = new StringBuilder(59);
            }
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                double elapsedMillis = LogTime.getElapsedMillis(logTime);
                StringBuilder sb2 = new StringBuilder(59);
                sb2.append("Finished http url fetcher fetch in ");
                sb2.append(elapsedMillis);
                Log.v("HttpUrlFetcher", sb2.toString());
            }
            throw th;
        }
    }

    HttpUrlFetcher(GlideUrl glideUrl2, int i, HttpUrlConnectionFactory httpUrlConnectionFactory) {
        this.glideUrl = glideUrl2;
        this.timeout = i;
        this.connectionFactory = httpUrlConnectionFactory;
    }
}
