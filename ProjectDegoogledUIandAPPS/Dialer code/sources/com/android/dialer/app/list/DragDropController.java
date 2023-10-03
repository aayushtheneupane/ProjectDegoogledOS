package com.android.dialer.app.list;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class DragDropController {
    private final DragItemContainer dragItemContainer;
    private final int[] locationOnScreen = new int[2];
    private final List<OnDragDropListener> onDragDropListeners = new ArrayList();

    public interface DragItemContainer {
        PhoneFavoriteSquareTileView getViewForLocation(int i, int i2);
    }

    public DragDropController(DragItemContainer dragItemContainer2) {
        this.dragItemContainer = dragItemContainer2;
    }

    public void addOnDragDropListener(OnDragDropListener onDragDropListener) {
        if (!this.onDragDropListeners.contains(onDragDropListener)) {
            this.onDragDropListeners.add(onDragDropListener);
        }
    }

    public void handleDragFinished(int i, int i2, boolean z) {
        if (z) {
            for (int i3 = 0; i3 < this.onDragDropListeners.size(); i3++) {
                this.onDragDropListeners.get(i3).onDroppedOnRemove();
            }
        }
        for (int i4 = 0; i4 < this.onDragDropListeners.size(); i4++) {
            this.onDragDropListeners.get(i4).onDragFinished(i, i2);
        }
    }

    public void handleDragHovered(View view, int i, int i2) {
        view.getLocationOnScreen(this.locationOnScreen);
        int[] iArr = this.locationOnScreen;
        int i3 = i + iArr[0];
        int i4 = i2 + iArr[1];
        PhoneFavoriteSquareTileView viewForLocation = this.dragItemContainer.getViewForLocation(i3, i4);
        for (int i5 = 0; i5 < this.onDragDropListeners.size(); i5++) {
            this.onDragDropListeners.get(i5).onDragHovered(i3, i4, viewForLocation);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean handleDragStarted(View view, int i, int i2) {
        view.getLocationOnScreen(this.locationOnScreen);
        int[] iArr = this.locationOnScreen;
        int i3 = i + iArr[0];
        int i4 = i2 + iArr[1];
        PhoneFavoriteSquareTileView viewForLocation = this.dragItemContainer.getViewForLocation(i3, i4);
        if (viewForLocation == null) {
            return false;
        }
        for (int i5 = 0; i5 < this.onDragDropListeners.size(); i5++) {
            this.onDragDropListeners.get(i5).onDragStarted(i3, i4, viewForLocation);
        }
        return true;
    }
}
