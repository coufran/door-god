package cn.coufran.doorgod.decider;

/**
 * 不等判定器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class NotEqualDecider<V> implements Decider<V> {
    /** 比较值 */
    private V compareValue;

    /**
     * 无参构造方法
     */
    public NotEqualDecider() {
    }

    /**
     * 指定比较值的构造方法
     * @param compareValue 比较值
     */
    public NotEqualDecider(V compareValue) {
        this.compareValue = compareValue;
    }

    /**
     * 获取比较值
     * @return 比较值
     */
    public V getCompareValue() {
        return compareValue;
    }

    /**
     * 设置比较值
     * @param compareValue  比较值
     */
    public void setCompareValue(V compareValue) {
        this.compareValue = compareValue;
    }

    @Override
    public boolean decide(V value) {
        if(compareValue == null) {
            return value != null;
        }
        return !this.compareValue.equals(value);
    }
}
