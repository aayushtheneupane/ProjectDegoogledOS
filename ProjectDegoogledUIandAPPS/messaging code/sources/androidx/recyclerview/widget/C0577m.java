package androidx.recyclerview.widget;

import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.m */
class C0577m {
    public int fromX;
    public int fromY;
    public C0586qa newHolder;
    public C0586qa oldHolder;
    public int toX;
    public int toY;

    C0577m(C0586qa qaVar, C0586qa qaVar2, int i, int i2, int i3, int i4) {
        this.oldHolder = qaVar;
        this.newHolder = qaVar2;
        this.fromX = i;
        this.fromY = i2;
        this.toX = i3;
        this.toY = i4;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("ChangeInfo{oldHolder=");
        Pa.append(this.oldHolder);
        Pa.append(", newHolder=");
        Pa.append(this.newHolder);
        Pa.append(", fromX=");
        Pa.append(this.fromX);
        Pa.append(", fromY=");
        Pa.append(this.fromY);
        Pa.append(", toX=");
        Pa.append(this.toX);
        Pa.append(", toY=");
        Pa.append(this.toY);
        Pa.append('}');
        return Pa.toString();
    }
}
