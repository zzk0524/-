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

	//查询全部账号
	@Select("select * from accounts")
	List<Account> selectAccount();
	
	
	//查询密码
	@Select("select * from accounts where account=#{account}")
	Account selectPassword(String account);

	
	
	//修改密码
	@Update("update accounts set password=#{password} where account=#{account}")
	void updatePassword(@Param("account")String account,@Param("password")String password);
	
	
	
	//查询货物表
	@Select("select * from goods")
	List<Goods> selectGoods();
	
	//查询所有货物
	@Select("select * from goods where state=1")
	List<Goods> selectAllGoods();
	
	//按商品编号查询货物
	@Select("select * from goods where goodsid=#{goodsId} and state=1")
	List<Goods> selectGoodsByGoodsid(String goodsId);
	
	//按商品名称查询货物
	@Select("select * from goods where goodsname like '%${goodsName}%' and state=1")
	List<Goods> selectGoodsByGoodsname(@Param("goodsName")String goodsName);
	
	//按商品类型查询货物
	@Select("select * from goods where goodssort=#{sort} and state=1")
	List<Goods> selectGoodsByGoodssort(String sort);
	
	//增加货物信息
	@Insert("insert into goods values(null,#{goods.goodsid},#{goods.goodsname},#{goods.goodssort},#{goods.inprice},#{goods.unitprice},0,1)")
	void addGoods(@Param("goods")Goods goods);
	
	//修改货物信息
	@Update("update goods set goodsid=#{goods.goodsid},goodsname=#{goods.goodsname},goodssort=#{goods.goodssort},inprice=#{goods.inprice},unitprice=#{goods.unitprice},number=#{goods.number} where id=#{goods.id}")
	void updateGoods(@Param("goods")Goods goods);
	
	//删除货物信息
	@Update("update goods set state=0 where id=#{id}")
	void deleteGoodsById(int id);
	
	//查询商品编号是否重复
	@Select("select count(*) from goods where goodsid=#{goodsId}")
	int isGoodsid(@Param("goodsId")String goodsId);
	
	
	
	//查询进出货表
	@Select("select * from list")
	List<ListList> selectListList();
	
	//查询所有进出货
	@Select("select l.id,g.goodsid,g.goodsname,g.goodssort,l.state,l.number,l.price,date_format(l.time,'%Y-%m-%d') as time from goods as g,list as l where g.goodsid=l.goodsid")
	List<GoodsList> selectAllList();
	
	//查询进出货
	@SelectProvider(type=SelectListProvider.class, method = "selectList")
	List<GoodsList> selectList(@Param("goods")String goods,@Param("frontTime")Date frontTime,@Param("endTime")Date endTime);
	
	//增加进出货信息
	@Insert("insert into list values(null,#{list.goodsid},#{list.state},#{list.number},#{list.price},#{list.time})")
	void addList(@Param("list") ListList list);
	
	//修改进出货信息
	@Update("update list set goodsid=#{list.goodsid},state=#{list.state},number=#{list.number},price=#{list.price},time=#{list.time} where id=#{list.id}")
	void updateList(@Param("list") ListList list);
		
	//删除进出货信息
	@Delete("delete from list where id=#{list.id}")
	void deleteList(@Param("list")ListList list);
	
	//改变商品库存
	@Update("update goods set number=#{number} where goodsid=#{goodsId}")
	void updateGoodsNumber(@Param("number")Integer number,@Param("goodsId")String goodsId);
	
	//商品总进货
	@Select("select ifnull(SUM(number),0) from list where state='进货' and goodsid=#{goodsId}")
	int inNumber(String goodsId);
	
	//商品总出货
	@Select("select ifnull(SUM(number),0) from list where state='出货' and goodsid=#{goodsId}")
	int outNumber(String goodsId);
	
	
	
	
	//近7天销售额
	@Select("select IFNULL(b.sum,0) FROM( SELECT date_sub(curdate(), interval 6 day) as p union all SELECT date_sub(curdate(), interval 5 day) as p union all SELECT date_sub(curdate(), interval 4 day) as p union all SELECT date_sub(curdate(), interval 3 day) as p union all SELECT date_sub(curdate(), interval 2 day) as p union all SELECT date_sub(curdate(), interval 1 day) as p union all SELECT curdate() as p )a left join( select DATE(time) as datetime,SUM(price) as sum from list WHERE state='出货' GROUP BY DATE(time)  ) b on a.p=b.datetime")
	List<Double> sevenDaysUnitprice();
	
	//近7天进货金额
	@Select("select IFNULL(b.sum,0) FROM( SELECT date_sub(curdate(), interval 6 day) as p union all SELECT date_sub(curdate(), interval 5 day) as p union all SELECT date_sub(curdate(), interval 4 day) as p union all SELECT date_sub(curdate(), interval 3 day) as p union all SELECT date_sub(curdate(), interval 2 day) as p union all SELECT date_sub(curdate(), interval 1 day) as p union all SELECT curdate() as p )a left join( select DATE(time) as datetime,SUM(price) as sum from list WHERE state='进货' GROUP BY DATE(time)  ) b on a.p=b.datetime")
	List<Double> sevenDaysInprice();
	
	//近六个月销售额
	@Select("select IFNULL(b.sum,0) from( select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 5 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 4 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 3 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 2 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),'%y-%m') as p union all select DATE_FORMAT(CURDATE(),'%y-%m') as p ) as a LEFT JOIN( select DATE_FORMAT(time,'%y-%m') datetime,SUM(price) sum from list where state='出货' GROUP BY DATE_FORMAT(time,'%y-%m') ) as b on a.p=b.datetime")
	List<Double> sixMonthsUnitprice();
	
	//近六个月进货金额
	@Select("select IFNULL(b.sum,0) from( select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 5 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 4 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 3 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 2 MONTH),'%y-%m') as p union all select DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),'%y-%m') as p union all select DATE_FORMAT(CURDATE(),'%y-%m') as p ) as a LEFT JOIN( select DATE_FORMAT(time,'%y-%m') datetime,SUM(price) sum from list where state='进货' GROUP BY DATE_FORMAT(time,'%y-%m') ) as b on a.p=b.datetime")
	List<Double> sixMonthsInprice();
	
	//按分类查看本日销售额
	@Select("select sum(price) as price,goodssort from list as l,goods as g where g.goodsid=l.goodsid and l.state='出货' and time=DATE_FORMAT(NOW(),'%y-%m-%d') GROUP BY goodssort")
	List<PriceByGoodssort> dayPriceByGoodssort();
	
	//按分类查看本月销售额
	@Select("select sum(price) as price,goodssort from list as l,goods as g where g.goodsid=l.goodsid and l.state='出货' and DATE_FORMAT(time,'%y-%m')=DATE_FORMAT(NOW(),'%y-%m') GROUP BY goodssort")
	List<PriceByGoodssort> monthPriceByGoodssort();
	

	
}
