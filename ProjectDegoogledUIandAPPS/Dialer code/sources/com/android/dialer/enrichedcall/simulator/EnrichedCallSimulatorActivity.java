package com.android.dialer.enrichedcall.simulator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;

public class EnrichedCallSimulatorActivity extends AppCompatActivity implements EnrichedCallManager.StateChangedListener, View.OnClickListener {
    private Button refreshButton;
    private SessionsAdapter sessionsAdapter;

    private EnrichedCallManager getEnrichedCallManager() {
        return EnrichedCallComponent.get(this).getEnrichedCallManager();
    }

    public static Intent newIntent(Context context) {
        Assert.isNotNull(context);
        return new Intent(context, EnrichedCallSimulatorActivity.class);
    }

    public void onClick(View view) {
        if (view == this.refreshButton) {
            LogUtil.m9i("EnrichedCallSimulatorActivity.onClick", "refreshing sessions", new Object[0]);
            this.sessionsAdapter.setSessionStrings(((EnrichedCallManagerStub) getEnrichedCallManager()).getAllSessionsForDisplay());
            this.sessionsAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.enterBlock("EnrichedCallSimulatorActivity.onCreate");
        super.onCreate(bundle);
        setContentView((int) R.layout.enriched_call_simulator_activity);
        ((Toolbar) findViewById(R.id.toolbar)).setTitle((int) R.string.enriched_call_simulator_activity);
        this.refreshButton = (Button) findViewById(R.id.refresh);
        this.refreshButton.setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sessions_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.sessionsAdapter = new SessionsAdapter();
        this.sessionsAdapter.setSessionStrings(((EnrichedCallManagerStub) getEnrichedCallManager()).getAllSessionsForDisplay());
        recyclerView.setAdapter(this.sessionsAdapter);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        LogUtil.enterBlock("EnrichedCallSimulatorActivity.onPause");
        super.onPause();
        ((EnrichedCallManagerStub) getEnrichedCallManager()).unregisterStateChangedListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        LogUtil.enterBlock("EnrichedCallSimulatorActivity.onResume");
        super.onResume();
        ((EnrichedCallManagerStub) getEnrichedCallManager()).registerStateChangedListener(this);
    }
}
