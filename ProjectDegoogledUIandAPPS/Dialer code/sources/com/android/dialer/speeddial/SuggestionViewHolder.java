package com.android.dialer.speeddial;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.historyitemactions.HistoryItemBottomSheetHeaderInfo;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.speeddial.SpeedDialFragment;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;
import com.android.dialer.widget.ContactPhotoView;

public class SuggestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final SuggestedContactsListener listener;
    private final TextView nameOrNumberView;
    private final TextView numberView;
    private final ContactPhotoView photoView;
    private SpeedDialUiItem speedDialUiItem;

    public interface SuggestedContactsListener {
    }

    SuggestionViewHolder(View view, SuggestedContactsListener suggestedContactsListener) {
        super(view);
        this.photoView = (ContactPhotoView) view.findViewById(R.id.avatar);
        this.nameOrNumberView = (TextView) view.findViewById(R.id.name);
        this.numberView = (TextView) view.findViewById(R.id.number);
        this.itemView.setOnClickListener(this);
        view.findViewById(R.id.overflow).setOnClickListener(this);
        this.listener = suggestedContactsListener;
    }

    public void bind(Context context, SpeedDialUiItem speedDialUiItem2) {
        Assert.isNotNull(speedDialUiItem2.defaultChannel());
        this.speedDialUiItem = speedDialUiItem2;
        String formatNumber = PhoneNumberHelper.formatNumber(context, speedDialUiItem2.defaultChannel().number(), R$style.getCurrentCountryIso(context));
        String label = speedDialUiItem2.defaultChannel().label();
        if (!TextUtils.isEmpty(label)) {
            formatNumber = context.getString(R.string.call_subject_type_and_number, new Object[]{label, formatNumber});
        }
        this.nameOrNumberView.setText(speedDialUiItem2.name());
        this.numberView.setText(formatNumber);
        this.photoView.setPhoto(speedDialUiItem2.getPhotoInfo());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.overflow) {
            SuggestedContactsListener suggestedContactsListener = this.listener;
            SpeedDialUiItem speedDialUiItem2 = this.speedDialUiItem;
            HistoryItemBottomSheetHeaderInfo.Builder newBuilder = HistoryItemBottomSheetHeaderInfo.newBuilder();
            newBuilder.setPhotoInfo(this.speedDialUiItem.getPhotoInfo());
            newBuilder.setPrimaryText(this.nameOrNumberView.getText().toString());
            newBuilder.setSecondaryText(this.numberView.getText().toString());
            ((SpeedDialFragment.SpeedDialSuggestedListener) suggestedContactsListener).onOverFlowMenuClicked(speedDialUiItem2, (HistoryItemBottomSheetHeaderInfo) newBuilder.build());
            return;
        }
        ((SpeedDialFragment.SpeedDialSuggestedListener) this.listener).onRowClicked(this.speedDialUiItem.defaultChannel());
    }
}
