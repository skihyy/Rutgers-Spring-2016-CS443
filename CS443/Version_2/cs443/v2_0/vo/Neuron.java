package cs443.v2_0.vo;

import java.util.Map;

/**
 * Compared to version 1, in this version, a neuron will be more likely a real
 * neuron. It has reseting potential, threshold for firing. And to better
 * simulate the model, all data except id are specified in double instead of
 * int. Self-weight is changed to capability. weightMap has been changed to
 * connectionWeightMap. In order to avoid concurrency, every neuron will save
 * its own data in its own file instead of sharing a file.
 * 
 * @author YuyangHE
 * @date 2016年4月3日
 * @version 2.0
 * @since 2016年4月3日
 */
public class Neuron
{
	protected int id;

	protected double resetingPotential;

	protected double threshold;

	protected double currentPotential;

	/**
	 * The weight map of other neurons map is <ID, weight>. Being more specific,
	 * the connection map is shown as a "upstream-endstream" model, i.e., a
	 * neuron will only have the connection weight of other neurons if other
	 * neurons may receive its message. So a representing neuron will only have
	 * information of connections connected to a comparing neuron.
	 */
	protected Map<Integer, Double> connectionWeightMap = null;

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
	 * Getter of resetingPotential.
	 * 
	 * @return the resetingPotential
	 */
	public double getResetingPotential()
	{
		return resetingPotential;
	}

	/**
	 * Setter of resetingPotential.
	 * 
	 * @param resetingPotential
	 *            the resetingPotential to set
	 */
	public void setResetingPotential(double resetingPotential)
	{
		this.resetingPotential = resetingPotential;
	}

	/**
	 * Getter of threshold.
	 * 
	 * @return the threshold
	 */
	public double getThreshold()
	{
		return threshold;
	}

	/**
	 * Setter of threshold.
	 * 
	 * @param threshold
	 *            the threshold to set
	 */
	public void setThreshold(double threshold)
	{
		this.threshold = threshold;
	}

	/**
	 * Getter of connectionWeightMap.
	 * 
	 * @return the connectionWeightMap
	 */
	public Map<Integer, Double> getConnectionWeightMap()
	{
		return connectionWeightMap;
	}

	/**
	 * Setter of connectionWeightMap.
	 * 
	 * @param connectionWeightMap
	 *            the connectionWeightMap to set
	 */
	public void setConnectionWeightMap(Map<Integer, Double> connectionWeightMap)
	{
		this.connectionWeightMap = connectionWeightMap;
	}
}
