package er.extensions.formatters;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ERXJodaDateTimeFormatter extends Format {
	private transient DateTimeFormatter formatter;
	private String _pattern;
	private BaseChronology _chronology;
	private Locale _locale;
	private DateTimeZone _zone;
	
	public ERXJodaDateTimeFormatter(String pattern) {
		this(pattern, null, null, null);
	}
	
	public ERXJodaDateTimeFormatter(String pattern, DateTimeZone zone) {
		this(pattern, null, null, zone);
	}
	
	public ERXJodaDateTimeFormatter(String pattern, TimeZone zone) {
		this(pattern, null, null, DateTimeZone.forTimeZone(zone));
	}
	
	public ERXJodaDateTimeFormatter(String pattern, Locale locale, TimeZone zone) {
		this(pattern, null, locale, DateTimeZone.forTimeZone(zone));
	}
	
	public ERXJodaDateTimeFormatter(String pattern, BaseChronology chronology, Locale locale, DateTimeZone zone) {
		_pattern = pattern;
		_chronology = chronology;
		_locale = locale;
		_zone = zone;
	}
	
	public ERXJodaDateTimeFormatter(Locale locale, String style) {
		this(DateTimeFormat.patternForStyle(style, locale));
	}
	
	protected DateTimeFormatter formatter() {
		if(formatter == null) {
			formatter = DateTimeFormat.forPattern(_pattern);
			if(_chronology != null) { formatter = formatter.withChronology(_chronology); }
			if(_locale != null) { formatter = formatter.withLocale(_locale); }
			if(_zone != null) { formatter = formatter.withZone(_zone); }
		}
		return formatter;
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer buffer, FieldPosition pos) {
		formatter().printTo(buffer, (DateTime)obj);
		return buffer;
	}

	@Override
	public DateTime parseObject(String str, ParsePosition pos) {
		DateTime dt = formatter().parseDateTime(str);
		pos.setIndex(str.length());
		return dt;
	}

}
