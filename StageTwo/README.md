
                                            StageTwo      
                                                            ArrayList + io
            此StageTwo是在StageOne的基础之上使用ArrayList实现，并且按照要求对代码进行了简单重构。
  
    同时进行数据的持久化，将数据保存在文本文档中，程序加载时自动读取文件内容生成集合类，增加删除修改类内容同步到文本文档中。
   
设计思路：
    1、首先将原先的数组改为集合，实现的更加简单了
    2、代码重构，增加接口菜单类，增加姓名的模糊查询
    3、进行数据的持久化，将数据保存到文本文档中，将数据读取到集合中并生成新的对象
 
所遇困难：
    1、最大的困难便是NullPointerException了，基本上处处都可能出现，花费了很多的时间查错与修改
    2、增加文件操作类，原操作方法为别对Student和Staff进行操作，读取和保存都需要进行异常处理，可能会出现NullPointerException
    3、对操作方法增加通配符，如 ArrayList<? extends Person> 便可以在一个方法中对Student和Staff进行操作
    
心得体会:
    1、解决了一个又一个bug，最后成功的按照自己的思路完整的跑完了整个程序，感觉非常的快乐
    2、使用多种方法完成同一个要求