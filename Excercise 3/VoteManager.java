import java.io.File;
import java.io.PrintWriter;

class VoteManager
{
	public VoteManager()
	{

	}

	public void submitVote(String first, String last, String vote)
	{
		try
		{
			PrintWriter ballot = new PrintWriter("./votes/" + first + "_" + last + "_" + "ballot.txt", "UTF-8");
			ballot.println(vote);
			ballot.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean hasVoted(String first, String last)
	{
		try
		{
			File folder = new File("./votes");
			File[] listOfFiles = folder.listFiles();
			
			for(int i=0; i<listOfFiles.length; i++)
			{
				String filename = listOfFiles[i].getName();
				String[] voteInfo = filename.split("\\_");
				if(voteInfo.length > 1)
				{
					if(voteInfo[0].equals(first) && voteInfo[1].equals(last))
					{
						return true;
					}
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}