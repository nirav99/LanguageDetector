package com.languagedetector;

import java.io.*;
import java.util.*;

public class Driver
{
  private ArrayList<LanguageProfile> profileList;
  
  public Driver() throws IOException
  {
    profileList = new ArrayList<LanguageProfile>();
    buildProfiles();
  }
  
  private void buildProfiles() throws IOException
  {
    addProfile("EN.json");
    addProfile("FR.json");
    addProfile("DE.json");
  }
  
  public String getLangauge(String input)
  {
    NgramBuilder ngramBuilder = new NgramBuilder(input);
    HashSet<String> ngrams = ngramBuilder.getNgrams();
      
    Predictor predictor = new Predictor(profileList);
    return predictor.predictTextLangauge(ngrams);
  }
  
  private void addProfile(String profileFile) throws IOException
  {
	InputStream inputStream = Driver.class.getClassLoader().getResourceAsStream(profileFile);
	if(inputStream == null)
      inputStream = new FileInputStream(new File("./profiles/" + profileFile));
	
    LanguageProfileBuilder builder = new LanguageProfileBuilder(inputStream);
    profileList.add(builder.languageProfile());
  }
  
  public static void main(String[] args)
  {
    try
    {
      if(args == null || args.length == 0 || (args.length == 1 && args[0].toLowerCase().contains("help")))
      {
        System.err.println("Provide text at command line whose language needs to be determined");
        return;
      }
      StringBuilder input = new StringBuilder();
      
      for(int i = 0; i < args.length; i++)
        input.append(args[0]).append(" ");
      
 /*     
      input = "This is one line of text. It is written in English. Let's see how this evaluates";
      input = "Die USA machen Russland für den Angriff auf den Uno-Hilfskonvoi in Syrien verantwortlich, liefern aber keine Beweise. Moskau weist alle Schuld von sich - und präsentiert ständig neue Versionen des Tathergangs";
      
      input = "Gabriel, marquis de Ralston, vient d'apprendre l'existence de sa sueur bâtarde Juliana, qui arrive tout droit d'Italie. Pour lancer dans le beau monde cette jeune fille aux origines douteuses, il lui faut un chaperon exemplaire. Pourquoi pas lady Calpurnia Hartwell qui est considérée comme un parangon de vertu ? Elle sera garante de la réputation de Juliana. Sauf que Calpurnia est en train de se rendre compte que sa vie l'ennuie profondément. Elle a même établi une liste de choses scandaleuses à faire ";
      input = "Herren damen kinder Bekleidung";
*/
      Driver driver = new Driver();
      System.out.println("Predicted page langauge = " + driver.getLangauge(input.toString()));
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
