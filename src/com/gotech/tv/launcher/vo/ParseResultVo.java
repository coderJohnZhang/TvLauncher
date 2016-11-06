package com.gotech.tv.launcher.vo;

import java.util.List;

import android.text.TextUtils;

/**
 * 解析返回结果
 * 
 * @author meto
 * 
 */
public class ParseResultVo
{

	/**
	 * 返回结果 类型
	 */
	public int result;
	/**
	 * 返回结果 字串 （可选 ）
	 */
	public String reMsg;
	/**
	 * 返回结果集合 (可选)
	 */
	public List<?> list;
	/**
	 * 返回结果 总记录
	 */
	public int total;

	/**
	 * 对象属性
	 */
	public Object obj;

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String getReMsg()
	{
		return reMsg;
	}

	public void setReMsg(String reMsg)
	{
		this.reMsg = reMsg;
	}

	public List<?> getList()
	{
		return list;
	}

	public void setList(List<?> list)
	{
		this.list = list;
	}

	public Object getObj()
	{
		return obj;
	}

	public void setObj(Object obj)
	{
		this.obj = obj;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	@Override
	public String toString()
	{
		return buildString();
	}

	private String buildString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[result: " + result);
		if (!TextUtils.isEmpty(reMsg))
		{
			builder.append(", msg: " + reMsg);
		}
		builder.append("]@");
		builder.append(getClass().getSimpleName());
		builder.append("@");
		builder.append(Integer.toHexString(hashCode()));
		return builder.toString();
	}
}
