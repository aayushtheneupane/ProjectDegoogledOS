package com.android.dialer.app.list;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.contacts.common.list.ContactEntry;
import com.android.contacts.common.list.ContactTileView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.function.Supplier;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.shortcuts.ShortcutRefresher;
import com.android.dialer.strictmode.StrictModeUtils;
import com.google.common.collect.ComparisonChain;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PhoneFavoritesTileAdapter extends BaseAdapter implements OnDragDropListener {
    private boolean awaitingRemove = false;
    private ArrayList<ContactEntry> contactEntries = null;
    private final Comparator<ContactEntry> contactEntryComparator = new Comparator<ContactEntry>() {
        private String getPreferredSortName(ContactEntry contactEntry) {
            return ContactsComponent.get(PhoneFavoritesTileAdapter.this.context).contactDisplayPreferences().getSortName(contactEntry.namePrimary, contactEntry.nameAlternative);
        }

        public int compare(Object obj, Object obj2) {
            ContactEntry contactEntry = (ContactEntry) obj;
            ContactEntry contactEntry2 = (ContactEntry) obj2;
            return ComparisonChain.start().compare(contactEntry.pinned, contactEntry2.pinned).compare((Comparable<?>) getPreferredSortName(contactEntry), (Comparable<?>) getPreferredSortName(contactEntry2)).result();
        }
    };
    /* access modifiers changed from: private */
    public Context context;
    private OnDataSetChangedForAnimationListener dataSetChangedListener;
    private boolean delayCursorUpdates = false;
    private int dragEnteredEntryIndex = -1;
    private ContactEntry draggedEntry = null;
    private int draggedEntryIndex = -1;
    private int dropEntryIndex = -1;
    private boolean inDragging = false;
    private ContactTileView.Listener listener;
    private int numFrequents;
    private int numStarred;
    private ContactPhotoManager photoManager;
    private Resources resources;

    interface OnDataSetChangedForAnimationListener {
    }

    public PhoneFavoritesTileAdapter(Context context2, ContactTileView.Listener listener2, OnDataSetChangedForAnimationListener onDataSetChangedForAnimationListener) {
        this.dataSetChangedListener = onDataSetChangedForAnimationListener;
        this.listener = listener2;
        this.context = context2;
        this.resources = context2.getResources();
        this.numFrequents = 0;
        this.contactEntries = new ArrayList<>();
    }

    private void arrangeContactsByPinnedPosition(ArrayList<ContactEntry> arrayList) {
        PriorityQueue priorityQueue = new PriorityQueue(21, this.contactEntryComparator);
        LinkedList linkedList = new LinkedList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ContactEntry contactEntry = arrayList.get(i);
            int i2 = contactEntry.pinned;
            if (i2 > 21 || i2 == 0) {
                linkedList.add(contactEntry);
            } else if (i2 > -1) {
                priorityQueue.add(contactEntry);
            }
        }
        int min = Math.min(21, linkedList.size() + priorityQueue.size());
        arrayList.clear();
        for (int i3 = 1; i3 < min + 1; i3++) {
            if (!priorityQueue.isEmpty() && ((ContactEntry) priorityQueue.peek()).pinned <= i3) {
                ContactEntry contactEntry2 = (ContactEntry) priorityQueue.poll();
                contactEntry2.pinned = i3;
                arrayList.add(contactEntry2);
            } else if (!linkedList.isEmpty()) {
                arrayList.add((ContactEntry) linkedList.remove(0));
            }
        }
        while (!priorityQueue.isEmpty()) {
            ContactEntry contactEntry3 = (ContactEntry) priorityQueue.poll();
            contactEntry3.pinned = 0;
            arrayList.add(contactEntry3);
        }
        arrayList.addAll(linkedList);
    }

    private ArrayList<ContentProviderOperation> getReflowedPinningOperations(ArrayList<ContactEntry> arrayList, int i, int i2) {
        ArrayList<ContentProviderOperation> arrayList2 = new ArrayList<>();
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        while (min <= max) {
            ContactEntry contactEntry = arrayList.get(min);
            min++;
            if (contactEntry.pinned != min) {
                Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactEntry.f5id));
                ContentValues contentValues = new ContentValues();
                contentValues.put("pinned", Integer.valueOf(min));
                arrayList2.add(ContentProviderOperation.newUpdate(withAppendedPath).withValues(contentValues).build());
            }
        }
        return arrayList2;
    }

    private void markDropArea(int i) {
        if (this.draggedEntry != null && isIndexInBound(this.dragEnteredEntryIndex) && isIndexInBound(i)) {
            ((OldSpeedDialFragment) this.dataSetChangedListener).cacheOffsetsForDatasetChange();
            this.contactEntries.remove(this.dragEnteredEntryIndex);
            this.dragEnteredEntryIndex = i;
            this.contactEntries.add(this.dragEnteredEntryIndex, ContactEntry.BLANK_ENTRY);
            ContactEntry.BLANK_ENTRY.f5id = this.draggedEntry.f5id;
            ((OldSpeedDialFragment) this.dataSetChangedListener).onDataSetChangedForAnimation(new long[0]);
            notifyDataSetChanged();
        }
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public int getCount() {
        ArrayList<ContactEntry> arrayList = this.contactEntries;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public long getItemId(int i) {
        return getItem(i).f5id;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNumFrequents() {
        return this.numFrequents;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PhoneFavoriteTileView phoneFavoriteTileView = view instanceof PhoneFavoriteTileView ? (PhoneFavoriteTileView) view : null;
        if (phoneFavoriteTileView == null) {
            phoneFavoriteTileView = (PhoneFavoriteTileView) View.inflate(this.context, R.layout.phone_favorite_tile_view, (ViewGroup) null);
        }
        phoneFavoriteTileView.setPhotoManager(this.photoManager);
        phoneFavoriteTileView.setListener(this.listener);
        phoneFavoriteTileView.loadFromContact(getItem(i));
        phoneFavoriteTileView.setPosition(i);
        return phoneFavoriteTileView;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEnabled(int i) {
        return getCount() > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isIndexInBound(int i) {
        return i >= 0 && i < this.contactEntries.size();
    }

    public /* synthetic */ void lambda$handleDrop$0$PhoneFavoritesTileAdapter(ArrayList arrayList) {
        if (!arrayList.isEmpty()) {
            try {
                this.context.getContentResolver().applyBatch("com.android.contacts", arrayList);
                ((LoggingBindingsStub) Logger.get(this.context)).logInteraction(InteractionEvent$Type.SPEED_DIAL_PIN_CONTACT);
            } catch (OperationApplicationException | RemoteException e) {
                LogUtil.m7e("PhoneFavoritesTileAdapter", "Exception thrown when pinning contacts", e);
            }
        }
    }

    public /* synthetic */ Integer lambda$unstarAndUnpinContact$1$PhoneFavoritesTileAdapter(Uri uri, ContentValues contentValues) {
        return Integer.valueOf(this.context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null));
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void onDragFinished(int i, int i2) {
        int i3;
        int i4;
        boolean z = false;
        this.delayCursorUpdates = false;
        this.inDragging = false;
        if (!this.awaitingRemove && this.draggedEntry != null) {
            if (isIndexInBound(this.dragEnteredEntryIndex) && (i4 = this.dragEnteredEntryIndex) != this.draggedEntryIndex) {
                this.dropEntryIndex = i4;
                this.contactEntries.set(this.dropEntryIndex, this.draggedEntry);
                ((OldSpeedDialFragment) this.dataSetChangedListener).cacheOffsetsForDatasetChange();
                z = true;
            } else if (isIndexInBound(this.draggedEntryIndex)) {
                this.contactEntries.remove(this.dragEnteredEntryIndex);
                this.contactEntries.add(this.draggedEntryIndex, this.draggedEntry);
                this.dropEntryIndex = this.draggedEntryIndex;
                notifyDataSetChanged();
            }
            if (z && (i3 = this.dropEntryIndex) < 21) {
                StrictModeUtils.bypass((Runnable) new Runnable(getReflowedPinningOperations(this.contactEntries, this.draggedEntryIndex, i3)) {
                    private final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        PhoneFavoritesTileAdapter.this.lambda$handleDrop$0$PhoneFavoritesTileAdapter(this.f$1);
                    }
                });
            }
            this.draggedEntry = null;
        }
    }

    public void onDragHovered(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
        if (phoneFavoriteSquareTileView != null) {
            int indexOf = this.contactEntries.indexOf(phoneFavoriteSquareTileView.getContactEntry());
            if (this.inDragging && this.dragEnteredEntryIndex != indexOf && isIndexInBound(indexOf) && indexOf < 21 && indexOf >= 0) {
                markDropArea(indexOf);
            }
        }
    }

    public void onDragStarted(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
        this.delayCursorUpdates = true;
        this.inDragging = true;
        int indexOf = this.contactEntries.indexOf(phoneFavoriteSquareTileView.getContactEntry());
        if (isIndexInBound(indexOf)) {
            this.draggedEntry = this.contactEntries.get(indexOf);
            this.draggedEntryIndex = indexOf;
            this.dragEnteredEntryIndex = indexOf;
            markDropArea(this.dragEnteredEntryIndex);
        }
    }

    public void onDroppedOnRemove() {
        ContactEntry contactEntry = this.draggedEntry;
        if (contactEntry != null) {
            Uri uri = contactEntry.lookupUri;
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("starred", false);
            contentValues.put("pinned", -1);
            StrictModeUtils.bypass(new Supplier(uri, contentValues) {
                private final /* synthetic */ Uri f$1;
                private final /* synthetic */ ContentValues f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object get() {
                    return PhoneFavoritesTileAdapter.this.lambda$unstarAndUnpinContact$1$PhoneFavoritesTileAdapter(this.f$1, this.f$2);
                }
            });
            this.awaitingRemove = true;
            ((LoggingBindingsStub) Logger.get(this.context)).logInteraction(InteractionEvent$Type.SPEED_DIAL_REMOVE_CONTACT);
        }
    }

    /* access modifiers changed from: package-private */
    public void setContactCursor(Cursor cursor) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z2;
        boolean z3;
        String str;
        Cursor cursor2 = cursor;
        if (!this.delayCursorUpdates && cursor2 != null && !cursor.isClosed()) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor2.getColumnIndex("starred");
                while (true) {
                    if (cursor2.getInt(columnIndex) != 0) {
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } else {
                        i = cursor.getPosition();
                        break;
                    }
                }
                i = cursor.getCount();
            } else {
                i = cursor.getCount();
            }
            this.numStarred = i;
            if (this.awaitingRemove) {
                ((OldSpeedDialFragment) this.dataSetChangedListener).cacheOffsetsForDatasetChange();
            }
            this.numFrequents = cursor.getCount() - this.numStarred;
            this.contactEntries.clear();
            LongSparseArray longSparseArray = new LongSparseArray(cursor.getCount());
            int i14 = 1;
            if (cursor.moveToFirst()) {
                int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("starred");
                int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow("contact_id");
                int columnIndexOrThrow3 = cursor2.getColumnIndexOrThrow("photo_uri");
                int columnIndexOrThrow4 = cursor2.getColumnIndexOrThrow("lookup");
                int columnIndexOrThrow5 = cursor2.getColumnIndexOrThrow("pinned");
                int columnIndexOrThrow6 = cursor2.getColumnIndexOrThrow("display_name");
                int columnIndexOrThrow7 = cursor2.getColumnIndexOrThrow("display_name_alt");
                int columnIndexOrThrow8 = cursor2.getColumnIndexOrThrow("is_super_primary");
                int columnIndexOrThrow9 = cursor2.getColumnIndexOrThrow("data2");
                int columnIndexOrThrow10 = cursor2.getColumnIndexOrThrow("data3");
                int columnIndexOrThrow11 = cursor2.getColumnIndexOrThrow("data1");
                int i15 = 0;
                int i16 = 0;
                i4 = 0;
                i3 = 0;
                i2 = 0;
                while (true) {
                    if (cursor2.getInt(columnIndexOrThrow) < i14 && i15 >= 20) {
                        break;
                    }
                    int i17 = i15;
                    long j = cursor2.getLong(columnIndexOrThrow2);
                    ContactEntry contactEntry = (ContactEntry) longSparseArray.get(j);
                    int i18 = columnIndexOrThrow2;
                    if (contactEntry != null) {
                        if (!contactEntry.isDefaultNumber) {
                            contactEntry.phoneNumber = null;
                        }
                        i11 = columnIndexOrThrow;
                        i12 = columnIndexOrThrow3;
                        i10 = columnIndexOrThrow4;
                        i9 = columnIndexOrThrow5;
                        i7 = columnIndexOrThrow6;
                        i8 = columnIndexOrThrow7;
                        i13 = columnIndexOrThrow11;
                        i15 = i17;
                    } else {
                        String string = cursor2.getString(columnIndexOrThrow3);
                        String string2 = cursor2.getString(columnIndexOrThrow4);
                        i12 = columnIndexOrThrow3;
                        int i19 = cursor2.getInt(columnIndexOrThrow5);
                        String string3 = cursor2.getString(columnIndexOrThrow6);
                        String string4 = cursor2.getString(columnIndexOrThrow7);
                        if (cursor2.getInt(columnIndexOrThrow) > 0) {
                            i11 = columnIndexOrThrow;
                            z2 = true;
                        } else {
                            i11 = columnIndexOrThrow;
                            z2 = false;
                        }
                        if (cursor2.getInt(columnIndexOrThrow8) > 0) {
                            i10 = columnIndexOrThrow4;
                            i9 = columnIndexOrThrow5;
                            z3 = true;
                        } else {
                            i10 = columnIndexOrThrow4;
                            i9 = columnIndexOrThrow5;
                            z3 = false;
                        }
                        ContactEntry contactEntry2 = new ContactEntry();
                        contactEntry2.f5id = j;
                        i7 = columnIndexOrThrow6;
                        if (!TextUtils.isEmpty(string3)) {
                            i8 = columnIndexOrThrow7;
                            str = string3;
                        } else {
                            i8 = columnIndexOrThrow7;
                            str = this.resources.getString(R.string.missing_name);
                        }
                        contactEntry2.namePrimary = str;
                        if (TextUtils.isEmpty(string4)) {
                            string4 = this.resources.getString(R.string.missing_name);
                        }
                        contactEntry2.nameAlternative = string4;
                        contactEntry2.photoUri = string != null ? Uri.parse(string) : null;
                        contactEntry2.lookupKey = string2;
                        contactEntry2.lookupUri = ContentUris.withAppendedId(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, string2), j);
                        contactEntry2.isFavorite = z2;
                        contactEntry2.isDefaultNumber = z3;
                        String str2 = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(this.resources, cursor2.getInt(columnIndexOrThrow9), cursor2.getString(columnIndexOrThrow10));
                        i13 = columnIndexOrThrow11;
                        contactEntry2.phoneNumber = cursor2.getString(i13);
                        contactEntry2.pinned = i19;
                        this.contactEntries.add(contactEntry2);
                        if (z2) {
                            i16++;
                        }
                        if (i19 != 0) {
                            i4++;
                        }
                        if (!TextUtils.isEmpty(string3)) {
                            i2++;
                        }
                        if (string != null) {
                            i3++;
                        }
                        longSparseArray.put(j, contactEntry2);
                        i15 = i17 + 1;
                    }
                    if (!cursor.moveToNext()) {
                        break;
                    }
                    columnIndexOrThrow11 = i13;
                    columnIndexOrThrow2 = i18;
                    columnIndexOrThrow3 = i12;
                    columnIndexOrThrow = i11;
                    columnIndexOrThrow4 = i10;
                    columnIndexOrThrow5 = i9;
                    columnIndexOrThrow7 = i8;
                    columnIndexOrThrow6 = i7;
                    i14 = 1;
                }
                i6 = i15;
                i5 = i16;
                z = false;
            } else {
                i6 = 0;
                z = false;
                i5 = 0;
                i4 = 0;
                i3 = 0;
                i2 = 0;
            }
            this.awaitingRemove = z;
            arrangeContactsByPinnedPosition(this.contactEntries);
            ShortcutRefresher.refresh(this.context, this.contactEntries);
            notifyDataSetChanged();
            Duo duo = DuoComponent.get(this.context).getDuo();
            Iterator<ContactEntry> it = this.contactEntries.iterator();
            int i20 = 0;
            while (it.hasNext()) {
                String str3 = it.next().phoneNumber;
                if (str3 == null) {
                    i20++;
                } else {
                    ((DuoStub) duo).isReachable(this.context, str3);
                }
            }
            ((LoggingBindingsStub) Logger.get(this.context)).logSpeedDialContactComposition(i6, i5, i4, i20, i3, i2, 0);
            new Object[1][0] = Integer.valueOf(i6);
            new Object[1][0] = Integer.valueOf(i5);
            new Object[1][0] = Integer.valueOf(i4);
            new Object[1][0] = Integer.valueOf(i20);
            new Object[1][0] = Integer.valueOf(i3);
            new Object[1][0] = Integer.valueOf(i2);
            notifyDataSetChanged();
            ((OldSpeedDialFragment) this.dataSetChangedListener).onDataSetChangedForAnimation(new long[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void setPhotoLoader(ContactPhotoManager contactPhotoManager) {
        this.photoManager = contactPhotoManager;
    }

    public ContactEntry getItem(int i) {
        return this.contactEntries.get(i);
    }
}
