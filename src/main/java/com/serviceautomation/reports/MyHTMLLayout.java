package com.serviceautomation.reports;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class MyHTMLLayout
  extends HTMLLayout
{
  protected final int BUF_SIZE = 256;
  protected final int MAX_CAPACITY = 1024;
  static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
  private StringBuffer sbuf = new StringBuffer(256);
  boolean locationInfo = false;
  
  public String format(LoggingEvent event)
  {
    if (this.sbuf.capacity() > 1024) {
      this.sbuf = new StringBuffer(256);
    } else {
      this.sbuf.setLength(0);
    }
    this.sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);
    
    this.sbuf.append("<td style=\"font-size:medium;font-family:calibri;\">");
    this.sbuf.append(event.timeStamp - LoggingEvent.getStartTime());
    this.sbuf.append("</td>" + Layout.LINE_SEP);
    
    String escapedThread = Transform.escapeTags(event.getThreadName());
    this.sbuf.append("<td style=\"font-size:medium;font-family:calibri;\" title=\"" + escapedThread + " thread\">");
    this.sbuf.append(escapedThread);
    this.sbuf.append("</td>" + Layout.LINE_SEP);
    
    this.sbuf.append("<td title=\"Level\" style=\"font-size:medium;font-family:calibri;\" >");
    if (event.getLevel().equals(Level.DEBUG))
    {
      this.sbuf.append("<font color=\"#339933\">");
      this.sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
      this.sbuf.append("</font>");
    }
    else if (event.getLevel().isGreaterOrEqual(Level.WARN))
    {
      this.sbuf.append("<font color=\"#993300\" style=\"font-size:medium;font-family:calibri;\" ><strong>");
      this.sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
      this.sbuf.append("</strong></font>");
    }
    else
    {
      this.sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
    }
    this.sbuf.append("</td>" + Layout.LINE_SEP);
    
    String escapedLogger = Transform.escapeTags(event.getLoggerName());
    this.sbuf.append("<td title=\"" + escapedLogger + " category\" style=\"font-size:medium;font-family:calibri;\" >");
    this.sbuf.append(escapedLogger);
    this.sbuf.append("</td>" + Layout.LINE_SEP);
    if (this.locationInfo)
    {
      LocationInfo locInfo = event.getLocationInformation();
      this.sbuf.append("<td style=\"font-size:medium;font-family:calibri;\">");
      this.sbuf.append(Transform.escapeTags(locInfo.getFileName()));
      this.sbuf.append(':');
      this.sbuf.append(locInfo.getLineNumber());
      this.sbuf.append("</td>" + Layout.LINE_SEP);
    }
    this.sbuf.append("<td title=\"Message\" style=\"font-size:medium;font-family:calibri;\" >");
    this.sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
    this.sbuf.append("</td>" + Layout.LINE_SEP);
    this.sbuf.append("</tr>" + Layout.LINE_SEP);
    if (event.getNDC() != null)
    {
      this.sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size:medium;font-family:calibri;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
      this.sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));
      this.sbuf.append("</td></tr>" + Layout.LINE_SEP);
    }
    String[] s = event.getThrowableStrRep();
    if (s != null)
    {
      this.sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size:medium;font-family:calibri;\" colspan=\"6\">");
      appendThrowableAsHTML(s, this.sbuf);
      this.sbuf.append("</td></tr>" + Layout.LINE_SEP);
    }
    return this.sbuf.toString();
  }
  
  void appendThrowableAsHTML(String[] s, StringBuffer sbuf)
  {
    if (s != null)
    {
      int len = s.length;
      if (len == 0) {
        return;
      }
      sbuf.append(Transform.escapeTags(s[0]));
      sbuf.append(Layout.LINE_SEP);
      for (int i = 1; i < len; i++)
      {
        sbuf.append(TRACE_PREFIX);
        sbuf.append(Transform.escapeTags(s[i]));
        sbuf.append(Layout.LINE_SEP);
      }
    }
  }
}
