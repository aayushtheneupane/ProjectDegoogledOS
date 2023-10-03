package com.android.dialer.preferredsim.impl;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.R;
import com.android.dialer.activecalls.ActiveCallInfo;
import com.android.dialer.activecalls.ActiveCallsComponent;
import com.android.dialer.activecalls.impl.ActiveCallsImpl;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.preferredsim.PreferredAccountWorker;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class PreferredAccountWorkerImpl implements PreferredAccountWorker {
    public static final String METADATA_SUPPORTS_PREFERRED_SIM = "supports_per_number_preferred_account";
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    public PreferredAccountWorkerImpl(Context context, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0085, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0086, code lost:
        if (r9 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        $closeResource(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008b, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008e, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008f, code lost:
        if (r10 != null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0091, code lost:
        $closeResource(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0094, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
        if (r10 != null) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.common.base.Optional<java.lang.String> getAccountType(android.content.ContentResolver r9, long r10) {
        /*
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.common.Assert.isWorkerThread()
            android.net.Uri r0 = android.provider.ContactsContract.Data.CONTENT_URI
            android.net.Uri r2 = android.content.ContentUris.withAppendedId(r0, r10)
            java.lang.String r10 = "raw_contact_id"
            java.lang.String[] r3 = new java.lang.String[]{r10}
            r4 = 0
            r5 = 0
            r6 = 0
            r1 = r9
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6)
            r11 = 0
            r0 = 0
            if (r10 == 0) goto L_0x0032
            boolean r1 = r10.moveToFirst()     // Catch:{ all -> 0x008c }
            if (r1 != 0) goto L_0x0025
            goto L_0x0032
        L_0x0025:
            long r1 = r10.getLong(r11)     // Catch:{ all -> 0x008c }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x008c }
            com.google.common.base.Optional r1 = com.google.common.base.Optional.m67of(r1)     // Catch:{ all -> 0x008c }
            goto L_0x0038
        L_0x0032:
            com.google.common.base.Optional r1 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x008c }
            if (r10 == 0) goto L_0x003b
        L_0x0038:
            $closeResource(r0, r10)
        L_0x003b:
            boolean r10 = r1.isPresent()
            if (r10 != 0) goto L_0x0046
            com.google.common.base.Optional r9 = com.google.common.base.Optional.absent()
            return r9
        L_0x0046:
            android.net.Uri r10 = android.provider.ContactsContract.RawContacts.CONTENT_URI
            java.lang.Object r1 = r1.get()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            android.net.Uri r4 = android.content.ContentUris.withAppendedId(r10, r1)
            java.lang.String r10 = "account_type"
            java.lang.String[] r5 = new java.lang.String[]{r10}
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r9
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)
            if (r9 == 0) goto L_0x0079
            boolean r10 = r9.moveToFirst()     // Catch:{ all -> 0x0083 }
            if (r10 != 0) goto L_0x006d
            goto L_0x0079
        L_0x006d:
            java.lang.String r10 = r9.getString(r11)     // Catch:{ all -> 0x0083 }
            com.google.common.base.Optional r10 = com.google.common.base.Optional.fromNullable(r10)     // Catch:{ all -> 0x0083 }
            $closeResource(r0, r9)
            return r10
        L_0x0079:
            com.google.common.base.Optional r10 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x0083 }
            if (r9 == 0) goto L_0x0082
            $closeResource(r0, r9)
        L_0x0082:
            return r10
        L_0x0083:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0085 }
        L_0x0085:
            r11 = move-exception
            if (r9 == 0) goto L_0x008b
            $closeResource(r10, r9)
        L_0x008b:
            throw r11
        L_0x008c:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x008e }
        L_0x008e:
            r11 = move-exception
            if (r10 == 0) goto L_0x0094
            $closeResource(r9, r10)
        L_0x0094:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.preferredsim.impl.PreferredAccountWorkerImpl.getAccountType(android.content.ContentResolver, long):com.google.common.base.Optional");
    }

    public static Intent getQuickContactIntent() {
        Intent intent = new Intent("android.provider.action.QUICK_CONTACT");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(ContactsContract.Contacts.CONTENT_URI.buildUpon().appendPath("1").build());
        return intent;
    }

    private boolean isSelectable(PhoneAccountHandle phoneAccountHandle) {
        ImmutableList<ActiveCallInfo> activeCalls = ((ActiveCallsImpl) ActiveCallsComponent.get(this.appContext).activeCalls()).getActiveCalls();
        if (activeCalls.isEmpty()) {
            return true;
        }
        UnmodifiableIterator<ActiveCallInfo> it = activeCalls.iterator();
        while (it.hasNext()) {
            if (Objects.equals(phoneAccountHandle, it.next().phoneAccountHandle().orNull())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public SelectPhoneAccountDialogOptions.Builder createDialogOptionsBuilder(List<PhoneAccountHandle> list, String str, SuggestionProvider.Suggestion suggestion) {
        Optional optional;
        ((LoggingBindingsStub) Logger.get(this.appContext)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_SHOWN);
        if (str != null) {
            ((LoggingBindingsStub) Logger.get(this.appContext)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_IN_CONTACTS);
        }
        if (suggestion != null) {
            ((LoggingBindingsStub) Logger.get(this.appContext)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_SUGGESTION_AVAILABLE);
            int ordinal = suggestion.reason.ordinal();
            if (ordinal == 1) {
                ((LoggingBindingsStub) Logger.get(this.appContext)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_SUGGESTED_CARRIER);
            } else if (ordinal == 2) {
                ((LoggingBindingsStub) Logger.get(this.appContext)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_SUGGESTED_FREQUENCY);
            }
        }
        SelectPhoneAccountDialogOptions.Builder newBuilder = SelectPhoneAccountDialogOptions.newBuilder();
        newBuilder.setTitle(R.string.pre_call_select_phone_account);
        newBuilder.setCanSetDefault(str != null);
        newBuilder.setSetDefaultLabel(R.string.pre_call_select_phone_account_remember);
        for (PhoneAccountHandle next : list) {
            SelectPhoneAccountDialogOptions.Entry.Builder newBuilder2 = SelectPhoneAccountDialogOptions.Entry.newBuilder();
            newBuilder2.setPhoneAccountHandleComponentName(next.getComponentName().flattenToString());
            newBuilder2.setPhoneAccountHandleId(next.getId());
            if (isSelectable(next)) {
                Optional<String> hint = SuggestionProvider.getHint(this.appContext, next, suggestion);
                if (hint.isPresent()) {
                    newBuilder2.setHint(hint.get());
                }
            } else {
                newBuilder2.setEnabled(false);
                ImmutableList<ActiveCallInfo> activeCalls = ((ActiveCallsImpl) ActiveCallsComponent.get(this.appContext).activeCalls()).getActiveCalls();
                if (activeCalls.isEmpty()) {
                    LogUtil.m8e("CallingAccountSelector.getActiveCallLabel", "active calls no longer exist", new Object[0]);
                    optional = Optional.absent();
                } else {
                    ActiveCallInfo activeCallInfo = activeCalls.get(0);
                    if (!activeCallInfo.phoneAccountHandle().isPresent()) {
                        LogUtil.m8e("CallingAccountSelector.getActiveCallLabel", "active call has no phone account", new Object[0]);
                        optional = Optional.absent();
                    } else {
                        PhoneAccount phoneAccount = ((TelecomManager) this.appContext.getSystemService(TelecomManager.class)).getPhoneAccount(activeCallInfo.phoneAccountHandle().get());
                        if (phoneAccount == null) {
                            LogUtil.m8e("CallingAccountSelector.getActiveCallLabel", "phone account not found", new Object[0]);
                            optional = Optional.absent();
                        } else {
                            optional = Optional.m67of(phoneAccount.getLabel().toString());
                        }
                    }
                }
                if (optional.isPresent()) {
                    newBuilder2.setHint(this.appContext.getString(R.string.pre_call_select_phone_account_hint_other_sim_in_use, new Object[]{optional.get()}));
                }
            }
            newBuilder.addEntries(newBuilder2);
        }
        return newBuilder;
    }

    public SelectPhoneAccountDialogOptions getVoicemailDialogOptions() {
        SelectPhoneAccountDialogOptions.Builder builderWithAccounts = R$style.builderWithAccounts(((TelecomManager) this.appContext.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts());
        builderWithAccounts.setTitle(R.string.pre_call_select_phone_account);
        builderWithAccounts.setCanSetDefault(false);
        return (SelectPhoneAccountDialogOptions) builderWithAccounts.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02e0, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02e1, code lost:
        if (r0 != null) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02e3, code lost:
        $closeResource(r13, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02e6, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01db, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01dc, code lost:
        if (r6 != null) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01de, code lost:
        $closeResource(r13, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01e1, code lost:
        throw r14;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0239  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.android.dialer.preferredsim.PreferredAccountWorker.Result lambda$selectAccount$0$PreferredAccountWorkerImpl(java.lang.String r14, java.util.List r15) throws java.lang.Exception {
        /*
            r13 = this;
            com.android.dialer.common.Assert.isWorkerThread()
            android.content.Context r0 = r13.appContext
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.configprovider.ConfigProviderComponent r1 = com.android.dialer.configprovider.ConfigProviderComponent.get(r0)
            com.android.dialer.configprovider.ConfigProvider r1 = r1.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r1 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r1
            r2 = 1
            java.lang.String r3 = "preferred_sim_enabled"
            boolean r1 = r1.getBoolean(r3, r2)
            r3 = 0
            if (r1 != 0) goto L_0x001d
            goto L_0x005f
        L_0x001d:
            android.content.Intent r1 = getQuickContactIntent()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ResolveInfo r0 = r0.resolveActivity(r1, r4)
            java.lang.String r1 = "CallingAccountSelector.isPreferredSimEnabled"
            if (r0 == 0) goto L_0x0058
            android.content.pm.ActivityInfo r4 = r0.activityInfo
            if (r4 == 0) goto L_0x0058
            android.content.pm.ApplicationInfo r4 = r4.applicationInfo
            if (r4 == 0) goto L_0x0058
            android.content.pm.ActivityInfo r4 = r0.activityInfo
            android.content.pm.ApplicationInfo r4 = r4.applicationInfo
            android.os.Bundle r4 = r4.metaData
            if (r4 != 0) goto L_0x0040
            goto L_0x0058
        L_0x0040:
            android.content.pm.ActivityInfo r0 = r0.activityInfo
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo
            android.os.Bundle r0 = r0.metaData
            java.lang.String r4 = "supports_per_number_preferred_account"
            boolean r0 = r0.getBoolean(r4, r3)
            if (r0 != 0) goto L_0x0056
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r4 = "system contacts does not support preferred SIM"
            com.android.dialer.common.LogUtil.m9i(r1, r4, r0)
            goto L_0x005f
        L_0x0056:
            r0 = r2
            goto L_0x0060
        L_0x0058:
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r4 = "cannot resolve quick contact app"
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r1, (java.lang.String) r4, (java.lang.Object[]) r0)
        L_0x005f:
            r0 = r3
        L_0x0060:
            r1 = 0
            if (r0 != 0) goto L_0x0069
            com.google.common.base.Optional r0 = com.google.common.base.Optional.absent()
            goto L_0x0138
        L_0x0069:
            android.content.Context r0 = r13.appContext
            boolean r0 = com.android.dialer.util.PermissionsUtil.hasContactsReadPermissions(r0)
            if (r0 != 0) goto L_0x0080
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r4 = "PreferredAccountWorker.doInBackground"
            java.lang.String r5 = "missing READ_CONTACTS permission"
            com.android.dialer.common.LogUtil.m9i(r4, r5, r0)
            com.google.common.base.Optional r0 = com.google.common.base.Optional.absent()
            goto L_0x0138
        L_0x0080:
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            if (r0 == 0) goto L_0x008c
            com.google.common.base.Optional r0 = com.google.common.base.Optional.absent()
            goto L_0x0138
        L_0x008c:
            android.content.Context r0 = r13.appContext
            android.content.ContentResolver r4 = r0.getContentResolver()
            android.net.Uri r0 = android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI
            java.lang.String r5 = android.net.Uri.encode(r14)
            android.net.Uri r5 = android.net.Uri.withAppendedPath(r0, r5)
            java.lang.String r0 = "data_id"
            java.lang.String[] r6 = new java.lang.String[]{r0}
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9)
            if (r0 != 0) goto L_0x00b6
            com.google.common.base.Optional r4 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x02de }
            if (r0 == 0) goto L_0x00b3
            goto L_0x0133
        L_0x00b3:
            r0 = r4
            goto L_0x0138
        L_0x00b6:
            android.content.Context r4 = r13.appContext     // Catch:{ all -> 0x02de }
            com.android.dialer.configprovider.ConfigProviderComponent r4 = com.android.dialer.configprovider.ConfigProviderComponent.get(r4)     // Catch:{ all -> 0x02de }
            com.android.dialer.configprovider.ConfigProvider r4 = r4.getConfigProvider()     // Catch:{ all -> 0x02de }
            com.android.dialer.configprovider.SharedPrefConfigProvider r4 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r4     // Catch:{ all -> 0x02de }
            java.lang.String r5 = "preferred_sim_valid_account_types"
            java.lang.String r6 = "com.google;com.osp.app.signin;com.android.exchange;com.google.android.exchange;com.google.android.gm.exchange"
            java.lang.String r4 = r4.getString(r5, r6)     // Catch:{ all -> 0x02de }
            java.lang.String r5 = ";"
            java.lang.String[] r4 = r4.split(r5)     // Catch:{ all -> 0x02de }
            com.google.common.collect.ImmutableSet r4 = com.google.common.collect.ImmutableSet.copyOf((E[]) r4)     // Catch:{ all -> 0x02de }
            r5 = r1
        L_0x00d5:
            boolean r6 = r0.moveToNext()     // Catch:{ all -> 0x02de }
            if (r6 == 0) goto L_0x012f
            android.content.Context r6 = r13.appContext     // Catch:{ all -> 0x02de }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ all -> 0x02de }
            long r7 = r0.getLong(r3)     // Catch:{ all -> 0x02de }
            com.google.common.base.Optional r6 = getAccountType(r6, r7)     // Catch:{ all -> 0x02de }
            boolean r7 = r6.isPresent()     // Catch:{ all -> 0x02de }
            java.lang.String r8 = "CallingAccountSelector.getDataId"
            if (r7 == 0) goto L_0x0112
            java.lang.Object r7 = r6.get()     // Catch:{ all -> 0x02de }
            boolean r7 = r4.contains(r7)     // Catch:{ all -> 0x02de }
            if (r7 != 0) goto L_0x0112
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x02de }
            r7.<init>()     // Catch:{ all -> 0x02de }
            java.lang.String r9 = "ignoring non-writable "
            r7.append(r9)     // Catch:{ all -> 0x02de }
            r7.append(r6)     // Catch:{ all -> 0x02de }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x02de }
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ all -> 0x02de }
            com.android.dialer.common.LogUtil.m9i(r8, r6, r7)     // Catch:{ all -> 0x02de }
            goto L_0x00d5
        L_0x0112:
            if (r5 == 0) goto L_0x012a
            java.lang.String r6 = r0.getString(r3)     // Catch:{ all -> 0x02de }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x02de }
            if (r5 != 0) goto L_0x012a
            java.lang.String r4 = "lookup result not unique, ignoring"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x02de }
            com.android.dialer.common.LogUtil.m9i(r8, r4, r5)     // Catch:{ all -> 0x02de }
            com.google.common.base.Optional r4 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x02de }
            goto L_0x0133
        L_0x012a:
            java.lang.String r5 = r0.getString(r3)     // Catch:{ all -> 0x02de }
            goto L_0x00d5
        L_0x012f:
            com.google.common.base.Optional r4 = com.google.common.base.Optional.fromNullable(r5)     // Catch:{ all -> 0x02de }
        L_0x0133:
            $closeResource(r1, r0)
            goto L_0x00b3
        L_0x0138:
            boolean r4 = r0.isPresent()
            java.lang.String r5 = "CallingAccountSelector.usePreferredAccount"
            if (r4 == 0) goto L_0x01e2
            android.content.Context r4 = r13.appContext
            java.lang.Object r6 = r0.get()
            java.lang.String r6 = (java.lang.String) r6
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.common.Assert.isNotNull(r6)
            android.content.ContentResolver r7 = r4.getContentResolver()
            android.net.Uri r8 = com.android.dialer.preferredsim.PreferredSimFallbackContract.CONTENT_URI
            java.lang.String r9 = "preferred_phone_account_component_name"
            java.lang.String r10 = "preferred_phone_account_id"
            java.lang.String[] r9 = new java.lang.String[]{r9, r10}
            java.lang.String[] r11 = new java.lang.String[r2]
            r11[r3] = r6
            r12 = 0
            java.lang.String r10 = "data_id = ?"
            android.database.Cursor r6 = r7.query(r8, r9, r10, r11, r12)
            if (r6 != 0) goto L_0x0170
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x01d9 }
            if (r6 == 0) goto L_0x018a
            goto L_0x0187
        L_0x0170:
            boolean r7 = r6.moveToFirst()     // Catch:{ all -> 0x01d9 }
            if (r7 != 0) goto L_0x017b
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x01d9 }
            goto L_0x0187
        L_0x017b:
            java.lang.String r7 = r6.getString(r3)     // Catch:{ all -> 0x01d9 }
            java.lang.String r2 = r6.getString(r2)     // Catch:{ all -> 0x01d9 }
            com.google.common.base.Optional r2 = android.support.p002v7.appcompat.R$style.getValidPhoneAccount(r4, r7, r2)     // Catch:{ all -> 0x01d9 }
        L_0x0187:
            $closeResource(r1, r6)
        L_0x018a:
            boolean r4 = r2.isPresent()
            if (r4 == 0) goto L_0x01e2
            java.lang.Object r14 = r2.get()
            android.telecom.PhoneAccountHandle r14 = (android.telecom.PhoneAccountHandle) r14
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = r13.isSelectable(r14)
            if (r2 == 0) goto L_0x01b4
            android.content.Context r13 = r13.appContext
            com.android.dialer.logging.LoggingBindings r13 = com.android.dialer.logging.Logger.get(r13)
            com.android.dialer.logging.DialerImpression$Type r15 = com.android.dialer.logging.DialerImpression$Type.DUAL_SIM_SELECTION_PREFERRED_USED
            com.android.dialer.logging.LoggingBindingsStub r13 = (com.android.dialer.logging.LoggingBindingsStub) r13
            r13.logImpression(r15)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r13 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((android.telecom.PhoneAccountHandle) r14)
            goto L_0x01d0
        L_0x01b4:
            android.content.Context r14 = r13.appContext
            com.android.dialer.logging.LoggingBindings r14 = com.android.dialer.logging.Logger.get(r14)
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.DUAL_SIM_SELECTION_PREFERRED_NOT_SELECTABLE
            com.android.dialer.logging.LoggingBindingsStub r14 = (com.android.dialer.logging.LoggingBindingsStub) r14
            r14.logImpression(r2)
            java.lang.Object[] r14 = new java.lang.Object[r3]
            java.lang.String r2 = "preferred account not selectable"
            com.android.dialer.common.LogUtil.m9i(r5, r2, r14)
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$Builder r13 = r13.createDialogOptionsBuilder(r15, r0, r1)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r13 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((com.android.contacts.common.widget.SelectPhoneAccountDialogOptions.Builder) r13)
        L_0x01d0:
            r13.setDataId(r0)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result r13 = r13.build()
            goto L_0x02dd
        L_0x01d9:
            r13 = move-exception
            throw r13     // Catch:{ all -> 0x01db }
        L_0x01db:
            r14 = move-exception
            if (r6 == 0) goto L_0x01e1
            $closeResource(r13, r6)
        L_0x01e1:
            throw r14
        L_0x01e2:
            android.content.Context r2 = r13.appContext
            java.lang.Class<android.telecom.TelecomManager> r4 = android.telecom.TelecomManager.class
            java.lang.Object r2 = r2.getSystemService(r4)
            android.telecom.TelecomManager r2 = (android.telecom.TelecomManager) r2
            java.lang.String r4 = "tel"
            android.telecom.PhoneAccountHandle r2 = r2.getDefaultOutgoingPhoneAccount(r4)
            java.lang.String r4 = "global account not selectable"
            if (r2 == 0) goto L_0x0239
            java.lang.Object r14 = r0.orNull()
            java.lang.String r14 = (java.lang.String) r14
            boolean r0 = r13.isSelectable(r2)
            if (r0 == 0) goto L_0x0219
            android.content.Context r13 = r13.appContext
            com.android.dialer.logging.LoggingBindings r13 = com.android.dialer.logging.Logger.get(r13)
            com.android.dialer.logging.DialerImpression$Type r14 = com.android.dialer.logging.DialerImpression$Type.DUAL_SIM_SELECTION_GLOBAL_USED
            com.android.dialer.logging.LoggingBindingsStub r13 = (com.android.dialer.logging.LoggingBindingsStub) r13
            r13.logImpression(r14)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r13 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((android.telecom.PhoneAccountHandle) r2)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result r13 = r13.build()
            goto L_0x02dd
        L_0x0219:
            android.content.Context r0 = r13.appContext
            com.android.dialer.logging.LoggingBindings r0 = com.android.dialer.logging.Logger.get(r0)
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.DUAL_SIM_SELECTION_GLOBAL_NOT_SELECTABLE
            com.android.dialer.logging.LoggingBindingsStub r0 = (com.android.dialer.logging.LoggingBindingsStub) r0
            r0.logImpression(r2)
            java.lang.Object[] r0 = new java.lang.Object[r3]
            com.android.dialer.common.LogUtil.m9i(r5, r4, r0)
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$Builder r13 = r13.createDialogOptionsBuilder(r15, r14, r1)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r13 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((com.android.contacts.common.widget.SelectPhoneAccountDialogOptions.Builder) r13)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result r13 = r13.build()
            goto L_0x02dd
        L_0x0239:
            android.content.Context r1 = r13.appContext
            com.android.dialer.preferredsim.suggestion.SimSuggestionComponent r1 = com.android.dialer.preferredsim.suggestion.SimSuggestionComponent.get(r1)
            com.android.dialer.preferredsim.suggestion.SuggestionProvider r1 = r1.getSuggestionProvider()
            android.content.Context r2 = r13.appContext
            com.android.dialer.preferredsim.suggestion.stub.StubSuggestionProvider r1 = (com.android.dialer.preferredsim.suggestion.stub.StubSuggestionProvider) r1
            com.google.common.base.Optional r14 = r1.getSuggestion(r2, r14)
            boolean r1 = r14.isPresent()
            if (r1 == 0) goto L_0x02a7
            java.lang.Object r1 = r14.get()
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Suggestion r1 = (com.android.dialer.preferredsim.suggestion.SuggestionProvider.Suggestion) r1
            boolean r1 = r1.shouldAutoSelect
            if (r1 == 0) goto L_0x02a7
            java.lang.Object r14 = r14.get()
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Suggestion r14 = (com.android.dialer.preferredsim.suggestion.SuggestionProvider.Suggestion) r14
            java.lang.Object r0 = r0.orNull()
            java.lang.String r0 = (java.lang.String) r0
            android.telecom.PhoneAccountHandle r1 = r14.phoneAccountHandle
            boolean r2 = r13.isSelectable(r1)
            if (r2 == 0) goto L_0x0288
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r15 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((android.telecom.PhoneAccountHandle) r1)
            android.content.Context r13 = r13.appContext
            com.android.dialer.logging.LoggingBindings r13 = com.android.dialer.logging.Logger.get(r13)
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.DUAL_SIM_SELECTION_SUGGESTION_AUTO_SELECTED
            com.android.dialer.logging.LoggingBindingsStub r13 = (com.android.dialer.logging.LoggingBindingsStub) r13
            r13.logImpression(r0)
            r15.setSuggestion(r14)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result r13 = r15.build()
            goto L_0x02dd
        L_0x0288:
            android.content.Context r1 = r13.appContext
            com.android.dialer.logging.LoggingBindings r1 = com.android.dialer.logging.Logger.get(r1)
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.DUAL_SIM_SELECTION_SUGGESTION_AUTO_NOT_SELECTABLE
            com.android.dialer.logging.LoggingBindingsStub r1 = (com.android.dialer.logging.LoggingBindingsStub) r1
            r1.logImpression(r2)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            com.android.dialer.common.LogUtil.m9i(r5, r4, r1)
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$Builder r13 = r13.createDialogOptionsBuilder(r15, r0, r14)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r13 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((com.android.contacts.common.widget.SelectPhoneAccountDialogOptions.Builder) r13)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result r13 = r13.build()
            goto L_0x02dd
        L_0x02a7:
            java.lang.Object r1 = r0.orNull()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r14.orNull()
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Suggestion r2 = (com.android.dialer.preferredsim.suggestion.SuggestionProvider.Suggestion) r2
            com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$Builder r13 = r13.createDialogOptionsBuilder(r15, r1, r2)
            com.android.dialer.preferredsim.PreferredAccountWorker$Result$Builder r13 = com.android.dialer.preferredsim.PreferredAccountWorker.Result.builder((com.android.contacts.common.widget.SelectPhoneAccountDialogOptions.Builder) r13)
            boolean r15 = r14.isPresent()
            if (r15 == 0) goto L_0x02ca
            java.lang.Object r14 = r14.get()
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Suggestion r14 = (com.android.dialer.preferredsim.suggestion.SuggestionProvider.Suggestion) r14
            r13.setSuggestion(r14)
        L_0x02ca:
            boolean r14 = r0.isPresent()
            if (r14 == 0) goto L_0x02d9
            java.lang.Object r14 = r0.get()
            java.lang.String r14 = (java.lang.String) r14
            r13.setDataId(r14)
        L_0x02d9:
            com.android.dialer.preferredsim.PreferredAccountWorker$Result r13 = r13.build()
        L_0x02dd:
            return r13
        L_0x02de:
            r13 = move-exception
            throw r13     // Catch:{ all -> 0x02e0 }
        L_0x02e0:
            r14 = move-exception
            if (r0 == 0) goto L_0x02e6
            $closeResource(r13, r0)
        L_0x02e6:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.preferredsim.impl.PreferredAccountWorkerImpl.lambda$selectAccount$0$PreferredAccountWorkerImpl(java.lang.String, java.util.List):com.android.dialer.preferredsim.PreferredAccountWorker$Result");
    }

    public ListenableFuture<PreferredAccountWorker.Result> selectAccount(String str, List<PhoneAccountHandle> list) {
        return this.backgroundExecutor.submit(new Callable(str, list) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return PreferredAccountWorkerImpl.this.lambda$selectAccount$0$PreferredAccountWorkerImpl(this.f$1, this.f$2);
            }
        });
    }
}
