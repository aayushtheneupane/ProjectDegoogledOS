package com.bumptech.glide.module;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;

public abstract class AppGlideModule implements AppliesOptions, RegistersComponents {
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
    }

    public boolean isManifestParsingEnabled() {
        return true;
    }

    public void registerComponents(Context context, Glide glide, Registry registry) {
    }
}
