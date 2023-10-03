package com.android.bubble.stub;

import android.graphics.drawable.Drawable;
import com.android.bubble.Bubble;
import com.android.bubble.BubbleInfo;
import java.util.List;

public class BubbleStub implements Bubble {
    public void hide() {
    }

    public boolean isDismissed() {
        return false;
    }

    public boolean isVisible() {
        return false;
    }

    public void setBubbleInfo(BubbleInfo bubbleInfo) {
    }

    public void show() {
    }

    public void updateActions(List<BubbleInfo.Action> list) {
    }

    public void updateAvatar(Drawable drawable) {
    }

    public void updatePhotoAvatar(Drawable drawable) {
    }
}
