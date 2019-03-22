package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Map;

public interface CountDao {

	public Map<String, Integer> Statistic(BufferedReader bufferedReader, BufferedWriter bufferedWriter);
}
