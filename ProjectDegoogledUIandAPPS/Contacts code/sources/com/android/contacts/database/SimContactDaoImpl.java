package com.android.contacts.database;

import android.annotation.TargetApi;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import com.android.contacts.R;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.model.SimCard;
import com.android.contacts.model.SimContact;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.PermissionsUtil;
import com.android.contacts.util.SharedPreferenceUtil;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimContactDaoImpl extends SimContactDao {
    public static String EMAILS = "emails";
    public static final Uri ICC_CONTENT_URI = Uri.parse("content://icc/adn");
    private static final int IMPORT_MAX_BATCH_SIZE = 300;
    public static String NAME = "name";
    public static String NUMBER = "number";
    static final int QUERY_MAX_BATCH_SIZE = 100;
    private static final Object SIM_READ_LOCK = new Object();
    private static final String TAG = "SimContactDao";
    public static String _ID = "_id";
    private final Context mContext;
    private final ContentResolver mResolver;
    private final TelephonyManager mTelephonyManager;

    public SimContactDaoImpl(Context context) {
        this(context, context.getContentResolver(), (TelephonyManager) context.getSystemService("phone"));
    }

    public SimContactDaoImpl(Context context, ContentResolver contentResolver, TelephonyManager telephonyManager) {
        this.mContext = context;
        this.mResolver = contentResolver;
        this.mTelephonyManager = telephonyManager;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean canReadSimContacts() {
        return hasTelephony() && hasPermissions() && this.mTelephonyManager.getSimState() == 5;
    }

    public List<SimCard> getSimCards() {
        List<SimCard> list;
        if (!canReadSimContacts()) {
            return Collections.emptyList();
        }
        if (CompatUtils.isMSIMCompatible()) {
            list = getSimCardsFromSubscriptions();
        } else {
            list = Collections.singletonList(SimCard.create(this.mTelephonyManager, this.mContext.getString(R.string.single_sim_display_label)));
        }
        return SharedPreferenceUtil.restoreSimStates(this.mContext, list);
    }

    public ArrayList<SimContact> loadContactsForSim(SimCard simCard) {
        if (simCard.hasValidSubscriptionId()) {
            return loadSimContacts(simCard.getSubscriptionId());
        }
        return loadSimContacts();
    }

    public ArrayList<SimContact> loadSimContacts(int i) {
        return loadFrom(ICC_CONTENT_URI.buildUpon().appendPath("subId").appendPath(String.valueOf(i)).build());
    }

    public ArrayList<SimContact> loadSimContacts() {
        return loadFrom(ICC_CONTENT_URI);
    }

    public ContentProviderResult[] importContacts(List<SimContact> list, AccountWithDataSet accountWithDataSet) throws RemoteException, OperationApplicationException {
        if (list.size() < IMPORT_MAX_BATCH_SIZE) {
            return importBatch(list, accountWithDataSet);
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int size = list.size();
            int i2 = i + IMPORT_MAX_BATCH_SIZE;
            arrayList.addAll(Arrays.asList(importBatch(list.subList(i, Math.min(size, i2)), accountWithDataSet)));
            i = i2;
        }
        return (ContentProviderResult[]) arrayList.toArray(new ContentProviderResult[arrayList.size()]);
    }

    public void persistSimState(SimCard simCard) {
        SharedPreferenceUtil.persistSimStates(this.mContext, Collections.singletonList(simCard));
    }

    public void persistSimStates(List<SimCard> list) {
        SharedPreferenceUtil.persistSimStates(this.mContext, list);
    }

    public SimCard getSimBySubscriptionId(int i) {
        List<SimCard> restoreSimStates = SharedPreferenceUtil.restoreSimStates(this.mContext, getSimCards());
        if (i == -1 && !restoreSimStates.isEmpty()) {
            return restoreSimStates.get(0);
        }
        for (SimCard next : getSimCards()) {
            if (next.getSubscriptionId() == i) {
                return next;
            }
        }
        return null;
    }

    public Map<AccountWithDataSet, Set<SimContact>> findAccountsOfExistingSimContacts(List<SimContact> list) {
        ArrayMap arrayMap = new ArrayMap();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 100;
            findAccountsOfExistingSimContacts(list.subList(i, Math.min(list.size(), i2)), arrayMap);
            i = i2;
        }
        return arrayMap;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0064 A[Catch:{ all -> 0x00a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void findAccountsOfExistingSimContacts(java.util.List<com.android.contacts.model.SimContact> r8, java.util.Map<com.android.contacts.model.account.AccountWithDataSet, java.util.Set<com.android.contacts.model.SimContact>> r9) {
        /*
            r7 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Comparator r1 = com.android.contacts.model.SimContact.compareByPhoneThenName()
            java.util.Collections.sort(r8, r1)
            android.database.Cursor r1 = r7.queryRawContactsForSimContacts(r8)
        L_0x0010:
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x0053
            java.lang.String r2 = com.android.contacts.database.SimContactDaoImpl.DataQuery.getPhoneNumber(r1)     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = com.android.contacts.database.SimContactDaoImpl.DataQuery.getDisplayName(r1)     // Catch:{ all -> 0x00a7 }
            int r2 = com.android.contacts.model.SimContact.findByPhoneAndName(r8, r2, r3)     // Catch:{ all -> 0x00a7 }
            if (r2 >= 0) goto L_0x0025
            goto L_0x0010
        L_0x0025:
            java.lang.Object r2 = r8.get(r2)     // Catch:{ all -> 0x00a7 }
            com.android.contacts.model.SimContact r2 = (com.android.contacts.model.SimContact) r2     // Catch:{ all -> 0x00a7 }
            long r3 = com.android.contacts.database.SimContactDaoImpl.DataQuery.getRawContactId(r1)     // Catch:{ all -> 0x00a7 }
            java.lang.Long r5 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            boolean r5 = r0.containsKey(r5)     // Catch:{ all -> 0x00a7 }
            if (r5 != 0) goto L_0x0045
            java.lang.Long r5 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x00a7 }
            r6.<init>()     // Catch:{ all -> 0x00a7 }
            r0.put(r5, r6)     // Catch:{ all -> 0x00a7 }
        L_0x0045:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x00a7 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ all -> 0x00a7 }
            r3.add(r2)     // Catch:{ all -> 0x00a7 }
            goto L_0x0010
        L_0x0053:
            r1.close()
            java.util.Set r8 = r0.keySet()
            android.database.Cursor r8 = r7.queryAccountsOfRawContacts(r8)
        L_0x005e:
            boolean r1 = r8.moveToNext()     // Catch:{ all -> 0x00a2 }
            if (r1 == 0) goto L_0x009e
            com.android.contacts.model.account.AccountWithDataSet r1 = com.android.contacts.database.SimContactDaoImpl.AccountQuery.getAccount(r8)     // Catch:{ all -> 0x00a2 }
            long r2 = com.android.contacts.database.SimContactDaoImpl.AccountQuery.getId(r8)     // Catch:{ all -> 0x00a2 }
            boolean r4 = r9.containsKey(r1)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x007a
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ all -> 0x00a2 }
            r4.<init>()     // Catch:{ all -> 0x00a2 }
            r9.put(r1, r4)     // Catch:{ all -> 0x00a2 }
        L_0x007a:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x00a2 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x00a2 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x00a2 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00a2 }
        L_0x0088:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00a2 }
            if (r3 == 0) goto L_0x005e
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00a2 }
            com.android.contacts.model.SimContact r3 = (com.android.contacts.model.SimContact) r3     // Catch:{ all -> 0x00a2 }
            java.lang.Object r4 = r9.get(r1)     // Catch:{ all -> 0x00a2 }
            java.util.Set r4 = (java.util.Set) r4     // Catch:{ all -> 0x00a2 }
            r4.add(r3)     // Catch:{ all -> 0x00a2 }
            goto L_0x0088
        L_0x009e:
            r8.close()
            return
        L_0x00a2:
            r9 = move-exception
            r8.close()
            throw r9
        L_0x00a7:
            r8 = move-exception
            r1.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.database.SimContactDaoImpl.findAccountsOfExistingSimContacts(java.util.List, java.util.Map):void");
    }

    private ContentProviderResult[] importBatch(List<SimContact> list, AccountWithDataSet accountWithDataSet) throws RemoteException, OperationApplicationException {
        return this.mResolver.applyBatch("com.android.contacts", createImportOperations(list, accountWithDataSet));
    }

    @TargetApi(22)
    private List<SimCard> getSimCardsFromSubscriptions() {
        List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList();
        ArrayList arrayList = new ArrayList();
        for (SubscriptionInfo create : activeSubscriptionInfoList) {
            arrayList.add(SimCard.create(create));
        }
        return arrayList;
    }

    private List<SimContact> getContactsForSim(SimCard simCard) {
        List<SimContact> contacts = simCard.getContacts();
        return contacts != null ? contacts : loadContactsForSim(simCard);
    }

    private ArrayList<SimContact> loadFrom(Uri uri) {
        synchronized (SIM_READ_LOCK) {
            Cursor query = this.mResolver.query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query == null) {
                ArrayList<SimContact> arrayList = new ArrayList<>(0);
                return arrayList;
            }
            try {
                ArrayList<SimContact> loadFromCursor = loadFromCursor(query);
                return loadFromCursor;
            } finally {
                query.close();
            }
        }
    }

    private ArrayList<SimContact> loadFromCursor(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(_ID);
        int columnIndex2 = cursor.getColumnIndex(NAME);
        int columnIndex3 = cursor.getColumnIndex(NUMBER);
        int columnIndex4 = cursor.getColumnIndex(EMAILS);
        ArrayList<SimContact> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            SimContact simContact = new SimContact(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3), parseEmails(cursor.getString(columnIndex4)));
            if (simContact.hasName() || simContact.hasPhone() || simContact.hasEmails()) {
                arrayList.add(simContact);
            }
        }
        return arrayList;
    }

    private Cursor queryRawContactsForSimContacts(List<SimContact> list) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (SimContact next : list) {
            if (next.hasPhone()) {
                i++;
            } else if (next.hasName()) {
                i2++;
            }
        }
        ArrayList arrayList = new ArrayList(i + 1);
        sb.append('(');
        sb.append("mimetype");
        sb.append("=? AND ");
        arrayList.add("vnd.android.cursor.item/phone_v2");
        sb.append("data1");
        sb.append(" IN (");
        sb.append(Joiner.m6on(',').join((Iterable<?>) Collections.nCopies(i, '?')));
        sb.append(')');
        for (SimContact next2 : list) {
            if (next2.hasPhone()) {
                arrayList.add(next2.getPhone());
            }
        }
        sb.append(')');
        if (i2 > 0) {
            sb.append(" OR (");
            sb.append("mimetype");
            sb.append("=? AND ");
            arrayList.add("vnd.android.cursor.item/name");
            sb.append("display_name");
            sb.append(" IN (");
            sb.append(Joiner.m6on(',').join((Iterable<?>) Collections.nCopies(i2, '?')));
            sb.append(')');
            for (SimContact next3 : list) {
                if (!next3.hasPhone() && next3.hasName()) {
                    arrayList.add(next3.getName());
                }
            }
            sb.append(')');
        }
        return this.mResolver.query(ContactsContract.Data.CONTENT_URI.buildUpon().appendQueryParameter("visible_contacts_only", "true").build(), DataQuery.PROJECTION, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), (String) null);
    }

    private Cursor queryAccountsOfRawContacts(Set<Long> set) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = new String[set.size()];
        sb.append("_id");
        sb.append(" IN (");
        sb.append(Joiner.m6on(',').join((Iterable<?>) Collections.nCopies(strArr.length, '?')));
        sb.append(")");
        int i = 0;
        for (Long longValue : set) {
            strArr[i] = String.valueOf(longValue.longValue());
            i++;
        }
        return this.mResolver.query(ContactsContract.RawContacts.CONTENT_URI, AccountQuery.PROJECTION, sb.toString(), strArr, (String) null);
    }

    private ArrayList<ContentProviderOperation> createImportOperations(List<SimContact> list, AccountWithDataSet accountWithDataSet) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        for (SimContact appendCreateContactOperations : list) {
            appendCreateContactOperations.appendCreateContactOperations(arrayList, accountWithDataSet);
        }
        return arrayList;
    }

    private String[] parseEmails(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(",");
        }
        return null;
    }

    private boolean hasTelephony() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean hasPermissions() {
        return PermissionsUtil.hasContactsPermissions(this.mContext) && PermissionsUtil.hasPhonePermissions(this.mContext);
    }

    public static class DebugImpl extends SimContactDaoImpl {
        private SparseArray<SimCard> mCardsBySubscription = new SparseArray<>();
        private List<SimCard> mSimCards = new ArrayList();

        public boolean canReadSimContacts() {
            return true;
        }

        public DebugImpl(Context context) {
            super(context);
        }

        public DebugImpl addSimCard(SimCard simCard) {
            this.mSimCards.add(simCard);
            this.mCardsBySubscription.put(simCard.getSubscriptionId(), simCard);
            return this;
        }

        public List<SimCard> getSimCards() {
            return SharedPreferenceUtil.restoreSimStates(getContext(), this.mSimCards);
        }

        public ArrayList<SimContact> loadContactsForSim(SimCard simCard) {
            return new ArrayList<>(simCard.getContacts());
        }
    }

    private static final class DataQuery {
        public static final String[] PROJECTION = {"raw_contact_id", "data1", "display_name", "mimetype"};

        public static long getRawContactId(Cursor cursor) {
            return cursor.getLong(0);
        }

        public static String getPhoneNumber(Cursor cursor) {
            if (isPhoneNumber(cursor)) {
                return cursor.getString(1);
            }
            return null;
        }

        public static String getDisplayName(Cursor cursor) {
            return cursor.getString(2);
        }

        public static boolean isPhoneNumber(Cursor cursor) {
            return "vnd.android.cursor.item/phone_v2".equals(cursor.getString(3));
        }
    }

    private static final class AccountQuery {
        public static final String[] PROJECTION = {"_id", "account_name", "account_type", "data_set"};

        public static long getId(Cursor cursor) {
            return cursor.getLong(0);
        }

        public static AccountWithDataSet getAccount(Cursor cursor) {
            return new AccountWithDataSet(cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
    }
}
