package com.npm.mmdb.utility.web;


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;

import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.Gid.GidBuilder;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.mybatis.model.gid.GidFile.GidFileBuilder;
import com.npm.mmdb.utility.BoolChecker;



public class WebMlb
{
	private static final String	ABS_HREF				= "abs:href";
	private static final String	GD2_GAME_NBR			= "{GAME_NBR}";
	private static final String	MOCK_GAME_NBR			= "{GAME$NBR}";
	private static final String	GD2_AWAY_SPORT_CODE		= "{AWAY_SPORT_CODE}";
	private static final String	MOCK_AWAY_SPORT_CODE	= "{AWAY$SPORT$CODE}";

	public static final List<GidFile> getGidFilesFromGidPage(final Gid gid) throws IOException
	{
		String gidPageUrl = Gd2MlbTag.buildUrlFromGid(gid);
		List<GidFile> gidFiles = new LinkedList<>( );
		for (Iterator<Element> elemIter = NetworkTools.getPageLinks(gidPageUrl).iterator( ) ; elemIter.hasNext( ) ;)
		{
			Element element = elemIter.next( );
			String elementText = element.ownText( );
			// System.out.println(elementText);
			if (element.attr(ABS_HREF).length( ) < gidPageUrl.length( ))
			{
				continue;
			}
			else if (Gd2MlbFile.isGd2MlbFileElement(elementText))
			{
				if (!elementText.startsWith(Gd2MlbFile.INNING.getTag( )))
				{
					gidFiles.add(parseGidFile(new GidFileBuilder( ), elementText).build( ));
				}
				else
				{
					for (Iterator<Element> inningElemIter = NetworkTools.getPageLinks(element.attr(ABS_HREF))
							.iterator( ) ; inningElemIter.hasNext( ) ;)
					{
						Element inningElement = inningElemIter.next( );
						String inningText = inningElement.attr(ABS_HREF).substring(gidPageUrl.length( ));
						if (Gd2MlbFile.isGdwMlbInningFileElement(inningElement.ownText( )))
						{
							gidFiles.add(parseGidFile(new GidFileBuilder( ), inningText).build( ));
						}
					}
				}
			}
		}
		return gidFiles;
	}

	public static final List<Gid> getGidsFromDayPage(final Integer year, final Integer month, final Integer day) throws IOException
	{
		if (BoolChecker.checkIntegersNotNullNorZero(year, month, day))
		{
			List<Gid> gids = new LinkedList<>( );
			for (Iterator<Element> elemIter = NetworkTools.getPageLinks(Gd2MlbTag.buildUrl(year, month, day))
					.iterator( ) ; elemIter.hasNext( ) ;)
			{
				Element element = elemIter.next( );
				String elementText = element.ownText( );
				if (elementText.startsWith(Gd2MlbTag.GID.getTag( )))
				{
					gids.add(parseGid(new GidBuilder( ).year(year).month(month).day(day), elementText).build( ));
				}
			}
			return gids;
		}
		else
		{
			throw new IllegalArgumentException(
					"WebMlb$getGidsFromDayPage(" + year + ", " + month + ", " + day + ") cannot handle this combination of inputs");
		}
	}
	
	public static final List<Integer> getDateNumbersFromPageLinks(final String url, final Gd2MlbTag match) throws IOException
	{
		List<Integer> dateNumbers = new LinkedList<>( );
		for (Iterator<Element> elemIter = NetworkTools.getPageLinks(url).iterator( ) ; elemIter.hasNext( ) ;)
		{
			Element element = elemIter.next( );
			if (element.ownText( ).startsWith(match.getTag( )))
			{
				String numText = element.ownText( ).substring(element.ownText( ).indexOf("_") + 1);
				try
				{
					int num = Integer.parseInt(numText.substring(0, match.getDateNumberLength( )));
					dateNumbers.add(new Integer(num));
				}
				catch (NumberFormatException e)
				{
					continue;
				}
			}
		}
		return dateNumbers;
	}

	public static final GidBuilder parseGid(final GidBuilder iBuilder, final String iGidText)
	{
		GidBuilder builder = iBuilder;
		String gidText = iGidText;
		if (gidText.endsWith("/"))
		{
			gidText = gidText.substring(0, gidText.length( ) - 1);
		}
		if (gidText.contains(GD2_AWAY_SPORT_CODE))
		{
			gidText = gidText.replace(GD2_AWAY_SPORT_CODE, MOCK_AWAY_SPORT_CODE);
		}
		if (gidText.contains(GD2_GAME_NBR))
		{
			gidText = gidText.replace(GD2_GAME_NBR, MOCK_GAME_NBR);
		}
		int i = 0;
		for (String gidPart : gidText.split("_"))
		{
			switch (i++)
			{
				case 0:	// "gid"
				case 1:	// "year"
				case 2:	// "month"
				case 3:	// "day"
					break;
				case 4: // "awayTeam"
					if (gidPart.length( ) > 3)
					{
						builder = builder.awayAbbrev(gidPart.substring(0, 3));
						if (gidPart.contains(MOCK_AWAY_SPORT_CODE))
						{
							gidPart = gidPart.replace(MOCK_AWAY_SPORT_CODE, GD2_AWAY_SPORT_CODE);
						}
						builder = builder.awaySportCode(gidPart.substring(3));
					}
					else
					{
						builder = builder.awayAbbrev(gidPart);
					}
					break;
				case 5: // "homeTeam"
					if (gidPart.length( ) > 3)
					{
						builder = builder.homeAbbrev(gidPart.substring(0, 3));
						builder = builder.homeSportCode(gidPart.substring(3));
					}
					else
					{
						builder = builder.homeAbbrev(gidPart);
					}
					break;
				case 6: // "gameNumber"
					if (gidPart.contains(MOCK_GAME_NBR))
					{
						gidPart = gidPart.replace(MOCK_GAME_NBR, GD2_GAME_NBR);
					}
					builder = builder.gameNumber(gidPart);
					break;
				case 7: // "note"
					builder = builder.note(gidPart);
			}
		}
		return builder;
	}
	
	public static final GidFileBuilder parseGidFile(final GidFileBuilder iBuilder, final String iGidFileText)
	{
		GidFileBuilder builder = iBuilder;
		String gidFileText = iGidFileText;
		if (gidFileText.contains("/"))
		{
			builder = builder.localPath(gidFileText.substring(0, gidFileText.indexOf("/")));
			gidFileText = gidFileText.substring(gidFileText.indexOf("/") + 1);
		}
		builder.filename(gidFileText.substring(0, gidFileText.indexOf(".")));
		builder.ext(gidFileText.substring(gidFileText.indexOf(".") + 1));
		return builder;
	}
}
