![jxufe](https://github.com/FiseTch/anaylse/raw/master/src/main/webapp/images/logo.png) 
[![baidu]](http://baidu.com)
[baidu]:http://www.baidu.com/img/bdlogo.gif "百度Logo"
#### 项目名称 ： anaylse
#### 系统框架 ：ssm(spring,springMvc,mybatis)+ maven 
#### 系统结构 : 标准的mvc模式，将整个系统划分为表现层，controller层，service层，DAO层四层
  ##### 使用spring MVC负责请求的转发和视图管理 ，spring实现业务对象管理，mybatis作为数据对象的持久化引擎
  ##### 系统说明 ：2018毕设 
#### 题目：基于信息技术环境下的教学方法（试卷分析系统）
#### 对象群体：老师
#### 功能：通过对学生成绩的测量与分析，从而反映学生掌握知识的程度及反映教师施教的水平和试卷的合理程度，以便达到促进学生的学习和教师改进教学方法的目的
#### 系统内部功能实现的一般流程 ：
  ##### 先写实体类entity，定义对象的属性，（可以参照数据库中表的字段来设置，数据库的设计应该在所有编码开始之前）。
  ##### 写Mapper.xml（Mybatis），其中定义你的功能，对应要对数据库进行的那些操作，比如 insert、selectAll、selectByKey、delete、update等。
  ##### 写Mapper.java，将Mapper.xml中的操作按照id映射成Java函数。
  ##### 写Service.java，为控制层提供服务，接受控制层的参数，完成相应的功能，并返回给控制层。
  ##### 写Controller.java，连接页面请求和服务层，获取页面请求的参数，通过自动装配，映射不同的URL到相应的处理函数，并获取参数，对参数进行处理，之后传给服务层。
  ##### 写JSP页面调用，请求哪些参数，需要获取什么数据。

#### 数据库角色：用户（老师），对象（测评成绩），用户上传数据，试卷分析结果
#### 预计实现功能： 前台用户的注册登录，excel模板的上传，生成及下载，对上传的excel的在线编辑，excel处理后结果的显示及下载。用户上传excel的保存及下载
#### 实现功能： 前台用户的注册登录，excel模板的上传，生成及下载，excel处理后结果的显示及下载。用户上传excel的保存及下载

#### 难点：前台页面运用js+css展示，调用jquery，bootstrap时有时造成版本冲突，
 ##### 前台存储时间给数据库时，会有时间延迟8小时的情况出现。
 ##### 采用modelAndView进行页面转发的时候，页面跳转会有默认值
 ##### mybatis 通过.xml文件进行与数据库之间的操作，sql语句中的变量之间的空格太多
 ##### 如何设置泛型，并设置工厂模式
 ##### java连接数据库如何设置参数的注解功能
 ##### ssm的注解功能的具体实现

