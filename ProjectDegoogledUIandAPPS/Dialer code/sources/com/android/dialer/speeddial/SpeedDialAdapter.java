package com.android.dialer.speeddial;

import android.content.Context;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.widget.FrameLayout;
import com.android.dialer.R;
import com.android.dialer.speeddial.FavoritesViewHolder;
import com.android.dialer.speeddial.HeaderViewHolder;
import com.android.dialer.speeddial.SpeedDialFragment;
import com.android.dialer.speeddial.SuggestionViewHolder;
import com.android.dialer.speeddial.draghelper.SpeedDialItemTouchHelperCallback;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SpeedDialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SpeedDialItemTouchHelperCallback.ItemTouchHelperAdapter {
    private final Context context;
    private FavoritesViewHolder draggingFavoritesViewHolder;
    private final FavoritesViewHolder.FavoriteContactsListener favoritesListener;
    private final HeaderViewHolder.SpeedDialHeaderListener headerListener;
    private final SpeedDialFragment.HostInterface hostInterface;
    private ItemTouchHelper itemTouchHelper;
    /* access modifiers changed from: private */
    public final Map<Integer, Integer> positionToRowTypeMap = new ArrayMap();
    private RemoveViewHolder removeViewHolder;
    private List<SpeedDialUiItem> speedDialUiItems;
    private final SuggestionViewHolder.SuggestedContactsListener suggestedListener;

    public SpeedDialAdapter(Context context2, FavoritesViewHolder.FavoriteContactsListener favoriteContactsListener, SuggestionViewHolder.SuggestedContactsListener suggestedContactsListener, HeaderViewHolder.SpeedDialHeaderListener speedDialHeaderListener, SpeedDialFragment.HostInterface hostInterface2) {
        this.context = context2;
        this.favoritesListener = favoriteContactsListener;
        this.suggestedListener = suggestedContactsListener;
        this.headerListener = speedDialHeaderListener;
        this.hostInterface = hostInterface2;
    }

    static /* synthetic */ int lambda$setSpeedDialUiItems$0(SpeedDialUiItem speedDialUiItem, SpeedDialUiItem speedDialUiItem2) {
        if (!speedDialUiItem.isStarred() || !speedDialUiItem2.isStarred()) {
            return Boolean.compare(speedDialUiItem2.isStarred(), speedDialUiItem.isStarred());
        }
        return Integer.compare(speedDialUiItem.pinnedPosition().mo10247or(-1).intValue(), speedDialUiItem2.pinnedPosition().mo10247or(-1).intValue());
    }

    private void updatePositionToRowTypeMap() {
        this.positionToRowTypeMap.clear();
        if (!this.speedDialUiItems.isEmpty()) {
            this.positionToRowTypeMap.put(0, 0);
            this.positionToRowTypeMap.put(1, 1);
            int i = 2;
            for (int i2 = 0; i2 < this.speedDialUiItems.size(); i2++) {
                if (this.speedDialUiItems.get(i2).isStarred()) {
                    this.positionToRowTypeMap.put(Integer.valueOf(i2 + 2), 3);
                    i++;
                } else {
                    this.positionToRowTypeMap.put(Integer.valueOf(i2 + 3), 4);
                }
            }
            List<SpeedDialUiItem> list = this.speedDialUiItems;
            if (!list.get(list.size() - 1).isStarred()) {
                this.positionToRowTypeMap.put(Integer.valueOf(i), 2);
            }
        }
    }

    public boolean canDropOver(RecyclerView.ViewHolder viewHolder) {
        return (viewHolder instanceof FavoritesViewHolder) || (viewHolder instanceof RemoveViewHolder);
    }

    public void dropOnRemoveView(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof FavoritesViewHolder) {
            int adapterPosition = viewHolder.getAdapterPosition();
            this.favoritesListener.onRequestRemove(this.speedDialUiItems.remove(adapterPosition - 2));
            ((FavoritesViewHolder) viewHolder).getAvatarContainer().animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setInterpolator(new AnticipateInterpolator()).start();
            updatePositionToRowTypeMap();
        }
    }

    public void enterRemoveView() {
        FavoritesViewHolder favoritesViewHolder = this.draggingFavoritesViewHolder;
        if (favoritesViewHolder != null) {
            favoritesViewHolder.getAvatarContainer().animate().scaleX(0.5f).scaleY(0.5f).alpha(0.5f).start();
        }
    }

    public int getItemCount() {
        return this.positionToRowTypeMap.size();
    }

    public int getItemViewType(int i) {
        return this.positionToRowTypeMap.get(Integer.valueOf(i)).intValue();
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i) {
                int itemViewType = SpeedDialAdapter.this.getItemViewType(i);
                if (!(itemViewType == 0 || itemViewType == 1 || itemViewType == 2)) {
                    if (itemViewType == 3) {
                        return 1;
                    }
                    if (itemViewType != 4) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid row type: ");
                        outline13.append(SpeedDialAdapter.this.positionToRowTypeMap.get(Integer.valueOf(i)));
                        throw new IllegalStateException(outline13.toString());
                    }
                }
                return 3;
            }
        };
    }

    public ImmutableList<SpeedDialUiItem> getSpeedDialUiItems() {
        List<SpeedDialUiItem> list = this.speedDialUiItems;
        if (list == null || list.isEmpty()) {
            return ImmutableList.m74of();
        }
        return ImmutableList.copyOf(this.speedDialUiItems);
    }

    public boolean hasFrequents() {
        return !this.speedDialUiItems.isEmpty() && getItemViewType(getItemCount() - 1) == 4;
    }

    public void leaveRemoveView() {
        FavoritesViewHolder favoritesViewHolder = this.draggingFavoritesViewHolder;
        if (favoritesViewHolder != null) {
            favoritesViewHolder.getAvatarContainer().animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).start();
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            return;
        }
        if (itemViewType == 1) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.setHeaderText(R.string.favorites_header);
            headerViewHolder.showAddButton(true);
        } else if (itemViewType == 2) {
            HeaderViewHolder headerViewHolder2 = (HeaderViewHolder) viewHolder;
            headerViewHolder2.setHeaderText(R.string.suggestions_header);
            headerViewHolder2.showAddButton(false);
        } else if (itemViewType == 3) {
            FavoritesViewHolder favoritesViewHolder = (FavoritesViewHolder) viewHolder;
            favoritesViewHolder.bind(this.context, this.speedDialUiItems.get(i - 2));
            FrameLayout avatarContainer = favoritesViewHolder.getAvatarContainer();
            avatarContainer.setScaleX(1.0f);
            avatarContainer.setScaleY(1.0f);
            avatarContainer.setAlpha(1.0f);
        } else if (itemViewType == 4) {
            ((SuggestionViewHolder) viewHolder).bind(this.context, this.speedDialUiItems.get(i - 3));
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Invalid view holder: ", viewHolder));
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        if (i == 0) {
            this.removeViewHolder = new RemoveViewHolder(from.inflate(R.layout.favorite_remove_view_layout, viewGroup, false));
            return this.removeViewHolder;
        } else if (i == 1 || i == 2) {
            return new HeaderViewHolder(from.inflate(R.layout.speed_dial_header_layout, viewGroup, false), this.headerListener);
        } else {
            if (i == 3) {
                return new FavoritesViewHolder(from.inflate(R.layout.favorite_item_layout, viewGroup, false), this.itemTouchHelper, this.favoritesListener);
            }
            if (i == 4) {
                return new SuggestionViewHolder(from.inflate(R.layout.suggestion_row_layout, viewGroup, false), this.suggestedListener);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid viewType: ", i));
        }
    }

    public void onItemMove(int i, int i2) {
        if (i2 != 0) {
            List<SpeedDialUiItem> list = this.speedDialUiItems;
            list.add(i2 - 2, list.remove(i - 2));
            notifyItemMoved(i, i2);
        }
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 0) {
            FavoritesViewHolder favoritesViewHolder = this.draggingFavoritesViewHolder;
            if (favoritesViewHolder != null) {
                favoritesViewHolder.onSelectedChanged(false);
                this.draggingFavoritesViewHolder = null;
                this.hostInterface.dragFavorite(false);
                this.removeViewHolder.hide();
            }
        } else if (i == 2 && viewHolder != null) {
            this.draggingFavoritesViewHolder = (FavoritesViewHolder) viewHolder;
            this.draggingFavoritesViewHolder.onSelectedChanged(true);
            this.hostInterface.dragFavorite(true);
            this.removeViewHolder.show();
        }
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper2) {
        this.itemTouchHelper = itemTouchHelper2;
    }

    public void setSpeedDialUiItems(List<SpeedDialUiItem> list) {
        this.speedDialUiItems = new ArrayList();
        this.speedDialUiItems.addAll(list);
        this.speedDialUiItems.sort($$Lambda$SpeedDialAdapter$2LxBbDRRSZx84cxLaPCeWMy0ov0.INSTANCE);
        updatePositionToRowTypeMap();
    }
}
