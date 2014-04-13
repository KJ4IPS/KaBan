package guru.haun.kaban.persistance;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "ActiveBans")
public class ActiveBansDBO {
	
	
	public ActiveBansDBO(long id, UUID banned, String bannedName, Date bannedTime, Date expireTime, UUID banner, String bannerName, String reason){
		this.id = id;
		this.setBanned(banned);
		this.setBannedName(bannedName);
		this.setBannedTime(bannedTime);
		this.setExpireTime(expireTime);
		this.setBanner(banner);
		this.setBannerName(bannerName);
		this.setReason(reason);
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

	@Id
	private long id;
	
	@NotNull
	private UUID banned;
	
	@Length(max=17)
	@NotEmpty
	private String bannedName;
	
	@NotNull
	private Date bannedTime;
	
	@NotNull
	private Date expireTime;
	
	@Basic
	private String reason;
	
	@Basic
	private UUID banner;
	
	@Length(max=17)
	private String bannerName;
}
