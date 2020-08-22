package com.example.userconsumer;

import java.util.Map;

import com.mxn.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-provider")
public interface ConsumerApi extends UserApi {
	
	@GetMapping("/getMap")
	Map<Integer, String> getMap(@RequestParam("id") Integer id);
	@GetMapping("/getMap2")
	Map<Integer, String> getMap2(@RequestParam("id") Integer id, @RequestParam("name") String name);
	
	@GetMapping("/getMap3")
	Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);
	
	@PostMapping("/postMap")
	Map<Integer, String> postMap(Map<String, Object> map);
	
}
