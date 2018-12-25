package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.enums.Detail;
import com.example.demo.enums.Item;
import com.example.demo.enums.OrderResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
//		List<OrderResponse>orderResponses=new ArrayList<>();
//		OrderResponse orderResponse=new OrderResponse();
//		OrderResponse orderResponse1=new OrderResponse();
//		orderResponse.setOid(1);
//		orderResponse.setDate("2018-12-23");
//		Detail detail=new Detail();
////		detail.setTotal(5);
//		Item item=new Item();
//		item.setName("阿司匹林");
//		item.setNum(2);
//		item.setPrice(5);
//		Item item1=new Item();
//		item1.setPrice(15);
//		item1.setNum(3);
//		item1.setName("快克");
//		List<Item>items=new ArrayList<>();
//		items.add(item);
//		items.add(item1);
//		detail.setItems(items);
//		orderResponse.setDetails(detail);
//		System.out.println(JSON.toJSONString(JSON.toJSONString(orderResponse)));
		String s="50005052670";
		System.out.println("长度：==================================="+s.length());
	}

}
