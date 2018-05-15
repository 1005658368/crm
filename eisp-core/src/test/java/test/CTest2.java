package test;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CTest2 {
	private static final Logger log = LoggerFactory.getLogger(CTest.class);

	@Test
	public void Test1(){
//		System.out.println("abc1");
//		System.out.println("abc2");
//		List list=new ArrayList<>();
		List list=null;
		System.out.println(CollectionUtils.isEmpty(list));
	}
	


}
