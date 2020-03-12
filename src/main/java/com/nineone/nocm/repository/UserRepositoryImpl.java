package com.nineone.nocm.repository;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nineone.nocm.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private String namespace = "com.nineone.nocm.mapper.user";

	@Override
	public int insertUser(User user) {
		return sqlSession.insert(namespace + ".insertUser", user);
	}
	
	

	@Override
	public User getUserfindById(String id) {
		return sqlSession.selectOne(namespace + ".getUserfindById", id);
	}



	@Override
	public int insertSNSInfo(Map<String, Object> map) {
		return sqlSession.insert(namespace + ".insertSNSInfo", map);
	}


    
}
