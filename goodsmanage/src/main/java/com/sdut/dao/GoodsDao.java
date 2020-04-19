package com.sdut.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.sdut.bean.*;
import com.sdut.provider.SelectListProvider;
import com.sdut.util.PriceByGoodssort;
@Mapper
public interface GoodsDao {

	//��ѯȫ���˺�
	@Select("select * from accounts")
	List<Account> selectAccount();
	
	
	//��ѯ����
	@Select("select * from accounts where account=#{account}")
	Account selectPassword(String account);

	
	
	//�޸�����
	@Update("update accounts set password=#{password} where account=#{account}")
	void updatePassword(@Param("account")String account,@Param("password")String password);
	
	
	
	//��ѯ�����
	@Select("select * from goods")
	List<Goods> selectGoods();
	
	//��ѯ���л���
	@Select("select * from goods where state=1")
	List<Goods> selectAllGoods();
	
	//����Ʒ��Ų�ѯ����
	@Select("select * from goods where goodsid=#{goodsId} and state=1")
	List<Goods> selectGoodsByGoodsid(String goodsId);
	
	//����Ʒ���Ʋ�ѯ����
	@Select("select * from goods where goodsname like '%${goodsName}%' and state=1")
	List<Goods> selectGoodsByGoodsname(@Param("goodsName")String goodsName);
	
	//����Ʒ���Ͳ�ѯ����
	@Select("select * from goods where goodssort=#{sort} and state=1")
	List<Goods> selectGoodsByGoodssort(String sort);
	
	//���ӻ�����Ϣ
	@Insert("insert into goods values(null,#{goods.goodsid},#{goods.goodsname},#{goods.goodssort},#{goods.inprice},#{goods.unitprice},0,1)")
	void addGoods(@Param("goods")Goods goods);
	
	//�޸Ļ�����Ϣ
	@Update("update goods set goodsid=#{goods.goodsid},goodsname=#{goods.goodsname},goodssort=#{goods.goodssort},inprice=#{goods.inprice},unitprice=#{goods.unitprice},number=#{goods.number} where id=#{goods.id}")
	void updateGoods(@Param("goods")Goods goods);
	
	//ɾ��������Ϣ
	@Update("update goods set state=0 where id=#{id}")
	void deleteGoodsById(int id);
	
	//��ѯ��Ʒ����Ƿ��ظ�
	@Select("select count(*) from goods where goodsid=#{goodsId}")
	int isGoodsid(@Param("goodsId")String goodsId);
	
	
	
	//��ѯ��������
	@Select("select * from list")
	List<ListList> selectListList();
	
	//��ѯ���н�����
	@Select("select l.id,g.goodsid,g.goodsname,g.goodssort,l.state,l.number,l.price,date_format(l.time,'%Y-%m-%d') as time from goods as g,list as l where g.goodsid=l.goodsid")
	List<GoodsList> selectAllList();
	
	//��ѯ������
	@SelectProvider(type=SelectListProvider.class, method = "selectList")
	List<GoodsList> selectList(@Param("goods")String goods,@Param("frontTime")Date frontTime,@Param("endTime")Date endTime);
	
	//���ӽ�������Ϣ
	@Insert("insert into list values(null,#{list.goodsid},#{list.state},#{list.number},#{list.price},#{list.time})")
	void addList(@Param("list") ListList list);
	
	//�޸Ľ�������Ϣ
	@Update("update list set goodsid=#{list.goodsid},state=#{list.state},number=#{list.number},price=#{list.price},time=#{list.time} where id=#{list.id}")
	void updateList(@Param("list") ListList list);
		
	//ɾ����������Ϣ
	@Delete("delete from list where id=#{list.id}")
	void deleteList(@Param("list")ListList list);
	
	//�ı���Ʒ���
	@Update("update goods set number=#{number} where goodsid=#{goodsId}")
	void updateGoodsNumber(@Param("number")Integer number,@Param("goodsId")String goodsId);
	
	//��Ʒ�ܽ���
	@Select("select ifnull(SUM(number),0) from list where state='����' and goodsid=#{goodsId}")
	int inNumber(String goodsId);
	
	//��Ʒ�ܳ���
	@Select("select ifnull(SUM(number),0) from list where state='����' and goodsid=#{goodsId}")
	int outNumber(String goodsId);
	
	
	
	
	//��7�����۶�
	@Select("select IFNULL(b.sum,0) FROM( SELECT date_sub(curdate(), interval 6 day) as p union all SELECT date_sub(curdate(), interval 5 day) as p union all SELECT date_sub(curdate(), interval 4 day) as p union all SELECT date_sub(curdate(), interval 3 day) as p union all SELECT date_sub(curdate(), interval 2 day) as p union all SELECT date_sub(curdate(), interval 1 day) as p union all SELECT curdate() as p )a left join( select DATE(time) as datetime,SUM(price) as sum from list WHERE state='����' GROUP BY DATE(time)  ) b on a.p=b.datetime")
	List<Double> sevenDaysUnitprice();
	
	//��7��������
	@Select("select IFNULL(b.sum,0) FROM( SELECT date_sub(curdate(), interval 6 day) as p union all SELECT date_sub(curdate(), interval 5 day) as p union all SELECT date_sub(curdate(), interval 4 day) as p union all SELECT date_sub(curdate(), interval 3 day) as p union all SELECT date_sub(curdate(), interval 2 day) as p union all SELECT date_sub(curdate(), interval 1 day) as p union all SELECT curdate() as p )a left join( select DATE(time) as datetime,SUM(price) as sum from list WHERE state='����' GROUP BY DATE(time)  ) b on a.p=b.datetime")
	List<Double> sevenDaysInprice();
	
	//�����������۶�
	@Select("select IFNULL(b.sum,0) from( select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 5 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 4 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 3 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 2 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),'%y-%m') as p union all select DATE_FORMAT(CURDATE(),'%y-%m') as p ) as a LEFT JOIN( select DATE_FORMAT(time,'%y-%m') datetime,SUM(price) sum from list where state='����' GROUP BY DATE_FORMAT(time,'%y-%m') ) as b on a.p=b.datetime")
	List<Double> sixMonthsUnitprice();
	
	//�������½������
	@Select("select IFNULL(b.sum,0) from( select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 5 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 4 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 3 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 2 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),'%y-%m') as p union all select DATE_FORMAT(CURDATE(),'%y-%m') as p ) as a LEFT JOIN( select DATE_FORMAT(time,'%y-%m') datetime,SUM(price) sum from list where state='����' GROUP BY DATE_FORMAT(time,'%y-%m') ) as b on a.p=b.datetime")
	List<Double> sixMonthsInprice();
	
	//������鿴�������۶�
	@Select("select sum(price) as price,goodssort from list as l,goods as g where g.goodsid=l.goodsid and l.state='����' and time=DATE_FORMAT(NOW(),'%y-%m-%d') GROUP BY goodssort")
	List<PriceByGoodssort> dayPriceByGoodssort();
	
	//������鿴�������۶�
	@Select("select sum(price) as price,goodssort from list as l,goods as g where g.goodsid=l.goodsid and l.state='����' and DATE_FORMAT(time,'%y-%m')=DATE_FORMAT(NOW(),'%y-%m') GROUP BY goodssort")
	List<PriceByGoodssort> monthPriceByGoodssort();
	

	
}
