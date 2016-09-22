package com.languagedetector;

import java.util.*;
import java.util.regex.*;

/**
 * Converts the given content to a collection 1-gram, 2-gram and 3-gram characters
 * @author nirav99
 *
 */
public class NgramBuilder
{
  private HashSet<String> ngramSet;
  
  public NgramBuilder(String content)
  {
    ngramSet = new HashSet<String>();
    
    buildNgrams(removePunctuations(content));
  }
  
  public HashSet<String> getNgrams()
  {
    return this.ngramSet;
  }
  
  private String removePunctuations(String content)
  {
    String newContent = content.replaceAll("[\\?;:<>!#\\(\\)\\[\\]\\{\\}\"^~]+", " ");
    newContent = newContent.replaceAll("[\\.,](?!\\d)", " ");
    newContent = newContent.replaceAll("(?<!\\d)[\\.,]", " ");
    newContent = newContent.replaceAll("[`'](?!\\w)", " ");
    newContent = newContent.replaceAll("(?<!\\w)[`']", " ");
    newContent = newContent.replaceAll("\\s+", " ");
    return newContent;
  }
  
  private void buildNgrams(String content)
  {
    buildNgram(content, 3);
    buildNgram(content, 2);
    buildNgram(content, 1);
  }
  
  private void buildNgram(String content, int ngramSize)
  {
    int end = content.length() - ngramSize;
    
    String subString = null;
    
    for(int i = 0; i <= end; i++)
    {
      subString = content.substring(i, i + ngramSize);
      
      if(shouldAddStringToNgram(subString))
        ngramSet.add(subString);
    }
  }
  
  private boolean shouldAddStringToNgram(String content)
  {
    if(content.matches("\\d+") || content.matches("[\\d\\W]+") || content.matches("\\w\\s\\w"))
      return false;
    return true;
  }
  
  public static void main(String[] args)
  {
    try
    {
      String s = "Testing 123";
      NgramBuilder builder = new NgramBuilder(s);
      System.out.println(s);
      System.out.println("Modified = " + builder.removePunctuations(s));
      
      System.out.println("Ngram set : ");
      
      HashSet<String> ngramSet = builder.getNgrams();
      
      for(String ngram : ngramSet)
        System.out.println("#" + ngram + "#");
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
