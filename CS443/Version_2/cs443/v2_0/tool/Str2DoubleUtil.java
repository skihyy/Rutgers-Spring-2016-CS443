package cs443.v2_0.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YuyangHE
 * @date 2016年4月3日
 * @version 1.0
 * @since 2016年4月3日
 */
public class Str2DoubleUtil
{
	/**
	 * This function can transform a string into a number (double). If the
	 * string is in the wrong format, null will be returned.
	 * 
	 * @param str
	 *            input string
	 * @return number from the string, or null if wrong format
	 */
	public static Double Str2Double(String str)
	{
		if (null == str)
		{
			return null;
		}

		str = str.trim();

		double result = 0;

		try
		{
			result = Double.parseDouble(str);
		}
		catch (NumberFormatException e)
		{
			try
			{
				result = Integer.parseInt(str) * 1.0;
			}
			catch (NumberFormatException e2)
			{
				return null;
			}
		}

		return result;
	}

	/**
	 * This function can transform a string into a map <ID, weight>.
	 * 
	 * @param str
	 *            input string
	 * @return map a map or null if the string is null
	 */
	public static Map<Integer, Double> Str2Map(String str)
	{
		if (null == str)
		{
			return null;
		}

		String[] tmp = str.trim().split(" ");

		if (2 != tmp.length)
		{
			return null;
		}

		Map<Integer, Double> result = new HashMap<Integer, Double>();

		int key = Str2Double(tmp[0]).intValue();

		Double value = Str2Double(tmp[1]);

		if (null == value)
		{
			return null;
		}

		result.put(key, value);

		return result;
	}
}
