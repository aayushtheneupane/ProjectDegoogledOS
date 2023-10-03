package com.android.contacts.drawer;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.activities.PeopleActivity;
import com.android.contacts.group.GroupListItem;
import com.android.contacts.list.ContactListFilter;
import com.android.contacts.model.account.AccountDisplayInfo;
import com.android.contacts.model.account.AccountDisplayInfoFactory;
import com.android.contacts.profile.ProfileItem;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.SharedPreferenceUtil;
import com.android.contactsbind.HelpUtils;
import com.android.contactsbind.ObjectFactory;
import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends BaseAdapter {
    private AccountDisplayInfoFactory mAccountDisplayFactory;
    private List<AccountEntryItem> mAccountEntries = new ArrayList();
    private HeaderItem mAccountHeader = null;
    private final Activity mActivity;
    private boolean mAreGroupWritableAccountsAvailable;
    private BaseDrawerItem mCreateLabelButton = null;
    private BaseDrawerItem mEmergencyItem = null;
    private DividerItem mEmergencyItemDivider = null;
    private List<GroupEntryItem> mGroupEntries = new ArrayList();
    private HeaderItem mGroupHeader = null;
    private final LayoutInflater mInflater;
    private List<BaseDrawerItem> mItemsList = new ArrayList();
    private List<BaseDrawerItem> mMiscItems = new ArrayList();
    private NavSpacerItem mNavSpacerItem = null;
    private List<PrimaryItem> mPrimaryItems = new ArrayList();
    private ProfileEntryItem mProfileEntryItem = null;
    private DividerItem mProfileEntryItemDivider = null;
    private ContactListFilter mSelectedAccount;
    private long mSelectedGroupId;
    private PeopleActivity.ContactsView mSelectedView;

    public int getViewTypeCount() {
        return 11;
    }

    public DrawerAdapter(Activity activity) {
        this.mInflater = LayoutInflater.from(activity);
        this.mActivity = activity;
        initializeDrawerMenuItems();
    }

    private void initializeDrawerMenuItems() {
        this.mNavSpacerItem = new NavSpacerItem(R.id.nav_drawer_spacer);
        this.mProfileEntryItemDivider = new DividerItem();
        this.mPrimaryItems.add(new PrimaryItem(R.id.nav_all_contacts, R.string.contactsList, R.drawable.quantum_ic_account_circle_vd_theme_24, PeopleActivity.ContactsView.ALL_CONTACTS));
        if (ObjectFactory.getAssistantFragment() != null) {
            this.mPrimaryItems.add(new PrimaryItem(R.id.nav_assistant, R.string.menu_assistant, R.drawable.quantum_ic_assistant_vd_theme_24, PeopleActivity.ContactsView.ASSISTANT));
        }
        this.mGroupHeader = new HeaderItem(R.id.nav_groups, R.string.menu_title_groups);
        this.mAccountHeader = new HeaderItem(R.id.nav_filters, R.string.menu_title_filters);
        this.mCreateLabelButton = new BaseDrawerItem(5, R.id.nav_create_label, R.string.menu_new_group_action_bar, R.drawable.quantum_ic_add_vd_theme_24);
        this.mEmergencyItem = new BaseDrawerItem(9, R.id.nav_emergency, R.string.menu_emergency_information_txt, R.drawable.quantum_ic_drawer_emergency_info_24);
        this.mEmergencyItemDivider = new DividerItem();
        this.mMiscItems.add(new DividerItem());
        this.mMiscItems.add(new MiscItem(R.id.nav_settings, R.string.menu_settings, R.drawable.quantum_ic_settings_vd_theme_24));
        if (HelpUtils.isHelpAndFeedbackAvailable()) {
            this.mMiscItems.add(new MiscItem(R.id.nav_help, R.string.menu_help, R.drawable.quantum_ic_help_vd_theme_24));
        }
        rebuildItemsList();
    }

    private void rebuildItemsList() {
        this.mItemsList.clear();
        this.mItemsList.add(this.mNavSpacerItem);
        ProfileEntryItem profileEntryItem = this.mProfileEntryItem;
        if (profileEntryItem != null) {
            this.mItemsList.add(profileEntryItem);
            this.mItemsList.add(this.mProfileEntryItemDivider);
        }
        this.mItemsList.addAll(this.mPrimaryItems);
        if (this.mAreGroupWritableAccountsAvailable || !this.mGroupEntries.isEmpty()) {
            this.mItemsList.add(this.mGroupHeader);
        }
        this.mItemsList.addAll(this.mGroupEntries);
        if (this.mAreGroupWritableAccountsAvailable) {
            this.mItemsList.add(this.mCreateLabelButton);
        }
        if (this.mAccountEntries.size() > 0) {
            this.mItemsList.add(this.mAccountHeader);
        }
        this.mItemsList.addAll(this.mAccountEntries);
        if (ImplicitIntentsUtil.getIntentForEmergencyInfo(this.mActivity) != null) {
            this.mItemsList.add(this.mEmergencyItemDivider);
            this.mItemsList.add(this.mEmergencyItem);
        }
        this.mItemsList.addAll(this.mMiscItems);
        this.mItemsList.add(this.mNavSpacerItem);
    }

    public void setProfile(ProfileItem profileItem) {
        this.mProfileEntryItem = new ProfileEntryItem(R.id.nav_myprofile, profileItem);
        notifyChangeAndRebuildList();
    }

    public void setGroups(List<GroupListItem> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (GroupListItem groupEntryItem : list) {
            arrayList.add(new GroupEntryItem(R.id.nav_group, groupEntryItem));
        }
        this.mGroupEntries.clear();
        this.mGroupEntries.addAll(arrayList);
        this.mAreGroupWritableAccountsAvailable = z;
        notifyChangeAndRebuildList();
    }

    public void setAccounts(List<ContactListFilter> list) {
        ArrayList arrayList = new ArrayList();
        for (ContactListFilter accountEntryItem : list) {
            arrayList.add(new AccountEntryItem(R.id.nav_filter, accountEntryItem));
        }
        this.mAccountDisplayFactory = AccountDisplayInfoFactory.fromListFilters(this.mActivity, list);
        this.mAccountEntries.clear();
        this.mAccountEntries.addAll(arrayList);
        notifyChangeAndRebuildList();
    }

    public int getCount() {
        return this.mItemsList.size();
    }

    public BaseDrawerItem getItem(int i) {
        return this.mItemsList.get(i);
    }

    public long getItemId(int i) {
        return (long) getItem(i).f9id;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseDrawerItem item = getItem(i);
        switch (item.viewType) {
            case 0:
                return getPrimaryItemView((PrimaryItem) item, view, viewGroup);
            case 1:
                return getDrawerItemView(item, view, viewGroup);
            case 2:
                return getHeaderItemView((HeaderItem) item, view, viewGroup);
            case 3:
                return getGroupEntryView((GroupEntryItem) item, view, viewGroup);
            case 4:
                return getAccountItemView((AccountEntryItem) item, view, viewGroup);
            case 5:
                return getDrawerItemView(item, view, viewGroup);
            case 6:
                return getBaseItemView(R.layout.nav_drawer_spacer, view, viewGroup);
            case 7:
                return getBaseItemView(R.layout.drawer_horizontal_divider, view, viewGroup);
            case 8:
                return getProfileEntryView((ProfileEntryItem) item, view, viewGroup);
            case 9:
                return getDrawerItemView(item, view, viewGroup);
            default:
                throw new IllegalStateException("Unknown drawer item " + item);
        }
    }

    private View getBaseItemView(int i, View view, ViewGroup viewGroup) {
        return view == null ? this.mInflater.inflate(i, viewGroup, false) : view;
    }

    private View getPrimaryItemView(PrimaryItem primaryItem, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.drawer_primary_item, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(primaryItem.text);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setImageResource(primaryItem.icon);
        ((TextView) view.findViewById(R.id.assistant_new_badge)).setVisibility((primaryItem.contactsView != PeopleActivity.ContactsView.ASSISTANT || !(SharedPreferenceUtil.isWelcomeCardDismissed(this.mActivity) ^ true)) ? 8 : 0);
        view.setActivated(primaryItem.contactsView == this.mSelectedView);
        if (primaryItem.contactsView == this.mSelectedView) {
            z = true;
        }
        updateSelectedStatus(textView, imageView, z);
        view.setId(primaryItem.f9id);
        return view;
    }

    private View getHeaderItemView(HeaderItem headerItem, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(R.layout.drawer_header, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.title)).setText(headerItem.text);
        view.setId(headerItem.f9id);
        return view;
    }

    private View getProfileEntryView(ProfileEntryItem profileEntryItem, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(R.layout.drawer_secondline_item, viewGroup, false);
            view.setId(profileEntryItem.f9id);
        }
        ProfileItem access$000 = profileEntryItem.profile;
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        if (!access$000.HasProfile()) {
            getPhotoLoader().loadDirectoryPhoto(imageView, (Uri) null, false, true, (ContactPhotoManager.DefaultImageRequest) null);
            imageView.setImageResource(R.drawable.quantum_ic_drawer_my_info_32);
        } else if (access$000.getPhotoId() != 0) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            getPhotoLoader().loadThumbnail(imageView, access$000.getPhotoId(), false, true, (ContactPhotoManager.DefaultImageRequest) null);
        } else if (access$000.getPhotoUri() != null) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            getPhotoLoader().loadDirectoryPhoto(imageView, Uri.parse(access$000.getPhotoUri()), false, true, (ContactPhotoManager.DefaultImageRequest) null);
        } else {
            getPhotoLoader().loadDirectoryPhoto(imageView, (Uri) null, false, true, (ContactPhotoManager.DefaultImageRequest) null);
            imageView.setImageResource(R.drawable.quantum_ic_drawer_my_info_32);
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(this.mActivity.getString(R.string.settings_my_info_title));
        TextView textView2 = (TextView) view.findViewById(R.id.summary);
        if (access$000.HasProfile()) {
            textView2.setText(access$000.getDisplayName());
            if (access$000.getDisplayNameSource() == 20) {
                textView2.setTextDirection(3);
            }
        } else {
            textView2.setText(this.mActivity.getString(R.string.set_up_profile));
        }
        updateSelectedStatus(textView, imageView, false);
        view.setTag(Long.valueOf(access$000.HasProfile() ? access$000.getContactId() : -1));
        return view;
    }

    private View getGroupEntryView(GroupEntryItem groupEntryItem, View view, ViewGroup viewGroup) {
        if (view == null || !(view.getTag() instanceof GroupEntryItem)) {
            view = this.mInflater.inflate(R.layout.drawer_item, viewGroup, false);
            view.setId(groupEntryItem.f9id);
        }
        GroupListItem access$100 = groupEntryItem.group;
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(access$100.getTitle());
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.quantum_ic_label_vd_theme_24);
        boolean z = access$100.getGroupId() == this.mSelectedGroupId && this.mSelectedView == PeopleActivity.ContactsView.GROUP_VIEW;
        updateSelectedStatus(textView, imageView, z);
        view.setActivated(z);
        view.setTag(access$100);
        view.setContentDescription(this.mActivity.getString(R.string.navigation_drawer_label, new Object[]{access$100.getTitle()}));
        return view;
    }

    private View getAccountItemView(AccountEntryItem accountEntryItem, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null || !(view.getTag() instanceof ContactListFilter)) {
            view = this.mInflater.inflate(R.layout.drawer_item, viewGroup, false);
            view.setId(accountEntryItem.f9id);
        }
        ContactListFilter access$200 = accountEntryItem.account;
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(this.mActivity.getPackageName().equals(access$200.accountType) ? this.mActivity.getString(R.string.account_phone) : access$200.accountName);
        if (access$200.equals(this.mSelectedAccount) && this.mSelectedView == PeopleActivity.ContactsView.ACCOUNT_VIEW) {
            z = true;
        }
        textView.setTextAppearance(this.mActivity, z ? R.style.DrawerItemTextActiveStyle : R.style.DrawerItemTextInactiveStyle);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        AccountDisplayInfo accountDisplayInfoFor = this.mAccountDisplayFactory.getAccountDisplayInfoFor(accountEntryItem.account);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageDrawable(accountDisplayInfoFor.getIcon());
        view.setTag(access$200);
        view.setActivated(z);
        view.setContentDescription(accountDisplayInfoFor.getTypeLabel() + " " + accountEntryItem.account.accountName);
        return view;
    }

    private View getDrawerItemView(BaseDrawerItem baseDrawerItem, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(R.layout.drawer_item, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(baseDrawerItem.text);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setImageResource(baseDrawerItem.icon);
        view.setId(baseDrawerItem.f9id);
        updateSelectedStatus(textView, imageView, false);
        return view;
    }

    public int getItemViewType(int i) {
        return getItem(i).viewType;
    }

    private void updateSelectedStatus(TextView textView, ImageView imageView, boolean z) {
        textView.setTextAppearance(this.mActivity, z ? R.style.DrawerItemTextActiveStyle : R.style.DrawerItemTextInactiveStyle);
        if (z) {
            imageView.setColorFilter(this.mActivity.getResources().getColor(R.color.primary_color), PorterDuff.Mode.SRC_ATOP);
        } else {
            imageView.clearColorFilter();
        }
    }

    private void notifyChangeAndRebuildList() {
        rebuildItemsList();
        notifyDataSetChanged();
    }

    public void setSelectedContactsView(PeopleActivity.ContactsView contactsView) {
        if (this.mSelectedView != contactsView) {
            this.mSelectedView = contactsView;
            notifyChangeAndRebuildList();
        }
    }

    public void setSelectedGroupId(long j) {
        if (this.mSelectedGroupId != j) {
            this.mSelectedGroupId = j;
            notifyChangeAndRebuildList();
        }
    }

    public long getSelectedGroupId() {
        return this.mSelectedGroupId;
    }

    public void setSelectedAccount(ContactListFilter contactListFilter) {
        if (this.mSelectedAccount != contactListFilter) {
            this.mSelectedAccount = contactListFilter;
            notifyChangeAndRebuildList();
        }
    }

    public ContactListFilter getSelectedAccount() {
        return this.mSelectedAccount;
    }

    public static class BaseDrawerItem {
        public final int icon;

        /* renamed from: id */
        public final int f9id;
        public final int text;
        public final int viewType;

        public BaseDrawerItem(int i, int i2, int i3, int i4) {
            this.viewType = i;
            this.f9id = i2;
            this.text = i3;
            this.icon = i4;
        }
    }

    public static class PrimaryItem extends BaseDrawerItem {
        public final PeopleActivity.ContactsView contactsView;

        public PrimaryItem(int i, int i2, int i3, PeopleActivity.ContactsView contactsView2) {
            super(0, i, i2, i3);
            this.contactsView = contactsView2;
        }
    }

    public static class MiscItem extends BaseDrawerItem {
        public MiscItem(int i, int i2, int i3) {
            super(1, i, i2, i3);
        }
    }

    public static class HeaderItem extends BaseDrawerItem {
        public HeaderItem(int i, int i2) {
            super(2, i, i2, 0);
        }
    }

    public static class NavSpacerItem extends BaseDrawerItem {
        public NavSpacerItem(int i) {
            super(6, i, 0, 0);
        }
    }

    public static class DividerItem extends BaseDrawerItem {
        public DividerItem() {
            super(7, 0, 0, 0);
        }
    }

    public static class ProfileEntryItem extends BaseDrawerItem {
        /* access modifiers changed from: private */
        public final ProfileItem profile;

        public ProfileEntryItem(int i, ProfileItem profileItem) {
            super(8, i, 0, 0);
            this.profile = profileItem;
        }
    }

    public static class GroupEntryItem extends BaseDrawerItem {
        /* access modifiers changed from: private */
        public final GroupListItem group;

        public GroupEntryItem(int i, GroupListItem groupListItem) {
            super(3, i, 0, 0);
            this.group = groupListItem;
        }
    }

    public static class AccountEntryItem extends BaseDrawerItem {
        /* access modifiers changed from: private */
        public final ContactListFilter account;

        public AccountEntryItem(int i, ContactListFilter contactListFilter) {
            super(4, i, 0, 0);
            this.account = contactListFilter;
        }
    }

    private ContactPhotoManager getPhotoLoader() {
        return ContactPhotoManager.getInstance(this.mActivity);
    }
}
