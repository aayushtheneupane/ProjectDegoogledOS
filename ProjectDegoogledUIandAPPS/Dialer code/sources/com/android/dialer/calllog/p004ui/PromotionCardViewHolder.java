package com.android.dialer.calllog.p004ui;

import android.support.p002v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.calllog.p004ui.PromotionCardViewHolder;
import com.android.dialer.promotion.Promotion;

/* renamed from: com.android.dialer.calllog.ui.PromotionCardViewHolder */
public class PromotionCardViewHolder extends RecyclerView.ViewHolder {
    private final Button okButton;
    private final Promotion promotion;

    /* renamed from: com.android.dialer.calllog.ui.PromotionCardViewHolder$DismissListener */
    interface DismissListener {
        void onDismiss();
    }

    PromotionCardViewHolder(View view, Promotion promotion2) {
        super(view);
        this.promotion = promotion2;
        ((ImageView) view.findViewById(R.id.new_call_log_promotion_card_icon)).setImageResource(promotion2.getIconRes());
        ((TextView) view.findViewById(R.id.new_call_log_promotion_card_title)).setText(promotion2.getTitle());
        TextView textView = (TextView) view.findViewById(R.id.new_call_log_promotion_card_details);
        textView.setText(promotion2.getDetails());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.okButton = (Button) view.findViewById(R.id.new_call_log_promotion_card_ok);
    }

    public /* synthetic */ void lambda$setDismissListener$0$PromotionCardViewHolder(DismissListener dismissListener, View view) {
        this.promotion.dismiss();
        dismissListener.onDismiss();
    }

    /* access modifiers changed from: package-private */
    public void setDismissListener(DismissListener dismissListener) {
        this.okButton.setOnClickListener(new View.OnClickListener(dismissListener) {
            private final /* synthetic */ PromotionCardViewHolder.DismissListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                PromotionCardViewHolder.this.lambda$setDismissListener$0$PromotionCardViewHolder(this.f$1, view);
            }
        });
    }
}
