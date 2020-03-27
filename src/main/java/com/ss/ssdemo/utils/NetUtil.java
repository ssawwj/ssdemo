package com.ss.ssdemo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 网络工具
 *
 * @author Administrator
 *
 */
public class NetUtil {



    /**
     * 中国移动：China Mobile
     * 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     */
    private static final String CM = "^1(3[4-9]|4[7]|5[0-27-9]|7[0]|7[8]|8[2-478])\\d{8}$";
    /**
     * 中国联通：China Unicom
     * 130,131,132,145,152,155,156,1709,171,176,185,186
     */
    private static final String CU = "^1(3[0-2]|4[5]|5[56]|709|7[1]|7[6]|8[56])\\d{8}$";
    /**
     * 中国电信：China Telecom
     * 133,134,153,1700,177,180,181,189
     */
    private static final String CT = "^1(3[34]|53|77|700|8[019])\\d{8}$";

    /**
     * 获取访问的真是IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

        // 获取X-Forwarded-For
        String ip = "";
        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // 获取的IP实际上是代理服务器的地址，并不是客户端的IP地址
            ip = request.getRemoteAddr();
        }
        /*
         * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
         * X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
         * 192.168.1.100 用户真实IP为： 192.168.1.110
         */
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     *
     * @param phone_number
     * @return
     */
    public static int matchesPhoneNumber(String phone_number) {

        int flag = 0;
        if (phone_number.matches(CM)) {
            flag = 1;
        } else if (phone_number.matches(CU)) {
            flag = 2;
        } else if (phone_number.matches(CT)) {
            flag = 3;
        } else {
            flag = 4;
        }
        return flag;
    }

    public static void main(String[] args) {
        String tel = "14561198278";
        System.out.println(matchesPhoneNumber(tel));
    }



    /**
     * 获得手机号所属运营商
     *
     * @param phone
     * @return 移动:1 联通:2 电信:3
     */
    public static int getOperatePipe(String phone) {
        int sptype = 0;
        String start = phone.substring(0, 3);
        String start2 = phone.substring(0, 4);
        String start3 = phone.substring(0, 5);
        String yidong = "135.136.137.138.139.147.148.150.151.152.157.158.159.165.172.178.182.183.184.187.188.195.198";
        String yidong2 = "1340.1341.1342.1343.1344.1345.1346.1347.1348.1440.1703.1705.1706";
        String yidong3 = "10647.10648";
        String liantong = "130.131.132.145.146.155.156.166.167.171.175.176.185.186";
        String liantong2 = "1704.1707.1708.1709";
        String liantong3 = "10646";
        String dianxin = "133.149.153.162.173.177.180.181.189.191.193.199";
        String dianxin2 = "1349.1410.1700.1701.1702";
        String dianxin3 = "10649.17400.17401.17402.17403.17404.17405";
        if (yidong.contains(start) || yidong2.contains(start2) || yidong3.contains(start3)) {
            sptype = 1;
        } else if (liantong.contains(start) || liantong2.contains(start2) || liantong3.contains(start3)) {
            sptype = 2;
        } else if (dianxin.contains(start) || dianxin2.contains(start2) || dianxin3.contains(start3)) {
            sptype = 3;
        }
        return sptype;
    }
}
