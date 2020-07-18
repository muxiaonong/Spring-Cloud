package com.mxn.order.service.impl;

import com.mxn.order.service.GrabService;
import com.mxn.order.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.ResponseResult;


@Service("grabRedisRedissonService")
public class GrabRedisRedissonServiceImpl implements GrabService {

	@Autowired
	RedissonClient redissonClient;
	
	@Autowired
	OrderService orderService;
	
    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
    	String lock = "order_"+(orderId+"");
    	
    	RLock rlock = redissonClient.getLock(lock.intern());
    	
    	
    	try {
    		// 此代码默认 设置key 超时时间30秒，过10秒，再延时
    		rlock.lock();
			System.out.println("用户:"+driverId+" 执行抢单逻辑");
			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
            	System.out.println("用户:"+driverId+" 抢单成功");
            }else {
            	System.out.println("用户:"+driverId+" 抢单失败");
            }
            
        } finally {
        	rlock.unlock();
        }
        return null;
    }
}
