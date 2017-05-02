package vo;

public class BookVo {

	Long no;
	Long author_no;
	Long price;
	String name;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getAuthor_no() {
		return author_no;
	}

	public void setAuthor_no(Long author_no) {
		this.author_no = author_no;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BookVo [no=" + no + ", author_no=" + author_no + ", price=" + price + ", name=" + name + "]";
	}

}
