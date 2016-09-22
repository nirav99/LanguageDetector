package com.languagedetector;

import java.util.*;
import java.util.Map.Entry;

public class LanguageProfile
{
  private final HashMap<String, Integer> ngramMap;
  private int unigramCount;
  private int bigramCount;
  private int trigramCount;
  private String name;
  
  public LanguageProfile(HashMap<String, Integer> map, int unigram, int bigram,
                         int trigram, String name)
  {
    this.ngramMap = map;
    this.unigramCount = unigram;
    this.bigramCount = bigram;
    this.trigramCount = trigram;
    this.name = name;
  }
  
  public HashMap<String, Integer> ngramFrequencyMap()
  {
    return this.ngramMap;
  }
  
  public int unigramCount()
  {
    return this.unigramCount;
  }
  
  public int bigramCount()
  {
    return this.bigramCount;
  }
  
  public int trigramCount()
  {
    return this.trigramCount;
  }
  
  public String name()
  {
    return this.name;
  }
  
  public void print()
  {
    System.out.println("Name : " + this.name);
    System.out.println("Unigram : " + this.unigramCount + " Bigram : " + this.bigramCount + " Trigram : " + this.trigramCount);
    
    Set<Entry<String, Integer>> entrySet = this.ngramMap.entrySet();
    
    for(Entry<String, Integer> entry : entrySet)
      System.out.println("\"" + entry.getKey() + "\": " + entry.getValue());
  }
}
