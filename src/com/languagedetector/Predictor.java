package com.languagedetector;

import java.util.*;

public class Predictor
{
  private ArrayList<LanguageProfile> profileList;
  private double[] probabilityList;
  
  public Predictor(ArrayList<LanguageProfile> profileList)
  {
    this.profileList = profileList;
    probabilityList = new double[profileList.size()];
  }
  
  public String predictTextLangauge(HashSet<String> ngramList)
  {
    for(String word : ngramList) 
    {
      updateProbabilities(word);
    }
    return getPageLanguage();
  }
  
  private void updateProbabilities(String word)
  {
    for(int i = 0; i < profileList.size(); i++)
    {
      updateWordProability(word, i);
    }
  }
  
  private void updateWordProability(String word, int index)
  {
    LanguageProfile profile = profileList.get(index);
    HashMap<String, Integer> map = profile.ngramFrequencyMap();
    int vocabularySize = map.size();
    
    Integer value = map.get(word);
    int totalWords;
    
    if(word.length() == 1)
      totalWords = profile.unigramCount();
    else
    if(word.length() == 2)
      totalWords = profile.bigramCount();
    else
      totalWords = profile.trigramCount();
    
    
    double prob = (value != null) ? 1.0 * (value + 1) / (totalWords + vocabularySize) :
                  1.0 / (totalWords + vocabularySize);

    probabilityList[index] = probabilityList[index] + Math.log(prob);
  }
  
  private String getPageLanguage()
  {
    int maxIndex = 0;
    double maxValue = probabilityList[0];
    
    for(int i = 1; i < probabilityList.length; i++)
    {
      if(probabilityList[i] > maxValue)
      {
        maxValue = probabilityList[i];
        maxIndex = i;
      }
    }
    
    return profileList.get(maxIndex).name();    	
  }
}
