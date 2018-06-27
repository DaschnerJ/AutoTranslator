import com.gtranslate.Language;
import com.gtranslate.Translator;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Translate implements Serializable {

    static final long serialVersionUID = 66L;

    String inFrom = Language.ENGLISH;
    String  inTo = Language.ENGLISH;
    String  outFrom = Language.ENGLISH;
    String  outTo = Language.ENGLISH;

    boolean saved = false;

    HashMap<String, String> languages = new HashMap<>();

    public Translate()
    {
        populateLanguages();
        ClassSaver cs = new ClassSaver();
        if(cs.load("translate") != null) {
            Translate tr = (Translate) cs.load("translate");
            inFrom = tr.inFrom;
            inTo = tr.inTo;
            outFrom = tr.outFrom;
            outTo = tr.outTo;
            saved = true;
        }
    }

    public void info()
    {
        say("Translate:");
        say("");
        say("   inFrom: " + inFrom);
        say("   inTo: " + inTo);
        say("   outFrom: " + outFrom);
        say("   outTo: " + outTo);
        say("");
        say("   Saved: " + saved);
    }
    
    private void populateLanguages()
    {
        languages.put("AFRIKAANS","af");
        languages.put("ALBANIAN","sq");
        languages.put("ARABIC","ar");
        languages.put("ARMENIAN","hy");
        languages.put("AZERBAIJANI","az");
        languages.put("BASQUE","eu");
        languages.put("BELARUSIAN","be");
        languages.put("BENGALI","bn");
        languages.put("BULGARIAN","bg");
        languages.put("CATALAN","ca");
        languages.put("CHINESE","zh-CN");
        languages.put("CROATIAN","hr");
        languages.put("CZECH","cs");
        languages.put("DANISH","da");
        languages.put("DUTCH","nl");
        languages.put("ENGLISH","en");
        languages.put("ESTONIAN","et");
        languages.put("FILIPINO","tl");
        languages.put("FINNISH","fi");
        languages.put("FRENCH","fr");
        languages.put("GALICIAN","gl");
        languages.put("GEORGIAN","ka");
        languages.put("GERMAN","de");
        languages.put("GREEK","el");
        languages.put("GUJARATI","gu");
        languages.put("HAITIAN_CREOLE","ht");
        languages.put("HEBREW","iw");
        languages.put("HINDI","hi");
        languages.put("HUNGARIAN","hu");
        languages.put("ICELANDIC","is");
        languages.put("INDONESIAN","id");
        languages.put("IRISH","ga");
        languages.put("ITALIAN","it");
        languages.put("JAPANESE","ja");
        languages.put("KANNADA","kn");
        languages.put("KOREAN","ko");
        languages.put("LATIN","la");
        languages.put("LATVIAN","lv");
        languages.put("LITHUANIAN","lt");
        languages.put("MACEDONIAN","mk");
        languages.put("MALAY","ms");
        languages.put("MALTESE","mt");
        languages.put("NORWEGIAN","no");
        languages.put("PERSIAN","fa");
        languages.put("POLISH","pl");
        languages.put("PORTUGUESE","pt");
        languages.put("ROMANIAN","ro");
        languages.put("RUSSIAN","ru");
        languages.put("SERBIAN","sr");
        languages.put("SLOVAK","sk");
        languages.put("SLOVENIAN","sl");
        languages.put("SPANISH","es");
        languages.put("SWAHILI","sw");
        languages.put("SWEDISH","sv");
        languages.put("TAMIL","ta");
        languages.put("TELUGU","te");
        languages.put("THAI","th");
        languages.put("TURKISH","tr");
        languages.put("UKRAINIAN","uk");
        languages.put("URDU","ur");
        languages.put("VIETNAMESE","vi");
        languages.put("WELSH","cy");
        languages.put("YIDDISH","yi");
        languages.put("CHINESE_SIMPLIFIED","zh-CN");
        languages.put("CHINESE_TRADITIONAL","zh-TW");
    }

    public String translateIn(String msg)
    {
//        if(inFrom.equals("auto"))
//        {
//            Translator translate = Translator.getInstance();
//            Language language = Language.getInstance();
//            System.out.println("Got translate instance... " + msg.trim());
//            String[] s = msg.trim().split(" ");
//            String prefixLanguage = translate.detect(s[0]);
//            System.out.println("Detected language...");//en
//            String tL = language.getNameLanguage(prefixLanguage, Language.ENGLISH);
//            System.out.println("Detected: " + tL);
//            String text = translate.translate(msg, tL, inTo);
//            return text;
//        }
//        else
//        {
        //Translator http = new Translator();
        String word = "";
        try {
            word = callUrlAndParseResult(inFrom, inTo, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(word);
//            Translator translate = Translator.getInstance();
//            String text = translate.translate(msg, inFrom, inTo);
            return word;
        //}
    }

    public String translateOut(String msg)
    {
//        if(outFrom.equals("auto"))
//        {
//            Translator translate = Translator.getInstance();
//            Language language = Language.getInstance();
//            System.out.println("Got translate instance... " + msg.trim());
//            String[] s = msg.trim().split(" ");
//            String prefixLanguage = translate.detect(s[0]);
//            System.out.println("Detected language...");//en
//            String tL = language.getNameLanguage(prefixLanguage, Language.ENGLISH);
//            System.out.println("Detected: " + tL);
//            String text = translate.translate(msg, tL, outTo);
//            return text;
//        }
//        else
//        {
//            Translator translate = Translator.getInstance();
//            String text = translate.translate(msg, outFrom, outTo);
//            return msg;
//        }
        String word = "";
        try {
            word = callUrlAndParseResult(outFrom, outTo, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(word);
//            Translator translate = Translator.getInstance();
//            String text = translate.translate(msg, inFrom, inTo);
        return word;
    }

    private ArrayList<String> getLanguages()
    {
        return new ArrayList<>(languages.keySet());
    }

    private String requestAutoLanguage()
    {
        saved = false;
        say("Please type one of the following languages to select it: ");
        say("auto");
        ArrayList<String> lang = getLanguages();
        lang.forEach(x -> say(x.toLowerCase()));
        String input = getInput();
        if(input.toLowerCase().equals("auto"))
        {
            return "auto";
        }
        else if(languages.keySet().contains(input.toUpperCase()))
        {
            return languages.get(input.toUpperCase());
        }
        else if(input.toLowerCase().equals("cancel"))
        {
            return "auto";
        }
        else
        {
            say("Please select a proper language or type \"cancel\" to select auto by default.");
            return requestAutoLanguage();
        }

    }

    private String requestLanguage()
    {
        saved = false;
        say("Please type one of the following languages to select it: ");
        ArrayList<String> lang = getLanguages();
        lang.forEach(x -> say(x.toLowerCase()));
        String input = getInput();
        if(languages.keySet().contains(input.toUpperCase()))
        {
            return languages.get(input.toUpperCase());
        }
        else if(input.toLowerCase().equals("cancel"))
        {
            return Language.ENGLISH;
        }
        else
        {
            say("Please select a proper language or type \"cancel\" to select english by default.");
            return requestLanguage();
        }
    }

    public void requestLanguages()
    {
        say("Startin set up...");
        setInFrom();
        setInTo();
        setOutFrom();
        setOutTo();
        say("Set up is complete!");
    }

    public void setInFrom()
    {
        say("What language do people message to you in? (Put auto if it more than one language.)");
        {
            inFrom = requestAutoLanguage();
        }
    }

    public void setInTo()
    {
        say("What language do you want to read in?");
        {
            inTo = requestLanguage();
        }
    }

    public void setOutFrom()
    {
        say("What language do you use? (Put auto if you type in more than one language.)");
        {
            outFrom = requestAutoLanguage();
        }
    }

    public void setOutTo()
    {
        say("What language do you want your messages translated to?");
        {
            outTo = requestLanguage();
        }
    }

    private void say(String msg)
    {
        System.out.println(msg);
    }

    Scanner in = new Scanner(System.in);

    private String getInput()
    {
        return in.nextLine();
    }

    public void save()
    {
        saved = true;
        ClassSaver cs = new ClassSaver();
        cs.save(this, "translate");
    }

//    private String detectLanguage(String msg)
//    {
//
//    }

    private String callUrlAndParseResult(String langFrom, String langTo,
                                         String word) throws Exception
    {

        String url = "https://translate.googleapis.com/translate_a/single?"+
                "client=gtx&"+
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseResult(response.toString());
    }

    private String parseResult(String inputJson) throws Exception
    {
        /*
         * inputJson for word 'hello' translated to language Hindi from English-
         * [[["नमस्ते","hello",,,1]],,"en"]
         * We have to get 'नमस्ते ' from this json.
         */

        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);

        return jsonArray3.get(0).toString();
    }





}
