package com.mxn.order.service.impl;

import com.mxn.order.service.GrabService;
import com.mxn.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.ResponseResult;

@Service("grabJvmLockService")
public class GrabJvmLockServiceImpl implements GrabService {
	
	@Autowired
	OrderService orderService;
	
	@Override
	public ResponseResult grabOrder(int orderId, int driverId) {
		String lock = (orderId+"");
		
		synchronized (lock.intern()) {
			try {
				System.out.println("用户:"+driverId+" 执行下单逻辑");
				
	            boolean b = orderService.grab(orderId, driverId);
	            if(b) {
	            	System.out.println("用户:"+driverId+" 下单成功");
	            }else {
	            	System.out.println("用户:"+driverId+" 下单失败");
	            }
	        } finally {
	        	
	        }
		}
		
		
		return null;
	}

}
