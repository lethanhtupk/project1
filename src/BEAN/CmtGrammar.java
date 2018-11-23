package BEAN;

public class CmtGrammar {
	
	private int cmtgrammarid;
	private int memberid;
	private String cmtgrammarcontent;
	private int grammarguidelineid;
	
	private String membername;
	
	public int getCmtgrammarid() {
		return cmtgrammarid;
	}
	public void setCmtgrammarid(int cmtgrammarid) {
		this.cmtgrammarid = cmtgrammarid;
	}
	
	
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	
	public String getCmtgrammarcontent() {
		return cmtgrammarcontent;
	}
	public void setCmtgrammarcontent(String cmtgrammarcontent) {
		this.cmtgrammarcontent = cmtgrammarcontent;
	}
	
	
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	
	
	public int getGrammarguidelineid() {
		return grammarguidelineid;
	}
	public void setGrammarguidelineid(int grammarguidelineid) {
		this.grammarguidelineid = grammarguidelineid;
	}

}
