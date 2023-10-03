package com.android.dialer.app.list;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.android.dialer.app.calllog.CallLogFragment;
import com.android.dialer.app.calllog.VisualVoicemailCallLogFragment;
import com.android.dialer.contactsfragment.ContactsFragment;
import com.android.dialer.util.CallUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialtactsPagerAdapter extends FragmentPagerAdapter {
    private CallLogFragment callLogFragment;
    private ContactsFragment contactsFragment;
    private final List<Fragment> fragments = new ArrayList();
    private boolean hasActiveVoicemailProvider;
    private OldSpeedDialFragment oldSpeedDialFragment;
    private final String[] tabTitles;
    private CallLogFragment voicemailFragment;

    public DialtactsPagerAdapter(FragmentManager fragmentManager, String[] strArr, boolean z) {
        super(fragmentManager);
        this.tabTitles = strArr;
        this.hasActiveVoicemailProvider = z;
        this.fragments.addAll(Collections.nCopies(4, (Object) null));
    }

    public int getCount() {
        return this.hasActiveVoicemailProvider ? 4 : 3;
    }

    public Fragment getItem(int i) {
        new Object[1][0] = Integer.valueOf(i);
        int rtlPosition = getRtlPosition(i);
        if (rtlPosition == 0) {
            if (this.oldSpeedDialFragment == null) {
                this.oldSpeedDialFragment = new OldSpeedDialFragment();
            }
            return this.oldSpeedDialFragment;
        } else if (rtlPosition == 1) {
            if (this.callLogFragment == null) {
                this.callLogFragment = new CallLogFragment(-1, -1);
            }
            return this.callLogFragment;
        } else if (rtlPosition == 2) {
            if (this.contactsFragment == null) {
                ContactsFragment contactsFragment2 = new ContactsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("extra_header", 1);
                contactsFragment2.setArguments(bundle);
                this.contactsFragment = contactsFragment2;
            }
            return this.contactsFragment;
        } else if (rtlPosition == 3) {
            if (this.voicemailFragment == null) {
                this.voicemailFragment = new VisualVoicemailCallLogFragment();
                new Object[1][0] = this.voicemailFragment;
            }
            return this.voicemailFragment;
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("No fragment at position ", i));
        }
    }

    public long getItemId(int i) {
        return (long) getRtlPosition(i);
    }

    public int getItemPosition(Object obj) {
        return (this.hasActiveVoicemailProvider || this.fragments.indexOf(obj) != 3) ? -1 : -2;
    }

    public CharSequence getPageTitle(int i) {
        return this.tabTitles[i];
    }

    public int getRtlPosition(int i) {
        return CallUtil.isRtl() ? (getCount() - 1) - i : i;
    }

    public boolean hasActiveVoicemailProvider() {
        return this.hasActiveVoicemailProvider;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        new Object[1][0] = Integer.valueOf(i);
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        if (fragment instanceof OldSpeedDialFragment) {
            this.oldSpeedDialFragment = (OldSpeedDialFragment) fragment;
        } else {
            boolean z = fragment instanceof CallLogFragment;
            if (z && i == 1) {
                this.callLogFragment = (CallLogFragment) fragment;
            } else if (fragment instanceof ContactsFragment) {
                this.contactsFragment = (ContactsFragment) fragment;
            } else if (z && i == 3) {
                this.voicemailFragment = (CallLogFragment) fragment;
                this.voicemailFragment.toString();
            }
        }
        this.fragments.set(i, fragment);
        return fragment;
    }

    public void removeVoicemailFragment(FragmentManager fragmentManager) {
        if (this.voicemailFragment != null) {
            fragmentManager.beginTransaction().remove(this.voicemailFragment).commitAllowingStateLoss();
            this.voicemailFragment = null;
        }
    }

    public void setHasActiveVoicemailProvider(boolean z) {
        this.hasActiveVoicemailProvider = z;
    }
}
