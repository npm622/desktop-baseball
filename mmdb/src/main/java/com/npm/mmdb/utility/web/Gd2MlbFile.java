package com.npm.mmdb.utility.web;

public enum Gd2MlbFile
{
	BOXSCORE, GAME, INNING, LINESCORE, PLAYERS;
	
	public final String getTag( )
	{
		switch (this)
		{
			case BOXSCORE:
				return toString( ) + ".";
			case GAME:
				return toString( ) + ".";
			case INNING:
				return toString( );
			case LINESCORE:
				return toString( ) + ".";
			case PLAYERS:
				return toString( ) + ".";
			default:
				throw new IllegalArgumentException(this + " is not a supported enum value in Gd2MlbFile#getTag( )");
		}
	}
	
	private static final String	INNING_ALL_TAG	= INNING.getTag( ) + "_all";

	public static final boolean isGd2MlbFileElement(final String elementText)
	{
		for (Gd2MlbFile gd2MlbFile : Gd2MlbFile.values( ))
		{
			if (elementText.startsWith(gd2MlbFile.getTag( )))
			{
				return true;
			}
		}
		return false;
	}
	
	public static final boolean isGdwMlbInningFileElement(final String elementText)
	{
		if (elementText.startsWith(INNING_ALL_TAG))
		{
			return true;
		}
		else if (elementText.contains("_") && elementText.contains("."))
		{
			try
			{
				Integer.parseInt(elementText.substring(elementText.indexOf("_") + 1, elementText.indexOf(".")));
				return true;
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString( )
	{
		return name( ).toLowerCase( );
	}
}
