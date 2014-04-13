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
	public long dbid;
	
	public KaBanBanList(UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this.banned = banned;
		this.bannedName = bannedName;
		this.bannedTime = bannedTime;
		this.expireTime = expireTime;
		this.banner = banner;
		this.bannerName = bannerName;
		this.reason = reason;
		this.dbid = 0;
	}
	public KaBanBanList(long dbid, UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this(banned,bannedName,bannedTime,expireTime,banner,bannerName,reason);
		this.dbid = dbid;
	}
	
	public KaBanBanList() {
		;
	}
	
	public boolean hasExpired(){
		return this.expireTime.compareTo(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime()) >= 0;
	}
}
