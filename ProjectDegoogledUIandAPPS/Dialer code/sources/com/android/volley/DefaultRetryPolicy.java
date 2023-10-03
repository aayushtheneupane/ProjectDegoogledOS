package com.android.volley;

public class DefaultRetryPolicy {
    private final float mBackoffMultiplier = 1.0f;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs = 2500;
    private final int mMaxNumRetries = 1;

    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    public void retry(VolleyError volleyError) throws VolleyError {
        boolean z = true;
        this.mCurrentRetryCount++;
        int i = this.mCurrentTimeoutMs;
        this.mCurrentTimeoutMs = i + ((int) (((float) i) * this.mBackoffMultiplier));
        if (this.mCurrentRetryCount > this.mMaxNumRetries) {
            z = false;
        }
        if (!z) {
            throw volleyError;
        }
    }
}
