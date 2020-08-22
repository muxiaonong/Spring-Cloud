package com.example.userconsumer;
import java.util.Map;

import com.mxn.userapi.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

	@Autowired
	ConsumerApi api;
	
	@Value("${server.port}")
	String port;
	
//	@Autowired
//	MashibingApi mapi;

	@GetMapping("/alive")
	public String alive() {
		/**
		 * URL 不能变 
		 * 
		 * jar
		 * 文档
		 */
		return api.alive();
	}
	
	
	@GetMapping("/alive2")
	public String alive2() {
		/**
		 * URL 不能变 
		 * 
		 * jar
		 * 文档
		 */
		return "Consumer："+port+api.alive();
	}
	
	
//	@GetMapping("/vip")
//	public String vip() {
//		
//		return mapi.getVip();
//	}
	
	@GetMapping("/map")
	public String map(Integer id) {
		System.out.println(id);
		return  api.getMap(id).toString();
	}
	
	@GetMapping("/map2")
	public Map<Integer, String> map2(Integer id,String name) {
		System.out.println(id);
		return api.getMap2(id,name);
	}
	
	
	@GetMapping("/map3")
	public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//		
//		map.put("id", id);
//		map.put("name", name);
//		syso
		System.out.println(map);
		return api.getMap3(map);
	}
	
	
	@GetMapping("/map4")
	public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//		
//		map.put("id", id);
//		map.put("name", name);
//		syso
		System.out.println(map);
		return api.postMap(map);
	}
	
	@PostMapping("/postPerson")
	public Person postPerson(@RequestParam Map<String, Object> map){
		Person person = new Person();
		person.setId(Integer.parseInt(map.get("id").toString()));
		person.setName("xxoo");
		return api.postPerson(person);
	}

	
}