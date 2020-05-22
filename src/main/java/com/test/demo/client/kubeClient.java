package com.test.demo.client;

import io.kubernetes.client.Copy;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class kubeClient {
    private static final Logger logger = LoggerFactory.getLogger(kubeClient.class);


    //获取k8s所有pod
    public ArrayList<String> kubelistPodForAllNamespaces() throws  ApiException
    {
        logger.info("test");
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
    //获取所有Node
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
    //创建一个Namespace
    public ArrayList<String> kubeCreateNamespace(String str_namespace) throws  ApiException
    {
        ArrayList<String> data = new ArrayList<String>();
        V1Namespace body = new V1NamespaceBuilder()
                .withMetadata((new V1ObjectMetaBuilder()
                        .withName(str_namespace)
                        .build()))
                .build();

        String pretty = "pretty_example";
        String dryRun = null;
        String fieldManager = "fieldManager_example";
        //创建一个api
        CoreV1Api api = new CoreV1Api();

        try {
            body = api.createNamespace(body, pretty, dryRun, fieldManager);
            System.out.println(body);
        } catch (ApiException e) {
            System.err.println("Exception when calling CoreV1Api#createNamespace");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
        data.add(body.toString());
        System.out.println(body.toString());
        return data;
    }
    //创建一个Pod
    public ArrayList<String> kubeCreatePod(String podName,String podReplicas,String podNamespace,String podVolume) throws  ApiException
    {
        ArrayList<String> data = new ArrayList<String>();
        V1Deployment vp = new V1DeploymentBuilder()
                .withMetadata(new V1ObjectMetaBuilder()
                        .withName(podName)
                        .withNamespace(podNamespace).build())
                .withSpec(new V1DeploymentSpecBuilder()
                        .withReplicas(Integer.parseInt(podReplicas)).build())

                .build();

        String pretty = "pretty_example";
        String dryRun = null;
        String fieldManager = "fieldManager_example";
        //创建一个api
        AppsV1Api api = new AppsV1Api();
        try {
            vp = api.createNamespacedDeployment("test-namespace",vp,pretty,dryRun,fieldManager);
            System.out.println(vp);
        } catch (ApiException e) {
            System.err.println("Exception when calling CoreV1Api#createNamespace");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
        data.add(vp.toString());
        System.out.println(vp.toString());
        return data;
    }

    //获取所有namespace
    public ArrayList<String> kubelistNamespace1() throws  ApiException
    {
        Map<String,String> map = new HashMap<>();
        map.put("1","test");
        map.get("1");

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


}
