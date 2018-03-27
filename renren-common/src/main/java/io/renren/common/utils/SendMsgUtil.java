package io.renren.common.utils;

public class SendMsgUtil {
    /**
     * 发送短信消息
     * 方法说明
     *
     * @param phones
     * @param content
     * @return String
     * @Discription:扩展说明
     */
    @SuppressWarnings("deprecation")
    public static String sendMsg(String phones, String content) {

        return null;
    }

    /**
     * 随机生成6位随机验证码
     * 方法说明
     *
     * @return String
     * @Discription:扩展说明
     */
    public static String createRandomVcode() {
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }


}