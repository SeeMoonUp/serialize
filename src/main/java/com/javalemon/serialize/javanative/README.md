## java原生序列化方式
### 使用方式

1. 如果类的字段表示的就是类的逻辑信息，如基本的POJO类，那就可以使用默认序列化机制，只要声明实现Serializable接口即可
2. 否则的话，如LinkedList，那就可以使用transient关键字，实现writeObject和readObject自定义序列化过程。
3. Java的序列化机制可以自动处理如引用同一个对象、循环引用等情况。

### 序列化原理

序列化到底是如何发生的？关键在ObjectInputStream的readObject和ObjectOutputStream的writeObject
方法中，它们的实现都非常复杂，正因为这些复杂的实现才使得序列化看上去很神奇，我们简单介绍其基本逻辑。

writeObject的基本逻辑是：

1. 如果对象没有实现Serializable，抛出异常NotSerializableException
2. 每个对象都有一个编号，如果之前已经写过该对象了，则本次只会写该对象的引用，这可以解决对象引用和循环引用的问题。
3. 如果对象实现了writeObject方法，调用它的自定义方法
4. 默认是利用反射机制，遍历对象结构图，对每个没有标记为transient的字段，根据其类型，分别进行处理，写出到流，流中的信息包括字段的类型，即完整类型、字段名、字段值等。

readObject的基本逻辑是：

1. 不调用任何的构造方法
2. 它自己就相当于是一个独立的构造方法，根据字节流初始化对象，利用的也是反射机制
3. 在解析字节流时，对于引用到的类型信息，会动态加载，如果找不到类，会抛出ClassNotFoundException