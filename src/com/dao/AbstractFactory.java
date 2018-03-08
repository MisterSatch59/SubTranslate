package com.dao;

import com.beans.Language;
import com.beans.Subtitles;

public abstract class AbstractFactory {
	public static final int DAOFACTORY = 1;
	public static final int DAOFACTORY2 = 2;
	
	public abstract Dao<Subtitles> getDaoSubtitles();
	
	public abstract Dao<Language> getDaoLanguages();
	
	  //Méthode permettant de récupérer les Factory
	  public static AbstractFactory getFactory(int type){
	    switch(type){
	      case DAOFACTORY:
	        return DaoFactory.getInstance();
	      case DAOFACTORY2: 
	        return DaoFactory2.getInstance();
	      default:
	        return null;
	    }
	  }
}
