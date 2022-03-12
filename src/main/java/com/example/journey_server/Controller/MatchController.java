package com.example.journey_server.Controller;

import com.example.journey_server.Service.MatchService;
import com.example.journey_server.entity.Peer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;


//    , @RequestParam("limit") int limit
    @PostMapping("/match")
    public List<Peer> match(@RequestBody Peer peer) {
//        List<Peer> usersList = matchService.getMatch(peer);
        List<Peer> usersList = new ArrayList<>();
        Peer peer1 = new Peer("11@qq.com",1,23,4.4,-6.32,53.33,-6.51,53.3,0L,0L,5);
        Peer peer2 = new Peer("22@qq.com",2,23,4.4,-6.32,53.33,-6.51,53.3,0L,0L,5);
        usersList.add(peer1);
        usersList.add(peer2) ;
        return usersList;
    }

}
