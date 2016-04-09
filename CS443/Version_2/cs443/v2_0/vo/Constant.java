package cs443.v2_0.vo;

/**
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 2.0
 * @since 2016年3月26日
 */
public class Constant
{	
	/**
	 * -70 mV
	 */
	public static final double RESETING_POTENTIAL = 0;
	
	/**
	 * 90 mV
	 */
	public static final double THRESHOLD = 3;
	
	/**
	 * a time delay for a neuron used for the spike train update (msec)
	 */
	public static final double TIME_DELAY_MSEC = 100;
	
	/**
	 * a time delay for a neuron used for the spike train update (sec)
	 */
	public static final double TIME_DELAY_SEC = TIME_DELAY_MSEC / 1000;
	
	//////////////////////////////////////////////////////////////
	//    Constant below are used for data recording/reading    //
	//////////////////////////////////////////////////////////////
	
	public static final String ROOT_DATA_PATH = "Data";
	
	public static final String REPRESENTING_LAYER_PATH = "Representing_Layer";
	
	public static final String COMPARISON_LAYER_PATH = "Comparison_Layer";
	
	public static final String CONNECTION_WEIGHT_FILE_PREFIX = "Connection_";
	
	/**
	 * This file is used for recording the potential change in a neuron.
	 * Every neuron will have its own record file.
	 */
	public static final String POTENTIAL_FILE_PREFIX = "Potential_";
	
	public static final String FILE_SUFFIX = ".txt";
	
}
