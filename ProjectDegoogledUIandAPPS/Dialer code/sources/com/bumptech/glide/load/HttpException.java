package com.bumptech.glide.load;

import java.io.IOException;

public final class HttpException extends IOException {
    private static final long serialVersionUID = 1;
    private final int statusCode;

    public HttpException(String str) {
        super(str, (Throwable) null);
        this.statusCode = -1;
    }

    public HttpException(String str, int i) {
        super(str, (Throwable) null);
        this.statusCode = i;
    }
}
