package com.android.contacts.model.account;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.util.DeviceLocalAccountTypeFactory;
import com.android.contactsbind.ObjectFactory;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AccountTypeProvider {
    private final ImmutableMap<String, AuthenticatorDescription> mAuthTypes;
    private final ConcurrentMap<String, List<AccountType>> mCache;
    private final Context mContext;
    private final DeviceLocalAccountTypeFactory mLocalAccountTypeFactory;

    public AccountTypeProvider(Context context) {
        this(context, ObjectFactory.getDeviceLocalAccountTypeFactory(context), ContentResolver.getSyncAdapterTypes(), ((AccountManager) context.getSystemService("account")).getAuthenticatorTypes());
    }

    public AccountTypeProvider(Context context, DeviceLocalAccountTypeFactory deviceLocalAccountTypeFactory, SyncAdapterType[] syncAdapterTypeArr, AuthenticatorDescription[] authenticatorDescriptionArr) {
        this.mCache = new ConcurrentHashMap();
        this.mContext = context;
        this.mLocalAccountTypeFactory = deviceLocalAccountTypeFactory;
        this.mAuthTypes = onlyContactSyncable(authenticatorDescriptionArr, syncAdapterTypeArr);
    }

    public List<AccountType> getAccountTypes(String str) {
        if (this.mContext.getPackageName().equals(str)) {
            str = null;
        }
        if (str == null) {
            Object accountType = this.mLocalAccountTypeFactory.getAccountType(str);
            if (accountType == null) {
                accountType = new FallbackAccountType(this.mContext);
            }
            return Collections.singletonList(accountType);
        }
        List<AccountType> list = (List) this.mCache.get(str);
        if (list != null) {
            return list;
        }
        List<AccountType> loadTypes = loadTypes(str);
        this.mCache.put(str, loadTypes);
        return loadTypes;
    }

    public AccountType getType(String str, String str2) {
        for (AccountType next : getAccountTypes(str)) {
            if (Objects.equal(next.dataSet, str2)) {
                return next;
            }
        }
        return null;
    }

    public AccountType getTypeForAccount(AccountWithDataSet accountWithDataSet) {
        return getType(accountWithDataSet.type, accountWithDataSet.dataSet);
    }

    public boolean shouldUpdate(AuthenticatorDescription[] authenticatorDescriptionArr, SyncAdapterType[] syncAdapterTypeArr) {
        ImmutableMap<String, AuthenticatorDescription> onlyContactSyncable = onlyContactSyncable(authenticatorDescriptionArr, syncAdapterTypeArr);
        if (!onlyContactSyncable.keySet().equals(this.mAuthTypes.keySet())) {
            return true;
        }
        for (AuthenticatorDescription next : onlyContactSyncable.values()) {
            if (!deepEquals(this.mAuthTypes.get(next.type), next)) {
                return true;
            }
        }
        return false;
    }

    public boolean supportsContactsSyncing(String str) {
        return this.mAuthTypes.containsKey(str);
    }

    private List<AccountType> loadTypes(String str) {
        AccountType accountType;
        AuthenticatorDescription authenticatorDescription = this.mAuthTypes.get(str);
        if (authenticatorDescription == null) {
            if (Log.isLoggable("AccountTypeProvider", 3)) {
                Log.d("AccountTypeProvider", "Null auth type for " + str);
            }
            return Collections.emptyList();
        }
        if (GoogleAccountType.ACCOUNT_TYPE.equals(str)) {
            accountType = new GoogleAccountType(this.mContext, authenticatorDescription.packageName);
        } else if (ExchangeAccountType.isExchangeType(str)) {
            accountType = new ExchangeAccountType(this.mContext, authenticatorDescription.packageName, str);
        } else if (SamsungAccountType.isSamsungAccountType(this.mContext, str, authenticatorDescription.packageName)) {
            accountType = new SamsungAccountType(this.mContext, authenticatorDescription.packageName, str);
        } else if (ExternalAccountType.hasContactsXml(this.mContext, authenticatorDescription.packageName) || !DeviceLocalAccountTypeFactory.Util.isLocalAccountType(this.mLocalAccountTypeFactory, str)) {
            if (Log.isLoggable("AccountTypeProvider", 3)) {
                Log.d("AccountTypeProvider", "Registering external account type=" + str + ", packageName=" + authenticatorDescription.packageName);
            }
            accountType = new ExternalAccountType(this.mContext, authenticatorDescription.packageName, false);
        } else {
            if (Log.isLoggable("AccountTypeProvider", 3)) {
                Log.d("AccountTypeProvider", "Registering local account type=" + str + ", packageName=" + authenticatorDescription.packageName);
            }
            accountType = this.mLocalAccountTypeFactory.getAccountType(str);
        }
        if (accountType.isInitialized()) {
            accountType.initializeFieldsFromAuthenticator(authenticatorDescription);
            ImmutableList.Builder builder = ImmutableList.builder();
            builder.add((Object) accountType);
            for (String next : accountType.getExtensionPackageNames()) {
                ExternalAccountType externalAccountType = new ExternalAccountType(this.mContext, next, true);
                if (externalAccountType.isInitialized()) {
                    if (!externalAccountType.hasContactsMetadata()) {
                        Log.w("AccountTypeProvider", "Skipping extension package " + next + " because it doesn't have the CONTACTS_STRUCTURE metadata");
                    } else if (TextUtils.isEmpty(externalAccountType.accountType)) {
                        Log.w("AccountTypeProvider", "Skipping extension package " + next + " because the CONTACTS_STRUCTURE metadata doesn't have the accountType attribute");
                    } else if (!Objects.equal(externalAccountType.accountType, str)) {
                        Log.w("AccountTypeProvider", "Skipping extension package " + next + " because the account type + " + externalAccountType.accountType + " doesn't match expected type " + str);
                    } else {
                        if (Log.isLoggable("AccountTypeProvider", 3)) {
                            Log.d("AccountTypeProvider", "Registering extension package account type=" + accountType.accountType + ", dataSet=" + accountType.dataSet + ", packageName=" + next);
                        }
                        builder.add((Object) externalAccountType);
                    }
                }
            }
            return builder.build();
        } else if (!accountType.isEmbedded()) {
            if (Log.isLoggable("AccountTypeProvider", 3)) {
                Log.d("AccountTypeProvider", "Skipping external account type=" + str + ", packageName=" + authenticatorDescription.packageName);
            }
            return Collections.emptyList();
        } else {
            throw new IllegalStateException("Problem initializing embedded type " + accountType.getClass().getCanonicalName());
        }
    }

    private static ImmutableMap<String, AuthenticatorDescription> onlyContactSyncable(AuthenticatorDescription[] authenticatorDescriptionArr, SyncAdapterType[] syncAdapterTypeArr) {
        HashSet hashSet = new HashSet();
        for (SyncAdapterType syncAdapterType : syncAdapterTypeArr) {
            if (syncAdapterType.authority.equals("com.android.contacts")) {
                hashSet.add(syncAdapterType.accountType);
            }
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (AuthenticatorDescription authenticatorDescription : authenticatorDescriptionArr) {
            if (hashSet.contains(authenticatorDescription.type)) {
                builder.put(authenticatorDescription.type, authenticatorDescription);
            }
        }
        return builder.build();
    }

    private boolean deepEquals(AuthenticatorDescription authenticatorDescription, AuthenticatorDescription authenticatorDescription2) {
        return Objects.equal(authenticatorDescription, authenticatorDescription2) && Objects.equal(authenticatorDescription.packageName, authenticatorDescription2.packageName) && authenticatorDescription.labelId == authenticatorDescription2.labelId && authenticatorDescription.iconId == authenticatorDescription2.iconId && authenticatorDescription.smallIconId == authenticatorDescription2.smallIconId && authenticatorDescription.accountPreferencesId == authenticatorDescription2.accountPreferencesId && authenticatorDescription.customTokens == authenticatorDescription2.customTokens;
    }
}
