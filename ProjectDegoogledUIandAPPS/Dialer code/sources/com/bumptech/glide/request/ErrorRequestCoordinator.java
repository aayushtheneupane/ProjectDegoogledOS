package com.bumptech.glide.request;

public final class ErrorRequestCoordinator implements RequestCoordinator, Request {
    private Request error;
    private final RequestCoordinator parent;
    private Request primary;

    public ErrorRequestCoordinator(RequestCoordinator requestCoordinator) {
        this.parent = requestCoordinator;
    }

    private boolean isValidRequest(Request request) {
        return request.equals(this.primary) || (this.primary.isFailed() && request.equals(this.error));
    }

    public void begin() {
        if (!this.primary.isRunning()) {
            this.primary.begin();
        }
    }

    public boolean canNotifyCleared(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (!(requestCoordinator == null || requestCoordinator.canNotifyCleared(this)) || !isValidRequest(request)) {
            return false;
        }
        return true;
    }

    public boolean canNotifyStatusChanged(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (!(requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this)) || !isValidRequest(request)) {
            return false;
        }
        return true;
    }

    public boolean canSetImage(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (!(requestCoordinator == null || requestCoordinator.canSetImage(this)) || !isValidRequest(request)) {
            return false;
        }
        return true;
    }

    public void clear() {
        this.primary.clear();
        if (this.error.isRunning()) {
            this.error.clear();
        }
    }

    public boolean isAnyResourceSet() {
        RequestCoordinator requestCoordinator = this.parent;
        if (requestCoordinator != null && requestCoordinator.isAnyResourceSet()) {
            return true;
        }
        if ((this.primary.isFailed() ? this.error : this.primary).isResourceSet()) {
            return true;
        }
        return false;
    }

    public boolean isCancelled() {
        return (this.primary.isFailed() ? this.error : this.primary).isCancelled();
    }

    public boolean isComplete() {
        return (this.primary.isFailed() ? this.error : this.primary).isComplete();
    }

    public boolean isEquivalentTo(Request request) {
        if (!(request instanceof ErrorRequestCoordinator)) {
            return false;
        }
        ErrorRequestCoordinator errorRequestCoordinator = (ErrorRequestCoordinator) request;
        if (!this.primary.isEquivalentTo(errorRequestCoordinator.primary) || !this.error.isEquivalentTo(errorRequestCoordinator.error)) {
            return false;
        }
        return true;
    }

    public boolean isFailed() {
        return this.primary.isFailed() && this.error.isFailed();
    }

    public boolean isResourceSet() {
        return (this.primary.isFailed() ? this.error : this.primary).isResourceSet();
    }

    public boolean isRunning() {
        return (this.primary.isFailed() ? this.error : this.primary).isRunning();
    }

    public void onRequestFailed(Request request) {
        if (request.equals(this.error)) {
            RequestCoordinator requestCoordinator = this.parent;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestFailed(this);
            }
        } else if (!this.error.isRunning()) {
            this.error.begin();
        }
    }

    public void onRequestSuccess(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestSuccess(this);
        }
    }

    public void pause() {
        if (!this.primary.isFailed()) {
            this.primary.pause();
        }
        if (this.error.isRunning()) {
            this.error.pause();
        }
    }

    public void recycle() {
        this.primary.recycle();
        this.error.recycle();
    }

    public void setRequests(Request request, Request request2) {
        this.primary = request;
        this.error = request2;
    }
}
