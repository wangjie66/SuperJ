/**	
 * <br>
 * Copyright 2011 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.gwrq.gwrqtj <br>
 * FileName: AnalyseHqlMaker.java <br>
 * <br>
 * @version
 * @author hyzha@iflytek.com
 * @created 2013-5-16
 * @last Modified 
 * @history
 */
package utils;

import com.iflytek.seclib.SecLib;
import com.iflytek.seclib.sqli.SQLHelper.PieceType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 分析HQL构造类
 * 
 * @author hyzha@iflytek.com
 * @lastModified
 * @history
 */
public class AnalyseHqlMaker {

	/**
	 * logger
	 */
	protected final static Log logger = LogFactory.getLog(AnalyseHqlMaker.class);

	/**
	 * escape
	 */
	private static final String ESCAPE_CONSTANT = "'/'";

	/**
	 * repalce
	 */
	private static final String ESCAPE_CONSTANT_REPLACE = "/";

	/**
	 * 替换非法字符
	 * 
	 * @param str
	 * @return
	 * @author sbwang@iflytek.com
	 * @created 2013-5-29 下午09:49:10
	 * @lastModified
	 * @history
	 */
	public static String replace(String str) {
		if (StringUtils.isNotEmpty(str)) {
			str = str.replace("'", "''").replace("/", ESCAPE_CONSTANT_REPLACE + "/")
					.replace("%", ESCAPE_CONSTANT_REPLACE + "%").replace("\\", ESCAPE_CONSTANT_REPLACE + "\\")
					.replace("_", ESCAPE_CONSTANT_REPLACE + "_");
		}
		return str;
	}

	/**
	 * 
	 * hql like 模糊查询
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 安全过滤后的SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:23:56
	 * @lastModified
	 * @history
	 */
	public static String popuHqlLike(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			param = AnalyseHqlMaker.replace(param);
			sb.append(" and ").append(column).append(" like '%").append("$StringValue$").append("%' escape '\\' ");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param.trim());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlLike", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * like防sql注入
	 * 
	 * @param name
	 *            字段名称
	 * @param val
	 *            字段值
	 * @param values
	 *            值集合
	 * @return
	 * @author rhzhao
	 * @created 2015年11月10日 下午10:34:43
	 * @lastModified
	 * @history
	 */
	public static String popuHqlLike(String name, String val, Map<String, Object> values) {
		if (StringUtils.isNotBlank(val)) {
			val = AnalyseHqlMaker.replace(val);
			try {
				values.put(name, "%" + SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val)) + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlLike", e);
			}
			return " and " + name + " like :" + name + "   escape  " + ESCAPE_CONSTANT;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * like防sql注入
	 * 
	 * @param name
	 *            字段名称
	 * @param val
	 *            字段值
	 * @param values
	 *            值集合
	 * @return
	 * @author rhzhao
	 * @throws SQLException
	 * @created 2015年11月10日 下午10:34:43
	 * @lastModified
	 * @history
	 */
	public static String popuHqlLike(String name, String val, List<Object> values) {
		if (StringUtils.isNotBlank(val)) {
			val = AnalyseHqlMaker.replace(val);
			try {
				values.add("%" + SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val)) + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlLike", e);
			}
			return " and " + name + " like ?   ";
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 
	 * 或like模糊检索</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:25:05
	 * @lastModified
	 * @history
	 */
	public static String popuHqlOrLike(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			param = AnalyseHqlMaker.replace(param);
			sb.append(" or ").append(column).append(" like '%").append("$StringValue$").append("%' escape '\\' ");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param.trim());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlLike", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * like防sql注入
	 * 
	 * @param name
	 *            字段名称
	 * @param val
	 *            字段值
	 * @param values
	 *            值集合
	 * @return
	 * @author rhzhao
	 * @throws SQLException
	 * @created 2015年11月10日 下午10:34:43
	 * @lastModified
	 * @history
	 */
	public static String popuHqlLikeLeft(String name, String val, Map<String, Object> values) {
		if (StringUtils.isNotBlank(val)) {
			val = AnalyseHqlMaker.replace(val);
			try {
				values.put(name, SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val)) + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlLikeLeft", e);
			}
			return " and " + name + " like :" + name + "   ";
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 数组模糊匹配
	 *
	 * @author: xzran
	 * @createTime: 2017年8月15日 下午8:41:06
	 * @history:
	 * @param name
	 * @param val
	 * @param values
	 * @return String
	 */
	public static String popuHqlLikeOr(String name, String vals, Map<String, Object> values) {
		String sql = "";
		if (StringUtils.isNotBlank(vals)) {
			String[] arrs = vals.split(",");
			for (int i = 0; i < arrs.length; i++) {
				values.put(name + i, "%" + arrs[i] + "%");
				if (i == 0) {
					sql = " and ( " + name + " like :" + name + i + " ";
				} else {
					sql = sql + " or " + name + " like :" + name + i + " ";
				}

			}
			if (StringUtils.isNotBlank(sql)) {
				sql += " )";
			}
			return sql;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 数组精确查询
	 *
	 * @author: xzran
	 * @createTime: 2017年8月15日 下午8:41:06
	 * @history:
	 * @param name
	 * @param val
	 * @param values
	 * @return String
	 */
	public static String popuHqlEqOr(String name, String vals, Map<String, Object> values) {
		String sql = "";
		if (StringUtils.isNotBlank(vals)) {
			String[] arrs = vals.split(",");
			for (int i = 0; i < arrs.length; i++) {
				values.put(name+i, arrs[i]);
				if(i == 0){
					sql = " and ( " + name + " = :"+name+i+" ";
				}else {
					sql = sql + " or " + name + " = :"+name+i+" ";
				}

			}
			if (StringUtils.isNotBlank(sql)) {
				sql += " )";
			}
			return sql;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * equal防sql注入
	 * 
	 * @param name
	 *            字段名称
	 * @param val
	 *            字段值
	 * @param values
	 *            值集合
	 * @return
	 * @author rhzhao
	 * @created 2015年11月10日 下午10:39:48
	 * @lastModified
	 * @history
	 */
	public static String popuHqlEq(String name, String val, Map<String, Object> values) {
		if (StringUtils.isNotBlank(val)) {
			try {
				values.put(name, SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlEq", e);
			}
			return " and " + name + " = :" + name + " ";
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 *  equal
	 *  @param name
	 *  @param val
	 *  @param values
	 *  @return
	 *  @author fgwu
	 *  @created 2017年12月4日 下午7:58:35
	 *  @lastModified
	 *  @history
	 */
	public static String popuHqlEqual(String name, String val,
                                      Map<String, Object> values) {
		if(StringUtils.isNotBlank(val)){
			values.put(name, StringUtils.trim(val));
			return " and "+name+" = :"+name +" ";
		}
		return "";
	}
	
	/**
	 *  equal防sql注入
	 *  @param name 字段名称
	 *  @param val 字段值 
	 *  @param values 值集合
	 *  @return
	 *  @author rhzhao
	 *  @created 2015年11月18日 上午11:07:05
	 *  @lastModified       
	 *  @history           
	 */
	public static String popuHqlEq(String name, String val,
                                   List<Object> values) {
		try {
			if(StringUtils.isNotBlank(val)){
				values.add(SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val)));
				return " and "+name+" = ? ";
			}
		} catch (Exception e) {
			logger.error("popuHqlEq", e);
		}
		return StringUtils.EMPTY;
	}
	

	/**
	 * equal防sql注入
	 * 
	 * @param name
	 *            字段名称
	 * @param val
	 *            字段值
	 * @param values
	 *            值集合
	 * @return
	 * @author rhzhao
	 * @throws SQLException
	 * @created 2015年11月10日 下午10:39:48
	 * @lastModified
	 * @history
	 */
	public static String popuHqlEqLowerCase(String name, String val, Map<String, Object> values) {
		if (StringUtils.isNotBlank(val)) {
			try {
				values.put(name, SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlEqLowerCase", e);
			}
			return " and LOWER(" + name + ") = :" + name + " ";
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 
	 * 精确查询</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 安全过滤后的SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:26:32
	 * @lastModified
	 * @history
	 */
	public static String popuHqlEq(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" =\'").append("$StringValue$").append("\'");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlEq", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * 
	 * 精确不等于查询</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:27:25
	 * @lastModified
	 * @history
	 */
	public static String popuHqlNotEq(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" <> \'").append("$StringValue$").append("\'");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlNotEq", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * 
	 * 字符串in查询
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:28:17
	 * @lastModified
	 * @history
	 */
	public static String popuHqlIn(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" in ( ").append("$StringValue$").append(")");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlIn", e);
			}
			return sql;
		}
		return sb.toString();
	}
	
	/**
     * in查询
     * 
     * @param name
     *            字段名称
     * @param val
     *            字段值
     * @param values
     *            值集合
     * @return
     * @author fangwang6
     * @throws ParseException
     * @created 2017年10月14日 下午1:36:23
     * @lastModified
     * @history
     */
	public static String popuHqlIn(String name, List<String> val,
                                   Map<String, Object> values) throws ParseException {
        if(val!=null&&val.size()>0){
                values.put(name, val);
                return " and "+name+" in( :"+name+") ";
         }
        return "";
    }

	/**
	 * 
	 * 整型in查询</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:29:25
	 * @lastModified
	 * @history
	 */
	public static String popuHqlInInt(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" in ( ").append("$NumericValue$").append(")");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("exception", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * 
	 * 字符串not in 查询</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:30:38
	 * @lastModified
	 * @history
	 */
	public static String popuHqlNotIn(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" not in ( ").append("$StringValue$").append(")");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlNotIn", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * 
	 * 字符串时间大于等于查询</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:31:08
	 * @lastModified
	 * @history
	 */
	public static String popuBeginStringDateHql(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" >=\'").append("$StringValue$").append("\'");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuBeginStringDateHql", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * 
	 * 字符串时间小于等于查询</br>
	 * 
	 * @param column
	 *            列名
	 * @param param
	 *            参数值
	 * @return 过滤后的安全SQL
	 * @author llchen
	 * @created 2017年8月15日 下午11:31:53
	 * @lastModified
	 * @history
	 */
	public static String popuEndStringDateHql(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(param)) {
			sb.append(" and ").append(column).append(" <=\'").append("$StringValue$").append("\'");
			String sql = sb.toString();
			try {
				sql = SecLib.sqli.createSafeSQL(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuEndStringDateHql", e);
			}
			return sql;
		}
		return sb.toString();
	}

	/**
	 * 时间开始
	 *
	 * @author: xzran
	 * @createTime: 2017年5月17日 下午7:27:09
	 * @history:
	 * @param name
	 * @param val
	 * @param values
	 * @return String
	 */
	public static String popuHqlStartTime(String name, String val, Map<String, Object> values) {
		String start = "start";
		if (StringUtils.isNotBlank(val)) {
			// val = AnalyseHqlMaker.replace(val);
			val = DateUtils.formatStartDate(val);
			values.put(start, val);
			return " and " + name + " >=  :" + start;
		}
		return StringUtils.EMPTY;
	}

	/**
	 *  时间开始(去除特殊符号)
	 *  @param name
	 *  @param val
	 *  @param values
	 *  @return
	 *  @author mhyang@iflytek
	 *  @created 2017年10月26日 上午10:18:40
	 *  @lastModified
	 *  @history
	 */
	public static String popuHqlStartTimeRemoveSym(String name, String val, Map<String, Object> values) {
		String start = "start";
		if (StringUtils.isNotBlank(val)) {
			// val = AnalyseHqlMaker.replace(val);
			val = DateUtils.formatDateTime(val);
			values.put(start, val);
			return " and " + name + " >=  :" + start;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 时间结束
	 *
	 * @author: xzran
	 * @createTime: 2017年5月17日 下午7:27:09
	 * @history:
	 * @param name
	 * @param val
	 * @param values
	 * @return String
	 */
	public static String popuHqlEndTime(String name, String val, Map<String, Object> values) {
		String end = "end";
		if (StringUtils.isNotBlank(val)) {
			// val = AnalyseHqlMaker.replace(val);
			val = DateUtils.formatEndDate(val);
			values.put(end, val);
			return " and " + name + " <=  :" + end;
		}
		return StringUtils.EMPTY;
	}

	/**
	 *  时间结束(去除特殊符号)
	 *  @param name
	 *  @param val
	 *  @param values
	 *  @return
	 *  @author mhyang@iflytek
	 *  @created 2017年10月26日 上午10:20:57
	 *  @lastModified
	 *  @history
	 */
	public static String popuHqlEndTimeRemoveSym(String name, String val, Map<String, Object> values) {
		String end = "end";
		if (StringUtils.isNotBlank(val)) {
			// val = AnalyseHqlMaker.replace(val);
			val = DateUtils.formatDateTime(val);
			values.put(end, val);
			return " and " + name + " <=  :" + end;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	* 不同字段，like同一个值
	* @author: lwsong
	* @createTime: 2017年8月22日 下午5:04:06
	* @history:
	* @param name
	* @param vals
	* @param values
	* @return String
	 */
	public static String popuHqlOrLike(String name1, String name2, String vals, Map<String, Object> values) {
		String sql = "";
		if (StringUtils.isNotBlank(vals)) {
			try {
				values.put(name1, SecLib.sqli.getSafeValue(PieceType.StringValue, "%"+StringUtils.trim(vals)+"%"));
			} catch (SQLException e) {
				logger.error("popuHqlOrLike", e);
			}
			sql+=" and ( "+name1 +" like  :"+name1+" or "+name2 +" like :"+name1+" )";
			return sql;
		}
		return StringUtils.EMPTY;
	}
	
	public static String popuHqlOrLikeName(String names, String val, Map<String, Object> values) {
		String sql = "";
		try {
			if (StringUtils.isNotBlank(val)) {
				val=replace(val);
				if(StringUtils.isNotBlank(names)){
					String[] nameArr=names.split(",");
					sql+=" and ( ";
					int index=0;
					for(String name : nameArr){
						if(index==0){
							sql+= name +" like  :"+name+ " escape " +ESCAPE_CONSTANT;
						}else{
							sql+=" or " + name +" like :"+name +" escape  " +ESCAPE_CONSTANT ;
						}
						index++;
						values.put(name, SecLib.sqli.getSafeValue(PieceType.StringValue, "%"+StringUtils.trim(val)+"%"));
					}
					sql+=" ) ";
				}
				return sql;
			}
		} catch (SQLException e) {
			logger.error("popuHqlOrLike", e);
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 
	 *  加工得到安全的值
	 *  @param pieceType 类型
	 *  @param unSafeValue 不安全的值
	 *  @return 安全的值
	 *  @author llchen
	 *  @created 2017年9月8日 上午11:36:56
	 *  @lastModified
	 *  @history
	 */
	@SuppressWarnings("deprecation")
	public static String getSafeValue(PieceType pieceType, String unSafeValue){
		try {
			return SecLib.sqli.getSafeValue(pieceType, StringUtils.trim(unSafeValue));
		} catch (Exception e) {
			logger.error("getSafeValue", e);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 增加排序字段
	 * @Author : JieWang
	 * @Date : Created in 2017/10/17 11:39
	 * @Email : wjahstu@163.com
	 */
	public static String popuHqlOrder(String orderBy, String order) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order)) {
			sb.append(" order by ").append(orderBy).append(" ").append(order) ;
			String sql = sb.toString();
			return sql;
		}
		return sb.toString();
	}

	/**
	 * @Author : JieWang
	 * @Date : Created in 2017/10/23 9:49
	 * @Email : wjahstu@163.com
	 * or的方法和上面区别，names是多个，值是同一个的情况
	 */
	public static String popuHqlEqOrForSameCoulums(String names, String val, Map<String, Object> values) {
		String sql = "";
		if (StringUtils.isNotBlank(val)) {
			String[] arrs = names.split(",");
			for (int i = 0; i < arrs.length; i++) {
				values.put(arrs[i], val);
				if(i == 0){
					sql = " and ( " + arrs[i] + " = :"+arrs[i]+" ";
				}else {
					sql = sql + " or " + arrs[i] + " = :"+arrs[i]+" ";
				}

			}
			if (StringUtils.isNotBlank(sql)) {
				sql += " )";
			}
			return sql;
		}
		return StringUtils.EMPTY;
	}

	public static String popuHqlBetween(String name, String val1, String val2 , Map<String, Object> values) {
		if (StringUtils.isNotBlank(val1) && StringUtils.isNotBlank(val2)) {
			try {
				values.put(name+"1", SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val1)));
				values.put(name+"2", SecLib.sqli.getSafeValue(PieceType.StringValue, StringUtils.trim(val2)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("popuHqlEq", e);
			}
			return " and " + name + " between :" + name+"1" + " and :"+name+"2";
		}
		return StringUtils.EMPTY;
	}
}