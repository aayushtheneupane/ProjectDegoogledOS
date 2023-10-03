package com.android.p032ex.chips;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.ex.chips.k */
public class C0684k extends BaseAdapter implements Filterable, C0658a, C0706x {
    static final int ALLOWANCE_FOR_DUPLICATES = 5;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_PREFERRED_MAX_RESULT_COUNT = 10;
    private static final int MESSAGE_SEARCH_PENDING = 1;
    private static final int MESSAGE_SEARCH_PENDING_DELAY = 1000;
    static final String PRIMARY_ACCOUNT_NAME = "name_for_primary_account";
    static final String PRIMARY_ACCOUNT_TYPE = "type_for_primary_account";
    public static final int QUERY_TYPE_EMAIL = 0;
    public static final int QUERY_TYPE_PHONE = 1;
    private static final String TAG = "BaseRecipientAdapter";
    private Account mAccount;
    private final ContentResolver mContentResolver;
    /* access modifiers changed from: private */
    public final Context mContext;
    protected CharSequence mCurrentConstraint;
    /* access modifiers changed from: private */
    public final C0672e mDelayedMessageHandler;
    private C0704v mDropdownChipLayouter;
    private List mEntries;
    private C0680i mEntriesUpdatedObserver;
    /* access modifiers changed from: private */
    public LinkedHashMap mEntryMap;
    /* access modifiers changed from: private */
    public Set mExistingDestinations;
    /* access modifiers changed from: private */
    public List mNonAggregatedEntries;
    private C0686l mPermissionsCheckListener;
    private C0707y mPhotoManager;
    protected final int mPreferredMaxResultCount;
    private final C0634B mQueryMode;
    private final int mQueryType;
    /* access modifiers changed from: private */
    public int mRemainingDirectoryCount;
    protected boolean mShowRequestPermissionsItem;
    private List mTempEntries;

    public C0684k(Context context) {
        this(context, 10, 0);
    }

    static /* synthetic */ C0686l access$100(C0684k kVar) {
        return null;
    }

    static /* synthetic */ int access$1010(C0684k kVar) {
        int i = kVar.mRemainingDirectoryCount;
        kVar.mRemainingDirectoryCount = i - 1;
        return i;
    }

    /* access modifiers changed from: private */
    public Cursor doQuery(CharSequence charSequence, int i, Long l) {
        if (!C0688m.m1070g(this.mContext)) {
            return null;
        }
        Uri.Builder appendQueryParameter = this.mQueryMode.mo5440sd().buildUpon().appendPath(charSequence.toString()).appendQueryParameter("limit", String.valueOf(i + 5));
        if (l != null) {
            appendQueryParameter.appendQueryParameter("directory", String.valueOf(l));
        }
        Account account = this.mAccount;
        if (account != null) {
            appendQueryParameter.appendQueryParameter(PRIMARY_ACCOUNT_NAME, account.name);
            appendQueryParameter.appendQueryParameter(PRIMARY_ACCOUNT_TYPE, this.mAccount.type);
        }
        System.currentTimeMillis();
        Cursor query = this.mContentResolver.query(appendQueryParameter.build(), this.mQueryMode.getProjection(), (String) null, (String[]) null, (String) null);
        System.currentTimeMillis();
        return query;
    }

    public static List setupOtherDirectories(Context context, Cursor cursor, Account account) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        C0678h hVar = null;
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            if (j != 1) {
                C0678h hVar2 = new C0678h();
                String string = cursor.getString(4);
                int i = cursor.getInt(5);
                hVar2.f794Xu = j;
                cursor.getString(3);
                hVar2.f796Zu = cursor.getString(1);
                hVar2.accountType = cursor.getString(2);
                if (!(string == null || i == 0)) {
                    try {
                        hVar2.f795Yu = packageManager.getResourcesForApplication(string).getString(i);
                        if (hVar2.f795Yu == null) {
                            Log.e(TAG, "Cannot resolve directory name: " + i + "@" + string);
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e(TAG, "Cannot resolve directory name: " + i + "@" + string, e);
                    }
                }
                if (hVar != null || account == null || !account.name.equals(hVar2.f796Zu) || !account.type.equals(hVar2.accountType)) {
                    arrayList.add(hVar2);
                } else {
                    hVar = hVar2;
                }
            }
        }
        if (hVar != null) {
            arrayList.add(1, hVar);
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void cacheCurrentEntries() {
        this.mTempEntries = this.mEntries;
    }

    /* access modifiers changed from: protected */
    public void cacheCurrentEntriesIfNeeded(int i, int i2) {
        if (i == 0 && i2 > 1) {
            cacheCurrentEntries();
        }
    }

    /* access modifiers changed from: protected */
    public void clearTempEntries() {
        this.mTempEntries = null;
    }

    /* access modifiers changed from: protected */
    public List constructEntryList() {
        return constructEntryList(this.mEntryMap, this.mNonAggregatedEntries);
    }

    /* access modifiers changed from: protected */
    public void fetchPhoto(C0699ra raVar, C0706x xVar) {
        this.mPhotoManager.populatePhotoBytesAsync(raVar, xVar);
    }

    public boolean forceShowAddress() {
        return false;
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getCount() {
        List entries = getEntries();
        if (entries != null) {
            return entries.size();
        }
        return 0;
    }

    public C0704v getDropdownChipLayouter() {
        return this.mDropdownChipLayouter;
    }

    /* access modifiers changed from: protected */
    public List getEntries() {
        List list = this.mTempEntries;
        return list != null ? list : this.mEntries;
    }

    public Filter getFilter() {
        return new C0668c(this, (C0666b) null);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return ((C0699ra) getEntries().get(i)).getEntryType();
    }

    public Map getMatchingRecipients(Set set) {
        return null;
    }

    public void getMatchingRecipients(ArrayList arrayList, C0645M m) {
        C0646N.m1043a(getContext(), this, arrayList, getAccount(), m);
    }

    public C0686l getPermissionsCheckListener() {
        return null;
    }

    public C0707y getPhotoManager() {
        return this.mPhotoManager;
    }

    public int getQueryType() {
        return this.mQueryType;
    }

    public String[] getRequiredPermissions() {
        return C0688m.f805ev;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        C0699ra raVar = (C0699ra) getEntries().get(i);
        CharSequence charSequence = this.mCurrentConstraint;
        if (charSequence == null) {
            str = null;
        } else {
            str = charSequence.toString();
        }
        return this.mDropdownChipLayouter.bindView(view, viewGroup, raVar, i, DropdownChipLayouter$AdapterType.BASE_RECIPIENT, str);
    }

    public int getViewTypeCount() {
        return 2;
    }

    public boolean isEnabled(int i) {
        return ((C0699ra) getEntries().get(i)).isSelectable();
    }

    public void onPhotoBytesAsyncLoadFailed() {
    }

    public void onPhotoBytesAsynchronouslyPopulated() {
        notifyDataSetChanged();
    }

    public void onPhotoBytesPopulated() {
    }

    /* access modifiers changed from: protected */
    public void putOneEntry(C0682j jVar, boolean z) {
        putOneEntry(jVar, z, this.mEntryMap, this.mNonAggregatedEntries, this.mExistingDestinations);
    }

    public void registerUpdateObserver(C0680i iVar) {
        this.mEntriesUpdatedObserver = iVar;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.List, android.database.Cursor] */
    /* access modifiers changed from: protected */
    public List searchOtherDirectories(Set set) {
        ? r1 = 0;
        if (!C0688m.m1070g(this.mContext) || this.mPreferredMaxResultCount - set.size() <= 0) {
            return r1;
        }
        try {
            Cursor query = this.mContentResolver.query(C0676g.URI, C0676g.f793Wu, (String) null, (String[]) null, (String) null);
            List list = setupOtherDirectories(this.mContext, query, this.mAccount);
            query.close();
            return list;
        } catch (Throwable th) {
            if (r1 != 0) {
                r1.close();
            }
            throw th;
        }
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    public void setDropdownChipLayouter(C0704v vVar) {
        this.mDropdownChipLayouter = vVar;
        this.mDropdownChipLayouter.setQuery(this.mQueryMode);
    }

    public void setPermissionsCheckListener(C0686l lVar) {
    }

    public void setPhotoManager(C0707y yVar) {
        this.mPhotoManager = yVar;
    }

    public void setShowRequestPermissionsItem(boolean z) {
        this.mShowRequestPermissionsItem = z;
    }

    /* access modifiers changed from: protected */
    public void startSearchOtherDirectories(CharSequence charSequence, List list, int i) {
        int size = list.size();
        for (int i2 = 1; i2 < size; i2++) {
            C0678h hVar = (C0678h) list.get(i2);
            if (hVar.filter == null) {
                hVar.filter = new C0674f(this, hVar);
            }
            hVar.filter.setLimit(i);
            hVar.filter.filter(charSequence);
        }
        this.mRemainingDirectoryCount = size - 1;
        C0672e eVar = this.mDelayedMessageHandler;
        eVar.sendMessageDelayed(eVar.obtainMessage(1, 0, 0, (Object) null), 1000);
    }

    /* access modifiers changed from: protected */
    public void updateEntries(List list) {
        this.mEntries = list;
        ((C0656Y) this.mEntriesUpdatedObserver).mo5461f(list);
        notifyDataSetChanged();
    }

    public C0684k(Context context, int i) {
        this(context, i, 0);
    }

    /* access modifiers changed from: private */
    public List constructEntryList(LinkedHashMap linkedHashMap, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = linkedHashMap.entrySet().iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            List list2 = (List) ((Map.Entry) it.next()).getValue();
            int size = list2.size();
            int i2 = i;
            for (int i3 = 0; i3 < size; i3++) {
                C0699ra raVar = (C0699ra) list2.get(i3);
                arrayList.add(raVar);
                this.mPhotoManager.populatePhotoBytesAsync(raVar, this);
                i2++;
            }
            if (i2 > this.mPreferredMaxResultCount) {
                i = i2;
                break;
            }
            i = i2;
        }
        if (i <= this.mPreferredMaxResultCount) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                C0699ra raVar2 = (C0699ra) it2.next();
                if (i > this.mPreferredMaxResultCount) {
                    break;
                }
                arrayList.add(raVar2);
                this.mPhotoManager.populatePhotoBytesAsync(raVar2, this);
                i++;
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static void putOneEntry(C0682j jVar, boolean z, LinkedHashMap linkedHashMap, List list, Set set) {
        C0682j jVar2 = jVar;
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        Set set2 = set;
        if (!set2.contains(jVar2.f797Gj)) {
            set2.add(jVar2.f797Gj);
            if (!z) {
                list.add(C0699ra.m1082b(jVar2.displayName, jVar2.f803dv, jVar2.f797Gj, jVar2.f799_u, jVar2.f800av, jVar2.contactId, jVar2.f798Xu, jVar2.f801bv, jVar2.f802cv, true, jVar2.lookupKey));
            } else if (linkedHashMap2.containsKey(Long.valueOf(jVar2.contactId))) {
                ((List) linkedHashMap2.get(Long.valueOf(jVar2.contactId))).add(C0699ra.m1080a(jVar2.displayName, jVar2.f803dv, jVar2.f797Gj, jVar2.f799_u, jVar2.f800av, jVar2.contactId, jVar2.f798Xu, jVar2.f801bv, jVar2.f802cv, true, jVar2.lookupKey));
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(C0699ra.m1082b(jVar2.displayName, jVar2.f803dv, jVar2.f797Gj, jVar2.f799_u, jVar2.f800av, jVar2.contactId, jVar2.f798Xu, jVar2.f801bv, jVar2.f802cv, true, jVar2.lookupKey));
                linkedHashMap2.put(Long.valueOf(jVar2.contactId), arrayList);
            }
        }
    }

    public C0699ra getItem(int i) {
        return (C0699ra) getEntries().get(i);
    }

    public C0684k(int i, Context context) {
        this(context, 10, i);
    }

    public C0684k(int i, Context context, int i2) {
        this(context, i2, i);
    }

    public C0684k(Context context, int i, int i2) {
        this.mDelayedMessageHandler = new C0672e(this, (C0666b) null);
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mPreferredMaxResultCount = i;
        this.mPhotoManager = new C0696q(this.mContentResolver);
        this.mQueryType = i2;
        if (i2 == 0) {
            this.mQueryMode = C0635C.EMAIL;
        } else if (i2 == 1) {
            this.mQueryMode = C0635C.PHONE;
        } else {
            this.mQueryMode = C0635C.EMAIL;
            Log.e(TAG, "Unsupported query type: " + i2);
        }
    }
}
