package com.example.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author cbjun
 * @create 2020/5/28 16:14
 */
public class Base64Utils {

    /**
     * 加密base64字符串
     * @param base64Str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeBase64(String base64Str) throws UnsupportedEncodingException {
        try{ base64Str = XMLFormat.format(base64Str); } catch (Exception e){}

        String str = "";
        if(StringUtils.isNotBlank(base64Str)){
            BASE64Encoder base64Encoder = new BASE64Encoder();
            str = base64Encoder.encode(base64Str.getBytes("utf-8")).replaceAll("\\s|\r|\n","");

        } else {
        }

        return str;
    }
    public static String decodeBase64(String base64Str) throws IOException {

        String str = "";
        if(isBase64(base64Str)){        //是base64字符串，走正常解密流程
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] b = base64Decoder.decodeBuffer(base64Str.replaceAll("\\s|\r|\n",""));
            str = new String(b, "utf-8");

            try{ str = XMLFormat.format(str); } catch (Exception e){}
            str=str.replaceAll("\\[","【").replaceAll("\\]","】")
                    .replaceAll(":", "：")
                    .replaceAll("\\(","（").replaceAll("\\)", "）")
                    .replaceAll("\\{","【").replaceAll("\\}", "】")
                    .replaceAll("\"","'")
                    .replaceAll("&","#");
        } else {        //不是base64字符串 打印错误日志
        }

        return str;
    }

    //是否为base64字符串
    private static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }

    /**
     * 转换成map=head+body(listMap)
     * @param str 要解析的报文
     * @param startPoint head和body切割位置
     * @param len listMap中每个map的长度
     * @return head、body
     */
    public static Map format2detailMap(String str, int startPoint, int len){
        Map oMap = new HashMap();
        Matcher matcher = Pattern.compile("\\@").matcher(str.toString());
        int mIdx = 0;
        while(matcher.find()) {
            mIdx++;
            //当"^"符号第N次出现的位置
            if(mIdx == startPoint){
                break;
            }
        }
        int p = matcher.start();
        String head = str.substring(0, p);
        String[] heads = head.split("\\@");
        Map headMap = new HashMap();
        for (String h : heads) {
            String[] s = h.split("\\>");
            if(s.length == 1)
                headMap.put(s[0], "");
            else
                headMap.put(s[0], s[1]);
        }
        oMap.put("detailhead", headMap);
        String body = str.substring(p+1);
//	    int row = Dbtool.readDbInt(headMap.get("Ret_Row"));
        String[] bodys = body.split("\\@");
        List<Map> list = new ArrayList<Map>();
        Map map = new HashMap();
        for (int i=0;i<bodys.length;i++) {
//	    	if(){
//	    		continue;
//	    	}
            if((i-len)%len == 0 && i !=0){
                list.add(map);
                map = new HashMap();
            }
            String[] s = bodys[i].split("\\>");
            if(s.length == 1)
                map.put(s[0].substring(0, s[0].indexOf("[")), "");
            else if(s[0].indexOf("[") != -1)
                map.put(s[0].substring(0, s[0].indexOf("[")), s[1]);

            if(i == bodys.length-1)
                list.add(map);
        }
        oMap.put("detailbody", list);
        System.out.println("format2detailMap 处理后的数据（head+body）："+oMap.toString());
//		System.out.println("head="+oMap.get("head"));
//		System.out.println("body="+oMap.get("body"));
        return oMap;
    }

    /**
     * 转换成map=head+body(listMap)
     * @param str 要解析的报文
     * @param startPoint head和body切割位置
     * @param len listMap中每个map的长度
     * @return head、body
     */
    public static Map format2listMap(String str,int startPoint,int len){
        Map oMap = new HashMap();
        Matcher matcher = Pattern.compile("\\^").matcher(str.toString());
        int mIdx = 0;
        while(matcher.find()) {
            mIdx++;
            //当"^"符号第N次出现的位置
            if(mIdx == startPoint){
                break;
            }
        }
        int p = matcher.start();
        String head = str.substring(0, p);
        String[] heads = head.split("\\^");
        Map headMap = new HashMap();
        for (String h : heads) {
            String[] s = h.split("\\=");
            if(s.length == 1)
                headMap.put(s[0], "");
            else
                headMap.put(s[0], s[1]);
        }
        oMap.put("head", headMap);
        String body = str.substring(p+1);
//	    int row = Dbtool.readDbInt(headMap.get("Ret_Row"));
        String[] bodys = body.split("\\^");
        List<Map> list = new ArrayList<Map>();
        if("".equals(body)&&"0".equals(headMap.get("Ret_Row"))){
            oMap.put("body", list);
            System.out.println("format2listMap 处理后的数据（head+body）："+oMap.toString());
            return oMap;
        }
        Map map = new HashMap();
        for (int i=0;i<bodys.length;i++) {
            if((i-len)%len == 0 && i !=0){
                list.add(map);
                map = new HashMap();
            }
            String[] s = bodys[i].split("\\=");
            if(s.length == 1)
                map.put(s[0].substring(0, s[0].indexOf("[")), "");
            else
                map.put(s[0].substring(0, s[0].indexOf("[")), s[1]);

            if(i == bodys.length-1)
                list.add(map);
        }
        oMap.put("body", list);
        System.out.println("format2listMap 处理后的数据（head+body）："+oMap.toString());
//		System.out.println("head="+oMap.get("head"));
//		System.out.println("body="+oMap.get("body"));
        return oMap;
    }


    public static void main(String[] args) throws IOException {
        StringBuffer detail = new StringBuffer();
        detail.append("Ret_Code=0^Ret_Info=成功^Ret_Row=27^GHKSDM[1]=008^GHKSMC[1]=普内科^GHKSLB[1]=1^ZTDM[1]=^ZTMC[1]=^ZGGH[1]=0^ZGXM[1]=^GHF[1]=0.00^ZLF[1]=16.00^GHSL[1]=200^YYGHSL[1]=0^KZDYS[1]=N^GHBZ[1]=Y^PBKSSJ[1]=06:00:00^PBJSSJ[1]=11:30:59^SJLX[1]=A^PHOTONAME[1]=^ZFFS[1]=U^KSFL[1]= ^KSFLMC[1]=^ZCMC[1]=^GHKSDM[2]=012^GHKSMC[2]=消化内科^GHKSLB[2]=11^ZTDM[2]=^ZTMC[2]=^ZGGH[2]=0^ZGXM[2]=^GHF[2]=0.00^ZLF[2]=16.00^GHSL[2]=50^YYGHSL[2]=0^KZDYS[2]=N^GHBZ[2]=Y^PBKSSJ[2]=06:00:00^PBJSSJ[2]=11:30:59^SJLX[2]=A^PHOTONAME[2]=^ZFFS[2]=U^KSFL[2]= ^KSFLMC[2]=^ZCMC[2]=^GHKSDM[3]=020^GHKSMC[3]=普外科^GHKSLB[3]=1^ZTDM[3]=^ZTMC[3]=^ZGGH[3]=0^ZGXM[3]=^GHF[3]=0.00^ZLF[3]=16.00^GHSL[3]=50^YYGHSL[3]=0^KZDYS[3]=N^GHBZ[3]=Y^PBKSSJ[3]=06:00:00^PBJSSJ[3]=11:30:59^SJLX[3]=A^PHOTONAME[3]=^ZFFS[3]=U^KSFL[3]= ^KSFLMC[3]=^ZCMC[3]=^GHKSDM[4]=022^GHKSMC[4]=骨科^GHKSLB[4]=11^ZTDM[4]=^ZTMC[4]=^ZGGH[4]=0^ZGXM[4]=^GHF[4]=0.00^ZLF[4]=16.00^GHSL[4]=40^YYGHSL[4]=0^KZDYS[4]=N^GHBZ[4]=Y^PBKSSJ[4]=06:00:00^PBJSSJ[4]=11:30:59^SJLX[4]=A^PHOTONAME[4]=^ZFFS[4]=U^KSFL[4]= ^KSFLMC[4]=^ZCMC[4]=^GHKSDM[5]=031^GHKSMC[5]=妇科门诊^GHKSLB[5]=11^ZTDM[5]=^ZTMC[5]=^ZGGH[5]=0^ZGXM[5]=^GHF[5]=0.00^ZLF[5]=16.00^GHSL[5]=100^YYGHSL[5]=0^KZDYS[5]=N^GHBZ[5]=Y^PBKSSJ[5]=06:00:00^PBJSSJ[5]=11:30:59^SJLX[5]=A^PHOTONAME[5]=^ZFFS[5]=U^KSFL[5]= ^KSFLMC[5]=^ZCMC[5]=^GHKSDM[6]=032^GHKSMC[6]=产科门诊^GHKSLB[6]=11^ZTDM[6]=^ZTMC[6]=^ZGGH[6]=0^ZGXM[6]=^GHF[6]=0.00^ZLF[6]=16.00^GHSL[6]=50^YYGHSL[6]=0^KZDYS[6]=N^GHBZ[6]=Y^PBKSSJ[6]=06:00:00^PBJSSJ[6]=11:30:59^SJLX[6]=A^PHOTONAME[6]=^ZFFS[6]=U^KSFL[6]= ^KSFLMC[6]=^ZCMC[6]=^GHKSDM[7]=034^GHKSMC[7]=儿科门诊^GHKSLB[7]=7^ZTDM[7]=^ZTMC[7]=^ZGGH[7]=0^ZGXM[7]=^GHF[7]=0.00^ZLF[7]=17.00^GHSL[7]=200^YYGHSL[7]=0^KZDYS[7]=N^GHBZ[7]=Y^PBKSSJ[7]=06:00:00^PBJSSJ[7]=11:30:59^SJLX[7]=A^PHOTONAME[7]=^ZFFS[7]=U^KSFL[7]= ^KSFLMC[7]=^ZCMC[7]=^GHKSDM[8]=051^GHKSMC[8]=眼科^GHKSLB[8]=11^ZTDM[8]=^ZTMC[8]=^ZGGH[8]=0^ZGXM[8]=^GHF[8]=0.00^ZLF[8]=16.00^GHSL[8]=100^YYGHSL[8]=0^KZDYS[8]=N^GHBZ[8]=Y^PBKSSJ[8]=06:00:00^PBJSSJ[8]=11:30:59^SJLX[8]=A^PHOTONAME[8]=^ZFFS[8]=U^KSFL[8]= ^KSFLMC[8]=^ZCMC[8]=^GHKSDM[9]=053^GHKSMC[9]=针灸理疗科^GHKSLB[9]=11^ZTDM[9]=^ZTMC[9]=^ZGGH[9]=0^ZGXM[9]=^GHF[9]=0.00^ZLF[9]=16.00^GHSL[9]=100^YYGHSL[9]=0^KZDYS[9]=N^GHBZ[9]=Y^PBKSSJ[9]=06:00:00^PBJSSJ[9]=11:30:59^SJLX[9]=A^PHOTONAME[9]=^ZFFS[9]=G^KSFL[9]= ^KSFLMC[9]=^ZCMC[9]=^GHKSDM[10]=060^GHKSMC[10]=皮肤科^GHKSLB[10]=11^ZTDM[10]=^ZTMC[10]=^ZGGH[10]=0^ZGXM[10]=^GHF[10]=0.00^ZLF[10]=16.00^GHSL[10]=150^YYGHSL[10]=0^KZDYS[10]=N^GHBZ[10]=Y^PBKSSJ[10]=06:00:00^PBJSSJ[10]=11:30:59^SJLX[10]=A^PHOTONAME[10]=^ZFFS[10]=U^KSFL[10]= ^KSFLMC[10]=^ZCMC[10]=^GHKSDM[11]=062^GHKSMC[11]=中医科^GHKSLB[11]=11^ZTDM[11]=^ZTMC[11]=^ZGGH[11]=0^ZGXM[11]=^GHF[11]=0.00^ZLF[11]=16.00^GHSL[11]=50^YYGHSL[11]=0^KZDYS[11]=N^GHBZ[11]=Y^PBKSSJ[11]=06:00:00^PBJSSJ[11]=11:30:59^SJLX[11]=A^PHOTONAME[11]=^ZFFS[11]=U^KSFL[11]= ^KSFLMC[11]=^ZCMC[11]=^GHKSDM[12]=130^GHKSMC[12]=急诊外科^GHKSLB[12]=2^ZTDM[12]=^ZTMC[12]=^ZGGH[12]=0^ZGXM[12]=^GHF[12]=0.00^ZLF[12]=19.00^GHSL[12]=200^YYGHSL[12]=0^KZDYS[12]=N^GHBZ[12]=Y^PBKSSJ[12]=00:00:00^PBJSSJ[12]=23:59:59^SJLX[12]=F^PHOTONAME[12]=^ZFFS[12]=U^KSFL[12]= ^KSFLMC[12]=^ZCMC[12]=^GHKSDM[13]=131^GHKSMC[13]=急诊儿科^GHKSLB[13]=8^ZTDM[13]=^ZTMC[13]=^ZGGH[13]=0^ZGXM[13]=^GHF[13]=0.00^ZLF[13]=22.00^GHSL[13]=200^YYGHSL[13]=0^KZDYS[13]=N^GHBZ[13]=Y^PBKSSJ[13]=00:00:00^PBJSSJ[13]=23:59:59^SJLX[13]=F^PHOTONAME[13]=^ZFFS[13]=G^KSFL[13]= ^KSFLMC[13]=^ZCMC[13]=^GHKSDM[14]=133^GHKSMC[14]=犬伤门诊^GHKSLB[14]=2^ZTDM[14]=^ZTMC[14]=^ZGGH[14]=0^ZGXM[14]=^GHF[14]=0.00^ZLF[14]=16.00^GHSL[14]=50^YYGHSL[14]=0^KZDYS[14]=N^GHBZ[14]=Y^PBKSSJ[14]=00:00:00^PBJSSJ[14]=23:59:59^SJLX[14]=F^PHOTONAME[14]=^ZFFS[14]=G^KSFL[14]= ^KSFLMC[14]=^ZCMC[14]=^GHKSDM[15]=146^GHKSMC[15]=普外科专副^GHKSLB[15]=3^ZTDM[15]=^ZTMC[15]=^ZGGH[15]=908^ZGXM[15]=曹清^GHF[15]=0.00^ZLF[15]=20.00^GHSL[15]=50^YYGHSL[15]=0^KZDYS[15]=Y^GHBZ[15]=Y^PBKSSJ[15]=06:00:00^PBJSSJ[15]=11:30:59^SJLX[15]=A^PHOTONAME[15]=^ZFFS[15]=G^KSFL[15]= ^KSFLMC[15]=^ZCMC[15]=副主任医师^GHKSDM[16]=149^GHKSMC[16]=肛肠科专正^GHKSLB[16]=4^ZTDM[16]=^ZTMC[16]=^ZGGH[16]=211^ZGXM[16]=李蕾蕾^GHF[16]=0.00^ZLF[16]=22.00^GHSL[16]=50^YYGHSL[16]=0^KZDYS[16]=Y^GHBZ[16]=Y^PBKSSJ[16]=06:00:00^PBJSSJ[16]=11:30:59^SJLX[16]=A^PHOTONAME[16]=^ZFFS[16]=G^KSFL[16]= ^KSFLMC[16]=^ZCMC[16]=主任医师^GHKSDM[17]=154^GHKSMC[17]=中医科专副^GHKSLB[17]=3^ZTDM[17]=^ZTMC[17]=^ZGGH[17]=171^ZGXM[17]=唐方红^GHF[17]=0.00^ZLF[17]=20.00^GHSL[17]=60^YYGHSL[17]=0^KZDYS[17]=Y^GHBZ[17]=Y^PBKSSJ[17]=06:00:00^PBJSSJ[17]=11:30:59^SJLX[17]=A^PHOTONAME[17]=^ZFFS[17]=G^KSFL[17]= ^KSFLMC[17]=^ZCMC[17]=副主任医师^GHKSDM[18]=160^GHKSMC[18]=骨科专副^GHKSLB[18]=3^ZTDM[18]=^ZTMC[18]=^ZGGH[18]=916^ZGXM[18]=贾学文^GHF[18]=0.00^ZLF[18]=20.00^GHSL[18]=50^YYGHSL[18]=0^KZDYS[18]=Y^GHBZ[18]=Y^PBKSSJ[18]=06:00:00^PBJSSJ[18]=11:30:59^SJLX[18]=A^PHOTONAME[18]=^ZFFS[18]=G^KSFL[18]= ^KSFLMC[18]=^ZCMC[18]=副主任医师^GHKSDM[19]=181^GHKSMC[19]=妇产科专副^GHKSLB[19]=3^ZTDM[19]=^ZTMC[19]=^ZGGH[19]=138^ZGXM[19]=刘长青^GHF[19]=0.00^ZLF[19]=20.00^GHSL[19]=50^YYGHSL[19]=0^KZDYS[19]=Y^GHBZ[19]=Y^PBKSSJ[19]=06:00:00^PBJSSJ[19]=11:30:59^SJLX[19]=A^PHOTONAME[19]=^ZFFS[19]=G^KSFL[19]= ^KSFLMC[19]=^ZCMC[19]=副主任医师^GHKSDM[20]=187^GHKSMC[20]=泌尿外科专副^GHKSLB[20]=3^ZTDM[20]=^ZTMC[20]=^ZGGH[20]=1007^ZGXM[20]=谢世英^GHF[20]=0.00^ZLF[20]=20.00^GHSL[20]=50^YYGHSL[20]=0^KZDYS[20]=Y^GHBZ[20]=Y^PBKSSJ[20]=06:00:00^PBJSSJ[20]=11:30:59^SJLX[20]=A^PHOTONAME[20]=^ZFFS[20]=G^KSFL[20]= ^KSFLMC[20]=^ZCMC[20]=副主任医师^GHKSDM[21]=188^GHKSMC[21]=妇产科专正^GHKSLB[21]=4^ZTDM[21]=^ZTMC[21]=^ZGGH[21]=23^ZGXM[21]=张裕芬^GHF[21]=0.00^ZLF[21]=22.00^GHSL[21]=50^YYGHSL[21]=0^KZDYS[21]=Y^GHBZ[21]=Y^PBKSSJ[21]=06:00:00^PBJSSJ[21]=11:30:59^SJLX[21]=A^PHOTONAME[21]=^ZFFS[21]=G^KSFL[21]= ^KSFLMC[21]=^ZCMC[21]=主任医师^GHKSDM[22]=190^GHKSMC[22]=耳鼻咽喉专副^GHKSLB[22]=3^ZTDM[22]=^ZTMC[22]=^ZGGH[22]=240^ZGXM[22]=杨青松^GHF[22]=0.00^ZLF[22]=20.00^GHSL[22]=50^YYGHSL[22]=0^KZDYS[22]=Y^GHBZ[22]=Y^PBKSSJ[22]=06:00:00^PBJSSJ[22]=11:30:59^SJLX[22]=A^PHOTONAME[22]=^ZFFS[22]=G^KSFL[22]= ^KSFLMC[22]=^ZCMC[22]=副主任医师^GHKSDM[23]=194^GHKSMC[23]=中医儿科^GHKSLB[23]=11^ZTDM[23]=^ZTMC[23]=^ZGGH[23]=177^ZGXM[23]=叶思柳^GHF[23]=0.00^ZLF[23]=16.00^GHSL[23]=60^YYGHSL[23]=0^KZDYS[23]=Y^GHBZ[23]=Y^PBKSSJ[23]=06:00:00^PBJSSJ[23]=11:30:59^SJLX[23]=A^PHOTONAME[23]=^ZFFS[23]=G^KSFL[23]= ^KSFLMC[23]=^ZCMC[23]=医师^GHKSDM[24]=197^GHKSMC[24]=急诊内科^GHKSLB[24]=2^ZTDM[24]=^ZTMC[24]=^ZGGH[24]=0^ZGXM[24]=^GHF[24]=0.00^ZLF[24]=19.00^GHSL[24]=300^YYGHSL[24]=0^KZDYS[24]=N^GHBZ[24]=Y^PBKSSJ[24]=00:00:00^PBJSSJ[24]=23:59:59^SJLX[24]=F^PHOTONAME[24]=^ZFFS[24]=G^KSFL[24]= ^KSFLMC[24]=^ZCMC[24]=^GHKSDM[25]=202^GHKSMC[25]=康复医学科^GHKSLB[25]=11^ZTDM[25]=^ZTMC[25]=^ZGGH[25]=0^ZGXM[25]=^GHF[25]=0.00^ZLF[25]=16.00^GHSL[25]=50^YYGHSL[25]=0^KZDYS[25]=N^GHBZ[25]=Y^PBKSSJ[25]=06:00:00^PBJSSJ[25]=11:30:59^SJLX[25]=A^PHOTONAME[25]=^ZFFS[25]=U^KSFL[25]= ^KSFLMC[25]=^ZCMC[25]=^GHKSDM[26]=223^GHKSMC[26]=甲乳病专副^GHKSLB[26]=3^ZTDM[26]=^ZTMC[26]=^ZGGH[26]=908^ZGXM[26]=曹清^GHF[26]=0.00^ZLF[26]=20.00^GHSL[26]=50^YYGHSL[26]=0^KZDYS[26]=Y^GHBZ[26]=Y^PBKSSJ[26]=06:00:00^PBJSSJ[26]=11:30:59^SJLX[26]=A^PHOTONAME[26]=^ZFFS[26]=G^KSFL[26]= ^KSFLMC[26]=^ZCMC[26]=副主任医师^GHKSDM[27]=246^GHKSMC[27]=五官核酸采样^GHKSLB[27]=11^ZTDM[27]=^ZTMC[27]=^ZGGH[27]=0^ZGXM[27]=^GHF[27]=0.00^ZLF[27]=16.00^GHSL[27]=40^YYGHSL[27]=0^KZDYS[27]=N^GHBZ[27]=Y^PBKSSJ[27]=06:00:00^PBJSSJ[27]=11:30:59^SJLX[27]=A^PHOTONAME[27]=^ZFFS[27]=G^KSFL[27]= ^KSFLMC[27]=^ZCMC[27]=^");
        Map result = format2listMap(detail.toString(),3,21);
        System.out.println(result);

    }


}
