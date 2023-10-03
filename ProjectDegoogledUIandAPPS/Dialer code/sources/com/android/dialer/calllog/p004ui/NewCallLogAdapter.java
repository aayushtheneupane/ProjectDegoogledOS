package com.android.dialer.calllog.p004ui;

import android.app.Activity;
import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.calllog.p004ui.PromotionCardViewHolder;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.promotion.Promotion;
import com.android.dialer.time.Clock;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

/* renamed from: com.android.dialer.calllog.ui.NewCallLogAdapter */
final class NewCallLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Activity activity;
    private final Clock clock;
    private ImmutableList<CoalescedRow> coalescedRows;
    private Integer olderHeaderPosition;
    private final PopCounts popCounts = new PopCounts();
    private final Promotion promotion;
    private Integer promotionCardPosition;
    private final RealtimeRowProcessor realtimeRowProcessor;
    private Integer todayHeaderPosition;
    private Integer yesterdayHeaderPosition;

    /* renamed from: com.android.dialer.calllog.ui.NewCallLogAdapter$OnScrollListenerForRecordingPromotionCardFirstViewTime */
    private static final class OnScrollListenerForRecordingPromotionCardFirstViewTime extends RecyclerView.OnScrollListener {
        private final Promotion promotion;

        OnScrollListenerForRecordingPromotionCardFirstViewTime(Promotion promotion2) {
            this.promotion = promotion2;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 2) {
                this.promotion.onViewed();
                recyclerView.removeOnScrollListener(this);
            }
        }
    }

    /* renamed from: com.android.dialer.calllog.ui.NewCallLogAdapter$PopCounts */
    static class PopCounts {
        int didNotPop;
        int popped;

        PopCounts() {
        }
    }

    NewCallLogAdapter(Activity activity2, ImmutableList<CoalescedRow> immutableList, Clock clock2, Promotion promotion2) {
        this.activity = activity2;
        this.coalescedRows = immutableList;
        this.clock = clock2;
        this.realtimeRowProcessor = ((DaggerAospDialerRootComponent) ((HasRootComponent) activity2.getApplicationContext()).component()).callLogUiComponent().realtimeRowProcessor();
        this.promotion = promotion2;
        setCardAndHeaderPositions();
    }

    private void setCardAndHeaderPositions() {
        int i;
        Integer num = null;
        this.promotionCardPosition = null;
        Promotion promotion2 = this.promotion;
        int i2 = 0;
        if (promotion2 == null || !promotion2.isEligibleToBeShown()) {
            i = 0;
        } else {
            this.promotionCardPosition = 0;
            i = 1;
        }
        if (this.coalescedRows.isEmpty()) {
            this.todayHeaderPosition = null;
            this.yesterdayHeaderPosition = null;
            this.olderHeaderPosition = null;
            return;
        }
        long currentTimeMillis = this.clock.currentTimeMillis();
        UnmodifiableIterator<CoalescedRow> it = this.coalescedRows.iterator();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            long dayDifference = (long) CallLogDates.getDayDifference(currentTimeMillis, it.next().getTimestamp());
            if (dayDifference != 0) {
                if (dayDifference != 1) {
                    i2 = (this.coalescedRows.size() - i3) - i4;
                    break;
                }
                i4++;
            } else {
                i3++;
            }
        }
        if (i3 > 0) {
            i3++;
        }
        if (i4 > 0) {
            i4++;
        }
        if (i2 > 0) {
            i2++;
        }
        this.todayHeaderPosition = i3 > 0 ? Integer.valueOf(i) : null;
        this.yesterdayHeaderPosition = i4 > 0 ? Integer.valueOf(i3 + i) : null;
        if (i2 > 0) {
            num = Integer.valueOf(i3 + i4 + i);
        }
        this.olderHeaderPosition = num;
    }

    /* access modifiers changed from: package-private */
    public void clearCache() {
        this.realtimeRowProcessor.clearCache();
    }

    public int getItemCount() {
        int i = 0;
        int i2 = this.promotionCardPosition != null ? 1 : 0;
        if (this.todayHeaderPosition != null) {
            i = 1;
        }
        if (this.yesterdayHeaderPosition != null) {
            i++;
        }
        if (this.olderHeaderPosition != null) {
            i++;
        }
        return this.coalescedRows.size() + i + i2;
    }

    public int getItemViewType(int i) {
        Integer num = this.promotionCardPosition;
        if (num != null && i == num.intValue()) {
            return 1;
        }
        Integer num2 = this.todayHeaderPosition;
        if (num2 != null && i == num2.intValue()) {
            return 2;
        }
        Integer num3 = this.yesterdayHeaderPosition;
        if (num3 != null && i == num3.intValue()) {
            return 3;
        }
        Integer num4 = this.olderHeaderPosition;
        return (num4 == null || i != num4.intValue()) ? 5 : 4;
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$NewCallLogAdapter() {
        notifyItemRemoved(this.promotionCardPosition.intValue());
        setCardAndHeaderPositions();
    }

    /* access modifiers changed from: package-private */
    public void logMetrics(Context context) {
        LoggingBindings loggingBindings = Logger.get(context);
        PopCounts popCounts2 = this.popCounts;
        ((LoggingBindingsStub) loggingBindings).logAnnotatedCallLogMetrics(popCounts2.popped, popCounts2.didNotPop);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Promotion promotion2 = this.promotion;
        if (promotion2 != null && promotion2.isEligibleToBeShown()) {
            recyclerView.addOnScrollListener(new OnScrollListenerForRecordingPromotionCardFirstViewTime(this.promotion));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        Integer num = this.promotionCardPosition;
        if (num == null || i != num.intValue()) {
            Integer num2 = this.todayHeaderPosition;
            if (num2 == null || i != num2.intValue()) {
                Integer num3 = this.yesterdayHeaderPosition;
                if (num3 == null || i != num3.intValue()) {
                    Integer num4 = this.olderHeaderPosition;
                    i2 = (num4 == null || i != num4.intValue()) ? 5 : 4;
                } else {
                    i2 = 3;
                }
            } else {
                i2 = 2;
            }
        } else {
            i2 = 1;
        }
        if (i2 == 1) {
            ((PromotionCardViewHolder) viewHolder).setDismissListener(new PromotionCardViewHolder.DismissListener() {
                public final void onDismiss() {
                    NewCallLogAdapter.this.lambda$onBindViewHolder$0$NewCallLogAdapter();
                }
            });
        } else if (i2 == 2) {
            ((HeaderViewHolder) viewHolder).setHeader(R.string.new_call_log_header_today);
        } else if (i2 == 3) {
            ((HeaderViewHolder) viewHolder).setHeader(R.string.new_call_log_header_yesterday);
        } else if (i2 == 4) {
            ((HeaderViewHolder) viewHolder).setHeader(R.string.new_call_log_header_older);
        } else if (i2 == 5) {
            NewCallLogViewHolder newCallLogViewHolder = (NewCallLogViewHolder) viewHolder;
            int i3 = 0;
            Integer num5 = this.promotionCardPosition;
            if (num5 != null && i > num5.intValue()) {
                i3 = 1;
            }
            Integer num6 = this.todayHeaderPosition;
            if (num6 != null && i > num6.intValue()) {
                i3++;
            }
            Integer num7 = this.yesterdayHeaderPosition;
            if (num7 != null && i > num7.intValue()) {
                i3++;
            }
            Integer num8 = this.olderHeaderPosition;
            if (num8 != null && i > num8.intValue()) {
                i3++;
            }
            newCallLogViewHolder.bind(this.coalescedRows.get(i - i3));
        } else {
            throw new IllegalStateException("Unexpected view type " + i2 + " at position: " + i);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new PromotionCardViewHolder(LayoutInflater.from(this.activity).inflate(R.layout.new_call_log_promotion_card, viewGroup, false), this.promotion);
        }
        if (i == 2 || i == 3 || i == 4) {
            return new HeaderViewHolder(LayoutInflater.from(this.activity).inflate(R.layout.new_call_log_header, viewGroup, false));
        }
        if (i == 5) {
            Activity activity2 = this.activity;
            return new NewCallLogViewHolder(activity2, LayoutInflater.from(activity2).inflate(R.layout.new_call_log_entry, viewGroup, false), this.clock, this.realtimeRowProcessor, this.popCounts);
        }
        throw new UnsupportedOperationException(GeneratedOutlineSupport.outline5("Unsupported view type: ", i));
    }

    /* access modifiers changed from: package-private */
    public void updateRows(ImmutableList<CoalescedRow> immutableList) {
        this.coalescedRows = immutableList;
        this.realtimeRowProcessor.clearCache();
        PopCounts popCounts2 = this.popCounts;
        popCounts2.popped = 0;
        popCounts2.didNotPop = 0;
        setCardAndHeaderPositions();
        notifyDataSetChanged();
    }
}
