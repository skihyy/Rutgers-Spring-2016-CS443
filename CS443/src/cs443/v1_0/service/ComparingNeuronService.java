package cs443.v1_0.service;

import cs443.v1_0.dao.NeuronFunctionDao;
import cs443.v1_0.vo.Constant;
import cs443.v1_0.vo.Neuron;

/**
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 1.0
 * @since 2016年3月26日
 */
public class ComparingNeuronService extends Neuron implements NeuronFunctionDao
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v1_0.dao.NeuronFunctionDao#firing(java.lang.Object)
	 */
	@Override
	public void firing(Object... o)
	{
		double incomingSpike = Constant.INITIAL_SPIKING_RATE;

		for (Object obj : o)
		{
			if (obj instanceof Double || obj instanceof Integer)
			{
				try
				{
					incomingSpike = (double) obj;
				}
				catch (ClassCastException e)
				{
					incomingSpike = ((Integer) obj) * 1.0;
				}

				if (Constant.INITIAL_SPIKING_RATE == this.spikingRate)
				{
					this.spikingRate = incomingSpike;
				}
				else if (this.spikingRate < incomingSpike)
				{
					sendingInfo();
					this.spikingRate = incomingSpike;
					sendingInfo();
				}
				else
				{
					sendingInfo(incomingSpike);
					sendingInfo();
				}

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v1_0.dao.NeuronFunctionDao#sendingInfo(java.lang.Object[])
	 */
	@Override
	public Object sendingInfo(Object... o)
	{
		if (null != o && 0 < o.length)
		{
			for (Object obj : o)
			{
				System.out.println(obj);
			}
			return o;
		}
		else
		{
			System.out.println(spikingRate);
			return this.spikingRate;
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Spiking Rate: ");
		sb.append(this.spikingRate);
		sb.append("\n");
		return sb.toString();
	}
}
