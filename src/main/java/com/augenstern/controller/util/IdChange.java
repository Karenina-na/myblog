package com.augenstern.controller.util;

import java.util.Random;

/**
 * 转码工具
 * id结构：因数-id-随机数-长度
 */
public class IdChange {
    /**
     * 后端转前端
     * @param OldId
     * @return
     */
    public static int BackToFront(int OldId) {
        Random random = new Random();
        int factor = random.nextInt(9) + 1;
        StringBuilder result = new StringBuilder();
        String id = String.valueOf(OldId*factor);
        //乘数因子
        result.append(factor);
        //id
        result.append(id);
        //填充随机数
        while (result.length()<8){
            String temp=new String(String.valueOf(random.nextInt(9)+1));
            result.append(temp);
        }
        //长度
        result.append(id.length());

        return Integer.parseInt(result.toString());
    }

    /**
     * 前端转后端
     * @param OldId
     * @return
     */
    public static int FrontToBack(int OldId) {
        String id=String.valueOf(OldId);
        //取长度
        int length=Integer.parseInt(id.substring(8,9));
        //取乘数因子
        int factor=Integer.parseInt(id.substring(0,1));
        //取id
        int Id=Integer.parseInt(id.substring(1,1+length));
        return Id/factor;
    }
}
