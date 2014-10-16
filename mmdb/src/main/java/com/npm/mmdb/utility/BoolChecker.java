package com.npm.mmdb.utility;

public class BoolChecker
{
	public static final boolean checkAllNotNull(final Object... vals)
	{
		for (Object val : vals)
		{
			if (val == null)
			{
				return false;
			}
		}
		return true;
	}

	public static final boolean checkStringsNotNullNorEmpty(final String... vals)
	{
		for (String val : vals)
		{
			if (val == null || val.trim( ).equals(""))
			{
				return false;
			}
		}
		return true;
	}
	
	public static final boolean checkIntegersNotNullNorZero(final Integer... vals)
	{
		for (Integer val : vals)
		{
			if (val == null || val.intValue( ) == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static final boolean checkIntegersNotNullAndPositive(final Integer... vals)
	{
		for (Integer val : vals)
		{
			if (val == null || val.intValue( ) <= 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static final boolean checkIntsAllZero(final int... vals)
	{
		for (int val : vals)
		{
			if (val != 0)
			{
				return false;
			}
		}
		return true;
	}
}
