package com.example.test.task;

import com.example.test.model.Jybg;
import com.example.test.repostory.JybgRepostory;
import com.example.test.service.JybgServicer;
import net.sf.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTaskJob extends QuartzJobBean
{

    @Autowired
    private JybgServicer JybgServicer;

    @Resource
    JybgRepostory JybgRepostory;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("定时任务开启======================"+ simpleDateFormat.format(new Date()));
        this.updateCommentLikeData();
        System.out.println("定时任务结束======================"+ simpleDateFormat.format(new Date()));
    }

    private void updateCommentLikeData(){
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
                String PhpSyncId = (String) clientMsgObject.getJSONObject("item").getJSONObject("data").get("sync_id");
                System.out.println(PhpSyncId);
                System.out.println(syncId);
                if(PhpSyncId == syncId){
                    jybg.setFlag("1");
                    JybgRepostory.save(jybg);
                }else {
                    jybg.setFlag("2");
                    JybgRepostory.save(jybg);
                }
            }else {
                jybg.setFlag("3");
                JybgRepostory.save(jybg);
            }
        }
    }
}
