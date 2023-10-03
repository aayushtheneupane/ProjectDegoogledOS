package com.android.incallui.sessiondata;

import android.widget.ImageView;

public interface AvatarPresenter {
    ImageView getAvatarImageView();

    int getAvatarSize();

    boolean shouldShowAnonymousAvatar();
}
