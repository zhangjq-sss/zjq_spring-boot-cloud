package org.eureka.elasticsearch.canal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zjq.euraka_domain.user.User;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eureka.elasticsearch.domain.EsUser;
import org.eureka.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component      //被 spring 容器管理
@Order(2)     //如果多个自定义的 ApplicationRunner  ，用来标明执行的顺序
@Slf4j
public class CanalClient implements ApplicationRunner{
	
	@Autowired
	private UserService userService;

    public static Map<String, Object> transforListToMap(List<CanalEntry.Column> afterColumnsList) {
        Map map = new HashMap();
        if (afterColumnsList != null && afterColumnsList.size() > 0) {
            for (CanalEntry.Column column : afterColumnsList) {
                map.put(column.getName(), column.getValue());
            }
        }
        return map;
    }

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		log.info("---开始监听t_users数据库执行情况---");
		 while (true) {
	            //连接canal 端口默认11111
	            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(), 11111), "example", "root", "123456");
	            connector.connect();
	            //订阅 监控的 数据库.表
	            connector.subscribe("user.t_users");
	            //一次取5条
	            Message msg = connector.getWithoutAck(1000);

	            long batchId = msg.getId();
	            int size = msg.getEntries().size();
	            if (batchId < 0 || size == 0) {
	                log.info("没有消息，休眠5秒");
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
	                                log.info("插入数据库："+dataMap.toString());
	                                EsUser user = JSON.parseObject(JSONObject.toJSONString(dataMap,true),EsUser.class);
	                                userService.save(user);
	                            } else if (row.getEventType() == CanalEntry.EventType.UPDATE) {
	                                //具体业务操作
	                                log.info("更新数据库："+dataMap.toString());
	                                EsUser user = JSON.parseObject(JSONObject.toJSONString(dataMap,true),EsUser.class);
	                                userService.update(user);
	                            } else if (row.getEventType() == CanalEntry.EventType.DELETE) {
	                                List<CanalEntry.Column> beforeColumnsList = rowdata.getBeforeColumnsList();
	                                for (CanalEntry.Column column : beforeColumnsList) {
	                                    if ("id".equals(column.getName())) {
	                                        //具体业务操作
	                                        log.info("删除数据库的id：" + column.getValue());
	                                        EsUser user = userService.queryUserBYId(Long.parseLong(column.getValue()));
	                                        userService.delete(user);
	                                    }
	                                }
	                            } else {
	                                log.info("其他操作类型不做处理");
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


}