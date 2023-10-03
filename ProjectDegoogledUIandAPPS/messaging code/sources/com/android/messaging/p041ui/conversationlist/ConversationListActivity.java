package com.android.messaging.p041ui.conversationlist;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1410N;

/* renamed from: com.android.messaging.ui.conversationlist.ConversationListActivity */
public class ConversationListActivity extends AbstractConversationListActivity {
    /* renamed from: E */
    public void mo7532E() {
        mo7526bb();
    }

    /* renamed from: Y */
    public boolean mo7533Y() {
        return !mo7528db();
    }

    /* renamed from: gb */
    public void mo7535gb() {
        C1040Ea.get().mo6985v(this);
    }

    /* renamed from: hb */
    public void mo7536hb() {
        C1040Ea.get().mo6986w(this);
    }

    /* renamed from: ib */
    public void mo7537ib() {
        C1040Ea.get().mo6948A(this);
    }

    /* renamed from: jb */
    public void mo7538jb() {
        C1040Ea.get().mo6958a((Context) this, (MessageData) null);
    }

    public void onBackPressed() {
        if (mo7528db()) {
            mo7526bb();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.conversation_list_activity);
        invalidateActionBar();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (super.onCreateOptionsMenu(menu)) {
            return true;
        }
        getMenuInflater().inflate(R.menu.conversation_list_fragment_menu, menu);
        MenuItem findItem = menu.findItem(R.id.action_debug_options);
        if (findItem != null) {
            C1410N.m3547Nj();
            findItem.setVisible(false).setEnabled(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_debug_options) {
            mo7529eb();
            return true;
        } else if (itemId != R.id.action_settings) {
            switch (itemId) {
                case R.id.action_show_archived /*2131361870*/:
                    mo7535gb();
                    return true;
                case R.id.action_show_blocked_contacts /*2131361871*/:
                    mo7536hb();
                    return true;
                case R.id.action_start_new_conversation /*2131361872*/:
                    mo7538jb();
                    return true;
                default:
                    return super.onOptionsItemSelected(menuItem);
            }
        } else {
            mo7537ib();
            return true;
        }
    }

    public void onResume() {
        super.onResume();
        supportInvalidateOptionsMenu();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        ConversationListFragment conversationListFragment = (ConversationListFragment) getFragmentManager().findFragmentById(R.id.conversation_list_fragment);
        if (z && conversationListFragment != null) {
            conversationListFragment.mo7558va();
        }
    }

    /* access modifiers changed from: protected */
    public void updateActionBar(ActionBar actionBar) {
        actionBar.setTitle((CharSequence) getString(R.string.app_name));
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setNavigationMode(0);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.action_bar_background_color)));
        actionBar.show();
        actionBar.setHomeAsUpIndicator((Drawable) null);
    }
}
