package test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CTest2 {
	private static final Logger log = LoggerFactory.getLogger(CTest.class);

	@Test
	public void Test1(){
		ArrayList<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		for(String s:list){
			if(s.equals("2")||s.equals("3")){
				list.remove(s);
			}
			System.out.println(s);
		}
//		System.out.println("abc1");
//		System.out.println("abc2");
//		List list=new ArrayList<>();
//		List list=null;
//		System.out.println(CollectionUtils.isEmpty(list));
	}
	


}
