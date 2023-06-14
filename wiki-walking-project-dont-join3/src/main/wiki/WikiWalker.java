package main.wiki;

import static org.junit.Assert.assertTrue;

import java.util.*;

public class WikiWalker 
{

	public HashMap<String, HashSet<String>> articles = new HashMap<>();
	public Set <String> visited = new HashSet<>();
	public HashMap<String, Map<String, Integer>> log = new HashMap<>();


    // TODO: Choose some data structure to implement the site map!

    public WikiWalker()
    {
    }

    /**
     * Adds an article with the given name to the site map and associates the
     * given linked articles found on the page. Duplicate links in that list are
     * ignored, as should an article's links to itself.
     * 
     * @param articleName
     *            The name of the page's article
     * @param articleLinks
     *            List of names for those articles linked on the page
     */
    public void addArticle(String articleName, List<String> articleLinks) 
    {
    	HashSet<String> set = new HashSet<String>(articleLinks);
    	articles.put(articleName, set);
    	Map<String, Integer> counts = new HashMap<>();;
    	log.put(articleName, counts);
    	for (String link : articleLinks)
    	{
    		counts.put(link, 0);
    	}
    }

    /**
     * Determines whether or not, based on the added articles with their links,
     * there is *some* sequence of links that could be followed to take the user
     * from the source article to the destination.
     * 
     * @param src
     *            The beginning article of the possible path
     * @param dest
     *            The end article along a possible path
     * @return boolean representing whether or not that path exists
     */
    public boolean hasPath(String src, String dest) 
    {
    	visited.add(src);
    	if (src.equals(dest))
    	{
        	visited.clear();
    		return true;
    	}
    	if (!articles.containsKey(src)) 
    	{
        	visited.clear();
            return false;
        }
    	if (articles.get(src).contains(dest))
    	{
        	visited.clear();
    		return true;
    	}
    	else
    	{
    		for (String link : articles.get(src))
    		{
    			if (!visited.contains(link))
    			{
    				if (hasPath(link, dest))
    				{
    			    	visited.clear();
    					return true;
    				}
    			}
    		}
    	}
    	visited.clear();
    	return false;
    }

    /**
     * Increments the click counts of each link along some trajectory. For
     * instance, a trajectory of ["A", "B", "C"] will increment the click count
     * of the "B" link on the "A" page, and the count of the "C" link on the "B"
     * page. Assume that all given trajectories are valid, meaning that a link
     * exists from page i to i+1 for each index i
     * 
     * @param traj
     *            A sequence of a user's page clicks; must be at least 2 article
     *            names in length
     */
    public void logTrajectory(List<String> traj) 
    {
    	if (traj.size() <= 1)
    	{
    		throw new IllegalArgumentException();
    	}
    	for (int i = 0; i < traj.size(); i++)
    	{
    		Map<String, Integer> counts = new HashMap<>();;
        	if (!log.containsKey(traj.get(i)))
        	{
        		log.put(traj.get(i), counts);
        	}
    	}
    	String src = traj.get(0);
        for (int i = 1; i < traj.size(); i++) 
        {
            String dest = traj.get(i);
            Map<String, Integer> tempMap = log.get(src);
            tempMap.put(dest, tempMap.getOrDefault(dest, 0) + 1);
            src = dest;
        }
    }

    /**
     * Returns the number of clickthroughs recorded from the src article to the
     * destination article. If the destination article is not a link directly
     * reachable from the src, returns -1.
     * 
     * @param src
     *            The article on which the clickthrough occurs.
     * @param dest
     *            The article requested by the clickthrough.
     * @throws IllegalArgumentException
     *             if src isn't in site map
     * @return The number of times the destination has been requested from the
     *         source.
     */
    public int clickthroughs(String src, String dest) 
    {
        if (!log.containsKey(src))
        {
        	throw new IllegalArgumentException();
        }
        if (log.get(src).containsKey(dest))
        {
        	return log.get(src).get(dest);
        }
        if (!log.get(src).containsKey(dest))
        {
        	return -1;
        }
        return 0;
    }

    /**
     * Based on the pattern of clickthrough trajectories recorded by this
     * WikiWalker, returns the most likely trajectory of k clickthroughs
     * starting at (but not including in the output) the given src article.
     * Duplicates and cycles are possible outputs along a most likely trajectory. In
     * the event of a tie in max clickthrough "weight," this method will choose
     * the link earliest in the ascending alphabetic order of those tied.
     * 
     * @param src
     *            The starting article of the trajectory (which will not be
     *            included in the output)
     * @param k
     *            The maximum length of the desired trajectory (though may be
     *            shorter in the case that the trajectory ends with a terminal
     *            article).
     * @return A List containing the ordered article names of the most likely
     *         trajectory starting at src.
     */
    public List<String> mostLikelyTrajectory(String src, int k) 
    {
    	if (!log.containsKey(src))
        {
        	throw new IllegalArgumentException();
        }
    	Map<String, Integer> counts = new HashMap<>();
    	List<String> trajectory = new ArrayList<>();
    	if (log.containsKey(src))
    	{
    		for (int i = 0; i < k; i++)
    		{
    			Map<String, Integer> links = log.get(src);
    			if (links == null || links.isEmpty())
    			{
    				break;
    			}
    			String next = null;
        		int max = 0;
        		for (String link : links.keySet())
        		{
        			int count = counts.getOrDefault(link, 0) + links.get(link);
        			if (count > max)
        			{
        				next = link;
        				max = count;
        			}
        		}
        		if (next == null)
        		{
        	        List<String> list = new ArrayList<String>(links.keySet());
        	        next = list.get(0);
        		}
        		trajectory.add(next);
        		counts.put(next, counts.getOrDefault(next, 0) + 1);
        		src = next;		
        		counts.clear();
    		}
    	}
    	return trajectory;
    }
}
