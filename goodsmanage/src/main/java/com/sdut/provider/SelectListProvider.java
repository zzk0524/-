package com.sdut.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SelectListProvider {

	public String selectList(Map<?,?> map){
		
		SQL sql=new SQL();
		sql.SELECT("l.id,g.goodsid,g.goodsname,g.goodssort,l.state,l.number,l.price,l.time").FROM("goods as g,list as l");
		sql.WHERE(" g.goodsid=l.goodsid");
		sql.WHERE(" g.state=1");
		if(map.get("frontTime")!=null&&!map.get("frontTime").equals("")&&map.get("endTime")!=null&&!map.get("endTime").equals("")){
			sql.WHERE(" l.time>=#{frontTime}");
			sql.WHERE(" l.time<=#{endTime}");
		}
		if(map.get("goods")!=null&&!map.get("goods").equals("")){
			if(diff(map.get("goods").toString())==1){
	            sql.WHERE(" g.goodsid = "+map.get("goods"));
			}
			if(diff(map.get("goods").toString())==2){
				System.out.println(diff(map.get("goods").toString()));
	            sql.WHERE(" g.goodssort = '"+map.get("goods")+"'");
			}
			if(diff(map.get("goods").toString())==3){
				String goods ="'%"+map.get("goods")+"%'";
	            sql.WHERE(" g.goodsname like "+goods);
			}
		}
		
		return sql.toString();
	}
	public static int diff(String goods){

		if(goods.length()==3&&Character.isDigit(goods.charAt(0))&&Character.isDigit(goods.charAt(1))&&Character.isDigit(goods.charAt(2))){
			return 1;
		}else if(goods.equals("食品")||goods.equals("饮品")||goods.equals("生鲜")||goods.equals("烟酒")||goods.equals("百货")||goods.equals("数码")||goods.equals("服装")){
			return 2;
		}else{
			return 3;
		}
	}
}
