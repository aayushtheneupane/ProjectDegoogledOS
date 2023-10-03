package p003j$.util.stream;

import java.util.Arrays;
import p003j$.util.Spliterator;
import p003j$.util.function.Consumer;
import p003j$.util.function.IntFunction;
import p003j$.util.stream.Node;

/* renamed from: j$.util.stream.Nodes */
abstract class Nodes {
    static {
        new EmptyNode.OfRef();
    }

    static Node.Builder builder(long j, IntFunction intFunction) {
        if (j < 0 || j >= 2147483639) {
            return builder();
        }
        return new FixedNodeBuilder(j, intFunction);
    }

    static Node.Builder builder() {
        return new SpinedNodeBuilder();
    }

    /* renamed from: j$.util.stream.Nodes$EmptyNode */
    abstract class EmptyNode implements Node {
        public void forEach(Object obj) {
        }

        EmptyNode() {
        }

        /* renamed from: j$.util.stream.Nodes$EmptyNode$OfRef */
        class OfRef extends EmptyNode {
            public /* bridge */ /* synthetic */ void forEach(Consumer consumer) {
                super.forEach(consumer);
            }

            private OfRef() {
            }
        }
    }

    /* renamed from: j$.util.stream.Nodes$ArrayNode */
    abstract class ArrayNode implements Node {
        final Object[] array;
        int curSize;

        ArrayNode(long j, IntFunction intFunction) {
            if (j < 2147483639) {
                this.array = (Object[]) intFunction.apply((int) j);
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void forEach(Consumer consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }
    }

    /* renamed from: j$.util.stream.Nodes$FixedNodeBuilder */
    final class FixedNodeBuilder extends ArrayNode implements Node.Builder {
        public boolean cancellationRequested() {
            return Sink$$CC.cancellationRequested$$dflt$$(this);
        }

        FixedNodeBuilder(long j, IntFunction intFunction) {
            super(j, intFunction);
        }

        public Node build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
        }

        public void begin(long j) {
            if (j == ((long) this.array.length)) {
                this.curSize = 0;
            } else {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", new Object[]{Long.valueOf(j), Integer.valueOf(this.array.length)}));
            }
        }

        public void accept(Object obj) {
            int i = this.curSize;
            Object[] objArr = this.array;
            if (i < objArr.length) {
                this.curSize = i + 1;
                objArr[i] = obj;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", new Object[]{Integer.valueOf(this.array.length)}));
        }

        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
            }
        }

        public String toString() {
            return String.format("FixedNodeBuilder[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    /* renamed from: j$.util.stream.Nodes$SpinedNodeBuilder */
    final class SpinedNodeBuilder extends SpinedBuffer implements Node, Node.Builder {
        private boolean building = false;

        public Node build() {
            return this;
        }

        public boolean cancellationRequested() {
            return Sink$$CC.cancellationRequested$$dflt$$(this);
        }

        SpinedNodeBuilder() {
        }

        public Spliterator spliterator() {
            return super.spliterator();
        }

        public void forEach(Consumer consumer) {
            super.forEach(consumer);
        }

        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        public void accept(Object obj) {
            super.accept(obj);
        }

        public void end() {
            this.building = false;
        }
    }
}
