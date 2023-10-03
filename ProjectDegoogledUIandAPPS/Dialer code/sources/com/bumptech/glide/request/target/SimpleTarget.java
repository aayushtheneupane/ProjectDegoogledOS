package com.bumptech.glide.request.target;

import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.util.Util;

public abstract class SimpleTarget<Z> extends BaseTarget<Z> {
    private final int height = Integer.MIN_VALUE;
    private final int width = Integer.MIN_VALUE;

    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        if (Util.isValidDimensions(this.width, this.height)) {
            ((SingleRequest) sizeReadyCallback).onSizeReady(this.width, this.height);
            return;
        }
        int i = this.width;
        int i2 = this.height;
        StringBuilder sb = new StringBuilder(176);
        sb.append("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ");
        sb.append(i);
        sb.append(" and height: ");
        sb.append(i2);
        sb.append(", either provide dimensions in the constructor or call override()");
        throw new IllegalArgumentException(sb.toString());
    }

    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }
}
