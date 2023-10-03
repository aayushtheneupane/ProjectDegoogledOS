package com.android.volley;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.android.volley.toolbox.BasicNetwork;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.mQueue = blockingQueue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    /* access modifiers changed from: package-private */
    public void processRequest(Request<?> request) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        request.sendEvent(3);
        try {
            request.addMarker("network-queue-take");
            if (request.isCanceled()) {
                request.finish("network-discard-cancelled");
                request.notifyListenerResponseNotUsable();
                request.sendEvent(4);
                return;
            }
            int i = Build.VERSION.SDK_INT;
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
            NetworkResponse performRequest = ((BasicNetwork) this.mNetwork).performRequest(request);
            request.addMarker("network-http-complete");
            if (!performRequest.notModified || !request.hasHadResponseDelivered()) {
                Response<?> parseNetworkResponse = request.parseNetworkResponse(performRequest);
                request.addMarker("network-parse-complete");
                if (request.shouldCache() && parseNetworkResponse.cacheEntry != null) {
                    this.mCache.put(request.getCacheKey(), parseNetworkResponse.cacheEntry);
                    request.addMarker("network-cache-written");
                }
                request.markDelivered();
                ((ExecutorDelivery) this.mDelivery).postResponse(request, parseNetworkResponse, (Runnable) null);
                request.notifyListenerResponseReceived(parseNetworkResponse);
                request.sendEvent(4);
                return;
            }
            request.finish("not-modified");
            request.notifyListenerResponseNotUsable();
            request.sendEvent(4);
        } catch (VolleyError e) {
            e.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
            ((ExecutorDelivery) this.mDelivery).postError(request, e);
            request.notifyListenerResponseNotUsable();
        } catch (Exception e2) {
            VolleyLog.m60e(e2, "Unhandled exception %s", e2.toString());
            VolleyError volleyError = new VolleyError((Throwable) e2);
            volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
            ((ExecutorDelivery) this.mDelivery).postError(request, volleyError);
            request.notifyListenerResponseNotUsable();
        } catch (Throwable th) {
            request.sendEvent(4);
            throw th;
        }
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                processRequest(this.mQueue.take());
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.m59e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }
}
