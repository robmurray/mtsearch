package com.mt.search.controller;

import com.mt.search.dto.HealthStatusResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Simple health checks such DB, JMS server connecttivity. Normally used by
 * network monitoring tools such as Nagios
 *
 * Created by robertmurray on 9/27/17.
 */
@Controller
@RequestMapping("/health")
public class HealthCheckRESTController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    HealthStatusResponse isHealthy() {
        return new HealthStatusResponse(true);
    }

}
