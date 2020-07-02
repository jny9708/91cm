package com.nineone.nocm.repository;

import com.nineone.nocm.domain.Channel;
import com.nineone.nocm.domain.JoinInfo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelRepositoryImpl implements ChannelRepository {

	@Autowired
    private UserAuthoritiesRepository authoritiesRepository;

    @Autowired
    private SqlSessionTemplate sqlSession;

    private String namespace = "com.nineone.nocm.mapper.channel";

    @Override
    public Channel getChannel(int id) {
        return sqlSession.selectOne(namespace+ ".getChannel", id);
    }

    @Override
    public int insertChannel(Channel channel) {
        return sqlSession.insert(namespace + ".insertChannel",channel);
    }

    @Override
    public int deleteChannel(int channelId) {
        return sqlSession.delete(namespace+".deleteChannel", channelId);
    }

    @Override
    public int updateChannel(Channel channel) {
        return sqlSession.update(namespace + ".updateChannel",channel);
    }

    @Override
    public List<Channel> channelList(String userEmail) {
    	List<String> roles = authoritiesRepository.getUserRoles(userEmail);
    	String addQueryId = roles.contains("ROLE_ROOT")?"All":"";
    	return sqlSession.selectList(namespace + ".channelList" + addQueryId, userEmail);
    }

    @Override
	public int joinInfo(JoinInfo joinInfo) {
		return sqlSession.insert(namespace + ".insertJoinInfo", joinInfo);
	}
}