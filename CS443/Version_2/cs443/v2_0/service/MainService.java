package cs443.v2_0.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs443.v2_0.tool.SpiketrainGenerator;
import cs443.v2_0.vo.Constant;
import cs443.v2_1.dao.impl.ComparingNeuronImpl;
import cs443.v2_1.dao.impl.RepresentingNeuronImpl;

/**
 * @author YuyangHE
 * @date 2016年4月3日
 * @version 1.0
 * @since 2016年4月3日
 */
public class MainService
{
	public static List<RepresentingNeuronImpl> representingNeurons = new ArrayList<RepresentingNeuronImpl>();
	public static List<ComparingNeuronImpl> comparingNeurons = new ArrayList<ComparingNeuronImpl>();
	
	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		Double currentTime = 0.0, number1 = -1.0, number2 = -1.0;;
		int count = 0;

		representingNeurons.add(new RepresentingNeuronImpl(1));
		representingNeurons.add(new RepresentingNeuronImpl(2));
		comparingNeurons.add(new ComparingNeuronImpl(3));
		comparingNeurons.add(new ComparingNeuronImpl(4));

		Random ramdon = new Random((long) Math.pow(System.currentTimeMillis(), Math.PI));

		while (true)
		{
			if (0 == count % 10)
			{
				number1 = ramdon.nextDouble() * 10;
				number2 = ramdon.nextDouble() * 10;
				
				while(number1 < 1)
				{
					number1 *= 10;
				}
				
				while(number2 < 1)
				{
					number2 *= 10;
				}

				System.out.println("----------------------------------------");
				System.out.println("Number 1 is " + number1);
				System.out.println("Number 2 is " + number2);
				
				representingNeurons.get(0).setSpikeTrain(SpiketrainGenerator.num2SpikeTrain(number1));
				representingNeurons.get(1).setSpikeTrain(SpiketrainGenerator.num2SpikeTrain(number2));
			}
			
			for (RepresentingNeuronImpl representingNeuron : representingNeurons)
			{
				representingNeuron.update(Constant.TIME_DELAY_SEC);
			}

			for (ComparingNeuronImpl comparingNeuron : comparingNeurons)
			{
				comparingNeuron.update(Constant.TIME_DELAY_SEC);
			}

			currentTime += Constant.TIME_DELAY_SEC;
			++count;

			try
			{
				Thread.currentThread().sleep((long) Constant.TIME_DELAY_MSEC);
				//Thread.currentThread().sleep(3000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
