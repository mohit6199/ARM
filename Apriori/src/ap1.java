import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ap1 extends Configured implements Tool {

  @Override
  public int run(String[] args) throws Exception {

    String input, output;
    if(args.length == 2) {
      input = args[0];
      output = args[1];
    } else {
      input = "your-input-dir";
      output = "your-output-dir";
    }
    //Configuration conf = new Configuration();
    //conf.set("test", "123");

    //Job job = new Job(conf);

    JobConf conf = new JobConf(getConf(), ap1.class);
    conf.setJobName(this.getClass().getName());

    FileInputFormat.setInputPaths(conf, new Path(input));
    FileOutputFormat.setOutputPath(conf, new Path(output));
    //conf.setPartitionerClass(MyPartitioner.class);
    conf.setMapperClass(apmaper.class);
   conf.setReducerClass(apreducer.class);

    conf.setMapOutputKeyClass(Text.class);
    conf.setMapOutputValueClass(IntWritable.class);
    conf.setNumReduceTasks(1);
    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(DoubleWritable.class);

    JobClient.runJob(conf);
    return 0;
  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new ap1(), args);
    System.exit(exitCode);
  }
}
