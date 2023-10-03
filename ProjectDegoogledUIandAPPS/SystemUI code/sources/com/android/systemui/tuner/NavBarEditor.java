package com.android.systemui.tuner;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.PreferenceFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1780R$menu;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.phone.NavigationBarInflaterView;
import com.android.systemui.tuner.TunerService;
import java.util.ArrayList;
import java.util.List;

public class NavBarEditor extends PreferenceFragment implements TunerService.Tunable {
    private NavBarAdapter mNavBarAdapter;

    public void onCreatePreferences(Bundle bundle, String str) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1779R$layout.nav_bar_tuner, viewGroup, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void notifyChanged() {
        Settings.Secure.putString(getContext().getContentResolver(), "sysui_nav_bar", this.mNavBarAdapter.getNavString());
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(16908298);
        Context context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.mNavBarAdapter = new NavBarAdapter(context);
        recyclerView.setAdapter(this.mNavBarAdapter);
        recyclerView.addItemDecoration(new Dividers(context));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this.mNavBarAdapter.mCallbacks);
        this.mNavBarAdapter.setTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        setHasOptionsMenu(true);
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this, "sysui_nav_bar");
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
    }

    public void onTuningChanged(String str, String str2) {
        if ("sysui_nav_bar".equals(str)) {
            Context context = getContext();
            if (str2 == null) {
                str2 = context.getString(C1784R$string.config_navBarLayout);
            }
            String[] split = str2.split(";");
            String[] strArr = {"start", "center", "end"};
            String[] strArr2 = {getString(C1784R$string.start), getString(C1784R$string.center), getString(C1784R$string.end)};
            this.mNavBarAdapter.clear();
            for (int i = 0; i < 3; i++) {
                this.mNavBarAdapter.addButton(strArr[i], strArr2[i]);
                for (String str3 : split[i].split(",")) {
                    this.mNavBarAdapter.addButton(str3, getLabel(str3, context));
                }
            }
            this.mNavBarAdapter.addButton("add", getString(C1784R$string.add_button));
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(C1780R$menu.nav_bar_tuner_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1777R$id.nav_bar_reset) {
            Settings.Secure.putString(getContext().getContentResolver(), "sysui_nav_bar", (String) null);
            return true;
        } else if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            getActivity().onBackPressed();
            return true;
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /* access modifiers changed from: private */
    public static CharSequence getLabel(String str, Context context) {
        if (str.startsWith("home")) {
            return context.getString(C1784R$string.accessibility_home);
        }
        if (str.startsWith("back")) {
            return context.getString(C1784R$string.accessibility_back);
        }
        if (str.startsWith("recent")) {
            return context.getString(C1784R$string.accessibility_recent);
        }
        if (str.startsWith("space")) {
            return context.getString(C1784R$string.space);
        }
        if (str.startsWith("left")) {
            return context.getString(C1784R$string.left);
        }
        return str.startsWith("right") ? context.getString(C1784R$string.right) : str;
    }

    private static class Holder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView title;

        public Holder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(16908310);
        }
    }

    private static class Dividers extends RecyclerView.ItemDecoration {
        private final Drawable mDivider;

        public Dividers(Context context) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16843284, typedValue, true);
            this.mDivider = context.getDrawable(typedValue.resourceId);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDraw(canvas, recyclerView, state);
            int paddingLeft = recyclerView.getPaddingLeft();
            int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
                this.mDivider.setBounds(paddingLeft, bottom, width, this.mDivider.getIntrinsicHeight() + bottom);
                this.mDivider.draw(canvas);
            }
        }
    }

    private class NavBarAdapter extends RecyclerView.Adapter<Holder> implements View.OnClickListener {
        private int mButtonLayout;
        /* access modifiers changed from: private */
        public List<String> mButtons = new ArrayList();
        /* access modifiers changed from: private */
        public final ItemTouchHelper.Callback mCallbacks = new ItemTouchHelper.Callback() {
            public boolean isItemViewSwipeEnabled() {
                return false;
            }

            public boolean isLongPressDragEnabled() {
                return true;
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            }

            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getItemViewType() != 1) {
                    return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
                }
                return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
            }

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                int adapterPosition = viewHolder.getAdapterPosition();
                int adapterPosition2 = viewHolder2.getAdapterPosition();
                if (adapterPosition2 == 0) {
                    return false;
                }
                move(adapterPosition, adapterPosition2, NavBarAdapter.this.mButtons);
                move(adapterPosition, adapterPosition2, NavBarAdapter.this.mLabels);
                NavBarAdapter.this.notifyItemMoved(adapterPosition, adapterPosition2);
                return true;
            }

            private <T> void move(int i, int i2, List<T> list) {
                list.add(i > i2 ? i2 : i2 + 1, list.get(i));
                if (i > i2) {
                    i++;
                }
                list.remove(i);
            }

            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                NavBarEditor.this.notifyChanged();
            }
        };
        private int mCategoryLayout;
        /* access modifiers changed from: private */
        public List<CharSequence> mLabels = new ArrayList();
        /* access modifiers changed from: private */
        public ItemTouchHelper mTouchHelper;

        /* access modifiers changed from: private */
        public float getAmountValue(int i) {
            if (i == 0) {
                return 0.25f;
            }
            if (i == 1) {
                return 0.5f;
            }
            if (i == 2) {
                return 0.75f;
            }
            if (i == 3) {
                return 1.0f;
            }
            if (i == 4) {
                return 1.25f;
            }
            if (i == 5) {
                return 1.5f;
            }
            return i == 6 ? 1.75f : 1.0f;
        }

        private int getProgressValue(float f) {
            if (f == 0.25f) {
                return 0;
            }
            if (f == 0.5f) {
                return 1;
            }
            if (f == 0.75f) {
                return 2;
            }
            if (f == 1.0f) {
                return 3;
            }
            if (f == 1.25f) {
                return 4;
            }
            if (f == 1.5f) {
                return 5;
            }
            return f == 1.75f ? 6 : 3;
        }

        public NavBarAdapter(Context context) {
            this.mButtonLayout = context.getTheme().obtainStyledAttributes((AttributeSet) null, R.styleable.Preference, 16842894, 0).getResourceId(3, 0);
            this.mCategoryLayout = context.getTheme().obtainStyledAttributes((AttributeSet) null, R.styleable.Preference, 16842892, 0).getResourceId(3, 0);
        }

        public void setTouchHelper(ItemTouchHelper itemTouchHelper) {
            this.mTouchHelper = itemTouchHelper;
        }

        public void clear() {
            this.mButtons.clear();
            this.mLabels.clear();
            notifyDataSetChanged();
        }

        public void addButton(String str, CharSequence charSequence) {
            this.mButtons.add(str);
            this.mLabels.add(charSequence);
            notifyItemInserted(this.mLabels.size() - 1);
        }

        /* access modifiers changed from: private */
        public String getNavString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < this.mButtons.size() - 1; i++) {
                String str = this.mButtons.get(i);
                if (str.equals("center") || str.equals("end")) {
                    if (sb.length() == 0 || sb.toString().endsWith(";")) {
                        sb.append("space");
                    }
                    sb.append(";");
                } else {
                    if (sb.length() != 0 && !sb.toString().endsWith(";")) {
                        sb.append(",");
                    }
                    sb.append(str);
                }
            }
            if (sb.toString().endsWith(";")) {
                sb.append("space");
            }
            return sb.toString();
        }

        public int getItemViewType(int i) {
            String str = this.mButtons.get(i);
            if (str.equals("start") || str.equals("center") || str.equals("end")) {
                return 2;
            }
            return str.equals("add") ? 0 : 1;
        }

        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            View inflate = from.inflate(getLayoutId(i), viewGroup, false);
            if (i == 1) {
                from.inflate(C1779R$layout.nav_control_widget, (ViewGroup) inflate.findViewById(16908312));
            }
            return new Holder(inflate);
        }

        private int getLayoutId(int i) {
            if (i == 2) {
                return this.mCategoryLayout;
            }
            return this.mButtonLayout;
        }

        public void onBindViewHolder(Holder holder, int i) {
            holder.title.setText(this.mLabels.get(i));
            if (holder.getItemViewType() == 1) {
                bindButton(holder, i);
            } else if (holder.getItemViewType() == 0) {
                bindAdd(holder);
            }
        }

        private void bindAdd(Holder holder) {
            TypedValue typedValue = new TypedValue();
            Context context = holder.itemView.getContext();
            context.getTheme().resolveAttribute(16843829, typedValue, true);
            ImageView imageView = (ImageView) holder.itemView.findViewById(16908294);
            imageView.setImageResource(C1776R$drawable.ic_add);
            imageView.setImageTintList(ColorStateList.valueOf(context.getColor(typedValue.resourceId)));
            holder.itemView.findViewById(16908304).setVisibility(8);
            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    NavBarAdapter.this.showAddDialog(view.getContext());
                }
            });
        }

        private void bindButton(final Holder holder, int i) {
            Context context = holder.itemView.getContext();
            holder.itemView.findViewById(16908350).setVisibility(8);
            holder.itemView.findViewById(16908304).setVisibility(8);
            if (this.mLabels.get(i).equals(context.getString(C1784R$string.accessibility_home))) {
                holder.itemView.findViewById(C1777R$id.close).setVisibility(4);
            } else {
                holder.itemView.findViewById(C1777R$id.close).setVisibility(0);
                bindClick(holder.itemView.findViewById(C1777R$id.close), holder);
            }
            bindClick(holder.itemView.findViewById(C1777R$id.width), holder);
            holder.itemView.findViewById(C1777R$id.drag).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    NavBarAdapter.this.mTouchHelper.startDrag(holder);
                    return true;
                }
            });
        }

        /* access modifiers changed from: private */
        public void showAddDialog(Context context) {
            final String[] strArr = {"back", "home", "recent", "space", "left", "right"};
            final CharSequence[] charSequenceArr = new CharSequence[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                charSequenceArr[i] = NavBarEditor.getLabel(strArr[i], context);
            }
            new AlertDialog.Builder(context).setTitle(C1784R$string.select_button).setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    int size = NavBarAdapter.this.mButtons.size() - 1;
                    NavBarAdapter.this.mButtons.add(size, strArr[i]);
                    NavBarAdapter.this.mLabels.add(size, charSequenceArr[i]);
                    NavBarAdapter.this.notifyItemInserted(size);
                    NavBarEditor.this.notifyChanged();
                }
            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
        }

        private void bindClick(View view, Holder holder) {
            view.setOnClickListener(this);
            view.setTag(holder);
        }

        public void onClick(View view) {
            Holder holder = (Holder) view.getTag();
            if (view.getId() == C1777R$id.width) {
                showWidthDialog(holder, view.getContext());
            } else if (view.getId() == C1777R$id.close) {
                int adapterPosition = holder.getAdapterPosition();
                this.mButtons.remove(adapterPosition);
                this.mLabels.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                NavBarEditor.this.notifyChanged();
            }
        }

        private void showWidthDialog(Holder holder, Context context) {
            final String str = this.mButtons.get(holder.getAdapterPosition());
            String extractSize = NavigationBarInflaterView.extractSize(str);
            boolean z = false;
            float parseFloat = (extractSize == null || !extractSize.contains("W")) ? 1.0f : Float.parseFloat(extractSize.substring(0, extractSize.indexOf("W")));
            if (str.startsWith("home") || str.startsWith("back") || str.startsWith("recent")) {
                z = true;
            }
            final boolean z2 = z;
            AlertDialog create = new AlertDialog.Builder(context).setTitle(C1784R$string.adjust_button_width).setView(C1779R$layout.nav_width_view).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
            final AlertDialog alertDialog = create;
            final Holder holder2 = holder;
            create.setButton(-1, context.getString(17039370), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String extractButton = NavigationBarInflaterView.extractButton(str);
                    float access$900 = NavBarAdapter.this.getAmountValue(((SeekBar) alertDialog.findViewById(C1777R$id.seekbar)).getProgress());
                    if (access$900 == 1.0f) {
                        NavBarAdapter.this.mButtons.set(holder2.getAdapterPosition(), extractButton);
                    } else {
                        List access$600 = NavBarAdapter.this.mButtons;
                        int adapterPosition = holder2.getAdapterPosition();
                        StringBuilder sb = new StringBuilder();
                        sb.append(extractButton);
                        sb.append("[");
                        sb.append(access$900);
                        sb.append(z2 ? "WC" : "W");
                        sb.append("]");
                        access$600.set(adapterPosition, sb.toString());
                    }
                    NavBarEditor.this.notifyChanged();
                }
            });
            create.show();
            SeekBar seekBar = (SeekBar) create.findViewById(C1777R$id.seekbar);
            seekBar.setMax(6);
            seekBar.setProgress(getProgressValue(parseFloat));
        }

        public int getItemCount() {
            return this.mButtons.size();
        }
    }
}
