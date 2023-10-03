package com.android.dialer.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    GlideRequest(Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        super(glide, requestManager, cls, context);
    }

    public RequestBuilder apply(RequestOptions requestOptions) {
        super.apply(requestOptions);
        return this;
    }

    public GlideRequest<TranscodeType> circleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).circleCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).circleCrop();
        }
        return this;
    }

    public RequestBuilder clone() {
        return (GlideRequest) super.clone();
    }

    public GlideRequest<TranscodeType> fallback(Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fallback(drawable);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(drawable);
        }
        return this;
    }

    public RequestBuilder listener(RequestListener requestListener) {
        super.listener(requestListener);
        return this;
    }

    public RequestBuilder load(Object obj) {
        super.load(obj);
        return this;
    }

    public GlideRequest<TranscodeType> placeholder(Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).placeholder(drawable);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(drawable);
        }
        return this;
    }

    public RequestBuilder transition(TransitionOptions transitionOptions) {
        super.transition(transitionOptions);
        return this;
    }

    /* renamed from: clone  reason: collision with other method in class */
    public Object m114clone() throws CloneNotSupportedException {
        return (GlideRequest) super.clone();
    }

    public RequestBuilder load(Uri uri) {
        super.load(uri);
        return this;
    }

    public RequestBuilder load(Integer num) {
        return (GlideRequest) super.load(num);
    }
}
