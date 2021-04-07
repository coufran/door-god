package cn.coufran.doorgod.decider;

/**
 * 范围比较器，比较某值是否在某个范围区间内，可指定区间开闭
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComparableDecider<T extends Comparable<T>> implements Decider<T> {
    /** 范围下界：最小值 */
    private T min;
    /** 是否左闭：范围是否包含下界 */
    private boolean includeMin = true;
    /** 范围上界：最大值 */
    private T max;
    /** 是否右闭：范围是否包含上界 */
    private boolean includeMax = true;

    /**
     * 无参构造方法
     */
    public ComparableDecider() {
    }

    /**
     * 指定界限的构造方法，默认左闭右闭
     * @param min 范围下界
     * @param max 范围上界
     */
    public ComparableDecider(T min, T max) {
        this.min = min;
        this.max = max;
    }

    /**
     * 指定界限和开闭情况的构造方法
     * @param min 范围下界
     * @param includeMin 是否左闭
     * @param max 范围上界
     * @param includeMax 是否右闭
     */
    public ComparableDecider(T min, boolean includeMin, T max, boolean includeMax) {
        this.min = min;
        this.includeMin = includeMin;
        this.max = max;
        this.includeMax = includeMax;
    }

    /**
     * 设置范围下界
     * @param min 范围下界
     */
    public void setMin(T min) {
        this.min = min;
    }

    /**
     * 设置是否左闭
     * @param includeMin 是否左闭
     */
    public void setIncludeMin(boolean includeMin) {
        this.includeMin = includeMin;
    }

    /**
     * 设置范围上界
     * @param max 范围上界
     */
    public void setMax(T max) {
        this.max = max;
    }

    /**
     * 设置是否右闭
     * @param includeMax 是否右闭
     */
    public void setIncludeMax(boolean includeMax) {
        this.includeMax = includeMax;
    }

    /**
     * 范围决策
     */
    @Override
    public boolean decide(T t) {
        boolean result = true;
        if(min != null) {
            int compareValue = t.compareTo(min);
            if(includeMin) {
                result = compareValue >= 0;
            } else {
                result = compareValue > 0;
            }
        }
        if(result && max != null) {
            int compareValue = t.compareTo(max);
            if(includeMax) {
                result = compareValue <= 0;
            } else {
                result = compareValue < 0;
            }
        }
        return result;
    }
}
