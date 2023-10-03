package com.android.incallui.incall.impl;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.android.dialer.common.Assert;
import com.android.dialer.multimedia.MultimediaData;
import com.android.incallui.sessiondata.MultimediaFragment;

public class InCallPagerAdapter extends FragmentStatePagerAdapter {
    private MultimediaData attachments;
    private final boolean showInCallButtonGrid;

    public InCallPagerAdapter(FragmentManager fragmentManager, MultimediaData multimediaData, boolean z) {
        super(fragmentManager);
        this.attachments = multimediaData;
        this.showInCallButtonGrid = z;
    }

    public int getButtonGridPosition() {
        return getCount() - 1;
    }

    public int getCount() {
        boolean z = true;
        int i = this.showInCallButtonGrid ? 1 : 0;
        MultimediaData multimediaData = this.attachments;
        if (multimediaData != null && multimediaData.hasData()) {
            i++;
        }
        if (i <= 0) {
            z = false;
        }
        Assert.checkArgument(z, "InCallPager adapter doesn't have any pages.", new Object[0]);
        return i;
    }

    public Fragment getItem(int i) {
        if (!this.showInCallButtonGrid) {
            return MultimediaFragment.newInstance(this.attachments, true, false, false);
        }
        if (i == getButtonGridPosition()) {
            return new InCallButtonGridFragment();
        }
        return MultimediaFragment.newInstance(this.attachments, true, false, false);
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public void setAttachments(MultimediaData multimediaData) {
        if (this.attachments != multimediaData) {
            this.attachments = multimediaData;
            notifyDataSetChanged();
        }
    }
}
