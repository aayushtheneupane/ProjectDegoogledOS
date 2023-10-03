package com.android.messaging.p041ui.conversationlist;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;
import com.android.messaging.util.C1410N;

/* renamed from: com.android.messaging.ui.conversationlist.ArchivedConversationListActivity */
public class ArchivedConversationListActivity extends AbstractConversationListActivity {
    /* renamed from: E */
    public void mo7532E() {
        onBackPressed();
    }

    /* renamed from: Y */
    public boolean mo7533Y() {
        return false;
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
        getFragmentManager().beginTransaction().add(16908290, ConversationListFragment.m3074i("archived_mode")).commit();
        invalidateActionBar();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (super.onCreateOptionsMenu(menu)) {
            return true;
        }
        getMenuInflater().inflate(R.menu.archived_conversation_list_menu, menu);
        MenuItem findItem = menu.findItem(R.id.action_debug_options);
        if (findItem != null) {
            C1410N.m3547Nj();
            findItem.setVisible(false).setEnabled(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            mo7532E();
            return true;
        } else if (itemId != R.id.action_debug_options) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            mo7529eb();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void updateActionBar(ActionBar actionBar) {
        actionBar.setTitle((CharSequence) getString(R.string.archived_activity_title));
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.archived_conversation_action_bar_background_color)));
        actionBar.show();
        actionBar.setHomeAsUpIndicator((Drawable) null);
    }
}
