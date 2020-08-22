package com.mxn.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/User")
public interface UserApi {
	
	/**
	 * 查看当面服务状态
	 * @return 么么哒
	 */
	@GetMapping("/alive")
	public String alive();
	
	@GetMapping("/getById")
	public String getById(Integer id);
	
	@PostMapping("/postPerson")
	public Person postPerson(@RequestBody Person person);
	
	

}
