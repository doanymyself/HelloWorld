package demo.helloworld.model;

/**
 * ********************************************************
 * 数据层，直接负责数据获取，无论是从api获得，
 * 还是从本地数据库读取数据，本质上都是IO操作。
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public class TaskDataSourceImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "Hello ";
    }

    @Override
    public String getStringFromCache() {
        return "World";
    }
}
