package com.neliolucas.zeroip.services;

import com.google.gson.Gson;
import com.neliolucas.zeroip.models.PublicIp;
import com.neliolucas.zeroip.repositories.PublicIpRepository;
import com.neliolucas.zeroip.utils.Utils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Nelio
 * @date 24/03/2021
 */

@Service
@Transactional
public class IpService {

    @Autowired
    RestTemplate template;

    @Autowired
    PublicIpRepository publicIpRepository;

    @Value("${run.mode}")
    private String mode;

    @Scheduled(fixedRate = 10000)
    public void fetchAndUpdateServerPublicIp()
    {
        if (mode.equals("updater")) {
            try {


                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                String url = "https://api.ipify.org/?format=json";

                HttpEntity<String> getEntity = new HttpEntity<String>(headers);
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, getEntity, String.class);

                PublicIp publicIp = new Gson().fromJson(response.getBody(), PublicIp.class);


                PublicIp ip = publicIpRepository.findAll().stream().findAny().orElse(null);
                if (ip == null) {
                    publicIp.setLastUpdated(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                    publicIpRepository.save(publicIp);
                    Utils.logInfo(this, "No Ip exists , new one created. Current IP is => " + publicIp.getIp());
                } else {
                    ip.setIp(publicIp.getIp());
                    ip.setLastUpdated(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                    publicIpRepository.save(ip);
                    Utils.logInfo(this, "Updating existing IP to => " + publicIp.getIp());


                }
            } catch (Exception e) {
                Utils.logError(this, "Something went wrong unable to update IP");
                Utils.logError(this, e.getLocalizedMessage());
            }
        }
        else
        {
            Utils.logInfo(this,"This application is set to run in " + mode +  " mode");
        }
    }

    public PublicIp latestIp()
    {
       return publicIpRepository.findAll().stream().findAny().orElse(null);
    }

}

