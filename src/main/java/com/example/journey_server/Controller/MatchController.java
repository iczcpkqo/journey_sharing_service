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


    @PostMapping("/matchLeader")
    public List<Peer> matchLeader(@RequestBody Peer peer) {
        System.out.println(peer.getEmail());
        List<Peer> usersList = matchService.getMatch(peer);

//        List<Peer> usersList = new ArrayList<>();
//        Peer peer1 = new Peer("11@qq.com", "male", 23, 4.4, 53.33,-6.32,  53.3,-6.51,  0L, 0L, 5, false);
//        Peer peer2 = new Peer("22@qq.com", "female", 23, 4.4, 53.33,-6.32,  53.3,-6.51,  0L, 0L, 5, false);
//        usersList.add(peer1);
//        usersList.add(peer2);
        return usersList;
    }

    @PostMapping("/matchMember")
    public List<Peer> matchMember(@RequestBody Peer peer) {
        System.out.println("matchMember:" + peer.getEmail());
        List<Peer> usersList = matchService.getMatchMember(peer);
//        List<Peer> usersList = new ArrayList<>();
//        Peer peer1 = new Peer("11@qq.com", "male", 23, 4.4, -6.32, 53.33, -6.51, 53.3, 0L, 0L, 5, false);
//        Peer peer2 = new Peer("22@qq.com", "female", 23, 4.4, -6.32, 53.33, -6.51, 53.3, 0L, 0L, 5, false);
//        usersList.add(peer1);
//        usersList.add(peer2);
        return usersList;
    }

}
