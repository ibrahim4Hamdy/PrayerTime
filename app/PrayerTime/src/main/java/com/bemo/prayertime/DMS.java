package com.bemo.prayertime;

public class DMS {
    private final int D;
    private final int M;
    private final float S;
    private static DMS _instance;

    public static DMS getInstance(double dd){
        if (null == _instance) return  _instance = new DMS(dd);

        return _instance;
    }

    public DMS(double dd) {
        this.D=(int)dd;
        this.M=(int)((dd-this.D)*60);
        this.S= (float)(((dd-this.D)-(this.M/60))*3600);

    }

    public String getDMS() {
        return
                String.valueOf(this.D)+":"+
                String.valueOf(this.M)+":"+
                String.valueOf(this.S);
    }
    public String getDM() {
        return (
                String.valueOf(this.D)+":"+
                String.valueOf(this.M)
        );
    }
    public int getD() {
        return D;
    }

    public int getM() {
        return M;
    }

    public double getS() {
        return S;
    }
}
