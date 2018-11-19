package vo;

public class TokensVO {
	private int tokens_sq;
	private String mac;
	private String token;
	public int getTokens_sq() {
		return tokens_sq;
	}
	public void setTokens_sq(int tokens_sq) {
		this.tokens_sq = tokens_sq;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "TokensVO [tokens_sq=" + tokens_sq + ", mac=" + mac + ", token=" + token + "]";
	}
}
