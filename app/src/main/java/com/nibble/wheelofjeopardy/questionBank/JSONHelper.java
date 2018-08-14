package com.nibble.wheelofjeopardy.questionBank;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSONHelper
{
    /**
     *
     * @param path
     * @param type
     * @param q1 question and answers
     * @param q2 question and answers
     * @param q3 question and answers
     * @param q4 question and answers
     * @param q5 question and answers
     * @throws JSONException
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static JSONObject jsonobj;
    private static JSONArray questionList;
    private static String path;

    public JSONHelper(int id)
    {
        this.path = id+".json";
    }

    public JSONHelper()
    {

    }

    public void getID(int i) throws FileNotFoundException, IOException, ParseException
    {
        path = i+".json";
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
    {
        int id = 1;
        String type = "type1";
        String type2 = "type2";
        String[] q1 = new String[] {"q1","a1"};
        String[] q2 = new String[] {"q2","a2"};
        String[] q3 = new String[] {"q3","a3"};
        String[] q4 = new String[] {"q4","a4"};
        String[] q5 = new String[] {"q5","a5"};
        //writeQuestionBank(path, type, q1, q2, q3, q4, q5);
        writeQuestionGroup(id, type, q1, q2, q3, q4, q5);
        System.out.println(readQuestion(2));
        System.out.println(readQuestion(5));
        System.out.println(readAnswer(2));
        writeAnswer(2, "newAnswer");
        System.out.println(readAnswer(5));
        setType("newType");
        System.out.println(getType());
    }
    public static void writeQuestionGroup(
            int id,
            String type,
            String[] q1,
            String[] q2,
            String[] q3,
            String[] q4,
            String[] q5
    )
    {
        path = id+".json";
        jsonobj = new JSONObject();
        jsonobj.put("type", type);
        questionList = new JSONArray();
        questionList.add(0, q1[0]);
        questionList.add(1, q2[0]);
        questionList.add(2, q3[0]);
        questionList.add(3, q4[0]);
        questionList.add(4, q5[0]);
        questionList.add(5, q1[1]);
        questionList.add(6, q2[1]);
        questionList.add(7, q3[1]);
        questionList.add(8, q4[1]);
        questionList.add(9, q5[1]);
        jsonobj.put("questions", questionList);

        try(FileWriter file = new FileWriter(path))
        {
            file.write(jsonobj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //System.out.println(jsonobj);
    }

    public static void writeAnswer(int number, String newAnswer) throws FileNotFoundException, IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        jsonobj = (JSONObject) obj;
        questionList = (JSONArray) jsonobj.get("questions");
        questionList.set(number-1+5, newAnswer);
        jsonobj.put("questions", questionList);
        try(FileWriter file = new FileWriter(path))
        {
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //System.out.println(obj);
        //JSONArray questionLists
    }

    public static String getType() throws FileNotFoundException, IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        jsonobj = (JSONObject) obj;
        return (String) jsonobj.get("type");
    }

    public static void setType(String newType) throws FileNotFoundException, IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        jsonobj = (JSONObject) obj;
        jsonobj.put("type", newType);
        try(FileWriter file = new FileWriter(path))
        {
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeQuestion(int number, String newQuestion) throws FileNotFoundException, IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        jsonobj = (JSONObject) obj;
        questionList = (JSONArray) jsonobj.get("questions");
        questionList.set(number-1, newQuestion);
        jsonobj.put("questions", questionList);
        try(FileWriter file = new FileWriter(path))
        {
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(obj);
        //JSONArray questionLists
    }

    public static String readQuestion(int number) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        jsonobj = (JSONObject) obj;
        questionList = (JSONArray) jsonobj.get("questions");
        return questionList.get(number-1).toString();
        /**
         JSONObject obj = new JSONObject(path);
         JSONArray questionLists = obj.getJSONArray("questions");
         return questionLists.getJSONArray(number-1).get(0).toString();
         **/
    }

    public static String readAnswer(int number) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        jsonobj = (JSONObject) obj;
        questionList = (JSONArray) jsonobj.get("questions");
        return questionList.get(number-1+5).toString();
    }

}