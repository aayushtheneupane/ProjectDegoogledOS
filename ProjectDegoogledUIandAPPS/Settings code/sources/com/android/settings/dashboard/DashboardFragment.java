package com.android.settings.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerListHelper;
import com.android.settings.core.SettingsBaseActivity;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public abstract class DashboardFragment extends SettingsPreferenceFragment implements SettingsBaseActivity.CategoryListener, Indexable, SummaryLoader.SummaryConsumer, PreferenceGroup.OnExpandButtonClickListener, BasePreferenceController.UiBlockListener {
    UiBlockerController mBlockerController;
    private DashboardFeatureProvider mDashboardFeatureProvider;
    private final Set<String> mDashboardTilePrefKeys = new ArraySet();
    private boolean mListeningToCategoryChange;
    private DashboardTilePlaceholderPreferenceController mPlaceholderPreferenceController;
    private final Map<Class, List<AbstractPreferenceController>> mPreferenceControllers = new ArrayMap();
    private SummaryLoader mSummaryLoader;
    private List<String> mSuppressInjectedTileKeys;

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String getLogTag();

    /* access modifiers changed from: protected */
    public boolean shouldForceRoundedIcon() {
        return false;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mSuppressInjectedTileKeys = Arrays.asList(context.getResources().getStringArray(C1715R.array.config_suppress_injected_tile_keys));
        this.mDashboardFeatureProvider = FeatureFactory.getFactory(context).getDashboardFeatureProvider(context);
        ArrayList<AbstractPreferenceController> arrayList = new ArrayList<>();
        List<AbstractPreferenceController> createPreferenceControllers = createPreferenceControllers(context);
        List<BasePreferenceController> filterControllers = PreferenceControllerListHelper.filterControllers(PreferenceControllerListHelper.getPreferenceControllersFromXml(context, getPreferenceScreenResId()), createPreferenceControllers);
        if (createPreferenceControllers != null) {
            arrayList.addAll(createPreferenceControllers);
        }
        arrayList.addAll(filterControllers);
        filterControllers.stream().filter($$Lambda$DashboardFragment$SiRpeKDC_3jmfXOTbVaWpa8f5Y.INSTANCE).forEach(new Consumer() {
            public final void accept(Object obj) {
                Lifecycle.this.addObserver((LifecycleObserver) ((BasePreferenceController) obj));
            }
        });
        this.mPlaceholderPreferenceController = new DashboardTilePlaceholderPreferenceController(context);
        arrayList.add(this.mPlaceholderPreferenceController);
        for (AbstractPreferenceController addPreferenceController : arrayList) {
            addPreferenceController(addPreferenceController);
        }
        checkUiBlocker(arrayList);
    }

    static /* synthetic */ boolean lambda$onAttach$0(BasePreferenceController basePreferenceController) {
        return basePreferenceController instanceof LifecycleObserver;
    }

    /* access modifiers changed from: package-private */
    public void checkUiBlocker(List<AbstractPreferenceController> list) {
        ArrayList arrayList = new ArrayList();
        list.stream().filter($$Lambda$DashboardFragment$cuPvmIFPXgkQOdpQSGIa6sm2tHk.INSTANCE).forEach(new Consumer(arrayList) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                DashboardFragment.this.lambda$checkUiBlocker$3$DashboardFragment(this.f$1, (AbstractPreferenceController) obj);
            }
        });
        if (!arrayList.isEmpty()) {
            this.mBlockerController = new UiBlockerController(arrayList);
            this.mBlockerController.start(new Runnable() {
                public final void run() {
                    DashboardFragment.this.lambda$checkUiBlocker$4$DashboardFragment();
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$checkUiBlocker$2(AbstractPreferenceController abstractPreferenceController) {
        return abstractPreferenceController instanceof BasePreferenceController.UiBlocker;
    }

    public /* synthetic */ void lambda$checkUiBlocker$3$DashboardFragment(List list, AbstractPreferenceController abstractPreferenceController) {
        ((BasePreferenceController) abstractPreferenceController).setUiBlockListener(this);
        list.add(abstractPreferenceController.getPreferenceKey());
    }

    public /* synthetic */ void lambda$checkUiBlocker$4$DashboardFragment() {
        updatePreferenceVisibility(this.mPreferenceControllers);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getPreferenceManager().setPreferenceComparisonCallback(new PreferenceManager.SimplePreferenceComparisonCallback());
        if (bundle != null) {
            updatePreferenceStates();
        }
    }

    public void onCategoriesChanged() {
        if (this.mDashboardFeatureProvider.getTilesForCategory(getCategoryKey()) != null) {
            refreshDashboardTiles(getLogTag());
        }
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        refreshAllPreferences(getLogTag());
    }

    public void onStart() {
        super.onStart();
        if (this.mDashboardFeatureProvider.getTilesForCategory(getCategoryKey()) != null) {
            SummaryLoader summaryLoader = this.mSummaryLoader;
            if (summaryLoader != null) {
                summaryLoader.setListening(true);
            }
            FragmentActivity activity = getActivity();
            if (activity instanceof SettingsBaseActivity) {
                this.mListeningToCategoryChange = true;
                ((SettingsBaseActivity) activity).addCategoryListener(this);
            }
        }
    }

    public void notifySummaryChanged(Tile tile) {
        String dashboardKeyForTile = this.mDashboardFeatureProvider.getDashboardKeyForTile(tile);
        Preference findPreference = getPreferenceScreen().findPreference(dashboardKeyForTile);
        if (findPreference == null) {
            Log.d(getLogTag(), String.format("Can't find pref by key %s, skipping update summary %s", new Object[]{dashboardKeyForTile, tile.getDescription()}));
            return;
        }
        findPreference.setSummary(tile.getSummary(findPreference.getContext()));
    }

    public void onResume() {
        super.onResume();
        updatePreferenceStates();
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Collection<List<AbstractPreferenceController>> values = this.mPreferenceControllers.values();
        this.mMetricsFeatureProvider.logDashboardStartIntent(getContext(), preference.getIntent(), getMetricsCategory());
        for (List<AbstractPreferenceController> it : values) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((AbstractPreferenceController) it2.next()).handlePreferenceTreeClick(preference)) {
                        return true;
                    }
                }
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    public void onStop() {
        super.onStop();
        SummaryLoader summaryLoader = this.mSummaryLoader;
        if (summaryLoader != null) {
            summaryLoader.setListening(false);
        }
        if (this.mListeningToCategoryChange) {
            FragmentActivity activity = getActivity();
            if (activity instanceof SettingsBaseActivity) {
                ((SettingsBaseActivity) activity).remCategoryListener(this);
            }
            this.mListeningToCategoryChange = false;
        }
    }

    public void onExpandButtonClick() {
        this.mMetricsFeatureProvider.action(0, 834, getMetricsCategory(), (String) null, 0);
    }

    /* access modifiers changed from: protected */
    public <T extends AbstractPreferenceController> T use(Class<T> cls) {
        List list = this.mPreferenceControllers.get(cls);
        if (list == null) {
            return null;
        }
        if (list.size() > 1) {
            Log.w("DashboardFragment", "Multiple controllers of Class " + cls.getSimpleName() + " found, returning first one.");
        }
        return (AbstractPreferenceController) list.get(0);
    }

    /* access modifiers changed from: protected */
    public void addPreferenceController(AbstractPreferenceController abstractPreferenceController) {
        if (this.mPreferenceControllers.get(abstractPreferenceController.getClass()) == null) {
            this.mPreferenceControllers.put(abstractPreferenceController.getClass(), new ArrayList());
        }
        this.mPreferenceControllers.get(abstractPreferenceController.getClass()).add(abstractPreferenceController);
    }

    public String getCategoryKey() {
        return DashboardFragmentRegistry.PARENT_TO_CATEGORY_KEY_MAP.get(getClass().getName());
    }

    /* access modifiers changed from: protected */
    public boolean displayTile(Tile tile) {
        if (this.mSuppressInjectedTileKeys == null || !tile.hasKey()) {
            return true;
        }
        return !this.mSuppressInjectedTileKeys.contains(tile.getKey(getContext()));
    }

    private void displayResourceTiles() {
        int preferenceScreenResId = getPreferenceScreenResId();
        if (preferenceScreenResId > 0) {
            addPreferencesFromResource(preferenceScreenResId);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            preferenceScreen.setOnExpandButtonClickListener(this);
            this.mPreferenceControllers.values().stream().flatMap($$Lambda$seyL25CSW2NInOydsTbSDrNW6pM.INSTANCE).forEach(new Consumer() {
                public final void accept(Object obj) {
                    ((AbstractPreferenceController) obj).displayPreference(PreferenceScreen.this);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void updatePreferenceStates() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (List<AbstractPreferenceController> it : this.mPreferenceControllers.values()) {
            for (AbstractPreferenceController abstractPreferenceController : it) {
                if (abstractPreferenceController.isAvailable()) {
                    String preferenceKey = abstractPreferenceController.getPreferenceKey();
                    if (TextUtils.isEmpty(preferenceKey)) {
                        Log.d("DashboardFragment", String.format("Preference key is %s in Controller %s", new Object[]{preferenceKey, abstractPreferenceController.getClass().getSimpleName()}));
                    } else {
                        Preference findPreference = preferenceScreen.findPreference(preferenceKey);
                        if (findPreference == null) {
                            Log.d("DashboardFragment", String.format("Cannot find preference with key %s in Controller %s", new Object[]{preferenceKey, abstractPreferenceController.getClass().getSimpleName()}));
                        } else {
                            abstractPreferenceController.updateState(findPreference);
                        }
                    }
                }
            }
        }
    }

    private void refreshAllPreferences(String str) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        displayResourceTiles();
        refreshDashboardTiles(str);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Log.d(str, "All preferences added, reporting fully drawn");
            activity.reportFullyDrawn();
        }
        updatePreferenceVisibility(this.mPreferenceControllers);
    }

    /* access modifiers changed from: package-private */
    public void updatePreferenceVisibility(Map<Class, List<AbstractPreferenceController>> map) {
        UiBlockerController uiBlockerController;
        if (getPreferenceScreen() != null && map != null && (uiBlockerController = this.mBlockerController) != null) {
            boolean isBlockerFinished = uiBlockerController.isBlockerFinished();
            for (List<AbstractPreferenceController> it : map.values()) {
                for (AbstractPreferenceController abstractPreferenceController : it) {
                    Preference findPreference = findPreference(abstractPreferenceController.getPreferenceKey());
                    if (findPreference != null) {
                        findPreference.setVisible(isBlockerFinished && abstractPreferenceController.isAvailable());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshDashboardTiles(String str) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        DashboardCategory tilesForCategory = this.mDashboardFeatureProvider.getTilesForCategory(getCategoryKey());
        if (tilesForCategory == null) {
            Log.d(str, "NO dashboard tiles for " + str);
            return;
        }
        List<Tile> tiles = tilesForCategory.getTiles();
        if (tiles == null) {
            Log.d(str, "tile list is empty, skipping category " + tilesForCategory.key);
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>(this.mDashboardTilePrefKeys);
        SummaryLoader summaryLoader = this.mSummaryLoader;
        if (summaryLoader != null) {
            summaryLoader.release();
        }
        getContext();
        this.mSummaryLoader = new SummaryLoader(getActivity(), getCategoryKey());
        this.mSummaryLoader.setSummaryConsumer(this);
        boolean shouldForceRoundedIcon = shouldForceRoundedIcon();
        for (Tile next : tiles) {
            String dashboardKeyForTile = this.mDashboardFeatureProvider.getDashboardKeyForTile(next);
            if (TextUtils.isEmpty(dashboardKeyForTile)) {
                Log.d(str, "tile does not contain a key, skipping " + next);
            } else if (displayTile(next)) {
                if (this.mDashboardTilePrefKeys.contains(dashboardKeyForTile)) {
                    Preference findPreference = preferenceScreen.findPreference(dashboardKeyForTile);
                    this.mDashboardFeatureProvider.bindPreferenceToTile(getActivity(), shouldForceRoundedIcon, getMetricsCategory(), findPreference, next, dashboardKeyForTile, this.mPlaceholderPreferenceController.getOrder());
                } else {
                    Preference preference = new Preference(getPrefContext());
                    this.mDashboardFeatureProvider.bindPreferenceToTile(getActivity(), shouldForceRoundedIcon, getMetricsCategory(), preference, next, dashboardKeyForTile, this.mPlaceholderPreferenceController.getOrder());
                    preferenceScreen.addPreference(preference);
                    this.mDashboardTilePrefKeys.add(dashboardKeyForTile);
                }
                arrayList.remove(dashboardKeyForTile);
            }
        }
        for (String str2 : arrayList) {
            this.mDashboardTilePrefKeys.remove(str2);
            Preference findPreference2 = preferenceScreen.findPreference(str2);
            if (findPreference2 != null) {
                preferenceScreen.removePreference(findPreference2);
            }
        }
        this.mSummaryLoader.setListening(true);
    }

    public void onBlockerWorkFinished(BasePreferenceController basePreferenceController) {
        this.mBlockerController.countDown(basePreferenceController.getPreferenceKey());
    }
}
