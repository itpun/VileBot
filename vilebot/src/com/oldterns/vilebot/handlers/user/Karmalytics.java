package com.oldterns.vilebot.handlers.user;
import ca.szc.keratin.bot.KeratinBot;
import ca.szc.keratin.bot.annotation.AssignedBot;
import ca.szc.keratin.bot.annotation.HandlerContainer;
import ca.szc.keratin.core.event.message.recieve.ReceivePrivmsg;
import com.oldterns.vilebot.Vilebot;
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
            	break;
            case weeklyPattern:
            	break;
            case monthlyPattern:
            	break;
            default:
            	event.reply("Invalid karmalytics parameter. Please refer to help.");
            	break;
            }
        }
    }


    @AssignedBot
    private KeratinBot bot;

}
