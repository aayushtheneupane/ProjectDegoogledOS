package com.android.contacts.group;

import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.android.contacts.ContactSaveService;
import com.android.contacts.ContactsUtils;
import com.android.contacts.GroupMetaDataLoader;
import com.android.contacts.R;
import com.android.contacts.activities.ActionBarAdapter;
import com.android.contacts.activities.PeopleActivity;
import com.android.contacts.interactions.GroupDeletionDialogFragment;
import com.android.contacts.list.ContactsRequest;
import com.android.contacts.list.ContactsSectionIndexer;
import com.android.contacts.list.MultiSelectContactsListFragment;
import com.android.contacts.list.MultiSelectEntryContactListAdapter;
import com.android.contacts.logging.Logger;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.ImplicitIntentsUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupMembersFragment extends MultiSelectContactsListFragment<GroupMembersAdapter> {
    /* access modifiers changed from: private */
    public ActionBarAdapter mActionBarAdapter;
    private final ActionBarAdapter.Listener mActionBarListener = new ActionBarAdapter.Listener() {
        public void onAction(int i) {
            if (i == 2) {
                if (GroupMembersFragment.this.mIsEditMode) {
                    GroupMembersFragment.this.displayDeleteButtons(true);
                    GroupMembersFragment.this.mActionBarAdapter.setActionBarTitle(GroupMembersFragment.this.getString(R.string.title_edit_group));
                } else {
                    GroupMembersFragment.this.displayCheckBoxes(true);
                }
                GroupMembersFragment.this.mActivity.invalidateOptionsMenu();
            } else if (i == 3) {
                GroupMembersFragment.this.mActionBarAdapter.setSearchMode(false);
                if (GroupMembersFragment.this.mIsEditMode) {
                    GroupMembersFragment.this.displayDeleteButtons(false);
                } else {
                    GroupMembersFragment.this.displayCheckBoxes(false);
                }
                GroupMembersFragment.this.mActivity.invalidateOptionsMenu();
            }
        }

        public void onUpButtonPressed() {
            GroupMembersFragment.this.mActivity.onBackPressed();
        }
    };
    /* access modifiers changed from: private */
    public PeopleActivity mActivity;
    private final MultiSelectContactsListFragment.OnCheckBoxListActionListener mCheckBoxListener = new MultiSelectContactsListFragment.OnCheckBoxListActionListener() {
        public void onStartDisplayingCheckBoxes() {
            GroupMembersFragment.this.mActionBarAdapter.setSelectionMode(true);
        }

        public void onSelectedContactIdsChanged() {
            if (GroupMembersFragment.this.mActionBarAdapter != null) {
                if (GroupMembersFragment.this.mIsEditMode) {
                    GroupMembersFragment.this.mActionBarAdapter.setActionBarTitle(GroupMembersFragment.this.getString(R.string.title_edit_group));
                } else {
                    GroupMembersFragment.this.mActionBarAdapter.setSelectionCount(GroupMembersFragment.this.getSelectedContactIds().size());
                }
            }
        }

        public void onStopDisplayingCheckBoxes() {
            GroupMembersFragment.this.mActionBarAdapter.setSelectionMode(false);
        }
    };
    /* access modifiers changed from: private */
    public Set<String> mGroupMemberContactIds = new HashSet();
    /* access modifiers changed from: private */
    public GroupMetaData mGroupMetaData;
    private final LoaderManager.LoaderCallbacks<Cursor> mGroupMetaDataCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public CursorLoader onCreateLoader(int i, Bundle bundle) {
            return new GroupMetaDataLoader(GroupMembersFragment.this.mActivity, GroupMembersFragment.this.mGroupUri);
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            if (cursor == null || cursor.isClosed() || !cursor.moveToNext()) {
                Log.e("GroupMembers", "Failed to load group metadata for " + GroupMembersFragment.this.mGroupUri);
                Toast.makeText(GroupMembersFragment.this.getContext(), R.string.groupLoadErrorToast, 0).show();
                GroupMembersFragment.this.mHandler.sendEmptyMessage(1);
                return;
            }
            GroupMembersFragment groupMembersFragment = GroupMembersFragment.this;
            GroupMetaData unused = groupMembersFragment.mGroupMetaData = new GroupMetaData((Context) groupMembersFragment.getActivity(), cursor);
            GroupMembersFragment.this.onGroupMetadataLoaded();
        }
    };
    /* access modifiers changed from: private */
    public Uri mGroupUri;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                GroupMembersFragment.this.mActivity.onBackPressed();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsEditMode;

    public static abstract class Query {
        public static final String[] EMAIL_PROJECTION = {"contact_id", "_id", "is_super_primary", "data1"};
        public static final String[] PHONE_PROJECTION = {"contact_id", "_id", "is_super_primary", "data1"};
    }

    private class FilterCursorWrapper extends CursorWrapper {
        private int mCount;
        private int[] mIndex;
        private int mPos;

        public FilterCursorWrapper(Cursor cursor) {
            super(cursor);
            int i;
            this.mCount = 0;
            this.mPos = 0;
            this.mCount = super.getCount();
            this.mIndex = new int[this.mCount];
            ArrayList arrayList = new ArrayList();
            if (Log.isLoggable("GroupMembers", 2)) {
                Log.v("GroupMembers", "Group members CursorWrapper start: " + this.mCount);
            }
            Bundle extras = cursor.getExtras();
            String[] stringArray = extras.getStringArray("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES");
            int[] intArray = extras.getIntArray("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS");
            ContactsSectionIndexer contactsSectionIndexer = (stringArray == null || intArray == null) ? null : new ContactsSectionIndexer(stringArray, intArray);
            GroupMembersFragment.this.mGroupMemberContactIds.clear();
            int i2 = 0;
            while (true) {
                i = this.mCount;
                if (i2 >= i) {
                    break;
                }
                super.moveToPosition(i2);
                String string = getString(0);
                if (!GroupMembersFragment.this.mGroupMemberContactIds.contains(string)) {
                    int[] iArr = this.mIndex;
                    int i3 = this.mPos;
                    this.mPos = i3 + 1;
                    iArr[i3] = i2;
                    GroupMembersFragment.this.mGroupMemberContactIds.add(string);
                } else {
                    arrayList.add(Integer.valueOf(i2));
                }
                i2++;
            }
            if (contactsSectionIndexer != null && GroupUtil.needTrimming(i, intArray, contactsSectionIndexer.getPositions())) {
                GroupUtil.updateBundle(extras, contactsSectionIndexer, arrayList, stringArray, intArray);
            }
            this.mCount = this.mPos;
            this.mPos = 0;
            super.moveToFirst();
            if (Log.isLoggable("GroupMembers", 2)) {
                Log.v("GroupMembers", "Group members CursorWrapper end: " + this.mCount);
            }
        }

        public boolean move(int i) {
            return moveToPosition(this.mPos + i);
        }

        public boolean moveToNext() {
            return moveToPosition(this.mPos + 1);
        }

        public boolean moveToPrevious() {
            return moveToPosition(this.mPos - 1);
        }

        public boolean moveToFirst() {
            return moveToPosition(0);
        }

        public boolean moveToLast() {
            return moveToPosition(this.mCount - 1);
        }

        public boolean moveToPosition(int i) {
            int i2 = this.mCount;
            if (i >= i2) {
                this.mPos = i2;
                return false;
            } else if (i < 0) {
                this.mPos = -1;
                return false;
            } else {
                this.mPos = this.mIndex[i];
                return super.moveToPosition(this.mPos);
            }
        }

        public int getCount() {
            return this.mCount;
        }

        public int getPosition() {
            return this.mPos;
        }
    }

    public static GroupMembersFragment newInstance(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("groupUri", uri);
        GroupMembersFragment groupMembersFragment = new GroupMembersFragment();
        groupMembersFragment.setArguments(bundle);
        return groupMembersFragment;
    }

    public GroupMembersFragment() {
        setPhotoLoaderEnabled(true);
        setSectionHeaderDisplayEnabled(true);
        setHasOptionsMenu(true);
        setListType(3);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mGroupMetaData != null) {
            menuInflater.inflate(R.menu.view_group, menu);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        boolean isSelectionMode = this.mActionBarAdapter.isSelectionMode();
        GroupMetaData groupMetaData = this.mGroupMetaData;
        boolean z = true;
        boolean z2 = groupMetaData != null && groupMetaData.editable;
        GroupMetaData groupMetaData2 = this.mGroupMetaData;
        boolean z3 = groupMetaData2 != null && groupMetaData2.readOnly;
        setVisible(getContext(), menu, R.id.menu_multi_send_email, !this.mIsEditMode && !isGroupEmpty());
        setVisible(getContext(), menu, R.id.menu_multi_send_message, !this.mIsEditMode && !isGroupEmpty());
        setVisible(getContext(), menu, R.id.menu_add, z2 && !isSelectionMode);
        setVisible(getContext(), menu, R.id.menu_rename_group, !z3 && !isSelectionMode);
        setVisible(getContext(), menu, R.id.menu_delete_group, !z3 && !isSelectionMode);
        setVisible(getContext(), menu, R.id.menu_edit_group, z2 && !this.mIsEditMode && !isSelectionMode && !isGroupEmpty());
        Context context = getContext();
        if (!z2 || !isSelectionMode || this.mIsEditMode) {
            z = false;
        }
        setVisible(context, menu, R.id.menu_remove_from_group, z);
    }

    private boolean isGroupEmpty() {
        return getAdapter() != null && ((GroupMembersAdapter) getAdapter()).isEmpty();
    }

    private static void setVisible(Context context, Menu menu, int i, boolean z) {
        MenuItem findItem = menu.findItem(i);
        if (findItem != null) {
            findItem.setVisible(z);
            Drawable icon = findItem.getIcon();
            if (icon != null) {
                icon.mutate().setColorFilter(ContextCompat.getColor(context, R.color.actionbar_icon_color), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    private class ContactDataHelperClass {
        private String firstItemId;
        private List<String> items;
        private String primaryItemId;

        private ContactDataHelperClass() {
            this.items = new ArrayList();
            this.firstItemId = null;
            this.primaryItemId = null;
        }

        public void addItem(String str, boolean z) {
            if (this.firstItemId == null) {
                this.firstItemId = str;
            }
            if (z) {
                this.primaryItemId = str;
            }
            this.items.add(str);
        }

        public boolean hasDefaultItem() {
            return this.primaryItemId != null || this.items.size() == 1;
        }

        public String getDefaultSelectionItemId() {
            String str = this.primaryItemId;
            return str != null ? str : this.firstItemId;
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: com.android.contacts.group.GroupMembersFragment$ContactDataHelperClass} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendToGroup(long[] r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = this;
            if (r12 == 0) goto L_0x013c
            int r0 = r12.length
            if (r0 != 0) goto L_0x0007
            goto L_0x013c
        L_0x0007:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = com.android.contacts.group.GroupUtil.convertArrayToString(r12)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "mailto"
            boolean r5 = r4.equals(r13)
            if (r5 == 0) goto L_0x0025
            java.lang.String r5 = "mimetype='vnd.android.cursor.item/email_v2'"
            goto L_0x0027
        L_0x0025:
            java.lang.String r5 = "mimetype='vnd.android.cursor.item/phone_v2'"
        L_0x0027:
            r3.append(r5)
            java.lang.String r5 = " AND "
            r3.append(r5)
            java.lang.String r5 = "contact_id"
            r3.append(r5)
            java.lang.String r5 = " IN ("
            r3.append(r5)
            r3.append(r2)
            java.lang.String r2 = ")"
            r3.append(r2)
            java.lang.String r8 = r3.toString()
            android.content.Context r2 = r11.getContext()
            android.content.ContentResolver r5 = r2.getContentResolver()
            android.net.Uri r6 = android.provider.ContactsContract.Data.CONTENT_URI
            boolean r2 = r4.equals(r13)
            if (r2 == 0) goto L_0x0058
            java.lang.String[] r2 = com.android.contacts.group.GroupMembersFragment.Query.EMAIL_PROJECTION
            goto L_0x005a
        L_0x0058:
            java.lang.String[] r2 = com.android.contacts.group.GroupMembersFragment.Query.PHONE_PROJECTION
        L_0x005a:
            r7 = r2
            r9 = 0
            r10 = 0
            android.database.Cursor r2 = r5.query(r6, r7, r8, r9, r10)
            if (r2 != 0) goto L_0x0064
            return
        L_0x0064:
            r3 = -1
            r2.moveToPosition(r3)     // Catch:{ all -> 0x0137 }
        L_0x0068:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x0137 }
            r5 = 1
            if (r3 == 0) goto L_0x00a9
            r3 = 0
            java.lang.String r6 = r2.getString(r3)     // Catch:{ all -> 0x0137 }
            java.lang.String r7 = r2.getString(r5)     // Catch:{ all -> 0x0137 }
            r8 = 2
            int r8 = r2.getInt(r8)     // Catch:{ all -> 0x0137 }
            if (r8 == 0) goto L_0x0080
            r3 = 1
        L_0x0080:
            r5 = 3
            java.lang.String r5 = r2.getString(r5)     // Catch:{ all -> 0x0137 }
            boolean r8 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0137 }
            if (r8 != 0) goto L_0x0068
            boolean r8 = r0.containsKey(r6)     // Catch:{ all -> 0x0137 }
            if (r8 != 0) goto L_0x009b
            com.android.contacts.group.GroupMembersFragment$ContactDataHelperClass r8 = new com.android.contacts.group.GroupMembersFragment$ContactDataHelperClass     // Catch:{ all -> 0x0137 }
            r9 = 0
            r8.<init>()     // Catch:{ all -> 0x0137 }
            r0.put(r6, r8)     // Catch:{ all -> 0x0137 }
            goto L_0x00a2
        L_0x009b:
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0137 }
            r8 = r6
            com.android.contacts.group.GroupMembersFragment$ContactDataHelperClass r8 = (com.android.contacts.group.GroupMembersFragment.ContactDataHelperClass) r8     // Catch:{ all -> 0x0137 }
        L_0x00a2:
            r8.addItem(r7, r3)     // Catch:{ all -> 0x0137 }
            r1.add(r5)     // Catch:{ all -> 0x0137 }
            goto L_0x0068
        L_0x00a9:
            r2.close()
            java.util.Collection r2 = r0.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x00b4:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00f9
            java.lang.Object r3 = r2.next()
            com.android.contacts.group.GroupMembersFragment$ContactDataHelperClass r3 = (com.android.contacts.group.GroupMembersFragment.ContactDataHelperClass) r3
            boolean r3 = r3.hasDefaultItem()
            if (r3 != 0) goto L_0x00b4
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x00d3:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00f1
            java.lang.Object r2 = r0.next()
            com.android.contacts.group.GroupMembersFragment$ContactDataHelperClass r2 = (com.android.contacts.group.GroupMembersFragment.ContactDataHelperClass) r2
            java.lang.String r2 = r2.getDefaultSelectionItemId()
            if (r2 == 0) goto L_0x00d3
            long r2 = java.lang.Long.parseLong(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r1.add(r2)
            goto L_0x00d3
        L_0x00f1:
            long[] r0 = com.google.common.primitives.Longs.toArray(r1)
            r11.startSendToSelectionPickerActivity(r12, r0, r13, r14)
            return
        L_0x00f9:
            int r2 = r1.size()
            if (r2 == 0) goto L_0x0106
            int r0 = r0.size()
            int r12 = r12.length
            if (r0 >= r12) goto L_0x0126
        L_0x0106:
            android.content.Context r12 = r11.getContext()
            boolean r0 = r4.equals(r13)
            if (r0 == 0) goto L_0x0118
            r0 = 2131755375(0x7f10016f, float:1.9141628E38)
            java.lang.String r0 = r11.getString(r0)
            goto L_0x011f
        L_0x0118:
            r0 = 2131755376(0x7f100170, float:1.914163E38)
            java.lang.String r0 = r11.getString(r0)
        L_0x011f:
            android.widget.Toast r12 = android.widget.Toast.makeText(r12, r0, r5)
            r12.show()
        L_0x0126:
            int r12 = r1.size()
            if (r12 != 0) goto L_0x012d
            return
        L_0x012d:
            java.lang.String r12 = ","
            java.lang.String r12 = android.text.TextUtils.join(r12, r1)
            com.android.contacts.group.GroupUtil.startSendToSelectionActivity(r11, r12, r13, r14)
            return
        L_0x0137:
            r12 = move-exception
            r2.close()
            throw r12
        L_0x013c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.group.GroupMembersFragment.sendToGroup(long[], java.lang.String, java.lang.String):void");
    }

    private void startSendToSelectionPickerActivity(long[] jArr, long[] jArr2, String str, String str2) {
        startActivity(GroupUtil.createSendToSelectionPickerIntent(getContext(), jArr, jArr2, str, str2));
    }

    private void startGroupAddMemberActivity() {
        startActivityForResult(GroupUtil.createPickMemberIntent(getContext(), this.mGroupMetaData, getMemberContactIds()), 100);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        long[] jArr;
        long[] jArr2;
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            this.mActivity.onBackPressed();
        } else if (itemId == R.id.menu_add) {
            startGroupAddMemberActivity();
        } else if (itemId == R.id.menu_multi_send_email) {
            if (this.mActionBarAdapter.isSelectionMode()) {
                jArr2 = ((GroupMembersAdapter) getAdapter()).getSelectedContactIdsArray();
            } else {
                jArr2 = GroupUtil.convertStringSetToLongArray(this.mGroupMemberContactIds);
            }
            sendToGroup(jArr2, ContactsUtils.SCHEME_MAILTO, getString(R.string.menu_sendEmailOption));
        } else if (itemId == R.id.menu_multi_send_message) {
            if (this.mActionBarAdapter.isSelectionMode()) {
                jArr = ((GroupMembersAdapter) getAdapter()).getSelectedContactIdsArray();
            } else {
                jArr = GroupUtil.convertStringSetToLongArray(this.mGroupMemberContactIds);
            }
            sendToGroup(jArr, ContactsUtils.SCHEME_SMSTO, getString(R.string.menu_sendMessageOption));
        } else if (itemId == R.id.menu_rename_group) {
            GroupMetaData groupMetaData = this.mGroupMetaData;
            AccountWithDataSet accountWithDataSet = new AccountWithDataSet(groupMetaData.accountName, groupMetaData.accountType, groupMetaData.dataSet);
            GroupMetaData groupMetaData2 = this.mGroupMetaData;
            GroupNameEditDialogFragment.newInstanceForUpdate(accountWithDataSet, "updateGroup", groupMetaData2.groupId, groupMetaData2.groupName).show(getFragmentManager(), "groupNameEditDialog");
        } else if (itemId == R.id.menu_delete_group) {
            deleteGroup();
        } else if (itemId == R.id.menu_edit_group) {
            this.mIsEditMode = true;
            this.mActionBarAdapter.setSelectionMode(true);
            displayDeleteButtons(true);
        } else if (itemId != R.id.menu_remove_from_group) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            logListEvent();
            removeSelectedContacts();
        }
        return true;
    }

    private void removeSelectedContacts() {
        long[] selectedContactIdsArray = ((GroupMembersAdapter) getAdapter()).getSelectedContactIdsArray();
        Context context = getContext();
        GroupMetaData groupMetaData = this.mGroupMetaData;
        new UpdateGroupMembersAsyncTask(1, context, selectedContactIdsArray, groupMetaData.groupId, groupMetaData.accountName, groupMetaData.accountType, groupMetaData.dataSet).execute(new Void[0]);
        this.mActionBarAdapter.setSelectionMode(false);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2 = intent;
        if (i2 == -1 && intent2 != null && i == 100) {
            long[] longArrayExtra = intent2.getLongArrayExtra("com.android.contacts.action.CONTACT_IDS");
            if (longArrayExtra == null) {
                long longExtra = intent2.getLongExtra("com.android.contacts.action.CONTACT_ID", -1);
                if (longExtra > -1) {
                    longArrayExtra = new long[]{longExtra};
                }
            }
            Context context = getContext();
            GroupMetaData groupMetaData = this.mGroupMetaData;
            new UpdateGroupMembersAsyncTask(0, context, longArrayExtra, groupMetaData.groupId, groupMetaData.accountName, groupMetaData.accountType, groupMetaData.dataSet).execute(new Void[0]);
            return;
        }
    }

    private void logListEvent() {
        Logger.logListEvent(7, getListType(), ((GroupMembersAdapter) getAdapter()).getCount(), -1, ((GroupMembersAdapter) getAdapter()).getSelectedContactIdsArray().length);
    }

    private void deleteGroup() {
        if (getMemberCount() == 0) {
            getContext().startService(ContactSaveService.createGroupDeletionIntent(getContext(), this.mGroupMetaData.groupId));
            this.mActivity.switchToAllContacts();
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        GroupMetaData groupMetaData = this.mGroupMetaData;
        GroupDeletionDialogFragment.show(fragmentManager, groupMetaData.groupId, groupMetaData.groupName);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (PeopleActivity) getActivity();
        PeopleActivity peopleActivity = this.mActivity;
        this.mActionBarAdapter = new ActionBarAdapter(peopleActivity, this.mActionBarListener, peopleActivity.getSupportActionBar(), this.mActivity.getToolbar(), R.string.enter_contact_name);
        this.mActionBarAdapter.setShowHomeIcon(true);
        ContactsRequest contactsRequest = new ContactsRequest();
        contactsRequest.setActionCode(20);
        this.mActionBarAdapter.initialize(bundle, contactsRequest);
        GroupMetaData groupMetaData = this.mGroupMetaData;
        if (groupMetaData != null) {
            this.mActivity.setTitle(groupMetaData.groupName);
            if (this.mGroupMetaData.editable) {
                setCheckBoxListListener(this.mCheckBoxListener);
            }
        }
    }

    public ActionBarAdapter getActionBarAdapter() {
        return this.mActionBarAdapter;
    }

    public void displayDeleteButtons(boolean z) {
        ((GroupMembersAdapter) getAdapter()).setDisplayDeleteButtons(z);
    }

    public ArrayList<String> getMemberContactIds() {
        return new ArrayList<>(this.mGroupMemberContactIds);
    }

    public int getMemberCount() {
        return this.mGroupMemberContactIds.size();
    }

    public boolean isEditMode() {
        return this.mIsEditMode;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.mGroupUri = (Uri) getArguments().getParcelable("groupUri");
        } else {
            this.mIsEditMode = bundle.getBoolean("editMode");
            this.mGroupUri = (Uri) bundle.getParcelable("groupUri");
            this.mGroupMetaData = (GroupMetaData) bundle.getParcelable("groupMetadata");
        }
        maybeAttachCheckBoxListener();
    }

    public void onResume() {
        super.onResume();
        this.mActionBarAdapter.setListener(this.mActionBarListener);
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        GroupMetaData groupMetaData = this.mGroupMetaData;
        if (groupMetaData == null || !groupMetaData.isValid()) {
            getLoaderManager().restartLoader(100, (Bundle) null, this.mGroupMetaDataCallbacks);
        } else {
            onGroupMetadataLoaded();
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null) {
            setVisibleScrollbarEnabled(true);
            FilterCursorWrapper filterCursorWrapper = new FilterCursorWrapper(cursor);
            bindMembersCount(filterCursorWrapper.getCount());
            super.onLoadFinished(loader, (Cursor) filterCursorWrapper);
            this.mActivity.invalidateOptionsMenu();
            this.mActionBarAdapter.updateOverflowButtonColor();
        }
    }

    private void bindMembersCount(int i) {
        View findViewById = getView().findViewById(R.id.account_filter_header_container);
        View findViewById2 = getView().findViewById(R.id.empty_group);
        if (i > 0) {
            GroupMetaData groupMetaData = this.mGroupMetaData;
            bindListHeader(getContext(), getListView(), findViewById, new AccountWithDataSet(groupMetaData.accountName, groupMetaData.accountType, groupMetaData.dataSet), i);
            findViewById2.setVisibility(8);
            return;
        }
        hideHeaderAndAddPadding(getContext(), getListView(), findViewById);
        findViewById2.setVisibility(0);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ActionBarAdapter actionBarAdapter = this.mActionBarAdapter;
        if (actionBarAdapter != null) {
            actionBarAdapter.setListener((ActionBarAdapter.Listener) null);
            this.mActionBarAdapter.onSaveInstanceState(bundle);
        }
        bundle.putBoolean("editMode", this.mIsEditMode);
        bundle.putParcelable("groupUri", this.mGroupUri);
        bundle.putParcelable("groupMetadata", this.mGroupMetaData);
    }

    /* access modifiers changed from: private */
    public void onGroupMetadataLoaded() {
        if (Log.isLoggable("GroupMembers", 2)) {
            Log.v("GroupMembers", "Loaded " + this.mGroupMetaData);
        }
        maybeAttachCheckBoxListener();
        this.mActivity.setTitle(this.mGroupMetaData.groupName);
        this.mActivity.invalidateOptionsMenu();
        this.mActivity.updateDrawerGroupMenu(this.mGroupMetaData.groupId);
        super.startLoading();
    }

    private void maybeAttachCheckBoxListener() {
        GroupMetaData groupMetaData = this.mGroupMetaData;
        if (groupMetaData != null && groupMetaData.editable) {
            setCheckBoxListListener(this.mCheckBoxListener);
        }
    }

    /* access modifiers changed from: protected */
    public GroupMembersAdapter createListAdapter() {
        GroupMembersAdapter groupMembersAdapter = new GroupMembersAdapter(getContext());
        groupMembersAdapter.setSectionHeaderDisplayEnabled(true);
        groupMembersAdapter.setDisplayPhotos(true);
        groupMembersAdapter.setDeleteContactListener(new DeletionListener());
        return groupMembersAdapter;
    }

    /* access modifiers changed from: protected */
    public void configureAdapter() {
        super.configureAdapter();
        if (this.mGroupMetaData != null) {
            ((GroupMembersAdapter) getAdapter()).setGroupId(this.mGroupMetaData.groupId);
        }
    }

    /* access modifiers changed from: protected */
    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.contact_list_content, (ViewGroup) null);
        View inflate2 = layoutInflater.inflate(R.layout.empty_group_view, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate2.findViewById(R.id.empty_group_image);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(0, getResources().getDisplayMetrics().heightPixels / getResources().getInteger(R.integer.empty_group_view_image_margin_divisor), 0, 0);
        layoutParams.gravity = 1;
        imageView.setLayoutParams(layoutParams);
        ((FrameLayout) inflate.findViewById(R.id.contact_list)).addView(inflate2);
        ((Button) inflate2.findViewById(R.id.add_member_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GroupMembersFragment groupMembersFragment = GroupMembersFragment.this;
                groupMembersFragment.startActivityForResult(GroupUtil.createPickMemberIntent(groupMembersFragment.getContext(), GroupMembersFragment.this.mGroupMetaData, GroupMembersFragment.this.getMemberContactIds()), 100);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(int i, long j) {
        Uri contactUri = ((GroupMembersAdapter) getAdapter()).getContactUri(i);
        if (contactUri != null) {
            if (((GroupMembersAdapter) getAdapter()).isDisplayingCheckBoxes()) {
                super.onItemClick(i, j);
                return;
            }
            Logger.logListEvent(2, 3, ((GroupMembersAdapter) getAdapter()).getCount(), i, 0);
            ImplicitIntentsUtil.startQuickContact(getActivity(), contactUri, 9);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onItemLongClick(int i, long j) {
        if (this.mActivity == null || !this.mIsEditMode) {
            return super.onItemLongClick(i, j);
        }
        return true;
    }

    private final class DeletionListener implements MultiSelectEntryContactListAdapter.DeleteContactListener {
        private DeletionListener() {
        }

        public void onContactDeleteClicked(int i) {
            new UpdateGroupMembersAsyncTask(1, GroupMembersFragment.this.getContext(), new long[]{((GroupMembersAdapter) GroupMembersFragment.this.getAdapter()).getContactId(i)}, GroupMembersFragment.this.mGroupMetaData.groupId, GroupMembersFragment.this.mGroupMetaData.accountName, GroupMembersFragment.this.mGroupMetaData.accountType, GroupMembersFragment.this.mGroupMetaData.dataSet).execute(new Void[0]);
        }
    }

    public boolean isCurrentGroup(long j) {
        GroupMetaData groupMetaData = this.mGroupMetaData;
        return groupMetaData != null && groupMetaData.groupId == j;
    }

    public boolean isInactive() {
        return !isAdded() || isRemoving() || isDetached();
    }

    public void onDestroy() {
        ActionBarAdapter actionBarAdapter = this.mActionBarAdapter;
        if (actionBarAdapter != null) {
            actionBarAdapter.setListener((ActionBarAdapter.Listener) null);
        }
        super.onDestroy();
    }

    public void updateExistingGroupFragment(Uri uri, String str) {
        toastForSaveAction(str);
        if (isEditMode() && getGroupCount() == 1) {
            exitEditMode();
        } else if (!GroupUtil.ACTION_REMOVE_FROM_GROUP.equals(str)) {
            this.mGroupUri = uri;
            this.mGroupMetaData = null;
            reloadData();
            this.mActivity.invalidateOptionsMenu();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toastForSaveAction(java.lang.String r7) {
        /*
            r6 = this;
            int r0 = r7.hashCode()
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = -1
            switch(r0) {
                case -2124897071: goto L_0x0035;
                case -595664074: goto L_0x002b;
                case -515792157: goto L_0x0021;
                case 731391203: goto L_0x0017;
                case 2107158443: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x003f
        L_0x000d:
            java.lang.String r0 = "switchGroup"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 4
            goto L_0x0040
        L_0x0017:
            java.lang.String r0 = "addToGroup"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 3
            goto L_0x0040
        L_0x0021:
            java.lang.String r0 = "createGroup"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 2
            goto L_0x0040
        L_0x002b:
            java.lang.String r0 = "updateGroup"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 0
            goto L_0x0040
        L_0x0035:
            java.lang.String r0 = "removeFromGroup"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = 1
            goto L_0x0040
        L_0x003f:
            r0 = -1
        L_0x0040:
            if (r0 == 0) goto L_0x0087
            if (r0 == r4) goto L_0x0083
            if (r0 == r3) goto L_0x007f
            if (r0 == r2) goto L_0x007b
            if (r0 == r1) goto L_0x008a
            android.content.Context r0 = r6.getContext()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "toastForSaveAction passed unknown action: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unhandled contact save action "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7)
            java.lang.String r7 = "GroupMembers"
            com.android.contactsbind.FeedbackHelper.sendFeedback(r0, r7, r1, r2)
            goto L_0x008a
        L_0x007b:
            r5 = 2131755372(0x7f10016c, float:1.9141621E38)
            goto L_0x008a
        L_0x007f:
            r5 = 2131755367(0x7f100167, float:1.9141611E38)
            goto L_0x008a
        L_0x0083:
            r5 = 2131755373(0x7f10016d, float:1.9141623E38)
            goto L_0x008a
        L_0x0087:
            r5 = 2131755377(0x7f100171, float:1.9141632E38)
        L_0x008a:
            r6.toast(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.group.GroupMembersFragment.toastForSaveAction(java.lang.String):void");
    }

    private void toast(int i) {
        if (i >= 0) {
            Toast.makeText(getContext(), i, 0).show();
        }
    }

    private int getGroupCount() {
        if (getAdapter() != null) {
            return ((GroupMembersAdapter) getAdapter()).getCount();
        }
        return -1;
    }

    public void exitEditMode() {
        this.mIsEditMode = false;
        this.mActionBarAdapter.setSelectionMode(false);
        displayDeleteButtons(false);
    }
}
