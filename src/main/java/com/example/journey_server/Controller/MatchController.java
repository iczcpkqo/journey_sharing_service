package com.example.journey_server.Controller;

import com.example.journey_server.Service.MatchService;
import com.example.journey_server.entity.Peer;
import com.example.journey_server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/matchLeader")
    public List<Peer> matchLeader(@RequestBody Peer peer) {
        System.out.println("matchLeader:" + peer.getEmail());
        List<Peer> usersList = matchService.getMatch(peer);
        System.out.println(usersList.size());
        return usersList;
    }


    @PostMapping("/matchMember")
    public List<Peer> matchMember(@RequestBody Peer peer) {
        System.out.println("matchMember:" + peer.getEmail());
        List<Peer> usersList = matchService.getMatchMember(peer);
//        System.out.println(usersList.size());
        return usersList;
    }

    @PostMapping("/createGroup")
    public String createGroup(@RequestBody List<Peer> peers) {
        Peer leader = matchService.getLeader(peers);
        if(redisUtil.lock(leader.getEmail())){
            String groupId = matchService.createGroup(peers);
            return groupId;
        }else {
            return "";
        }
    }

}
