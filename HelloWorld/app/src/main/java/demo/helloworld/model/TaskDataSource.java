package demo.helloworld.model;

/**
 * ********************************************************
 * data 层接口定义,作为数据层对外的接口
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public interface TaskDataSource {
    String getStringFromRemote();

    String getStringFromCache();
}
