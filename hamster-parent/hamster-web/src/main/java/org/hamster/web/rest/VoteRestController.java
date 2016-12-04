package org.hamster.web.rest;


import java.util.ArrayList;
import java.util.List;

import org.hamster.api.exc.DuplicateVoteException;
import org.hamster.container.ChallengeEngine;
import org.hamster.container.ChallengeEvent;
import org.hamster.model.runtime.Vote;
import org.hamster.service.VoteServiceImpl;
import org.hamster.web.dto.VoteDTO;
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
@RequestMapping("api/v1/votes")
public class VoteRestController
{
    @Autowired
    private VoteServiceImpl vs;

    @Autowired
    private ChallengeEngine engine;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<VoteDTO>> findAll()
    {
        Iterable<Vote> votes = vs.findAll();
        List<VoteDTO> result = new ArrayList<>();
        for (Vote v : votes)
        {
            result.add(new VoteDTO(v));
        }

        return new ResponseEntity<Iterable<VoteDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findSome", method = RequestMethod.POST)
    public ResponseEntity<Iterable<VoteDTO>> findSome(@RequestBody MultiIdBasedRequest request)
    {
        Iterable<Vote> votes = vs.findAll(request.getIds());
        List<VoteDTO> result = new ArrayList<>();
        for (Vote v : votes)
        {
            result.add(new VoteDTO(v));
        }
        return new ResponseEntity<Iterable<VoteDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public ResponseEntity<VoteDTO> findOne(@RequestBody SingleIdBasedRequest request)
    {
        Vote result = vs.findOne(request.getId());
        return new ResponseEntity<VoteDTO>(new VoteDTO(result), HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<VoteDTO> save(@RequestBody VoteDTO request)
        throws DuplicateVoteException
    {
        Vote vote = request.toEntity();
        vs.save(vote);
        engine.handleChallengeEvent(vote.getChallenge().getId(), ChallengeEvent.VOTED_ON);
        return new ResponseEntity<VoteDTO>(request, HttpStatus.OK);
    }
}
