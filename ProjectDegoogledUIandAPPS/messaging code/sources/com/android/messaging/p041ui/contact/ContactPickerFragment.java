package com.android.messaging.p041ui.contact;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.action.C0813c;
import com.android.messaging.datamodel.action.C0826p;
import com.android.messaging.datamodel.action.C0827q;
import com.android.messaging.datamodel.action.GetOrCreateConversationAction;
import com.android.messaging.datamodel.data.C0918c;
import com.android.messaging.datamodel.data.C0920d;
import com.android.messaging.datamodel.data.C0922e;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.C1051K;
import com.android.messaging.p041ui.CustomHeaderViewPager;
import com.android.messaging.p041ui.contact.ContactListItemView;
import com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView;
import com.android.messaging.p041ui.p042a.C1083j;
import com.android.messaging.util.C1419X;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1486ya;
import com.android.messaging.util.ContactUtil;
import java.util.ArrayList;
import java.util.Set;

/* renamed from: com.android.messaging.ui.contact.ContactPickerFragment */
public class ContactPickerFragment extends Fragment implements C0920d, ContactListItemView.HostInterface, ContactRecipientAutoCompleteView.ContactChipsChangeListener, Toolbar.OnMenuItemClickListener, C0826p {
    public static final String FRAGMENT_TAG = "contactpicker";
    public static final int MODE_CHIPS_ONLY = 2;
    public static final int MODE_PICK_INITIAL_CONTACT = 1;
    public static final int MODE_PICK_MAX_PARTICIPANTS = 4;
    public static final int MODE_PICK_MORE_CONTACTS = 3;
    public static final int MODE_UNDEFINED = 0;
    private AllContactsListViewHolder mAllContactsListViewHolder;
    final C0783c mBinding = C0784d.m1315q(this);
    private View mComposeDivider;
    private int mContactPickingMode = 0;
    /* access modifiers changed from: private */
    public CustomHeaderViewPager mCustomHeaderViewPager;
    private FrequentContactsListViewHolder mFrequentContactsListViewHolder;
    /* access modifiers changed from: private */
    public ContactPickerFragmentHost mHost;
    private C0827q mMonitor;
    private View mPendingExplodeView;
    /* access modifiers changed from: private */
    public ContactRecipientAutoCompleteView mRecipientTextView;
    private View mRootView;
    private Set mSelectedPhoneNumbers = null;
    private Toolbar mToolbar;

    /* renamed from: com.android.messaging.ui.contact.ContactPickerFragment$ContactPickerFragmentHost */
    public interface ContactPickerFragmentHost {
        void invalidateActionBar();

        void onBackButtonPressed();

        void onGetOrCreateNewConversation(String str);

        void onInitiateAddMoreParticipants();

        void onParticipantCountChanged(boolean z);
    }

    private void invalidateContactLists() {
        this.mAllContactsListViewHolder.invalidateList();
        this.mFrequentContactsListViewHolder.invalidateList();
    }

    private void maybeGetOrCreateConversation() {
        ArrayList recipientParticipantDataForConversationCreation = this.mRecipientTextView.getRecipientParticipantDataForConversationCreation();
        if (C0922e.m1932la(recipientParticipantDataForConversationCreation.size())) {
            C1486ya.m3847Oa(R.string.too_many_participants);
        } else if (recipientParticipantDataForConversationCreation.size() > 0 && this.mMonitor == null) {
            this.mMonitor = GetOrCreateConversationAction.m1370a(recipientParticipantDataForConversationCreation, (Object) null, (C0826p) this);
        }
    }

    private void showHideContactPagerWithAnimation(final boolean z) {
        if (z != (this.mCustomHeaderViewPager.getVisibility() == 0)) {
            this.mCustomHeaderViewPager.animate().alpha(z ? 1.0f : 0.0f).setStartDelay(!z ? (long) C1486ya.f2355ZK : 0).withStartAction(new Runnable() {
                public void run() {
                    ContactPickerFragment.this.mCustomHeaderViewPager.setVisibility(0);
                    ContactPickerFragment.this.mCustomHeaderViewPager.setAlpha(z ? 0.0f : 1.0f);
                }
            }).withEndAction(new Runnable() {
                public void run() {
                    ContactPickerFragment.this.mCustomHeaderViewPager.setVisibility(z ? 0 : 8);
                    ContactPickerFragment.this.mCustomHeaderViewPager.setAlpha(1.0f);
                }
            });
        }
    }

    private void showImeKeyboard() {
        C1424b.m3594t(this.mRecipientTextView);
        this.mRecipientTextView.requestFocus();
        C1486ya.m3855a(this.mRootView, (Runnable) new Runnable() {
            public void run() {
                Activity activity = ContactPickerFragment.this.getActivity();
                if (activity != null) {
                    C1419X.get().mo8049c(activity, ContactPickerFragment.this.mRecipientTextView);
                }
            }
        });
        this.mRecipientTextView.invalidate();
    }

    private void startExplodeTransitionForContactLists(boolean z) {
        final Rect rect;
        if (C1464na.m3758Yj()) {
            Explode explode = new Explode();
            View view = this.mPendingExplodeView;
            if (view == null) {
                rect = null;
            } else {
                rect = C1486ya.m3858h(view);
            }
            explode.setDuration((long) C1486ya.f2355ZK);
            explode.setInterpolator(C1486ya.f2358bL);
            explode.setEpicenterCallback(new Transition.EpicenterCallback() {
                public Rect onGetEpicenter(Transition transition) {
                    return rect;
                }
            });
            TransitionManager.beginDelayedTransition(this.mCustomHeaderViewPager, explode);
            toggleContactListItemsVisibilityForPendingTransition(z);
        }
    }

    private void toggleContactListItemsVisibilityForPendingTransition(boolean z) {
        if (C1464na.m3758Yj()) {
            this.mAllContactsListViewHolder.toggleVisibilityForPendingTransition(z, this.mPendingExplodeView);
            this.mFrequentContactsListViewHolder.toggleVisibilityForPendingTransition(z, this.mPendingExplodeView);
        }
    }

    /* access modifiers changed from: private */
    public void updateTextInputButtonsVisibility() {
        Menu menu = this.mToolbar.getMenu();
        MenuItem findItem = menu.findItem(R.id.action_ime_dialpad_toggle);
        MenuItem findItem2 = menu.findItem(R.id.action_delete_text);
        if (this.mContactPickingMode != 1) {
            findItem2.setVisible(false);
            findItem.setVisible(false);
        } else if (TextUtils.isEmpty(this.mRecipientTextView.getText())) {
            findItem2.setVisible(false);
            findItem.setVisible(true);
        } else {
            findItem2.setVisible(true);
            findItem.setVisible(false);
        }
    }

    private void updateVisualsForContactPickingMode(boolean z) {
        if (this.mRootView != null) {
            Menu menu = this.mToolbar.getMenu();
            MenuItem findItem = menu.findItem(R.id.action_add_more_participants);
            MenuItem findItem2 = menu.findItem(R.id.action_confirm_participants);
            int i = this.mContactPickingMode;
            if (i == 1) {
                findItem.setVisible(false);
                findItem2.setVisible(false);
                this.mCustomHeaderViewPager.setVisibility(0);
                this.mComposeDivider.setVisibility(4);
                this.mRecipientTextView.setEnabled(true);
                showImeKeyboard();
            } else if (i == 2) {
                if (z) {
                    if (this.mPendingExplodeView == null) {
                        this.mPendingExplodeView = this.mToolbar;
                    }
                    startExplodeTransitionForContactLists(false);
                    C1083j.m2687a(this.mCustomHeaderViewPager, this.mPendingExplodeView, true, C1486ya.f2355ZK);
                    showHideContactPagerWithAnimation(false);
                } else {
                    this.mCustomHeaderViewPager.setVisibility(8);
                }
                findItem.setVisible(true);
                findItem2.setVisible(false);
                this.mComposeDivider.setVisibility(0);
                this.mRecipientTextView.setEnabled(true);
            } else if (i == 3) {
                if (z) {
                    this.mCustomHeaderViewPager.setVisibility(0);
                    toggleContactListItemsVisibilityForPendingTransition(false);
                    startExplodeTransitionForContactLists(true);
                }
                findItem.setVisible(false);
                findItem2.setVisible(true);
                this.mCustomHeaderViewPager.setVisibility(0);
                this.mComposeDivider.setVisibility(4);
                this.mRecipientTextView.setEnabled(true);
                showImeKeyboard();
            } else if (i != 4) {
                C1424b.fail("Unsupported contact picker mode!");
            } else {
                findItem.setVisible(false);
                findItem2.setVisible(true);
                this.mCustomHeaderViewPager.setVisibility(0);
                this.mComposeDivider.setVisibility(4);
                this.mRecipientTextView.setEnabled(false);
            }
            updateTextInputButtonsVisibility();
        }
    }

    public boolean isContactSelected(C0918c cVar) {
        Set set = this.mSelectedPhoneNumbers;
        return set != null && set.contains(C1474sa.getDefault().mo8222Ka(cVar.mo6409Ef().getDestination()));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        C1424b.m3592ia(this.mContactPickingMode != 0);
        updateVisualsForContactPickingMode(false);
        this.mHost.invalidateActionBar();
    }

    public void onAllContactsCursorUpdated(Cursor cursor) {
        this.mBinding.mo5935yf();
        this.mAllContactsListViewHolder.onContactsCursorUpdated(cursor);
    }

    public void onContactChipsChanged(int i, int i2) {
        C1424b.m3592ia(i != i2);
        int i3 = this.mContactPickingMode;
        if (i3 == 1) {
            maybeGetOrCreateConversation();
        } else if (i3 == 2 && i > 0 && this.mRecipientTextView.isFocused()) {
            this.mHost.onInitiateAddMoreParticipants();
        }
        this.mHost.onParticipantCountChanged(C0922e.m1931ka(i2));
        this.mSelectedPhoneNumbers = this.mRecipientTextView.getSelectedDestinations();
        invalidateContactLists();
    }

    public void onContactCustomColorLoaded(C0922e eVar) {
        this.mBinding.mo5929a(eVar);
        invalidateContactLists();
    }

    public void onContactListItemClicked(C0918c cVar, ContactListItemView contactListItemView) {
        if (!isContactSelected(cVar)) {
            if (this.mContactPickingMode == 1) {
                this.mPendingExplodeView = contactListItemView;
            }
            this.mRecipientTextView.appendRecipientEntry(cVar.mo6409Ef());
        } else if (this.mContactPickingMode != 1) {
            this.mRecipientTextView.removeRecipientEntry(cVar.mo6409Ef());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAllContactsListViewHolder = new AllContactsListViewHolder(getActivity(), this);
        this.mFrequentContactsListViewHolder = new FrequentContactsListViewHolder(getActivity(), this);
        if (ContactUtil.m3525Kj()) {
            this.mBinding.mo5930b(C0947h.get().mo6601a((Context) getActivity(), (C0920d) this));
            ((C0922e) this.mBinding.getData()).mo6429a(getLoaderManager(), this.mBinding);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.contact_picker_fragment, viewGroup, false);
        this.mRecipientTextView = (ContactRecipientAutoCompleteView) inflate.findViewById(R.id.recipient_text_view);
        this.mRecipientTextView.setThreshold(0);
        this.mRecipientTextView.setDropDownAnchor(R.id.compose_contact_divider);
        this.mRecipientTextView.setContactChipsListener(this);
        this.mRecipientTextView.setDropdownChipLayouter(new ContactDropdownLayouter(layoutInflater, getActivity(), this));
        this.mRecipientTextView.setAdapter(new ContactRecipientAdapter(getActivity(), this));
        this.mRecipientTextView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                ContactPickerFragment.this.updateTextInputButtonsVisibility();
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        C1051K[] kArr = {this.mFrequentContactsListViewHolder, this.mAllContactsListViewHolder};
        this.mCustomHeaderViewPager = (CustomHeaderViewPager) inflate.findViewById(R.id.contact_pager);
        this.mCustomHeaderViewPager.mo6933a(kArr);
        this.mCustomHeaderViewPager.mo6935v(-1);
        this.mCustomHeaderViewPager.setBackgroundColor(getResources().getColor(R.color.contact_picker_background));
        this.mCustomHeaderViewPager.setCurrentItem(0);
        this.mToolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        this.mToolbar.setNavigationIcon((int) R.drawable.ic_arrow_back_light);
        this.mToolbar.setNavigationContentDescription((int) R.string.back);
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContactPickerFragment.this.mHost.onBackButtonPressed();
            }
        });
        this.mToolbar.inflateMenu(R.menu.compose_menu);
        this.mToolbar.setOnMenuItemClickListener(this);
        this.mComposeDivider = inflate.findViewById(R.id.compose_contact_divider);
        this.mRootView = inflate;
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mBinding.isBound()) {
            this.mBinding.unbind();
        }
        C0827q qVar = this.mMonitor;
        if (qVar != null) {
            qVar.unregister();
        }
        this.mMonitor = null;
    }

    public void onEntryComplete() {
        int i = this.mContactPickingMode;
        if (i == 1 || i == 3 || i == 4) {
            maybeGetOrCreateConversation();
        }
    }

    public void onFrequentContactsCursorUpdated(Cursor cursor) {
        this.mBinding.mo5935yf();
        this.mFrequentContactsListViewHolder.onContactsCursorUpdated(cursor);
        if (cursor != null && cursor.getCount() == 0) {
            this.mCustomHeaderViewPager.setCurrentItem(1);
        }
    }

    public void onGetOrCreateConversationFailed(C0813c cVar, Object obj) {
        C1424b.m3592ia(cVar == this.mMonitor);
        C1430e.m3622e("MessagingApp", "onGetOrCreateConversationFailed");
        this.mMonitor = null;
    }

    public void onGetOrCreateConversationSucceeded(C0813c cVar, Object obj, String str) {
        boolean z = true;
        C1424b.m3592ia(cVar == this.mMonitor);
        if (str == null) {
            z = false;
        }
        C1424b.m3592ia(z);
        this.mRecipientTextView.setInputType(131073);
        this.mHost.onGetOrCreateNewConversation(str);
        this.mMonitor = null;
    }

    public void onInvalidContactChipsPruned(int i) {
        C1424b.m3592ia(i > 0);
        C1486ya.showToast(R.plurals.add_invalid_contact_error, i);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_more_participants /*2131361834*/:
                this.mHost.onInitiateAddMoreParticipants();
                return true;
            case R.id.action_confirm_participants /*2131361846*/:
                maybeGetOrCreateConversation();
                return true;
            case R.id.action_delete_text /*2131361853*/:
                C1424b.equals(1, this.mContactPickingMode);
                this.mRecipientTextView.setText("");
                return true;
            case R.id.action_ime_dialpad_toggle /*2131361857*/:
                if ((this.mRecipientTextView.getInputType() & 3) != 3) {
                    this.mRecipientTextView.setInputType(131075);
                    menuItem.setIcon(R.drawable.ic_ime_light);
                } else {
                    this.mRecipientTextView.setInputType(131073);
                    menuItem.setIcon(R.drawable.ic_numeric_dialpad);
                }
                C1419X.get().mo8049c(getActivity(), this.mRecipientTextView);
                return true;
            default:
                return false;
        }
    }

    public void setContactPickingMode(int i, boolean z) {
        int i2 = this.mContactPickingMode;
        if (i2 != i) {
            boolean z2 = true;
            if (!(i2 == 0 || ((i2 == 1 && i == 2) || ((this.mContactPickingMode == 2 && i == 3) || ((this.mContactPickingMode == 3 && i == 4) || (this.mContactPickingMode == 4 && i == 3)))))) {
                z2 = false;
            }
            C1424b.m3592ia(z2);
            this.mContactPickingMode = i;
            updateVisualsForContactPickingMode(z);
        }
    }

    public void setHost(ContactPickerFragmentHost contactPickerFragmentHost) {
        this.mHost = contactPickerFragmentHost;
    }

    public void updateActionBar(ActionBar actionBar) {
        actionBar.hide();
        C1486ya.m3850a(getActivity(), getResources().getColor(R.color.compose_notification_bar_background));
    }
}
