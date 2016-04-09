package cs443.v2_1.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cs443.v1_0.tool.CloseUtil;
import cs443.v2_0.dao.NeuronFunctionDao;
import cs443.v2_0.vo.Constant;
import cs443.v2_0.vo.Neuron;

/**
 * The function of a representing neuron.
 * 
 * @author YuyangHE
 * @date 2016年4月3日
 * @version 2.0
 * @since 2016年4月3日
 */
public abstract class BaseNeuronImpl extends Neuron implements NeuronFunctionDao
{
	/**
	 * weight of connections with other neurons
	 */
	protected File connectionWeightFile = null;

	/**
	 * used for data collecting, will record its current
	 */
	protected File potentialRecordingFile = null;

	/**
	 * these three output is used to record potential of this neuron
	 */
	private FileOutputStream fos = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;

	/**
	 * Constructors of RepresentingNeuronImpl.
	 * 
	 * @param id
	 *            The ID of current neuron
	 */
	public BaseNeuronImpl(int id)
	{
		this.id = id;
		this.resetingPotential = Constant.RESETING_POTENTIAL;
		this.threshold = Constant.THRESHOLD;

		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_0.dao.NeuronFunctionDao#initialization(int)
	 */
	@Override
	public abstract void initialization();

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_0.dao.NeuronFunctionDao#update(double)
	 */
	@Override
	public abstract void update(double time);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_0.dao.NeuronFunctionDao#spike()
	 */
	@Override
	public abstract void spike();

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_0.dao.NeuronFunctionDao#sendMessage()
	 */
	@Override
	public abstract void sendMessage();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v2_0.dao.NeuronFunctionDao#recordPotential()
	 */
	@Override
	public void recordPotential()
	{
		if (null == potentialRecordingFile || !potentialRecordingFile.exists())
		{
			System.out.println("Potential file is not exist. Creating a new one.");
			try
			{
				potentialRecordingFile.createNewFile();
			}
			catch (IOException e)
			{
				System.out.println("Potential file creation failed.");
				e.printStackTrace();
			}
		}

		try
		{
			this.fos = new FileOutputStream(this.potentialRecordingFile, true);
			this.osw = new OutputStreamWriter(this.fos);
			this.bw = new BufferedWriter(this.osw);

			this.bw.write(this.currentPotential + "");
			this.bw.newLine();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Finding file error.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Writting error.");
			e.printStackTrace();
		}
		finally
		{
			CloseUtil.closeAll(bw, osw, fos);
		}
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + this.id);
		sb.append("\n");
		sb.append("Current Potential: " + this.currentPotential);
		sb.append("\n");
		
		return sb.toString();
	}
}
