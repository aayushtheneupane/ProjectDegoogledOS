package com.android.dialer.blocking;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.R$dimen;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorFactory;

@Deprecated
public class BlockedNumbersAutoMigrator {
    /* access modifiers changed from: private */
    public final Context appContext;
    private final DialerExecutorFactory dialerExecutorFactory;
    private final FilteredNumberAsyncQueryHandler queryHandler;

    private static class ShouldAttemptAutoMigrate implements DialerExecutor.Worker<Void, Boolean> {
        private final Context appContext;

        ShouldAttemptAutoMigrate(Context context) {
            this.appContext = context;
        }

        public Object doInBackground(Object obj) throws Throwable {
            Void voidR = (Void) obj;
            if (!R$dimen.isUserUnlocked(this.appContext)) {
                LogUtil.m9i("BlockedNumbersAutoMigrator", "not attempting auto-migrate: device is locked", new Object[0]);
                return false;
            }
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.appContext);
            if (defaultSharedPreferences.contains("checkedAutoMigrate")) {
                return false;
            }
            if (!FilteredNumberCompat.canAttemptBlockOperations(this.appContext)) {
                LogUtil.m9i("BlockedNumbersAutoMigrator", "not attempting auto-migrate: current user can't block", new Object[0]);
                return false;
            }
            LogUtil.m9i("BlockedNumbersAutoMigrator", "updating state as already checked for auto-migrate.", new Object[0]);
            defaultSharedPreferences.edit().putBoolean("checkedAutoMigrate", true).apply();
            if (!FilteredNumberCompat.hasMigratedToNewBlocking(this.appContext)) {
                return true;
            }
            LogUtil.m9i("BlockedNumbersAutoMigrator", "not attempting auto-migrate: already migrated.", new Object[0]);
            return false;
        }
    }

    public BlockedNumbersAutoMigrator(Context context, FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler, DialerExecutorFactory dialerExecutorFactory2) {
        Assert.isNotNull(context);
        this.appContext = context;
        Assert.isNotNull(filteredNumberAsyncQueryHandler);
        this.queryHandler = filteredNumberAsyncQueryHandler;
        Assert.isNotNull(dialerExecutorFactory2);
        this.dialerExecutorFactory = dialerExecutorFactory2;
    }

    /* access modifiers changed from: private */
    public void autoMigrate(boolean z) {
        if (z) {
            LogUtil.m9i("BlockedNumbersAutoMigrator", "attempting to auto-migrate.", new Object[0]);
            this.queryHandler.hasBlockedNumbers(new FilteredNumberAsyncQueryHandler.OnHasBlockedNumbersListener() {
                public void onHasBlockedNumbers(boolean z) {
                    if (z) {
                        LogUtil.m9i("BlockedNumbersAutoMigrator", "not auto-migrating: blocked numbers exist.", new Object[0]);
                        return;
                    }
                    LogUtil.m9i("BlockedNumbersAutoMigrator", "auto-migrating: no blocked numbers.", new Object[0]);
                    PreferenceManager.getDefaultSharedPreferences(BlockedNumbersAutoMigrator.this.appContext).edit().putBoolean(FilteredNumberCompat.HAS_MIGRATED_TO_NEW_BLOCKING_KEY, true).apply();
                }
            });
        }
    }

    public void asyncAutoMigrate() {
        ((DefaultDialerExecutorFactory) this.dialerExecutorFactory).createNonUiTaskBuilder(new ShouldAttemptAutoMigrate(this.appContext)).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                BlockedNumbersAutoMigrator.this.autoMigrate(((Boolean) obj).booleanValue());
            }
        }).build().executeParallel(null);
    }
}
