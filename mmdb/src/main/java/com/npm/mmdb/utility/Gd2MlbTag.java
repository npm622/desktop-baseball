package com.npm.mmdb.utility;

public enum Gd2MlbTag
{
	ROOT, YEAR, MONTH, DAY;
	
	public final String getTag( )
	{
		switch (this)
		{
			case ROOT:
				return "http://gd2.mlb.com/components/game/mlb/";
			case YEAR:
			case MONTH:
			case DAY:
				return toString( ) + "_";
			default:
				throw new IllegalArgumentException(this + " is not a supported enum value in Gd2MlbTag#getTag( )");
		}
	}
	
	public final String buildUrl(final String prefix, final Integer num)
	{
		switch (this)
		{
			case ROOT:
				return getTag( );
			case YEAR:
				return prefix + getTag( ) + MmdbPrinter.fourDigits(num) + "/";
			case MONTH:
			case DAY:
				return prefix + getTag( ) + MmdbPrinter.twoDigits(num) + "/";
			default:
				throw new IllegalArgumentException(
						this + " is not a supported enum value in Gd2MlbTag#buildUrl(" + prefix + ", " + num + ")");
		}
	}
	
	@Override
	public String toString( )
	{
		return name( ).toLowerCase( );
	}
}
