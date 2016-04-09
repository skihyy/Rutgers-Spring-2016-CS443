package cs443.v2_0.vo;

/**
 * A single spike that may fire or not fire.
 * 
 * @author YuyangHE
 * @date 2016年4月9日
 * @version 1.0
 * @since 2016年4月9日
 */
public class Spike
{
	/**
	 * Constructors of Spike.
	 */
	public Spike()
	{
	}

	/**
	 * Constructors of Spike.
	 * @param isFire
	 * @param weight
	 */
	public Spike(boolean isFire, double weight)
	{
		this.isFire = isFire;
		this.weight = weight;
	}

	protected boolean isFire;

	protected double weight;

	/**
	 * Getter of isFire.
	 * 
	 * @return the isFire
	 */
	public boolean isFire()
	{
		return isFire;
	}

	/**
	 * Setter of isFire.
	 * 
	 * @param isFire
	 *            the isFire to set
	 */
	public void setFire(boolean isFire)
	{
		this.isFire = isFire;
	}

	/**
	 * Getter of weight.
	 * 
	 * @return the weight
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * Setter of weight.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

}
