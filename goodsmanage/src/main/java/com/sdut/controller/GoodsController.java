package com.sdut.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdut.bean.Goods;
import com.sdut.bean.GoodsList;
import com.sdut.bean.ListList;
import com.sdut.service.GoodsService;
import com.sdut.util.Result;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {

	@Autowired
	GoodsService service;
	
	//查询密码
	@GetMapping("/selectPassword")
	public Result selectPassword(String account){
//		System.out.println(account);
		return service.selectPassword(account);
	}
	

	//修改密码
	@GetMapping("/updatePassword")
	public Result updatePassword(String account,String password){
//		System.out.println(account+password);
		return service.updatePassword(account,password);
	}
	
	
	//查询所有货物
	@GetMapping("/selectAllGoods")
	public Result selectAllGoods(){
		return service.selectAllGoods();
	}
	
	
	//按条件查询货物
	@GetMapping("/selectGoods")
	public Result selectGoods(String goods){
//		System.out.println(goods);
		return service.selectGoods(goods);
	}
	
	//增加货物信息
	@PostMapping("/addGoods")
	public Result addGoods(@RequestBody Goods goods){
//		System.out.println(goods);
		return service.addGoods(goods);
	}
	
	//修改货物信息
	@PostMapping("/updateGoods")
	public Result updateGoods(@RequestBody Goods goods){
//		System.out.println(goods);
		return service.updateGoods(goods);
	}
	
	//删除货物信息
	@GetMapping("/deleteGoodsById")
	public Result deleteGoodsById(int id){
		return service.deleteGoodsById(id);
	}
			
	
			
	//查询所有进出货
	@GetMapping("/selectAllList")
	public Result selectAllList(){
		return service.selectAllList();
	}
		
	//查询进出货
	@GetMapping("/selectList")
	public Result selectList(String goods,String frontTime,String endTime){
		System.out.println(goods+frontTime+endTime);
		return service.selectList(goods,frontTime,endTime);
	}
	
	//修改进出货信息
	@PostMapping("/updateList")
	public Result updateList(@RequestBody ListList list){
		return service.updateList(list);
	}
	
	//增加进出货信息
	@PostMapping("/addList")
	public Result addList(@RequestBody ListList list){
		return service.addList(list);
	}
	
	//删除进出货信息
	@PostMapping("/deleteList")
	public Result deleteList(@RequestBody ListList list){
		return service.deleteList(list);
	}
	
	
	
	//近七天销售额
	@GetMapping("/sevenDaysUnitprice")
	public Result sevenDaysUnitprice(){
		return service.sevenDaysUnitprice();
	}
	
	//近七天进货金额
	@GetMapping("/sevenDaysInprice")
	public Result sevenDaysInprice(){
		return service.sevenDaysInprice();
	}
	
	//近六个月销售额
	@GetMapping("/sixMonthsUnitprice")
	public Result sixMonthsUnitprice(){
		return service.sixMonthsUnitprice();
	}
	
	//近六个月进货金额
	@GetMapping("/sixMonthsInprice")
	public Result sixMonthsInprice(){
		return service.sixMonthsInprice();
	}
	
	//按分类查看本日销售额
	@GetMapping("/dayPriceByGoodssort")
	public Result dayPriceByGoodssort(){
		return service.dayPriceByGoodssort();
	}

	//按分类查看本月销售额
	@GetMapping("/monthPriceByGoodssort")
	public Result monthPriceByGoodssort(){
		return service.monthPriceByGoodssort();
	}
	
	
	
	//备份
	@GetMapping("/backup")
	public Result backup(){
		return service.backup();
	}
	
	
}
