package com.android.messaging.p041ui.conversation;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.contact.ContactPickerFragment;
import com.android.messaging.p041ui.conversationlist.ConversationListActivity;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1486ya;
import com.android.vcard.VCardConfig;

/* renamed from: com.android.messaging.ui.conversation.ConversationActivity */
public class ConversationActivity extends BugleActionBarActivity implements ContactPickerFragment.ContactPickerFragmentHost, C1144M, C1187p {

    /* renamed from: Ub */
    private ConversationActivityUiState f1781Ub;

    /* renamed from: Vb */
    private boolean f1782Vb;

    /* renamed from: Wb */
    private boolean f1783Wb;

    /* renamed from: Em */
    private C1146O m2802Em() {
        return (C1146O) getFragmentManager().findFragmentByTag("conversation");
    }

    /* renamed from: ra */
    private void m2803ra(boolean z) {
        if (!this.f1782Vb && !this.f1783Wb) {
            C1424b.m3594t(this.f1781Ub);
            Intent intent = getIntent();
            String Ue = this.f1781Ub.mo7334Ue();
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            boolean ij = this.f1781Ub.mo7343ij();
            boolean hj = this.f1781Ub.mo7342hj();
            C1146O Em = m2802Em();
            if (ij) {
                C1424b.m3594t(Ue);
                if (Em == null) {
                    Em = new C1146O();
                    beginTransaction.add(R.id.conversation_fragment_container, Em, "conversation");
                }
                MessageData messageData = (MessageData) intent.getParcelableExtra("draft_data");
                if (!hj) {
                    intent.removeExtra("draft_data");
                }
                Em.mo7391a((C1144M) this);
                Em.mo7387a((Context) this, Ue, messageData);
            } else if (Em != null) {
                Em.mo7426ua();
                beginTransaction.remove(Em);
            }
            ContactPickerFragment contactPickerFragment = (ContactPickerFragment) getFragmentManager().findFragmentByTag(ContactPickerFragment.FRAGMENT_TAG);
            if (hj) {
                if (contactPickerFragment == null) {
                    contactPickerFragment = new ContactPickerFragment();
                    beginTransaction.add(R.id.contact_picker_fragment_container, contactPickerFragment, ContactPickerFragment.FRAGMENT_TAG);
                }
                contactPickerFragment.setHost(this);
                contactPickerFragment.setContactPickingMode(this.f1781Ub.mo7339ej(), z);
            } else if (contactPickerFragment != null) {
                beginTransaction.remove(contactPickerFragment);
            }
            beginTransaction.commit();
            invalidateActionBar();
        }
    }

    /* renamed from: I */
    public void mo7324I() {
        this.f1781Ub.mo7341gj();
    }

    /* renamed from: X */
    public boolean mo7325X() {
        return !this.f1783Wb && hasWindowFocus();
    }

    /* renamed from: a */
    public void mo7326a(int i) {
    }

    /* renamed from: a */
    public void mo7327a(int i, int i2, boolean z) {
        C1424b.m3592ia(i != i2);
        m2803ra(z);
    }

    /* renamed from: e */
    public void mo7328e() {
        if (C1464na.m3758Yj()) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    /* renamed from: e */
    public void mo7329e(int i) {
    }

    /* renamed from: g */
    public void mo6914g(int i) {
        super.mo6914g(i);
        invalidateActionBar();
    }

    /* renamed from: k */
    public void mo7330k() {
        invalidateActionBar();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 2 && i2 == -1) {
            C1146O Em = m2802Em();
            if (Em != null) {
                Em.mo7422ra();
            } else {
                C1430e.m3622e("MessagingApp", "ConversationFragment is missing after launching AttachmentChooserActivity!");
            }
        } else if (i2 == 1) {
            finish();
        }
    }

    public void onBackButtonPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        if (mo6908K() != null) {
            mo6907G();
            return;
        }
        C1146O Em = m2802Em();
        if (Em == null || !Em.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.conversation_activity);
        Intent intent = getIntent();
        if (bundle != null) {
            this.f1781Ub = (ConversationActivityUiState) bundle.getParcelable("uistate");
        } else if (intent.getBooleanExtra("goto_conv_list", false)) {
            finish();
            Intent intent2 = new Intent(this, ConversationListActivity.class);
            intent2.setFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
            startActivity(intent2);
            return;
        }
        if (this.f1781Ub == null) {
            this.f1781Ub = new ConversationActivityUiState(intent.getStringExtra("conversation_id"));
        }
        this.f1781Ub.mo7336a(this);
        this.f1782Vb = false;
        m2803ra(false);
        String stringExtra = intent.getStringExtra("attachment_uri");
        if (!TextUtils.isEmpty(stringExtra)) {
            String stringExtra2 = intent.getStringExtra("attachment_type");
            Rect h = C1486ya.m3858h(findViewById(R.id.conversation_and_compose_container));
            if (C1481w.isImageType(stringExtra2)) {
                C1040Ea.get().mo6952a((Activity) this, Uri.parse(stringExtra), h, MessagingContentProvider.m1267l(this.f1781Ub.mo7334Ue()));
            } else if (C1481w.m3830Ea(stringExtra2)) {
                C1040Ea.get().mo6972d((Context) this, Uri.parse(stringExtra));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ConversationActivityUiState conversationActivityUiState = this.f1781Ub;
        if (conversationActivityUiState != null) {
            conversationActivityUiState.mo7336a((C1187p) null);
        }
    }

    public void onGetOrCreateNewConversation(String str) {
        C1424b.m3592ia(str != null);
        this.f1781Ub.mo7345ta(str);
    }

    public void onInitiateAddMoreParticipants() {
        this.f1781Ub.mo7340fj();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (super.onOptionsItemSelected(menuItem)) {
            return true;
        }
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        mo7333sa();
        return true;
    }

    public void onParticipantCountChanged(boolean z) {
        this.f1781Ub.mo7335Z(z);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f1783Wb = true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f1782Vb = false;
        this.f1783Wb = false;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("uistate", this.f1781Ub.clone());
        this.f1782Vb = true;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        C1146O Em = m2802Em();
        if (z && Em != null) {
            Em.mo7424ta();
        }
    }

    /* renamed from: p */
    public boolean mo7332p() {
        return this.f1781Ub.mo7344p();
    }

    /* renamed from: sa */
    public void mo7333sa() {
        C1146O Em = m2802Em();
        if (Em == null || !Em.mo7423sa()) {
            mo7328e();
        }
    }

    public void updateActionBar(ActionBar actionBar) {
        actionBar.setHomeAsUpIndicator((Drawable) null);
        C1146O Em = m2802Em();
        ContactPickerFragment contactPickerFragment = (ContactPickerFragment) getFragmentManager().findFragmentByTag(ContactPickerFragment.FRAGMENT_TAG);
        if (contactPickerFragment != null && this.f1781Ub.mo7342hj()) {
            contactPickerFragment.updateActionBar(actionBar);
        } else if (Em != null && this.f1781Ub.mo7343ij()) {
            Em.updateActionBar(actionBar);
        }
    }
}
