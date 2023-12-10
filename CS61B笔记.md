# 作业配置

https://sp21.datastructur.es/materials/lab/lab2setup/lab2setup

作业中会有一个 `pom.xml`，用来指定库的位置(不然要手动指定)

要使用，先从`File | Settings`  打开设置，然后 `Build, Execution, Deployment > Build Tools > Maven`

![maven-settings](https://img2023.cnblogs.com/blog/1892247/202310/1892247-20231016205552953-1287662953.png)

确保右上角显示“对于新项目”。更新以下设置：

1. 选中“Use plugin registry”按钮
2. 在底部，选中“Local repository”行上的 override 按钮
3. 将“本地存储库”路径更新为 `javalib` 的路径
4. 保存

之后就可以通过 `pom.xml` 来导入



注意一个 Style的问题，就是构造函数不用写public(因为肯定得public)，以及接口的所有method也全都不用写public



一个动态类型选择的复习题，看了基本上就会了

```java
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greet(Person other) {System.out.println("Hello, " + other.name);}
}

public class Grandma extends Person {

    public Grandma(String name, int age) {
        super(name, age);
    }

    @Override
    public void greet(Person other) {System.out.println("Hello, young whippersnapper");}

    public void greet(Grandma other) {System.out.println("How was bingo, " + other.name + "?");}
}

public class testPeople {
    public static void main(String[] args) {
        Person n = new Person("Neil", 12);
        Person a = new Grandma("Ada", 60);
        Grandma v = new Grandma("Vidya", 80);
        Grandma al = new Person("Alex", 70); // Compile time error
        n.greet(a); // "Hello Ada"
        n.greet(v); // "Hello Vidya"
        v.greet(a); // "Hello, young whippersnapper"
        v.greet((Grandma) a); // "How was bingo, Ada?"
        a.greet(n); // "Hello, young whippersnapper"
        a.greet(v); // "Hello, young whippersnapper"
        ((Grandma) a).greet(v); // "How was bingo, Vidya?"
        ((Grandma) n).greet(v); // Runtime error
    }
}
```



# Lab8

注意 `instanceof ` 不能直接用于泛型判断，使用以下的代码解决问题

```java
@Override
public boolean equals(Object o) {
    if (!(o instanceof MyHashMap.Node)) {
        return false;
    }
    MyHashMap.Node other = (MyHashMap.Node) o;
    return key.equals(other.key) && value.equals(other.value);
}
```



# K-D Tree

![image-20231208221034273](https://img2023.cnblogs.com/blog/1892247/202312/1892247-20231208221039705-2095727638.png)

第 i 层用第 i%k 维来比较即可，其实也相当于一个二分（相当于用一个超平面去二分）

至于寻找最近的，只能通过 pruning 来优化，即优先搜好的那一边

![image-20231208223906644](https://img2023.cnblogs.com/blog/1892247/202312/1892247-20231208223915726-909214328.png)

# LZW 压缩

如下图所示，每次编码时往表中加入一个（当前匹配串+后一个字符），这样在下次碰到重复字符串的时候就可以复用

![image-20231210153923964](https://img2023.cnblogs.com/blog/1892247/202312/1892247-20231210153929416-1331867493.png)

![image-20231210153950618](https://img2023.cnblogs.com/blog/1892247/202312/1892247-20231210153953056-454439512.png)

在解码的时候，每次往表中加入（上一个匹配到的单词+这次匹配到的单词的第一个字母）

![image-20231210155046377](https://img2023.cnblogs.com/blog/1892247/202312/1892247-20231210155049996-1873648657.png)

![image-20231210155104712](https://img2023.cnblogs.com/blog/1892247/202312/1892247-20231210155107456-1543144882.png)
