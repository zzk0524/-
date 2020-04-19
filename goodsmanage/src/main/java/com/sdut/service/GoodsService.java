package com.sdut.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sdut.bean.Account;
import com.sdut.bean.Goods;
import com.sdut.bean.GoodsList;
import com.sdut.bean.ListList;
import com.sdut.dao.GoodsDao;
import com.sdut.util.Result;

@Service
public class GoodsService {

	@Autowired
	GoodsDao dao;
	
	//��ѯ����
	public Result selectPassword(String account){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.selectPassword(account));
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//�޸�����
	public Result updatePassword(String account,String password){
		Result result=new Result();
//		System.out.println(account+password);
		try {
			result.setSuccess(true);
			result.setMessage("�޸ĳɹ�����");
			dao.updatePassword(account,password);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
		
		
	
	//��ѯ���л���
	public Result selectAllGoods(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.selectAllGoods());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//��������ѯ����
	public Result selectGoods(String goods){
		Result result=new Result();
		try {
			if(goods.length()==3&&Character.isDigit(goods.charAt(0))&&Character.isDigit(goods.charAt(1))&&Character.isDigit(goods.charAt(2))){
				result.setSuccess(true);
				result.setData(dao.selectGoodsByGoodsid(goods));
				return result;
			}else if(goods.equals("ʳƷ")||goods.equals("��Ʒ")||goods.equals("����")||goods.equals("�̾�")||goods.equals("�ٻ�")||goods.equals("����")||goods.equals("��װ")){
				result.setSuccess(true);
				result.setData(dao.selectGoodsByGoodssort(goods));
				return result;
			}else{
				result.setSuccess(true);
				result.setData(dao.selectGoodsByGoodsname(goods));
				return result;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//���ӻ�����Ϣ
	public Result addGoods(Goods goods){
		Result result=new Result();
		if(isGoodsid(goods.getGoodsid())){
			try {
				result.setSuccess(true);
				result.setMessage("���ӳɹ�����");
				dao.addGoods(goods);
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
				result.setMessage("���������󣡣�");
				return result;
			}
		}else{
			result.setSuccess(false);
			result.setMessage("��Ʒ����ѱ�ռ�ã���");
			return result;
		}
		
	}
	
	//�޸Ļ�����Ϣ
	public Result updateGoods(Goods goods){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setMessage("�޸ĳɹ�����");
			dao.updateGoods(goods);;
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//ɾ��������Ϣ
	public Result deleteGoodsById(int id){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setMessage("ɾ���ɹ�����");
			dao.deleteGoodsById(id);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	
	
	//��ѯ���н�����
	public Result selectAllList(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.selectAllList());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			e.printStackTrace();
			return result;
		}
	}
			
	//��ѯ������
	public Result selectList(String goods,String frontTime,String endTime){
		Result result=new Result();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date front=null;
		Date end=null;
		try {
			if(frontTime!=null&&!frontTime.equals("")&&endTime!=null&&!endTime.equals("")){
				front=sdf.parse(frontTime);
				end=sdf.parse(endTime);
			}
			result.setSuccess(true);
			result.setData(dao.selectList(goods,front,end));
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
		
	//���ӽ�������Ϣ
	public Result addList(ListList list){
		Result result=new Result();
		if(!isGoodsid(list.getGoodsid())){
			try {
				result.setSuccess(true);
				dao.addList(list);
				int in=dao.inNumber(list.getGoodsid());
				int out=dao.outNumber(list.getGoodsid());
				int number=in-out;
				dao.updateGoodsNumber(number,list.getGoodsid());
				if(number<0){
					result.setMessage("��Ʒ�����Ϊ��������");
				}else{
					result.setMessage("�ɹ�����");
				}
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.setSuccess(false);
				result.setMessage("���������󣡣�");
				return result;
			}
		}else{
			result.setSuccess(false);
			result.setMessage("��Ʒ�����ڣ���");
			return result;
		}
	}
	
	//�޸Ľ�������Ϣ
	public Result updateList(ListList list){
		Result result=new Result();
		if(!isGoodsid(list.getGoodsid())){
			try {
				result.setSuccess(true);
				dao.updateList(list);
				int in=dao.inNumber(list.getGoodsid());
				int out=dao.outNumber(list.getGoodsid());
				int number=in-out;
				dao.updateGoodsNumber(number,list.getGoodsid());
				if(number<0){
					result.setMessage("��Ʒ�����Ϊ��������");
				}else{
					result.setMessage("�ɹ�����");
				}
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.setSuccess(false);
				result.setMessage("���������󣡣�");
				return result;
			}
		}else{
			result.setSuccess(false);
			result.setMessage("��Ʒ�����ڣ���");
			return result;
		}
	}
	
	//ɾ����������Ϣ
	public Result deleteList(ListList list){
		Result result=new Result();
		try {
			result.setSuccess(true);
			dao.deleteList(list);
			int in=dao.inNumber(list.getGoodsid());
			int out=dao.outNumber(list.getGoodsid());
			int number=in-out;
			dao.updateGoodsNumber(number,list.getGoodsid());
			if(number<0){
				result.setMessage("��Ʒ�����Ϊ��������");
			}else{
				result.setMessage("�ɹ�����");
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	
	
	
	//���������۶�
	public Result sevenDaysUnitprice(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.sevenDaysUnitprice());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//������������
	public Result sevenDaysInprice(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.sevenDaysInprice());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//�����������۶�
	public Result sixMonthsUnitprice(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.sixMonthsUnitprice());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//�������½������
	public Result sixMonthsInprice(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.sixMonthsInprice());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	//������鿴�������۶�
	public Result dayPriceByGoodssort(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.dayPriceByGoodssort());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	//������鿴�������۶�
	public Result monthPriceByGoodssort(){
		Result result=new Result();
		try {
			result.setSuccess(true);
			result.setData(dao.monthPriceByGoodssort());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("���������󣡣�");
			return result;
		}
	}
	
	
	//�ж���Ʒ����Ƿ��ظ�
	public boolean isGoodsid(String goodsId){
		if(dao.isGoodsid(goodsId)>0){
			return false;
		}else{
			return true;
		}
	}
	
	
	//����account��
	public Result backupAccount(){
		Result result=new Result();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			java.util.List<Account> l=dao.selectAccount();
			String sheetName = "AccountBackup";
			HSSFSheet stuSheet = workbook.createSheet(sheetName);
			HSSFRow head = stuSheet.createRow(0);
			head.createCell(0).setCellValue("id");
			head.createCell(1).setCellValue("account");
			head.createCell(2).setCellValue("password");
			for(int i=0;i<l.size();i++){
				HSSFRow dataRow = stuSheet.createRow(i+1);
				//ѭ��ʱ��ʵ�����ȡ�������ö�Ӧ��get���� ���н�������ֵ
				Account s = l.get(i);
				dataRow.createCell(0).setCellValue(s.getId());
				dataRow.createCell(1).setCellValue(s.getAccount());
				dataRow.createCell(2).setCellValue(s.getPassword());
			}
			String folderpath="G:\\goodsmanage\\backup";
			File folder = new File(folderpath);
		    //����ļ��в����ڴ�����Ӧ���ļ���
		    if (!folder.exists()) {
		        folder.mkdirs();
		    }
		    //�����ļ���
		    String fileName = sheetName + ".xls";
		    String savePath = folderpath + File.separator + fileName;
		    File file=new File(savePath);
		    if(file.exists()){
		    	file.delete();
		    }
		    // System.out.println(savePath);

		    OutputStream fileOut = new FileOutputStream(savePath);
		    workbook.write(fileOut);
		    fileOut.close();
		    workbook.close();
//		    System.out.println("������ϣ���");
			result.setSuccess(true);
			result.setMessage("���ݳɹ�����");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
	}
	//����goods��
	public Result backupGoods(){
		Result result=new Result();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			List<Goods> l=dao.selectGoods();
			String sheetName = "GoodsBackup";
			HSSFSheet stuSheet = workbook.createSheet(sheetName);
			HSSFRow head = stuSheet.createRow(0);
			head.createCell(0).setCellValue("id");
			head.createCell(1).setCellValue("goodsid");
			head.createCell(2).setCellValue("goodsname");
			head.createCell(3).setCellValue("goodssort");
			head.createCell(4).setCellValue("inprice");
			head.createCell(5).setCellValue("unitprice");
			head.createCell(6).setCellValue("number");
			head.createCell(7).setCellValue("state");
			for(int i=0;i<l.size();i++){
				HSSFRow dataRow = stuSheet.createRow(i+1);
				//ѭ��ʱ��ʵ�����ȡ�������ö�Ӧ��get���� ���н�������ֵ
				Goods s = l.get(i);
				dataRow.createCell(0).setCellValue(s.getId());
				dataRow.createCell(1).setCellValue(s.getGoodsid());
				dataRow.createCell(2).setCellValue(s.getGoodsname());
				dataRow.createCell(3).setCellValue(s.getGoodssort());
				dataRow.createCell(4).setCellValue(s.getInprice());
				dataRow.createCell(5).setCellValue(s.getUnitprice());
				dataRow.createCell(6).setCellValue(s.getNumber());
				dataRow.createCell(7).setCellValue(s.getState());
			}
			String folderpath="G:\\goodsmanage\\backup";
			File folder = new File(folderpath);
		    //����ļ��в����ڴ�����Ӧ���ļ���
		    if (!folder.exists()) {
		        folder.mkdirs();
		    }
		    //�����ļ���
		    String fileName = sheetName + ".xls";
		    String savePath = folderpath + File.separator + fileName;
		    File file=new File(savePath);
		    if(file.exists()){
		    	file.delete();
		    }
		    // System.out.println(savePath);
		    OutputStream fileOut = new FileOutputStream(savePath);
		    workbook.write(fileOut);
		    fileOut.close();
		    workbook.close();
//		    System.out.println("������ϣ���");
			result.setSuccess(true);
			result.setMessage("���ݳɹ�����");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
	}
	//����list��
	public Result backupList(){
		Result result=new Result();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			List<ListList> l=dao.selectListList();
			String sheetName = "ListBackup";
			HSSFSheet stuSheet = workbook.createSheet(sheetName);
			HSSFRow head = stuSheet.createRow(0);
			head.createCell(0).setCellValue("id");
			head.createCell(1).setCellValue("goodsid");
			head.createCell(2).setCellValue("state");
			head.createCell(3).setCellValue("number");
			head.createCell(4).setCellValue("price");
			head.createCell(5).setCellValue("time");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			for(int i=0;i<l.size();i++){
				HSSFRow dataRow = stuSheet.createRow(i+1);
				//ѭ��ʱ��ʵ�����ȡ�������ö�Ӧ��get���� ���н�������ֵ
				ListList s = l.get(i);
				dataRow.createCell(0).setCellValue(s.getId());
				dataRow.createCell(1).setCellValue(s.getGoodsid());
				dataRow.createCell(2).setCellValue(s.getState());
				dataRow.createCell(3).setCellValue(s.getNumber());
				dataRow.createCell(4).setCellValue(s.getPrice());
				dataRow.createCell(5).setCellValue(sdf.format(s.getTime()));
			}
			String folderpath="G:\\goodsmanage\\backup";
			File folder = new File(folderpath);
		    //����ļ��в����ڴ�����Ӧ���ļ���
		    if (!folder.exists()) {
		        folder.mkdirs();
		    }
		    //�����ļ���
		    String fileName = sheetName + ".xls";
		    String savePath = folderpath + File.separator + fileName;
		    File file=new File(savePath);
		    if(file.exists()){
		    	file.delete();
		    }
		    // System.out.println(savePath);
		    OutputStream fileOut = new FileOutputStream(savePath);
		    workbook.write(fileOut);
		    fileOut.close();
		    workbook.close();
//		    System.out.println("������ϣ���");
			result.setSuccess(true);
			result.setMessage("���ݳɹ�����");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
	}
	public Result backup(){
		Result result=new Result();
		try {
			Result a=backupAccount();
			Result g=backupGoods();
			Result l=backupList();
			result.setSuccess(true);
			result.setMessage("�˺ű�"+a.getMessage()+"�����"+g.getMessage()+"��������"+l.getMessage()+"   �ѱ�����·��G:/goodsmanage/backup��");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
		
	}
}
	
	
