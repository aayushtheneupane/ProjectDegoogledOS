package com.android.contacts.list;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.activities.ActionBarAdapter;
import com.android.contacts.group.GroupMembersFragment;
import com.android.contacts.list.MultiSelectEntryContactListAdapter;
import com.android.contacts.logging.Logger;
import com.android.contacts.logging.SearchState;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.GoogleAccountType;
import java.util.ArrayList;
import java.util.TreeSet;

public abstract class MultiSelectContactsListFragment<T extends MultiSelectEntryContactListAdapter> extends ContactEntryListFragment<T> implements MultiSelectEntryContactListAdapter.SelectedContactsListener {
    protected boolean mAnimateOnLoad;
    private OnCheckBoxListActionListener mCheckBoxListListener;

    public interface OnCheckBoxListActionListener {
        void onSelectedContactIdsChanged();

        void onStartDisplayingCheckBoxes();

        void onStopDisplayingCheckBoxes();
    }

    public ActionBarAdapter getActionBarAdapter() {
        return null;
    }

    public void setCheckBoxListListener(OnCheckBoxListActionListener onCheckBoxListActionListener) {
        this.mCheckBoxListListener = onCheckBoxListActionListener;
    }

    public void setAnimateOnLoad(boolean z) {
        this.mAnimateOnLoad = z;
    }

    public void onSelectedContactsChanged() {
        OnCheckBoxListActionListener onCheckBoxListActionListener = this.mCheckBoxListListener;
        if (onCheckBoxListActionListener != null) {
            onCheckBoxListActionListener.onSelectedContactIdsChanged();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (bundle == null && this.mAnimateOnLoad) {
            setLayoutAnimation(getListView(), R.anim.slide_and_fade_in_layout_animation);
        }
        return getView();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            ((MultiSelectEntryContactListAdapter) getAdapter()).setSelectedContactIds((TreeSet) bundle.getSerializable("selected_contacts"));
        }
    }

    public void onStart() {
        super.onStart();
        OnCheckBoxListActionListener onCheckBoxListActionListener = this.mCheckBoxListListener;
        if (onCheckBoxListActionListener != null) {
            onCheckBoxListActionListener.onSelectedContactIdsChanged();
        }
    }

    public TreeSet<Long> getSelectedContactIds() {
        return ((MultiSelectEntryContactListAdapter) getAdapter()).getSelectedContactIds();
    }

    public long[] getSelectedContactIdsArray() {
        return ((MultiSelectEntryContactListAdapter) getAdapter()).getSelectedContactIdsArray();
    }

    /* access modifiers changed from: protected */
    public void configureAdapter() {
        super.configureAdapter();
        ((MultiSelectEntryContactListAdapter) getAdapter()).setSelectedContactsListener(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("selected_contacts", getSelectedContactIds());
    }

    public void displayCheckBoxes(boolean z) {
        if (getAdapter() != null) {
            ((MultiSelectEntryContactListAdapter) getAdapter()).setDisplayCheckBoxes(z);
            if (!z) {
                clearCheckBoxes();
            }
        }
    }

    public void clearCheckBoxes() {
        ((MultiSelectEntryContactListAdapter) getAdapter()).setSelectedContactIds(new TreeSet());
    }

    /* access modifiers changed from: protected */
    public boolean onItemLongClick(int i, long j) {
        int size = ((MultiSelectEntryContactListAdapter) getAdapter()).getSelectedContactIds().size();
        long contactId = getContactId(i);
        int partitionForPosition = ((MultiSelectEntryContactListAdapter) getAdapter()).getPartitionForPosition(i);
        if (contactId >= 0 && ((long) partitionForPosition) == 0) {
            OnCheckBoxListActionListener onCheckBoxListActionListener = this.mCheckBoxListListener;
            if (onCheckBoxListActionListener != null) {
                onCheckBoxListActionListener.onStartDisplayingCheckBoxes();
            }
            ((MultiSelectEntryContactListAdapter) getAdapter()).toggleSelectionOfContactId(contactId);
            Logger.logListEvent(3, getListType(), ((MultiSelectEntryContactListAdapter) getAdapter()).getCount(), i, 1);
            int headerViewsCount = (i + getListView().getHeaderViewsCount()) - getListView().getFirstVisiblePosition();
            if (headerViewsCount >= 0 && headerViewsCount < getListView().getChildCount()) {
                getListView().getChildAt(headerViewsCount).sendAccessibilityEvent(1);
            }
        }
        int size2 = ((MultiSelectEntryContactListAdapter) getAdapter()).getSelectedContactIds().size();
        OnCheckBoxListActionListener onCheckBoxListActionListener2 = this.mCheckBoxListListener;
        if (!(onCheckBoxListActionListener2 == null || size == 0 || size2 != 0)) {
            onCheckBoxListActionListener2.onStopDisplayingCheckBoxes();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(int i, long j) {
        long contactId = getContactId(i);
        if (contactId >= 0) {
            if (((MultiSelectEntryContactListAdapter) getAdapter()).isDisplayingCheckBoxes()) {
                ((MultiSelectEntryContactListAdapter) getAdapter()).toggleSelectionOfContactId(contactId);
            }
            if (this.mCheckBoxListListener != null && ((MultiSelectEntryContactListAdapter) getAdapter()).getSelectedContactIds().size() == 0) {
                this.mCheckBoxListListener.onStopDisplayingCheckBoxes();
            }
        }
    }

    private long getContactId(int i) {
        int contactColumnIdIndex = ((MultiSelectEntryContactListAdapter) getAdapter()).getContactColumnIdIndex();
        Cursor cursor = (Cursor) ((MultiSelectEntryContactListAdapter) getAdapter()).getItem(i);
        if (cursor != null && cursor.getColumnCount() > contactColumnIdIndex) {
            return cursor.getLong(contactColumnIdIndex);
        }
        Log.w("MultiContactsList", "Failed to get contact ID from cursor column " + contactColumnIdIndex);
        return -1;
    }

    public SearchState createSearchState() {
        return createSearchState(-1);
    }

    public SearchState createSearchStateForSearchResultClick(int i) {
        return createSearchState(i);
    }

    private SearchState createSearchState(int i) {
        int i2;
        MultiSelectEntryContactListAdapter multiSelectEntryContactListAdapter = (MultiSelectEntryContactListAdapter) getAdapter();
        if (multiSelectEntryContactListAdapter == null) {
            return null;
        }
        SearchState searchState = new SearchState();
        if (multiSelectEntryContactListAdapter.getQueryString() == null) {
            i2 = 0;
        } else {
            i2 = multiSelectEntryContactListAdapter.getQueryString().length();
        }
        searchState.queryLength = i2;
        searchState.numPartitions = multiSelectEntryContactListAdapter.getPartitionCount();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (true) {
            if (i3 >= multiSelectEntryContactListAdapter.getPartitionCount()) {
                break;
            }
            Cursor cursor = multiSelectEntryContactListAdapter.getCursor(i3);
            if (cursor == null || cursor.isClosed()) {
                arrayList.clear();
            } else {
                arrayList.add(Integer.valueOf(cursor.getCount()));
                i3++;
            }
        }
        arrayList.clear();
        if (!arrayList.isEmpty()) {
            int i4 = 0;
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                i4 += ((Integer) arrayList.get(i5)).intValue();
            }
            searchState.numResults = i4;
        }
        if (i >= 0) {
            searchState.selectedPartition = multiSelectEntryContactListAdapter.getPartitionForPosition(i);
            searchState.selectedIndexInPartition = multiSelectEntryContactListAdapter.getOffsetInPartition(i);
            Cursor cursor2 = multiSelectEntryContactListAdapter.getCursor(searchState.selectedPartition);
            searchState.numResultsInSelectedPartition = (cursor2 == null || cursor2.isClosed()) ? -1 : cursor2.getCount();
            if (!arrayList.isEmpty()) {
                int i6 = 0;
                for (int i7 = 0; i7 < searchState.selectedPartition; i7++) {
                    i6 += ((Integer) arrayList.get(i7)).intValue();
                }
                searchState.selectedIndex = i6 + searchState.selectedIndexInPartition;
            }
        }
        return searchState;
    }

    /* access modifiers changed from: protected */
    public void setLayoutAnimation(final ViewGroup viewGroup, int i) {
        if (viewGroup != null) {
            viewGroup.setLayoutAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    viewGroup.setLayoutAnimation((LayoutAnimationController) null);
                }
            });
            viewGroup.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getActivity(), i));
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        View findViewById = getView().findViewById(R.id.account_filter_header_container);
        if (findViewById != null) {
            if (!(absListView == null || absListView.getChildAt(0) == null || absListView.getChildAt(0).getTop() >= 0)) {
                i++;
            }
            if (i == 0) {
                ViewCompat.setElevation(findViewById, ContactPhotoManager.OFFSET_DEFAULT);
            } else {
                ViewCompat.setElevation(findViewById, getResources().getDimension(R.dimen.contact_list_header_elevation));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void bindListHeaderCustom(View view, View view2) {
        bindListHeaderCommon(view, view2);
        TextView textView = (TextView) view2.findViewById(R.id.account_filter_header);
        textView.setText(R.string.listCustomView);
        textView.setAllCaps(false);
        ((ImageView) view2.findViewById(R.id.account_filter_icon)).setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void bindListHeader(Context context, View view, View view2, AccountWithDataSet accountWithDataSet, int i) {
        String str;
        if (i < 0) {
            hideHeaderAndAddPadding(context, view, view2);
            return;
        }
        bindListHeaderCommon(view, view2);
        AccountType accountType = AccountTypeManager.getInstance(context).getAccountType(accountWithDataSet.type, accountWithDataSet.dataSet);
        TextView textView = (TextView) view2.findViewById(R.id.account_filter_header);
        if (shouldShowAccountName(accountType)) {
            str = String.format(context.getResources().getQuantityString(R.plurals.contacts_count_with_account, i), new Object[]{Integer.valueOf(i), accountWithDataSet.name});
        } else {
            str = context.getResources().getQuantityString(R.plurals.contacts_count, i, new Object[]{Integer.valueOf(i)});
        }
        textView.setText(str);
        textView.setAllCaps(false);
        Drawable displayIcon = accountType != null ? accountType.getDisplayIcon(context) : null;
        ImageView imageView = (ImageView) view2.findViewById(R.id.account_filter_icon);
        if (accountType instanceof GoogleAccountType) {
            imageView.getLayoutParams().height = getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_size);
            imageView.getLayoutParams().width = getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_size);
            setMargins(imageView, getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_left_margin), getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_right_margin));
        } else {
            imageView.getLayoutParams().height = getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_size_alt);
            imageView.getLayoutParams().width = getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_size_alt);
            setMargins(imageView, getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_left_margin_alt), getResources().getDimensionPixelOffset(R.dimen.contact_browser_list_header_icon_right_margin_alt));
        }
        imageView.requestLayout();
        imageView.setVisibility(0);
        imageView.setImageDrawable(displayIcon);
    }

    private boolean shouldShowAccountName(AccountType accountType) {
        return (accountType.isGroupMembershipEditable() && (this instanceof GroupMembersFragment)) || GoogleAccountType.ACCOUNT_TYPE.equals(accountType.accountType);
    }

    private void setMargins(View view, int i, int i2) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i2);
            view.setLayoutParams(marginLayoutParams);
            view.requestLayout();
        }
    }

    private void bindListHeaderCommon(View view, View view2) {
        view2.setVisibility(0);
        setListViewPaddingTop(view, 0);
    }

    /* access modifiers changed from: protected */
    public void hideHeaderAndAddPadding(Context context, View view, View view2) {
        view2.setVisibility(8);
        setListViewPaddingTop(view, context.getResources().getDimensionPixelSize(R.dimen.contact_browser_list_item_padding_top_or_bottom));
    }

    private void setListViewPaddingTop(View view, int i) {
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
    }
}
