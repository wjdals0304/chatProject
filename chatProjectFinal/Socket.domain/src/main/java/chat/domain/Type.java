package chat.domain;

public enum Type {

	FILEUP("FUP","파일업로드")
	,FILEDOWN("FDW", "파일다운로드")
	,FILEERROR("FER", "파일에러")
	,IMG("IMG", "이미지")
	,TEXT("TXT", "메시지")
	,KICK("KIK", "강제퇴장요청")
	,KICKOK("KOK","강제퇴장승인")
	,KICKERROR("KER", "강제퇴장에러")
	,INVITE("INV","초대요청")
	,INVITEOK("IOK","초대승인")
	,INVITEERROR("IER","초대에러")
	,EXIT("EXT","퇴장요청")
	,EXITOK("EOK","퇴장승인")
	,EXITERROR("EER","퇴장에러")
	,GRANT("GNT","위임요청")
	,GRANTOK("GOK","위임승인")
	,GRANTERROR("GER", "위임에러");
	
	private String code;
	private String krName;
	
	private Type(){
		
	}
	private Type(String code, String krName){
		this.code = code;
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
	
	public static Type getByCode(String code){
		Type[] types = Type.values();
		for(Type type : types){
			if(code.equals(type.getCode())){
				return type;
			}
		}
		return null;
	}
	
}
