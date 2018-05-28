class BeispielGA
{
	
	public static void main(String[] args)
	{	
		GA einGA = new GA();
		
		

		for(int g=0; g<100;g++)
		{
			einGA.rekombinieren();//selektion
			einGA.mutieren();
			einGA.bewerten();//DEkodieren
		}

	}
}