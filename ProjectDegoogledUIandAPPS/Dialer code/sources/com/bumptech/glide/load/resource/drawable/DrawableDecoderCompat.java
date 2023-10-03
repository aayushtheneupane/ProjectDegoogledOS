package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p002v7.content.res.AppCompatResources;

public final class DrawableDecoderCompat {
    private static volatile boolean shouldCallAppCompatResources = true;

    public static Drawable getDrawable(Context context, int i, Resources.Theme theme) {
        try {
            if (shouldCallAppCompatResources) {
                return AppCompatResources.getDrawable(context, i);
            }
        } catch (NoClassDefFoundError unused) {
            shouldCallAppCompatResources = false;
        } catch (Resources.NotFoundException unused2) {
        }
        if (theme == null) {
            theme = context.getTheme();
        }
        return FontResourcesParserCompat.getDrawable(context.getResources(), i, theme);
    }
}
