import java.lang.UnsupportedOperationException;

public class TemperatureConverter 
{
	public static double convert(char fromUnit, char toUnit, double temperature) throws UnsupportedOperationException
	{
		//Convert to Celcius
		switch(fromUnit)
		{
			case 'C':
				//Do nothing
				break;
			case 'K':
				temperature -= 273.15;
				break;
			case 'F':
				temperature = (temperature - 32) * (5.0/9.0);
				break;
			default:
				throw new UnsupportedOperationException("Error: Unsupported 'from' unit");
		}

		//Then convert to whatever
		switch(toUnit)
		{
			case 'C':
				//Do nothing
				break;
			case 'K':
				temperature += 273.15;
				break;
			case 'F':
				temperature = (temperature + 32) * 1.8;
				break;
			default:
				throw new UnsupportedOperationException("Error: Unsupported 'to' unit");
		}

		return temperature;
	}
}