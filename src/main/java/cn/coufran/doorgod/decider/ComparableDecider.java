package cn.coufran.doorgod.decider;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComparableDecider<T extends Comparable<T>> implements Decider<T> {
    private T min;
    private boolean includeMin = true;
    private T max;
    private boolean includeMax = true;

    public ComparableDecider() {
    }

    public ComparableDecider(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public ComparableDecider(T min, boolean includeMin, T max, boolean includeMax) {
        this.min = min;
        this.includeMin = includeMin;
        this.max = max;
        this.includeMax = includeMax;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public void setIncludeMin(boolean includeMin) {
        this.includeMin = includeMin;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public void setIncludeMax(boolean includeMax) {
        this.includeMax = includeMax;
    }

    @Override
    public boolean decide(T t) {
        boolean result = true;
        if(min != null) {
            int compareValue = t.compareTo(min);
            if(includeMin) {
                result = result && compareValue > 0;
            } else {
                result = result && compareValue >= 0;
            }
        }
        if(result && max != null) {
            int compareValue = t.compareTo(max);
            if(includeMax) {
                result = result && compareValue < 0;
            } else {
                result = result && compareValue <= 0;
            }
        }
        return result;
    }
}
