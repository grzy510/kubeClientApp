package com.test.demo.client;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

import java.util.ArrayList;

public class kubeClient {

    //获取k8s所有pod
    public ArrayList<String> kubelistPodForAllNamespaces() throws  ApiException
    {
        ArrayList<String> baseData = new ArrayList<String>();
        ArrayList<String> data = new ArrayList<String>();
        //创建一个api
        CoreV1Api api = new CoreV1Api();
        //打印所有的pod
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            baseData.add(item.getMetadata().getName());
            baseData.add(item.getMetadata().getNamespace());
            baseData.add(item.getStatus().getPhase());
            data.add(baseData.toString());
            baseData.clear();
        }

        return data;
    }
    //获取所有namespace
    public ArrayList<String> kubelistNamespace() throws  ApiException
    {
        ArrayList<String> data = new ArrayList<String>();
        //创建一个api
        CoreV1Api api = new CoreV1Api();
        //打印所有的pod
        V1NamespaceList list = api.listNamespace(null, null, null, null, null, null, null, null, null);
        for (V1Namespace item : list.getItems()) {
            data.add(item.getMetadata().getName());
            System.out.println(item.getMetadata().getName());
        }
        return data;
    }

    public ArrayList<String> kubelistNode() throws  ApiException
    {
        ArrayList<String> baseData = new ArrayList<String>();
        ArrayList<String> data = new ArrayList<String>();
        //创建一个api
        CoreV1Api api = new CoreV1Api();
        //打印所有的pod
        V1NodeList list = api.listNode(null, null, null, null, null, null, null, null, null);
        for (V1Node item : list.getItems()) {
            baseData.add(item.getMetadata().getName());
            //baseData.add(item.getStatus().phase());
            data.add(baseData.toString());
            baseData.clear();
        }
        System.out.println(data);
        return data;
    }
}
