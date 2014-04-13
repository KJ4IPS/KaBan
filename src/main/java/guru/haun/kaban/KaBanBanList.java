package guru.haun.kaban;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class KaBanBanList {
	public UUID banned;
	public String bannedName;
	public UUID banner;
	public String bannerName;
	public String reason;
	public Date bannedTime;
	public Date expireTime;
	
	public boolean hasExpired(){
		return this.expireTime.compareTo(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime()) >= 0;
	}
}
