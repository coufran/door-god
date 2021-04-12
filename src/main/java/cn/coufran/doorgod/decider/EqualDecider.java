package cn.coufran.doorgod.decider;

/**
 * 判等比较器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class EqualDecider<T> implements Decider<T> {

    /** 比较值 */
    private T compareValue;

    /**
     * 无参构造方法
     */
    public EqualDecider() {
    }

    /**
     * 指定比较值的构造方法
     * @param compareValue 比较值
     */
    public EqualDecider(T compareValue) {
        this.compareValue = compareValue;
    }

    /**
     * 获取比较值
     * @return 比较值
     */
    public T getCompareValue() {
        return compareValue;
    }

    /**
     * 设置比较值
     * @param compareValue 比较值
     */
    public void setCompareValue(T compareValue) {
        this.compareValue = compareValue;
    }

    @Override
    public boolean decide(Object value) {
        if(this.compareValue == null) {
            return value == null;
        }
        return this.compareValue.equals(value);
    }
}
