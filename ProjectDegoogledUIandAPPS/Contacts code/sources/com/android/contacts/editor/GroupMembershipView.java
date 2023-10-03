package com.android.contacts.editor;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.group.GroupNameEditDialogFragment;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.RawContactModifier;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.util.UiClosables;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Iterator;

public class GroupMembershipView extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {
    private boolean mAccountHasGroups;
    private String mAccountName;
    private String mAccountType;
    private GroupMembershipAdapter<GroupSelectionItem> mAdapter;
    /* access modifiers changed from: private */
    public boolean mCreatedNewGroup;
    private String mDataSet;
    private long mDefaultGroupId;
    private boolean mDefaultGroupVisibilityKnown;
    private boolean mDefaultGroupVisible;
    private long mFavoritesGroupId;
    private TextView mGroupList;
    private Cursor mGroupMetaData;
    private GroupNameEditDialogFragment mGroupNameEditDialogFragment;
    private int mHintTextColor;
    private DataKind mKind;
    private GroupNameEditDialogFragment.Listener mListener = new GroupNameEditDialogFragment.Listener() {
        public void onGroupNameEditCancelled() {
        }

        public void onGroupNameEditCompleted(String str) {
            boolean unused = GroupMembershipView.this.mCreatedNewGroup = true;
        }
    };
    private String mNoGroupString;
    private ListPopupWindow mPopup;
    /* access modifiers changed from: private */
    public int mPrimaryTextColor;
    private RawContactDelta mState;

    public static final class GroupSelectionItem {
        private boolean mChecked;
        private final long mGroupId;
        private final String mTitle;

        public GroupSelectionItem(long j, String str, boolean z) {
            this.mGroupId = j;
            this.mTitle = str;
            this.mChecked = z;
        }

        public long getGroupId() {
            return this.mGroupId;
        }

        public boolean isChecked() {
            return this.mChecked;
        }

        public void setChecked(boolean z) {
            this.mChecked = z;
        }

        public String toString() {
            return this.mTitle;
        }
    }

    private class GroupMembershipAdapter<T> extends ArrayAdapter<T> {
        private int mNewestGroupPosition;

        public int getViewTypeCount() {
            return 2;
        }

        public GroupMembershipAdapter(Context context, int i) {
            super(context, i);
        }

        public boolean getItemIsCheckable(int i) {
            return i != getCount() - 1;
        }

        public int getItemViewType(int i) {
            return getItemIsCheckable(i) ^ true ? 1 : 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 == null) {
                return null;
            }
            CheckedTextView checkedTextView = (CheckedTextView) view2;
            if (!getItemIsCheckable(i)) {
                checkedTextView.setCheckMarkDrawable((Drawable) null);
            }
            checkedTextView.setTextColor(GroupMembershipView.this.mPrimaryTextColor);
            return checkedTextView;
        }

        public int getNewestGroupPosition() {
            return this.mNewestGroupPosition;
        }

        public void setNewestGroupPosition(int i) {
            this.mNewestGroupPosition = i;
        }
    }

    public GroupMembershipView(Context context) {
        super(context);
    }

    public GroupMembershipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        Resources resources = getContext().getResources();
        this.mPrimaryTextColor = resources.getColor(R.color.primary_text_color);
        this.mHintTextColor = resources.getColor(R.color.editor_disabled_text_color);
        this.mNoGroupString = getContext().getString(R.string.group_edit_field_hint_text);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void setGroupNameEditDialogFragment() {
        this.mGroupNameEditDialogFragment = (GroupNameEditDialogFragment) ((Activity) getContext()).getFragmentManager().findFragmentByTag("createGroupDialog");
        GroupNameEditDialogFragment groupNameEditDialogFragment = this.mGroupNameEditDialogFragment;
        if (groupNameEditDialogFragment != null) {
            groupNameEditDialogFragment.setListener(this.mListener);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextView textView = this.mGroupList;
        if (textView != null) {
            textView.setEnabled(z);
        }
    }

    public void setKind(DataKind dataKind) {
        this.mKind = dataKind;
        ((ImageView) findViewById(R.id.kind_icon)).setContentDescription(getResources().getString(dataKind.titleRes));
    }

    public void setGroupMetaData(Cursor cursor) {
        this.mGroupMetaData = cursor;
        updateView();
        if (this.mCreatedNewGroup) {
            this.mCreatedNewGroup = false;
            onClick(this);
            if (this.mPopup != null) {
                int newestGroupPosition = this.mAdapter.getNewestGroupPosition();
                ListView listView = this.mPopup.getListView();
                if (listView != null && !listView.isItemChecked(newestGroupPosition)) {
                    listView.setItemChecked(newestGroupPosition, true);
                    onItemClick(listView, (View) null, newestGroupPosition, listView.getItemIdAtPosition(newestGroupPosition));
                }
            }
        }
    }

    public boolean wasGroupMetaDataBound() {
        return this.mGroupMetaData != null;
    }

    public boolean accountHasGroups() {
        return this.mAccountHasGroups;
    }

    public void setState(RawContactDelta rawContactDelta) {
        this.mState = rawContactDelta;
        this.mAccountType = this.mState.getAccountType();
        this.mAccountName = this.mState.getAccountName();
        this.mDataSet = this.mState.getDataSet();
        this.mDefaultGroupVisibilityKnown = false;
        this.mCreatedNewGroup = false;
        updateView();
        setGroupNameEditDialogFragment();
    }

    private void updateView() {
        boolean z;
        Cursor cursor = this.mGroupMetaData;
        if (cursor == null || cursor.isClosed() || this.mAccountType == null || this.mAccountName == null) {
            setVisibility(8);
            return;
        }
        this.mFavoritesGroupId = 0;
        this.mDefaultGroupId = 0;
        StringBuilder sb = new StringBuilder();
        this.mGroupMetaData.moveToPosition(-1);
        while (true) {
            z = false;
            if (!this.mGroupMetaData.moveToNext()) {
                break;
            }
            String string = this.mGroupMetaData.getString(0);
            String string2 = this.mGroupMetaData.getString(1);
            String string3 = this.mGroupMetaData.getString(2);
            if (string.equals(this.mAccountName) && string2.equals(this.mAccountType) && Objects.equal(string3, this.mDataSet)) {
                long j = this.mGroupMetaData.getLong(3);
                if (!this.mGroupMetaData.isNull(6) && this.mGroupMetaData.getInt(6) != 0) {
                    this.mFavoritesGroupId = j;
                } else if (this.mGroupMetaData.isNull(5) || this.mGroupMetaData.getInt(5) == 0) {
                    this.mAccountHasGroups = true;
                } else {
                    this.mDefaultGroupId = j;
                }
                if (!(j == this.mFavoritesGroupId || j == this.mDefaultGroupId || !hasMembership(j))) {
                    String string4 = this.mGroupMetaData.getString(4);
                    if (!TextUtils.isEmpty(string4)) {
                        if (sb.length() != 0) {
                            sb.append(", ");
                        }
                        sb.append(string4);
                    }
                }
            }
        }
        if (!this.mAccountHasGroups) {
            setVisibility(8);
            return;
        }
        if (this.mGroupList == null) {
            this.mGroupList = (TextView) findViewById(R.id.group_list);
            this.mGroupList.setOnClickListener(this);
        }
        this.mGroupList.setEnabled(isEnabled());
        if (sb.length() == 0) {
            this.mGroupList.setText(this.mNoGroupString);
            this.mGroupList.setTextColor(this.mHintTextColor);
        } else {
            this.mGroupList.setText(sb);
            this.mGroupList.setTextColor(this.mPrimaryTextColor);
        }
        setVisibility(0);
        if (!this.mDefaultGroupVisibilityKnown) {
            long j2 = this.mDefaultGroupId;
            if (j2 != 0 && !hasMembership(j2)) {
                z = true;
            }
            this.mDefaultGroupVisible = z;
            this.mDefaultGroupVisibilityKnown = true;
        }
    }

    public void onClick(View view) {
        int i;
        if (UiClosables.closeQuietly(this.mPopup)) {
            this.mPopup = null;
            return;
        }
        requestFocus();
        this.mAdapter = new GroupMembershipAdapter<>(getContext(), R.layout.group_membership_list_item);
        long j = -1;
        this.mGroupMetaData.moveToPosition(-1);
        while (true) {
            if (!this.mGroupMetaData.moveToNext()) {
                break;
            }
            String string = this.mGroupMetaData.getString(0);
            String string2 = this.mGroupMetaData.getString(1);
            String string3 = this.mGroupMetaData.getString(2);
            if (string.equals(this.mAccountName) && string2.equals(this.mAccountType) && Objects.equal(string3, this.mDataSet)) {
                long j2 = this.mGroupMetaData.getLong(3);
                if (j2 != this.mFavoritesGroupId && (j2 != this.mDefaultGroupId || this.mDefaultGroupVisible)) {
                    if (j2 > j) {
                        GroupMembershipAdapter<GroupSelectionItem> groupMembershipAdapter = this.mAdapter;
                        groupMembershipAdapter.setNewestGroupPosition(groupMembershipAdapter.getCount());
                        j = j2;
                    }
                    this.mAdapter.add(new GroupSelectionItem(j2, this.mGroupMetaData.getString(4), hasMembership(j2)));
                }
            }
        }
        this.mAdapter.add(new GroupSelectionItem(133, getContext().getString(R.string.create_group_item_label), false));
        this.mPopup = new ListPopupWindow(getContext(), (AttributeSet) null);
        this.mPopup.setAnchorView(this.mGroupList);
        this.mPopup.setAdapter(this.mAdapter);
        this.mPopup.setModal(true);
        this.mPopup.setInputMethodMode(2);
        this.mPopup.show();
        ListView listView = this.mPopup.getListView();
        listView.setChoiceMode(2);
        listView.setOverScrollMode(0);
        int count = this.mAdapter.getCount();
        for (i = 0; i < count; i++) {
            listView.setItemChecked(i, this.mAdapter.getItem(i).isChecked());
        }
        listView.setOnItemClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        UiClosables.closeQuietly(this.mPopup);
        this.mPopup = null;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ValuesDelta insertChild;
        Long groupRowId;
        ListView listView = (ListView) adapterView;
        int count = this.mAdapter.getCount();
        int i2 = count - 1;
        if (listView.isItemChecked(i2)) {
            listView.setItemChecked(i2, false);
            createNewGroup();
            return;
        }
        for (int i3 = 0; i3 < count; i3++) {
            this.mAdapter.getItem(i3).setChecked(listView.isItemChecked(i3));
        }
        ArrayList<ValuesDelta> mimeEntries = this.mState.getMimeEntries("vnd.android.cursor.item/group_membership");
        if (mimeEntries != null) {
            Iterator<ValuesDelta> it = mimeEntries.iterator();
            while (it.hasNext()) {
                ValuesDelta next = it.next();
                if (!(next.isDelete() || (groupRowId = next.getGroupRowId()) == null || groupRowId.longValue() == this.mFavoritesGroupId)) {
                    if ((groupRowId.longValue() != this.mDefaultGroupId || this.mDefaultGroupVisible) && !isGroupChecked(groupRowId.longValue())) {
                        next.markDeleted();
                    }
                }
            }
        }
        for (int i4 = 0; i4 < count; i4++) {
            GroupSelectionItem item = this.mAdapter.getItem(i4);
            long groupId = item.getGroupId();
            if (item.isChecked() && !hasMembership(groupId) && (insertChild = RawContactModifier.insertChild(this.mState, this.mKind)) != null) {
                insertChild.setGroupRowId(groupId);
            }
        }
        updateView();
    }

    private boolean isGroupChecked(long j) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            GroupSelectionItem item = this.mAdapter.getItem(i);
            if (j == item.getGroupId()) {
                return item.isChecked();
            }
        }
        return false;
    }

    private boolean hasMembership(long j) {
        Long groupRowId;
        if (j == this.mDefaultGroupId && this.mState.isContactInsert()) {
            return true;
        }
        ArrayList<ValuesDelta> mimeEntries = this.mState.getMimeEntries("vnd.android.cursor.item/group_membership");
        if (mimeEntries == null) {
            return false;
        }
        Iterator<ValuesDelta> it = mimeEntries.iterator();
        while (it.hasNext()) {
            ValuesDelta next = it.next();
            if (!next.isDelete() && (groupRowId = next.getGroupRowId()) != null && groupRowId.longValue() == j) {
                return true;
            }
        }
        return false;
    }

    private void createNewGroup() {
        UiClosables.closeQuietly(this.mPopup);
        this.mPopup = null;
        this.mGroupNameEditDialogFragment = GroupNameEditDialogFragment.newInstanceForCreation(new AccountWithDataSet(this.mAccountName, this.mAccountType, this.mDataSet), (String) null);
        this.mGroupNameEditDialogFragment.setListener(this.mListener);
        this.mGroupNameEditDialogFragment.show(((Activity) getContext()).getFragmentManager(), "createGroupDialog");
    }
}
