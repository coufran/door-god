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
## 使用决策注解定义校验规则
1. 普通校验

在待校验的属性或getter方法上添加决策注解，两种方式等价，可同时使用。

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
2. 指定错误消息的校验

决策注解中，可以使用``message``自定义消息。

示例：
```java
class Entity {
    @NotNull(message = "请输入值")
    private String value;

    @NotNull(message = "请输入值")
    public String getValue() {
        return value;
    }
}
```
3. 指定校验组的校验

决策注解中，可以使用``group``自定义校验组，在校验时，也会指定校验组，只有相同校验组的决策注解才会生效。

示例：
```java
class MyGroups {
    public static final String TEST = "test";
}

class Entity {
    @NotNull(group = MyGroups.TEST)
    private String value;

    @NotNull(group = {MyGroups.TEST, Groups.DEFAULT})
    public String getValue() {
        return value;
    }
}
```
4. 同时使用多个相同的注解

每个决策注解都有一个内部注解``List``，可以通过该注解标记多个决策注解。

示例：

```java
class MyGroups {
    public static final String INPUT = "input";
    public static final String SCAN = "scan";
}

class Entity {
    @NotNull.List({
            @NotNull(message = "请输入值", groups = MyGroups.INPUT)
            @NotNull(message = "请扫描值", groups = MyGroups.SCAN)
    })
    private String value;

    public String getValue() {
        return value;
    }
}
```
## 内置决策器
| |工厂方法|决策器|备注|
|---|---|---|---|
|1|``notNull()``     |``NotNullDecider``   |非空     |
|2|``isNull()``      |``IsNullDecider``    |空       |
|3|``greaterThan()`` |``ComparableDecider``|大于     |
|4|``greaterEqual()``|``ComparableDecider``|大于等于  |
|5|``lessThan()``    |``ComparableDecider``|小于     |
|6|``lessEqual()``   |``ComparableDecider``|小于等于  |
|7|``between()``     |``ComparableDecider``|区间     |
|8|``is()``          |``EqualDecider``     |相同     |
|9|``not()``         |``NotEqualDecider``  |不同     |

## 内置决策注解
| |决策注解|决策器|备注|
|---|---|---|---|
|1|``@NotNull``|``NotNullDecider``    |非空|
|2|``@IsNull`` |``IsNullDecider``     |空 |
|3|``@Min``    |``ComparableDecider`` |最小|
|4|``@Max``    |``ComparableDecider`` |最大|
|5|``@Between``|``ComparableDecider`` |区间|
|6|``@Is``     |``EqualDecider``      |相同|
|7|``@Not``    |``NotEqualDecider``   |不同|

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
2. 填充默认方法
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = MyDecider.class)
public @interface MyDecide() {
    // 定义决策消息
    String message() default "";
    // 定义校验组
    String[] groups() default Groups.DEFAULT;
}
```
3. 使用``@Property``定义决策参数
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = MyDecider.class)
public @interface MyDecide() {
    @Property("compareValue") // 定义对应的决策器属性名，没有参数默认使用方法名，即"value"
    String value() default null;
}
```
> 注意：决策器需要有对应属性唯一的setter方法
4. 增加List内部注解
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = MyDecider.class)
public @interface MyDecide() {
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
    @DecideList
    @interface List {
        MyDecide[] value();
    }
}
```
5. 使用决策注解
```java
class Entity {
    @MyDecide("xxx")
    private String value;
    // getter
}
```