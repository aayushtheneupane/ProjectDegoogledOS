package com.android.dialer.app.list;

public interface OnDragDropListener {
    void onDragFinished(int i, int i2);

    void onDragHovered(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView);

    void onDragStarted(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView);

    void onDroppedOnRemove();
}
