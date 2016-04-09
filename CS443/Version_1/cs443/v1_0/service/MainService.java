package cs443.v1_0.service;

/**
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 1.0
 * @since 2016年3月26日
 */
public class MainService
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		NeuronService neuronList[] = new NeuronService[3];
		
		for(int i = 0 ; i < 3; ++i)
		{
			neuronList[i] = new NeuronService(i + 1, null);
		}
		
		//only 2 neurons get it
		neuronList[1].firing(9);
		neuronList[2].firing(7);
		
		ComparingNeuronService comparitor = new ComparingNeuronService();
		comparitor.firing(neuronList[0].sendingInfo());
		comparitor.firing(neuronList[1].sendingInfo());
		comparitor.firing(neuronList[2].sendingInfo());
		
	}

}
