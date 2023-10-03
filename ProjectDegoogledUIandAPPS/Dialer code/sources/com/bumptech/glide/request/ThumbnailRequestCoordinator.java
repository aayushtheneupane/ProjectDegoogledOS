package com.bumptech.glide.request;

public class ThumbnailRequestCoordinator implements RequestCoordinator, Request {
    private Request full;
    private boolean isRunning;
    private final RequestCoordinator parent;
    private Request thumb;

    ThumbnailRequestCoordinator() {
        this.parent = null;
    }

    public void begin() {
        this.isRunning = true;
        if (!this.full.isComplete() && !this.thumb.isRunning()) {
            this.thumb.begin();
        }
        if (this.isRunning && !this.full.isRunning()) {
            this.full.begin();
        }
    }

    public boolean canNotifyCleared(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (!(requestCoordinator == null || requestCoordinator.canNotifyCleared(this)) || !request.equals(this.full)) {
            return false;
        }
        return true;
    }

    public boolean canNotifyStatusChanged(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (!(requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this)) || !request.equals(this.full) || isAnyResourceSet()) {
            return false;
        }
        return true;
    }

    public boolean canSetImage(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (!(requestCoordinator == null || requestCoordinator.canSetImage(this))) {
            return false;
        }
        if (request.equals(this.full) || !this.full.isResourceSet()) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.isRunning = false;
        this.thumb.clear();
        this.full.clear();
    }

    public boolean isAnyResourceSet() {
        RequestCoordinator requestCoordinator = this.parent;
        if ((requestCoordinator != null && requestCoordinator.isAnyResourceSet()) || isResourceSet()) {
            return true;
        }
        return false;
    }

    public boolean isCancelled() {
        return this.full.isCancelled();
    }

    public boolean isComplete() {
        return this.full.isComplete() || this.thumb.isComplete();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEquivalentTo(com.bumptech.glide.request.Request r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.bumptech.glide.request.ThumbnailRequestCoordinator
            r1 = 0
            if (r0 == 0) goto L_0x002a
            com.bumptech.glide.request.ThumbnailRequestCoordinator r4 = (com.bumptech.glide.request.ThumbnailRequestCoordinator) r4
            com.bumptech.glide.request.Request r0 = r3.full
            if (r0 != 0) goto L_0x0010
            com.bumptech.glide.request.Request r0 = r4.full
            if (r0 != 0) goto L_0x002a
            goto L_0x0018
        L_0x0010:
            com.bumptech.glide.request.Request r2 = r4.full
            boolean r0 = r0.isEquivalentTo(r2)
            if (r0 == 0) goto L_0x002a
        L_0x0018:
            com.bumptech.glide.request.Request r3 = r3.thumb
            if (r3 != 0) goto L_0x0021
            com.bumptech.glide.request.Request r3 = r4.thumb
            if (r3 != 0) goto L_0x002a
            goto L_0x0029
        L_0x0021:
            com.bumptech.glide.request.Request r4 = r4.thumb
            boolean r3 = r3.isEquivalentTo(r4)
            if (r3 == 0) goto L_0x002a
        L_0x0029:
            r1 = 1
        L_0x002a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.ThumbnailRequestCoordinator.isEquivalentTo(com.bumptech.glide.request.Request):boolean");
    }

    public boolean isFailed() {
        return this.full.isFailed();
    }

    public boolean isResourceSet() {
        return this.full.isResourceSet() || this.thumb.isResourceSet();
    }

    public boolean isRunning() {
        return this.full.isRunning();
    }

    public void onRequestFailed(Request request) {
        RequestCoordinator requestCoordinator;
        if (request.equals(this.full) && (requestCoordinator = this.parent) != null) {
            requestCoordinator.onRequestFailed(this);
        }
    }

    public void onRequestSuccess(Request request) {
        if (!request.equals(this.thumb)) {
            RequestCoordinator requestCoordinator = this.parent;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestSuccess(this);
            }
            if (!this.thumb.isComplete()) {
                this.thumb.clear();
            }
        }
    }

    public void pause() {
        this.isRunning = false;
        this.full.pause();
        this.thumb.pause();
    }

    public void recycle() {
        this.full.recycle();
        this.thumb.recycle();
    }

    public void setRequests(Request request, Request request2) {
        this.full = request;
        this.thumb = request2;
    }

    public ThumbnailRequestCoordinator(RequestCoordinator requestCoordinator) {
        this.parent = requestCoordinator;
    }
}
