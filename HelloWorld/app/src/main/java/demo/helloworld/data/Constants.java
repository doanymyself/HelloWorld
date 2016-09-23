package demo.helloworld.data;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/8/10.
 */
public class Constants {
    // 底栏要显示的模块
    public static String[] moduleStr = {"首页", "资讯", "社区", "活动", "个人"};
    // 生产服务器环境(域名)
    public static String HOST = "http://api.ncihealth.cn";
    // 准生产测试服务器环境(域名)
    // public static String HOST = "http://t.app.ncihealth.cn";
    // 准生产测试服务器环境(ip)
    // public static String HOST = "http://10.1.44.120:8080";
    // 外网测试服务器环境(ip)
    // public static String HOST = "http://219.143.202.184:8080";
    // 内网测试服务器环境(ip)
    // public static String HOST = "http://10.1.44.46:8080";
    public static String REQUST_PORT = "http://yunlive.applinzi.com/";
    public static String REQUST_UPLOADPIC = "http://yunlive.applinzi.com/upload.php";

    //聚合数据 历史上的今天 事件列表URL
    public static String TODAY_IN_HISTORY_URL = "http://api.juheapi.com/japi/toh";
    //聚合数据 历史上的今天 APPKEY
    public static String TODAY_IN_HISTORY_APPKEY = "ee722030a163b0971c61f8da248e5357";
}
