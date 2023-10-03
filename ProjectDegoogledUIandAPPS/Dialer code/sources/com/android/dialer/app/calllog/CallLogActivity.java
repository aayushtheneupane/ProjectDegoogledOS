package com.android.dialer.app.calllog;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.view.ViewPager;
import android.support.p002v7.app.ActionBar;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.common.list.ViewPagerTabs;
import com.android.dialer.R;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.postcall.PostCall;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.TransactionSafeActivity;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CallLogActivity extends TransactionSafeActivity implements ViewPager.OnPageChangeListener {
    static final int TAB_INDEX_ALL = 0;
    static final int TAB_INDEX_MISSED = 1;
    /* access modifiers changed from: private */
    public CallLogFragment allCallsFragment;
    private boolean isResumed;
    /* access modifiers changed from: private */
    public CallLogFragment missedCallsFragment;
    private int selectedPageIndex;
    /* access modifiers changed from: private */
    public String[] tabTitles;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPagerTabs viewPagerTabs;

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public int getCount() {
            return 2;
        }

        public Fragment getItem(int i) {
            int access$000 = CallLogActivity.this.getRtlPosition(i);
            if (access$000 == 0) {
                return new CallLogFragment(-1, true);
            }
            if (access$000 == 1) {
                return new CallLogFragment(3, true);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("No fragment at position ", i));
        }

        public long getItemId(int i) {
            return (long) CallLogActivity.this.getRtlPosition(i);
        }

        public CharSequence getPageTitle(int i) {
            return CallLogActivity.this.tabTitles[i];
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            CallLogFragment callLogFragment = (CallLogFragment) super.instantiateItem(viewGroup, i);
            int access$000 = CallLogActivity.this.getRtlPosition(i);
            if (access$000 == 0) {
                CallLogFragment unused = CallLogActivity.this.allCallsFragment = callLogFragment;
            } else if (access$000 == 1) {
                CallLogFragment unused2 = CallLogActivity.this.missedCallsFragment = callLogFragment;
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid position: ", i));
            }
            return callLogFragment;
        }
    }

    /* access modifiers changed from: private */
    public int getRtlPosition(int i) {
        if (!CallUtil.isRtl()) {
            return i;
        }
        this.viewPagerAdapter.getCount();
        return 1 - i;
    }

    private void updateMissedCalls(int i) {
        if (i != this.selectedPageIndex) {
            int rtlPosition = getRtlPosition(i);
            if (rtlPosition == 0) {
                CallLogFragment callLogFragment = this.allCallsFragment;
                if (callLogFragment != null) {
                    callLogFragment.markMissedCallsAsReadAndRemoveNotifications();
                }
            } else if (rtlPosition == 1) {
                CallLogFragment callLogFragment2 = this.missedCallsFragment;
                if (callLogFragment2 != null) {
                    callLogFragment2.markMissedCallsAsReadAndRemoveNotifications();
                }
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid position: ", i));
            }
        }
    }

    public /* synthetic */ void lambda$onActivityResult$0$CallLogActivity(String str, View view) {
        startActivity(CallUtil.getSendSmsIntent(str));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4 && i2 == -1 && intent != null && intent.getBooleanExtra("has_enriched_call_data", false)) {
            String stringExtra = intent.getStringExtra("phone_number");
            Snackbar make = Snackbar.make(findViewById(R.id.calllog_frame), (CharSequence) getString(R.string.ec_data_deleted), 5000);
            make.setAction((int) R.string.view_conversation, (View.OnClickListener) new View.OnClickListener(stringExtra) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    CallLogActivity.this.lambda$onActivityResult$0$CallLogActivity(this.f$1, view);
                }
            });
            make.setActionTextColor(getResources().getColor(R.color.dialer_snackbar_action_text_color));
            make.show();
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        PerformanceReport.recordClick(UiAction$Type.PRESS_ANDROID_BACK_BUTTON);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.call_log_activity);
        getWindow().setBackgroundDrawable((Drawable) null);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setElevation(0.0f);
        Intent intent = getIntent();
        int i = (intent == null || intent.getIntExtra("android.provider.extra.CALL_TYPE_FILTER", -1) != 3) ? TAB_INDEX_ALL : 1;
        this.selectedPageIndex = i;
        this.tabTitles = new String[2];
        this.tabTitles[TAB_INDEX_ALL] = getString(R.string.call_log_all_title);
        this.tabTitles[1] = getString(R.string.call_log_missed_title);
        this.viewPager = (ViewPager) findViewById(R.id.call_log_pager);
        this.viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        this.viewPager.setAdapter(this.viewPagerAdapter);
        this.viewPager.setOffscreenPageLimit(1);
        this.viewPager.setOnPageChangeListener(this);
        this.viewPagerTabs = (ViewPagerTabs) findViewById(R.id.viewpager_header);
        this.viewPagerTabs.setViewPager(this.viewPager);
        this.viewPager.setCurrentItem(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call_log_options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (!isSafeToCommitTransactions()) {
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            PerformanceReport.recordClick(UiAction$Type.CLOSE_CALL_HISTORY_WITH_CANCEL_BUTTON);
            Intent intent = new Intent("com.android.dialer.main.impl.MAIN");
            intent.addFlags(67108864);
            startActivity(intent);
            return true;
        } else if (menuItem.getItemId() != R.id.delete_all) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            new ClearCallLogDialog().show(getFragmentManager(), "deleteCallLog");
            return true;
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.viewPagerTabs.onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.viewPagerTabs.onPageScrolled(i, f, i2);
    }

    public void onPageSelected(int i) {
        updateMissedCalls(i);
        this.selectedPageIndex = i;
        if (this.isResumed) {
            ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.CALL_LOG_FILTER, this);
        }
        this.viewPagerTabs.onPageSelected(i);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.isResumed = false;
        super.onPause();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.delete_all);
        CallLogFragment callLogFragment = this.allCallsFragment;
        if (!(callLogFragment == null || findItem == null)) {
            CallLogAdapter adapter = callLogFragment.getAdapter();
            findItem.setVisible(adapter != null && !adapter.isEmpty());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        PostCall.restartPerformanceRecordingIfARecentCallExist(this);
        if (!PerformanceReport.isRecording()) {
            PerformanceReport.startRecording();
        }
        this.isResumed = true;
        super.onResume();
        ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.CALL_LOG_FILTER, this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        ViewPager viewPager2;
        if (!isChangingConfigurations() && (viewPager2 = this.viewPager) != null) {
            this.selectedPageIndex = -1;
            updateMissedCalls(viewPager2.getCurrentItem());
        }
        super.onStop();
    }
}
