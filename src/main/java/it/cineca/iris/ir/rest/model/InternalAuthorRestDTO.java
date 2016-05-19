package it.cineca.iris.ir.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalAuthorRestDTO {
    private String author;
    private String authority;
    
    private int share;
    private int order;    
    private int confidence;
    
	public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getShare() {
        return share;
    }
    public void setShare(int share) {
        this.share = share;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    
    public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
    public int getConfidence() {
        return confidence;
    }
    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public InternalAuthorRestDTO() {
        super();
    }
    
    public InternalAuthorRestDTO(String author, int share,int order, String authority, int confidence){
        super();
        this.author = author;
        this.share = share;
        this.order = order;
        this.authority = authority;
        this.confidence = confidence;
    }
}
