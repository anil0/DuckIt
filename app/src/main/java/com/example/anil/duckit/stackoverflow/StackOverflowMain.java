package com.example.anil.duckit.stackoverflow;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by anil on 26/07/2017.
 */
public class StackOverflowMain
{

    public Answer searchForQuestion(String question)
    {
        try
        {
            //"loop hashmap java"
            //String question = "why is it faster to process a sorted array than an unsorted array";

            System.out.println( "bacon" + question );

            String questionEncoded = URLEncoder.encode( question, "UTF-8" );
            String urlString = "https://api.stackexchange.com/2.2/similar?order=desc&sort=relevance&title=" + questionEncoded + "&site=stackoverflow&filter=!9YdnSIN18";

            final URL url = new URL( urlString );
            final List<Item> listItems = getAnswers( getAnswerID( url ) );

            if( !listItems.isEmpty() )
            {
                final Item item = listItems.get(0);
                Answer answer = getBodyAndLocateElements( item );

                return answer;
            }
            else
            {
                System.out.println( "No data returned by the StackOverflow API... " );
                System.out.println( "Likely to be an incorrect answer ID" );
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return null;
    }


    private Answer getBodyAndLocateElements( final Item item )
    {
        final Element doc = Jsoup.parse( item.getBody() ).body();
        final String answerLink = "https://stackoverflow.com/questions/".concat( String.valueOf( item.getAnswerId() ) );

        final Answer answer = new Answer( answerLink, doc );
        System.out.println( answer.getAnswerLink() );
        System.out.println( answer.getBody() );

        return answer;

    }


    private List<Item> getAnswers( final int answerID )
    {
        try
        {
            String answerUrlString = "https://api.stackexchange.com/2.2/answers/" + answerID + "?order=desc&sort=activity&site=stackoverflow&filter=!9YdnSMKKT";
            final URL urlAnswer = new URL( answerUrlString );
            final List<Item> answeredData = getAnswerData( urlAnswer );
            if( !answeredData.isEmpty() )
            {
                return answeredData;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    private int getAnswerID( final URL url ) throws Exception
    {
        String parsedString = sendRequest( url );
        if( parsedString != null )
        {
            JSONObject jsonObj = new JSONObject( parsedString );
            JSONArray jsonArray = jsonObj.getJSONArray( "items" );
            return jsonArray.getJSONObject( 0 ).getInt( "accepted_answer_id" );
        }

        return 0;
    }


    private List<Item> getAnswerData( final URL url ) throws Exception
    {
        String parsedString = sendRequest( url );
        if( parsedString != null )
        {
            JSONObject jsonObj = new JSONObject( parsedString );
            JSONArray jsonArray = jsonObj.getJSONArray( "items" );
            return new Parser().parseJson( jsonArray.toString() );
        }

        return new ArrayList<>();
    }


    private String sendRequest( URL url )
    {
        BufferedReader reader = null;
        HttpURLConnection con = null;
        try
        {
            final StringBuilder strBuilder = new StringBuilder();
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty( "Content-Type", "application/json" );

            // receive response
            String encoding = con.getHeaderField("Content-Encoding");
            boolean gzipped = encoding != null && encoding.toLowerCase().contains("gzip");

            System.out.println("IS GZIPPED: " + gzipped);

            final InputStream is;
            final InputStreamReader inputStreamReader;

            if(gzipped)
            {
                is = new GZIPInputStream(con.getInputStream());
                inputStreamReader = new InputStreamReader(is);
            }
            else
            {
                is = con.getInputStream();
                inputStreamReader = new InputStreamReader(is);
            }

            reader = new BufferedReader( inputStreamReader );

            String line;
            while( (line = reader.readLine()) != null )
            {
                strBuilder.append( line );
            }

            return strBuilder.toString();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if( reader != null )
                {
                    reader.close();
                    con.disconnect();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}
