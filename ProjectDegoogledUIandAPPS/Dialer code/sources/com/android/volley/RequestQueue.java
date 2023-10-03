package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestQueue {
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue<Request<?>> mCacheQueue = new PriorityBlockingQueue<>();
    private final Set<Request<?>> mCurrentRequests = new HashSet();
    private final ResponseDelivery mDelivery;
    private final NetworkDispatcher[] mDispatchers;
    private final List<RequestEventListener> mEventListeners = new ArrayList();
    private final List<RequestFinishedListener> mFinishedListeners = new ArrayList();
    private final Network mNetwork;
    private final PriorityBlockingQueue<Request<?>> mNetworkQueue = new PriorityBlockingQueue<>();
    private final AtomicInteger mSequenceGenerator = new AtomicInteger();

    public interface RequestEventListener {
        void onRequestEvent(Request<?> request, int i);
    }

    @Deprecated
    public interface RequestFinishedListener<T> {
        void onRequestFinished(Request<T> request);
    }

    public RequestQueue(Cache cache, Network network) {
        ExecutorDelivery executorDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
        this.mCache = cache;
        this.mNetwork = network;
        this.mDispatchers = new NetworkDispatcher[4];
        this.mDelivery = executorDelivery;
    }

    public <T> Request<T> add(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.add(request);
        }
        request.setSequence(this.mSequenceGenerator.incrementAndGet());
        request.addMarker("add-to-queue");
        sendRequestEvent(request, 0);
        if (!request.shouldCache()) {
            this.mNetworkQueue.add(request);
            return request;
        }
        this.mCacheQueue.add(request);
        return request;
    }

    /* access modifiers changed from: package-private */
    public <T> void finish(Request<T> request) {
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.remove(request);
        }
        synchronized (this.mFinishedListeners) {
            for (RequestFinishedListener onRequestFinished : this.mFinishedListeners) {
                onRequestFinished.onRequestFinished(request);
            }
        }
        sendRequestEvent(request, 5);
    }

    /* access modifiers changed from: package-private */
    public void sendRequestEvent(Request<?> request, int i) {
        synchronized (this.mEventListeners) {
            for (RequestEventListener onRequestEvent : this.mEventListeners) {
                onRequestEvent.onRequestEvent(request, i);
            }
        }
    }

    public void start() {
        CacheDispatcher cacheDispatcher = this.mCacheDispatcher;
        if (cacheDispatcher != null) {
            cacheDispatcher.quit();
        }
        for (NetworkDispatcher networkDispatcher : this.mDispatchers) {
            if (networkDispatcher != null) {
                networkDispatcher.quit();
            }
        }
        this.mCacheDispatcher = new CacheDispatcher(this.mCacheQueue, this.mNetworkQueue, this.mCache, this.mDelivery);
        this.mCacheDispatcher.start();
        for (int i = 0; i < this.mDispatchers.length; i++) {
            NetworkDispatcher networkDispatcher2 = new NetworkDispatcher(this.mNetworkQueue, this.mNetwork, this.mCache, this.mDelivery);
            this.mDispatchers[i] = networkDispatcher2;
            networkDispatcher2.start();
        }
    }
}
