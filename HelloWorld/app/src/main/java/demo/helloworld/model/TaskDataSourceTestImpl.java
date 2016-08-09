package demo.helloworld.model;

/**
 * ********************************************************
 * <p/>
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public class TaskDataSourceTestImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "Hello ";
    }

    @Override
    public String getStringFromCache() {
        return " world Test ";
    }
}
