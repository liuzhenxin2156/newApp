package com.example.newapp.utils;

public class AppConstants {

    //wx APP_ID
    public static final String APP_ID = "wxf9e6f5338349d7b0";

    //WX 密钥
    public static final String APP_SERECET = "bcd56dd8ca5d2d5059e4ec6cd6c836d1";

    //请求地址
    public static final String SERVER_BASE_URL = "http://apitest.agridata.org";

    //服务协议
    public static final String AGREEMENT_URL = "http://api.agridata.org/upload/appdoc/agreement.html";

    //隐私政策
    public static final String PRIVACY_URL = "http://api.agridata.org/upload/appdoc/privacy.html";

    //GPS 购买
    public static final String WEIDIAN_URL = "https://weidian.com/item.html?itemID=3336351319";

    //保险服务
    public static final String INSURANCE_URL = "http://dwj.scahi.org.cn/Pages/Wx/Insurance/menu.html";


    //相对人类型
    public static final String para_xdr_category = "para_xdr_category";

    //证件类型
    public static final String para_identity_type = "para_identity_type";

    //默认加密字符
    public static final String TOKEN_DEL = "baoxun20190801";

    //UUID
    public static String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    /**
     * RxEvent事件通知1.0
     */
    public interface RxEvent {
        String WX_LOGIN = "wx_login";                                    //wx登录
        String UPDATA_NAME = "updata_name";                              //更新名字
        String INFORMATION = "information";                             //选择畜主

    }

    /**
     * code 码
     */
    public interface CODE {
        int STATE_CODE = 200;                                    //200 ro  400
        int CODE_ZERO = 0;                                    //0
        int CODE_ONE = 1;                                    //1
        int CODE_FUONE = -1;                                   //-1
    }

    /**
     * SharedPreferences常量
     */
    public interface SP {
        String IS_TOKEN = "is_token";                                              //保存token
        String IS_USERID = "is_userid";                                              //保存token
        String IS_WX_SUCCESS_INFO = "is_wx_success_info";                          //保存WXSuccessInfo
        String IS_SHOW_SERVICE = "is_show_service";                          // 是否显示隐私服务弹窗
        String IS_WX_FIRST_LOGIN = "is_wx_first_login";                         //wx登录更新
        String PIGTRANSITCAR = "pig_transit_car";                             //生猪运输车辆
        String RESOURCE_PATH = "resource_path";                          // App资源路径
        String DEVICE_ID = "device_id";                              // deviceId
    }

    public interface TYPE {
        String[] stringsType = {"规模厂", "散养户", "贩运户", "无害化处理厂", "屠宰场", "其他"};
        String[] stringsPerorUnit = {"个人", "单位"};
        String[] stringsCarCarrying = {"大于4.5吨", "小于4.5吨（含）"};
        String[] stringsCarLicenseColor = {"蓝", "黄", "小黄牌", "农用车", "三轮车", "新能源", "未知"};
        String[] stringsCarAddress = {"县内", "省内", "跨省"};
        String[] stringsTypeOfTransportation = {"生猪运输", "牛羊运输", "禽类运输", "产品运输"};
        String[] stringsAnimal = {"猪", "肉牛", "奶牛", "羊", "肉鸡", "蛋鸡", "鸭", "鹅", "兔", "鸽", "鹌鹑", "马", "驴", "骡"};
    }

    /**
     * 验证器常量
     */
    public interface Validator {
        // 正则表达式：验证用户名（规则：11位手机号或邮箱）
        String REGEX_ACCOUNT = "^(\\d{11})|([\\w\\.\\-_]+@[\\w-]+[\\.][\\w-]+[\\w-_.]*)$";

        //正则表达式：验证校验码（规则：4位数字）
        String REGEX_CHECKCODE = "\\d{6}";

        // 正则表达式：验证密码（规则6-20位数字或字母或符号至少包括2种）
        //  String REGEX_PASSWORD = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
        String REGEX_PASSWORD = "^(?!^[0-9]+$)(?!^[a-zA-Z]+$)(?!^[^a-zA-Z0-9]+$)^.{6,20}$";

        // 正则表达式：验证手机号
        // String REGEX_MOBILE = "^[1][34578][0-9]{9}$";
        String REGEX_MOBILE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

        // 正则表达式：验证昵称
        String REGEX_NICKNAME = "^([\\u4e00-\\u9fa5\\w_-]*)$";

        // 正则表达式：验证汉字、字母、数字
        String REGEX_CHAR_NUMBER = "^([\\u4e00-\\u9fa5\\w]*)$";

        // 正则表达式：验证邮箱
        //String REGEX_EMAIL = "^([a-z0-9A-Z_]+[-|\\.]?)+@([a-z0-9A-Z]+?\\.)+[a-zA-Z]{2,}$";
        String REGEX_EMAIL = "[\\w\\.\\-_]+@[\\w-]+[\\.][\\w-]+[\\w-_.]*$";

        // @{senderCube:10225,name:Cymbi}消息验证
        String REGEX_AT_MESSAGE = "@\\{cube:[^,]*,name:[^\\}]*\\}";

        //{"qrKey":"ebd4b9ed-579d-4ccd-bb7c-91d2a200f0acqr_login","osName":"web","expiredTimestamp":1492756338953} 扫码登录
        String REGEX_SCAN_LOGIN = "\\{\"qrKey\":\".+\",\"osName\":\".+\",\"expiredTimestamp\":\\d*\\}";

        // 定义html转义字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        String REGEX_HTML_SPECIAL = "\\&[a-zA-Z]{1,10};";

        //群卡片电话格式
        String REGEX_CARD_PHONE = "^[0-9\\-]{3,20}$";
    }
}
