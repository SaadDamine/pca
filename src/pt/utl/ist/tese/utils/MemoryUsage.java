package pt.utl.ist.tese.utils;

import java.text.NumberFormat;

public class MemoryUsage {

	protected long _memory; 
	
	public MemoryUsage() {
		restart();
	}
	
	public void restart() {
		_memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}
	
	@Override
	public String toString() {
		long usage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) - _memory;
		double mem = (double)usage / (1024 * 1024);

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5);
		return "" + nf.format(mem) + " MB";
	}
	
	public static String free() {
		return NumberFormat.getInstance().format(freeMemRun()) + " MB";
	}
	
	public static String usage() {
		return NumberFormat.getInstance().format(memUsage()) + " MB";
	}
	
	public static String total() {
		return NumberFormat.getInstance().format(totalMem()) + " MB";
	}
	
	public static String max() {
		return NumberFormat.getInstance().format(maxMem()) + " MB";
	}
	
	public static double freeMemRun() {	
		return ((double)Runtime.getRuntime().freeMemory()) / (1024*1024);
	}
	public static double freeMem() {	
		return maxMem() - memUsage();
	}
	
	public static double memUsage() {
		return ((double)Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024*1024);
	}
	
	public static double totalMem() {
		return ((double)Runtime.getRuntime().totalMemory()) / (1024*1024);
	}
	
	public static double maxMem() {
		return ((double)Runtime.getRuntime().maxMemory()) / (1024*1024);
	}
}
