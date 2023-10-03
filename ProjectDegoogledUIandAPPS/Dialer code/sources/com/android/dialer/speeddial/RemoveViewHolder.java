package com.android.dialer.speeddial;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;

public class RemoveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final View removeViewContent;

    RemoveViewHolder(View view) {
        super(view);
        this.removeViewContent = view;
    }

    /* access modifiers changed from: package-private */
    public void hide() {
        this.removeViewContent.setVisibility(4);
        this.removeViewContent.setAlpha(1.0f);
        this.removeViewContent.animate().alpha(0.0f).start();
    }

    public void onClick(View view) {
    }

    /* access modifiers changed from: package-private */
    public void show() {
        this.removeViewContent.setVisibility(0);
        this.removeViewContent.setAlpha(0.0f);
        this.removeViewContent.animate().alpha(1.0f).start();
    }
}
