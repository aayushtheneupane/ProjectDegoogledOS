package com.android.contacts.common.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.accounts.OnAccountsUpdateListener;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncAdapterType;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.TimingLogger;
import com.android.contacts.common.MoreContactUtils;
import com.android.contacts.common.list.ContactListFilterController;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.account.AccountTypeWithDataSet;
import com.android.contacts.common.model.account.AccountWithDataSet;
import com.android.contacts.common.model.account.ExchangeAccountType;
import com.android.contacts.common.model.account.ExternalAccountType;
import com.android.contacts.common.model.account.FallbackAccountType;
import com.android.contacts.common.model.account.GoogleAccountType;
import com.android.contacts.common.model.account.SamsungAccountType;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AccountTypeManager */
class AccountTypeManagerImpl extends AccountTypeManager implements OnAccountsUpdateListener, SyncStatusObserver {
    private static final Comparator<AccountWithDataSet> ACCOUNT_COMPARATOR = new Comparator<AccountWithDataSet>() {
        public int compare(Object obj, Object obj2) {
            AccountWithDataSet accountWithDataSet = (AccountWithDataSet) obj;
            AccountWithDataSet accountWithDataSet2 = (AccountWithDataSet) obj2;
            if (Objects.equals(accountWithDataSet.name, accountWithDataSet2.name) && Objects.equals(accountWithDataSet.type, accountWithDataSet2.type) && Objects.equals(accountWithDataSet.dataSet, accountWithDataSet2.dataSet)) {
                return 0;
            }
            String str = accountWithDataSet2.name;
            if (!(str == null || accountWithDataSet2.type == null)) {
                String str2 = accountWithDataSet.name;
                if (!(str2 == null || accountWithDataSet.type == null)) {
                    int compareTo = str2.compareTo(str);
                    if (compareTo != 0) {
                        return compareTo;
                    }
                    int compareTo2 = accountWithDataSet.type.compareTo(accountWithDataSet2.type);
                    if (compareTo2 != 0) {
                        return compareTo2;
                    }
                    String str3 = accountWithDataSet.dataSet;
                    if (str3 != null) {
                        String str4 = accountWithDataSet2.dataSet;
                        if (str4 != null) {
                            return str3.compareTo(str4);
                        }
                    }
                }
                return 1;
            }
            return -1;
        }
    };
    private static final Map<AccountTypeWithDataSet, AccountType> EMPTY_UNMODIFIABLE_ACCOUNT_TYPE_MAP = Collections.unmodifiableMap(new HashMap());
    private static final Uri SAMPLE_CONTACT_URI = ContactsContract.Contacts.getLookupUri(1, "xxx");
    private AccountManager mAccountManager;
    private Map<AccountTypeWithDataSet, AccountType> mAccountTypesWithDataSets;
    private List<AccountWithDataSet> mAccounts = new ArrayList();
    private BroadcastReceiver mBroadcastReceiver;
    private final Runnable mCheckFilterValidityRunnable = new Runnable() {
        public void run() {
            ContactListFilterController.getInstance(AccountTypeManagerImpl.this.mContext).checkFilterValidity(true);
        }
    };
    private List<AccountWithDataSet> mContactWritableAccounts = new ArrayList();
    /* access modifiers changed from: private */
    public Context mContext;
    private AccountType mFallbackAccountType;
    private volatile CountDownLatch mInitializationLatch;
    /* access modifiers changed from: private */
    public final InvitableAccountTypeCache mInvitableAccountTypeCache;
    private Map<AccountTypeWithDataSet, AccountType> mInvitableAccountTypes;
    private final AtomicBoolean mInvitablesCacheIsInitialized = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean mInvitablesTaskIsRunning = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Handler mListenerHandler;
    private HandlerThread mListenerThread;
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    /* compiled from: AccountTypeManager */
    private class FindInvitablesTask extends AsyncTask<Void, Void, Map<AccountTypeWithDataSet, AccountType>> {
        /* synthetic */ FindInvitablesTask(C02591 r2) {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            Void[] voidArr = (Void[]) objArr;
            AccountTypeManagerImpl accountTypeManagerImpl = AccountTypeManagerImpl.this;
            return accountTypeManagerImpl.findUsableInvitableAccountTypes(accountTypeManagerImpl.mContext);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            AccountTypeManagerImpl.this.mInvitableAccountTypeCache.setCachedValue((Map) obj);
            AccountTypeManagerImpl.this.mInvitablesTaskIsRunning.set(false);
        }
    }

    /* compiled from: AccountTypeManager */
    private static final class InvitableAccountTypeCache {
        private Map<AccountTypeWithDataSet, AccountType> mInvitableAccountTypes;
        private long mTimeLastSet;

        /* synthetic */ InvitableAccountTypeCache(C02591 r1) {
        }

        public Map<AccountTypeWithDataSet, AccountType> getCachedValue() {
            return this.mInvitableAccountTypes;
        }

        public boolean isExpired() {
            return SystemClock.elapsedRealtime() - this.mTimeLastSet > 60000;
        }

        public void setCachedValue(Map<AccountTypeWithDataSet, AccountType> map) {
            this.mInvitableAccountTypes = map;
            this.mTimeLastSet = SystemClock.elapsedRealtime();
        }
    }

    public AccountTypeManagerImpl(Context context) {
        new ArrayList();
        this.mAccountTypesWithDataSets = new ArrayMap();
        this.mInvitableAccountTypes = EMPTY_UNMODIFIABLE_ACCOUNT_TYPE_MAP;
        this.mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                AccountTypeManagerImpl.this.mListenerHandler.sendMessage(AccountTypeManagerImpl.this.mListenerHandler.obtainMessage(1, intent));
            }
        };
        this.mInitializationLatch = new CountDownLatch(1);
        this.mContext = context;
        this.mFallbackAccountType = new FallbackAccountType(context);
        this.mAccountManager = AccountManager.get(this.mContext);
        this.mListenerThread = new HandlerThread("AccountChangeListener");
        this.mListenerThread.start();
        this.mListenerHandler = new Handler(this.mListenerThread.getLooper()) {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    AccountTypeManagerImpl.this.loadAccountsInBackground();
                } else if (i == 1) {
                    AccountTypeManagerImpl.this.processBroadcastIntent((Intent) message.obj);
                }
            }
        };
        this.mInvitableAccountTypeCache = new InvitableAccountTypeCache((C02591) null);
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
        this.mAccountManager.addOnAccountsUpdatedListener(this, this.mListenerHandler, false);
        ContentResolver.addStatusChangeListener(1, this);
        this.mListenerHandler.sendEmptyMessage(0);
    }

    private void addAccountType(AccountType accountType, Map<AccountTypeWithDataSet, AccountType> map, Map<String, List<AccountType>> map2) {
        map.put(AccountTypeWithDataSet.get(accountType.accountType, accountType.dataSet), accountType);
        List list = map2.get(accountType.accountType);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(accountType);
        map2.put(accountType.accountType, list);
    }

    static Map<AccountTypeWithDataSet, AccountType> findAllInvitableAccountTypes(Context context, Collection<AccountWithDataSet> collection, Map<AccountTypeWithDataSet, AccountType> map) {
        ArrayMap arrayMap = new ArrayMap();
        for (AccountWithDataSet accountTypeWithDataSet : collection) {
            AccountTypeWithDataSet accountTypeWithDataSet2 = accountTypeWithDataSet.getAccountTypeWithDataSet();
            AccountType accountType = map.get(accountTypeWithDataSet2);
            if (accountType != null && !arrayMap.containsKey(accountTypeWithDataSet2)) {
                if (Log.isLoggable("AccountTypeManager", 3)) {
                    Log.d("AccountTypeManager", "Type " + accountTypeWithDataSet2 + " inviteClass=" + accountType.getInviteContactActivityClassName());
                }
                if (!TextUtils.isEmpty(accountType.getInviteContactActivityClassName())) {
                    arrayMap.put(accountTypeWithDataSet2, accountType);
                }
            }
        }
        return Collections.unmodifiableMap(arrayMap);
    }

    /* access modifiers changed from: private */
    public Map<AccountTypeWithDataSet, AccountType> findUsableInvitableAccountTypes(Context context) {
        ensureAccountsLoaded();
        Map<AccountTypeWithDataSet, AccountType> map = this.mInvitableAccountTypes;
        if (map.isEmpty()) {
            return EMPTY_UNMODIFIABLE_ACCOUNT_TYPE_MAP;
        }
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.putAll(map);
        PackageManager packageManager = context.getPackageManager();
        for (AccountTypeWithDataSet next : map.keySet()) {
            Intent invitableIntent = MoreContactUtils.getInvitableIntent(map.get(next), SAMPLE_CONTACT_URI);
            if (invitableIntent == null) {
                arrayMap.remove(next);
            } else if (packageManager.resolveActivity(invitableIntent, 65536) == null) {
                arrayMap.remove(next);
            } else if (!next.hasData(context)) {
                arrayMap.remove(next);
            }
        }
        return Collections.unmodifiableMap(arrayMap);
    }

    /* access modifiers changed from: package-private */
    public void ensureAccountsLoaded() {
        CountDownLatch countDownLatch = this.mInitializationLatch;
        if (countDownLatch != null) {
            while (true) {
                try {
                    countDownLatch.await();
                    return;
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public AccountType getAccountType(AccountTypeWithDataSet accountTypeWithDataSet) {
        AccountType accountType;
        ensureAccountsLoaded();
        synchronized (this) {
            accountType = this.mAccountTypesWithDataSets.get(accountTypeWithDataSet);
            if (accountType == null) {
                accountType = this.mFallbackAccountType;
            }
        }
        return accountType;
    }

    public List<AccountWithDataSet> getAccounts(boolean z) {
        ensureAccountsLoaded();
        return z ? this.mContactWritableAccounts : this.mAccounts;
    }

    public Map<AccountTypeWithDataSet, AccountType> getUsableInvitableAccountTypes() {
        ensureAccountsLoaded();
        if (!this.mInvitablesCacheIsInitialized.get()) {
            this.mInvitableAccountTypeCache.setCachedValue(findUsableInvitableAccountTypes(this.mContext));
            this.mInvitablesCacheIsInitialized.set(true);
        } else if (this.mInvitableAccountTypeCache.isExpired() && this.mInvitablesTaskIsRunning.compareAndSet(false, true)) {
            new FindInvitablesTask((C02591) null).execute(new Void[0]);
        }
        return this.mInvitableAccountTypeCache.getCachedValue();
    }

    /* access modifiers changed from: protected */
    public void loadAccountsInBackground() {
        List<AccountType> list;
        AuthenticatorDescription[] authenticatorDescriptionArr;
        AuthenticatorDescription authenticatorDescription;
        AccountType accountType;
        AccountType samsungAccountType;
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "AccountTypeManager.loadAccountsInBackground start");
        }
        TimingLogger timingLogger = new TimingLogger("AccountTypeManager", "loadAccountsInBackground");
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashSet<String> hashSet = new HashSet<>();
        AccountManager accountManager = this.mAccountManager;
        SyncAdapterType[] syncAdapterTypes = ContentResolver.getSyncAdapterTypes();
        AuthenticatorDescription[] authenticatorTypes = accountManager.getAuthenticatorTypes();
        int length = syncAdapterTypes.length;
        int i = 0;
        while (i < length) {
            int i2 = length;
            SyncAdapterType syncAdapterType = syncAdapterTypes[i];
            SyncAdapterType[] syncAdapterTypeArr = syncAdapterTypes;
            long j = currentThreadTimeMillis;
            if ("com.android.contacts".equals(syncAdapterType.authority)) {
                String str = syncAdapterType.accountType;
                int length2 = authenticatorTypes.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        authenticatorDescription = null;
                        break;
                    }
                    authenticatorDescription = authenticatorTypes[i3];
                    int i4 = length2;
                    if (str.equals(authenticatorDescription.type)) {
                        break;
                    }
                    i3++;
                    length2 = i4;
                }
                if (authenticatorDescription == null) {
                    Log.w("AccountTypeManager", "No authenticator found for type=" + str + ", ignoring it.");
                } else {
                    if ("com.google".equals(str)) {
                        accountType = new GoogleAccountType(this.mContext, authenticatorDescription.packageName);
                        authenticatorDescriptionArr = authenticatorTypes;
                    } else {
                        if (ExchangeAccountType.isExchangeType(str)) {
                            authenticatorDescriptionArr = authenticatorTypes;
                            samsungAccountType = new ExchangeAccountType(this.mContext, authenticatorDescription.packageName, str);
                        } else {
                            authenticatorDescriptionArr = authenticatorTypes;
                            if (SamsungAccountType.isSamsungAccountType(this.mContext, str, authenticatorDescription.packageName)) {
                                samsungAccountType = new SamsungAccountType(this.mContext, authenticatorDescription.packageName, str);
                            } else {
                                Log.d("AccountTypeManager", "Registering external account type=" + str + ", packageName=" + authenticatorDescription.packageName);
                                accountType = new ExternalAccountType(this.mContext, authenticatorDescription.packageName, false);
                            }
                        }
                        accountType = samsungAccountType;
                    }
                    if (accountType.isInitialized()) {
                        accountType.accountType = authenticatorDescription.type;
                        int i5 = authenticatorDescription.labelId;
                        int i6 = authenticatorDescription.iconId;
                        addAccountType(accountType, arrayMap, arrayMap2);
                        hashSet.addAll(accountType.getExtensionPackageNames());
                    } else if (accountType.isEmbedded()) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Problem initializing embedded type ");
                        outline13.append(accountType.getClass().getCanonicalName());
                        throw new IllegalStateException(outline13.toString());
                    }
                    i++;
                    length = i2;
                    syncAdapterTypes = syncAdapterTypeArr;
                    currentThreadTimeMillis = j;
                    authenticatorTypes = authenticatorDescriptionArr;
                }
            }
            authenticatorDescriptionArr = authenticatorTypes;
            i++;
            length = i2;
            syncAdapterTypes = syncAdapterTypeArr;
            currentThreadTimeMillis = j;
            authenticatorTypes = authenticatorDescriptionArr;
        }
        long j2 = currentThreadTimeMillis;
        int i7 = 0;
        if (!hashSet.isEmpty()) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Registering ");
            outline132.append(hashSet.size());
            outline132.append(" extension packages");
            Log.d("AccountTypeManager", outline132.toString());
            for (String str2 : hashSet) {
                ExternalAccountType externalAccountType = new ExternalAccountType(this.mContext, str2, true);
                if (externalAccountType.isInitialized()) {
                    if (!externalAccountType.hasContactsMetadata()) {
                        Log.w("AccountTypeManager", "Skipping extension package " + str2 + " because it doesn't have the CONTACTS_STRUCTURE metadata");
                    } else if (TextUtils.isEmpty(externalAccountType.accountType)) {
                        Log.w("AccountTypeManager", "Skipping extension package " + str2 + " because the CONTACTS_STRUCTURE metadata doesn't have the accountType attribute");
                    } else {
                        StringBuilder outline133 = GeneratedOutlineSupport.outline13("Registering extension package account type=");
                        outline133.append(externalAccountType.accountType);
                        outline133.append(", dataSet=");
                        outline133.append(externalAccountType.dataSet);
                        outline133.append(", packageName=");
                        outline133.append(str2);
                        Log.d("AccountTypeManager", outline133.toString());
                        addAccountType(externalAccountType, arrayMap, arrayMap2);
                    }
                }
            }
        }
        timingLogger.addSplit("Loaded account types");
        Account[] accounts = this.mAccountManager.getAccounts();
        int length3 = accounts.length;
        boolean z = true;
        boolean z2 = false;
        while (i7 < length3) {
            Account account = accounts[i7];
            if (ContentResolver.getIsSyncable(account, "com.android.contacts") > 0) {
                z2 = z;
            }
            if (z2 && (list = (List) arrayMap2.get(account.type)) != null) {
                for (AccountType accountType2 : list) {
                    Account[] accountArr = accounts;
                    ArrayMap arrayMap3 = arrayMap2;
                    int i8 = length3;
                    AccountWithDataSet accountWithDataSet = new AccountWithDataSet(account.name, account.type, accountType2.dataSet);
                    arrayList.add(accountWithDataSet);
                    if (accountType2.areContactsWritable()) {
                        arrayList2.add(accountWithDataSet);
                    }
                    if (accountType2.isGroupMembershipEditable()) {
                        arrayList3.add(accountWithDataSet);
                    }
                    accounts = accountArr;
                    arrayMap2 = arrayMap3;
                    length3 = i8;
                }
            }
            i7++;
            z = true;
            z2 = false;
            accounts = accounts;
            arrayMap2 = arrayMap2;
            length3 = length3;
        }
        Collections.sort(arrayList, ACCOUNT_COMPARATOR);
        Collections.sort(arrayList2, ACCOUNT_COMPARATOR);
        Collections.sort(arrayList3, ACCOUNT_COMPARATOR);
        timingLogger.addSplit("Loaded accounts");
        synchronized (this) {
            this.mAccountTypesWithDataSets = arrayMap;
            this.mAccounts = arrayList;
            this.mContactWritableAccounts = arrayList2;
            this.mInvitableAccountTypes = findAllInvitableAccountTypes(this.mContext, arrayList, arrayMap);
        }
        timingLogger.dumpToLog();
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis();
        StringBuilder outline134 = GeneratedOutlineSupport.outline13("Loaded meta-data for ");
        outline134.append(this.mAccountTypesWithDataSets.size());
        outline134.append(" account types, ");
        outline134.append(this.mAccounts.size());
        outline134.append(" accounts in ");
        outline134.append(elapsedRealtime2 - elapsedRealtime);
        outline134.append("ms(wall) ");
        outline134.append(currentThreadTimeMillis2 - j2);
        outline134.append("ms(cpu)");
        Log.i("AccountTypeManager", outline134.toString());
        if (this.mInitializationLatch != null) {
            this.mInitializationLatch.countDown();
            this.mInitializationLatch = null;
        }
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "AccountTypeManager.loadAccountsInBackground finish");
        }
        this.mMainThreadHandler.post(this.mCheckFilterValidityRunnable);
    }

    public void onAccountsUpdated(Account[] accountArr) {
        loadAccountsInBackground();
    }

    public void onStatusChanged(int i) {
        this.mListenerHandler.sendEmptyMessage(0);
    }

    public void processBroadcastIntent(Intent intent) {
        this.mListenerHandler.sendEmptyMessage(0);
    }
}
