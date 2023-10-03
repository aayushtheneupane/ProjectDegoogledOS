package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.util.List;

public class ResourceDrawableDecoder implements ResourceDecoder<Uri, Drawable> {
    private final Context context;

    public ResourceDrawableDecoder(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public boolean handles(Object obj, Options options) throws IOException {
        return ((Uri) obj).getScheme().equals("android.resource");
    }

    public Resource<Drawable> decode(Uri uri, int i, int i2, Options options) {
        Integer num;
        Context context2;
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            String authority = uri.getAuthority();
            num = Integer.valueOf(this.context.getResources().getIdentifier(pathSegments.get(1), pathSegments.get(0), authority));
        } else {
            if (pathSegments.size() == 1) {
                try {
                    num = Integer.valueOf(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                }
            }
            num = null;
        }
        if (num == null) {
            String valueOf = String.valueOf(uri);
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline4(valueOf.length() + 25, "Unrecognized Uri format: ", valueOf));
        } else if (num.intValue() != 0) {
            int intValue = num.intValue();
            String authority2 = uri.getAuthority();
            if (authority2.equals(this.context.getPackageName())) {
                context2 = this.context;
            } else {
                try {
                    context2 = this.context.createPackageContext(authority2, 0);
                } catch (PackageManager.NameNotFoundException e) {
                    String valueOf2 = String.valueOf(uri);
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline4(valueOf2.length() + 57, "Failed to obtain context or unrecognized Uri format for: ", valueOf2), e);
                }
            }
            return NonOwnedDrawableResource.newInstance(DrawableDecoderCompat.getDrawable(context2, intValue, (Resources.Theme) null));
        } else {
            String valueOf3 = String.valueOf(uri);
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline4(valueOf3.length() + 34, "Failed to obtain resource id for: ", valueOf3));
        }
    }
}
