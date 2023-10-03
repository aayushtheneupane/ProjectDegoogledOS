package com.android.messaging.p041ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.p041ui.conversationlist.C1211C;
import com.android.messaging.p041ui.conversationlist.ShareIntentFragment;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1451h;
import com.android.messaging.widget.WidgetConversationProvider;

/* renamed from: com.android.messaging.ui.WidgetPickConversationActivity */
public class WidgetPickConversationActivity extends BaseBugleActivity implements C1211C {
    private int mAppWidgetId = 0;

    /* renamed from: n */
    public static void m2667n(int i) {
        C1451h Pd = C0967f.get().mo6653Pd();
        Pd.remove("conversation_id" + i);
    }

    /* renamed from: o */
    public static String m2668o(int i) {
        C1451h Pd = C0967f.get().mo6653Pd();
        return Pd.getString("conversation_id" + i, (String) null);
    }

    /* renamed from: Z */
    public void mo7108Z() {
        finish();
    }

    /* renamed from: a */
    public void mo7109a(C0934q qVar) {
        int i = this.mAppWidgetId;
        String Ue = qVar.mo6505Ue();
        C1451h Pd = C0967f.get().mo6653Pd();
        Pd.putString("conversation_id" + i, Ue);
        WidgetConversationProvider.m3887b((Context) this, this.mAppWidgetId);
        Intent intent = new Intent();
        intent.putExtra("appWidgetId", this.mAppWidgetId);
        setResult(-1, intent);
        finish();
    }

    public void onAttachFragment(Fragment fragment) {
        String action = getIntent().getAction();
        if (!"android.appwidget.action.APPWIDGET_CONFIGURE".equals(action)) {
            C1424b.fail("Unsupported action type: " + action);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(0);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mAppWidgetId = extras.getInt("appWidgetId", 0);
        }
        if (this.mAppWidgetId == 0) {
            finish();
        }
        ShareIntentFragment shareIntentFragment = new ShareIntentFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("hide_conv_button_key", true);
        shareIntentFragment.setArguments(bundle2);
        shareIntentFragment.show(getFragmentManager(), "ShareIntentFragment");
    }
}
