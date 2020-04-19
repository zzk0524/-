package test;

import org.junit.Test;

import com.sdut.service.GoodsService;
import com.sdut.util.Result;

public class test {

	@Test
	public void testBackup(){
		GoodsService g=new GoodsService();
		Result result= g.backupAccount();
		System.out.println(result.getSuccess());
	}
}
