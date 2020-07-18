package com.mxn.order.service.impl;

import com.mxn.order.service.GrabService;
import com.mxn.order.service.OrderService;
import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.common.dto.ResponseResult;


import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("grabRedisRedissonRedLockLockService")
public class GrabRedisRedissonRedLockLockServiceImpl implements GrabService {

    @Autowired
    private RedissonClient redissonRed1;
    @Autowired
    private RedissonClient redissonRed2;
    @Autowired
    private RedissonClient redissonRed3;
    
    @Autowired
    OrderService orderService;

    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
        String lockKey = (RedisKeyConstant.GRAB_LOCK_ORDER_KEY_PRE + orderId).intern();
        //红锁
        RLock rLock1 = redissonRed1.getLock(lockKey);
        RLock rLock2 = redissonRed2.getLock(lockKey);
        RLock rLock3 = redissonRed2.getLock(lockKey);
        RedissonRedLock rLock = new RedissonRedLock(rLock1,rLock2,rLock3);



        try {
            rLock.lock();
    		// 此代码默认 设置key 超时时间30秒，过10秒，再延时
			System.out.println("用户:"+driverId+" 执行抢单逻辑");
			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
            	System.out.println("用户:"+driverId+" 抢单成功");
            }else {
            	System.out.println("用户:"+driverId+" 抢单失败");
            }
            
        } finally {
        	rLock.unlock();
        }
        return null;
    }
}
