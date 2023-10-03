package com.android.dialer.callcomposer;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentPagerAdapter;
import com.android.dialer.common.Assert;

public class CallComposerPagerAdapter extends FragmentPagerAdapter {
    private final int messageComposerCharLimit;

    public CallComposerPagerAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager);
        this.messageComposerCharLimit = i;
    }

    public int getCount() {
        return 3;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return new CameraComposerFragment();
        }
        if (i == 1) {
            return new GalleryComposerFragment();
        }
        if (i == 2) {
            int i2 = this.messageComposerCharLimit;
            MessageComposerFragment messageComposerFragment = new MessageComposerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("char_limit", i2);
            messageComposerFragment.setArguments(bundle);
            return messageComposerFragment;
        }
        Assert.fail();
        throw null;
    }
}
