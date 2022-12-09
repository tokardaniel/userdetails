package hu.dansz.userdetails;

import hu.dansz.userdetails.db.DatabaseSeeding;
import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see hu.dansz.userdetails.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private Properties properties;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		DatabaseSeeding databaseSeeding = new DatabaseSeeding();
		databaseSeeding.seedingDb();

		// needed for the styling used by the quickstart
		getCspSettings().blocking()
			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
			.add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
			.add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

		// add your configuration here
	}
}
