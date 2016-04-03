package it.cineca.iris.ir.rest.model;

public class InternalAuthorRestDTO {
    private String author;
    private int share;
    private int order;
    private String authority;
    
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

    public InternalAuthorRestDTO() {
        super();
    }
    
    public InternalAuthorRestDTO(String author, int share,int order, String authority){
        super();
        this.author = author;
        this.share = share;
        this.order = order;
        this.authority = authority;
    }
}
