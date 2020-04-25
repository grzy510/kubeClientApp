package com.test.demo.controller;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.test.demo.client.kubeClient;

@RestController
public class kubeController {

    //k8s连接初始化
    @RequestMapping("/init")
    public void kubeInit() throws IOException
    {
        //直接写config path
        String kubeConfigPath = "C:\\Users\\yanxiao\\Desktop\\demo\\config";
        //加载k8s, config
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        //将加载config的client设置为默认的client
        Configuration.setDefaultApiClient(client);
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



    public static void main(String [] args) throws ApiException, IOException, ApiException {
        kubeController tk = new kubeController();
        tk.kubeInit();
        tk.kubelistNode();


    }
}
