package guru.haun.kaban;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class KaBanBanEntry {
	private UUID banned;
	private String bannedName;
	private UUID banner;
	private String bannerName;
	private String reason;
	private Date bannedTime;
	private  Date expireTime;
	private long dbid;
	
	public KaBanBanEntry(UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this.banned = banned;
		this.bannedName = bannedName;
		this.bannedTime = bannedTime;
		this.expireTime = expireTime;
		this.banner = banner;
		this.bannerName = bannerName;
		this.reason = reason;
		this.dbid = 0;
	}
	public KaBanBanEntry(long dbid, UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this(banned,bannedName,bannedTime,expireTime,banner,bannerName,reason);
		this.dbid = dbid;
	}
	
	public boolean hasExpired(){
		return this.expireTime.compareTo(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime()) >= 0;
	}
	public UUID getBanned() {
		return banned;
	}
	public void setBanned(UUID banned) {
		this.banned = banned;
	}
	public String getBannedName() {
		return bannedName;
	}
	public void setBannedName(String bannedName) {
		this.bannedName = bannedName;
	}
	public UUID getBanner() {
		return banner;
	}
	public void setBanner(UUID banner) {
		this.banner = banner;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getBannedTime() {
		return bannedTime;
	}
	public void setBannedTime(Date bannedTime) {
		this.bannedTime = bannedTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public long getDbid() {
		return dbid;
	}
	public void setDbid(long dbid) {
		this.dbid = dbid;
	}
}
