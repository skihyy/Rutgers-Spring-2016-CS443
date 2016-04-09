package cs443.v1_0.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import cs443.v1_0.dao.NeuronFunctionDao;
import cs443.v1_0.tool.CloseUtil;
import cs443.v1_0.vo.Constant;
import cs443.v1_0.vo.Neuron;

/**
 * @author YuyangHE
 * @date 2016年3月26日
 * @version 1.0
 * @since 2016年3月26日
 */
public class NeuronService extends Neuron implements NeuronFunctionDao
{
	private File weightFile;
	private FileInputStream fis = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private FileOutputStream fos = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;

	/**
	 * Constructors of NeuronService.
	 * 
	 * @param id
	 * @param weightFile
	 */
	public NeuronService(int id, File weightFile)
	{
		this.id = id;

		if (null == weightFile)
		{
			System.out.println("The weight file loading error. Will use the default weight file.");
			this.weightFile = new File(Constant.WEIGHT_FILE);
		}
		else
		{
			this.weightFile = weightFile;
		}

		initialization();
	}

	private void initialization()
	{
		String tmpReading = null;
		int count = 1, tmpWeight = 0;
		try
		{
			fis = new FileInputStream(this.weightFile);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			while (null != (tmpReading = br.readLine()))
			{
				if (null == this.weightMap)
				{
					this.weightMap = new HashMap<Integer, Integer>();
				}

				try
				{
					tmpWeight = Integer.parseInt(tmpReading);
				}
				catch (NumberFormatException e)
				{
					System.out.println("Reading weight of ID " + count + " error. Set to 0.");
					tmpWeight = Constant.INITIAL_WEIGHT;
				}

				if (this.id == count)
				{
					this.weight = tmpWeight;
				}
				else
				{
					weightMap.put(count, tmpWeight);
				}
				++count;

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File is not existed.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Reading error");
		}
		finally
		{
			CloseUtil.closeAll(br, isr, fis);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v1_0.dao.NeuronFunctionDao#firing()
	 */
	@Override
	public void firing(Object... obj)
	{
		for (Object o : obj)
		{
			if (o instanceof Double || o instanceof Integer)
			{
				if (Constant.MAX_WEIGHT >= this.weight && Constant.INITIAL_WEIGHT <= this.weight)
				{
					try
					{
						this.spikingRate = (double) o;
					}
					catch (ClassCastException e)
					{
						this.spikingRate = ((Integer) o) * 1.0;
					}
					if (Constant.MAX_WEIGHT > this.weight && Constant.INITIAL_WEIGHT <= this.weight)
					{
						++this.weight;
					}
				}
				// else
				// the neuron has some specification so it will not change
			}
			else
			{
				if (Constant.INITIAL_WEIGHT >= this.weight && Constant.MIN_WEIGHT < this.weight)
				{
					--this.weight;
				}
				// else
				// the neuron has some specification so it will not change
			}
		}

		// write back to file
		int size = this.weightMap.size() + 1;
		try
		{
			fos = new FileOutputStream(this.weightFile);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);

			for (int i = 0; i < size; ++i)
			{
				if (this.id == i + 1)
				{
					bw.write(this.weight + "");
				}
				else
				{
					bw.write(this.weightMap.get(i + 1) + "");
				}
				bw.newLine();
			}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs443.v1_0.dao.NeuronFunctionDao#sendingInfo(java.lang.Object[])
	 */
	@Override
	public Object sendingInfo(Object... o)
	{
		return this.spikingRate;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ");
		sb.append(this.id);
		sb.append("\n");
		sb.append("Spiking Rate: ");
		sb.append(this.spikingRate);
		sb.append("\n");
		sb.append("Weight: ");
		sb.append(this.weight);
		sb.append("\n");
		return sb.toString();
	}

}
