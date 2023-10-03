package com.android.dialer.preferredsim.suggestion;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.common.Assert;
import com.android.dialer.inject.HasRootComponent;

public abstract class SimSuggestionComponent {

    public interface HasComponent {
    }

    public static SimSuggestionComponent get(Context context) {
        Assert.isWorkerThread();
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).simSuggestionComponent();
    }

    public abstract SuggestionProvider getSuggestionProvider();
}
