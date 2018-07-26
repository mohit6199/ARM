import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class apreducer extends MapReduceBase implements
    Reducer<Text, IntWritable, Text, DoubleWritable> {

  @Override
  public void reduce(Text key, Iterator<IntWritable> values,
      OutputCollector<Text, DoubleWritable> output, Reporter reporter)
      throws IOException {
	  
	/*Configuration conf = context.getConfiguration();
	  String param = conf.get("test");
	  */
	  int tot=0;
    double count = 0;
    while (values.hasNext()) {
      IntWritable value = values.next();
      
      count++;
    }
    String a=key.toString();
    
    if(count>=3)
      {output.collect(new Text(a.substring(1)), new DoubleWritable(count));
      tot++;
      }
      }
    }
