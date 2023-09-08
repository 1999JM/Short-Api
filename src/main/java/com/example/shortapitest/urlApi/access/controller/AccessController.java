package com.example.shortapitest.urlApi.access.controller;

import com.example.shortapitest.urlApi.url.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccessController {

    private final UrlService urlService;

    @GetMapping("/jmApi/{id}")
    public String createAccessLog(@PathVariable String id, HttpServletRequest request){

        //문제점 1. jmApi로 시작하는 모든 요청을 수용함 == 아무 url을 넣어도 jmApi로 시도하면 api가 동작함.

        String ip = "";
        Map<String, String> map = new HashMap<String, String>();

        try {
            ip = InetAddress.getLocalHost().getHostAddress();

            String agent = request.getHeader("USER-AGENT");
            map.put("ip", ip);
            map.put("header", agent);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String url = urlService.searchUrl(id,map);

        return "redirect:"+url;
    }

         /*   try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                //MAC 주소 조회
                byte[] hardwareAddress = ni.getHardwareAddress();

                //Virtual Box의 MAC 주소 형태의 차이로 구별
                boolean virtual = false;
                if(hardwareAddress != null) {
                    //hardwareAddress와 localIp 값을 각각 출력해보고
                    //Virtual Box IP가 아닐 때의 MAC 주소를 아래 조건문을 통해 필터링
                    if(hardwareAddress[0] != -44)
                        virtual = true;
                }
                if(virtual) continue;

                for(InterfaceAddress addr : ni.getInterfaceAddresses()) {
                    InetAddress address = addr.getAddress();
                    if(address.isSiteLocalAddress()) {
                        //주소 문자열에서 /가 나오는 것을 치환
                        String localIp = addr.getAddress().toString().replace("/", "");
                        System.out.println(localIp + "1");
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }*/

    //Virtual Box가 없는 경우 위에서 걸리지 않는 것으로 보임
    //따라서, 위에서 반환되지 않은 경우
    //서버 IP를 조회하는 기존 코드를 추가 작성


}
