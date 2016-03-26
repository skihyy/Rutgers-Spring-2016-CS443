package cs443.v1_0.vo;

import java.util.Map;

/**
 * The VO of a neuron, containing spiking rate and neuron type. IMPORTANT: In
 * the first version, the weight is given to the neuron other than the
 * connection so that the weight can represent the "capability" of sorting of a
 * neuron.
 * 
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 1.0
 * @since 2016年3月26日
 */
public class Neuron
{
	protected int id;

	protected double spikingRate = Constant.INITIAL_SPIKING_RATE;

	/**
	 * self-weight
	 */
	protected int weight;

	/**
	 * the weight map of other neurons map is <ID, weight>
	 */
	protected Map<Integer, Integer> weightMap = null;

	/**
	 * Getter of id.
	 * 
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Setter of id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Getter of spikingRate.
	 * 
	 * @return the spikingRate
	 */
	public double getSpikingRate()
	{
		return spikingRate;
	}

	/**
	 * Setter of spikingRate.
	 * 
	 * @param spikingRate
	 *            the spikingRate to set
	 */
	public void setSpikingRate(double spikingRate)
	{
		this.spikingRate = spikingRate;
	}

	/**
	 * Getter of weight.
	 * 
	 * @return the weight
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * Setter of weight.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWight(int weight)
	{
		this.weight = weight;
	}

	/**
	 * Getter of weightMap.
	 * 
	 * @return the weightMap
	 */
	public Map<Integer, Integer> getWeightMap()
	{
		return weightMap;
	}

	/**
	 * Setter of weightMap.
	 * 
	 * @param weightMap
	 *            the weightMap to set
	 */
	public void setWeightMap(Map<Integer, Integer> weightMap)
	{
		this.weightMap = weightMap;
	}
}
