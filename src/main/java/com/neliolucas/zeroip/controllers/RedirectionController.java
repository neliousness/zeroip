package com.neliolucas.zeroip.controllers;

import com.neliolucas.zeroip.services.IpService;
import com.neliolucas.zeroip.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nelio
 * @date 24/03/2021
 */

@Controller
public class RedirectionController {

    @Autowired
    IpService ipService;

    @Value("${run.mode}")
    private String mode;



    @GetMapping({"/{port}","/{port}/{endpoint}","/"})
    public ModelAndView redirection(@PathVariable (value = "endpoint",required = false) String location,
                                    @PathVariable (value = "port",required = false) String port, HttpServletRequest request)
    {
        Utils.logInfo(this,"this + " + request.getRequestURL());
        if (mode.equals("redirection")) {

            String formattedPort = port != null ?  ":" + port : "";
            String formattedLocation = location != null ? "/" + location : "";
            Utils.logInfo(this,"redirect:http://" + ipService.latestIp().getIp().concat(formattedPort)
                    .concat(formattedLocation));
            return new ModelAndView("redirect:http://" + ipService.latestIp().getIp().concat(formattedPort)
                    .concat(formattedLocation));
        }
        else
        {
            return new ModelAndView();
        }
    }


}
