package m2.ila.vv.targets;

public class GetterSetterTarget {
    public int i;
    public String str;
    private double internal;
    protected byte internal2;

    public GetterSetterTarget(int i, String str) {
        this.i = i;
        this.str = str;
        internal = 0.0;
        internal2 = 1;
    }

    public void print() {
        System.out.println("i: " + i + ", str: " + str + ", internal: " + internal + ", internal2: " + internal2);
    }
}
