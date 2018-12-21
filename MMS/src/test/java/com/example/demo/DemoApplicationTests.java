package com.example.demo;

import com.example.demo.model.Word;
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
		Word word=new Word();
		List<String>s=new ArrayList<>();
		s.add("检查1");
		s.add("检查2");
		word.createWord(s);
//		word.dowloadWord()
	}

}
