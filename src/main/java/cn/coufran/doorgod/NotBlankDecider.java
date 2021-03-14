package cn.coufran.doorgod;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class NotBlankDecider implements Decider {
    @Override
    public boolean decide(Object value) {
        return value != null && !"".equals(value);
    }
}
