package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Request;
import com.rijai.LocationApi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {
    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getRequests() {
        return (List<Request>) requestRepository.findAll();
    }

    @Override
    public Request getRequest(Long id) {
        Optional<Request> optional=requestRepository.findById(id);
        if(optional.isEmpty())
            return null;
        else
            return (Request) optional.get();
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Request updateRequest(Long id, Request request) {
        return requestRepository.save(request);
    }

    public void deleteRequest(Long id){
        Optional<Request> request = requestRepository.findById(id);
        if(request.isPresent()) {
            requestRepository.delete(request.get());
        }
    }
}
