package com.android.messaging.p041ui.conversation;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.p016v4.media.session.C0107q;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversation.C */
class C1133C implements ActionMode.Callback {
    final /* synthetic */ C1146O this$0;

    C1133C(C1146O o) {
        this.this$0 = o;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        C0936s data = this.this$0.f1834Na.getData();
        String messageId = data.getMessageId();
        switch (menuItem.getItemId()) {
            case R.id.action_delete_message /*2131361852*/:
                if (this.this$0.f1834Na != null) {
                    this.this$0.mo7399f(messageId);
                }
                return true;
            case R.id.action_download /*2131361855*/:
                if (this.this$0.f1834Na != null) {
                    this.this$0.mo7401g(messageId);
                    this.this$0.mHost.mo7370G();
                }
                return true;
            case R.id.action_send /*2131361867*/:
                if (this.this$0.f1834Na != null) {
                    this.this$0.mo7403h(messageId);
                    this.this$0.mHost.mo7370G();
                }
                return true;
            case R.id.copy_text /*2131361966*/:
                C1424b.m3592ia(data.hasText());
                ((ClipboardManager) this.this$0.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, data.getText()));
                this.this$0.mHost.mo7370G();
                return true;
            case R.id.details_menu /*2131361979*/:
                C1200va.m3041a((Context) this.this$0.getActivity(), data, ((C0931n) this.this$0.mBinding.getData()).getParticipants(), ((C0931n) this.this$0.mBinding.getData()).mo6462Y(data.mo6559sg()));
                this.this$0.mHost.mo7370G();
                return true;
            case R.id.forward_message_menu /*2131362011*/:
                C1040Ea.get().mo6967b((Context) this.this$0.getActivity(), ((C0931n) this.this$0.mBinding.getData()).mo6473b(data));
                this.this$0.mHost.mo7370G();
                return true;
            case R.id.save_attachment /*2131362113*/:
                if (C1464na.m3753Tj()) {
                    C1145N n = new C1145N(this.this$0.getActivity());
                    for (MessagePartData messagePartData : data.mo6534_f()) {
                        n.mo7375b(messagePartData.getContentUri(), messagePartData.getContentType());
                    }
                    if (n.mo7377vb() > 0) {
                        n.mo8233b(new Void[0]);
                        this.this$0.mHost.mo7370G();
                    }
                } else {
                    this.this$0.getActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
                }
                return true;
            case R.id.share_message_menu /*2131362133*/:
                MessagePartData g = this.this$0.f1835Oa;
                if (this.this$0.f1835Oa == null && C0107q.isAllWhitespace(data.getText())) {
                    List _f = data.mo6534_f();
                    if (_f.size() > 0) {
                        g = (MessagePartData) _f.get(0);
                    }
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                if (g == null) {
                    intent.putExtra("android.intent.extra.TEXT", data.getText());
                    intent.setType("text/plain");
                } else {
                    intent.putExtra("android.intent.extra.STREAM", g.getContentUri());
                    intent.setType(g.getContentType());
                }
                this.this$0.startActivity(Intent.createChooser(intent, this.this$0.getResources().getText(R.string.action_share)));
                this.this$0.mHost.mo7370G();
                return true;
            default:
                return false;
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        boolean z = false;
        if (this.this$0.f1834Na == null) {
            return false;
        }
        C0936s data = this.this$0.f1834Na.getData();
        this.this$0.getActivity().getMenuInflater().inflate(R.menu.conversation_fragment_select_menu, menu);
        menu.findItem(R.id.action_download).setVisible(data.mo6528Dg());
        menu.findItem(R.id.action_send).setVisible(data.mo6529Eg());
        menu.findItem(R.id.share_message_menu).setVisible(data.mo6540dg());
        MenuItem findItem = menu.findItem(R.id.save_attachment);
        if (this.this$0.f1835Oa != null) {
            z = true;
        }
        findItem.setVisible(z);
        menu.findItem(R.id.forward_message_menu).setVisible(data.mo6540dg());
        menu.findItem(R.id.copy_text).setVisible(data.mo6539cg());
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.this$0.m2856a((ConversationMessageView) null, (MessagePartData) null);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }
}
