package com.android.settings.wifi.calling;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.ims.ImsManager;
import com.android.internal.util.CollectionUtils;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.network.SubscriptionUtil;
import com.android.settings.search.actionbar.SearchMenuController;
import com.android.settings.support.actionbar.HelpMenuController;
import com.android.settings.support.actionbar.HelpResourceProvider;
import com.android.settings.widget.RtlCompatibleViewPager;
import com.android.settings.widget.SlidingTabLayout;
import com.android.settings.wifi.tether.TetherService;
import com.android.settingslib.core.lifecycle.ObservableFragment;
import com.havoc.config.center.C1715R;
import java.util.List;

public class WifiCallingSettings extends InstrumentedFragment implements HelpResourceProvider {
    private WifiCallingViewPagerAdapter mPagerAdapter;
    /* access modifiers changed from: private */
    public List<SubscriptionInfo> mSil;
    private SlidingTabLayout mTabLayout;
    private RtlCompatibleViewPager mViewPager;

    public int getHelpResource() {
        return C1715R.string.help_uri_wifi_calling;
    }

    public int getMetricsCategory() {
        return 105;
    }

    private final class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        private InternalViewPagerListener() {
        }

        public void onPageSelected(int i) {
            WifiCallingSettings.this.updateTitleForCurrentSub();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.wifi_calling_settings_tabs, viewGroup, false);
        this.mTabLayout = (SlidingTabLayout) inflate.findViewById(C1715R.C1718id.sliding_tabs);
        this.mViewPager = (RtlCompatibleViewPager) inflate.findViewById(C1715R.C1718id.view_pager);
        this.mPagerAdapter = new WifiCallingViewPagerAdapter(getChildFragmentManager(), this.mViewPager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new InternalViewPagerListener());
        maybeSetViewForSubId();
        return inflate;
    }

    private void maybeSetViewForSubId() {
        Intent intent;
        if (this.mSil != null && (intent = getActivity().getIntent()) != null) {
            int intExtra = intent.getIntExtra("android.provider.extra.SUB_ID", -1);
            if (SubscriptionManager.isValidSubscriptionId(intExtra)) {
                for (SubscriptionInfo next : this.mSil) {
                    if (intExtra == next.getSubscriptionId()) {
                        this.mViewPager.setCurrentItem(this.mSil.indexOf(next));
                        return;
                    }
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        SearchMenuController.init((InstrumentedFragment) this);
        HelpMenuController.init((ObservableFragment) this);
        updateSubList();
    }

    public void onStart() {
        super.onStart();
        List<SubscriptionInfo> list = this.mSil;
        if (list == null || list.size() <= 1) {
            this.mTabLayout.setVisibility(8);
        } else {
            this.mTabLayout.setViewPager(this.mViewPager);
        }
        updateTitleForCurrentSub();
    }

    final class WifiCallingViewPagerAdapter extends FragmentPagerAdapter {
        private final RtlCompatibleViewPager mViewPager;

        public WifiCallingViewPagerAdapter(FragmentManager fragmentManager, RtlCompatibleViewPager rtlCompatibleViewPager) {
            super(fragmentManager);
            this.mViewPager = rtlCompatibleViewPager;
        }

        public CharSequence getPageTitle(int i) {
            return String.valueOf(((SubscriptionInfo) WifiCallingSettings.this.mSil.get(i)).getDisplayName());
        }

        public Fragment getItem(int i) {
            Log.d("WifiCallingSettings", "Adapter getItem " + i);
            Bundle bundle = new Bundle();
            bundle.putBoolean("need_search_icon_in_action_bar", false);
            bundle.putInt(TetherService.EXTRA_SUBID, ((SubscriptionInfo) WifiCallingSettings.this.mSil.get(i)).getSubscriptionId());
            WifiCallingSettingsForSub wifiCallingSettingsForSub = new WifiCallingSettingsForSub();
            wifiCallingSettingsForSub.setArguments(bundle);
            return wifiCallingSettingsForSub;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            Log.d("WifiCallingSettings", "Adapter instantiateItem " + i);
            return super.instantiateItem(viewGroup, this.mViewPager.getRtlAwareIndex(i));
        }

        public int getCount() {
            if (WifiCallingSettings.this.mSil == null) {
                Log.d("WifiCallingSettings", "Adapter getCount null mSil ");
                return 0;
            }
            Log.d("WifiCallingSettings", "Adapter getCount " + WifiCallingSettings.this.mSil.size());
            return WifiCallingSettings.this.mSil.size();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isWfcEnabledByPlatform(SubscriptionInfo subscriptionInfo) {
        return ImsManager.getInstance(getActivity(), subscriptionInfo.getSimSlotIndex()).isWfcEnabledByPlatform();
    }

    /* access modifiers changed from: package-private */
    public boolean isWfcProvisionedOnDevice(SubscriptionInfo subscriptionInfo) {
        return ImsManager.getInstance(getActivity(), subscriptionInfo.getSimSlotIndex()).isWfcProvisionedOnDevice();
    }

    private void updateSubList() {
        this.mSil = SubscriptionUtil.getActiveSubscriptions((SubscriptionManager) getContext().getSystemService(SubscriptionManager.class));
        if (this.mSil != null) {
            int i = 0;
            while (i < this.mSil.size()) {
                SubscriptionInfo subscriptionInfo = this.mSil.get(i);
                if (!isWfcEnabledByPlatform(subscriptionInfo) || !isWfcProvisionedOnDevice(subscriptionInfo)) {
                    this.mSil.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTitleForCurrentSub() {
        if (CollectionUtils.size(this.mSil) > 1) {
            getActivity().getActionBar().setTitle(SubscriptionManager.getResourcesForSubId(getContext(), this.mSil.get(this.mViewPager.getCurrentItem()).getSubscriptionId()).getString(C1715R.string.wifi_calling_settings_title));
        }
    }
}
