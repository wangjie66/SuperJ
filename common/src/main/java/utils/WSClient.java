package utils;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * WebService 统一客户端
 * Created by Administrator on 2017/10/27.
 */
public class WSClient {


    /**
     * WSDL文档地址
     */
    public String wsdl;

    /**
     * 命名空间
     */
    public String namespace;

    public WSClient(String wsdl, String namespace) {
        this.wsdl = wsdl;
        this.namespace = namespace;
    }


    /**
     * 通用客户端调用
     *
     * @param method WebService方法
     * @param args   参数
     * @return
     */
    public String axis(String method, String[] args) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(wsdl));
            QName qName = new QName(namespace, method);
            call.setOperationName(qName);
            setParameter(call, args);
            call.setReturnType(XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(namespace + method);
            String result = (String) call.invoke(args);
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 指定参数名调用
     *
     * @param method
     * @param params
     * @return
     */
    public String axis(String method, Map<String, String> params) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(wsdl));
            QName qName = new QName(namespace, method);
            call.setOperationName(qName);
            setParameter(call,params);
            call.setReturnType(XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(namespace + method);
            String result = (String) call.invoke(params.values().toArray());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 按顺序设置参数
     *
     * @param call
     * @param args
     */
    private void setParameter(Call call, String[] args) {
        for (int i = 0; i < args.length; i++) {
            call.addParameter("arg" + i, XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        }
    }

    /**
     * 按参数名设置参数
     */
    private void setParameter(Call call, Map<String, String> params) {
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            call.addParameter(new QName(namespace, (String)entry.getKey()),
                    XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
        }
    }

}
