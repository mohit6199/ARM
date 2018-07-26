import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.jobcontrol.Job;

public class apmaper extends MapReduceBase implements
    Mapper<LongWritable, Text, Text, IntWritable> {
	static int tot;
static OutputCollector<Text,IntWritable> a;
apmaper(){
	tot=0;
}
  @Override
  public void map(LongWritable key, Text value,
      OutputCollector<Text, IntWritable> output, Reporter reporter)
      throws IOException {
	  a=output;
	 String s = value.toString();
	   int l=0;
	   if(!(s.substring(0,s.indexOf(',')).equals("TxnID"))){
   String word[]=s.substring(s.indexOf(',')+1).split(",");
   for(int i=0;i<word.length;i++)
   {
	   if(word[i].length()!=0)
		   l++;
   
   }
   for(int i=1;i<=3;i++)
	   printCombination(word,l,i);
   tot++;
   
	   }
	 /*  Configuration conf = new Configuration();
	    conf.setInt("total", tot);
	    Job job =new Job((JobConf) conf);
	  */
	 }
    		
      //  output.collect(new Text(letter), new IntWritable(word.length()));
      static String calcuword(String s){
    	  int c=0;
    	  for(int i=0;i<s.length();i++)
    	  {
    		  if(s.charAt(i)==' ')
    			  c++;
    	  }
    	  return Integer.toString(c+1);
    	  
      }
    
  static void combinationUtil(String arr[], String data[], int start,
          int end, int index, int r)throws IOException
  {
// Current combination is ready to be printed, print it
	  if (index == r)
	  {	
		  String s="";		
		  for (int j=0; j<r; j++)
			  s=s+data[j]+" ";
		  s=s.trim();
		  a.collect(new Text(calcuword(s)+s), new IntWritable(1));
		  return;
	  }

// replace index with all possible elements. The condition
// "end-i+1 >= r-index" makes sure that including one element
// at index will make a combination with remaining elements
// at remaining positions
for (int i=start; i<=end && end-i+1 >= r-index; i++)
{
data[index] = arr[i];
combinationUtil(arr, data, i+1, end, index+1, r);
}
}

// The main function that prints all combinations of size r
// in arr[] of size n. This function mainly uses combinationUtil()
static void printCombination(String arr[], int n, int r) throws IOException
{
// A temporary array to store all combination one by one
String data[]=new String[r];

// Print all combination using temprary array 'data[]'
combinationUtil(arr, data, 0, n-1, 0, r);
}
}

/*
//Java program to print all combination of size r in an array of size n
import java.io.*;*/

