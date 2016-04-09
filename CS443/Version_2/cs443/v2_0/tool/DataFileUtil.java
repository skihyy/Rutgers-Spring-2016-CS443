package cs443.v2_0.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YuyangHE
 * @date 2016年4月9日
 * @version 1.0
 * @since 2016年4月9日
 */
public class DataFileUtil
{
	public static Map<Integer, Double> readConnectionWeight(File connectionWeightFile)
	{
		Map<Integer, Double> connectionWeightMap = new HashMap<Integer, Double>();
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		String tmpReading = null;
		Map<Integer, Double> tmpWeightMap = null;

		try
		{
			fis = new FileInputStream(connectionWeightFile);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			while (null != (tmpReading = br.readLine()))
			{
				tmpWeightMap = Str2DoubleUtil.Str2Map(tmpReading);

				if (null == tmpWeightMap || tmpWeightMap.isEmpty())
				{
					continue;
				}
				
				for(int key : tmpWeightMap.keySet())
				{
					if(connectionWeightMap.containsKey(key))
					{
						continue;
					}
				}

				connectionWeightMap.putAll(tmpWeightMap);

				tmpWeightMap = null;
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Connection weight file is not exist. Quit.");
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			System.out.println("Reading error");
			e.printStackTrace();
		}
		finally
		{
			CloseUtil.closeAll(br, isr, fis);
		}
		
		return connectionWeightMap;
	}
}
