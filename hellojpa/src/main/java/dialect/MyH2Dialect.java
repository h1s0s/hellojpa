package dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect extends H2Dialect {

	//사용자 정의 함수를 JPA에 등록하는 방법
	//persistence.xml도 수정해야함
	public MyH2Dialect(){
		registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
	}
}
