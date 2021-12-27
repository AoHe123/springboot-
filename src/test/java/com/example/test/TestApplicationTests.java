package com.example.test;

import com.example.test.model.Jybg;
import com.example.test.repostory.JybgRepostory;
import com.example.test.service.JybgServicer;


import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        System.out.print(1111);
    }

    @Resource
    JybgRepostory JybgRepostory;
    @Test
    void getFindAll(){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
        Sort sort = Sort.by(order);
        PageRequest of = PageRequest.of(0, 20, sort);
        Page<Jybg> all = JybgRepostory.findAll(of);
        System.out.println("总条数："+all.getTotalElements());
        System.out.println("总页数："+all.getTotalPages());
        System.out.println("当前页："+all.getPageable());
        System.out.println("当前页1："+all.getContent());
        System.out.println("当前页2："+all.getNumber());
        System.out.println("当前页3："+all.getNumberOfElements());

    }


    @Autowired
    private JybgServicer JybgServicer;



    @Test
    void getFlag3() {
        Jybg jybg = new Jybg();
        Page<Jybg> allMobile = JybgServicer.getAllMobile();
        RestTemplate client  = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        MultiValueMap<String, Object> form  = new LinkedMultiValueMap<>();

        for (Jybg srt:allMobile){
            jybg.setId(srt.getId());
            jybg.setDrugID(srt.getDrugID());
            jybg.setPihao(srt.getPihao());
            jybg.setUrl(srt.getUrl());
            jybg.setImage(srt.getImage());
            jybg.setType(srt.getType());
            jybg.setNwedrugId(srt.getNwedrugId());
            if (srt.getUrl() != null && srt.getImage() != null) {
                //设置请求体
                String syncId = String.format("%09d", srt.getId());
                FileSystemResource fileSystemResource = new FileSystemResource(srt.getUrl()+"/"+srt.getImage());
                form.add("filename", fileSystemResource);
                form.add("sync_id",syncId);
                form.add("drug_id",srt.getDrugID());
                form.add("batch_number",srt.getPihao());
                form.add("token",10000019);
                HttpEntity<MultiValueMap<String, Object>> params = new HttpEntity<>(form, headers);
                ResponseEntity<String> entity = client.postForEntity("http://api.51yywd.cn/Image/batch", params, String.class);
                String body = entity.getBody();
                System.out.println(body);
                JSONObject clientMsgObject = JSONObject.fromObject(body);
                System.out.println(clientMsgObject);
                String PhpSyncId = (String) clientMsgObject.getJSONObject("item").getJSONObject("data").get("sync_id");
                System.out.println(PhpSyncId);
                System.out.println(syncId);
                if(PhpSyncId == syncId){
                    jybg.setFlag("1");
                   // JybgRepostory.save(jybg);
                }else {
                    jybg.setFlag("2");
                   // JybgRepostory.save(jybg);
                }
            }else {
                jybg.setFlag("3");
               // JybgRepostory.save(jybg);
            }
        }
    }
}
