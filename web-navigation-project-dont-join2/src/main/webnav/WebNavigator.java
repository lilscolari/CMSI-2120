package main.webnav;

import java.util.*;

/**
 * Web Navigator used to track which URLs a user is currently
 * or was previously browsing, as well as tools for updating the
 * currently viewed based on their session history.
 * @author Cameron Scolari
 * @version 1.0
 */
public class WebNavigator {

	// Variable used to keep track of the current string.
    private String current;
    
    // Stack used to keep track of the web pages visited before the current one.
    Stack<String> Sback = new Stack<String>();
    
    // Stack used to keep track of the web pages ahead of the current one.
	Stack<String> Sforward = new Stack<String>();
    
    /**
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     *  @param site The new site being visited
     */
    public void visit(String site) 
    {
    	if (this.current != null)
    	{
    		Sback.push(this.current);
    	}
    	this.current = site;
    	if (Sforward != null)
    	{
    		Sforward.clear();
    	}
    }
    
    /**
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back() 
    {
    	try
    	{
    		if (Sback != null)
    		{
    			Sforward.push(this.current);
    			this.current = Sback.pop();
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("Cannot use back to return null.");
    		Sforward.pop();
    	}
    }
    
    /**
     * Changes the current site to the one that was last
     * returned to via back()
     */
    public void forw() 
    {
    	try
    	{
    		if (Sforward != null)
    		{
    			Sback.push(this.current);
    			this.current = Sforward.pop();
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("No forward sites.");
    	}
    }
    
    /**
     * Returns the String representing the site that the navigator
     * is currently at
     * @return The current site's URL
     */
    public String getCurrent() 
    {
        return this.current;
    }
}