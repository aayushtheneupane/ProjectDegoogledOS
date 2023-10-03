package com.android.systemui;

import android.app.Application;
import android.content.Context;
import androidx.core.app.CoreComponentFactory;
import com.android.systemui.SystemUIApplication;

public class SystemUIAppComponentFactory extends CoreComponentFactory {
    public ContextComponentHelper mComponentHelper;

    public Application instantiateApplication(ClassLoader classLoader, String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Application instantiateApplication = super.instantiateApplication(classLoader, str);
        if (instantiateApplication instanceof SystemUIApplication) {
            ((SystemUIApplication) instantiateApplication).setContextAvailableCallback(new SystemUIApplication.ContextAvailableCallback() {
                public final void onContextAvailable(Context context) {
                    SystemUIAppComponentFactory.this.lambda$instantiateApplication$0$SystemUIAppComponentFactory(context);
                }
            });
        }
        return instantiateApplication;
    }

    public /* synthetic */ void lambda$instantiateApplication$0$SystemUIAppComponentFactory(Context context) {
        SystemUIFactory.createFromConfig(context);
        SystemUIFactory.getInstance().getRootComponent().inject(this);
    }
}
