package com.android.contacts.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.contacts.R;
import com.android.contacts.list.ContactListFilterController;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountTypeProvider;
import com.android.contacts.model.account.AccountTypeWithDataSet;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.FallbackAccountType;
import com.android.contacts.model.account.GoogleAccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.util.concurrent.ContactsExecutors;
import com.android.contactsbind.experiments.Flags;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: AccountTypeManager */
class AccountTypeManagerImpl extends AccountTypeManager implements OnAccountsUpdateListener, SyncStatusObserver {
    /* access modifiers changed from: private */
    public final AccountManager mAccountManager;
    private List<AccountWithDataSet> mAccountManagerAccounts = new ArrayList();
    private ListenableFuture<AccountTypeProvider> mAccountTypesFuture;
    private final Function<AccountTypeProvider, List<AccountWithDataSet>> mAccountsExtractor = new Function<AccountTypeProvider, List<AccountWithDataSet>>() {
        public List<AccountWithDataSet> apply(AccountTypeProvider accountTypeProvider) {
            AccountTypeManagerImpl accountTypeManagerImpl = AccountTypeManagerImpl.this;
            return accountTypeManagerImpl.getAccountsWithDataSets(accountTypeManagerImpl.mAccountManager.getAccounts(), accountTypeProvider);
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            AccountTypeManagerImpl.this.reloadAccountTypes();
        }
    };
    /* access modifiers changed from: private */
    public final Context mContext;
    private final ListeningExecutorService mExecutor;
    private final AccountType mFallbackAccountType;
    /* access modifiers changed from: private */
    public final DeviceLocalAccountLocator mLocalAccountLocator;
    private List<AccountWithDataSet> mLocalAccounts = new ArrayList();
    private ListenableFuture<List<AccountWithDataSet>> mLocalAccountsFuture;
    private final Executor mMainThreadExecutor;
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public AccountTypeProvider mTypeProvider;

    public AccountTypeManagerImpl(Context context) {
        this.mContext = context;
        this.mLocalAccountLocator = DeviceLocalAccountLocator.create(context);
        this.mTypeProvider = new AccountTypeProvider(context);
        this.mFallbackAccountType = new FallbackAccountType(context);
        this.mAccountManager = AccountManager.get(this.mContext);
        this.mExecutor = ContactsExecutors.getDefaultThreadPoolExecutor();
        this.mMainThreadExecutor = ContactsExecutors.newHandlerExecutor(this.mMainThreadHandler);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
        intentFilter2.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter2);
        this.mContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
        this.mAccountManager.addOnAccountsUpdatedListener(this, this.mMainThreadHandler, false);
        ContentResolver.addStatusChangeListener(1, this);
        if (Flags.getInstance().getBoolean("Account__cp2_device_account_detection_enabled")) {
            this.mContext.getContentResolver().registerContentObserver(ContactsContract.RawContacts.CONTENT_URI, true, new ContentObserver(this.mMainThreadHandler) {
                public boolean deliverSelfNotifications() {
                    return true;
                }

                public void onChange(boolean z) {
                    AccountTypeManagerImpl.this.reloadLocalAccounts();
                }

                public void onChange(boolean z, Uri uri) {
                    AccountTypeManagerImpl.this.reloadLocalAccounts();
                }
            });
        }
        loadAccountTypes();
    }

    public void onStatusChanged(int i) {
        reloadAccountTypesIfNeeded();
    }

    public void onAccountsUpdated(Account[] accountArr) {
        reloadLocalAccounts();
        maybeNotifyAccountsUpdated(this.mAccountManagerAccounts, getAccountsWithDataSets(accountArr, this.mTypeProvider));
    }

    /* access modifiers changed from: private */
    public void maybeNotifyAccountsUpdated(List<AccountWithDataSet> list, List<AccountWithDataSet> list2) {
        if (!Objects.equal(list, list2)) {
            list.clear();
            list.addAll(list2);
            notifyAccountsChanged();
        }
    }

    private void notifyAccountsChanged() {
        ContactListFilterController.getInstance(this.mContext).checkFilterValidity(true);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(AccountTypeManager.BROADCAST_ACCOUNTS_CHANGED));
    }

    private synchronized void startLoadingIfNeeded() {
        if (this.mTypeProvider == null && this.mAccountTypesFuture == null) {
            reloadAccountTypesIfNeeded();
        }
        if (this.mLocalAccountsFuture == null) {
            reloadLocalAccounts();
        }
    }

    private synchronized void loadAccountTypes() {
        this.mTypeProvider = new AccountTypeProvider(this.mContext);
        this.mAccountTypesFuture = this.mExecutor.submit(new Callable<AccountTypeProvider>() {
            public AccountTypeProvider call() throws Exception {
                AccountTypeManagerImpl accountTypeManagerImpl = AccountTypeManagerImpl.this;
                List unused = accountTypeManagerImpl.getAccountsWithDataSets(accountTypeManagerImpl.mAccountManager.getAccounts(), AccountTypeManagerImpl.this.mTypeProvider);
                return AccountTypeManagerImpl.this.mTypeProvider;
            }
        });
    }

    private FutureCallback<List<AccountWithDataSet>> newAccountsUpdatedCallback(final List<AccountWithDataSet> list) {
        return new FutureCallback<List<AccountWithDataSet>>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(List<AccountWithDataSet> list) {
                AccountTypeManagerImpl.this.maybeNotifyAccountsUpdated(list, list);
            }
        };
    }

    private synchronized void reloadAccountTypesIfNeeded() {
        if (this.mTypeProvider == null || this.mTypeProvider.shouldUpdate(this.mAccountManager.getAuthenticatorTypes(), ContentResolver.getSyncAdapterTypes())) {
            reloadAccountTypes();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void reloadAccountTypes() {
        loadAccountTypes();
        Futures.addCallback(Futures.transform(this.mAccountTypesFuture, this.mAccountsExtractor), newAccountsUpdatedCallback(this.mAccountManagerAccounts), this.mMainThreadExecutor);
    }

    private synchronized void loadLocalAccounts() {
        this.mLocalAccountsFuture = this.mExecutor.submit(new Callable<List<AccountWithDataSet>>() {
            public List<AccountWithDataSet> call() throws Exception {
                return AccountTypeManagerImpl.this.mLocalAccountLocator.getDeviceLocalAccounts();
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void reloadLocalAccounts() {
        loadLocalAccounts();
        Futures.addCallback(this.mLocalAccountsFuture, newAccountsUpdatedCallback(this.mLocalAccounts), this.mMainThreadExecutor);
    }

    public ListenableFuture<List<AccountInfo>> getAccountsAsync() {
        return getAllAccountsAsyncInternal();
    }

    private synchronized ListenableFuture<List<AccountInfo>> getAllAccountsAsyncInternal() {
        final AccountTypeProvider accountTypeProvider;
        startLoadingIfNeeded();
        accountTypeProvider = this.mTypeProvider;
        return Futures.transform(Futures.nonCancellationPropagating(Futures.successfulAsList((ListenableFuture<? extends V>[]) new ListenableFuture[]{Futures.transform(this.mAccountTypesFuture, this.mAccountsExtractor), this.mLocalAccountsFuture})), new Function<List<List<AccountWithDataSet>>, List<AccountInfo>>() {
            public List<AccountInfo> apply(List<List<AccountWithDataSet>> list) {
                Preconditions.checkArgument(list.size() == 2, "List should have exactly 2 elements");
                ArrayList arrayList = new ArrayList();
                for (AccountWithDataSet accountWithDataSet : list.get(0)) {
                    arrayList.add(accountTypeProvider.getTypeForAccount(accountWithDataSet).wrapAccount(AccountTypeManagerImpl.this.mContext, accountWithDataSet));
                }
                for (AccountWithDataSet accountWithDataSet2 : list.get(1)) {
                    arrayList.add(accountTypeProvider.getTypeForAccount(accountWithDataSet2).wrapAccount(AccountTypeManagerImpl.this.mContext, accountWithDataSet2));
                }
                AccountInfo.sortAccounts((AccountWithDataSet) null, arrayList);
                return arrayList;
            }
        });
    }

    public ListenableFuture<List<AccountInfo>> filterAccountsAsync(final Predicate<AccountInfo> predicate) {
        return Futures.transform(getAllAccountsAsyncInternal(), new Function<List<AccountInfo>, List<AccountInfo>>() {
            public List<AccountInfo> apply(List<AccountInfo> list) {
                return new ArrayList(Collections2.filter(list, predicate));
            }
        }, (Executor) this.mExecutor);
    }

    public AccountInfo getAccountInfoForAccount(AccountWithDataSet accountWithDataSet) {
        if (accountWithDataSet == null) {
            return null;
        }
        AccountType typeForAccount = this.mTypeProvider.getTypeForAccount(accountWithDataSet);
        if (typeForAccount == null) {
            typeForAccount = this.mFallbackAccountType;
        }
        return typeForAccount.wrapAccount(this.mContext, accountWithDataSet);
    }

    /* access modifiers changed from: private */
    public List<AccountWithDataSet> getAccountsWithDataSets(Account[] accountArr, AccountTypeProvider accountTypeProvider) {
        ArrayList arrayList = new ArrayList();
        populateAccountsDataSet(accountTypeProvider, new Account(AccountTypeManager.DEVICE_ACCOUNT_NAME, this.mContext.getPackageName()), arrayList);
        for (Account populateAccountsDataSet : accountArr) {
            populateAccountsDataSet(accountTypeProvider, populateAccountsDataSet, arrayList);
        }
        return arrayList;
    }

    private void populateAccountsDataSet(AccountTypeProvider accountTypeProvider, Account account, List<AccountWithDataSet> list) {
        for (AccountType accountType : accountTypeProvider.getAccountTypes(account.type)) {
            list.add(new AccountWithDataSet(account.name, account.type, accountType.dataSet));
        }
    }

    public Account getDefaultGoogleAccount() {
        Context context = this.mContext;
        return AccountTypeManager.getDefaultGoogleAccount(this.mAccountManager, context.getSharedPreferences(context.getPackageName(), 0), this.mContext.getResources().getString(R.string.contact_editor_default_account_key));
    }

    public List<AccountInfo> getWritableGoogleAccounts() {
        Account[] accountsByType = this.mAccountManager.getAccountsByType(GoogleAccountType.ACCOUNT_TYPE);
        ArrayList arrayList = new ArrayList();
        for (Account account : accountsByType) {
            AccountWithDataSet accountWithDataSet = new AccountWithDataSet(account.name, account.type, (String) null);
            AccountType typeForAccount = this.mTypeProvider.getTypeForAccount(accountWithDataSet);
            if (typeForAccount != null) {
                arrayList.add(typeForAccount.wrapAccount(this.mContext, accountWithDataSet));
            }
        }
        return arrayList;
    }

    public boolean hasNonLocalAccount() {
        Account[] accounts = this.mAccountManager.getAccounts();
        if (accounts == null) {
            return false;
        }
        for (Account account : accounts) {
            if (this.mTypeProvider.supportsContactsSyncing(account.type)) {
                return true;
            }
        }
        return false;
    }

    public DataKind getKindOrFallback(AccountType accountType, String str) {
        DataKind kindForMimetype = accountType != null ? accountType.getKindForMimetype(str) : null;
        if (kindForMimetype == null) {
            kindForMimetype = this.mFallbackAccountType.getKindForMimetype(str);
        }
        if (kindForMimetype == null && Log.isLoggable("AccountTypeManager", 3)) {
            Log.d("AccountTypeManager", "Unknown type=" + accountType + ", mime=" + str);
        }
        return kindForMimetype;
    }

    public boolean exists(AccountWithDataSet accountWithDataSet) {
        Account[] accountsByType = this.mAccountManager.getAccountsByType(accountWithDataSet.type);
        int length = accountsByType.length;
        int i = 0;
        while (i < length) {
            if (!accountsByType[i].name.equals(accountWithDataSet.name)) {
                i++;
            } else if (this.mTypeProvider.getTypeForAccount(accountWithDataSet) != null) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public AccountType getAccountType(AccountTypeWithDataSet accountTypeWithDataSet) {
        AccountType type = this.mTypeProvider.getType(accountTypeWithDataSet.accountType, accountTypeWithDataSet.dataSet);
        return type != null ? type : this.mFallbackAccountType;
    }
}
