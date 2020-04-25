package com.test.demo.controller;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class test_k8s {

    @RequestMapping("/k")
    public ArrayList<String> k8s() throws ApiException, IOException, ApiException {
        //直接写config path
        ArrayList<String> data = new ArrayList<String>();
        String kubeConfigPath = "C:\\Users\\yanxiao\\Desktop\\demo\\config";

        //加载k8s, config
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        //将加载config的client设置为默认的client
        Configuration.setDefaultApiClient(client);

        //创建一个api
        CoreV1Api api = new CoreV1Api();
        //打印所有的pod
        //V1PodList list = api.listPodForAllNamespaces(null,null,null,null,null,null,null,null,null);
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

//        for (V1Pod item : list.getItems()) {
//
//            data.add(item.toString());
//            System.out.println(item);
//        }
        for (V1Pod item : list.getItems()) {
            data.add(item.getMetadata().getName());
            System.out.println(item.getMetadata().getName());
        }
        return data;

    }

    public static void main(String [] args) throws ApiException, IOException, ApiException {
        test_k8s tk = new test_k8s();
        tk.k8s();


    }
}
