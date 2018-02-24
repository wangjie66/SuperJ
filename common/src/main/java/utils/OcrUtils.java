package utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

@Component("ocrUtils")
public class OcrUtils {
    public String url;// = "http://172.17.2.19:801";//ocr服务器地址
    public String type;// = "ocr";//ifocr 印刷体识别  ocr 手写体识别
    public String ocr_type;// = "cn_manuscript";//en_print 印刷体 cn_manuscript 手写体
    /**
     * type_code:1印刷体，2手写体
     */
    public String type_code;
    public String type_sxt;
    public String ocr_type_sxt;
    public String type_yst;
    public String ocr_type_yst;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        if ("1".equals(type_code)) {
            return this.getType_yst();
        } else {
            return this.getType_sxt();
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOcr_type() {
        if ("1".equals(type_code)) {
            return this.getOcr_type_yst();
        } else {
            return this.getOcr_type_sxt();
        }
    }

    public void setOcr_type(String ocr_type) {
        this.ocr_type = ocr_type;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getType_sxt() {
        return type_sxt;
    }

    public void setType_sxt(String type_sxt) {
        this.type_sxt = type_sxt;
    }

    public String getOcr_type_sxt() {
        return ocr_type_sxt;
    }

    public void setOcr_type_sxt(String ocr_type_sxt) {
        this.ocr_type_sxt = ocr_type_sxt;
    }

    public String getType_yst() {
        return type_yst;
    }

    public void setType_yst(String type_yst) {
        this.type_yst = type_yst;
    }

    public String getOcr_type_yst() {
        return ocr_type_yst;
    }

    public void setOcr_type_yst(String ocr_type_yst) {
        this.ocr_type_yst = ocr_type_yst;
    }

    /**
     * 获取服务UID
     *
     * @return
     */
    public String getUid() {
        try {
            String getResult = HttpUtil.sendGet(new StringBuffer(this.getUrl()).append("/Engine/GetAuth").toString(), new StringBuffer("type=").append(this.getType()).toString());
//            JSONObject jsonObject = JSONObject.parseObject(s);
            return JSONObject.parseObject(getResult).getString("uid");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void releaseAuth(String uid) {
        HttpUtil.sendGet(new StringBuffer(this.getUrl()).append("/Engine/ReleaseAuth").toString(),
                new StringBuffer("type=").append(this.getType()).append("&uid=").append(uid).toString());
    }

    public String parseXML(String json) {
        StringBuilder finalResult = new StringBuilder();
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String result = jsonObject.getString("result");
            JSONObject jsonResult = JSONObject.parseObject(result);
            if (this.getType().equals("ocr")) {
                String temp = jsonResult.getString("1");
                JSONObject jsonXml = JSONObject.parseObject(temp);
                String xml = jsonXml.getString("xml");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
                NodeList list = document.getElementsByTagName("sentence");
                for (int i = 0; i < list.getLength(); ++i) {
                    Element element = (Element) list.item(i);
                    finalResult.append(element.getAttribute("content"));
                }
            } else {
                String temp = jsonResult.getString("text");
                finalResult = new StringBuilder(temp.replace("[", "").replace("]", "")
                        .replace("\"", ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return finalResult.toString();
    }

    public String ocrServer(byte[] filebody) {
        JSONObject jsonObject = new JSONObject();
        try {
            String uid = getUid();
            if (null == uid || StringUtils.isEmpty(uid)) {
                jsonObject.put("code", 100);
                jsonObject.put("data", "获取到的UID为NULL");
                return jsonObject.toString();
            } else {
                System.out.println("ocr识别uid=" + uid);
            }
            String serverUrl = this.getUrl() + "/Engine/OCR";
            ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
            ffkvp.add(new FormFieldKeyValuePair("type", this.getType()));
            ffkvp.add(new FormFieldKeyValuePair("uid", uid));
            ffkvp.add(new FormFieldKeyValuePair("picture_config", "{\"file_name\":\"ocr.jpg\",\"image_info\":{\"dpi\": 133,\"postfix\":\".jpg\",\"extend\":\"图片其他信息\",\"area_count\": 1},\"areas\":[{\"area_id\": 1,\"ocr_type\":\"" + this.getOcr_type() + "\",\"area_type\":\"目标区域、排除区域\",\"x\": 0,\"y\": 0,\"w\": 0,\"h\": 0,\"extend\":\"当前类型需要的其他信息\"}]}"));
            // 设定要上传的文件。UploadFileItem见后面的代码
            HttpPostEmulator hpe = new HttpPostEmulator();
            String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, filebody);
            String result = parseXML(response);
            releaseAuth(uid);
            jsonObject.put("code", 200);
            jsonObject.put("data", result);
            return jsonObject.toString();
        } catch (Exception e) {
//            releaseAuth(uid);
            e.printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("data", "调用服务出错");
            return jsonObject.toString();
        }
    }

    public String ocr(String imgPath) {
        String result = "";
        String uid = getUid();
        System.out.println("ocr识别uid" + uid);
        String serverUrl = this.getUrl() + "/Engine/OCR";
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        ffkvp.add(new FormFieldKeyValuePair("type", this.getType()));
        ffkvp.add(new FormFieldKeyValuePair("uid", uid));
        ffkvp.add(new FormFieldKeyValuePair("picture_config", "{\"file_name\":\"ocr.jpg\",\"image_info\":{\"dpi\": 133,\"postfix\":\".jpg\",\"extend\":\"图片其他信息\",\"area_count\": 1},\"areas\":[{\"area_id\": 1,\"ocr_type\":\"" + this.getOcr_type() + "\",\"area_type\":\"目标区域、排除区域\",\"x\": 0,\"y\": 0,\"w\": 0,\"h\": 0,\"extend\":\"当前类型需要的其他信息\"}]}"));
        // 设定要上传的文件。UploadFileItem见后面的代码
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("ocr.jpg", imgPath));
        HttpPostEmulator hpe = new HttpPostEmulator();
        String response;
        try {
            response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi);
            result = parseXML(response);
            System.out.println("ocr识别结果" + result);
        } catch (Exception e) {
            releaseAuth(uid);
            e.printStackTrace();
            return result;
        }
        releaseAuth(uid);
        return result;
    }

}
