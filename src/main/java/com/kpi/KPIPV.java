package com.kpi;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

@SuppressWarnings("deprecation")
public class KPIPV {

	public static class WriteDataToMysql implements Writable, DBWritable {
		String url;
		String count;

		public WriteDataToMysql() {
		}

		public WriteDataToMysql(String url, String count) {
			this.url = url;
			this.count = count;
		}

		public void write(DataOutput out) throws IOException {
			out.writeUTF(this.url);
			out.writeUTF(this.count);
		}

		public void readFields(DataInput in) throws IOException {
			this.url = in.readUTF();
			this.count = in.readUTF();
		}

		public void write(PreparedStatement statement) throws SQLException {
			statement.setString(1, this.url);
			statement.setString(2, this.count);
		}

		public void readFields(ResultSet resultSet) throws SQLException {
			this.url = resultSet.getString(1);
			this.count = resultSet.getString(2);
		}

		public String toString() {
			return new String(this.url + " " + this.count);
		}
	}

	public static class KPIPVMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			try {

				// 按照换行分割，取每一行数据处理
				String lines[] = value.toString().split("\n");

				for (String line : lines) {
					vo kpivo = kpi.parser(line.toString());

					if (kpivo.isValid()) {
						word.set(kpivo.getRequest());
						context.write(word, one);
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static class KPIPVReduce extends Reducer<Text, IntWritable, WriteDataToMysql, WriteDataToMysql> {

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			context.write(new WriteDataToMysql(key.toString(), String.valueOf(sum)), null);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		// mysql的jdbc驱动
		DistributedCache.addFileToClassPath(new Path("hdfs://10.0.12.114:9000/lib/mysql-connector-java-5.1.38-bin.jar"),
				conf);
		DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver", "jdbc:mysql://10.0.12.114:3306/hive", "hive",
				"hive");

		conf.addResource("classpath:/hadoop/core-site.xml");
		conf.addResource("classpath:/hadoop/hdfs-site.xml");
		conf.addResource("classpath:/hadoop/mapred-site.xml");

		String input = "hdfs://10.0.12.114:9000/user/hdfs/log_kpi/";

		Job job = new Job(conf, "KPIPV");
		job.setJarByClass(KPIPV.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(KPIPVMapper.class);
		job.setReducerClass(KPIPVReduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(DBOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(input));
		DBOutputFormat.setOutput(job, "urlcount", 2);

		job.waitForCompletion(true);
	}

}