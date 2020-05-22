package com.test.demo.controller;

import com.test.demo.database.kubeDatabase;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;

import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.test.demo.client.kubeClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class kubeController {

    private static final Logger logger = LoggerFactory.getLogger(kubeController.class);

    //k8s连接初始化
    @RequestMapping("/init")
    public String kubeInit() throws IOException
    {
        logger.info("k8s连接初始化");
        try{
            //直接写config path

            String kubeConfigPath = this.getClass().getClassLoader().getResource("static/config/config").getPath();

            //String kubeConfigPath = this.getClass().getClassLoader().getResource("config/config").getPath();
            System.out.println(kubeConfigPath);
            //加载k8s, config
            ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
            //将加载config的client设置为默认的client
            Configuration.setDefaultApiClient(client);
        }catch (IOException ie){
            ie.printStackTrace();
            return "init fail!";
        }

        return "init sucess!";
    }
    //取pods
    @RequestMapping("/kubelistPodForAllNamespaces")
    public ArrayList<String> kubelistPodForAllNamespaces() throws  ApiException {
        kubeClient kc = new kubeClient();
        ArrayList<String> returnData = kc.kubelistPodForAllNamespaces();
        return returnData;
    }
    //取namespaces
    @RequestMapping("/kubelistNamespace")
    public ArrayList<String> kubekubelistNamespace() throws  ApiException {
        kubeClient kc = new kubeClient();
        ArrayList<String> returnData = kc.kubelistNamespace();
        return returnData;
    }
    //取nodes
    @RequestMapping("/kubelistNode")
    public ArrayList<String> kubelistNode() throws  ApiException {
        kubeClient kc = new kubeClient();
        ArrayList<String> returnData = kc.kubelistNode();
        return returnData;
    }
    //创建namespace
    @RequestMapping("/kubeCreateNamespace")
    public ArrayList<String> kubeCreateNamespace(HttpServletRequest request,HttpServletResponse response) throws  ApiException {

        kubeClient kc = new kubeClient();
        String str_namespace = request.getParameter("namespace");
        System.out.println("创建namespace请求："+str_namespace);
        ArrayList<String> returnData = kc.kubeCreateNamespace(str_namespace);
        return returnData;
    }
    //创建namespace
    @RequestMapping("/kubecreatePod")
    public ArrayList<String> kubecreatePod(HttpServletRequest request,HttpServletResponse response) throws  ApiException {

        kubeClient kc = new kubeClient();
        String podName = request.getParameter("podName");
        String podReplicas = request.getParameter("podReplicas");
        String podNamespace = request.getParameter("podNamespace");
        String podVolume = request.getParameter("podVolume");
        System.out.println("创建pod请求："+podName);
        ArrayList<String> returnData = kc.kubeCreatePod(podName,podReplicas,podNamespace,podVolume);
        return returnData;
    }
    @RequestMapping("/queryLoginInfo")
    public boolean queryLoginInfo(HttpServletRequest request,HttpServletResponse response){

        kubeDatabase kd = new kubeDatabase();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("输入账号为:"+username);
        System.out.println("输入密码为:"+password);
        boolean result = kd.queryLoginInfo(username,password);
        return result;

    }



    public static void main(String [] args) throws  IOException, ApiException {
        kubeController tk = new kubeController();
        tk.kubeInit();
        kubeClient kc = new kubeClient();
        //String returnData = kc.kubeCreateNamespace("ddddd");
        //System.out.println(returnData);

    }
}
