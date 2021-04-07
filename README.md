# DoorGod
DoorGod是一个校验器。

# 用法
## 静态调用
建议静态引入使用的方法，例如。
```java
import static cn.coufran.doorgod.Checker.*; // 引入check()
import static cn.coufran.doorgod.decider.Deciders.*; // 引入deciders
```
1. 判定某条件是否成立。
```java
// 使用默认消息
check(CustomDecider decider);
// 指定非法消息
check(CustomDecider decider, String message);
```
示例：
```java
// 使用默认消息
check(() -> {
    // return true or false
    return "doorGod" != null;
});

// 指定非法消息
check(() -> {
    // return true or false
    return "doorGod" != null;
}, "参数不合法");
```
2. 使用决策器``Decider``决策。
```java
// 使用默认消息，消息会根据决策器生成
check(T value, Decider<T> decider);
// 指定非法消息
check(T value, Decider<T> decider, String message);
```
示例：
```java
// 使用默认消息
check("DoorGod", notNull()); // 使用了Deciders.notNull()构造决策器
// 指定非法消息
check("DoorGod", notNull(), "DoorGod is illegal");
```
3. 校验POJO的某个字段
```java
// 使用默认消息
check(T entity, Function<T, R> function, Decider<R> decider);
// 指定非法消息
check(T entity, Function<T, R> function, Decider<R> decider, String message);
```
示例：
```java
// 定义实体类，实体类包含value:String属性
Entity entity = new Entity();
entity.setValue("DoorGod");
// 使用默认消息
check(entity, Entity::getValue, notNull());
// 指定非法消息
check(entity, Entity::getValue, notNull(), "DoorGod is illegal");
```
4. 校验实体
```java
// 使用默认校验范围
check(entity);
// 指定校验范围（暂不支持）
check(entity, "default");
```
示例：
```java
// 首先在POJO类定义校验规则
class Entity {
    @NotNull(group="default") // 也可以定义在getter方法上
    private String value;
    
    // getter and setter（必须有getter）
}
// 定义实体类
Entity entity = new Entity();
entity.setValue("DoorGod");
// 使用默认校验范围
check(entity);
// 指定校验范围
check(entity, "default");
```
## 使用校验注解定义校验规则
1. 普通校验

在待校验的属性或getter方法上添加校验注解，两种方式等价，可同时使用。

示例：
```java
class Entity {
    @NotNull
    private String value;

    @NotNull
    public String getValue() {
        return value;
    }
}
```
## 自定义决策器
1. 定义一个类，实现``Decider``接口，重写``decide()``方法。
```java
public class MyDecider<T> implements Decider<T> {
    // 属性、getter、setter等
    
    @Override
    public boolean decide(T value) {
        // 校验通过返回true，否则返回false
        return true;
    }
}
```
2. 定义决策参数（可选）。
```java
public class MyDecider implements Decider<String> {
    private String compareValue;

    public void setCompareValue(String compareValue) {
        this.compareValue = compareValue;
    }

    // 属性、getter、setter等

    @Override
    public boolean decide(String value) {
        // 使用compareValue校验value
        // 校验通过返回true，否则返回false
        return true;
    }
}
```
3. 为了方便调用，定义工厂类（可选，建议）。
```java
public class MyDeciders {
    public static MyDecider myDecide(String compareValue) {
        // 任意方法构造
        MyDecider decider = new MyDecider();
        decider.setCompareValue(compareValue);
        return decider;
    }
}
```
4. 使用决策器
```java
import static xxx.xxx.MyDeciders.*;

Checker.check("xxx", myDecide("xxxx"));
```

## 自定义决策注解
1. 定义注解，使用``@Decide()``标注该注解，使用``decideBy``指定决策器。
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = MyDecider.class) // 定义决策注解，指定决策器
public @interface MyDecide() {
}
```
> 注意：决策器需要有无参构造方法
2. 定义决策参数（可选）
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = MyDecider.class)
public @interface MyDecide() {
    @Property("compareValue") // 定义对应的决策器属性名，不定义默认使用方法名，即value
    String value() default null;
}
```
> 注意：决策器需要有对应属性唯一的setter方法
3. 使用决策注解
```java
class Entity {
    @MyDecide("xxx")
    private String value;
    // getter
}
```