package com.yxh.conversion;

import com.yxh.conversion.tools.IDEA;
import com.yxh.conversion.tools.MyConstants;

public class TestIDEA {

    public static void main(String[] args) {
        IDEA idea = new IDEA();
        idea.getKey(MyConstants.SYS_IDEA_KEY);
        String strEnc = idea.getEncString("yanxianhe #$%^&^%$#$%^");
        System.out.println(strEnc);

        String strDes = idea.getDesString(strEnc);
        System.out.println(strDes);
    }
    
}
