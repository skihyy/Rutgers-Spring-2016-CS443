package cs443.v2_0.tool;

import java.util.ArrayList;
import java.util.List;

import cs443.v2_0.vo.Spike;

/**
 * A function utility that transfer a number into a spike train.
 * 
 * @author YuyangHE
 * @date 2016年4月9日
 * @version 1.0
 * @since 2016年4月9日
 */
public class SpiketrainGenerator
{
	public static List<Spike> num2SpikeTrain(Double number)
	{
		if(null == number)
		{
			return null;
		}
		
		double initialWeight = 3;
		
		List<Spike> spikeTrain = new ArrayList<Spike>();
		
		int intValue = number.intValue(), floatValue = (int) number.floatValue() * 1000;
		
		String biInt = Integer.toBinaryString(intValue),
				biFloat = Integer.toBinaryString(floatValue);
		
		char [] charInt = biInt.toCharArray(), charFloat = biFloat.toCharArray();
		
		for(char ch : charInt)
		{
			if('0' == ch)
			{
				spikeTrain.add(new Spike(false,0));
			}
			else
			{
				spikeTrain.add(new Spike(true,initialWeight));
				initialWeight /= 2;
			}
		}
		
		for(char ch : charFloat)
		{
			if('0' == ch)
			{
				spikeTrain.add(new Spike(false,0));
			}
			else
			{
				spikeTrain.add(new Spike(true,initialWeight));
				initialWeight /= 2;
			}
		}
		
		return spikeTrain;
	}
}
