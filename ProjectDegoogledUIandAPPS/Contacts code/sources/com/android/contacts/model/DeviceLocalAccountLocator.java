package com.android.contacts.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contactsbind.ObjectFactory;
import com.android.contactsbind.experiments.Flags;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class DeviceLocalAccountLocator {
    public static final DeviceLocalAccountLocator NULL_ONLY = new DeviceLocalAccountLocator() {
        public List<AccountWithDataSet> getDeviceLocalAccounts() {
            return Collections.singletonList(AccountWithDataSet.getNullAccount());
        }
    };

    public abstract List<AccountWithDataSet> getDeviceLocalAccounts();

    public static DeviceLocalAccountLocator create(Context context, Set<String> set) {
        if (Flags.getInstance().getBoolean("Account__cp2_device_account_detection_enabled")) {
            return new Cp2DeviceLocalAccountLocator(context.getContentResolver(), ObjectFactory.getDeviceLocalAccountTypeFactory(context), set);
        }
        return NULL_ONLY;
    }

    public static DeviceLocalAccountLocator create(Context context) {
        HashSet hashSet = new HashSet();
        for (Account account : ((AccountManager) context.getSystemService("account")).getAccounts()) {
            hashSet.add(account.type);
        }
        if (Flags.getInstance().getBoolean("Account__cp2_device_account_detection_enabled")) {
            return new Cp2DeviceLocalAccountLocator(context.getContentResolver(), ObjectFactory.getDeviceLocalAccountTypeFactory(context), hashSet);
        }
        return new NexusDeviceAccountLocator();
    }

    public static class NexusDeviceAccountLocator extends DeviceLocalAccountLocator {
        public List<AccountWithDataSet> getDeviceLocalAccounts() {
            return Collections.emptyList();
        }
    }
}
