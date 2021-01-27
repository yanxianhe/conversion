package com.yxh.conversion.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import java.lang.management.ManagementFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
public class MyUtils {
    /* 
     * 将时间戳转换为时间
     * <p>yyyy-MM-dd HH:mm:ss</p>
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /* 
     * 将时间转换为时间戳
     */ 
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = (Date) simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /* 
     * 生成唯一id
     * System.currentTimeMillis() + Random  
     */
    public static String getDateUUid(int rs){
        Random random = new Random();
        String res;
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long lt = new Long(System.currentTimeMillis());
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        for(int j = 0; j < rs; j++){
            result += random.nextInt(10);
        }
        return res+result;
    }
    /**
    * 根据年份生成系统编号
    * @return
    */
    public static String next(String code) {

        File file = getCountFile(code);
        
        if(StringUtils.isBlank(code)) {
            code = MyConstants.SYS_NAME;
        }
        try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
            String tp = fr.readLine();
            if(StringUtils.isBlank(tp)) {
                tp="0";
            }
            int nextNum = Integer.valueOf(tp) + 1;
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(String.valueOf(nextNum).getBytes());
            }
            String next = "";
            if (nextNum / 10 < 1) {
                next += "0000" + nextNum;
            } else if (nextNum / 100 < 1) {
                next += "000" + nextNum;
            } else if (nextNum / 1000 < 1) {
                next += "00" + nextNum;
            } else if (nextNum / 10000 < 1) {
                next += "0" + nextNum;
            } else {
                next += nextNum;
            }
            int year = LocalDate.now().getYear();
            return code + "" + year + "-" + next;
        } catch (IOException e) {
            e.printStackTrace();
            return "生成异常";
        }
    }
    /**
    * <p>记录编号</p>
    */
    private static File getCountFile(String code) {
        File file = new File(code + MyConstants.SYS_ON_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write("0".getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }
    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    public static void main(String[] args) {
        for(int i=0 ; i< 1 ;i++){
            // 构建 19 位 随机码
            System.out.println(getDateUUid(5));
            // 构建 20 位 随机码
            System.out.println(getDateUUid(6));
            // 构建 21 位 随机码
            System.out.println(getDateUUid(7));
        }

        // for(int i=0 ; i< 10 ;i++){
        //     System.out.println(next("cms-"));
        // }
        
    }

}
