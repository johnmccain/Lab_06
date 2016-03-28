import java.util.Random;
public class Dice 
{
	Random random;
	public Dice()
	{
		random = new Random();
	}
	
	public int roll(int sides)
	{
		return random.nextInt(sides) + 1;
	}
}
