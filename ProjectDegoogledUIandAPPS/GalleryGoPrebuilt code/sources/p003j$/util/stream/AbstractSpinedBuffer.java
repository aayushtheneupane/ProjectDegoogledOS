package p003j$.util.stream;

/* renamed from: j$.util.stream.AbstractSpinedBuffer */
abstract class AbstractSpinedBuffer {
    protected int elementIndex;
    protected final int initialChunkPower = 4;
    protected long[] priorElementCount;
    protected int spineIndex;

    protected AbstractSpinedBuffer() {
    }

    /* access modifiers changed from: protected */
    public int chunkSize(int i) {
        int i2;
        if (i == 0 || i == 1) {
            i2 = this.initialChunkPower;
        } else {
            i2 = Math.min((this.initialChunkPower + i) - 1, 30);
        }
        return 1 << i2;
    }
}
