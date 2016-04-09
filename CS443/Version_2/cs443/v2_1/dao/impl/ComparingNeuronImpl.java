package cs443.v2_1.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cs443.v2_0.tool.DataFileUtil;
import cs443.v2_0.vo.Constant;
import cs443.v2_0.vo.Spike;

/**
 * @author YuyangHE
 * @date 2016年4月9日
 * @version 1.0
 * @since 2016年4月9日
 */
public class ComparingNeuronImpl extends BaseNeuronImpl
{
	/**
	 * A spike train used as the input. Define that one comparing neuron can
	 * receive 2 spike trains as input.
	 */
	private List<List<Spike>> spikeTrains = new ArrayList<List<Spike>>();

	/**
	 * Constructors of RepresentingNeuronImpl.
	 * 
	 * @param id
	 *            The ID of current neuron
	 */
	public ComparingNeuronImpl(int id)
	{
		super(id);

		this.connectionWeightFile = new File(Constant.ROOT_DATA_PATH + File.separator + Constant.COMPARISON_LAYER_PATH
		        + File.separator + Constant.CONNECTION_WEIGHT_FILE_PREFIX + id + Constant.FILE_SUFFIX);

		this.potentialRecordingFile = new File(Constant.ROOT_DATA_PATH + File.separator + Constant.COMPARISON_LAYER_PATH
		        + File.separator + Constant.POTENTIAL_FILE_PREFIX + id + Constant.FILE_SUFFIX);

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
		if (2 == this.spikeTrains.size())
		{
			// get information get I
			double current = getCurrent();
			this.currentPotential = getPotential(current);
			this.spikeTrains.clear();
		}

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
	}

	/**
	 * Equivalent to <code>sumInputs()</code> in the lecture notes.
	 * 
	 * @return
	 */
	private double getCurrent()
	{
		int current = 0, count = 0;

		for (List<Spike> spikeTrain : this.spikeTrains)
		{
			for (Spike spike : spikeTrain)
			{
				if (spike.isFire())
				{
					if (0 == count)
					{
						current += spike.getWeight();
					}
					else
					{
						current -= spike.getWeight();
					}
				}
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
		//return 0.04 * Math.pow(this.currentPotential, 2) + 5 * this.currentPotential + 140 - 0.2 * this.currentPotential
		//        + current;
		return current;
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
		// send result
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
		System.out.println("Number 1 is greater.");
	}

	/**
	 * Getter of spikeTrains.
	 * 
	 * @return the spikeTrains
	 */
	public List<List<Spike>> getSpikeTrains()
	{
		return spikeTrains;
	}

	/**
	 * Setter of spikeTrains.
	 * 
	 * @param spikeTrains
	 *            the spikeTrains to set
	 */
	public void setSpikeTrains(List<List<Spike>> spikeTrains)
	{
		this.spikeTrains = spikeTrains;
	}
}
