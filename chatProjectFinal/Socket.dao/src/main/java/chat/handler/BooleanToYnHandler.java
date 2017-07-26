package chat.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

//여기서 선언한 TypeHandler<Boolean>의 Boolean이 제너릭으로 설정해 주었기 때문에 전체적으로 Boolean에 대한
//소스전개가 진행된다고 합니당.
public class BooleanToYnHandler implements TypeHandler<Boolean> {
	
	//@param ResultSet rs SQL 조회결과 데이터
	//@param String columnName  타입핸들러를 적용한 컬럼이름.
	//여기서 처리해줘야함. Mapper 같은데서 이쪽으로 보내준 값을 실제로 로직에서 사용할 값으로 리턴 해줘야됨.
	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		// ResultSet은 조회한 내용이고, columnName은 실제 컬럼 이름.

		String data = rs.getString(columnName);
		
		if("Y".equals(data)){
			return Boolean.TRUE;
		}else {
		//둘 중에 어느 것을 사용해도 상관 없음, wrapper class에서의 변환은 자동으로 해줌.
			return Boolean.FALSE;
		}
	}
	
	//column 순서로 호출할 경우.
	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		
		String data = rs.getString(columnIndex);
		
		if("Y".equals(data)){
			return Boolean.TRUE;
		} else{
		//둘 중에 어느 것을 사용해도 상관 없음, wrapper class에서의 변환은 자동으로 해줌.
			return Boolean.FALSE;
		}
	}

	//찾아봐야쥥. PLSQL 호출할때 사용 .
	@Override
	public Boolean getResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	//실제 로직에서 사용하던 boolean값이 Boolean param으로 온 것.
	@Override
	public void setParameter(PreparedStatement pstmt
							, int paramIndex
							, Boolean param
							, JdbcType jdbcType) throws SQLException {
		//
		if( param ){
			pstmt.setString(paramIndex, "Y");
		}else{
			pstmt.setString(paramIndex, "N");
		}
	}
	
}
