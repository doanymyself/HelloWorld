package demo.helloworld.model;

/**
 * ********************************************************
 * 从数据层获取的数据，在这里进行拼装和组合。
 * （作为业务层，对获取到的数据进行拼装，然后交给调用层）
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public class TaskManager {
    TaskDataSource dataSource;

    public TaskManager(TaskDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getShowContent() {
        //Todo what you want do on the original data
        return dataSource.getStringFromRemote() + dataSource.getStringFromCache();
    }
}
