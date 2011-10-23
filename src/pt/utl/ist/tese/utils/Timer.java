package pt.utl.ist.tese.utils;

import java.text.NumberFormat;
import java.util.Date;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.main.Main;

public class Timer {

	@SuppressWarnings("unused")
	private static final SimpleLogger log = new SimpleLogger(Main.class);
	
	protected long _timer;
	
	public Timer() {
		restartTimer();
	}
	
	public long restartTimer() {
		_timer = new Date().getTime();
		return _timer;
	}
	
	public String timeElapsed() {
	
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);

		
		double elapsed = new Date().getTime() - _timer;
		elapsed /= 1000;
		String s;
		double minutes = Math.floor( elapsed / 60 );
		String sec = "";
		if ( minutes > 0 ) {
			double seconds = elapsed - (minutes * 60);
			String min = "";
			
			if ( elapsed - minutes * 60 > 1 )
				sec = "s";
			if ( minutes > 1)
				min = "s";
			
			s = "" + nf.format(minutes) + " minute" + min + " and " 
								 + nf.format(seconds) + " second" + sec;  
		}
		else {
			if ( elapsed > 1 )
				sec = "s";
			s = "" + nf.format(elapsed) + " second" + sec;
		}
		
		return s;
	}
	
	@Override
	public String toString() {
		return timeElapsed();
	}
}
