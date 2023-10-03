package com.android.messaging.p041ui.conversation;

import android.graphics.Rect;
import androidx.recyclerview.widget.C0581o;
import androidx.recyclerview.widget.C0586qa;
import com.android.messaging.R;
import com.android.messaging.datamodel.action.InsertNewMessageAction;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.p041ui.AttachmentPreview;
import com.android.messaging.p041ui.p042a.C1079f;
import com.android.messaging.util.C1486ya;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversation.H */
class C1139H extends C0581o {

    /* renamed from: if */
    private C1079f f1822if;
    private final List mAddAnimations = new ArrayList();
    final /* synthetic */ C1146O this$0;

    C1139H(C1146O o) {
        this.this$0 = o;
    }

    public void endAnimations() {
        for (C0586qa qaVar : this.mAddAnimations) {
            qaVar.itemView.clearAnimation();
        }
        this.mAddAnimations.clear();
        C1079f fVar = this.f1822if;
        if (fVar != null) {
            fVar.cancel();
        }
        super.endAnimations();
    }

    /* renamed from: n */
    public boolean mo5176n(C0586qa qaVar) {
        ConversationMessageView conversationMessageView = (ConversationMessageView) qaVar.itemView;
        C0936s data = conversationMessageView.getData();
        if (this.mAddAnimations.remove(qaVar)) {
            qaVar.itemView.clearAnimation();
        }
        super.mo5179w(qaVar);
        long currentTimeMillis = System.currentTimeMillis() - data.mo6558rg();
        if (data.mo6558rg() != InsertNewMessageAction.m1376He() || data.mo6546gg() || currentTimeMillis >= 500) {
            super.mo5176n(qaVar);
            return true;
        }
        ConversationMessageBubbleView conversationMessageBubbleView = (ConversationMessageBubbleView) conversationMessageView.findViewById(R.id.message_content);
        Rect h = C1486ya.m3858h(this.this$0.f1828Ha);
        Rect h2 = C1486ya.m3858h(this.this$0.f1828Ha.findViewById(R.id.compose_message_text));
        AttachmentPreview attachmentPreview = (AttachmentPreview) this.this$0.f1828Ha.findViewById(R.id.attachment_draft_view);
        Rect h3 = C1486ya.m3858h(attachmentPreview);
        if (attachmentPreview.getVisibility() == 0) {
            h.top = h3.top;
        } else {
            h.top = h2.top;
        }
        h.top -= conversationMessageView.getPaddingTop();
        h.bottom = h2.bottom;
        h.left = conversationMessageView.getPaddingRight() + h.left;
        conversationMessageView.setAlpha(0.0f);
        this.f1822if = new C1079f(h, conversationMessageView);
        this.f1822if.mo7125c((Runnable) new C1137F(this, h2, attachmentPreview, conversationMessageBubbleView));
        this.f1822if.mo7126d((Runnable) new C1138G(this, conversationMessageView, qaVar));
        this.f1822if.mo7127dc();
        this.mAddAnimations.add(qaVar);
        return true;
    }

    /* renamed from: w */
    public void mo5179w(C0586qa qaVar) {
        if (this.mAddAnimations.remove(qaVar)) {
            qaVar.itemView.clearAnimation();
        }
        super.mo5179w(qaVar);
    }
}
