package cs443.v2_1.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import cs443.v2_0.tool.DataFileUtil;
import cs443.v2_0.tool.SendMessageUtil;
import cs443.v2_0.vo.Constant;
import cs443.v2_0.vo.Spike;

/**
 * @author YuyangHE
 * @date 2016年4月9日
 * @version 1.0
 * @since 2016年4月9日
 */
public class RepresentingNeuronImpl extends BaseNeuronImpl
{
	/**
	 * A spike train used as the input. Define that one representing neuron can
	 * only receive one spike train as input.
	 */
	private List<Spike> spikeTrain = new ArrayList<Spike>();

	/**
	 * Constructors of RepresentingNeuronImpl.
	 * 
	 * @param id
	 *            The ID of current neuron
	 */
	public RepresentingNeuronImpl(int id)
	{
		super(id);

		this.connectionWeightFile = new File(Constant.ROOT_DATA_PATH + File.separator + Constant.REPRESENTING_LAYER_PATH
		        + File.separator + Constant.CONNECTION_WEIGHT_FILE_PREFIX + id + Constant.FILE_SUFFIX);

		this.potentialRecordingFile = new File(
		        Constant.ROOT_DATA_PATH + File.separator + Constant.REPRESENTING_LAYER_PATH + File.separator
		                + Constant.POTENTIAL_FILE_PREFIX + id + Constant.FILE_SUFFIX);

		this.initialization();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_1.dao.impl.BaseNeuronImpl#initialization()
	 */
	@Override
	public void initialization()
	{
		this.connectionWeightMap = DataFileUtil.readConnectionWeight(this.connectionWeightFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_1.dao.impl.BaseNeuronImpl#update(double)
	 */
	@Override
	public void update(double time)
	{
		System.out.println("--Neuron " + this.id + "--");
		// get information get I
		double current = getCurrent();

		this.currentPotential = getPotential(current);

		if (Constant.THRESHOLD <= this.currentPotential)
		{
			this.spike();
			this.recordPotential();
			this.currentPotential = Constant.RESETING_POTENTIAL;
		}
		else
		{
			this.recordPotential();
		}

		this.spikeTrain.clear();
	}

	/**
	 * Equivalent to <code>sumInputs()</code> in the lecture notes.
	 * 
	 * @return
	 */
	private double getCurrent()
	{
		int current = 0;

		for (Spike spike : this.spikeTrain)
		{
			if (spike.isFire())
			{
				current += spike.getWeight();
			}
		}

		return current;
	}

	/**
	 * From Izhikevich implementation where dv/dt = 0.04v^2 + 5v + 140 - u + I.
	 * In this case, simply set u = self.b * v where self.b = 0.2 and v is the
	 * potential.
	 */
	private double getPotential(double current)
	{
		// return 0.04 * Math.pow(this.currentPotential, 2) + 5 *
		// this.currentPotential + 140 - 0.2 * this.currentPotential
		// + current;
		return current * 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_1.dao.impl.BaseNeuronImpl#spike()
	 */
	@Override
	public void spike()
	{
		System.out.println("Spiking!");

		// if it spikes
		// send this number to the comparing neuron
		this.sendMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_1.dao.impl.BaseNeuronImpl#sendMessage()
	 */
	@Override
	public void sendMessage()
	{
		int destinationID = getDestinationID();

		SendMessageUtil.sendMessage(destinationID, this.spikeTrain);
	}

	/**
	 * This id is the neuron in comparing layer that has the largest connection
	 * weight with current neuron.
	 */
	private int getDestinationID()
	{
		int id = -1;
		double maxPotential = -1, tmpPotential = -1;
		;

		Set<Entry<Integer, Double>> connectionWeight = this.connectionWeightMap.entrySet();

		for (Entry<Integer, Double> oneConnection : connectionWeight)
		{
			tmpPotential = oneConnection.getValue();
			if (tmpPotential > maxPotential)
			{
				maxPotential = tmpPotential;
				id = oneConnection.getKey();
			}
		}

		return id;
	}

	/**
	 * Getter of spikeTrain.
	 * 
	 * @return the spikeTrain
	 */
	public List<Spike> getSpikeTrain()
	{
		return spikeTrain;
	}

	/**
	 * Setter of spikeTrain.
	 * 
	 * @param spikeTrain
	 *            the spikeTrain to set
	 */
	public void setSpikeTrain(List<Spike> spikeTrain)
	{
		this.spikeTrain = spikeTrain;
	}

}
