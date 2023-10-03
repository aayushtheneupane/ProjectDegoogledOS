package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.a */
class C0553a {
    int cmd;
    int itemCount;
    Object payload;
    int positionStart;

    C0553a(int i, int i2, int i3, Object obj) {
        this.cmd = i;
        this.positionStart = i2;
        this.itemCount = i3;
        this.payload = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0553a.class != obj.getClass()) {
            return false;
        }
        C0553a aVar = (C0553a) obj;
        int i = this.cmd;
        if (i != aVar.cmd) {
            return false;
        }
        if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == aVar.positionStart && this.positionStart == aVar.itemCount) {
            return true;
        }
        if (this.itemCount != aVar.itemCount || this.positionStart != aVar.positionStart) {
            return false;
        }
        Object obj2 = this.payload;
        if (obj2 != null) {
            if (!obj2.equals(aVar.payload)) {
                return false;
            }
        } else if (aVar.payload != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.cmd;
        sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.positionStart);
        sb.append("c:");
        sb.append(this.itemCount);
        sb.append(",p:");
        sb.append(this.payload);
        sb.append("]");
        return sb.toString();
    }
}
