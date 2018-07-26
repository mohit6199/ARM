import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class ap2mapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, Text> {
	// static OutputCollector<Text,DoubleWritable> a;
	HashMap<String, Double> sup = new HashMap<String, Double>();

	static int calcuword(String s) {
		int c = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ')
				c++;
		}
		return (c + 1);

	}

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		// a=output;
		String s = value.toString();// text to string

		double val = Double.parseDouble(s.substring(s.indexOf('\t')));

		String item = (s.substring(0, s.indexOf('\t')).trim());
		// output.collect(new Text(item), new DoubleWritable(1111));

		// hash map to give item-->support of single items

		switch(calcuword(item)) {
		case 1:
			output.collect(new Text(item), new Text(Double.toString(val)));
			break;
		case 2:
			String items[]=item.split(" ");
			output.collect(new Text(item.trim()), new Text(""+val));
			for(int i=0;i<items.length;i++)
			{
				output.collect(new Text(items[i]), new Text(item+" "+val));
			}
			break;
		case 3:
			String items1[]=item.split(" ");
			for(int i=0;i<items1.length;i++)
			{
				
				for(int j=i+1;j<items1.length;j++)
					output.collect(new Text(items1[i]+" "+items1[j]), new Text(""+item+" "+val));
					
			}
		}

		/*else {
			String items[] = item.split(" "
			for (int i = 0; i < items.length; i++) {
				String ss = "";
				for (int j = 0; j < items.length; j++) {
					if (i != j)
						ss = ss + items[j];
				}
				double supx = (double) sup.get(items[i]);
				double conf = val / supx;
				output.collect(new Text(ss + "-->" + items[i]),
						new DoubleWritable(conf));

			}

		}
		*/

	}
}

// output.collect(new Text(letter), new IntWritable(word.length()));

