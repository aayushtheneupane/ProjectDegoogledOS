package com.android.dialer.speeddial;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.speeddial.SpeedDialFragment;

public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Button addButton;
    private final TextView headerText;
    private final SpeedDialHeaderListener listener;

    public interface SpeedDialHeaderListener {
    }

    public HeaderViewHolder(View view, SpeedDialHeaderListener speedDialHeaderListener) {
        super(view);
        this.listener = speedDialHeaderListener;
        this.headerText = (TextView) view.findViewById(R.id.speed_dial_header_text);
        this.addButton = (Button) view.findViewById(R.id.speed_dial_add_button);
        this.addButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        ((SpeedDialFragment.SpeedDialFragmentHeaderListener) this.listener).onAddFavoriteClicked();
    }

    public void setHeaderText(int i) {
        this.headerText.setText(i);
    }

    public void showAddButton(boolean z) {
        this.addButton.setVisibility(z ? 0 : 8);
    }
}
