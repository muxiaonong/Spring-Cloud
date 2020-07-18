package com.mxn.order.service;

import com.online.taxi.common.dto.ResponseResult;

public interface GrabService {

    /**
     * 商品抢单
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder(int orderId, int driverId);
}
