package cs443.v2_0.dao;

/**
 * <p>
 * The basic neuron function.
 * </p>
 * 
 * @author YuyangHE
 * @date 2016年4月3日
 * @version 2.0
 * @since 2016年4月3日
 */
public interface NeuronFunctionDao
{
	/**
	 * Although initialization is not a biological function of a neuron, but is
	 * needed in this program environment. It will read the current neuron's
	 * capability and properties.
	 */
	void initialization();
	
	/**
	 * The neuron will continually update its potential and may spike if the
	 * potential reaches the threshold.
	 * 
	 * @param time a time delay
	 */
	void update(double time);

	/**
	 * Spiking if threshold is reached.
	 */
	void spike();

	/**
	 * Sending information to some neuron based on its connection weight.
	 */
	void sendMessage();

	/**
	 * Recording current potential. Although this is not a biological function,
	 * it can help to gather data used for analysis.
	 */
	void recordPotential();
}
