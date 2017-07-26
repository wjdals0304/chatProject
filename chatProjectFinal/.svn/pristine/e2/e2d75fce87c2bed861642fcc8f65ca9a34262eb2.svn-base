package chat.domain;

public enum Category {

	ALL("ALL", "전체")
	,EDUCATION("EDU", "교육")
	,GAME("GAM", "게임")
	,COOKING("CUK", "요리")
	,TRAVEL("TRA", "여행")
	,SPORTS("SPO", "스포츠")
	,AREA("ARE","지역")
	,ENTERTAINMENT("ENT", "엔터테인먼트")
	,CHILDCARE("CHI","육아")
	,ETC("ETC", "기타");
	
	private String code;
	private String krName;
	
	private Category(){
		
	}
	
	private Category(String code, String krName){
		this.code = code;
		this.krName = krName;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKrName() {
		return krName;
	}

	public void setKrName(String krName) {
		this.krName = krName;
	}
	//카테고리 정보 조회하는 것.
	public static Category getByCode(String code){
		
		//가진거 다 조회한다음에 배열에 저장
		Category[] cates = Category.values();
		
		//조회 하고 거기에 맞는 카테고리 정보 리턴
		for(Category cate : cates){
			if(code.equals(cate.getCode())){
				return cate;
			}
		}
		return null;
	}
	
}
