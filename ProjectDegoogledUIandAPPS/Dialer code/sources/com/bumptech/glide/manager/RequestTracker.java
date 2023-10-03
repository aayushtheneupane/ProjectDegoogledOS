package com.bumptech.glide.manager;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker {
    private boolean isPaused;
    private final List<Request> pendingRequests = new ArrayList();
    private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());

    private boolean clearRemoveAndMaybeRecycle(Request request, boolean z) {
        boolean z2 = true;
        if (request == null) {
            return true;
        }
        boolean remove = this.requests.remove(request);
        if (!this.pendingRequests.remove(request) && !remove) {
            z2 = false;
        }
        if (z2) {
            request.clear();
            if (z) {
                request.recycle();
            }
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public boolean clearRemoveAndRecycle(Request request) {
        return clearRemoveAndMaybeRecycle(request, true);
    }

    public void clearRequests() {
        for (T clearRemoveAndMaybeRecycle : Util.getSnapshot(this.requests)) {
            clearRemoveAndMaybeRecycle(clearRemoveAndMaybeRecycle, false);
        }
        this.pendingRequests.clear();
    }

    public void pauseRequests() {
        this.isPaused = true;
        for (T t : Util.getSnapshot(this.requests)) {
            if (t.isRunning()) {
                t.pause();
                this.pendingRequests.add(t);
            }
        }
    }

    public void restartRequests() {
        for (T t : Util.getSnapshot(this.requests)) {
            if (!t.isComplete() && !t.isCancelled()) {
                t.pause();
                if (!this.isPaused) {
                    t.begin();
                } else {
                    this.pendingRequests.add(t);
                }
            }
        }
    }

    public void resumeRequests() {
        this.isPaused = false;
        for (T t : Util.getSnapshot(this.requests)) {
            if (!t.isComplete() && !t.isCancelled() && !t.isRunning()) {
                t.begin();
            }
        }
        this.pendingRequests.clear();
    }

    public void runRequest(Request request) {
        this.requests.add(request);
        if (!this.isPaused) {
            request.begin();
        } else {
            this.pendingRequests.add(request);
        }
    }

    public String toString() {
        String obj = super.toString();
        int size = this.requests.size();
        boolean z = this.isPaused;
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(obj, 41));
        sb.append(obj);
        sb.append("{numRequests=");
        sb.append(size);
        sb.append(", isPaused=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
