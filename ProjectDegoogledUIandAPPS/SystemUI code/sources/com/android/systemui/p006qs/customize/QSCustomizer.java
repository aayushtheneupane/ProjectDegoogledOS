package com.android.systemui.p006qs.customize;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.Utils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1780R$menu;
import com.android.systemui.C1784R$string;
import com.android.systemui.C1785R$style;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.p006qs.QSDetailClipper;
import com.android.systemui.p006qs.QSTileHost;
import com.android.systemui.plugins.p005qs.C0862QS;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.NotificationsQuickSettingsContainer;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.ArrayList;

/* renamed from: com.android.systemui.qs.customize.QSCustomizer */
public class QSCustomizer extends LinearLayout implements Toolbar.OnMenuItemClickListener {
    /* access modifiers changed from: private */
    public boolean isShown;
    private final QSDetailClipper mClipper;
    private final Animator.AnimatorListener mCollapseAnimationListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            if (!QSCustomizer.this.isShown) {
                QSCustomizer.this.setVisibility(8);
            }
            QSCustomizer.this.mNotifQsContainer.setCustomizerAnimating(false);
            QSCustomizer.this.mRecyclerView.setAdapter(QSCustomizer.this.mTileAdapter);
        }

        public void onAnimationCancel(Animator animator) {
            if (!QSCustomizer.this.isShown) {
                QSCustomizer.this.setVisibility(8);
            }
            QSCustomizer.this.mNotifQsContainer.setCustomizerAnimating(false);
        }
    };
    private Menu mColumnsLandscapeSubMenu;
    private Menu mColumnsSubMenu;
    private boolean mCustomizing;
    private int mDefaultColumns;
    private final Animator.AnimatorListener mExpandAnimationListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            if (QSCustomizer.this.isShown) {
                QSCustomizer.this.setCustomizing(true);
            }
            boolean unused = QSCustomizer.this.mOpening = false;
            QSCustomizer.this.mNotifQsContainer.setCustomizerAnimating(false);
        }

        public void onAnimationCancel(Animator animator) {
            boolean unused = QSCustomizer.this.mOpening = false;
            QSCustomizer.this.mNotifQsContainer.setCustomizerAnimating(false);
        }
    };
    private boolean mHeaderImageEnabled;
    private QSTileHost mHost;
    private boolean mIsShowingNavBackdrop;
    private final KeyguardMonitor.Callback mKeyguardCallback = new KeyguardMonitor.Callback() {
        public void onKeyguardShowingChanged() {
            if (QSCustomizer.this.isAttachedToWindow() && QSCustomizer.this.mKeyguardMonitor.isShowing() && !QSCustomizer.this.mOpening) {
                QSCustomizer.this.hide();
            }
        }
    };
    /* access modifiers changed from: private */
    public KeyguardMonitor mKeyguardMonitor;
    private GridLayoutManager mLayout;
    private final LightBarController mLightBarController;
    /* access modifiers changed from: private */
    public NotificationsQuickSettingsContainer mNotifQsContainer;
    /* access modifiers changed from: private */
    public boolean mOpening;
    private C0862QS mQs;
    private Menu mQsColumnsSubMenu;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    private Menu mRowsLandscapeSubMenu;
    private Menu mRowsSubMenu;
    private final ScreenLifecycle mScreenLifecycle;
    /* access modifiers changed from: private */
    public TileAdapter mTileAdapter;
    private final TileQueryHelper mTileQueryHelper;
    private Toolbar mToolbar;
    private final View mTransparentView;

    /* renamed from: mX */
    private int f60mX;

    /* renamed from: mY */
    private int f61mY;

    public QSCustomizer(Context context, AttributeSet attributeSet, LightBarController lightBarController, KeyguardMonitor keyguardMonitor, ScreenLifecycle screenLifecycle) {
        super(new ContextThemeWrapper(context, C1785R$style.edit_theme), attributeSet);
        LayoutInflater.from(getContext()).inflate(C1779R$layout.qs_customize_panel_content, this);
        this.mClipper = new QSDetailClipper(findViewById(C1777R$id.customize_container));
        this.mToolbar = (Toolbar) findViewById(16908682);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(16843531, typedValue, true);
        this.mToolbar.setNavigationIcon(getResources().getDrawable(typedValue.resourceId, this.mContext.getTheme()));
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QSCustomizer.this.hide();
            }
        });
        this.mToolbar.setOnMenuItemClickListener(this);
        new MenuInflater(this.mContext).inflate(C1780R$menu.qs_customize_menu, this.mToolbar.getMenu());
        MenuItem findItem = this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns);
        if (findItem != null) {
            this.mColumnsSubMenu = findItem.getSubMenu();
        }
        MenuItem findItem2 = this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape);
        if (findItem2 != null) {
            this.mColumnsLandscapeSubMenu = findItem2.getSubMenu();
        }
        MenuItem findItem3 = this.mToolbar.getMenu().findItem(C1777R$id.menu_item_qs_columns);
        if (findItem3 != null) {
            this.mQsColumnsSubMenu = findItem3.getSubMenu();
        }
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows);
        if (findItem != null) {
            this.mRowsSubMenu = findItem.getSubMenu();
        }
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_landscape);
        if (findItem2 != null) {
            this.mRowsLandscapeSubMenu = findItem2.getSubMenu();
        }
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_titles).setChecked(Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_tile_title_visibility", 1, -2) == 1);
        int colorAccentDefaultColor = Utils.getColorAccentDefaultColor(context);
        this.mToolbar.setTitleTextColor(colorAccentDefaultColor);
        this.mToolbar.getNavigationIcon().setTint(colorAccentDefaultColor);
        this.mToolbar.getOverflowIcon().setTint(colorAccentDefaultColor);
        this.mToolbar.setTitle(C1784R$string.qs_edit);
        this.mDefaultColumns = Math.max(1, this.mContext.getResources().getInteger(C1778R$integer.quick_settings_num_columns));
        this.mRecyclerView = (RecyclerView) findViewById(16908298);
        this.mTransparentView = findViewById(C1777R$id.customizer_transparent_view);
        this.mTileAdapter = new TileAdapter(getContext());
        this.mTileQueryHelper = new TileQueryHelper(context, this.mTileAdapter);
        this.mRecyclerView.setAdapter(this.mTileAdapter);
        this.mTileAdapter.getItemTouchHelper().attachToRecyclerView(this.mRecyclerView);
        this.mLayout = new GridLayoutManager(getContext(), this.mDefaultColumns);
        this.mLayout.setSpanSizeLookup(this.mTileAdapter.getSizeLookup());
        this.mRecyclerView.setLayoutManager(this.mLayout);
        this.mRecyclerView.addItemDecoration(this.mTileAdapter.getItemDecoration());
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setMoveDuration(150);
        this.mRecyclerView.setItemAnimator(defaultItemAnimator);
        this.mLightBarController = lightBarController;
        this.mKeyguardMonitor = keyguardMonitor;
        this.mScreenLifecycle = screenLifecycle;
        updateNavBackDrop(getResources().getConfiguration());
        updateSettings();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateNavBackDrop(configuration);
        updateResources();
    }

    private void updateResources() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTransparentView.getLayoutParams();
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(17105395);
        if (this.mHeaderImageEnabled) {
            layoutParams.height += this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_header_image_offset);
        }
        this.mTransparentView.setLayoutParams(layoutParams);
    }

    private void updateNavBackDrop(Configuration configuration) {
        View findViewById = findViewById(C1777R$id.nav_bar_background);
        int i = 0;
        this.mIsShowingNavBackdrop = configuration.smallestScreenWidthDp >= 600 || configuration.orientation != 2;
        if (findViewById != null) {
            if (!this.mIsShowingNavBackdrop) {
                i = 8;
            }
            findViewById.setVisibility(i);
        }
        updateNavColors();
        updateSettings();
    }

    private void updateNavColors() {
        this.mLightBarController.setQsCustomizing(this.mIsShowingNavBackdrop && this.isShown);
    }

    public void setHost(QSTileHost qSTileHost) {
        this.mHost = qSTileHost;
        this.mTileAdapter.setHost(qSTileHost);
    }

    public void setContainer(NotificationsQuickSettingsContainer notificationsQuickSettingsContainer) {
        this.mNotifQsContainer = notificationsQuickSettingsContainer;
    }

    public void setQs(C0862QS qs) {
        this.mQs = qs;
    }

    public void show(int i, int i2) {
        if (!this.isShown) {
            int[] locationOnScreen = findViewById(C1777R$id.customize_container).getLocationOnScreen();
            this.f60mX = i - locationOnScreen[0];
            this.f61mY = i2 - locationOnScreen[1];
            MetricsLogger.visible(getContext(), 358);
            this.isShown = true;
            this.mOpening = true;
            setTileSpecs();
            setVisibility(0);
            this.mClipper.animateCircularClip(this.f60mX, this.f61mY, true, this.mExpandAnimationListener);
            queryTiles();
            this.mNotifQsContainer.setCustomizerAnimating(true);
            this.mNotifQsContainer.setCustomizerShowing(true);
            this.mKeyguardMonitor.addCallback(this.mKeyguardCallback);
            updateNavColors();
        }
    }

    public void showImmediately() {
        if (!this.isShown) {
            setVisibility(0);
            this.mClipper.showBackground();
            this.isShown = true;
            setTileSpecs();
            setCustomizing(true);
            queryTiles();
            this.mNotifQsContainer.setCustomizerAnimating(false);
            this.mNotifQsContainer.setCustomizerShowing(true);
            this.mKeyguardMonitor.addCallback(this.mKeyguardCallback);
            updateNavColors();
        }
    }

    private void queryTiles() {
        this.mTileQueryHelper.queryTiles(this.mHost);
    }

    public void hide() {
        boolean z = this.mScreenLifecycle.getScreenState() != 0;
        if (this.isShown) {
            MetricsLogger.hidden(getContext(), 358);
            this.isShown = false;
            Menu menu = this.mColumnsSubMenu;
            if (menu != null) {
                menu.close();
            }
            Menu menu2 = this.mColumnsLandscapeSubMenu;
            if (menu2 != null) {
                menu2.close();
            }
            Menu menu3 = this.mQsColumnsSubMenu;
            if (menu3 != null) {
                menu3.close();
                Menu menu4 = this.mRowsSubMenu;
                if (menu4 != null) {
                    menu4.close();
                }
                Menu menu5 = this.mRowsLandscapeSubMenu;
                if (menu5 != null) {
                    menu5.close();
                }
                this.mToolbar.dismissPopupMenus();
                setCustomizing(false);
                save();
                if (z) {
                    this.mClipper.animateCircularClip(this.f60mX, this.f61mY, false, this.mCollapseAnimationListener);
                } else {
                    setVisibility(8);
                }
                this.mNotifQsContainer.setCustomizerAnimating(z);
                this.mNotifQsContainer.setCustomizerShowing(false);
                this.mKeyguardMonitor.removeCallback(this.mKeyguardCallback);
                updateNavColors();
            }
        }
    }

    public boolean isShown() {
        return this.isShown;
    }

    /* access modifiers changed from: private */
    public void setCustomizing(boolean z) {
        this.mCustomizing = z;
        this.mQs.notifyCustomizeChanged();
    }

    public boolean isCustomizing() {
        return this.mCustomizing || this.mOpening;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == C1777R$id.menu_item_reset) {
            MetricsLogger.action(getContext(), 359);
            reset();
        } else if (itemId == C1777R$id.menu_item_columns_three) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", 3, -2);
        } else if (itemId == C1777R$id.menu_item_columns_four) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", 4, -2);
        } else if (itemId == C1777R$id.menu_item_columns_five) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", 5, -2);
        } else if (itemId == C1777R$id.menu_item_columns_six) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", 6, -2);
        } else if (itemId == C1777R$id.menu_item_columns_seven) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", 7, -2);
        } else if (itemId == C1777R$id.menu_item_columns_eight) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", 8, -2);
        } else if (itemId == C1777R$id.menu_item_columns_landscape_four) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", 4, -2);
        } else if (itemId == C1777R$id.menu_item_columns_landscape_five) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", 5, -2);
        } else if (itemId == C1777R$id.menu_item_columns_landscape_six) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", 6, -2);
        } else if (itemId == C1777R$id.menu_item_columns_landscape_seven) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", 7, -2);
        } else if (itemId == C1777R$id.menu_item_columns_landscape_eight) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", 8, -2);
        } else if (itemId == C1777R$id.menu_item_titles) {
            menuItem.setChecked(!menuItem.isChecked());
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_tile_title_visibility", menuItem.isChecked() ? 1 : 0, -2);
        } else if (itemId == C1777R$id.menu_item_qs_columns_six) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_quickbar_columns", 6, -2);
        } else if (itemId == C1777R$id.menu_item_qs_columns_seven) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_quickbar_columns", 7, -2);
        } else if (itemId == C1777R$id.menu_item_qs_columns_eight) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_quickbar_columns", 8, -2);
        } else if (itemId == C1777R$id.menu_item_qs_columns_auto) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_quickbar_columns", -1, -2);
        } else if (itemId == C1777R$id.menu_item_rows_one) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 1, -2);
        } else if (itemId == C1777R$id.menu_item_rows_two) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 2, -2);
        } else if (itemId == C1777R$id.menu_item_rows_three) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 3, -2);
        } else if (itemId == C1777R$id.menu_item_rows_four) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 4, -2);
        } else if (itemId == C1777R$id.menu_item_rows_landscape_one) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_rows_landscape", 1, -2);
        } else if (itemId == C1777R$id.menu_item_rows_landscape_two) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_rows_landscape", 2, -2);
        }
        updateSettings();
        return false;
    }

    private void reset() {
        ArrayList arrayList = new ArrayList();
        for (String add : this.mContext.getString(C1784R$string.quick_settings_tiles_default).split(",")) {
            arrayList.add(add);
        }
        this.mTileAdapter.resetTileSpecs(this.mHost, arrayList);
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", this.mDefaultColumns, -2);
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", this.mDefaultColumns, -2);
        updateSettings();
    }

    private void setTileSpecs() {
        ArrayList arrayList = new ArrayList();
        for (QSTile tileSpec : this.mHost.getTiles()) {
            arrayList.add(tileSpec.getTileSpec());
        }
        this.mTileAdapter.setTileSpecs(arrayList);
        this.mRecyclerView.setAdapter(this.mTileAdapter);
    }

    private void save() {
        if (this.mTileQueryHelper.isFinished()) {
            this.mTileAdapter.saveSpecs(this.mHost);
        }
    }

    public void saveInstanceState(Bundle bundle) {
        if (this.isShown) {
            this.mKeyguardMonitor.removeCallback(this.mKeyguardCallback);
        }
        bundle.putBoolean("qs_customizing", this.mCustomizing);
    }

    public void restoreInstanceState(Bundle bundle) {
        if (bundle.getBoolean("qs_customizing")) {
            setVisibility(0);
            addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    QSCustomizer.this.removeOnLayoutChangeListener(this);
                    QSCustomizer.this.showImmediately();
                }
            });
        }
    }

    public void setEditLocation(int i, int i2) {
        int[] locationOnScreen = findViewById(C1777R$id.customize_container).getLocationOnScreen();
        this.f60mX = i - locationOnScreen[0];
        this.f61mY = i2 - locationOnScreen[1];
    }

    private void updateSettings() {
        boolean z = false;
        boolean z2 = this.mContext.getResources().getConfiguration().orientation == 1;
        int max = Math.max(1, this.mContext.getResources().getInteger(C1778R$integer.quick_settings_num_columns));
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", max, -2);
        int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", max, -2);
        int intForUser3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 3, -2);
        int intForUser4 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_rows_landscape", 2, -2);
        boolean z3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_tile_title_visibility", 1, -2) == 1;
        this.mTileAdapter.setColumnCount(z2 ? intForUser : intForUser2);
        TileAdapter tileAdapter = this.mTileAdapter;
        if (!z2) {
            intForUser3 = intForUser4;
        }
        tileAdapter.setRowsCount(intForUser3);
        this.mTileAdapter.setHideLabel(!z3);
        GridLayoutManager gridLayoutManager = this.mLayout;
        if (!z2) {
            intForUser = intForUser2;
        }
        gridLayoutManager.setSpanCount(intForUser);
        updateColumnsMenu(max);
        updateRowsMenu();
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_custom_header", 0, -2) == 1) {
            z = true;
        }
        this.mHeaderImageEnabled = z;
    }

    private void updateColumnsMenu(int i) {
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", i, -2);
        boolean z = true;
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_three).setChecked(intForUser == 3);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_four).setChecked(intForUser == 4);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_five).setChecked(intForUser == 5);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_six).setChecked(intForUser == 6);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_seven).setChecked(intForUser == 7);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_eight).setChecked(intForUser == 8);
        int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", i, -2);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape_three).setChecked(intForUser2 == 3);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape_four).setChecked(intForUser2 == 4);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape_five).setChecked(intForUser2 == 5);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape_six).setChecked(intForUser2 == 6);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape_seven).setChecked(intForUser2 == 7);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_columns_landscape_eight).setChecked(intForUser2 == 8);
        int intForUser3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_quickbar_columns", 6, -2);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_qs_columns_six).setChecked(intForUser3 == 6);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_qs_columns_seven).setChecked(intForUser3 == 7);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_qs_columns_eight).setChecked(intForUser3 == 8);
        MenuItem findItem = this.mToolbar.getMenu().findItem(C1777R$id.menu_item_qs_columns_auto);
        if (intForUser3 != -1) {
            z = false;
        }
        findItem.setChecked(z);
    }

    private void updateRowsMenu() {
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 3, -2);
        boolean z = false;
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_one).setChecked(intForUser == 1);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_two).setChecked(intForUser == 2);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_three).setChecked(intForUser == 3);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_four).setChecked(intForUser == 4);
        int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_rows_landscape", 2, -2);
        this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_landscape_one).setChecked(intForUser2 == 1);
        MenuItem findItem = this.mToolbar.getMenu().findItem(C1777R$id.menu_item_rows_landscape_two);
        if (intForUser2 == 2) {
            z = true;
        }
        findItem.setChecked(z);
    }
}
