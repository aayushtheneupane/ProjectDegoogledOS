package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import com.android.dialer.glide.DialerGlideModule;

final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
    private final DialerGlideModule appGlideModule = new DialerGlideModule();

    GeneratedAppGlideModuleImpl() {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.android.dialer.glide.DialerGlideModule");
        }
    }

    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        this.appGlideModule.applyOptions(context, glideBuilder);
    }

    public boolean isManifestParsingEnabled() {
        return this.appGlideModule.isManifestParsingEnabled();
    }

    public void registerComponents(Context context, Glide glide, Registry registry) {
        this.appGlideModule.registerComponents(context, glide, registry);
    }
}
