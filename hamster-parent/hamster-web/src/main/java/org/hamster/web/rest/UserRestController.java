package org.hamster.web.rest;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hamster.model.user.User;
import org.hamster.model.user.UserRelation;
import org.hamster.service.UserRelationServiceImpl;
import org.hamster.service.UserServiceImpl;
import org.hamster.web.dto.UserDTO;
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
@RequestMapping("api/v1/users")
public class UserRestController
{
    @Autowired
    private UserServiceImpl us;

    @Autowired
    private UserRelationServiceImpl urs;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UserDTO>> findAll()
    {
        Iterable<User> users = us.findAll();
        List<UserDTO> result = new ArrayList<>();
        for (User u : users)
        {
            Collection<UserRelation> friends = urs.findByUser(u);
            List<User> list = friends.stream().map(UserRelation::getFriend).collect(Collectors.toList());
            result.add(new UserDTO(u, list));
        }

        return new ResponseEntity<Iterable<UserDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findSome", method = RequestMethod.POST)
    public ResponseEntity<Iterable<UserDTO>> findSome(@RequestBody MultiIdBasedRequest request)
    {
        Iterable<User> users = us.findAll(request.getIds());
        List<UserDTO> result = new ArrayList<>();
        for (User u : users)
        {
            result.add(new UserDTO(u));
        }
        return new ResponseEntity<Iterable<UserDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> findOne(@RequestBody SingleIdBasedRequest request)
    {
        User result = us.findOne(request.getId());
        Collection<UserRelation> friends = urs.findByUser(result);
        List<User> list = friends.stream().map(UserRelation::getFriend).collect(Collectors.toList());
        return new ResponseEntity<UserDTO>(new UserDTO(result, list), HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO request)
    {
        // TODO actual saving
        return new ResponseEntity<UserDTO>(request, HttpStatus.OK);
    }
}
