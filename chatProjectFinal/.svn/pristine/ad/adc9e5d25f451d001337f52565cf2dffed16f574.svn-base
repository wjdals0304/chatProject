package chat.factory;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ChatSqlSessionFactory {

	private static ChatSqlSessionFactory instance;
	private static final String CONFIG = "mybatis-config.xml";
	private SqlSessionFactory sessionFactory;

	private ChatSqlSessionFactory() {
		//
		try {
			InputStream is = Resources.getResourceAsStream(CONFIG);
			// mybatis에서 SQL수행을 담당하는 객체인 SqlSession 객체 생성
			sessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ChatSqlSessionFactory createInstance() {
		//
		if (instance == null) {
			instance = new ChatSqlSessionFactory();
		}

		return instance;
	}

	public SqlSession createSqlSession() {
		//
		return sessionFactory.openSession();
	}
}
