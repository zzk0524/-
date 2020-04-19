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
	
	//��ѯ����
	@GetMapping("/selectPassword")
	public Result selectPassword(String account){
//		System.out.println(account);
		return service.selectPassword(account);
	}
	

	//�޸�����
	@GetMapping("/updatePassword")
	public Result updatePassword(String account,String password){
//		System.out.println(account+password);
		return service.updatePassword(account,password);
	}
	
	
	//��ѯ���л���
	@GetMapping("/selectAllGoods")
	public Result selectAllGoods(){
		return service.selectAllGoods();
	}
	
	
	//��������ѯ����
	@GetMapping("/selectGoods")
	public Result selectGoods(String goods){
//		System.out.println(goods);
		return service.selectGoods(goods);
	}
	
	//���ӻ�����Ϣ
	@PostMapping("/addGoods")
	public Result addGoods(@RequestBody Goods goods){
//		System.out.println(goods);
		return service.addGoods(goods);
	}
	
	//�޸Ļ�����Ϣ
	@PostMapping("/updateGoods")
	public Result updateGoods(@RequestBody Goods goods){
//		System.out.println(goods);
		return service.updateGoods(goods);
	}
	
	//ɾ��������Ϣ
	@GetMapping("/deleteGoodsById")
	public Result deleteGoodsById(int id){
		return service.deleteGoodsById(id);
	}
			
	
			
	//��ѯ���н�����
	@GetMapping("/selectAllList")
	public Result selectAllList(){
		return service.selectAllList();
	}
		
	//��ѯ������
	@GetMapping("/selectList")
	public Result selectList(String goods,String frontTime,String endTime){
		System.out.println(goods+frontTime+endTime);
		return service.selectList(goods,frontTime,endTime);
	}
	
	//�޸Ľ�������Ϣ
	@PostMapping("/updateList")
	public Result updateList(@RequestBody ListList list){
		return service.updateList(list);
	}
	
	//���ӽ�������Ϣ
	@PostMapping("/addList")
	public Result addList(@RequestBody ListList list){
		return service.addList(list);
	}
	
	//ɾ����������Ϣ
	@PostMapping("/deleteList")
	public Result deleteList(@RequestBody ListList list){
		return service.deleteList(list);
	}
	
	
	
	//���������۶�
	@GetMapping("/sevenDaysUnitprice")
	public Result sevenDaysUnitprice(){
		return service.sevenDaysUnitprice();
	}
	
	//������������
	@GetMapping("/sevenDaysInprice")
	public Result sevenDaysInprice(){
		return service.sevenDaysInprice();
	}
	
	//�����������۶�
	@GetMapping("/sixMonthsUnitprice")
	public Result sixMonthsUnitprice(){
		return service.sixMonthsUnitprice();
	}
	
	//�������½������
	@GetMapping("/sixMonthsInprice")
	public Result sixMonthsInprice(){
		return service.sixMonthsInprice();
	}
	
	//������鿴�������۶�
	@GetMapping("/dayPriceByGoodssort")
	public Result dayPriceByGoodssort(){
		return service.dayPriceByGoodssort();
	}

	//������鿴�������۶�
	@GetMapping("/monthPriceByGoodssort")
	public Result monthPriceByGoodssort(){
		return service.monthPriceByGoodssort();
	}
	
	
	
	//����
	@GetMapping("/backup")
	public Result backup(){
		return service.backup();
	}
	
	
}
