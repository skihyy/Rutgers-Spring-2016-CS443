package cs443.v1_0.dao;

/**
 * The basic neuron function.
 * 
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 1.0
 * @since 2016年3月26日
 */
public interface NeuronFunctionDao
{
	/**
	 * Processing some information.
	 * @param o some input
	 */
	void firing(Object... o);
	
	/**
	 * Sending out informations.
	 * @param o some input
	 */
	Object sendingInfo(Object... o);
}
