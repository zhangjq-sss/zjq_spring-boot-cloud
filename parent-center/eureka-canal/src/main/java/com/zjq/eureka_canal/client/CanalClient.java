package com.zjq.eureka_canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenwei01 on 2017/4/9.
 */
public class CanalClient {

    public static void main(String[] args) {
        while (true) {
            //连接canal 端口默认11111
            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(), 11111), "example", "root", "123456");
            connector.connect();
            //订阅 监控的 数据库.表
            connector.subscribe("user1.t_users");
            //一次取5条
            Message msg = connector.getWithoutAck(5);

            long batchId = msg.getId();
            int size = msg.getEntries().size();
            if (batchId < 0 || size == 0) {
                System.out.println("没有消息，休眠5秒");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //
                CanalEntry.RowChange row = null;
                for (CanalEntry.Entry entry : msg.getEntries()) {
                    try {
                        row = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                        List<CanalEntry.RowData> rowDatasList = row.getRowDatasList();
                        for (CanalEntry.RowData rowdata : rowDatasList) {
                            List<CanalEntry.Column> afterColumnsList = rowdata.getAfterColumnsList();
                            Map<String, Object> dataMap = transforListToMap(afterColumnsList);
                            if (row.getEventType() == CanalEntry.EventType.INSERT) {
                                //具体业务操作
                                System.out.println(dataMap);
                            } else if (row.getEventType() == CanalEntry.EventType.UPDATE) {
                                //具体业务操作
                                System.out.println(dataMap);
                            } else if (row.getEventType() == CanalEntry.EventType.DELETE) {
                                List<CanalEntry.Column> beforeColumnsList = rowdata.getBeforeColumnsList();
                                for (CanalEntry.Column column : beforeColumnsList) {
                                    if ("id".equals(column.getName())) {
                                        //具体业务操作
                                        System.out.println("删除的id：" + column.getValue());
                                    }
                                }
                            } else {
                                System.out.println("其他操作类型不做处理");
                            }

                        }

                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                }
                //确认消息
                connector.ack(batchId);
            }


        }
    }

    public static Map<String, Object> transforListToMap(List<CanalEntry.Column> afterColumnsList) {
        Map map = new HashMap();
        if (afterColumnsList != null && afterColumnsList.size() > 0) {
            for (CanalEntry.Column column : afterColumnsList) {
                map.put(column.getName(), column.getValue());
            }
        }
        return map;
    }


}