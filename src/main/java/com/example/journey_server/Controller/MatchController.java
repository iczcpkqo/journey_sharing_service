package com.example.journey_server.Controller;

import com.example.journey_server.Service.MatchService;
import com.example.journey_server.entity.Peer;
import com.example.journey_server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/matchLeader")
    public List<Peer> matchLeader(@RequestBody Peer peer) {
        System.out.println(peer.getEmail());
        List<Peer> result = new ArrayList<>();
        if(redisUtil.lock(peer.getEmail())){
            try{
                result = matchService.getMatch(peer);
            }catch (Exception e){
                redisUtil.unlock(peer.getEmail());
                return new ArrayList<>();
            }
            return result;
        }else {
            return result;
        }


//        List<Peer> usersList = new ArrayList<>();
//        Peer peer1 = new Peer("11@qq.com", "male", 23, 4.4, 53.33,-6.32,  53.3,-6.51,  0L, 0L, 5, false);
//        Peer peer2 = new Peer("22@qq.com", "female", 23, 4.4, 53.33,-6.32,  53.3,-6.51,  0L, 0L, 5, false);
//        usersList.add(peer1);
//        usersList.add(peer2);
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
