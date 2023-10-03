package com.android.dialer.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestOptions;

public class GlideRequests extends RequestManager {
    public GlideRequests(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }

    public RequestBuilder asBitmap() {
        return (GlideRequest) super.asBitmap();
    }

    public RequestBuilder asDrawable() {
        return (GlideRequest) mo6241as(Drawable.class);
    }

    public GlideRequest<Drawable> load(Uri uri) {
        return (GlideRequest) asDrawable().load(uri);
    }

    /* access modifiers changed from: protected */
    public void setRequestOptions(RequestOptions requestOptions) {
        if (requestOptions instanceof GlideOptions) {
            super.setRequestOptions(requestOptions);
        } else {
            super.setRequestOptions(new GlideOptions().apply(requestOptions));
        }
    }

    /* renamed from: as */
    public <ResourceType> GlideRequest<ResourceType> m19as(Class<ResourceType> cls) {
        return new GlideRequest<>(this.glide, this, cls, this.context);
    }

    /* renamed from: load  reason: collision with other method in class */
    public RequestBuilder m115load(Uri uri) {
        return (GlideRequest) asDrawable().load(uri);
    }

    public GlideRequest<Drawable> load(Integer num) {
        return (GlideRequest) asDrawable().load(num);
    }
}
