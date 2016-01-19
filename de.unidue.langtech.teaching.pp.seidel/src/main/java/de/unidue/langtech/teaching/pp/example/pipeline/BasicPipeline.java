package de.unidue.langtech.teaching.pp.example.pipeline;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.example.BaselineExample;
import de.unidue.langtech.teaching.pp.example.EvaluatorExample;
import de.unidue.langtech.teaching.pp.example.ReaderExample;

import de.unihd.dbs.heideltime.standalone.DocumentType;
import de.unihd.dbs.heideltime.standalone.HeidelTimeStandalone;
import de.unihd.dbs.heideltime.standalone.OutputType;
import de.unihd.dbs.heideltime.standalone.POSTagger;
import de.unihd.dbs.uima.annotator.heideltime.resources.Language;

import de.unihd.dbs.heideltime.standalone.components.ResultFormatter;
import de.unihd.dbs.heideltime.standalone.components.impl.TimeMLResultFormatter;

public class BasicPipeline
{
private static HeidelTimeStandalone heidelTime;
    public static void main(String[] args)
        throws Exception
    {
    	
    	
    
    	heidelTime = new HeidelTimeStandalone(Language.GERMAN,
                DocumentType.COLLOQUIAL,
              //  OutputType.XMI,
                OutputType.TIMEML,
                "src/main/resources/config.props",
                POSTagger.TREETAGGER, true);
    	
    	System.out.println("heideltime initialzied");
    	Date now = new Date();
    	
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_YEAR, 1);
    	Date tomorrow= calendar.getTime();
    	SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    	System.out.println("Jetzt: "+dt1.format(now));
    	System.out.println("Morgen: "+dt1.format(tomorrow));
    //	ResultFormatter form1 = new TimeMLResultFormatter();
    	String result = heidelTime.process("Wir sehen uns nächsten Montag",now);
    	System.out.println("Ergebnis: "+result);
    	
    	/*
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        ReaderExample.class,
                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(BaselineExample.class,BaselineExample.PARAM_MESSAGE,"test123"),
                AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class),
                AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class,SnowballStemmer.PARAM_LANGUAGE,"en"),
                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)

               
                
        );*/
    }
}
