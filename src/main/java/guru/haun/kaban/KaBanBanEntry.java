package guru.haun.kaban;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity
@Table(name="ActiveBans")
public class KaBanBanEntry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@NotNull
	private UUID banned;
	
	@NotEmpty
	@Length(max=17)
	private String bannedName;
	
	@NotNull
	private Date bannedTime;
	
	@NotNull
	private Date expireTime;
	
	private UUID banner;
	
	@NotEmpty
	@Length(max=17)
	private String bannerName;
	
	@NotEmpty
	@Length(max=255)
	private String reason;

	
	public KaBanBanEntry(UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this.banned = banned;
		this.bannedName = bannedName;
		this.bannedTime = bannedTime;
		this.expireTime = expireTime;
		this.banner = banner;
		this.bannerName = bannerName;
		this.reason = reason;
		this.id = 0;
	}
	public KaBanBanEntry(long dbid, UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this(banned,bannedName,bannedTime,expireTime,banner,bannerName,reason);
		this.id = dbid;
	}
	
	public KaBanBanEntry(){
		this.id = 0;
	}
	
	public boolean hasExpired() {
		if(this.isPerma())
			return false;
		return this.expireTime.compareTo(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime()) <= 0;
	}
	
	public boolean isPerma() {
		Calendar timeZero = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		timeZero.setTimeInMillis(0);
		return this.expireTime.equals(timeZero.getTime());
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
