# JavaEE-03-AOP 手动实现事务管理

***

2020年4月23日 更新

目前只为 HomeworkService 添加了切面，进入页面点击“学生登录”即可使用

在 connection 的获取与 setAutoCommit(false) 方法还存在疑惑

比如 before() 中对 connection 使用的 setAutoCommit(false) 方法似乎对 after() 中的 connection 不起作用，怀疑两个方法获取的 connection 不是同一个

尝试直接 hikariDataSource.setAutoCommit(false)，查询 github 首页知：autocommit 属性含义为 自动提交从池中返回的连接，应该是与目的相符，但结果与预期不符

