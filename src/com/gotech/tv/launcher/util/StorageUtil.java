package com.gotech.tv.launcher.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

public class StorageUtil
{

	public static String TAG = "StorageUtil";

	private static StorageUtil instance;

	/**
	 * singelten
	 * 
	 * @return
	 */
	public static StorageUtil getInstance()
	{
		if (instance == null)
			instance = new StorageUtil();
		return instance;
	}

	private static final int ERROR = -1;

	// 获得可用的内存
	public long getmem_UNUSED(Context mContext)
	{
		long MEM_UNUSED;
		// 得到ActivityManager
		ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);

		// 创建ActivityManager.MemoryInfo对象

		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(mi);

		// 取得剩余的内存空间

		MEM_UNUSED = mi.availMem;
		return MEM_UNUSED;
	}

	// 获得总内存
	public long getmem_TOLAL()
	{
		long mTotal;
		// /proc/meminfo读出的内核信息进行解释
		String path = "/proc/meminfo";
		String content = null;
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null)
			{
				content = line;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		// beginIndex
		int begin = content.indexOf(':');
		// endIndex
		int end = content.indexOf('k');
		// 截取字符串信息

		content = content.substring(begin + 1, end).trim();
		mTotal = Integer.parseInt(content) * 1024;
		return mTotal;
	}

	/**
	 * SDCARD是否存
	 */
	public boolean externalMemoryAvailable()
	{
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取手机内部剩余存储空间
	 * 
	 * @return
	 */
	public long getAvailableInternalMemorySize()
	{
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 获取手机内部总的存储空间
	 * 
	 * @return
	 */
	public long getTotalInternalMemorySize()
	{
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * 获取SDCARD剩余存储空间
	 * 
	 * @return
	 */
	public long getAvailableExternalMemorySize()
	{
		if (externalMemoryAvailable())
		{
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		}
		else
		{
			return ERROR;
		}
	}

	/**
	 * 获取SDCARD总的存储空间
	 * 
	 * @return
	 */
	public long getTotalExternalMemorySize()
	{
		if (externalMemoryAvailable())
		{
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		}
		else
		{
			return ERROR;
		}
	}
}
