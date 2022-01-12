package com.staling.jwt.demo;

import org.json.JSONObject;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

//import lombok.extern.slf4j.Slf4j;
//

@RestController
@RequestMapping("/api")
@RequestScope
public class SampleApi {

    @GetMapping("/sayHello")
    public JSONObject sayHello() {
       new SampleApi().cbsCustomerLatest();
        //return "Hello";
        return new SampleApi().cbsCustomerLatest();
    }

    @GetMapping("/sayBye")
    public String sayBye() {
        return "Bye";
    }

    public JSONObject cbsCustomerLatest() {
        
        String urn="2201065752846001";
        String accountNumber="202220200454";
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000);
        factory.setReadTimeout(30000);
    	RestTemplate restTemplate = new RestTemplate(factory);
        try {
            String res= restTemplate.getForObject("http://20.44.34.184:7071/bi" + "/etl/cbs_loan_latest.php?accountNumber={accountNumber}", String.class, accountNumber);
            JSONObject json = new JSONObject(res);  
            return json;
        } catch (Exception e) {
            //log.info("Failed to call bi API: " + e.getMessage());
            return null;
        }
    }
}
