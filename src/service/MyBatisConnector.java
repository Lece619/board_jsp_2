package service;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources; //어떤걸 import 할건지 잘 확인
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnector {
	
	SqlSessionFactory factory=null;
	
	
	//_single로 생성
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static MyBatisConnector single = null;

	public static MyBatisConnector getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new MyBatisConnector();
		//생성된 객체정보를 반환
		return single;
	}
	
	public MyBatisConnector() {
		// sqlMapConfig.xml 이라고 하는 설정파일을 읽어온다.
		try {
			//경로 설정시 config.mybatis의 sqlMapConfig.xml 과 똑같이 적어야 함
			
			//Reader : character 기반 stream
			Reader reader=Resources.getResourceAsReader("config/mybatis/sqlMapConfig.xml");
			
			// 위에서 읽어온 sqlMapConfig.xml에서 지정해둔 DB접근경로를
			// 실제로 읽어오는 작업
			// factory로 데이터 접근, 읽어오기 역할 (DB에 어떻게 접근해야 하는지 알고있는 변수)
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
		} catch (IOException e) {
			// 예외처리 (값이 없을 경우 대비)
			e.printStackTrace();
		}
	}
	
	//sqlMapConfig.xml의 정보를 담고있는 factory 객체 반환 
	//get + ctrl + space 자동완성
	
	// 다른곳에서도 factory를 사용할 수 있도록 설정해주어야함
	//getInstance().getFactory() 로 DAO에서 사용 가능
	public SqlSessionFactory getFactory() {
		return factory;
	}
	

	

}
