package cs443.v1_0.vo;

/**
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 1.0
 * @since 2016年3月26日
 */
public class Constant
{
	/**
	 * a neuron is used for representing numbers
	 */
	public static final int NUM_NEURON = 1;

	/**
	 * a neuron is not used for representing numbers
	 */
	public static final int OTHER_NEURON = 2;

	/**
	 * the file used for saving the weight of each neuron
	 * 
	 * IMPORTANT: In the first version, the weight is given to the neuron other
	 * than the connection so that the weight can represent the "capability" of
	 * sorting of a neuron.
	 */
	public static final String WEIGHT_FILE = "weight.dat";
	
	public static final int MAX_WEIGHT = 10;
	
	public static final int MIN_WEIGHT = -10;
	
	public static final int INITIAL_WEIGHT = 0;
	
	public static final int INITIAL_SPIKING_RATE = 0;
}
