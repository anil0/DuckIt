package com.example.anil.duckit.stackoverflow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by anil on 26/07/2017.
 */
public class Parser
{
    private List<Item> items = new ArrayList<>();

    public List<Item> parseJson(String s) throws JSONException
    {
        final Object tempJsonObject = new JSONTokener( s ).nextValue();

        if( tempJsonObject instanceof JSONArray )
        {
            parseArray( s );
        }

        if( tempJsonObject instanceof JSONObject )
        {
            JSONObject jsonObject = new JSONObject();
            parseItem( jsonObject );
        }

        return items;
    }


    private void parseArray(String s) throws JSONException
    {
        final JSONArray jsonArray = new JSONArray( s );

        for( int i = 0; i < jsonArray.length(); i++ )
        {
            final JSONObject jsonObject = jsonArray.getJSONObject( i );
            items.add( parseItem( jsonObject ) );
        }
    }


    private Item parseItem(JSONObject jsonObject) throws JSONException
    {
        final Item item = new Item();
        item.setAccepted( jsonObject.getBoolean( "is_accepted" ) );
        item.setQuestionId( jsonObject.getInt( "question_id" ) );
        item.setAnswerId( jsonObject.getInt( "answer_id" ) );
        item.setBody( jsonObject.getString( "body" ) );

        return item;
    }
}
