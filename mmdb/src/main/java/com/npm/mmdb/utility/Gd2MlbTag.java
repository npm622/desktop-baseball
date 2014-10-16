package com.npm.mmdb.utility;


import com.npm.mmdb.mybatis.model.gid.Gid;


public enum Gd2MlbTag
{
	ROOT, YEAR, MONTH, DAY, GID;
	
	public final String getTag( )
	{
		switch (this)
		{
			case ROOT:
				return "http://gd2.mlb.com/components/game/mlb/";
			case YEAR:
			case MONTH:
			case DAY:
			case GID:
				return toString( ) + "_";
			default:
				throw new IllegalArgumentException(this + " is not a supported enum value in Gd2MlbTag#getTag( )");
		}
	}
	
	public final String getUrlPart(final Integer num)
	{
		switch (this)
		{
			case ROOT:
				return getTag( );
			case YEAR:
				return getTag( ) + MmdbPrinter.fourDigits(num) + "/";
			case MONTH:
			case DAY:
				return getTag( ) + MmdbPrinter.twoDigits(num) + "/";
			case GID:
			default:
				throw new IllegalArgumentException(
						this + " is not a supported enum value in Gd2MlbTag#buildUrl(" + num + ")");
		}
	}
	
	public final int getDateNumberLength( )
	{
		switch (this)
		{
			case YEAR:
				return 4;
			case MONTH:
			case DAY:
				return 2;
			case ROOT:
			case GID:
			default:
				throw new IllegalArgumentException(
						this + " is not a supported enum value in Gd2MlbTag#getDateNumberLength( )");
		}
	}
	
	public static final String buildUrl(final Integer... nums)
	{
		StringBuilder sb = new StringBuilder(Gd2MlbTag.ROOT.getTag( ));
		int counter = 0;
		for (Integer num : nums)
		{
			switch (counter++)
			{
				case 0:
					sb.append(Gd2MlbTag.YEAR.getUrlPart(num));
					break;
				case 1:
					sb.append(Gd2MlbTag.MONTH.getUrlPart(num));
					break;
				case 2:
					sb.append(Gd2MlbTag.DAY.getUrlPart(num));
					break;
				default:
					throw new IllegalArgumentException(
							"Gd2MlbTag$buildUrl(...) does not support the input of " + nums.length + " numbers");
			}
		}
		return sb.toString( );
	}

	public static final String buildGidUrlPart(final Gid gid)
	{
		StringBuilder sb = new StringBuilder(
				GID.getTag( ) + MmdbPrinter.fourDigits(gid.getYear( )) + "_" + MmdbPrinter.twoDigits(gid.getMonth( )) + "_" + MmdbPrinter
						.twoDigits(gid.getDay( )) + "_" + gid.getAwayAbbrev( ) + gid.getAwaySportCode( ) + "_" + gid
						.getHomeAbbrev( ) + gid.getHomeSportCode( ) + "_" + gid.getGameNumber( ));
		if (gid.getNote( ) != null)
		{
			sb.append("_" + gid.getNote( ));
		}
		sb.append("/");
		return sb.toString( );
	}

	public static final String buildUrlFromGid(final Gid gid)
	{
		StringBuilder sb = new StringBuilder(buildUrl(gid.getYear( ), gid.getMonth( ), gid.getDay( )));
		if (BoolChecker.checkIntegersNotNullNorZero(gid.getYear( ), gid.getMonth( ), gid.getDay( )) && BoolChecker
				.checkAllNotNull(gid.getAwayAbbrev( ), gid.getAwaySportCode( ), gid.getHomeAbbrev( ),
						gid.getHomeSportCode( ), gid.getGameNumber( )))
		{
			sb.append(buildGidUrlPart(gid));
		}
		return sb.toString( );
	}
	
	@Override
	public String toString( )
	{
		return name( ).toLowerCase( );
	}
}
