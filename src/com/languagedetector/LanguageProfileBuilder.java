package com.languagedetector;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;

/**
 * Builds the profile for the given language models
 * @author nirav99
 *
 */
public class LanguageProfileBuilder
{
  private LanguageProfile languageProfile;
  
  public LanguageProfileBuilder(InputStream profileStream) throws IOException
  {
    String content = readFile(profileStream);
    Profile profile = buildProfile(content);
    languageProfile = new LanguageProfile(profile.freq, profile.n_words[0], profile.n_words[1],
                                          profile.n_words[2], profile.name);
  }
  
  public LanguageProfile languageProfile()
  {
    return this.languageProfile;
  }
  
  private String readFile(InputStream input) throws IOException
  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
    StringBuilder content = new StringBuilder();
    String line = null;
    
    while((line = reader.readLine()) != null)
    {
      content.append(line);
      content.append("\n");
    }
    reader.close();
    
    return content.toString();
  }
  
  private Profile buildProfile(String content)
  {
    Gson gson = new Gson();
    Profile languageProfile = gson.fromJson(content, Profile.class);
    return languageProfile;
  }
  
  class Profile
  {
    HashMap<String, Integer> freq;
    int[] n_words;
    String name;
  }
}

