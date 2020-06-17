package com.example.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 格式化xml字符串
 */
public class XMLFormat {
    static String XML_HEAD ="<?xml version=\"1.0\" encoding=\"gbk\"?>" +
            "<head>"
                    + "<actdate>20190528</actdate>"
                    + "<trdate>20190528</trdate>"
                    + "<trtime>140517</trtime>"
                    + "<trcode>104010Z205</trcode>"
                    + "<hisseq>32121212</hisseq>"
                    + "<bhpseq>32121211</bhpseq>"
                    + "<jdseq>3212121</jdseq>"
                    + "<filenum>0</filenum>"
                    + "<chncode></chncode>"
                    + "<termno>admin</termno>"
                + "</head>";


    public static String format(String content) {
        content.replaceAll("\\\\","");
        String header = getHeader(content);
        content = content.substring(header.length());
        content = content.replace(" ", "");
        return (StringUtils.isEmpty(header) ? "" : header)
                + format(null, content, 0);
    }


    public static String deFormat(String content) {
        String header = getHeader(content);
        content = content.substring(header.length());
        return (StringUtils.isEmpty(header) ? "" : header)
                + content.replaceAll("\\s", "");
    }

    private static String format(String tag, String content, int depth) {
        String format = "";
        String firstTag = "";
        if (StringUtils.isEmpty(tag)) {
            firstTag = getFirstTag(content);
        } else {
            firstTag = tag;
        }

        String inside = getInsideContent(firstTag, content);
        String outside = getOutsideContent(firstTag, content);

        String insideTag = "";
        try {
            insideTag = getFirstTag(inside);
        } catch (Exception e) {
            insideTag = null;
        }
        if (StringUtils.isEmpty(insideTag)) {
            format = "\r\n" + indent(depth) + "<" + firstTag + ">"
                    + inside + "</" + firstTag.split(" ")[0] + ">";
        } else {
            format = "\r\n" + indent(depth) + "<" + firstTag + ">"
                    + format(insideTag, inside, depth + 1)
                    + indent(depth) + "</" + firstTag.split(" ")[0] + ">";
        }

        String outsideTag = "";
        if (StringUtils.isEmpty(outside)) {
            outsideTag = null;
        } else {
            outsideTag = getFirstTag(outside);
        }
        if (!StringUtils.isEmpty(outsideTag)) {
            format += indent(depth) + format(outsideTag, outside, depth);
        } else if (StringUtils.isEmpty(outside)) {
            format += "\r\n";
        } else {
            throw new RuntimeException("xml报文格式不正确");
        }
        return format;
    }

    /**
     * 获取xml头部数据，格式：<? …… ?>
     * @return xml头部数据，null表示不存在
     */
    private static String getHeader(String content) {
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == ' ' || c == '\r' || c == '\n'
                    || c == '\t') {
                continue;
            }

            if (c == '<' && content.charAt(i + 1) == '?') {
                String header = "<?";
                for (i = i + 2; i < content.length(); i++) {
                    char end = content.charAt(i);
                    if (end == '?' && content.charAt(i + 1) == '>') {
                        header += "?>";
                        return header;
                    } else {
                        header += end;
                    }
                }
            }

            return "";
        }

        return "";
    }

    /**
     * 获取xml报文的第一个标签
     * @param content xml报文
     * @return 标签名称
     */
    private static String getFirstTag(String content) {
        StringBuilder tag = new StringBuilder();
        int index = 0;

        for (; index < content.length(); index++) {
            char temp = content.charAt(index);
            if (temp == ' ' || temp == '\r' || temp == '\n'
                    || temp == '\t') { //忽略空格回车字符
                continue;
            }

            if (temp != '<') {
                throw new RuntimeException("xml报文格式不正确");
            }
            break;
        }

        for (int i = index + 1; i < content.length(); i++) {

            char c = content.charAt(i);
            if (c == '>') {
                return tag.toString();
            }
            tag.append(c);
        }
        throw new RuntimeException("xml报文格式不正确");
    }

    private static String getOutsideContent(String tag, String content) {
        String endTag = "</" + tag.split(" ")[0] + ">";
        int endIndex = content.indexOf(endTag) + endTag.length();

        return content.substring(endIndex);
    }

    private static String getInsideContent(String tag, String content) {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag.split(" ")[0] + ">";

        int startIndex = content.indexOf(startTag) + startTag.length();
        int endIndex = content.indexOf(endTag);

        return content.substring(startIndex, endIndex);
    }

    private static String indent(int num) {
        String space = "";
        if (num == 0) {
            return space;
        } else {
            return space + PER_SPACE + indent(num - 1);
        }
    }

    private static final String PER_SPACE = "    "; //缩进字符串

    public static void main(String[] args) {
        String test = "<?xml version=\"1.0\" encoding=\"gbk\"?><root><head><actdate>20190615</actdate><trdate>20190615</trdate><trtime>110740</trtime><trcode>jd2his_insert_patient_si_encode</trcode><hisseq></hisseq><bhpseq></bhpseq><jdseq>jd20190615110615099</jdseq><filenum>0</filenum><succflag>2</succflag><retcode></retcode><retmsg>建档异常失败，原因：Object reference not set to an instance of an object.</retmsg></head><body></body></root>";
        String a = XMLFormat.format(test);
        System.out.println(a);
    }
}
