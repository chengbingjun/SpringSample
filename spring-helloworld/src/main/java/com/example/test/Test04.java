package com.example.test;

import com.alibaba.fastjson.JSON;
import com.example.utils.AnnotationTool;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cbjun
 * @create 2020/7/8 10:03
 */
public class Test04 {

    public static void main(String[] args) {
//        String s = "cc";
//        System.out.println(hehe(s));
        String userInfo = "{logoutBtn=false, patient_class=, multi_org=false, longcardno=330100D156000005011BA293AA4B9E55, returnBtn=false, med_id=, isLogin=true, balance=0.0, card_no=W49158758, wxQrCode={result_code=FAIL}, addr=, homeBtn=true, infoCount=2.0, medcard_info=<![CDATA[330100D156000005011BA293AA4B9E55|3|W49158758|330621199601212992|王泽华                        |1|||||330100800DAA|]]>, inPatientInfo={}, citizenCardInfo={cardno20=, is_new=, hairpin_date=, sex=, birth=, telephone=, cardno9=, period_validity=, balance=—, carddetail=, cardflag=, xph_=, wdflag=, name=, carddetail2=, cardno32=, idcard_no=, ifchild=}, sex=女, his_patient_info=, cur_menuname=门诊缴费, card_type=2, logBtn=false, phone=, patient_nature=, name=, ID_type=}";
        Map map=new HashMap();
        map.put("page_size","10");
        map.put("page_index","1");
        String  param= JSON.toJSONString(map);
        System.out.println(param);

    }

    public static boolean hehe(String s) {
        switch (s) {
            case "aa":
            case "bb":
                System.out.println("aa or bb");
                return true;
            case "cc":
                System.out.println("cc");
                return true;
            default:
                return false;
        }
    }
    @Test()
    @PostMapping("/hehehehe")
    public void test(){
        PostMapping result = AnnotationTool.getAnnotation(Test04.class,PostMapping.class,"test");
        String[] strs = result.value();
        List<String> list = Arrays.asList(strs);
        list.forEach(e->{
            System.out.println(e);
        });
    }
}
