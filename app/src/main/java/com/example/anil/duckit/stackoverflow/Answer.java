package com.example.anil.duckit.stackoverflow;

import org.jsoup.nodes.Element;

/**
 * Created by anil on 27/07/2017.
 */
public class Answer
{
    private Element body;
    private String answerLink;


    public Answer( String answerLink, Element body )
    {
        this.answerLink = answerLink;
        this.body = body;
    }


    public Element getBody()
    {
        return body;
    }


    public void setBody( Element body )
    {
        this.body = body;
    }


    public String getAnswerLink()
    {
        return answerLink;
    }


    public void setAnswerLink( String answerLink )
    {
        this.answerLink = answerLink;
    }
}
