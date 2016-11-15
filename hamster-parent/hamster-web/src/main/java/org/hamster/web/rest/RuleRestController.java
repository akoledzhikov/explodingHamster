package org.hamster.web.rest;


import java.util.ArrayList;
import java.util.List;

import org.hamster.model.runtime.RuleInstance;
import org.hamster.service.RuleInstanceServiceImpl;
import org.hamster.web.dto.RuleInstanceDTO;
import org.hamster.web.dto.request.MultiIdBasedRequest;
import org.hamster.web.dto.request.SingleIdBasedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/rules")
public class RuleRestController
{
    @Autowired
    private RuleInstanceServiceImpl ris;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<RuleInstanceDTO>> findAll()
    {
        Iterable<RuleInstance> rules = ris.findAll();
        List<RuleInstanceDTO> result = new ArrayList<>();
        for (RuleInstance ri : rules)
        {
            result.add(new RuleInstanceDTO(ri));
        }

        return new ResponseEntity<Iterable<RuleInstanceDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findSome", method = RequestMethod.POST)
    public ResponseEntity<Iterable<RuleInstanceDTO>> findSome(@RequestBody MultiIdBasedRequest request)
    {
        Iterable<RuleInstance> rules = ris.findAll(request.getIds());
        List<RuleInstanceDTO> result = new ArrayList<>();
        for (RuleInstance ri : rules)
        {
            result.add(new RuleInstanceDTO(ri));
        }
        return new ResponseEntity<Iterable<RuleInstanceDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public ResponseEntity<RuleInstanceDTO> findOne(@RequestBody SingleIdBasedRequest request)
    {
        RuleInstance result = ris.findOne(request.getId());
        return new ResponseEntity<RuleInstanceDTO>(new RuleInstanceDTO(result), HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<RuleInstanceDTO> save(@RequestBody RuleInstanceDTO request)
    {
        // TODO actual saving
        return new ResponseEntity<RuleInstanceDTO>(request, HttpStatus.OK);
    }
}
