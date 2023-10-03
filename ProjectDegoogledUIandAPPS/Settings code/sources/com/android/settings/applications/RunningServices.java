package com.android.settings.applications;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.widget.LoadingViewController;
import com.havoc.config.center.C1715R;

public class RunningServices extends SettingsPreferenceFragment {
    private View mLoadingContainer;
    /* access modifiers changed from: private */
    public LoadingViewController mLoadingViewController;
    private Menu mOptionsMenu;
    private final Runnable mRunningProcessesAvail = new Runnable() {
        public void run() {
            RunningServices.this.mLoadingViewController.showContent(true);
        }
    };
    private RunningProcessesView mRunningProcessesView;

    public int getMetricsCategory() {
        return 404;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(C1715R.string.runningservices_settings_title);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.manage_applications_running, (ViewGroup) null);
        this.mRunningProcessesView = (RunningProcessesView) inflate.findViewById(C1715R.C1718id.running_processes);
        this.mRunningProcessesView.doCreate();
        this.mLoadingContainer = inflate.findViewById(C1715R.C1718id.loading_container);
        this.mLoadingViewController = new LoadingViewController(this.mLoadingContainer, this.mRunningProcessesView);
        return inflate;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        this.mOptionsMenu = menu;
        menu.add(0, 1, 1, C1715R.string.show_running_services);
        menu.add(0, 2, 2, C1715R.string.show_background_processes);
        updateOptionsMenu();
    }

    public void onResume() {
        super.onResume();
        this.mLoadingViewController.handleLoadingContainer(this.mRunningProcessesView.doResume(this, this.mRunningProcessesAvail), false);
    }

    public void onPause() {
        super.onPause();
        this.mRunningProcessesView.doPause();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ActionBar actionBar = getActivity().getActionBar();
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            this.mRunningProcessesView.mAdapter.setShowBackground(false);
            actionBar.setTitle(C1715R.string.runningservices_settings_title);
        } else if (itemId != 2) {
            return false;
        } else {
            this.mRunningProcessesView.mAdapter.setShowBackground(true);
            actionBar.setTitle(C1715R.string.background_processes_settings_title);
        }
        updateOptionsMenu();
        return true;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        updateOptionsMenu();
    }

    private void updateOptionsMenu() {
        boolean showBackground = this.mRunningProcessesView.mAdapter.getShowBackground();
        this.mOptionsMenu.findItem(1).setVisible(showBackground);
        this.mOptionsMenu.findItem(2).setVisible(!showBackground);
    }
}
