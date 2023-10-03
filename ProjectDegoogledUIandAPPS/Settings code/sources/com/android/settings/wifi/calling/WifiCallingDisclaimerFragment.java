package com.android.settings.wifi.calling;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.core.InstrumentedFragment;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class WifiCallingDisclaimerFragment extends InstrumentedFragment implements View.OnClickListener {
    private Button mAgreeButton;
    private Button mDisagreeButton;
    private List<DisclaimerItem> mDisclaimerItemList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mScrollToBottom;

    public int getMetricsCategory() {
        return 105;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mDisclaimerItemList = DisclaimerItemFactory.create(getActivity(), arguments != null ? arguments.getInt("EXTRA_SUB_ID") : Integer.MAX_VALUE);
        if (this.mDisclaimerItemList.isEmpty()) {
            finish(-1);
        } else if (bundle != null) {
            this.mScrollToBottom = bundle.getBoolean("state_is_scroll_to_bottom", this.mScrollToBottom);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.wfc_disclaimer_fragment, viewGroup, false);
        this.mAgreeButton = (Button) inflate.findViewById(C1715R.C1718id.agree_button);
        this.mAgreeButton.setOnClickListener(this);
        this.mDisagreeButton = (Button) inflate.findViewById(C1715R.C1718id.disagree_button);
        this.mDisagreeButton.setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1715R.C1718id.disclaimer_item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new DisclaimerItemListAdapter(this.mDisclaimerItemList));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (!recyclerView.canScrollVertically(1)) {
                    boolean unused = WifiCallingDisclaimerFragment.this.mScrollToBottom = true;
                    WifiCallingDisclaimerFragment.this.updateButtonState();
                    recyclerView.removeOnScrollListener(this);
                }
            }
        });
        return inflate;
    }

    public void onResume() {
        super.onResume();
        updateButtonState();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("state_is_scroll_to_bottom", this.mScrollToBottom);
    }

    /* access modifiers changed from: private */
    public void updateButtonState() {
        this.mAgreeButton.setEnabled(this.mScrollToBottom);
    }

    public void onClick(View view) {
        if (view == this.mAgreeButton) {
            for (DisclaimerItem onAgreed : this.mDisclaimerItemList) {
                onAgreed.onAgreed();
            }
            finish(-1);
        } else if (view == this.mDisagreeButton) {
            finish(0);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void finish(int i) {
        FragmentActivity activity = getActivity();
        activity.setResult(i, (Intent) null);
        activity.finish();
    }
}
