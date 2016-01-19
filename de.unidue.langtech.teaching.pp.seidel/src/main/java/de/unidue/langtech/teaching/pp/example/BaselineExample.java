package de.unidue.langtech.teaching.pp.example;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

/**
 * The baseline always identifies "EN" as the document language.
 * 
 * @author zesch
 *
 */
public class BaselineExample
    extends JCasAnnotator_ImplBase
{
	public static final String PARAM_MESSAGE = "PARAM_MESSAGE";
	@ConfigurationParameter(name = PARAM_MESSAGE, mandatory = true, defaultValue = "Hello everyone")
	protected String message; 
	
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        System.out.println("Document is: " + jcas.getDocumentText());
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        
        String lang="";
        for(Token t : tokens){
        	System.out.println(t.getCoveredText());
        	String coveredText = t.getCoveredText().toLowerCase();
        	if(coveredText.equals("das")){
        		lang="DE";
        	}
        }
        
        for(Token t : tokens){
        	System.out.println(t.getCoveredText());
        	String coveredText = t.getCoveredText().toLowerCase();
        	if(coveredText.equals("the")){
        		lang="EN";
        	}
        	
        	
        }
        for(Token t : tokens){
        	System.out.println(t.getCoveredText());
        	String coveredText = t.getCoveredText().toLowerCase();
        	if(coveredText.equals("je")){
        		lang="FR";
        	}
        	
        	
        	
        }
        
        
        
        System.out.println("CAS contains " + tokens.size() + " tokens.");

			System.out.println(PARAM_MESSAGE+" "+ message);
		
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
        languageAnno.setLanguage(lang);
        languageAnno.addToIndexes();
        
        
    }
}