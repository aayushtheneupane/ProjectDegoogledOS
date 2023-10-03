package com.google.android.setupdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupdesign.DividerItemDecoration;
import com.google.android.setupdesign.R$styleable;

public class HeaderRecyclerView extends RecyclerView {
    private View header;
    private int headerRes;

    private static class HeaderViewHolder extends RecyclerView.ViewHolder implements DividerItemDecoration.DividedViewHolder {
        public boolean isDividerAllowedAbove() {
            return false;
        }

        public boolean isDividerAllowedBelow() {
            return false;
        }

        HeaderViewHolder(View view) {
            super(view);
        }
    }

    public static class HeaderAdapter<CVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final RecyclerView.Adapter<CVH> adapter;
        /* access modifiers changed from: private */
        public View header;
        private final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                HeaderAdapter.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int i, int i2) {
                if (HeaderAdapter.this.header != null) {
                    i++;
                }
                HeaderAdapter.this.notifyItemRangeChanged(i, i2);
            }

            public void onItemRangeInserted(int i, int i2) {
                if (HeaderAdapter.this.header != null) {
                    i++;
                }
                HeaderAdapter.this.notifyItemRangeInserted(i, i2);
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                if (HeaderAdapter.this.header != null) {
                    i++;
                    i2++;
                }
                for (int i4 = 0; i4 < i3; i4++) {
                    HeaderAdapter.this.notifyItemMoved(i + i4, i2 + i4);
                }
            }

            public void onItemRangeRemoved(int i, int i2) {
                if (HeaderAdapter.this.header != null) {
                    i++;
                }
                HeaderAdapter.this.notifyItemRangeRemoved(i, i2);
            }
        };

        public HeaderAdapter(RecyclerView.Adapter<CVH> adapter2) {
            this.adapter = adapter2;
            this.adapter.registerAdapterDataObserver(this.observer);
            setHasStableIds(this.adapter.hasStableIds());
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i != Integer.MAX_VALUE) {
                return this.adapter.onCreateViewHolder(viewGroup, i);
            }
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            return new HeaderViewHolder(frameLayout);
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (this.header != null) {
                i--;
            }
            if (viewHolder instanceof HeaderViewHolder) {
                View view = this.header;
                if (view != null) {
                    if (view.getParent() != null) {
                        ((ViewGroup) this.header.getParent()).removeView(this.header);
                    }
                    ((FrameLayout) viewHolder.itemView).addView(this.header);
                    return;
                }
                throw new IllegalStateException("HeaderViewHolder cannot find mHeader");
            }
            this.adapter.onBindViewHolder(viewHolder, i);
        }

        public int getItemViewType(int i) {
            if (this.header != null) {
                i--;
            }
            if (i < 0) {
                return Integer.MAX_VALUE;
            }
            return this.adapter.getItemViewType(i);
        }

        public int getItemCount() {
            int itemCount = this.adapter.getItemCount();
            return this.header != null ? itemCount + 1 : itemCount;
        }

        public long getItemId(int i) {
            if (this.header != null) {
                i--;
            }
            if (i < 0) {
                return Long.MAX_VALUE;
            }
            return this.adapter.getItemId(i);
        }

        public void setHeader(View view) {
            this.header = view;
        }

        public RecyclerView.Adapter<CVH> getWrappedAdapter() {
            return this.adapter;
        }
    }

    public HeaderRecyclerView(Context context) {
        super(context);
        init((AttributeSet) null, 0);
    }

    public HeaderRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public HeaderRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SudHeaderRecyclerView, i, 0);
        this.headerRes = obtainStyledAttributes.getResourceId(R$styleable.SudHeaderRecyclerView_sudHeader, 0);
        obtainStyledAttributes.recycle();
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        int i = this.header != null ? 1 : 0;
        accessibilityEvent.setItemCount(accessibilityEvent.getItemCount() - i);
        accessibilityEvent.setFromIndex(Math.max(accessibilityEvent.getFromIndex() - i, 0));
        if (Build.VERSION.SDK_INT >= 14) {
            accessibilityEvent.setToIndex(Math.max(accessibilityEvent.getToIndex() - i, 0));
        }
    }

    public View getHeader() {
        return this.header;
    }

    public void setHeader(View view) {
        this.header = view;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager != null && this.header == null && this.headerRes != 0) {
            this.header = LayoutInflater.from(getContext()).inflate(this.headerRes, this, false);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (!(this.header == null || adapter == null)) {
            HeaderAdapter headerAdapter = new HeaderAdapter(adapter);
            headerAdapter.setHeader(this.header);
            adapter = headerAdapter;
        }
        super.setAdapter(adapter);
    }
}
