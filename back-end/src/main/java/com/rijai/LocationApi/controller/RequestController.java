package com.rijai.LocationApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rijai.LocationApi.model.Account;
import com.rijai.LocationApi.model.Dog;
import com.rijai.LocationApi.model.Request;
import com.rijai.LocationApi.service.IAccountService;
import com.rijai.LocationApi.service.IDogService;
import com.rijai.LocationApi.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class RequestController {
    @Autowired
    private IRequestService requestService;
    @Autowired
    private IDogService dogService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping("/api/requests")
    public List<Request> findRequests(){
        return requestService.getRequests();
    }

    @RequestMapping(value="/api/create-request", method= RequestMethod.POST)
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @RequestMapping(value="/api/update-request/{id}", method=RequestMethod.PUT)
    public Request updateRequest(@PathVariable Long id, @RequestBody Request request) {
        return requestService.updateRequest(id, request);
    }
    
    @GetMapping(value = "/api/show-request/{id}")
    public Request showRequest(@PathVariable Long id) {
        return requestService.getRequest(id);
    }

    @RequestMapping(value = "/api/delete-request/{id}",  method = RequestMethod.DELETE)
    public void deleteRequest(@PathVariable("id") Long id) {
        requestService.deleteRequest(id);
    }
    
}
