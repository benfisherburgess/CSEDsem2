import java.io.*;
import java.util.ArrayList;

/**
 * A class that reads from .csv files.
 * @author ajb258
 * @version 1.0
 * @release N/A
 */

public class CSVReader
{
	/*TODO: implement readers for the users.csv file*/
	String csvFile = "src/entries.csv";
	String line = "";
	String csvSplit = ",";
	
	/**
	 * Default constructor.
	 */
	
	public CSVReader()
	{
		
	}
	
	
	/**
	 * 
	 * @param toRead the path of the file to be read
	 * @returnan arraylist of all entries contained in the file
	 */
	public ArrayList<Entry> readEntries(String toRead)
	{
		
		ArrayList<Entry> allEntries = new ArrayList<Entry>();
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(toRead));
			int iteration = 0;
			while ((line = br.readLine()) != null)
			{
				// If statement prevents headers of csv file from being displayed.
				if (iteration == 0)
				{
					iteration++;
				}
				else
				{
					String[] fields = line.split(csvSplit);
					int entryType = Integer.parseInt(fields[0]);
					int entryUser = Integer.parseInt(fields[1]);
					Entry currentEntry = new Entry(entryType,entryUser,fields[2],fields[3]);
					allEntries.add(currentEntry);	
				}	
			}
			
			br.close();
			return allEntries;
			
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param field: the name of the field to be searched for
	 * @param value: the value of the field to be searched for
	 * @return: an arraylist of all entries which contain the correct values for the field 
	 */
	public ArrayList<Entry> SearchFor(String field, String value){
		ArrayList<Entry> allFound = new ArrayList<Entry>();
		ArrayList<Entry> allEntries = readEntries("src/entries.csv");
		for(int i = 0; i<allEntries.size();i++){
			Entry current = allEntries.get(i);
			switch(field){
			case "TYPE" : 
				Integer Type = current.getType();
				if(Type.toString().equals(value) ){
					allFound.add(current);
				}
				break;
			case "USERID" :
				Integer UserID = current.getUserID();
				if(UserID.toString().equals(value)){
					allFound.add(current);
				}
				break;
			case "VALUE" :
				if(current.getValue().equals(value)){
					allFound.add(current);
				}
				break;
			case "DATE" :
				if(current.getDate().equals(value)){
					allFound.add(current);
				}
				break;
			}

		}

		return allFound;
	}
	
	public static void main(String[] args)
	{
		new CSVReader();
	}
}
