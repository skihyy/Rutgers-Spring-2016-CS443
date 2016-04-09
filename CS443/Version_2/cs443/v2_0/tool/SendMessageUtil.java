package cs443.v2_0.tool;

import java.util.List;

import cs443.v2_0.service.MainService;
import cs443.v2_0.vo.Spike;

/**
 * This is a function class providing a method for a neuron to send message to a
 * pointed neuron.
 * 
 * @author YuyangHE
 * @date 2016年4月9日
 * @version 1.0
 * @since 2016年4月9日
 */
public class SendMessageUtil
{
	/**
	 * Send message to a neuron according to its ID.
	 * 
	 * @param id
	 *            destination neuron ID
	 * @return whether sending success
	 */
	public static boolean sendMessage(int id, List<Spike> spikeTrain)
	{
		int size = MainService.comparingNeurons.size();
		for (int i = 0; i < size; ++i)
		{
			if (id == MainService.comparingNeurons.get(i).getId())
			{
				MainService.comparingNeurons.get(i).getSpikeTrains().add(spikeTrain);
				return true;
			}
		}
		return false;
	}
}
