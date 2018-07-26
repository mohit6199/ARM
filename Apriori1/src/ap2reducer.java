import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ap2reducer extends MapReduceBase implements
    Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterator<Text> values,
      OutputCollector<Text, Text> output, Reporter reporter)
      throws IOException{
	  String x1=key.toString();
  
	 String x="";
	  while(values.hasNext())
	  {
		  Text val = values.next();
		  x=x+"/t"+val.toString();
	  }
	 // output.collect(new Text(x1),new Text(x));
	  double supx= 0;
	  String list[]=x.split("/t");
	  int i=0;
	  for(String s:list){
		  s=s.trim();
		  i++;
		  if(i==1)
			  continue;
		  if(Character.isDigit(s.charAt(0)))
			  supx=Double.parseDouble(s);
		  
	  }
	  int i1=0;
	  for(String s:list)
	  {s=s.trim();
		  i1++;
		  if(i1==1)
			  continue;
		 // String s=t.toString();
		  if(Character.isDigit(s.charAt(0)))
			  continue;
		 
		  String y=s.substring(0,s.lastIndexOf(' '));
		  double supy=Double.parseDouble(s.substring(s.lastIndexOf(' ')));
		  double conf=supy/supx;
		  String yf="";
		  for(int e=0;e<x1.split(" ").length;e++)
			  yf=yf+y.replace(x1.split(" ")[e],"");
		  if(conf>=.6)
		  output.collect(new Text("{ "+x1+" }  -> "+y.replace(x1,"")),new Text(Double.toString(conf)));
	  }
	  /*
	  double supx;
	  for(Text t:list)
	  {
		  String s=t.toString();
		  if(Character.isDigit(s.charAt(0)))
			  supx=Double.parseDouble(s);
	  }
	  for(Text t:list)
	  {
		  String s=t.toString();
		  if(Character.isDigit(s.charAt(0)))
			  continue;
		  
		  String y=s.substring(0,s.lastIndexOf(' '));
		  double supy=Double.parseDouble(s.substring(s.lastIndexOf(' ')));
		  output.collect(new Text(y+" -> "+x),new DoubleWritable(supy/supx));
	  }
   */
	  
      //output.collect(key, new Text(x));
    }
  }
  

