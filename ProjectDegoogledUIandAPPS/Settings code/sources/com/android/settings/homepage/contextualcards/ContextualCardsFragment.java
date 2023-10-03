package com.android.settings.homepage.contextualcards;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.homepage.contextualcards.FocusRecyclerView;
import com.android.settings.homepage.contextualcards.slices.SwipeDismissalDelegate;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.wifi.slice.ContextualWifiScanWorker;
import com.havoc.config.center.C1715R;

public class ContextualCardsFragment extends InstrumentedFragment implements FocusRecyclerView.FocusListener {
    private FocusRecyclerView mCardsContainer;
    private ContextualCardManager mContextualCardManager;
    private ContextualCardsAdapter mContextualCardsAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private GridLayoutManager mLayoutManager;

    public int getMetricsCategory() {
        return 1502;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (bundle == null) {
            FeatureFactory.getFactory(context).getSlicesFeatureProvider().newUiSession();
        }
        this.mContextualCardManager = new ContextualCardManager(context, getSettingsLifecycle(), bundle);
    }

    public void onStart() {
        super.onStart();
        ContextualWifiScanWorker.newVisibleUiSession();
        this.mContextualCardManager.loadContextualCards(LoaderManager.getInstance(this));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = getContext();
        View inflate = layoutInflater.inflate(C1715R.layout.settings_homepage, viewGroup, false);
        this.mCardsContainer = (FocusRecyclerView) inflate.findViewById(C1715R.C1718id.card_container);
        this.mLayoutManager = new GridLayoutManager((Context) getActivity(), 2, 1, false);
        this.mCardsContainer.setLayoutManager(this.mLayoutManager);
        this.mContextualCardsAdapter = new ContextualCardsAdapter(context, this, this.mContextualCardManager);
        this.mCardsContainer.setAdapter(this.mContextualCardsAdapter);
        this.mContextualCardManager.setListener(this.mContextualCardsAdapter);
        this.mCardsContainer.setListener(this);
        this.mItemTouchHelper = new ItemTouchHelper(new SwipeDismissalDelegate(this.mContextualCardsAdapter));
        this.mItemTouchHelper.attachToRecyclerView(this.mCardsContainer);
        return inflate;
    }

    public void onWindowFocusChanged(boolean z) {
        this.mContextualCardManager.onWindowFocusChanged(z);
    }
}
