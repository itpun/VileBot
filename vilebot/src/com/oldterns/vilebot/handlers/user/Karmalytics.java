package com.oldterns.vilebot.handlers.user;
import ca.szc.keratin.bot.KeratinBot;
import ca.szc.keratin.bot.annotation.AssignedBot;
import ca.szc.keratin.bot.annotation.HandlerContainer;
import ca.szc.keratin.core.event.message.recieve.ReceivePrivmsg;
import com.oldterns.vilebot.Vilebot;
import com.oldterns.vilebot.db.KarmaDB;

import net.engio.mbassy.listener.Handler;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ipun on 29/08/16.
 */

@HandlerContainer
public class Karmalytics {
    private static final Pattern karmalyticsPattern = Pattern.compile( "^!karmalytics (.+)");
    private static final String todayPattern = "Today";
    private static final String weeklyPattern = "Weekly";
    private static final String monthlyPattern = "Monthly";

    
    @Handler
    public void karmalytics(ReceivePrivmsg event) {
        Matcher karmalyticsMatcher = karmalyticsPattern.matcher(event.getText());
        if (karmalyticsMatcher.matches()) {
            String type = karmalyticsMatcher.group(1);
            switch(type) {
            case todayPattern:   
            	getTransactionsToday(event);
            	break;
            case weeklyPattern:
            	event.reply(type);
            	break;
            case monthlyPattern:
            	event.reply(type);
            	break;
            default:
            	event.reply("Invalid karmalytics parameter. Please refer to help.");
            	break;
            }
        }
    }
    
    private void getTransactionsToday(ReceivePrivmsg event) {
    	double timeStart = getCurrentMidnightDateMillis();
    	double timeEnd = System.currentTimeMillis();
    	event.reply("timestart="+timeStart+", timeEnd="+timeEnd);
    	Set <String> transactions = KarmaDB.getKarmaTransactionsRange(timeEnd,timeStart);
    	Iterator<String> iter =transactions.iterator();
    	if (transactions.isEmpty()){
    		event.reply("No transactions today.");
    		return;
    	}
    	while (iter.hasNext()) {
    		String [] transaction = iter.next().split(",");
    		event.reply(iter.next());
    	}
    	return;
    }
    
    static private double getCurrentMidnightDateMillis() {
    	// http://stackoverflow.com/questions/6850874/how-to-create-a-java-date-object-of-midnight-today-and-midnight-tomorrow    
    	Calendar date = new GregorianCalendar();
    	// reset hour, minutes, seconds and millis
    	date.set(Calendar.HOUR_OF_DAY, 0);
    	date.set(Calendar.MINUTE, 0);
    	date.set(Calendar.SECOND, 0);
    	date.set(Calendar.MILLISECOND, 0);
    	return date.getTimeInMillis();
    }

    @AssignedBot
    private KeratinBot bot;

}
