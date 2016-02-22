package discretesimulator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;



public class main 
{
	private static int n;
	private static boolean has_run = false;
	private static Queue<String>[] queue;
	private static String result = "";
	private static LinkedList<String> joblist = new LinkedList<String>();
	public static void main(String[] args) throws IOException 
	{

		queue = new Queue[6];
		
		for(int i = 0; i <= 5; i++)
			queue[i] = new LinkedList<String>();
		try 
		{
			BufferedReader fstream = new BufferedReader(new FileReader(args[0]));


				
			int i = 0;
				
			
			while(fstream.ready())
			{
				joblist.add(i, fstream.readLine());
				i++;
			}
			
			
			
			i = 0;
			
		/*	while(i < jobs.size())
			{
				out.print(jobs.get(i));
				i++;
			}*/
			
			fstream.close();

			
			
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

		
		n = 1;
	
		
		while(!completed())
		{
			
			LinkedList<String> temp = new LinkedList<String>(joblist);
			
			for(String g: temp)
			{
				if(checkrdy(g, n))
				{
					queue[0].add(g);
					joblist.pop();
				}
				
			}
			
				while(!queue[0].isEmpty())
				{
					queue[1].add(queue[0].poll());
				}
				if(!queue[1].isEmpty())
				{
					
					String[] parts = queue[1].element().split(",");
					
					parts[2] = String.valueOf(process(Integer.valueOf(parts[2]), 1, parts[0]));
					
					if(Integer.valueOf(parts[2]) == 0)
					{	
						queue[1].poll();
					}
					else
					{		
						queue[1].poll();
						queue[2].add(parts[0] + "," + parts[1] + "," + parts[2]);
					}	
				}
				else
				{
					if(!queue[2].isEmpty())
					{
						String[] parts = queue[2].element().split(",");
						
						parts[2] = String.valueOf(process(Integer.valueOf(parts[2]), 2, parts[0]));
						
						if(Integer.valueOf(parts[2]) == 0)
						{
							queue[2].poll();
						}
						else
						{
							queue[2].poll();
							queue[3].add(parts[0] + "," + parts[1] + "," + parts[2]);
						}
					}
					else
					{
						if(!queue[3].isEmpty())
						{
							String[] parts = queue[3].element().split(",");
							parts[2] = String.valueOf(process(Integer.valueOf(parts[2]), 3, parts[0]));
							if(Integer.valueOf(parts[2]) == 0)
							{
								queue[3].poll();
							}
							else
							{
								queue[3].poll();
								queue[4].add(parts[0] + "," + parts[1] + "," + parts[2]);
							}
						}
						else
						{
							
							if(!queue[4].isEmpty())
							{
								
								String[] parts = queue[4].element().split(",");
								
								parts[2] = String.valueOf(process(Integer.valueOf(parts[2]), 4, parts[0]));
								
								if(Integer.valueOf(parts[2]) == 0)
								{
									
									queue[4].poll();
								}
								else
								{
									queue[4].poll();
									queue[5].add(parts[0] + "," + parts[1] + "," + parts[2]);
								}
							}
							else
							{
								
								if(!queue[5].isEmpty())
								{
									
									String[] parts = queue[5].element().split(",");
									
									parts[2] = String.valueOf(process(Integer.valueOf(parts[2]), 8, parts[0]));
									
									if(Integer.valueOf(parts[2]) == 0)
									{
										queue[5].poll();
									}
								}
	
							}
							
						}
					}
			}
		}
		
		PrintWriter out = new PrintWriter(args[1]);
		
		out.print(result);
		out.close();
		
	}

	public static boolean checkrdy(String g, int time)
	{
		String[] parts = g.split(",");
		
		if(Integer.valueOf(parts[1]) <= time )
		{
			return true;
		}
		
		return false;
	}
	public static int process(int units, int q, String id)
	{
		
		if(units < q)
		{
			for(int i = 0; i < units; i++)
			{
				result = result + id;
			}
			n += units;
			units = 0; 
		}
		else
		{
		units -= q;
		n += q;
		for(int i = 1; i <= q; i++)
		{
			result = result + id;
		}
		}
		
		has_run = true;
		
		return units;
		
	}
	public static boolean completed()
	{
		int i = 0;
		if(joblist.isEmpty())
			i++;
		if(queue[0].isEmpty())
			i++;
		if(queue[1].isEmpty())
			i++;
		if(queue[2].isEmpty())
			i++;
		if(queue[3].isEmpty())
			i++;
		if(queue[4].isEmpty())
			i++;
		if(queue[5].isEmpty())
			i++;
		
		if( i == 7)
			return true;
		
		
		return false;
		
		
		
		
	}



}
