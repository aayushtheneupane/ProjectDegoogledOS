package com.android.dialer.shortcuts;

import android.content.Context;
import android.os.Build;
import com.android.contacts.common.list.ContactEntry;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import java.util.ArrayList;
import java.util.List;

public final class ShortcutRefresher {

    private static final class RefreshWorker implements DialerExecutor.Worker<List<ContactEntry>, Void> {
        private final Context context;

        RefreshWorker(Context context2) {
            this.context = context2;
        }

        public Object doInBackground(Object obj) throws Throwable {
            LogUtil.enterBlock("ShortcutRefresher.Task.doInBackground");
            Context context2 = this.context;
            new DynamicShortcuts(context2, new IconFactory(context2)).refresh((List) obj);
            return null;
        }
    }

    public static void refresh(Context context, List<ContactEntry> list) {
        Assert.isMainThread();
        Assert.isNotNull(context);
        int i = Build.VERSION.SDK_INT;
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("dynamic_shortcuts_enabled", true)) {
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new RefreshWorker(context)).build().executeSerial(new ArrayList(list));
        }
    }
}
