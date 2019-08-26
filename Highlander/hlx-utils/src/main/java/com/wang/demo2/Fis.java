package com.wang.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

public class Fis {
    private static final Logger logger = LoggerFactory.getLogger(Fis.class);

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        String s = "";
//        URL url = new URL();
        String url = "http://220.174.93.84:7090/api/fis/getInc";
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/json");
        while (true) {

            String params = gson.toJson(new FisParams());
            HttpEntity entity = new StringEntity(params);
            request.setEntity(entity);
            HttpResponse response = client.execute(request);
            InputStream stream = response.getEntity().getContent();
            String result = StreamUtils.copyToString(stream, Charset.forName("utf-8"));
            ObjectMapper objectMapper = new ObjectMapper();
            Resp resp = objectMapper.readValue(result, Resp.class);
            System.out.println(resp.getData().size());
            System.out.println(gson.toJson(resp.getData()));
            Thread.sleep(10*1000);
        }
    }
}

class FisParams {
    private String user_key = "uk";
    private String sigh = "abc";
    private long timestamp;

    FisParams() {
        this.timestamp = new Date().getTime() / 1000;
    }
}

class Resp {
    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Bds> getData() {
        return data;
    }

    public void setData(List<Bds> data) {
        this.data = data;
    }

    private List<Bds> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


